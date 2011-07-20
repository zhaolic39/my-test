import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class HttpTest {
  public static void main(String[] args) {
    StringBuffer smsRes = new StringBuffer();
    byte[] req = getRequest();
    System.out.println(new String(req));
    try {
      URL url = new URL("http://m.us.mobileservice.blizzard.com/enrollment/enroll.htm");
      HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
      urlcon.setDoInput(true);
      urlcon.setDoOutput(true);
      urlcon.setRequestMethod("POST"); // 设置提交方式为POST
      urlcon.setRequestProperty("Content-type", "application/octet-stream");
      urlcon.setRequestProperty("Accept", "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
      urlcon.connect();

      OutputStream out = urlcon.getOutputStream();
      out.write(req);
      out.flush();
      out.close();

      InputStream ins = urlcon.getInputStream();
      byte[] bContent = new byte[2048];
      while (true) {
        int iReadNum = ins.read(bContent);
        if (iReadNum == -1) {
          break;
        }
        smsRes.append(new String(bContent, 0, iReadNum));
      }
      ins.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    System.out.println(smsRes);
  }
  
  public static byte[] getRequest() {
    String req = "10000000000000000000000000000000000000US0000000000000000";
    return req.getBytes();
//    byte[] req = new byte[56];
//    req[0] = 1;
//    
//    byte[] p_key = new byte[37];
//    for(int i=0;i<37;i++) {
//      p_key[i] = (byte)i;
//    }
//    System.arraycopy(p_key, 0, req, 1, 37);
//    System.arraycopy("US".getBytes(), 0, req, 38, 2);
//    
//    byte[] moblie = new byte[16];
//    for(int i=0;i<16;i++) {
//      moblie[i] = (byte)i;
//    }
//    System.arraycopy(p_key, 0, req, 40, 16);
//    
//    System.out.println(Arrays.toString(req));
//    return req;
  }
}
