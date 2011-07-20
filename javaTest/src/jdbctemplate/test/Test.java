package jdbctemplate.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbctemplate.JdbcCallBack;
import jdbctemplate.JdbcTemplate;
import jdbctemplate.po.Plan;
import junit.framework.TestCase;

public class Test extends TestCase {

  public void testQueryPlan() {
//    assertEquals(1, 1);
//    assertEquals(phone, mgr.getPhone(13888816666L));
    queryPlan(21);
    queryPlan(41);
  }

  /**
   * 
   */
  private static void queryPlan(int planId) {
    final String sql = "select * from ob_plan where plan_id = " + planId;
    
    //定义查询使用的回调函数
    JdbcCallBack<List<Plan>> jcb = new JdbcCallBack<List<Plan>>(){
      @Override
      public List<Plan> doInStatement(Statement stat) throws SQLException {
        List<Plan> list = new ArrayList<Plan>();
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next()){
          Plan plan = new Plan();
          plan.setPlanId(rs.getInt(1));
          plan.setPlanName(rs.getString(2));
          plan.setServType(rs.getString(3));
          
          list.add(plan);
        }
        return list;
      }
    };
    
    try {
      List<Plan> l = (List<Plan>) new JdbcTemplate().query(jcb);
      for(int i=0;i<l.size();i++){
        Plan p = (Plan)l.get(i);
        System.out.println(p.getPlanId() + "," + p.getPlanName() + "," + p.getServType());
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
