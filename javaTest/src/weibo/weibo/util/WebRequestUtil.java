package weibo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * <p>Title: 服务请求工具类</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 May 11, 2011
 */
public class WebRequestUtil {

  /**
   * 时间戳
   * @return
   */
  public static long getTimestamp() {
    return System.currentTimeMillis()/1000;
  }
  
  /**
   * 随机字符串
   * @param length
   * @return
   */
  public static String getRandomString(int length) { //length表示生成字符串的长度
    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
    Random random = new Random();   
    StringBuffer sb = new StringBuffer();   
    for (int i = 0; i < length; i++) {   
        int number = random.nextInt(base.length());   
        sb.append(base.charAt(number));   
    }   
    return sb.toString();   
  }
  
  /**
   * 解析返回参数
   * @param resp
   * @return
   */
  public static Map<String, String> getResponseParam(String resp) {
    Map<String, String> param = new HashMap<String, String>();
    String[] aa = resp.split("&");
    for(String s:aa) {
      String param_name = s.substring(0, s.indexOf("="));
      String param_value = s.substring(s.indexOf("=")+1);
      param.put(param_name, param_value);
    }
    return param;
  }
  
  /**
   * 获得签名
   * @param method
   * @param requst_token_url
   * @param signature_key
   * @param params
   * @return
   */
  public static String getSignature(String method, String requst_token_url, String signature_key, Map<String, String> params) {
    String signature = "";
    try {
      String bss = method + "&" + 
                   URLEncoder.encode(requst_token_url, "utf-8") + "&";
      
      String bsss = URLEncoder.encode(getBaseString(params), "utf-8");
      String baseString = bss + bsss;
      signature = hmacsha1(baseString, signature_key);
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return signature;
  }
  
  /**
   * 根据参数拼写base string
   * @param params
   * @return
   * @throws UnsupportedEncodingException
   */
  public static String getBaseString(Map<String, String> params){
    StringBuffer sbsss = new StringBuffer();
    try {
      Object[] keys = params.keySet().toArray();
      Arrays.sort(keys);  //排序
      for(Object key:keys) {
        String param_name = key.toString();
        String param_value = params.get(param_name);
        sbsss.append(param_name).append("=").append(URLEncoder.encode(param_value, "utf-8")).append("&");
      }
      sbsss.setLength(sbsss.length() - 1);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return sbsss.toString();
  }
  
  /**
   * 签名加密
   * @param data
   * @param key
   * @return
   */
  private static String hmacsha1(String data, String key) {
    byte[] byteHMAC = null;
    try {
      Mac mac = Mac.getInstance("HmacSHA1");
      SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
      mac.init(spec);
      byteHMAC = mac.doFinal(data.getBytes());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    String oauth = BASE64Encoder.encode(byteHMAC);
    return oauth;
  }
  
  public static String httpGet(String url) {
    HttpClient httpclient = new DefaultHttpClient();
    HttpGet httpget = new HttpGet(url); 
    HttpResponse response;
    String resp = "";
    try {
      response = httpclient.execute(httpget);
      HttpEntity entity = response.getEntity();
      resp = EntityUtils.toString(entity);
      httpclient.getConnectionManager().shutdown();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return resp;
  }
}
