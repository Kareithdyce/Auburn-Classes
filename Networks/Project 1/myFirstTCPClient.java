import java.net.*;  // for Socket
import java.io.*;   // for IOException and Input/OutputStream
import java.util.Scanner;

public class myFirstTCPClient {

   public static void main(String[] args) throws IOException {
   
      if ((args.length < 2) || (args.length > 3))  // Test for correct # of args
         throw new IllegalArgumentException("Parameter(s): <Server> <Word> [<Port>]");
   
      String server = args[0];   // Server name or IP address
      // Convert input String to bytes using the default character encoding
      
      int servPort = (args.length == 2) ? Integer.parseInt(args[1]) : 7;
      
      // Create socket that is connected to server on specified port
      Socket cSocket = new Socket(server, servPort);
      //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      //DataOutputStream out = new DataOutputStream(cSocket.getOutputStream()); 
      InputStream in = cSocket.getInputStream(); 
      OutputStream out = cSocket.getOutputStream();   
   
      System.out.println("Connected to Server");
      
      String S = "";
      Scanner reader = new Scanner(System.in);
      double min = Double.MAX_VALUE;
      double max = Double.MIN_VALUE;
      double avg = 0.0;
      double counter = 0.0; 
      while (!(S.equals("end"))) {
         // get sentence from user
         System.out.print("Enter a sentence(Type \"end\" to end the connection): ");
         S = reader.nextLine();
         byte[] byteBuffer = S.getBytes();
         out.write(byteBuffer);
         int totalBytesRcvd = 0; // Total bytes received so far 
	      int bytesRcvd; // Bytes received in last read 
         double start = System.nanoTime(); // start timer
         while (totalBytesRcvd < byteBuffer.length) { 
            if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd, 
            byteBuffer.length - totalBytesRcvd)) == -1) 
               throw new SocketException("Connection close prematurely"); 
            totalBytesRcvd += bytesRcvd; 
         } 
         System.out.println("Received: " + new String(byteBuffer));
         double end = System.nanoTime();
         double timer = end - start;
         if(timer > max){
            max = timer;
         }
         if(timer < min){
            min = timer;
         }
         avg += timer;
         System.out.println("Time Duration: " + (timer/1000000.0) + " ms");
         counter++;
      }
      System.out.println("Max: " + (max/1000000.0) + " ms");
      System.out.println("Min: " + (min/1000000.0) + " ms");
      System.out.println("Average: " + ((avg/counter)/1000000.0) + " ms");
         
      cSocket.close();
   }
}
