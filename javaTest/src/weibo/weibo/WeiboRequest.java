package weibo;

import weibo.imp.qq.QWeiboRequest;
import weibo.entity.TimeLineResponse;

/**
 * <p>Title: 微博请求调用入口</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 May 11, 2011
 */
public abstract class WeiboRequest {
  
  /**
   * 创建请求类，根据不同的微博
   * @return
   */
  public static WeiboRequest create() {
    WeiboRequest wr = new QWeiboRequest();
    return wr;
  }
  
  /**
   * home line时间线更新
   * @return
   */
  public abstract TimeLineResponse getHomeTimeLine();
}
