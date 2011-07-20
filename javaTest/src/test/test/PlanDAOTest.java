package test;

import junit.framework.Assert;
import junit.framework.TestCase;
import ormtest.dao.PlanDAO;
import ormtest.vo.Plan;

public class PlanDAOTest extends TestCase{

  public void setUp() throws Exception{
  }
  
  public void tearDown() throws Exception{
  }
  
  public void testGetPlan() throws Exception{
    Plan plan = PlanDAO.getPlan(2219);
    Assert.assertNotNull(plan);
    
    Assert.assertEquals(2219, plan.getPlan_id());
    Assert.assertEquals("CARE", plan.getServ_type());
    
    System.out.println(plan.getPlan_desc());
  }
}
