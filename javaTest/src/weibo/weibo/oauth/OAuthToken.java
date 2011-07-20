package weibo.oauth;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class OAuthToken {
  public String oauth_token = "";
  
  public String oauth_token_secret = "";
  
  public String oauth_verifier = "";
  
  public boolean oauth_callback_confirmed = false;
  
  public String acess_token = "";
  
  public String acess_token_secret = "";
  
  /** 请求结果 0:成功 */
  public int result_code = 0;
  
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
