import java.io.*;  // for ByteArrayInputStream
import java.net.*; // for DatagramPacket

public class RequestDecoderBin implements RequestDecoder, RequestBinConst {

  private String encoding;  // Character encoding

  public RequestDecoderBin() {
    encoding = DEFAULT_ENCODING;
  }

  public RequestDecoderBin(String encoding) {
    this.encoding = encoding;
  }

  public Request decode(InputStream wire) throws IOException {
    DataInputStream src = new DataInputStream(wire);
    int operand2 = 0;
    int tml = src.readInt();
    int requestID = src.readInt();
    if(tml == 7){
      int error = src.readInt();
      int result = src.readInt();
      return new Request(tml, requestID, error, result); 
    }
    int operandCode = src.readInt();
    int numberOperands = src.readInt();
    int operand1 = src.readInt();
    if(numberOperands == 2){
      operand2 = src.readInt(); 
      return new Request(tml, requestID, operandCode, numberOperands, operand1, operand2);
    }
    return new Request(tml, requestID, operandCode, numberOperands, operand1);
    
  }

  public Request decode(DatagramPacket p) throws IOException {
    ByteArrayInputStream payload =
      new ByteArrayInputStream(p.getData(), p.getOffset(), p.getLength());
    return decode(payload);
  }
}
