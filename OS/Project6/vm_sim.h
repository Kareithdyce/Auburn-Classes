#include <string>
#define OFFSET_BITS 8
#define OFFSET_MASK 0xFF
#define NUM_PAGES 256
#define NUM_FRAMES 256
#define PAGE_SIZE 256
#define TLB_SIZE 16
#define PHYS_MEM 65536
#define backing_store "BACKING_STORE"
using namespace std;

typedef unsigned int laddress_t;
typedef unsigned int paddress_t;

typedef unsigned int page_t;
typedef unsigned int frame_t;
typedef unsigned int offset_t;

typedef unsigned char byte_t;

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
    laddress_t laddress[NUM_PAGES];
    /* next candidate entry to be replaced/used */
    unsigned int size;
}laddress_list_t;


typedef struct {    
    page_t pages[NUM_PAGES];
    /* next candidate entry to be replaced/used */
    unsigned int next_ptr;
}page_table_t;

/* TLB */
int initTLB(tlb_t &t);
int searchTLB(page_t page, tlb_t t, frame_t &frame);
int TLB_display(tlb_t tlb);
int TLB_replacement_FIFO(page_t page_num, frame_t frame_num, tlb_t  &t);

/* Page Table*/
int init_pageTable(page_table_t &pageTable);
int search_pageTable(page_t page, page_table_t table, frame_t &frame_num);

/* Logic Address*/
int laddressLoader(string file, laddress_list_t &list);
int extract_laddress(laddress_t laddress, page_t &page_num, offset_t &offset);

int pageFaultHandler(page_t page_num, tlb_t &t);
                                                                                                                             
/* Physical Address*/
int create_paddress(frame_t frame_num, offset_t offset, paddress_t &paddress);
int read_paddress(paddress_t paddress, int value);

