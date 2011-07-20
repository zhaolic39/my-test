package xml.po;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class Plan {
  private int plan_id = 0;
  
  private String plan_name = "";
  
  private Date create_time = null;

  public Date getCreate_time() {
    return create_time;
  }

  public void setCreate_time(Date create_time) {
    this.create_time = create_time;
  }

  public int getPlan_id() {
    return plan_id;
  }

  public void setPlan_id(int plan_id) {
    this.plan_id = plan_id;
  }

  public String getPlan_name() {
    return plan_name;
  }

  public void setPlan_name(String plan_name) {
    this.plan_name = plan_name;
  }

  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
