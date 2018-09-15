/**
 * Name:Kareith Dyce kzd0030
 * Student ID: 903700162
 * File Name : hw5.exe
 * Ran using PuTTY
 * kzdd0030@tux062:~/Desktop/hw5$ vim hw5.cpp
 * wrote code
 * kzdd0030@tux062:~/Desktop/hw5$ g++ -o hw5 hw5.cpp
 * kzdd0030@tux062:~/Desktop/hw5$ ./hw5
 * Help with learning how to use pointers with linked list:
 * https://www.codementor.io/codementorteam/a-comprehensive-guide-to-implementation-of-singly-linked-list-using-c_plus_plus-ondlm5azr
*/
#include <limits>
#include <iostream>
#include <string>
#include <cassert>
//#define UNIT_TESTING
using namespace std;

struct TrivaNode { 
    string question;
    string answer;
    int points;
    TrivaNode *next;

};
class trivaList {
    private:
        TrivaNode *head, *tail;

    public:
        int count;
        trivaList() {
            count = 0;
            head = NULL;
            tail = NULL;
            defaultQuestions();
        }
    /**
     * Allows access to the head of the Linked list(the first question)
     * Returns the first node
    */    
    TrivaNode* getHead(){
        return head;
    }
    
    /**
     * Allows access to the tail of the Linked list(the last question)
     * Returns the last node
    */    
    TrivaNode* getTail(){
        return tail;
    }



/**
 * Adds TrivaNodes to the linked list and updates the linked list. 
 * Inputs 1) The question being asked.
 * 2) The answer to the question.
 * 3) The number of points the question is worth.
 */

void addQuestions(string quest, string ans, int point){
    TrivaNode *fact = new TrivaNode;
    string temp;//used to check that points is infact an int.
    count++;
    fact -> question = quest; 
    fact -> answer = ans;
    fact -> points = point; 
    fact -> next = NULL;
    if(head == NULL){
        head = fact;
        tail = fact;
        } 
    else {
        tail -> next = fact;
        tail = fact;
        }
    fact = NULL;
    }

    /**
     * Adds the three default questions linked list
     * Returns the linked list with the questions added.
     */

    void defaultQuestions() {
        addQuestions("How long was the shortest war on record? (Hint: how many minutes)", "38",100);
        addQuestions("What was Bank of America's original name? (Hint: Bank of Italy or Bank of Germany)", "Bank of Italy",50);
        addQuestions("What is the best-selling video game of all time? (Hint: Minecraft or Tetris)", "Tetris",20);
        }    
    

};

    /**
     * Allows the user to create a question to the linked list
     * Returns the linked list with the added question.
     */
    void makeQuestions(trivaList &list){
        string tempQ = "";
        string tempA = "";
        int counter = list.count;
        int tempP = 0;
        cout<<"Enter a question: ";
        getline(cin, tempQ);
        cout<<"Enter an answer: ";
        getline(cin, tempA);
        bool fail = false;
        do {
            cout<<"Enter award points: ";
            cin>> tempP;
            if(cin.fail()) {
                fail = true;
                cin.clear();
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
                cout<<"Error must enter in a valid number"<<endl;
            }
            else{
                fail = false;
            }
        }
        while(fail);
        
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
        list.addQuestions(tempQ, tempA, tempP);
        assert(list.count == ++counter);//Checks that that new node was added.
    }
    /**
     * Asks the user the triva question and then checks to see it the user right
     * Input: The current node in the linked list.
     * Output: Whether the user's answer was correct or not. 
     */
   bool qanda(TrivaNode *q){
       string response = "";
        cout<<"Question: ";
        cout<<q -> question<<endl;
        cout<<"Answer: ";
        getline(cin, response);    
        return response.find(q -> answer) != string::npos;
   } 
        
/**
 * Starts the triva game
 * Input: The list of triva questions in the linked list.
 * Return: 0) if the there were no errors
 * 1) if there were errors,
 * 2) The endscore of the game.
 */
int playGame(trivaList gameList, int trivaAsked, int &total_score){
    int error = 0;
    TrivaNode *game = gameList.getHead();
    total_score = 0;
    
    if(trivaAsked == 0) {
        cout<<"Warning – The number of trivia to be asked must equal to or larger than 1."<<endl;
        return 1;
    }
    if(trivaAsked > gameList.count) {
        cout<<"Warning – There are only three trivia in the list."<<endl;
        error = 1;
        trivaAsked = gameList.count;
    }
    for(int i = 0; i < trivaAsked; i++){
        if(qanda(game)){
            cout<<"Your answer is correct. You recieve "<<game ->points<<" points."<<endl;
            total_score += game->points;
        }
        else{
            cout<<"Your answer is wrong. The correct answer is: "<< game ->answer<<endl;
        }
        cout<<"Your total points: "<< total_score<<endl;
        game = game -> next;
        cout<<endl;
     }
    return error;
}



#ifdef UNIT_TESTING
/**
 * Tests the qanda function.
 */ 
void test_qanda(){
    trivaList test;
    cout<< "Unit Test Case 2.1: Ask 1 question in the linked list. The tester enters an incorrect answer.";
    cout<<endl;
    assert(false == qanda(test.getHead()));
    cout<<"Case 2.1 passed..."<<endl<<endl;
    
    cout<< "Unit Test Case 2.2: Ask 1 question in the linked list. The tester enters a correct answer.";
    cout<<endl;
    assert(true == qanda(test.getTail()));
    cout<<"Case 2.2 passed..."<<endl<<endl;
}
/**
 * Tests the play game function.
 */ 
void test_playGame(){
    int tester;
    trivaList test;
    cout<<"Unit Test Case 3.1: Ask the three questions in the linked list.(All Wrong)"<<endl;
    playGame(test,test.count,tester);
    assert(0 == tester);
    cout<<"Case 3.1 passed..."<<endl<<endl;

    cout<<"Unit Test Case 3.2: Ask the three questions in the linked list.(All Correct)"<<endl;
    playGame(test,test.count,tester);
    assert(170 == tester);
    cout<<"Case 3.2 passed..."<<endl<<endl;

    
    
    cout<<"Unit Test Case 4: Ask five questions in the linked list."<<endl;
    assert(1 == playGame(test, 5, tester));
    cout<<"Case 4 passed..."<<endl<<endl;

}
int main(){
    trivaList list;
    int filler = 0;
    cout<<"*** This is a debug version ***"<<endl;  
    cout<<"Unit Test Case 1: Ask no questions. The program should give a warning message."<<endl;
    assert(1 == playGame(list, 0, filler));
    cout<<endl;
    test_qanda();
    test_playGame();
    cout<<"*** End of the Debug Version ***";
    
    return 0;
    
}
#else
int main(){
    int filler = 0;
    trivaList tL;
    cout <<"*** Welcome to Kareith's trivia quiz game ***"<<endl;
    string moreQ;
    do{
        makeQuestions(tL);
        cout<<"Continue? (Yes/No): ";
        getline(cin, moreQ);
    }
    while (moreQ.find("No") == string::npos);
    playGame(tL, tL.count, filler);
    cout<<"*** Thank you for playing the trivia quiz game. Goodbye! ***";
    cin.ignore().get();
    return 0;
}
#endif
