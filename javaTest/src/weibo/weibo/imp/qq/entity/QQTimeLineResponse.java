package weibo.imp.qq.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * <p>Title: 腾讯微博时间线返回结果</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 May 11, 2011
 */
public class QQTimeLineResponse {
  private int ret = 0;
  private String msg = "";
  private QQTimeLineData data;
  
  public int getRet() {
    return ret;
  }
  public void setRet(int ret) {
    this.ret = ret;
  }
  public String getMsg() {
    return msg;
  }
  public void setMsg(String msg) {
    this.msg = msg;
  }
  public QQTimeLineData getData() {
    return data;
  }
  public void setData(QQTimeLineData data) {
    this.data = data;
  }

  public String toString(){
    return ReflectionToStringBuilder.toString(this);
  }
}
