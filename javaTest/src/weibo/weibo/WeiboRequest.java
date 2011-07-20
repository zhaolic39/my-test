package weibo;

import weibo.imp.qq.QWeiboRequest;
import weibo.entity.TimeLineResponse;

/**
 * <p>Title: ΢������������</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 May 11, 2011
 */
public abstract class WeiboRequest {
  
  /**
   * ���������࣬���ݲ�ͬ��΢��
   * @return
   */
  public static WeiboRequest create() {
    WeiboRequest wr = new QWeiboRequest();
    return wr;
  }
  
  /**
   * home lineʱ���߸���
   * @return
   */
  public abstract TimeLineResponse getHomeTimeLine();
}
