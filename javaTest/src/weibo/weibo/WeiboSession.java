package weibo;

/**
 * <p>Title: 当前使用的帐号</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 May 11, 2011
 */
public class WeiboSession {
  private String name = "zhaolic39";
  
  private String access_token = "0a1155987e2149b3974725367d56190f";
  
  private String access_token_secret = "729f7ab2d1570f201935e65c74bc56b0";
  
  private String nick_name = "";
  
  private String weibo_type = "";
  
  private static WeiboSession session = null;
  
  public static WeiboSession getSession() {
    if(session == null) session = new WeiboSession();
    return session;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public String getAccess_token_secret() {
    return access_token_secret;
  }

  public void setAccess_token_secret(String access_token_secret) {
    this.access_token_secret = access_token_secret;
  }

  public String getNick_name() {
    return nick_name;
  }

  public void setNick_name(String nick_name) {
    this.nick_name = nick_name;
  }

  public String getWeibo_type() {
    return weibo_type;
  }

  public void setWeibo_type(String weibo_type) {
    this.weibo_type = weibo_type;
  }
  
}
