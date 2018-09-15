/**
 * Name:Kareith Dyce kzd0030
 * Student ID: 903700162
 * File Name : hw1.exe
 * Ran using PuTTY
 * kzdd0030@tux062:~/Desktop$ vim hw1.cpp
 * wrote code
 * kzdd0030@tux062:~/Desktop$ g++ -o hw1 hw1.cpp
 * kzdd0030@tux062:~/Desktop$ ./hw1
 * 
 * Outside help : Help with PuTTY
*/
#include <iostream>
using namespace std;

int main(){
    double mouseKiller,  dietW, mouseW, deadlyR;
    const double SWEETENER = .001;
    cout<<"How much artificial sweetener does it take to kill a mouse: ";  
    cin>>mouseKiller; 
    cout<<endl<<"What is the weight of the mouse: ";
    cin>>mouseW;
    cout<<endl<<"What is the weight of the dieter: ";
    cin>>dietW;
    deadlyR = mouseKiller/mouseW;
    cout<<endl<<"You can drink "<< (deadlyR/SWEETENER) * dietW<<" diet soda(s) without dying."<<endl;

    return 0;
}