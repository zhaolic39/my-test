package spring.service.response;

import ormtest.vo.Plan;

public class GetPlanResponse {
  private Plan plan = null;

  public Plan getPlan() {
    return plan;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }

}
