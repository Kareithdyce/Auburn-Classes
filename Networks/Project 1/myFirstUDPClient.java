import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Scanner;

public class myFirstUDPClient {
   
   private static final int BUFSIZE = 128;   // Size of receive buffer

   public static void main(String[] args) throws IOException {
   
      if ((args.length < 2) || (args.length > 3))  // Test for correct # of args
         throw new IllegalArgumentException("Parameter(s): <Server> <Word> [<Port>]");
      
      int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;
      InetAddress serverAddress = InetAddress.getByName(args[0]);  // Server address
      
      try {
         //Maximum size of the message can be 10000 bytes
         byte[] byteBuffer = new byte[BUFSIZE];
          
         //Start the datagram socket to send the packet 
         DatagramSocket sock = new DatagramSocket();
         Scanner reader = new Scanner(System.in);
            System.out.print("Connected To The Server(Press Enter) ");
            
            
            
         String S = new BufferedReader(new InputStreamReader(System.in)).readLine();
         double min = Double.MAX_VALUE;
         double max = Double.MIN_VALUE;
         double avg = 0.0;
         double counter = 0.0; 
         while(!(S.equals("end"))){
            System.out.print("Enter a sentence(Type \"end\" to end the connection): ");
            S = reader.nextLine();

            byteBuffer = S.getBytes();
            InetAddress hostAddress = InetAddress.getByName(args[0]);
            
             
             //Add the converted message in bytes to the packet
            DatagramPacket packet = 
                  new DatagramPacket(byteBuffer,0,byteBuffer.length,hostAddress,Integer.parseInt(args[1]));
             
             //Save the current time to calculate round trip time
            double messSend = System.currentTimeMillis();
            sock.send(packet);
            
             //New packet which will recieve the response
            packet = new DatagramPacket(byteBuffer,byteBuffer.length);
            sock.receive(packet);
             
             //Get the time again so the round trip time can be calculated
            double messReceived = System.currentTimeMillis();
            String rev = new String(packet.getData(), 0, packet.getLength());
            System.out.println("From Server " + rev);
            double timer = messReceived - messSend;
            System.out.println("Total Time of Response " + timer + " ms");
            if(timer > max){
              max = timer;
            }
            if(timer < min){
               min = timer;
            }
            avg += timer;
            System.out.println("Time Duration: " + (timer) + " ms");
            counter++;
            }
      System.out.println("Max: " + max + " ms");
      System.out.println("Min: " + min + " ms");
      System.out.println("Average: " + (avg/counter) + " ms");
      
      }
      catch(IOException ie)
      {
         System.out.println("An Error Occured " + ie);
      }
   }
}

