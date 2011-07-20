package weibo.oauth;


/**
 * <p>Title: oauth ≈‰÷√</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 May 9, 2011
 */
public class OAuthConfig {
  /** app key */
  public static String OAUTH_CONSUMER_KEY = "73f2a315213248999ac94050e992a01d";
  
  /** app secret */
  public static String OAUTH_SIGNATURE = "fde38dab425d575a08b3ea552be026cc";
  
  /** «©√˚∑Ω∑® */
  public static String OAUTH_SIGNATURE_METHOD = "HMAC-SHA1";
  
  public static OAuthConfig create(){
    return new OAuthConfig();
  }
  
  public OAuthConfig() {}
  
  public String getOauthConsumerKey() {
    return OAUTH_CONSUMER_KEY;
  }
  
  public String getOauthSignature() {
    return OAUTH_SIGNATURE;
  }
  
  public String getOauthRequestUrl() {
    return "https://open.t.qq.com/cgi-bin/request_token";
  }
  
  public String getAcessRequestUrl() {
    return "https://open.t.qq.com/cgi-bin/access_token";
  }
  
  public String getOauthAuthorizeUrl() {
    return "https://open.t.qq.com/cgi-bin/authorize";
  }
  
  public String getOauthCallBackUrl() {
    return "http://10.1.0.143:38001/tool/";
  }
  
}
