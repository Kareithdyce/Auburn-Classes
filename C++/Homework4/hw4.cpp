    /**
     * Name:Kareith Dyce kzd0030
     * Student ID: 903700162
     * File Name : hw4.exe
     * Ran using PuTTY
     * kzdd0030@tux062:~/Desktop/hw4$ vim hw4.cpp
     * wrote code
     * kzdd0030@tux062:~/Desktop/hw4$ g++ -o hw4 hw4.cpp
     * kzdd0030@tux062:~/Desktop/hw4$ ./hw4
    */
#include <fstream>
#include <cstdlib>
#include <iostream>
#include <cassert>
#include <termios.h>
#include <unistd.h>
    
using namespace std;

const int MAX_SIZE = 100;
/**
 * Get the char from the keyboard. 
 */
int mygetch ( void ) 
{
  int ch;
  struct termios oldt, newt;
  
  tcgetattr ( STDIN_FILENO, &oldt );
  newt = oldt;
  newt.c_lflag &= ~( ICANON | ECHO );
  tcsetattr ( STDIN_FILENO, TCSANOW, &newt );
  ch = getchar();
  tcsetattr ( STDIN_FILENO, TCSANOW, &oldt );
  
  return ch;
} 

/**
 * Press any key method lets any key. 
 */

void press_any_key(void) {
  char ch;
  cout << "Press any key to continue...\n";
  ch = mygetch();
  if (ch == 0 || ch == 224) mygetch();
}
    
/*
Input: (1) Array storing data retrieved from the file (i.e.,
instream)
(2) input file stream object
Output: Size of array. Note: you need to use this parameter to
control the array size.
*/

int readfile(int inputArray[], ifstream& inStream){
    int index = 0;
    int temp = 0;
    //cout<< inputArray[50];
    while (inStream >> temp  && index < MAX_SIZE) {
        inputArray[index] = temp;
        index++; 
    
    }
    //cout<< inputArray[index-1];
    return index;
}

/*
Input: (1) The first sorted array.
(2)The size of the first array.
(3)The second sorted Array.
(4)The size of the second array.
(5)The combined sorted Array.

Output: The the total number of element for the two arrays. 
*/
int sort(int array1[], int array1_size, int array2[], int array2_size, int combined[]) {
    int index, i1 = 0, i2 = 0;
    for (index = 0; index < (array1_size+array2_size); index++){
         
         if((i1 < array1_size) && (i2 < array2_size) && (array1[i1] < array2[i2])) {
            combined[index] = array1[i1];
            i1++;
         }

         else if((i2 < array2_size)) {
            combined[index] = array2[i2];
            i2++;
         }

         else{
            combined[index] = array1[i1];
            i1++;
         }
      
      }
    
    cout<<"The sorted list of "<<index<<" numbers is:";
    for(int i = 0; i < index; i++) {
        cout<<combined[i]<<" ";
    }
    cout<<endl;
    return index;
}    

/**
 *  Writes the new array to the new file.
 * (1) The array to be writen in the new file.
 * (2) The number of filled elements.
 * (3) File Name.
 */
void writeFile(int output[], int size, string outputF){
    ofstream outStream;
    outStream.open((char*)outputF.c_str());
    if (outStream.fail()) {
        cout << "File open failed.\n";
        exit(1);

    }
    for(int i = 0; i < size-1; i++){
        outStream << output[i]<<endl;
    }
    outStream<< output[size-1];
    outStream.close();  
} 

/**
 * Test the read file method.
 */
void test_readfile(){
    int testArray[MAX_SIZE];
    fill(testArray, testArray + MAX_SIZE, 0); 
    ifstream inStreamTest;
    inStreamTest.open("input1.txt");  
    cout<<"*** Unit Test Cases ***\nUnit Test Case 1: readfile(int inputArray[], ifstream& inStream)."<<endl;
    cout<<"\tCase 1.1: Reading in a normal length file."<<endl;
    assert(6 == readfile(testArray,inStreamTest));
    inStreamTest.close();
    fill(testArray, testArray + MAX_SIZE, 0); 
    ifstream inStreamTest2;
    inStreamTest2.open("input0.txt");  
    cout<<"\tCase 1.1 Passed"<<endl;    
    cout<<"\tCase 1.2: Reading in an empty file."<<endl;
    assert(0 == readfile(testArray,inStreamTest));
    cout<<"\tCase 1.2 Passed"<<endl;    
    inStreamTest2.close();
    fill(testArray, testArray + MAX_SIZE, 0); 
    inStreamTest.open("inputMax.txt");  
    cout<<"\tCase 1.3: Reading in a file that is past filled."<<endl;
    assert(MAX_SIZE == readfile(testArray,inStreamTest));
    cout<<"\tCase 1.3 Passed"<<endl;    
    inStreamTest.close();
    press_any_key();    
}



/**
 * Tests the sort method. 
 */
void test_sort(){
    int tArray1[] = {1,3,5,7,9};
    int tArray1_size = 5;
    int tArray2[] = {2,4,6,8,10};
    int tArray2_size = 5;
    int tArray3[10];
    fill(tArray3, tArray3 + 10, 0); 
    
    cout<<"*** Unit Test Cases ***\nUnit Test Case 2:sort(int array1[], int array1_size, int array2[], int array2_size, int combined[])."<<endl;
    cout<<"\tCase 2.1: Sorting a normal and checking its length."<<endl;
    assert(10 == sort(tArray1, tArray1_size, tArray2, tArray2_size, tArray3)); 
    cout<<"\tCase 2.1 Passed"<<endl;    
    cout<<"\tCase 2.2: Checking the first index."<<endl;
    assert(1 == tArray3[0]); 
    cout<<"\tCase 2.2 Passed"<<endl;    
    cout<<"\tCase 2.3: Checking the last index."<<endl;
    assert(10 == tArray3[9]); 
    cout<<"\tCase 2.3 Passed"<<endl;    
    press_any_key();
}


/**
* Tests the writeFile method.   
*/
void test_writeFile(){
    int wArray[] = {1,2};
    int wArray_size = 2;
    ifstream inStreamWrite;
    ofstream outStreamWrite; 
    cout<<"*** Unit Test Cases ***\nUnit Test Case 3: writeFile(int output[], int size, string name)."<<endl;
    cout<<"\tCase 3.1: Making a file"<<endl;
    writeFile(wArray, wArray_size, "output.txt");
    inStreamWrite.open("output.txt");
    assert(false == inStreamWrite.fail());
    cout<<"\tCase 3.1 Passed"<<endl;    
    cout<<"\tCase 3.2: Checking that the file contains the correct contents."<<endl;
    assert(wArray_size == readfile(wArray, inStreamWrite));
    inStreamWrite.close();
    cout<<"\tCase 3.2 Passed"<<endl;    
    press_any_key();
}

int main(){

    ifstream inStreamFirst;
    int iArray1[MAX_SIZE];
    fill(iArray1, iArray1 + MAX_SIZE, 0);
    int iArray1_size;
    int iArray2[MAX_SIZE];
    fill(iArray2, iArray2 + MAX_SIZE, 0);
    int iArray2_size;
    int iArray3[MAX_SIZE*2];
    fill(iArray3, iArray3 + MAX_SIZE*2, 0); 
    int iArray3_size;
    string fileName1;
    string fileName2;
    string outputFile;
    test_readfile();
    test_sort();
    test_writeFile();
    cout << "\n*** Welcome to Kareith’s sorting program ***" <<endl;
    cout << "Enter the first input file name: ";
    cin >> fileName1;
    inStreamFirst.open((char*)fileName1.c_str());
    if (inStreamFirst.fail()) {
        cout << "File open failed.\n";
        exit(1);
    }
    iArray1_size = readfile(iArray1, inStreamFirst);
    inStreamFirst.close();
    cout<< "The list of "<<iArray1_size<<" numbers in file "<<fileName1<<" is:"<<endl;

    for(int i = 0; i < iArray1_size; i++){
        cout<< iArray1[i] <<endl;
    }
    cout << "Enter the second input file name: ";
    cin >> fileName2;
    inStreamFirst.open((char*)fileName2.c_str());
    
    if (inStreamFirst.fail()) {
        cout << "File open failed.\n";
        exit(1);
    }
    iArray2_size = readfile(iArray2, inStreamFirst);
    inStreamFirst.close();
    cout<< "The list of "<<iArray2_size<<" numbers in file "<<fileName2<<" is:"<<endl;
    for(int i = 0; i < iArray2_size; i++){
        cout<< iArray2[i] <<endl;
    }
    
    iArray3_size = sort(iArray1, iArray1_size, iArray2, iArray2_size, iArray3);
    cout << "Enter the output file name: ";
    cin >> outputFile;
    writeFile(iArray3, iArray3_size, outputFile);
    cout <<"*** Please check the new file - "<< outputFile << " ***" <<endl;
    cout <<"*** Goodbye. ***";
    
    return 0;

}