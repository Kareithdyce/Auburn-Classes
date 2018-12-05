#include "vm_sim.h"
#include <string>
#include <iostream>
#include <fstream>
#include <cstdlib>

using namespace std;
 /*TLB and pagetable*/
tlb_t tlb;
page_table_t pageTable;


/* TLB */
int initTLB(tlb_t &t){
    unsigned int i;
    t.next_tlb_ptr = 0;
    for(i = 0; i < TLB_SIZE; i++){
        t.tlb_entry[i].valid = false;
    } 
    return 0;
} 

int TLB_display(tlb_t t){
    unsigned int i;
    for(int i = 0; i < TLB_SIZE; i++){
        printf("TLB entry %d, page num: %d, frame num: %d, ", i,
        t.tlb_entry[i].page_num, t.tlb_entry[i].frame_num);
        
        if(t.tlb_entry[i].valid){
           printf("Invalid\n"); 
        }else{
            printf("Valid\n");
        }
        return 0;
    }
}

int TLB_replacement_FIFO(page_t page, frame_t frame, tlb_t &t){
    t.tlb_entry[t.next_tlb_ptr].page_num = page;
    t.tlb_entry[t.next_tlb_ptr].frame_num = frame;
    t.next_tlb_ptr = (t.next_tlb_ptr+1 % TLB_SIZE);
    return 0;
}

int searchTLB(page_t page, tlb_t t, frame_t &frame){
    int i = 0;
    for(i; i < TLB_SIZE; i++){
        if(!t.tlb_entry[i].valid){
            break;
        }
        if(t.tlb_entry[i].page_num == page){
            frame= t.tlb_entry[i].frame_num;
            return 0;
        }
    }
    search_pageTable(page, pageTable, frame);
    return 0;
}



/* Page Table*/
int init_pageTable(page_table_t &pageTable){
    return 0; 
} 


int search_pageTable(page_t page, page_table_t table, frame_t &frame){
    int i = 0;
    for(i; i < PAGE_SIZE; i++){
        if(table.pages[i] == page){
            //frame = tlb.tlb_entry[i].frame_num;
            return 0;
        }
    }
    return 0;
}


/* Logic Address */
int laddressLoader(string file, laddress_list_t &list){
    ifstream read;
    read.open((char*)file.c_str());
    if(read.fail()){
        cout<<"Error!"<<endl;
        return -1;
    }
    list.size = 0;
    int value;
    while(read>>value){
        list.laddress[list.size] = value;
        list.size++;
    }
    return 0;
}


int extract_laddress(laddress_t laddress, page_t &page, offset_t &offset){
    page = laddress >> OFFSET_BITS;
    offset = laddress & OFFSET_MASK;
    return 0;
}


int main(){
    /*Set up Variables*/
    page_t page_num;
    frame_t frame_num;
    offset_t offset;
    
    /*Addresses*/
    paddress_t paddress;
    laddress_t laddress;
    laddress_list_t laddressList;
    
   
    //physical_memory_t physical_memory;
    
    /*Value and address-value list*/
    //value_t value;
    //address_value_list_t address_value_list;

    /*Boolean for TLB hit and pgae fault*/
    bool is_tlb_hit;
    bool is_page_fault;
    
    /*Input and Output file names*/
    string input_file = "testinput.txt";
    string output_file = "output_physical_address_value";

    /*Initialize the system*/
    initTLB(tlb);
    init_pageTable(pageTable);

    /* Create a logical address list from the file*/
    laddressLoader(input_file, laddressList);
    int i = 0;
    for(i; i < TLB_SIZE; i++){
        extract_laddress(laddressList.laddress[i], page_num, offset);
        searchTLB(page_num, tlb, frame_num);
    }

    

    return 0;
}