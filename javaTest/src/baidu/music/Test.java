package baidu.music;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Test {
  public static void main(String[] args) throws UnsupportedEncodingException {
    String xml = getXml();
//    try {
//      SAXReader saxReader = new SAXReader();
//
//      Document document = saxReader.read("http://box.zhangmen.baidu.com/x?op=12&count=1&title=稻香$$周杰伦$$$$");
//      Element root = document.getRootElement();
//      Element count_node = (Element)root.selectObject("count");
//      System.out.println(count_node.getText());
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
    
  }
  
  
  public static String getXml() {
    String web_url = "http://box.zhangmen.baidu.com/x?op=12&count=1&title=稻香$$周杰伦$$$$";
    System.out.println("surl:" + web_url);
    
    StringBuffer result = new StringBuffer();
    try {
      URL url = new URL(web_url);
      HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
      urlcon.setDoInput(true);
      urlcon.setDoOutput(true);
      urlcon.setRequestMethod("POST"); // 设置提交方式为POST
      urlcon.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
      urlcon.connect();

      OutputStream out = urlcon.getOutputStream();
      out.flush();
      out.close();

      
      InputStream ins = urlcon.getInputStream();
      byte[] bContent = new byte[2048];
      while (true) {
        int iReadNum = ins.read(bContent);
        if (iReadNum == -1) {
          break;
        }
        result.append(new String(bContent, 0, iReadNum));
      }
      ins.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    System.out.println(result);
    
    return result.toString();
  }
}
