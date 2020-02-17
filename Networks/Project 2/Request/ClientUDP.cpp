#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include <iostream> 

using namespace std;
int main(int argc, char* argv []) {
	bool repeat = true;
    const int ZERO = 0;
    const int MAX_BASE16 = 255;
    const int MAX_CODE = 6;
      
    if(argc < 2){   
        cout << "Parameter(s): <Destination> <Port> [<encoding]"<<endl;
        return 0;
    }

    int server_port;
    const char* server_name = argv[1];
    server_port = atoi(argv[2]);

    struct sockaddr_in server_address;
	memset(&server_address, 0, sizeof(server_address));
	server_address.sin_family = AF_INET;

	// creates binary presentation of server name
	// and stores it as sin_addr
	// http://beej.us/guide/bgnet/output/html/multipage/inet_ntopman.html
	inet_pton(AF_INET, server_name, &server_address.sin_addr);

	// htons: port in network order format
	server_address.sin_port = htons(server_port);

	// open socket
	int sock;
	if ((sock = socket(PF_INET, SOCK_DGRAM, 0)) < 0) {
		printf("could not create socket\n");
		return 1;
	}

    do{
         int tml = 8;
         int opCode = -1;
         int numOp = 0; 
         while(opCode < ZERO || opCode > MAX_CODE) {
            cout << "Enter the Op Code: ";
            cin >> opCode;
            if(opCode < ZERO || opCode > MAX_CODE){
               cout << "Error invalid Op Code" << endl;
            }
            else if(opCode == MAX_CODE){
               numOp = 1;
            }else{
               numOp = 2;
            }
         }
         int op1 = -1;
         int op2 = -1;
         while(op1 < ZERO || op1 > MAX_BASE16) {
            cout << "Enter Operand 1: "<<endl;
            cin >> op1;
            if(op1 < ZERO || op1 > MAX_BASE16){
               cout << "Invalid Number" << endl;
            }
         }
         if(numOp == 2){
            while(op2 < ZERO || op2 > MAX_BASE16) {
               cout << "Enter Operand 2: ";
               cin >> op2;
               if(op2 < ZERO || op2 > MAX_BASE16){
                  cout << "Invalid Number";
               }
            }
         }
         Request request = new Request(tml, requestID++, opCode, numOp, op1, op2);
         //System.out.println();
         //System.out.println(request); 
        
         //double start = System.nanoTime(); // start timer
         //double end = System.nanoTime();
         //double timer = end - start;
         //System.out.println("Round Trip Time: " + (timer/1000000.0) + " ms");
         //System.out.print("Continue? Y/N ");
         //repeat = "Y".equalsIgnoreCase(reader.nextLine());
      }while(repeat);


	// data that will be sent to the server
	const char* data_to_send = "Hello";

	// send data
	int len =
	    sendto(sock, data_to_send, strlen(data_to_send), 0,
	           (struct sockaddr*)&server_address, sizeof(server_address));

	// received echoed data back
	char buffer[100];
	recvfrom(sock, buffer, len, 0, NULL, NULL);

	buffer[len] = '\0';
	printf("recieved: '%s'\n", buffer);

	// close the socket
	close(sock);
	return 0;
}