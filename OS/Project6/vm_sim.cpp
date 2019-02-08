/** 
 * Project 6
 * This is my main method which runs everything
 * By Kareith Dyce
 */
#define MAKEFILE
//Commet this out to use makefile It wouldn't work 
//on my computer
#include "intro.h"
#include "vm_sim.h"
#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <cstdlib>
#include <errno.h>
#include <unordered_map>




using namespace std;
string backing_store = "BACKING_STORE";
float pageFaults = 0;
float tlbHits = 0;
string choice;
unordered_map<page_t, string> mainMem;

/* TLB */
int initTLB(tlb_t &t){
    unsigned int i;
    t.next_tlb_ptr = 0;
    for(i = 0; i < TLB_SIZE; i++){
        //t.tlb_entry
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
    if(!t.tlb_entry[t.next_tlb_ptr].valid){
           t.tlb_entry[t.next_tlb_ptr].valid = true;
    }
    t.tlb_entry[t.next_tlb_ptr].page_num = page;
    t.tlb_entry[t.next_tlb_ptr].frame_num = frame;
    t.next_tlb_ptr = ((t.next_tlb_ptr+1) % TLB_SIZE);
    return 0;
}

int TLB_replacement_LRU(page_t page, frame_t frame, tlb_t &t){
    int i = 0, max = 0, index = 0;  
    for(i; i < TLB_SIZE; i++){
        if(!t.tlb_entry[i].valid){
            t.tlb_entry[i].valid = true;
            t.tlb_entry[i].page_num = page;
            t.tlb_entry[i].frame_num = frame;
            t.tlb_entry[i].counter = 0;
            return 0;
        }
        if(t.tlb_entry[i].counter > max){
            index = i;
            max = t.tlb_entry[i].counter;
        }
    }
    t.tlb_entry[index].page_num = page;
    t.tlb_entry[index].frame_num = frame;
    t.tlb_entry[index].counter = 0;
    return 0;
}


int searchTLB(page_t page, tlb_t &t, frame_t &frame, page_table_t &table){
    int i = 0;
    for(i; i < TLB_SIZE; i++){
        if(!t.tlb_entry[i].valid){
            break;
        }
        if(t.tlb_entry[i].page_num == page){
            frame= t.tlb_entry[i].frame_num;
            tlbHits++;
            return 0;
        }
    }
    return search_pageTable(page, t, frame, table);
}



int pageFaultHandler(page_t page, tlb_t &t, frame_t &frame, page_table_t &table){
    //cout<<"Page Fault"<<endl;
    pageFaults++;
    FILE *file = fopen("BACKING_STORE", "r+b");
    char storePage[PAGE_SIZE];
    if(file == 0){
        printf("Could not open file\n");
    }
    else{
        fseek(file, page*PAGE_SIZE, SEEK_SET);
        fread(storePage, sizeof(char), PAGE_SIZE, file);
        if(choice == "1"){
            TLB_replacement_FIFO(page, frame, t);
        }else{
            TLB_replacement_LRU(page, frame, t);
        }
        pageTableUpdate(page, frame, table);
        mainMem.insert({page, storePage});
    }

    fclose(file);
    return 0;
}



/* Page Table*/
int init_pageTable(page_table_t &pageTable){
    unsigned int i;
    pageTable.next_ptr = 0;
    for(i = 0; i < NUM_PAGES; i++){
        pageTable.pages[i].valid = false;
    } 
    return 0;  
} 


int search_pageTable(page_t page, tlb_t &t, frame_t &frame, page_table_t &table){
    int i = 0;
    for(i; i < PAGE_SIZE; i++){
        if(!table.pages[i].valid){
            break;
        }
        if(table.pages[i].page_num == page){
            frame = table.pages[i].frame_num;
            return 0;
        }
    }
    return pageFaultHandler(page, t, frame, table);
    }

int pageTableUpdate(page_t page, frame_t frame, page_table_t  &table){
    table.pages[table.next_ptr].page_num = page;
    table.pages[table.next_ptr].frame_num = frame;
    table.pages[table.next_ptr].valid = true;
    table.next_ptr++;
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
    int value;
    while(read>>value){
        list.aList.push_back(value);
    }
       

    read.close();
    return 0;
}


int extract_laddress(laddress_t laddress, page_t &page, offset_t &offset){
    page = laddress >> OFFSET_BITS;
    offset = laddress & OFFSET_MASK;
    return 0;
}

int create_paddress(frame_t frame_num, offset_t offset, paddress_t &paddress){
    paddress = frame_num << OFFSET_BITS | offset;
    return 0;
}

int main(){
    /*Set up Variables*/
    page_t page_num;
    frame_t frame_num;
    offset_t offset;

    /*TLB and pagetable*/
    tlb_t tlb;
    page_table_t pageTable;


    /*Addresses*/
    paddress_t paddress;
    laddress_t laddress;
    laddress_list_t laddressList;
    
   
    physical_memory_t physical_memory;
    
    /*Value and address-value list*/
    
    /*Input and Output file names*/
    string input_file = "InputFile.txt";
    string output_file = "vm_sim_output.txt";

    /*Initialize the system*/
    initTLB(tlb);
    init_pageTable(pageTable);
    #ifdef MAKEFILE
    //welcomeMessage();
    #else
    cout<<"Welcome to Kareith's VM Simulator Version 1.0"<<endl;
    cout<<"Number of logical pages: "<<NUM_PAGES<<endl;
    cout<<"Page size: "<<PAGE_SIZE<<" bytes"<<endl;
    cout<<"Page table size: "<<NUM_PAGES<<endl;
    cout<<"TLB size: "<<TLB_SIZE<<" entries"<<endl;
    cout<<"Number of physical frames: "<<NUM_FRAMES<<endl;
    cout<<"Physical memory size: "<<PHYS_MEM<<" bytes"<<endl<<endl;
    cout<<"Display Physical Addresses? [yes or no] ";
    #endif
    string ans = "";
    cin>>ans;
    do{
    cout<<"\nChoose TLB Replacement Strategy [1: FIFO, 2: LRU] ";
    cin>>choice;
    }while(choice != "1" && choice != "2");



    /* Create a logical address list from the file*/
    laddressLoader(input_file, laddressList);
    int i = 0;
    float size = laddressList.aList.size();
    ofstream read;
    read.open((char*)file.c_str());
    for(i; i < size; i++){
        extract_laddress(laddressList.aList.at(i), page_num, offset);
        searchTLB(page_num, tlb, frame_num, pageTable);
        create_paddress(frame_num,offset, paddress);
        
        if(ans == "yes" || ans == "y"){
            printf("Virutal Address: %d Physical address: %d Value: %d\n", laddressList.aList.at(i), paddress, mainMem[page_num][offset]);
        }
    }
    cout<<"Page fault rate: "<<((pageFaults * 100)/(float)(size))<<" %"<<endl;
    cout<<"TLB hit rate: "<<((tlbHits*100)/(float)size) <<" %"<<endl;
    return 0;
}