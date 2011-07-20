package xml.po;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class Content {
  private Plan[] plan = null;

  public Plan[] getPlan() {
    return plan;
  }

  public void setPlan(Plan[] plan) {
    this.plan = plan;
  }
  
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}
