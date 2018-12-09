/** 
 * Project 6
 * This is my message header which defines the welcome user message
 * By Kareith Dyce
 */
#include "intro.h"
#include "vm_sim.h"
#include <iostream>


void welcomeMessage(){
    cout<<"Welcome to Kareith's VM Simulator Version 1.0"<<endl;
    cout<<"Number of logical pages: "<<NUM_PAGES<<endl;
    cout<<"Page size: "<<PAGE_SIZE<<" bytes"<<endl;
    cout<<"Page table size: "<<NUM_PAGES<<endl;
    cout<<"TLB size: "<<TLB_SIZE<<" entries"<<endl;
    cout<<"Number of physical frames: "<<NUM_FRAMES<<endl;
    cout<<"Physical memory size: "<<PHYS_MEM<<" bytes"<<endl<<endl;
    cout<<"Display Physical Addresses? [yes or no] ";
}
    
    