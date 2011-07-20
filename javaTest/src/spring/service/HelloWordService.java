package spring.service;

import ormtest.vo.Plan;
import spring.annotation.Service;
import spring.service.request.GetPlanRequest;
import spring.service.response.GetPlanResponse;

public class HelloWordService {
  
  private HelloDAO helloDAO;
  
  public HelloDAO getHelloDAO() {
    return helloDAO;
  }

  public void setHelloDAO(HelloDAO helloDAO) {
    this.helloDAO = helloDAO;
  }

  @Service(serviceName="say_hello")
  public void sayHello(){
    helloDAO.doDao();
  }
  
  @Service(serviceName="get_plan")
  public GetPlanResponse getPlan(GetPlanRequest request){
    Plan p = helloDAO.getPlan(request.getPlan_id());
//    System.out.println(p.toString());
    GetPlanResponse response = new GetPlanResponse();
    response.setPlan(p);
    return response;
  }
}
