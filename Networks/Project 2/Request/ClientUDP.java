import java.net.*;  // for DatagramSocket, DatagramPacket, and InetAddress
import java.io.*;   // for IOException
import java.util.Scanner;

public class ClientUDP {
   public static void main(String args[]) throws Exception {
      final int ZERO = 0;
      final int MAX_BASE16 = 255;
      final int MAX_CODE = 6;
      Scanner reader = new Scanner(System.in);
      if (args.length != 2 && args.length != 3)  // Test for correct # of args        
         throw new IllegalArgumentException("Parameter(s): <Destination>" +
            	     " <Port> [<encoding]");
      InetAddress destAddr = InetAddress.getByName(args[0]);  // Destination address
      int destPort = Integer.parseInt(args[1]);               // Destination port
      int requestID = 0;
      boolean repeat = true;
      DatagramSocket sock = new DatagramSocket(); // UDP socket for sending
      RequestEncoder encoderRequest = (args.length == 3 ?
         	  new RequestEncoderBin(args[2]) :
         	  new RequestEncoderBin());
      do{
         int tml = 8;
         int opCode = -1;
         int numOp = 0; 
         while(opCode < ZERO || opCode > MAX_CODE) {
            System.out.print("Enter the Op Code: ");
            opCode = Integer.parseInt(reader.nextLine());
            if(opCode < ZERO || opCode > MAX_CODE){
               System.out.println("Error invalid Op Code");
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
            System.out.print("Enter Operand 1: ");
            op1 = Integer.parseInt(reader.nextLine());
            if(op1 < ZERO || op1 > MAX_BASE16){
               System.out.println("Invalid Number");
            }
         }
         if(numOp == 2){
            while(op2 < ZERO || op2 > MAX_BASE16) {
               System.out.print("Enter Operand 2: ");
               op2 = Integer.parseInt(reader.nextLine());
               if(op2 < ZERO || op2 > MAX_BASE16){
                  System.out.println("Invalid Number");
               }
            }
         }
         Request request = new Request(tml, requestID++, opCode, numOp, op1, op2);
         //System.out.println();
         //System.out.println(request); 
        
         byte[] codedRequest = encoderRequest.encode(request); // Encode request
      
         DatagramPacket message = new DatagramPacket(codedRequest, codedRequest.length, 
                    destAddr, destPort);
         DatagramPacket packet = new DatagramPacket(new byte[1024],1024);           
         double start = System.nanoTime(); // start timer
         sock.send(message);
         sock.receive(packet);
         double end = System.nanoTime();
         double timer = end - start;
         System.out.println("Round Trip Time: " + (timer/1000000.0) + " ms");
         RequestDecoder decoder = (args.length == 3 ?   // Which encoding              
              new RequestDecoderBin(args[2]) :
              new RequestDecoderBin() );
         System.out.println();
         System.out.println(decoder.decode(packet));
         System.out.print("Continue? Y/N ");
         repeat = "Y".equalsIgnoreCase(reader.nextLine());
      }while(repeat);
      Request empty = new Request(-1, -1, -1, -1, -1, -1);
      byte[] codedEmpty = encoderRequest.encode(empty);
      DatagramPacket nullPacket = new DatagramPacket(codedEmpty, codedEmpty.length, 
         			  destAddr, destPort);
      sock.send(nullPacket);
      
         

      sock.close();
   }
}
