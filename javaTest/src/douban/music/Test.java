package douban.music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class Test {


  public static void main(String[] args) throws ClientProtocolException, IOException {
    String res_json = getJson();
    System.out.println(res_json);
    
    Gson gson = new Gson(); 
    JsonParser jsonparer = new JsonParser();//初始化解析json格式的对象
    JsonArray jsonarray = jsonparer.parse(res_json).getAsJsonObject().getAsJsonArray("song");
    List<DoubanSongInfo> song_list = new ArrayList<DoubanSongInfo>();
    for(int  i=0;i<jsonarray.size();i++){
       String song_json = jsonarray.get(i).toString();
       DoubanSongInfo song_info = gson.fromJson(song_json, DoubanSongInfo.class);
       song_list.add(song_info);
    }
    for(DoubanSongInfo info:song_list) {
      System.out.println(info);
    }
  }
  
  public static String getJson() throws ClientProtocolException, IOException {        
    HttpClient httpclient = new DefaultHttpClient();
    HttpGet httpget = new HttpGet("http://douban.fm/j/mine/playlist?type=n&channel=1"); 
    
    System.out.println("executing request " + httpget.getURI());
    HttpResponse response = httpclient.execute(httpget);
    HttpEntity entity = response.getEntity();
    
    String resp = EntityUtils.toString(entity);
    httpget.abort();
    
    // When HttpClient instance is no longer needed, 
    // shut down the connection manager to ensure
    // immediate deallocation of all system resources
    httpclient.getConnectionManager().shutdown();        
    
    return resp;
  }


}
