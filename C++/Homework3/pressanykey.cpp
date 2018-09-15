/*
 * This program shows how to implement the "press_any_key()" function.
 * 
 */ 
#include <stdio.h>
#include <termios.h>
#include <unistd.h>
#include <ctype.h>
#include <iostream>
using namespace std;

int mygetch(void);
void press_any_key(void);

int main() {
  cout << "Test the press_any_key()\n";

  press_any_key();

  cout << "\nTest again ...\n";

  press_any_key();
  
  cout << endl; 
  return 0;
}

void press_any_key(void) {
  char ch;
  cout << "Press any key to continue...";
  ch = mygetch();
  if (ch == 0 || ch == 224) mygetch();
}

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