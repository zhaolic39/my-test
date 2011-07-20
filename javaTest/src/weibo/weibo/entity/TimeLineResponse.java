package weibo.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 时间线应答
 * @author zhaoli
 *
 */
public class TimeLineResponse {
	public long timestamp = 0;
	public int result_code = 0;
	public String result_msg = "";
	
	private List<TimeLineInfo> info = new ArrayList<TimeLineInfo>();

  public List<TimeLineInfo> getInfo() {
    return info;
  }

  public void setInfo(List<TimeLineInfo> info) {
    this.info = info;
  }
	
  public String toString(){
    return ReflectionToStringBuilder.toString(this);
  }
}
