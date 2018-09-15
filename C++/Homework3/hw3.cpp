    /**
     * Name:Kareith Dyce kzd0030
     * Student ID: 903700162
     * File Name : hw3.exe
     * Ran using PuTTY
     * kzdd0030@tux062:~/Desktop/hw3$ vim hw3.cpp
     * wrote code
     * kzdd0030@tux062:~/Desktop/hw3$ g++ -o hw3 hw3.cpp
     * kzdd0030@tux062:~/Desktop/hw3$ ./hw3
    */
    # include <iostream>
    # include <cstdlib>
    # include <termios.h>
    # include <unistd.h>
    # include <cassert>
    # include <ctime>

    using namespace std;

    bool Aaron, Bob, Charlie; //true means alive dead for false.
    bool firstSht;//Maks sure that the first misses for strategy 2
    int Awins, Bwins, Cwins; // Wins for each person
    double AwinsP, BwinsP, CwinsP; //Wins as a percent 
    const double PERCENT = 100; //Makes the totals be represented as a percent.
    const int TENK = 10000; // Number of trials.
    const int A_hit = 33; //Aaron's hit chance
    const int B_hit = 50; //Bob's hit chance


    /* Input: A_alive indicates whether Aaron is alive */
    /* C_alive indicates whether Charlie is alive */
    /* Return: true if at least two are alive */
    /* otherwise return false */
        
    bool at_least_two_alive(bool A_alive, bool B_alive, bool C_alive) {
        return (A_alive && B_alive || A_alive && C_alive || B_alive && C_alive);  
    } 

    /* Strategy 1: Use call by reference
    * Input: B_alive indicates whether Bob alive or dead
    * C_alive indicates whether Charlie is alive or dead
    * Return: Change B_alive into false if Bob is killed.
    * Change C_alive into false if Charlie is killed.
    */

    void Aaron_shoots1(bool& B_alive, bool& C_alive) {
        int shoot_chance = rand() % 100;
        if(shoot_chance < A_hit) {
            if(C_alive) {
                C_alive = false;
                cout<<"\t\tCharlie is dead."<<endl;
            }
            else {
                B_alive = false;
                cout<<"\t\tBob is dead."<<endl;
            }        
        }
        else{ 
            cout<<"\t\tAaron misses."<<endl;
        }
    }
    /* Call by reference
    * Input: A_alive indicates if Aaron is alive or dead
    * C_alive indicates whether Charlie is alive or dead
    * Return: Change A_alive into false if Aaron is killed.
    * Change C_alive into false if Charlie is killed.*/

    void Bob_shoots(bool& A_alive, bool& C_alive) {
        int shoot_chance = rand() % 100;
        if(shoot_chance < B_hit) { 
            if(C_alive){
                C_alive = false;
                cout<<"\t\tCharlie is dead."<<endl;
            }
            else{
                A_alive = false; 
                cout<<"\t\tAaron is dead."<<endl;   
            }        
        }
        else{
            cout<<"\t\tBob missed."<<endl;
        }
    }

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
  cout << "Press any key to continue...";
  ch = mygetch();
  if (ch == 0 || ch == 224) mygetch();
}
    /* Call by reference
    * Input: A_alive indicates if Aaron is alive or dead
    * B_alive indicates whether Bob is alive or dead
    * Return: Change A_alive into false if Aaron is killed.
    * Change B_alive into false if Bob is killed.
    */
    void Charlie_shoots(bool& A_alive, bool& B_alive){
        if(B_alive){
            B_alive = false;
            cout<<"\t\tBob is dead."<<endl;
        }
        else{
            A_alive = false;
            cout<<"\t\tAaron is dead."<<endl;
        }
    }

    /* Strategy 2: Use call by reference
    * Input: B_alive indicates whether Bob is alive or dead
    * C_alive indicates whether Charlie is alive or dead
    * Return: Change B_alive into false if Bob is killed.
    * Change C_alive into false if Charlie is killed.
    */

    void Aaron_shoots2(bool& B_alive, bool& C_alive){
        if(firstSht){
            firstSht = false;
            cout << "\t\tAaron intentionally misses his first shot"<<endl;
            cout << "\t\tBoth Bob and Charlie are alive."<<endl;
            return;
        }
        int shoot_chance = rand() % 100;
        if(shoot_chance < A_hit){
            if(C_alive){
                C_alive = false;
                cout<<"\t\tCharlie is dead."<<endl;
            }
            else{
                B_alive = false;
                cout<<"\t\tBob is dead."<<endl;
            }        
        }
        else{
            cout<<"\t\tAaron misses."<<endl;
        }
    }
    /**
     * Test Drivers
     */

    /**
     * Tests the at_least_two_alive function.
     * Returns nothing.
    */
    void test_at_least_two_alive(void) {
        cout << "Unit Testing 1: Function – at_least_two_alive()\n";
        cout << "\tCase 1: Aaron alive, Bob alive, Charlie alive\n";
        assert(true == at_least_two_alive(true, true, true));
        cout << "\tCase passed ...\n";
        cout << "\tCase 2: Aaron dead, Bob alive, Charlie alive\n";  
        assert(true == at_least_two_alive(false, true, true));
        cout << "\tCase passed ...\n";
        cout << "\tCase 3: Aaron alive, Bob dead, Charlie alive\n";
        assert(true == at_least_two_alive(true, false, true));
        cout << "\tCase passed ...\n";
        cout << "\tCase 4: Aaron alive, Bob alive, Charlie dead\n";
        assert(true == at_least_two_alive(true, true, false));
        cout << "\tCase passed ...\n";
        cout << "\tCase 5: Aaron dead, Bob dead, Charlie alive\n";  
        assert(false == at_least_two_alive(false, false, true));
        cout << "\tCase passed ...\n";
        cout << "\tCase 6: Aaron dead, Bob alive, Charlie dead\n";
        assert(false == at_least_two_alive(false, true, false));
        cout << "\tCase passed ...\n";
        cout << "\tCase 7: Aaron alive, Bob dead, Charlie dead\n";  
        assert(false == at_least_two_alive(true, false, false));
        cout << "\tCase passed ...\n";
        cout << "\tCase 8: Aaron dead, Bob dead, Charlie dead\n";
        assert(false == at_least_two_alive(false, false, false));\
        
        cout << "\tCase passed ...\n";
        press_any_key();
    }

    /**
     * Tests the Aaron_shoots1 function.
     * Returns nothing.
    */
    void test_Aaron_shoots1(void){
        cout << "Unit Testing 2: Function Aaron_shoots1(Bob_alive, Charlie_alive)\n";
        Charlie = true;
        Bob = true;
        cout << "\tCase 1: Bob alive, Charlie alive"<<endl;
        cout << "\t\tAaron is shooting at Charlie"<<endl;
        Aaron_shoots1(Bob, Charlie);
        Bob = false;    
        Charlie = true;
        cout << "\tCase 2: Bob dead, Charlie alive"<<endl;
        cout << "\t\tAaron is shooting at Charlie"<<endl;
        Aaron_shoots1(Bob, Charlie);
        Bob = true;
        Charlie = false;
        cout << "\tCase 3: Bob alive, Charlie dead"<<endl;
        cout << "\t\tAaron is shooting at Bob"<<endl;
        Aaron_shoots1(Bob, Charlie);
        press_any_key();
        
    }


    /**
     * Tests the Bob_shoots function.
     * Returns nothing.
    */
    void test_Bob_shoots(void){
        cout << "Unit Testing 3: Function Bob_shoots(Aaron_alive, Charlie_alive)\n";
        Charlie = true;
        Aaron = true;
        cout << "\tCase 1: Aaron alive, Charlie alive"<<endl;
        cout << "\t\tBob is shooting at Charlie"<<endl;
        Bob_shoots(Aaron, Charlie);
        Aaron = false;
        Charlie = true;
        cout << "\tCase 2: Aaron dead, Charlie alive"<<endl;
        cout << "\t\tBob is shooting at Charlie"<<endl;
        Bob_shoots(Aaron, Charlie);
        Aaron = true;
        Charlie = false;
        cout << "\tCase 3: Aaron alive, Charlie dead"<<endl;
        cout << "\t\tBob is shooting at Aaron"<<endl;
        Bob_shoots(Aaron, Charlie);
        press_any_key();
        
    }
    /**
     * Tests the Charlie_shoots function.
     * Returns nothing.
    */
    void test_Charlie_shoots(void){
        cout << "Unit Testing 4: Function Charlie_shoots(Aaron_alive, Bob_alive)\n";
        Bob = true;
        Aaron = true;
        cout << "\tCase 1: Aaron alive, Bob alive"<<endl;
        cout << "\t\tCharlie is shooting at Bob"<<endl;
        Charlie_shoots(Aaron, Bob);
        Aaron = false;
        Bob = true;
        cout << "\tCase 2: Aaron dead, Bob alive"<<endl;
        cout << "\t\tCharlie is shooting at Bob"<<endl;
        Charlie_shoots(Aaron, Bob);
        Aaron = true;
        Bob = false;
        cout << "\tCase 3: Aaron alive, Bob dead"<<endl;
        cout << "\t\tCharlie is shooting at Aaron"<<endl;
        Charlie_shoots(Aaron, Bob);
        press_any_key();
        
    }
    /**
     * Tests the Aaron_shoots2 function.
     * Returns nothing.
    */
    void test_Aaron_shoots2(void){
        cout << "Unit Testing 5: Function Aaron_shoots2(Bob_alive, Charlie_alive)\n";
        Charlie = true;
        Bob = true;
        cout << "\tCase 1: Bob alive, Charlie alive"<<endl;
        Aaron_shoots2(Bob, Charlie);
        Bob = false;
        Charlie = true;
        cout << "\tCase 2: Bob dead, Charlie alive"<<endl;
        cout << "\t\tAaron is shooting at Charlie"<<endl;
        Aaron_shoots2(Bob, Charlie);
        Bob = true;
        Charlie = false;
        cout << "\tCase 3: Bob alive, Charlie dead"<<endl;
        cout << "\t\tAaron is shooting at Bob"<<endl;
        Aaron_shoots2(Bob, Charlie);
        press_any_key();
        
    }

    /**
     * testType is the strategy being tested.
     * Runs startegy for 1000 times.
     * Prints out the results.
     * Returns the number of wins for Aaron.
     */

    int strategy(int testType){
        Awins = 0;
        Bwins = 0;
        Cwins = 0;
        cout << "Ready to test strategy "<< testType << "(run 10000 times):"<<endl;
        press_any_key();
        int count = 0; //counter for the loop;
        while(count < TENK) {
            Aaron = true;
            firstSht = true;
            Bob = true;
            Charlie = true;
            while(at_least_two_alive(Aaron, Bob, Charlie)) {
                if(Aaron) {
                    if(testType == 1) {
                        Aaron_shoots1(Bob, Charlie);
                    }
                    else {
                        Aaron_shoots2(Bob,Charlie);
                    }
                }
                if(Bob) {
                    Bob_shoots(Aaron, Charlie);
                }
                if(Charlie){
                    Charlie_shoots(Aaron, Bob);
                }
            }
            if(Aaron){
                Awins++;
            }
            if(Bob){
                Bwins++;
            }
            if(Charlie){
                Cwins++;
            }
            count++;
        }
        AwinsP = Awins/PERCENT;
        BwinsP = Bwins/PERCENT;
        CwinsP = Cwins/PERCENT;
            cout << "Aaron won "<< Awins << "/10000 duels or " <<AwinsP<< "%"<<endl;
            cout << "Bob won "<< Bwins << "/10000 duels or " <<BwinsP<< "%"<<endl;
            cout << "Charlie won "<< Cwins << "/10000 duels or " <<CwinsP<< "%"<<endl<<endl;
            return Awins;
    }




    int main(){
        /*Random seed*/
        srand(time(0));
        cout<<"*** Welcome to Kareith’s Duel Simulator ***"<<endl;    
        test_at_least_two_alive();
        test_Aaron_shoots1();
        test_Bob_shoots();
        test_Charlie_shoots();
        test_Aaron_shoots2();
        int first = strategy(1);//Strategy one.
        int second = strategy(2);//Strategy two.
        string output1 = (first < second?"2":"1");
        string output2 = (first < second?"1.":"2.");
        cout<<"Strategy "<<output1<< " is better than strategy "<<output2<<endl;
        
        return 0;
    }