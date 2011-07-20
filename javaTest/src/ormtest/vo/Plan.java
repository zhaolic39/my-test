package ormtest.vo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class Plan {
  
  int plan_id;
  
  String plan_name;
  
  String serv_type;
  
  String plan_desc;
  
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
  
  public String getServ_type() {
    return serv_type;
  }
  
  public void setServ_type(String serv_type) {
    this.serv_type = serv_type;
  }
  
  public String getPlan_desc() {
    return plan_desc;
  }
  
  public void setPlan_desc(String plan_desc) {
    this.plan_desc = plan_desc;
  }
  
  public String toString(){
    return ReflectionToStringBuilder.toString(this);
  }
  
}
