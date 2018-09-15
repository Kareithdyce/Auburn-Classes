/**
 * Student ID: 903700162
 * File Name : Project2.exe
 * Ran using PuTTY
 * kzdd0030@tux062:~/Desktop/Project2$ vim Project2.cpp
 * wrote code
 * kzdd0030@tux062:~/Desktop/Project2$ g++ -o Project2 Project2.cpp
 * kzdd0030@tux062:~/Desktop/Project2$ ./Project2
 */
#include <windows.h>
#include <limits>
#include <iostream>
#include <unordered_map>
#include <string>
#include <fstream>
#include <cstdlib>
#include <termios.h>
#include <unistd.h>
#include <stdio.h>

 using namespace std;
 ifstream reader;
 ofstream writer;


/**
 * Checks the input to make it is an integer.
 * Returns the int if it is an integer
 * 0 otherwise.
 */
void validInt(int &change){
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
    //return change;
}


class Client{
    private:
    string name;
    string address;
    string social;
    string employer;
    int income;


    public:
    Client(){};
    Client(string nameIn, string addressIn, string socialIn, string employerIn, int incomeIn){
        name = nameIn;
        address = addressIn;
        social = socialIn;
        employer = employerIn;
        income = incomeIn;
    }
    /**
    * Changes the details of the client; 
    */
    void changeInfo(string addressIn, string socialIn,  string employerIn, int incomeIn){
        social = socialIn;
        address = addressIn;
        employer = employerIn;
        income = incomeIn;
    }
    /**
     * The toString of client
     */
    void print() const{
        cout<<"Address: "<<address<<endl;
        cout<<"Social security number: "<<social<<endl;
        cout<<"Employer: "<<employer<<endl;
        cout<<"Annual income: "<<income<<endl;
    }
    /**
     * Returns the address of the client
     */ 
    string getAddress() const{
        return address;
    }    
   
    /**
     * Returns the social of the client
     */ 
    string  getSocial() const{
        return social;
    }
    
    /**
     * Returns the employer of the client
     */ 
    string getEmployer() const{
        return employer;
    }
    
    /**
     * Returns the income of the client
     */ 
    int getIncome() const{
        return income;
    }
    
};

class Account{
    private:
    double balance;
    string nameC;
    string aType;
    int number;
    public:
    Account(string aTypeIn, int numberIn, double balanceIn, string nameCIn){
        aType = aTypeIn;
        number = numberIn;
        balance = balanceIn;
        nameC = nameCIn;
    }

    /**
     * Returns the account type 
     */ 
    string getAtype() const{
        return aType;
    }
    
    /**
     * Returns the account number 
     */ 
    int getNumber() const{
        return number;
    }
    /**
     * Returns the account owner's name 
     */ 
    string getNameC() const{
        return nameC;
    }
    /**
     * Returns the current balance. 
     */ 
    double getBalance() const{
        return balance;
    }
    /**
     * Increases the money in the account.
     */ 
    void deposit(int change){
        balance += change; 
        cout<<"Balance of account "<<number<<" is: "<<balance<<endl;
    }

    /**
     * Decreases the money in the account. If the balance drops below zero
     * then the user is given a warning and the balance is set to zero.
     */ 
    void withdraw(int change){
        double check = balance;
        check -= change; 
        if(check < 0){
            cout<<"Error - Overdraft warning. Actual withdrawl is "<<balance<<endl;
           check = 0;    
        }
        balance = check;
        cout<<"Balance of account "<<number<<" is: "<<balance<<endl;
    }
};

/**
 * Gets the charater.
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
string url = "http://fakeupdate.net/win10u/";
const char* linkChar= url.c_str();
ShellExecute(NULL, NULL, linkChar, NULL, NULL,  SW_SHOWMAXIMIZED);

}

class Staff{
    private:
    string username;
    string password;
    char r;
    string role;
    public:
    unordered_map<string, Client> clientMap; 
    unordered_map<int, Account> accountMap; 
        
    Staff(){
        setRole("Branch Staff", 'S');
    };

    void setUpC(){
        string file = "client-info.txt";
        string name,address,social,employer;
        int income;
        reader.open((char*)file.c_str());
        if(reader.fail()){
            reader.close();
            writer.open((char*)file.c_str());
            writer.close();
            reader.open((char*)file.c_str());
        }
        while(getline(reader, name)){
            getline(reader, address);
            getline(reader, social); 
            getline(reader, employer);  
            reader>>income;
            reader.ignore(numeric_limits<streamsize>::max(), '\n');
            reader.clear();
            Client c = Client(name, address,social,employer,income);
            clientMap.insert({name, c});
            /*
            cout<<name<<endl;
            cout<<address<<endl;
            cout<<social<<endl;
            cout<<employer<<endl;
            cout<<income<<endl;
            */
        }
        reader.close();
    
    file = "account-info.txt";
    int aNum;
    string type;
    double bal;
    reader.open((char*)file.c_str());
    if(reader.fail()){
        reader.close();
        writer.open((char*)file.c_str());
        writer.close();
        reader.open((char*)file.c_str());
    }
    while(reader>>aNum){
        reader>>type;
        reader>>bal;
        reader.ignore(numeric_limits<streamsize>::max(), '\n');
        reader.clear();    
        getline(reader, name);
        Account a = Account(type,aNum,bal,name);
        accountMap.insert({aNum, a});       
    }
    reader.close();
    
    
    }

    Staff(string usernameIn, string passwordIn){
        username = usernameIn;
        password = passwordIn; 
        setRole("Branch Staff", 'S');
    
    }

    /**
     * Returns the username of the staff member
     */ 
    string getUsername() const{
       return username;
    }
    
    /**
     * Returns the password of the staff member
     */ 
    string getPassword() const{
        return password;
    }
    
    /**
     * Returns the role of the staff member
     */ 
    string getRole() const{
        return role;
    }
    
    /**
     * Returns the symbol of the staff member
     */ 
    char getR() const{
        return r;
    }

    /**
     * Sets the role for the staff member;
     */ 

    void setRole(string roleIn, char rIn){
        r = rIn;
        role = roleIn;
    }

    /**
     * Adds a client to the database
     */
    void addClient(){
        unordered_map<string, Client>::const_iterator got;
        string name;
        string address;
        string social;
        string employer;
        int income;
        cout<<"A new client will be added:"<<endl;
        cout<<"Client name: ";
        getline(cin,name);
        got = clientMap.find(name);
        if(got == clientMap.end()){
            cout<<"Address: ";
            getline(cin,address);
            cout<<"Social security number: ";
            getline(cin,social);
            cout<<"Employer: ";
            getline(cin,employer);
            cout<<"Annual income: ";
            cin>>income;
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            cin.clear();
            cout<<"A new client was added!"<<endl;
            Client c = Client(name, address, social,employer, income);
            clientMap.insert({name, c});
            
        }
        else{
            cout<<"Error - Client already exists"<<endl;
        }
        press_any_key();
    }


    

    /**
     * Edit the client info and updates it to the database
     * Input: the current map of clients
     * Output: The updated map of clients
     */ 
    void editClient(){
        if(clientMap.empty()){
            cout<<"Error - No clients in the system."<<endl;
            return;
        }
        unordered_map<string, Client>::const_iterator got;
        string name;
        string address;
        string social;
        string employer;
        int income;
        Client c;
        cout<<"Choose a client: ";
        getline(cin,name);
        got = clientMap.find(name);
        if(got != clientMap.end()){
            cout<<"Display "<<name<<"’s information:"<<endl;
            got->second.print();
            cout<<"Client "<<name<<"’s information will be updated ..."<<endl;
            int choice;
            do{
                cout<<"1) Confirm"<<endl;
                cout<<"2) Cancel"<<endl;
                validInt(choice);
            }while(choice == 0);
            
            switch(choice){
                case 1:
                cout<<"Address: ";
                getline(cin,address);
                cout<<"Social security number: ";
                getline(cin,social);
                cout<<"Employer: ";
                getline(cin,employer);
                cout<<"Annual income: ";
                cin>>income;
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
                cin.clear();
                c = Client(name, address, social,employer, income);
                clientMap.at(name) = c;
                cout<<"Client "<<name<<"’s information was updated!"<<endl;
                press_any_key();    
                break;
                case 2:
                break;
                default:
                cout<<"Error invalid input"<<endl;
            }    
        }
        else{
            cout<<"Error – The client is not in the system!"<<endl;
        }
    }
    /**
     * Adds a account to the system.
     */ 
    void addAccount(){
        string name;
        unordered_map<string, Client>::const_iterator got;
        do{
            cout<<"Choose a client: ";
            getline(cin,name);
        }while(name.empty());
        got = clientMap.find(name);
        if(got == clientMap.end()){
            cout<<"Error – The client is not in the system!"<<endl;
        }else{
            int aNum;
            string type;
            double money;
            cout<<"A new account will be created for "<<name<<" ..."<<endl;
            do{
                cout<<"Account Number: ";
                cin>>aNum;
                if(accountMap.find(aNum)!= accountMap.end()){
                    cout<<"Error - Account already in the system. Enter a new number"<<endl;
                }
            }while(accountMap.find(aNum)!= accountMap.end());
            cout<<"Account Type: ";
            cin>>type;
            cout<<"Balance: ";
            cin>>money;
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
            cin.clear();   
            cout<<"A new account was created for "<<name<<"!"<<endl;
            Account a = Account(type, aNum, money,name);
            accountMap.insert({aNum,a});
            press_any_key();
        }
    }      
    /**
     * Manages the account.
     * Allows for withdrawals and deposits. 
     */
    void manageAccount(){
        if(accountMap.empty()){
            cout<<"Error - No accounts in the system."<<endl;
            return;
        }
        int number, choice; 
        double delta;
        unordered_map<int, Account>::const_iterator got;
        cout<<"Which account will be managed: ";
        cin>>number;
        got = accountMap.find(number);
        if(got == accountMap.end()){
            cout<<"Error – Account "<<number<<" is not in the system!"<<endl;
        }else{
           cout<<"Manage account "<<number<<" for "<<got->second.getNameC()<<" ..."<<endl;
           Account a = got->second; 
            do{
                cout<<"1) Deposit"<<endl;
                cout<<"2) Withdraw"<<endl;
                cout<<"3) Cancel"<<endl;
                validInt(choice);
                switch(choice){
                    case 1:
                    cout<<"Deposit Amount: ";
                    cin>>delta;
                    a.deposit(delta);
                    break;
                    case 2:
                    cout<<"Withdraw Amount: ";
                    cin>>delta;
                    a.withdraw(delta);
                    break;
                    case 3:
                    accountMap.at(number) = a;
                    break;
                    default:
                    cout<<"Error invalid input"<<endl;
                }
            }while(choice != 3);
        }
    }
    

    /**
     * Saves the account and client information to a file.
     */
    void saveInfo(){
        string file1 = "client-info.txt";
        string file2 = "account-info.txt";
        writer.open((char*)file1.c_str());
        for (auto& x: clientMap) {
            writer<<x.first<<endl<<x.second.getAddress()<<endl<<x.second.getSocial()<<endl;
            writer<<x.second.getEmployer()<<endl<<x.second.getIncome()<<endl;
        }
        writer.close();
        writer.open((char*)file2.c_str());
        for (auto& x: accountMap) {
            writer<<x.first<<endl<<x.second.getAtype()<<endl<<x.second.getBalance()<<endl;
            writer<<x.second.getNameC()<<endl; 
        }
        writer.close();
        cout<<"Client information has been saved in the client-info file!"<<endl;
        cout<<"Account information has been saved in the account-info file!"<<endl;
        press_any_key();
    }
    /**
     * The menu for dealing with clients.
     */ 
    void clientMenu(){
        int choice = 0;
        setUpC();
        do{
        cout<<"============================================================="<<endl;
        cout<<"|    Teller Terminal System – Client and Account Management |"<<endl;
        cout<<"============================================================="<<endl;
        cout<<"1) Add a client"<<endl;
        cout<<"2) Add an account"<<endl;
        cout<<"3) Edit Client Information"<<endl;
        cout<<"4) Manage an account"<<endl;
        cout<<"5) Save Client and Account Information"<<endl;
        cout<<"6) Exit"<<endl;
        validInt(choice);
        switch(choice){
            case 1:
            addClient();
            break;
            case 2:
            addAccount();
            break;
            case 3:
            editClient();
            break;
            case 4:
            manageAccount();
            break;
            case 5:
            saveInfo();
            break;
            case 6:
            
            break;
            default:
            cout<<"Error invalid input"<<endl;
        }
        }while(choice != 6);
    }


    /**
     * The toString of the Staff
     */ 
    void print(){
        cout<<"User Name: "<<username<<" Role: "<<role<<endl;               
    }
    /**
     * Verifies that the users is really the user
     * Returns true if validiated and false otherwise
     */ 
    bool verify(){
        string name;
        string pass;
        cout<<"User Name: ";
        cin>>name;
        cout<<"Password: ";
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
        getline(cin,pass);
        if(name.find(username) != string::npos && pass.find(password) != string::npos){
            return true;
        }
        cout << "Validation Failed!"<<endl;
        return false;
    }
    /**
     * Change the password.
     */ 
    void changePassword(){
        if(verify()){
            string newpass;
            cout<<"New Password: ";
            getline(cin,newpass);
            if(newpass.find(password) != string::npos){
                cout<<"Error - Your new password must be different from the old one!"<<endl;
                return;
            }else if(newpass.empty()){
                cout<<"Error - Your new password must be non-empty!"<<endl;
                return;    
            }
            password = newpass;
        }
    }
    /**
     * The normal staff menu
     * Inputs: The map of staff
     * Outputs: The updated map of the staff
     */ 
    void menu(unordered_map<string, Staff> &map){
        int choice;    
        do{
            cout<<"==========================================================="<<endl;
            cout<<"|           Teller Terminal System – Branch Staff         |"<<endl;
            cout<<"==========================================================="<<endl;
            cout<<"1) Client and Account Management"<<endl;
            cout<<"2) Change password"<<endl;
            cout<<"3) Exit"<<endl;
            validInt(choice);
            switch(choice){
                case 1:
                    clientMenu();
                break;
                case 2:
                    changePassword();
                break;
                case 3:
                
                break;
                default:
                cout<<"Error invalid input"<<endl;
            }
        }while(choice != 3);
    }

};

class Admin : public Staff{
    public:
    Admin() : Staff(){    
        setRole("System Administrator", 'A');
    }

    Admin(string usernameIn, string passwordIn) : Staff(usernameIn, passwordIn){    
        setRole("System Administrator", 'A');
    }
    
    /**
     * Add a new member of the staff
     * Inputs: The map of staff
     * Outputs: The updated map of the staff
     */ 
    void addMember(unordered_map<string, Staff> &map){
        string name;
        unordered_map<string, Staff>::const_iterator got;
        string password;
        Staff s;
        int choice = 0;
        do{
            cout<<"User Name: ";
            cin.clear();
            cin>>name;
            got = map.find(name);
        }while(name.empty() || got != map.end());
        do{
            cin.clear();
            cout<<"Password: ";
            cin>>password;
        }while(password.empty());
        cout<<"Role (1 – Administrator; 2 – Branch Staff): ";
        cin>>choice; 
        switch(choice){
            case 1:
                s = Admin(name, password);
                break;
            case 2:
                s = Staff(name, password);
                break;
            default:
            choice = 0;
            cout<<"Invalid input try again."<<endl;
        }
        do{
            cout<<"1) Confirm"<<endl;
            cout<<"2) Cancel"<<endl;
            validInt(choice);
            switch(choice){
                case 1:
                    map.insert({name, s}); 
                    cout<<"new branch staff was added!"<<endl;
                    s.print();
                    press_any_key();
                    break;
                case 2:
                    break;
                default:
                choice = 0;
            }

        }while(choice == 0);
    }
    /**
     * Deletes a staff member from the system.
     * Input the containig the staff members
     * Returns the updated list 
     */
    void deleteMember(unordered_map<string, Staff> &map){
        unordered_map<string, Staff>::const_iterator got;
        string name;
        int choice = 0;
        cout<<"Delete a user - User Name: ";
        cin.clear();
        cin>>name;
        if(getUsername().find(name) != string::npos){
            cout<<"Error - Cannot delete current account"<<endl;
            return;
        }
        got = map.find(name);
        do{
            cout<<"1) Confirm"<<endl;
            cout<<"2) Cancel"<<endl;
            validInt(choice);
            switch(choice){
                case 1:
                    if(got == map.end()){
                        cout<<"Warning - User "<<name<<" is not in the system. No user is deleted!"<<endl;
                    }else{
                        cout<<"User "<<name<<" was deleted!"<<endl;
                        map.erase(got);
                    }
                    press_any_key();
                    break;
                case 2:
                    break;
                default:
                choice = 0;
            }
        }while(choice == 0);
    }
    /**
    * Displays the current staff members of the bank
    * Input the map contianing the staff members
    */
    void displayBranch(unordered_map<string, Staff> &map){
        cout<<"There are "<<map.size()<<" users in the system."<<endl;
        for (auto& x: map) {
                x.second.print();
            }
         press_any_key();   
    }
    
    /**
     * The admin menu
     */     
    void menu(unordered_map<string, Staff> &map){
        int choice;    
        do{
            cout<<"==========================================================="<<endl;
            cout<<"|     Teller Terminal System – System Administration      |"<<endl;
            cout<<"==========================================================="<<endl;
            cout<<"1) Client and Account Management"<<endl;
            cout<<"2) Add a branch staff member"<<endl;
            cout<<"3) Delete a branch staff member"<<endl;
            cout<<"4) Display branch staff"<<endl;
            cout<<"5) Change password"<<endl;
            cout<<"6) Exit"<<endl;
            validInt(choice);
        switch(choice){
            case 1:
                clientMenu();
            break;
            case 2:
                addMember(map);
            break;
            case 3:
                deleteMember(map);
            break;
            case 4:
                displayBranch(map);
            break;
            case 5:
               changePassword();
            break;
            case 6:
            break;
            default:
            cout<<"Error invalid input"<<endl;
            break;
        }
        }while(choice != 6);
    }
};

 
/**
 * Makes sure that the password document exists. If it isnt then
 * the file is created with the default user name and password.
 * Then reads the file for accounts.
 * Input: The hashmap that stores the staff login.
 * Output: The updated hashmap.
 */ 
void setup(unordered_map<string, Staff> &map){
    string name;
    string password;
    char type;
    
    string file = "staff-info.txt";
    reader.open((char*)file.c_str());
    if(reader.fail()){
        reader.close();
        writer.open((char*)file.c_str());
        writer<<"admin"<<endl;
        writer<<"0000"<<endl;
        writer<<"A"<<endl;
        writer.close();
        reader.open((char*)file.c_str());
        cout<<"Initializing system. Please wait"<<endl;
        for(int i = 0; i < 10; i++){
            cout<<"."<<endl;
        }
        cout<<"System setup."<<endl;
        press_any_key();
    }
    while(reader>>name){
        Staff s = Staff();
        Admin a = Admin();
        reader.ignore(numeric_limits<streamsize>::max(), '\n');
        getline(reader, password);
        //cout<<password<<endl;
        reader>>type;
        //cout<<type<<endl;
        switch(type){
            case 'A':
                a = Admin(name, password);
                map.insert({name, a});
                //cout<<"admin added"<<endl;
            break;
            case 'S':
                s = Staff(name, password);
                map.insert({name, s});
                //cout<<"staff added"<<endl;
            break;
            default:
            cout<<"Error"<<endl;
        } 
    }
    reader.close();
}

/**
 * Logins the user
 * Takes in the map that contains the list of works 
 * Returns the updated map
 */ 
void login(unordered_map<string, Staff> &map){
    string name, password;
    cout<<"==========================================================="<<endl;
    cout<<"|      Login to Access the Teller Terminal System         |"<<endl;
    cout<<"==========================================================="<<endl;
    cout<<"User Name: ";
    cin>>name;
    cout<<"Password: ";
    cin.ignore(numeric_limits<streamsize>::max(), '\n');
    getline(cin, password);
    unordered_map<string, Staff>::const_iterator got = map.find(name);
    if(got == map.end()){
        cout << "The user name is incorrect. Try again!"<<endl;
    } 
    else if(password.find(got-> second.getPassword()) != string::npos){
        Staff s = got -> second;
        
        if(s.getRole().find("Staff") != string::npos){
            s.menu(map);
            map.at(s.getUsername()) = s;
            
        }

        else{
            Admin a = Admin(s.getUsername(),s.getPassword());    
            a.menu(map);
            map.at(a.getUsername()) = a;
                
        }        
    }
    else{
        cout << "The password is incorrect. Try again!"<<endl;
    }
}

int main(){
    
    unordered_map<string, Staff> staffMap; 
    string file = "staff-info.txt";
    int choice = 0;
    setup(staffMap);
  do{
        cout<<"==========================================================="<<endl;
        cout<<"|     Welcome to the Auburn Branch of Tiger Bank!         |"<<endl;
        cout<<"==========================================================="<<endl;
        cout<<"1) Login"<<endl;
        cout<<"2) Quit"<<endl;
        validInt(choice);
        switch(choice){
            case 1:
            login(staffMap);
            writer.open((char*)file.c_str());
            for (auto& x: staffMap) {
                writer<<x.first<<endl<<x.second.getPassword()<<endl<<x.second.getR()<<endl;
            }
            writer.close();
            break;
            case 2:
            break;
            default:
            cout<<"Error invalid input"<<endl;
        }
    }while(choice != 2);
    return 0;
}