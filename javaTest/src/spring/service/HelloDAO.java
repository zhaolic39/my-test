package spring.service;

import ormtest.vo.Plan;
import spring.util.DbUtilsTemplate;

public class HelloDAO {
  private DbUtilsTemplate dbUtilsTemplate;

  public DbUtilsTemplate getDbUtilsTemplate() {
    return dbUtilsTemplate;
  }

  public void setDbUtilsTemplate(DbUtilsTemplate dbUtilsTemplate) {
    this.dbUtilsTemplate = dbUtilsTemplate;
  }

  public void doDao(){
    System.out.println("hello dao");
  }
  
  public Plan getPlan(int plan_id){
      Plan plan = dbUtilsTemplate.findFirst(Plan.class, "select PLAN_ID,PLAN_NAME,SERV_TYPE,PLAN_DESC from ob_plan where plan_id = ?", new Integer(plan_id));
      return plan;
  }
}
