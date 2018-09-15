0
#include <limits>
#include <iostream>
#include <string>
#include <fstream>
#include <ctime>
#include <cstdlib>

using namespace std;
const static int NUM_SPACES = 20;

/**
 * Opens the highscores text file.
 * If the file doesnt exist then the file is created.
 * Input the instream object. 
 */ 
void openScores(ifstream &read) {
    ofstream write;
    read.open("highscores.txt");
    if(read.fail()){
        read.close();
        write.open("highscores.txt");
        write.close();
        read.open("highscores.txt");
    }
}


/**
 * Checks the input to make it is an integer.
 * Returns the int if it is an integer
 *  0 otherwise.
 */
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



struct puzzle{
    string riddle;
    string answer1;
    string answer2;
    string answer3;
    string answer4;
    int correct;
    int change;
    char stat;
    puzzle* next;
};

class player{
    private:
    int intell, money, time;
    public:
    string name;
    puzzle* riddle;
    player(){}
    player(string nameIn){
        name = nameIn;
        intell = rand() % 11 + 5;
        money = rand() % 11 + 5;
        time = rand() % 16 + 30;

    }

    /**
     * Return the intelligence stat.
     */
    int getIntelligence(){
        return intell;
    }

    /**
     * Return the money stat.
     */
    int getMoney(){
        return money;
    }

    /**
     * Return the time stat.
     */
    int getTime(){
        return time;
    }	


    /**
     * Changes the intellegence stat.
     * Intput: Desired change for the intellegnce stat.
     */ 
    void changeIntell(int change){
        intell += change;
        if(intell < 0){
            intell = 0;
        }
    }

    /**
     * Changes the time stat.
     * Intput: Desired change for the time stat.
     */ 
    void changeTime(int change){
        time += change;
        if(time < 0){
            time = 0;
        }
    }

    /**
     * Changes the money stat.
     * Intput: Desired change for the money stat.
     */ 
    void changeMoney(int change){
        money += change;
        if(money < 0){
            money = 0;
        }
    }
    /**
     * Displays the current players stats;
     */
    void showStats(){
        cout<<"\nYou have: "<<endl;
        cout<<"Intelligence: "<<intell<<endl; 
        cout<<"Time: "<<time<<endl; 
        cout<<"Money: $"<<money<<".00"<<endl<<endl;
    }
};

struct score {
    string name;
    int point;
};

class scores {
    ifstream reader;
    ofstream writer;
    const static int MAX_SCORES = 11;
    int counter = 0;
    score scoreL[MAX_SCORES];
    public:
    scores(score s){
        scoreL[0] = s;
        counter++;
        readScore();
        sortScores();
        editScores();
    }

    /**
     *  Reads in the highscore textfile;
     */ 
    void readScore(){
        openScores(reader);
        string name;
        while(reader>>name && counter < MAX_SCORES){
            scoreL[counter].name = name;
            reader>>scoreL[counter].point;
            counter++;
        }
    }
    /**
     * Sorts the new scores witht the old onss.
     */ 
    void sortScores(){
        for(int i = 0; i < counter; i++){
            for(int j = 1; j < counter; j++){
                if(scoreL[j-1].point < scoreL[j].point){
                    score temp = scoreL[j];
                    scoreL[j] = scoreL[j-1];
                    scoreL[j-1] = temp;
                }
            }
        }
        if(counter == MAX_SCORES){
            counter--;
        }
        
    }

    /**
     * Modifies the scores text file;
     */ 
    void editScores(){
        writer.open("highscores.txt");
        for(int i = 0; i < counter; i++){
            writer<<scoreL[i].name<<" "<<scoreL[i].point<<endl;
        }
        writer.close();
    }
};
   

class hallway {
    public:
        puzzle *head, *tail;
        int count;
        hallway() {
            count = 1;
            head = new puzzle;
            tail = head;
            setUp();
        }
    
    /**
     * Fills the hallway with number of puzzles.
     */ 
    void setUp(){
        ifstream reader;
        reader.open("puzzles.txt");
        for(int i = 0; i < NUM_SPACES; i++){
            puzzle *fact = new puzzle;
            count++;
            getline(reader, fact -> riddle);
            getline(reader, fact -> answer1);
            getline(reader, fact -> answer2);
            getline(reader, fact -> answer3);
            getline(reader, fact -> answer4);
            reader>>fact -> correct;
            reader>>fact -> stat;
            reader.ignore(numeric_limits<streamsize>::max(), '\n');
            fact -> change = (i%3) + 1;  
            fact -> next = NULL;
            tail -> next = fact;
            tail = fact;
            fact = NULL;     
        }
    puzzle *end = new puzzle;    
    tail -> next = end;
    tail = end;
    count++;
    reader.close();
    }
};

class events {
    int chance;
    player p1;
    puzzle* p;
    public:
    events(){
        roll();
    }
    /**
     * Generates a random number for events.
     */ 
    void roll(){
        chance = (rand() % 100) + 1;
    }
    /**
     * Handles the answering of the riddles
     * Input: the player ansering the riddles
     * Output: the updated player.
     */ 
    void riddle(player &pL){
        int choice = 0;
        int mod = -3;
        int modT = -1;
        string message;
        cout<<"\nYou hear an ominous voice in the wind."    <<endl;
        cout<<"\"Answer my question correctly or face my wrath!\""<<endl;
           
        do{
            cout<<p -> riddle<<endl<<endl;
            cout<<"\t1)"<<p -> answer1<<endl;
            cout<<"\t2)"<<p -> answer2<<endl;
            cout<<"\t3)"<<p -> answer3<<endl;
            cout<<"\t4)"<<p -> answer4<<endl;
            validInt(choice);
            if(choice == p ->correct){
                mod = 1;
                message = "\"Correct...Lucky guess... I guess I should reward you or something...\"\n";
            }
            else{
                message = "\"Good guess... Too bad that's its wrong! Ha ha ha!\n"; 
                message += "Now let's see what you have to offer...\"\n";
            }
            if(choice == 0){
                cout<<"\"Huh, I didn't quiet catch that.\""<<endl;
            }
        }while(choice == 0);
        switch(p->stat){
            case 'I':
                if(mod > 0){
                    message += "You feel smarter! Intellegence has raised by " + to_string(p->change)
                    + " points!";
                }
                else{
                    message += "You feel knowledge slipping away from you! Intellegence has dropped by " + to_string(abs(mod * p->change))
                        + " points!";
                }
                pL.changeIntell(mod*p->change);
            break;
            case 'T': 
                if(mod > 0){
                    modT = 1;
                    message += "You see everything moving strangly as if moving in reverse! You now have" + to_string(p->change)
                    + " more hours!";
                }
                else{
                    message += "You see everything moving much faster around you! You now have" + to_string(p->change)
                    + " less hours!";
                }
                pL.changeTime(modT*p->change);
                modT = -1;
            break;
            case 'M':
                if(mod > 0){
                    message += "You feel your pockets getting heavier! Your money has raised by $" + to_string(p->change)
                    + ".00!";
                }
                else{
                    message += "You feel your pockets getting lighter! Your money has dropped by $" + to_string(abs(mod * p->change))
                    + ".00!";
                }
                pL.changeMoney(mod*p->change);
            break;
        }
        cout<<endl<<message<<endl;
    }


    /**.
     * Handle turn based events
     * Input: the player ansering the riddles
     * Output: the updated player.
     */  
    void event(player &pL){
        int deltaT = (rand() % 3) + 1;
        int deltaS = (rand() % 5) + 1;
        int joke = (rand() % 30) + 25;
        const static int NEG = -1;
        p = pL.riddle;
        roll();
        if(chance >= 75){
            riddle(pL);
            pL.changeTime(NEG);
        }
        else if(chance >= 60){
            cout<<"You get a message saying that your dissertation is now due in "<<deltaT<< " hours."<<endl;;
            cout<<"You immediately get to work as time flies by and you finish it in just the nick of time"<<endl;
            cout<<"However the strain of rushing has left you burnt out which causes your intellegence to drop "<<deltaS<<" points!"<<endl;
            pL.changeIntell(NEG*deltaS);
            pL.changeTime(NEG*deltaT);  
        }
        else if(chance >= 50){
            cout<<"You run into another graduate student  also braving the hallway and talk for"<<deltaT<< " hours."<<endl;;
            cout<<"After the talk you feel much refreshed and confident that you can beat the hallway."<<endl;
            cout<<"Your confidence raises "<<joke<<" points! However as you are about charge ahead to beat the hallway you hear a scream."<<endl;
            cout<<"You turn around and your newly found friend is gone without a trace... Your confidence falls "<<joke *3<<" points!"<<endl;
            cout<<"Good thing you aren't scored on that."<<endl<<endl;
            pL.changeTime(NEG*deltaT);  
        }
        else if(chance >= 35){
            cout<<"Suddenly a professor holding important looking documents runs right into you!"<<endl;
            cout<<"As the papers fly into the air you catch a glimse of same topic that you are currently working with."<<endl;
            cout<<"After you help the professor pick the papers you strike up a convseration for "<<deltaT<<" hours."<<endl;
            cout<<"You learn quite a bit from him and increase you intellegence by "<<deltaS<<" points!"<<endl;
            pL.changeTime(NEG*deltaT);  
            pL.changeIntell(deltaS);
        }
        else if(chance >= 25){
            cout<<"Suddenly a stack of over 300 tests appears in front of you almost making you trip."<<endl;
            cout<<"Suprised you see your mentor looking upset telling you to have it done in "<<deltaT<<" hours"<<endl;
            cout<<"It takes a trendous effort but you manage to finish just in the nick of time."<<endl;
            cout<<"You turn in the stack and recieve $"<<deltaS<<".00 and then prepare yourself to continue the hallway."<<endl;
            pL.changeTime(NEG*deltaT);  
            pL.changeMoney(deltaS);
        }
        else{
            cout<<"Nothing happened. Maybe things are looking up."<<endl;
            pL.changeTime(NEG);
        }
    }
};

class game{
    public:
    int dist = 0;
    hallway h;
    player p1;
    game(){}
    game(player pIn){
        p1 = pIn;
        p1.riddle = h.head;
    }

    /**
     * Handles the game over messages.(Not Quiting)
     */ 
    void gameOver(){
        if(p1.getIntelligence() == 0){
            cout<<"\nYour intellegence has dropped to zero! Sadly you also forgot how to breathe!"<<endl;
        }
        else if(p1.getMoney() == 0){
            cout<<"\nYour money is all gone! You manage to slowly escape the hallway but injured, weak, and hungry."<<endl;
            cout<<"Since you have no money you cannot get help and die."<<endl;
        }
        else if(p1.getTime() == 0){
            cout<<"\n\"Ding Dong Times Up! Your never leaving this hallway! Rot for the rest of etnerity!\""<<endl;
        }
            cout<<"Game Over"<<endl;           
    }   
    
    
    /**
     * Plays the game. Returns the score of the game.
     */ 
    int play(){
        cout<<"\n\nEntering the Game..."<<endl;
        int score = 1;
        p1.showStats();
        while(score > 0 && dist != NUM_SPACES){
            int left = NUM_SPACES - dist;
            cout<<"\nYou are "<<left<<" steps from the goal.";
            cout<<" Time left "<<p1.getTime()<<"."<<endl<<endl;
            turn();
            score = p1.getIntelligence() * p1.getMoney() * p1.getTime();
        }
        if(score < 0){
            cout<<"\nSo "<<p1.name<<", you had to quit tragic..."<<endl;
            cout<<"Better luck next time... \nIf you're brave enough to come back thats is!"<<endl;
        }
        
        if(score == 0){
            gameOver();
        }
        else if(dist == NUM_SPACES){
            cout<<"\nCongrats you have reached the end of the hallway."<<endl;
        }
        return score;
    }
    /**
     * Plays a turn of the game.
     */ 
    void turn(){
        int choice;
        cout<<endl;
        do{
            int deltaT = (rand() % 2) + 1;
            int deltaS = (rand() % 5) + 1;
            events e;
            cout<<"\t1) Move forward (takes time)"<<endl;
            cout<<"\t2) Read technical papers (boost intelligence, takes time)"<<endl;
            cout<<"\t3) Search for loose change (boost money, takes time)"<<endl;
            cout<<"\t4) View character"<<endl;
            cout<<"\t5) Quit the game"<<endl;
            validInt(choice);
            switch(choice){
                case 1:
                    cout<<"\nYou take a deep breath and step into the dark unkown..."<<endl;
                    p1.riddle = p1.riddle -> next;
                    dist++;
                    e = events();
                    e.event(p1);
                    return;
                break;
                case 2:
                    cout<<"You read through some technical papers."<<endl;
                    cout<<"Your intellect has increased by "<<deltaS<<", but you lost "<<deltaT;
                    cout<<" hours.\n"<<endl;
                    deltaT *= -1;
                    p1.changeMoney(deltaS);
                    p1.changeTime(deltaT);
                    break;
                case 3:
                    cout<<"You looked on the ground for some loose change."<<endl;
                    cout<<"You somehow found $"<<deltaS<<".00 in "<<deltaT;
                    cout<<" hours. What good luck!\n"<<endl;
                    deltaT *= -1;
                    p1.changeMoney(deltaS);
                    p1.changeTime(deltaT);
                    break;
                case 4:
                    p1.showStats();
                    break;
                case 5:
                    p1.changeTime(-100);
                    break;
                default:
                    cout<<"\tError invalid input. Try again."<<endl;
            }
        }while(choice == 0);
    }
};


class menu{
    public:
    ifstream reader;
    player p1;;
    int choice;
    game g;
    menu(player p1In){
        p1 = p1In;
    }


    int mainMenu(){
        int score = 0;
       do{
           
            cout<<"\n1) Start a New Game"<<endl;
            cout<<"2) View top 10 High Scores"<<endl;
            cout<<"3) Quit"<<endl;
            choice = validInt(choice);
            switch(choice){
                case 1:
                g = game(p1);
                return score = g.play();
                break;
                
                case 2:
                showScores();
                break;
                
                case 3:
                score = 0;
                break;

                default:
                cout<<"\nError invalid input. Try again."<<endl;
            }
        }while(choice != 3);
    return score;
    }

    /**
     * Prints out the current top 10 scores.
     */ 
    void showScores(){
        int counter = 0;
        cout<<"\nThe top high scores are:\n"<<endl;
        ifstream reader;
        string line;
        openScores(reader);
        while(getline(reader,line)){
            cout<<line<<endl;
            counter++;
        }
        if(counter < 10){
            cout<<"-no more scores to show-"<<endl;
        }
    }
};



int main(){
    srand(time(0));
    string temp;
    cout<<"What is your name? ";
    cin>>temp;
    player p = player(temp);
    cout<<"==========================================================="<<endl;
    cout<<"|\t\tWelcome, "<<p.name<<"!\t\t\t  |"<<endl;
    cout<<"==========================================================="<<endl;
    menu m = menu(p);
    int scoreT = m.mainMenu();
    if(scoreT > 0){
        cout<<"\nYour final score was: "<<scoreT;
        score s;
        s.name = p.name;
        s.point = scoreT;
        scores total = scores(s);
    }
    cin>>temp;
    return 0;
}

