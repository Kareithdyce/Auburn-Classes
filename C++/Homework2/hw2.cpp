/**
 * Name:Kareith Dyce kzd0030
 * Student ID: 903700162
 * File Name : hw2.exe
 * Ran using PuTTY
 * kzdd0030@tux062:~/Desktop/hw2$ vim hw2.cpp
 * wrote code
 * kzdd0030@tux062:~/Desktop/hw2$ g++ -o hw2 hw2.cpp
 * kzdd0030@tux062:~/Desktop/hw2$ ./hw2
 * Outdside Help: None writing the program. Only reffered to project file, FAQ and 
 * the class slides.
 *  
*/
#include <iostream>
using namespace std;
int main(){
    cout.setf(ios::fixed);
    cout.setf(ios::showpoint);
    cout.precision(2);
    double loanA, payM, rateI, balanceR, rateP, payCurr,
    prin, lastBal, monthInt, totalInt = 0;
    string firstRun = "N/A\tN/A\tN/A\tN/A";
    int term = 0;
    cout <<"Loan Amount: ";
    cin>>loanA;
    balanceR = loanA;
    cout<<"Interest Rate (% per year): ";
    cin>>rateI;
    cout<<"Monthly Payements: ";
    cin>>payM;
    cout<<"******************************************************"<<endl;
    cout<<"        Amortization Table"<<endl;
    cout<<"******************************************************"<<endl;
    cout<<"Month\tBalance\tPayment\tRate\tInterest\tPrincipal"<<endl;
    rateI /= 12; //converts the rate from yearly to monthly
    rateP = rateI / 100.0; //converts the rate to a percent
    cout<<term<<"\t$"<< balanceR<<firstRun<<endl;
    while(balanceR > 0){   
        term++; 
        lastBal = balanceR;
        monthInt = (balanceR * rateP);
        totalInt += monthInt;
        balanceR -=(payM - monthInt);
        payCurr = (balanceR < 0)? ((lastBal * (1 + rateP))):payM;
        balanceR = (balanceR < 0)? 0:balanceR;
        prin = payCurr-monthInt;
        if(prin < 0){
            cout<<"******************************************************"<<endl;
            cout<<endl<<"Error!! Monthly payment needs to be higher!!"<<endl;
            return 0;
        }
        cout<<term<<"\t$"<<balanceR<<"\t$"<<payCurr<<"\t"<<rateI;
        cout<<"\t$"<<monthInt<<"\t $"<<prin<<endl;
    }
    cout<<"******************************************************"<<endl;
    cout<<endl<<"It takes "<<term<<" month(s) to pay off the loan."<<endl;
    cout<<"Total interest paid is: $"<<totalInt<<endl;
return 0;
}