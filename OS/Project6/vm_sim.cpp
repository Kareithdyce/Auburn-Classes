#include "vm_sim.h"
#include <string>
#include <iostream>
#include <fstream>
#include <cstdlib>

using namespace std;

int init_TLB(tlb_t &TLB){
    TLB.next_tlb_ptr = 0;
    return 0;
} 


int init_page_table(page_table_t &pageTable){
    pageTable.next_ptr = 0;
    return 0; 
} 

int logic_address_loader(string file, laddress_list_t &laddress_list){
    ifstream read;
    read.open((char*)file.c_str());
    if(read.fail()){
        cout<<"Error!"<<endl;
        return -1;

    }
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
    
    /*TLB and pagetable*/
    tlb_t tlb;
    page_table_t page_table;

    //physical_memory_t physical_memory;
    
    /*Value and address-value list*/
    //value_t value;
    //address_value_list_t address_value_list;

    /*Boolean for TLB hit and pgae fault*/
    bool is_tlb_hit;
    bool is_page_fault;
    
    /*Input and Output file names*/
    string input_file = "input_logical_address_file";
    string output_file = "output_physical_address_value";

    /*Initialize the system*/
    init_TLB(tlb);
    init_page_table(page_table);

    /* Create a logical address list from the file*/
    logic_address_loader(input_file, laddressList);
    

    

    return 0;
}