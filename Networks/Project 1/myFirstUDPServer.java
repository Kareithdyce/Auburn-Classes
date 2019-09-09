import java.io.*;
import java.net.*;

public class myFirstUDPServer {

   private static final int BUFSIZE = 255;   // Size of receive buffer


   public static void main(String args[]) {
   
      if (args.length != 1)  // Test for correct argument list
         throw new IllegalArgumentException("Parameter(s): <Port>");
         
      int servPort = Integer.parseInt(args[0]);
   
      try {
      
           //Create the socket for server side
         DatagramSocket sock = new DatagramSocket(servPort);
         System.out.println("Looking for a Client...");
         String S = "";
         while(!(S.equals("end"))){
             
             //for receiving from client
            byte[] byteBuffer = new byte[BUFSIZE];
                  
             //Packet to receive the data from client
            DatagramPacket packet = new DatagramPacket(byteBuffer,byteBuffer.length);
            sock.receive(packet);
            System.out.println("Client Found!");
             
             //convert the message to string so it can be processed
            S = new String(packet.getData(), 0, packet.getLength());
            StringBuilder message = new StringBuilder();
            System.out.println("From Client " + S);
            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();
            System.out.println("Handling client at " + clientAddress.getHostAddress() 
			                       + " on port " + clientPort); 

             //Reverse the message
            message.append(S);
            message = message.reverse();
            System.out.println("Reversed: " + message);
            String rev = message.toString();
             //convert the message to bytes
            byteBuffer = rev.getBytes();
               
             //Add the information to packet and send reply back to client
            packet = new DatagramPacket(byteBuffer, 0, byteBuffer.length, clientAddress, clientPort);
            sock.send(packet);
         }
      }
      catch(IOException ie)
      {
         System.out.println("An Error Occured " + ie);
      }
      
   }
}