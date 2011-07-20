package weibo.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import weibo.util.WebRequestUtil;


/**
 * <p>Title: OAuth 认证请求类</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 May 9, 2011
 */
public class OAuthRequest {
  
  /**
   * oauth 授权第一步 获得request token
   * @return
   */
  public OAuthToken getRequestToken() {
    OAuthConfig config = OAuthConfig.create();
             
    String timestamp = String.valueOf(WebRequestUtil.getTimestamp());
    String nonce = WebRequestUtil.getRandomString(18);
    String signature_key = config.getOauthSignature() + "&";
      
    Map<String, String> params = new HashMap<String, String>(); //参数的顺序需要固定
    params.put("oauth_callback", config.getOauthCallBackUrl());
    params.put("oauth_consumer_key", config.getOauthConsumerKey());
    params.put("oauth_nonce", nonce);
    params.put("oauth_signature_method", OAuthConfig.OAUTH_SIGNATURE_METHOD);
    params.put("oauth_timestamp", timestamp);
    params.put("oauth_version", "1.0");
    
    String signature = WebRequestUtil.getSignature("GET", config.getOauthRequestUrl(), signature_key, params);
    System.out.println("signature:" + signature);
    
    StringBuffer req_param = new StringBuffer("");
    req_param.append("oauth_consumer_key=").append(config.getOauthConsumerKey())
             .append("&oauth_signature_method=").append(OAuthConfig.OAUTH_SIGNATURE_METHOD)
             .append("&oauth_signature=").append(signature)
             .append("&oauth_timestamp=").append(timestamp)
             .append("&oauth_nonce=").append(nonce)
             .append("&oauth_callback=").append(config.getOauthCallBackUrl());
    String url = config.getOauthRequestUrl() + "?" + req_param.toString();
    System.out.println("url:" + url);
    
    HttpClient httpclient = new DefaultHttpClient();
    HttpGet httpget = new HttpGet(url); 
    HttpResponse response;
    OAuthToken token_response = new OAuthToken();
    
    try {
      response = httpclient.execute(httpget);
      HttpEntity entity = response.getEntity();
      String resp = EntityUtils.toString(entity);
      
      if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {  //请求request token成功
        System.out.println("request token response:" + resp);
        //oauth_token=f8ce6fa0fb514f4090482e2533b03279&oauth_token_secret=eedcef71c50b967efb2998ce42bb6ba4&oauth_callback_confirmed=true
        Map<String, String> param = WebRequestUtil.getResponseParam(resp);
        token_response.oauth_token = param.get("oauth_token");
        token_response.oauth_token_secret = param.get("oauth_token_secret");
        token_response.oauth_callback_confirmed = "true".equals(param.get("oauth_callback_confirmed"));
      }else {
        System.out.println("request token fail:" + resp);
        token_response.result_code = -1;
      }
      httpclient.getConnectionManager().shutdown();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return token_response;
  }
  
  /**
   * 获取通行令牌
   * @param token
   * @return
   */
  public OAuthToken getAcessToken(OAuthToken token) {
    OAuthConfig config = OAuthConfig.create();
    
    String timestamp = String.valueOf(WebRequestUtil.getTimestamp());
    String nonce = WebRequestUtil.getRandomString(18);
    String signature_key = config.getOauthSignature() + "&" + token.oauth_token_secret;
    
    Map<String, String> params = new HashMap<String, String>(); //参数的顺序需要固定
    params.put("oauth_consumer_key", config.getOauthConsumerKey());
    params.put("oauth_nonce", nonce);
    params.put("oauth_signature_method", OAuthConfig.OAUTH_SIGNATURE_METHOD);
    params.put("oauth_timestamp", timestamp);
    params.put("oauth_token", token.oauth_token);
    params.put("oauth_verifier", token.oauth_verifier);
    params.put("oauth_version", "1.0");
    
    String signature = WebRequestUtil.getSignature("GET", config.getAcessRequestUrl(), signature_key, params);
    System.out.println("signature:" + signature);
    
    StringBuffer req_param = new StringBuffer("");
    req_param.append("oauth_consumer_key=").append(config.getOauthConsumerKey())
             .append("&oauth_signature_method=").append(OAuthConfig.OAUTH_SIGNATURE_METHOD)
             .append("&oauth_signature=").append(signature)
             .append("&oauth_timestamp=").append(timestamp)
             .append("&oauth_token=").append(token.oauth_token)
             .append("&oauth_verifier=").append(token.oauth_verifier)
             .append("&oauth_nonce=").append(nonce);
    String url = config.getAcessRequestUrl() + "?" + req_param.toString();
    System.out.println("url:" + url);
    
    HttpClient httpclient = new DefaultHttpClient();
    HttpGet httpget = new HttpGet(url); 
    HttpResponse response;
    
    try {
      response = httpclient.execute(httpget);
      HttpEntity entity = response.getEntity();
      String resp = EntityUtils.toString(entity);
      
      System.out.println(resp);
      if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {  //请求request token成功
        System.out.println("request token response:" + resp);
        //oauth_token=f8ce6fa0fb514f4090482e2533b03279&oauth_token_secret=eedcef71c50b967efb2998ce42bb6ba4&oauth_callback_confirmed=true
        Map<String, String> param = WebRequestUtil.getResponseParam(resp);
        token.acess_token = param.get("oauth_token");
        token.acess_token_secret = param.get("oauth_token_secret");
      }else {
        System.out.println("request token fail:" + resp);
        token.result_code = -1;
      }
      httpclient.getConnectionManager().shutdown();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return token;
  }
  
  public static void main(String[] args) throws IOException {
    OAuthConfig config = OAuthConfig.create();
    OAuthToken token = new OAuthRequest().getRequestToken();
    if(token.result_code == 0) {
      String token_url = OAuthConfig.create().getOauthAuthorizeUrl() + "?oauth_token=" + token.oauth_token + "&oauth_callback="+config.getOauthCallBackUrl();
      System.out.println("token_url:" + token_url);
      Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + token_url);
      
      String aString = "";
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      try {
        aString = br.readLine();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      
      token.oauth_verifier = aString;
      new OAuthRequest().getAcessToken(token);
    }
  }
}
