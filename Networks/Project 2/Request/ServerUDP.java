import java.net.*;  // for DatagramSocket and DatagramPacket
import java.io.*;   // for IOException

public class ServerUDP {

   public static void main(String[] args) throws Exception {
      System.out.println("Waiting for senders");
      if (args.length != 1 && args.length != 2)  // Test for correct # of args        
      {
         throw new IllegalArgumentException("Parameter(s): <Port> [<encoding>]");
      }
      int port = Integer.parseInt(args[0]);   // Receiving Port
      
      DatagramSocket sock = new DatagramSocket(port);  // UDP socket for receiving      
      DatagramPacket packet = new DatagramPacket(new byte[1024],1024);
      sock.receive(packet);
      System.out.println(new String(packet.getData(), 0, packet.getLength()));
      /*
      while(true){
         sock.receive(packet);
         RequestDecoder decoder = (args.length == 2 ?   // Which encoding              
              new RequestDecoderBin(args[1]) :
              new RequestDecoderBin() );
      
      
         Request receivedRequest = decoder.decode(packet);
         if(receivedRequest.tml == -1){
            break;
         }
         String [] packetString = {Integer.toHexString(receivedRequest.tml),Integer.toHexString(receivedRequest.requestID), Integer.toHexString(receivedRequest.operandCode),
            Integer.toHexString(receivedRequest.numberOperands), Integer.toHexString(receivedRequest.operand1), Integer.toHexString(receivedRequest.operand2)};
         int error = 0;
         for(int i = 0; i < packetString.length; i++){
            String temp = packetString[i];
            if(packetString[i].length() % 2 == 1){
                System.out.println("0x0" + temp.toUpperCase());
            }
            else{
               System.out.println("0x" + temp.toUpperCase());
            }
         }
         if(receivedRequest.tml * 3 != packet.getLength()){
            error = 127;
         }
         //System.out.println("Received Binary-Encoded Request");
         int result = 0;
         switch (receivedRequest.operandCode) {
            case 0:
               result = receivedRequest.operand1 + receivedRequest.operand2;
               break;
            case 1:
               result = receivedRequest.operand1 - receivedRequest.operand2;
               break;
            case 2:
               result = receivedRequest.operand1 * receivedRequest.operand2;
               break;
            case 3:
               result = receivedRequest.operand1 / receivedRequest.operand2;
               break;
            case 4:
               result = receivedRequest.operand1 >> receivedRequest.operand2;
               break;
            case 5:
               result = receivedRequest.operand1 << receivedRequest.operand2;
               break;
         
            default:
               result = ~receivedRequest.operand1;
               break;
         }
         
         //ANASTASIA
         System.out.println();
         Request res = new Request(receivedRequest.tml = 7, receivedRequest.requestID, error, result); 
         RequestEncoder encoderRequest = (args.length == 2 ?
         	  new RequestEncoderBin(args[1]) :
         	  new RequestEncoderBin());
         byte[] codedRequest = encoderRequest.encode(res);
      
         DatagramPacket message = new DatagramPacket(codedRequest, codedRequest.length, 
                   packet.getAddress(), packet.getPort());
         sock.send(message);

      }
      */
      sock.close();
   }
}