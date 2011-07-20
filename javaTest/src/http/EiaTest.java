package http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class EiaTest {
  public static void main(String[] args) throws ClientProtocolException, IOException {
    
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httpget = new HttpPost("http://www.eia.net.cn/zyzg/checksign.php?id=6df39ab0d2c32c2138c55c35781521b3"); 
    
    System.out.println("executing request " + httpget.getURI());
    HttpResponse response = httpclient.execute(httpget);
    HttpEntity entity = response.getEntity();
    
    String resp = EntityUtils.toString(entity);
    httpget.abort();
    
    // When HttpClient instance is no longer needed, 
    // shut down the connection manager to ensure
    // immediate deallocation of all system resources
    httpclient.getConnectionManager().shutdown();        
    
    System.out.println(resp);
  }
}
