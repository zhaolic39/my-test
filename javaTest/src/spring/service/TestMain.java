package spring.service;

import spring.handler.AbstractHandler;
import spring.service.request.GetPlanRequest;
import spring.service.response.GetPlanResponse;
import spring.util.ServerContext;


public class TestMain {

  /**
   * @param args
   */
  public static void main(String[] args) {
    GetPlanRequest req = new GetPlanRequest();
    req.setPlan_id(1790);
    GetPlanResponse res = (GetPlanResponse)ServerContext.getInstance().invokeService("get_plan", req);
    
    
    String reqstr = "<request><plan_id>1790</plan_id></request>";
    AbstractHandler handler = AbstractHandler.getHandler("XML");
    handler.invokeService(reqstr);
    System.out.println(res.getPlan());
    
//    ServerContext.getInstance().context.refresh();
  }

}
