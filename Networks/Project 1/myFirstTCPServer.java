import java.net.*;  // for Socket, ServerSocket, and InetAddress
import java.io.*;   // for IOException and Input/OutputStream

public class myFirstTCPServer {

   private static final int BUFSIZE = 32;   // Size of receive buffer

   public static void main(String[] args) throws IOException {
   
      if (args.length != 1)  // Test for correct # of args
         throw new IllegalArgumentException("Parameter(s): <Port>");
   
      int servPort = Integer.parseInt(args[0]);
      
      ServerSocket servSock = new ServerSocket(servPort); // create a server socket
      int recvMsgSize; // Size of received message 
		byte[] byteBuffer = new byte[BUFSIZE]; 
      System.out.println("Looking for a Client...");
      Socket clntSock = servSock.accept(); // set client connection
      System.out.println("Client Found!");
      //DataInputStream in = new DataInputStream(new BufferedInputStream(clntSock.getInputStream()));
      //BufferedReader in = new BufferedReader(new InputStreamReader(clntSock.getInputStream()));
      //String msg = "";
      InputStream in = clntSock.getInputStream(); 
		OutputStream out = clntSock.getOutputStream(); 
			// Receive until client closes connection, indicated by -1 return 
			while ((recvMsgSize = in.read(byteBuffer)) != -1){
            System.out.println("IP Address: " + clntSock.getInetAddress().getHostAddress());
            System.out.println("Port #: " + clntSock.getPort());
            String msg = new String(byteBuffer).substring(0, recvMsgSize);
            String rMsg = "";
            for(int i = msg.length()-1; i >= 0; i--){
               rMsg += msg.charAt(i);
            }
            System.out.println(rMsg);
            out.write(rMsg.getBytes(), 0, recvMsgSize); 
            
         }
			clntSock.close(); // Close the socket. We are done with this client!
   }
}
