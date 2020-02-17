import java.io.*;  // for ByteArrayOutputStream and DataOutputStream

public class RequestEncoderBin implements RequestEncoder, RequestBinConst {

  private String encoding;  // Character encoding

  public RequestEncoderBin() {
    encoding = DEFAULT_ENCODING;
  }

  public RequestEncoderBin(String encoding) {
    this.encoding = encoding;
  }

  public byte[] encode(Request request) throws Exception {

    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(buf);
    out.writeInt(request.tml);
    out.writeInt(request.requestID);
    if(request.tml == 7){
      out.writeInt(request.error);
      out.writeInt(request.result);
      out.flush();
      return buf.toByteArray();
    }
    out.writeInt(request.operandCode);
    out.writeInt(request.numberOperands);
    out.writeInt(request.operand1);
    if(request.numberOperands == 2){
      out.writeInt(request.operand2);
    }
    out.flush();
    return buf.toByteArray();
  }
}
