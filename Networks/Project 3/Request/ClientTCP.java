import java.net.*;  // for DatagramSocket, DatagramPacket, and InetAddress
import java.io.*;   // for IOException
import java.util.Scanner;

public class ClientTCP {
   public static void main(String args[]) throws Exception {
      final int ZERO = 0;
      final int MAX_BASE16 = 65535;
      final int MAX_CODE = 6;
      Scanner reader = new Scanner(System.in);
      if (args.length != 2 && args.length != 3)  // Test for correct # of args        
         throw new IllegalArgumentException("Parameter(s): <Destination>" +
            	     " <Port> [<encoding]");
      InetAddress destAddr = InetAddress.getByName(args[0]);  // Destination address
      int destPort = Integer.parseInt(args[1]);               // Destination port
      int requestID = 0;
      boolean repeat = true;
      Socket sock = new Socket(destAddr, destPort);
      OutputStream out = sock.getOutputStream();
      InputStream in = sock.getInputStream(); 
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
         Request request = new Request(tml, requestID++ % 255, opCode, numOp, op1, op2);
         //System.out.println();
         //System.out.println(request); 
        
         
      
         out.write(encoderRequest.encode(request));
         double start = System.nanoTime(); // start timer
         
         double end = System.nanoTime();
         double timer = end - start;
         System.out.println("Round Trip Time: " + (timer/1000000.0) + " ms");
         RequestDecoder decoder = (args.length == 3 ?   // Which encoding              
              new RequestDecoderBin(args[2]) :
              new RequestDecoderBin() );
         System.out.println();
         System.out.println(decoder.decode(sock.getInputStream()));
         System.out.print("Continue? Y/N ");
         repeat = "Y".equalsIgnoreCase(reader.nextLine());
      }while(repeat);
      Request empty = new Request(-1, -1, -1, -1, -1, -1);
      byte[] codedEmpty = encoderRequest.encode(empty);
      out.write(encoderRequest.encode(empty));
         
      
      sock.close();
   }
}
