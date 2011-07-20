package weibo.imp.qq.entity;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class QQTimeLineData {
  private int pos = 0;
  private long timestamp = 0;
  private int hasnext = 0;
  private QQTimeLineInfo[] info;
  
  public int getPos() {
    return pos;
  }
  public void setPos(int pos) {
    this.pos = pos;
  }
  public long getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
  public int getHasnext() {
    return hasnext;
  }
  public void setHasnext(int hasnext) {
    this.hasnext = hasnext;
  }
  public QQTimeLineInfo[] getInfo() {
    return info;
  }
  public void setInfo(QQTimeLineInfo[] info) {
    this.info = info;
  }
  
  public String toString(){
    return ReflectionToStringBuilder.toString(this);
  }
}
