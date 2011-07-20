package jdbctemplate.po;

public class Plan {
  
  private int planId;
  private String planName;
  private String servType;
  
  public int getPlanId() {
    return planId;
  }
  public void setPlanId(int planId) {
    this.planId = planId;
  }
  public String getPlanName() {
    return planName;
  }
  public void setPlanName(String planName) {
    this.planName = planName;
  }
  public String getServType() {
    return servType;
  }
  public void setServType(String servType) {
    this.servType = servType;
  }
  
}
