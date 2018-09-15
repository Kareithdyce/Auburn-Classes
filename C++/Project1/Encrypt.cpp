#include <limits>
#include <iostream>
#include <string>
#include <fstream>
#include <ctime>
#include <cstdlib>

using namespace std;

void encrypt(){
    ifstream reader;
    ofstream writer;
    string file;
    char letter;
    int key = 0;
    string line;
    do{
        cout<<"Enter a file name: ";
        cin>>file;
        reader.open(file);
    }while(cin.fail());
    cout<<"\nEnter a key: ";
    cin>>key;
    while(reader.get(letter)){
        letter +=key;
        line += letter;
    }
    reader.close();
    cout<<"\nEnter the new file name: ";
    cin>>file;
    writer.open(file);
    writer<<line;
    writer.close();
}

void decrypt(){
    ifstream reader;
    ofstream writer;
    string file;
    char letter;
    int key = 0;
    string line;
    do{
        cout<<"Enter a file name: ";
        cin>>file;
        reader.open(file);
    }while(cin.fail());
    cout<<"\nEnter a key: ";
    cin>>key;
    while(reader.get(letter)){
        letter -=key;
        line += letter;
    }
    reader.close();
    cout<<"\nEnter the new file name: ";
    cin>>file;
    writer.open(file);
    writer<<line;
    writer.close();
}



int validInt(int &change){
    cout<<"\n\tPlease choose an option: ";
    cin>>change;
    if(cin.fail()) {
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
        change = 0;
    }
    else{
       cin.ignore(numeric_limits<streamsize>::max(), '\n');
    }
    return change;
}


int main(){
    int choice = 0;
    do{
        cout<<"\t1) Encrypt\n\t2) Decrypt";
        validInt(choice);
        switch(choice){
            case 1:
                encrypt();
                break;
            case 2:
                decrypt();
                break;

            default:
            choice = 0;
            break;
        }
    }while (choice == 0);
    return 1;
}

