package weibo.imp.qq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import weibo.WeiboRequest;
import weibo.WeiboSession;
import weibo.entity.TimeLineInfo;
import weibo.entity.TimeLineResponse;
import weibo.imp.qq.entity.QQTimeLineData;
import weibo.imp.qq.entity.QQTimeLineInfo;
import weibo.imp.qq.entity.QQTimeLineResponse;
import weibo.oauth.OAuthConfig;
import weibo.util.WebRequestUtil;

import com.google.gson.Gson;
import com.google.gson.JsonFormatter;

/**
 * <p>Title: 微博请求调用入口</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 May 11, 2011
 */
public class QWeiboRequest extends WeiboRequest{
  
  public TimeLineResponse getHomeTimeLine() {
    OAuthConfig config = OAuthConfig.create();
    WeiboSession session = WeiboSession.getSession();
    
    String timestamp = String.valueOf(WebRequestUtil.getTimestamp());
    String nonce = WebRequestUtil.getRandomString(18);
    String signature_key = config.getOauthSignature() + "&" + session.getAccess_token_secret();
      
    Map<String, String> params = new HashMap<String, String>();
    
    params.put("oauth_consumer_key", config.getOauthConsumerKey());
    params.put("oauth_nonce", nonce);
    params.put("oauth_signature_method", OAuthConfig.OAUTH_SIGNATURE_METHOD);
    params.put("oauth_timestamp", timestamp);
    params.put("oauth_token", session.getAccess_token());
    params.put("oauth_version", "1.0");
    
    params.put("format", "json");
    params.put("pageflag", "0");
    params.put("reqnum", "20");
    params.put("pagetime", "0");
    
    String signature = WebRequestUtil.getSignature("GET", QWeiboConst.URL_HOME_TIMELINE, signature_key, params);
    System.out.println("signature:" + signature);
    
    String url = QWeiboConst.URL_HOME_TIMELINE + "?" + "oauth_signature=" + signature + "&"+WebRequestUtil.getBaseString(params);
    System.out.println("url:" + url);
    
    String json = WebRequestUtil.httpGet(url);
    json = JsonFormatter.formtJson(json);
    System.out.println(json);
    Gson gson = new Gson(); 
    QQTimeLineResponse tlres = gson.fromJson(json, QQTimeLineResponse.class);
    
    TimeLineResponse response = qqRes2WeiBoRes(tlres);
    return response;
  }
  
  /**
   * 作转换
   * @param qq_res
   * @return
   */
  private static TimeLineResponse qqRes2WeiBoRes(QQTimeLineResponse qq_res){
    TimeLineResponse response = new TimeLineResponse();
    response.result_code = qq_res.getRet();
    response.result_msg = qq_res.getMsg();
    if(response.result_code == 0){  //返回成功
      QQTimeLineData data = qq_res.getData();
      response.timestamp = data.getTimestamp();
      QQTimeLineInfo[] qq_infos = data.getInfo();
      if(qq_infos != null && qq_infos.length > 0){  //有信息
        List<TimeLineInfo> info_list = new ArrayList<TimeLineInfo>();
        for(QQTimeLineInfo qq_info :qq_infos){
          TimeLineInfo time_info = new TimeLineInfo();
          try {
            BeanUtils.copyProperties(time_info, qq_info); //复制bean属性
          } catch (Exception e) {
            e.printStackTrace();
          }
          info_list.add(time_info);
        }
        response.setInfo(info_list);
      }
    }
    return response;
  }
  
  public static void main(String[] args) {
    TimeLineResponse tlres = new QWeiboRequest().getHomeTimeLine();
    System.out.println(tlres);
  }
}
