package ormtest.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import ormtest.util.DBSql;
import ormtest.vo.Plan;

/**
 * <p>Title: ¼Æ»®µÄDAO</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Sep 10, 2010
 */
public class PlanDAO {
  
  public static Plan getPlan(int plan_id){
    try {
      QueryRunner qr = DBSql.getRunner();
      String sql = "select PLAN_ID,PLAN_NAME,SERV_TYPE,PLAN_DESC from ob_plan where plan_id = ?";
      System.out.println("sql:" + sql);
      Plan plan = qr.query(sql, new BeanHandler<Plan>(Plan.class), new Integer(plan_id));
      return plan;
    }
    catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public static void addPlan(Plan plan){
    try {
      QueryRunner qr = DBSql.getRunner();
      String sql = "insert into ob_plan ('PLAN_ID','PLAN_NAME','SERV_TYPE','PLAN_DESC') values(?, ?, ?, ?)";
      Object[] params = new Object[]{plan.getPlan_id(), plan.getPlan_name(), plan.getServ_type(), plan.getPlan_desc()};
      qr.update(sql, params);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
}
