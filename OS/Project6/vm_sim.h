#include <string>
#define OFFSET_BITS 8
#define OFFSET_MASK 0xFF
#define NUM_PAGES 256
#define NUM_FRAMES 256
#define PAGE_SIZE 256
#define TLB_SIZE 16
#define PHYS_MEM 65536
using namespace std;


typedef unsigned int laddress_t;
typedef unsigned int paddress_t;

typedef unsigned int page_t;
typedef unsigned int frame_t;
typedef unsigned int offset_t;


typedef struct{
    page_t page_num;
    frame_t frame_num;
    bool valid;
}tlb_entry_t;

typedef struct {    
    tlb_entry_t tlb_entry[TLB_SIZE];
    /* next candidate entry to be replaced/used */
    unsigned int next_tlb_ptr;
}tlb_t;


typedef struct {    
    laddress_t laddress[TLB_SIZE];
    /* next candidate entry to be replaced/used */
    unsigned int next_ptr;
}laddress_list_t;


typedef struct {    
    page_t page[NUM_PAGES];
    /* next candidate entry to be replaced/used */
    unsigned int next_ptr;
}page_table_t;

int init_TLB(tlb_t &tlb);
int init_page_table(page_table_t &pageTable);

int TLB_replacement_FIFO(page_t page_num, frame_t frame_num, tlb_t* tlb);

int TLB_display(tlb_t tlb);

int extract_laddress(laddress_t laddress, page_t &page_num, offset_t &offset);

int search_TLB(page_t page_num, tlb_t tlb, frame_t frame);

int search_pageTable(page_t page_num, tlb_t tlb, frame_t &frame_num);

int page_faultHandler(page_t page_num, tlb_t &tlb);
                                                                                                                             
int create_paddress(frame_t frame_num, offset_t offset, paddress_t paddress);

int read_paddress(paddress_t paddress, int value);

int logic_address_loader(string file, laddress_list_t &laddress_list);
