package test;

import junit.framework.TestCase;
import ormtest.vo.Plan;
import cache.CacheFactory;

import com.google.gson.Gson;

public class CacheFactoryTest extends TestCase{

  public void setUp() throws Exception{
  }
  
  public void tearDown() throws Exception{
  }
  
  public void testCache() throws Exception{
    long begin_time = System.currentTimeMillis();
    Gson gson = new Gson();
    CacheFactory factory = CacheFactory.getInstance();
    System.out.println("first load...");
    Plan plan = factory.getCachePlan(2219);
    System.out.println(gson.toJson(plan));
    
    System.out.println("second load...");
    plan = factory.getCachePlan(2219);
    System.out.println(gson.toJson(plan));
    
//    System.out.println("clear cache...");
//    factory.clearCache(CacheFactory.PLAN_CACHE);
    
    System.out.println("third load...");
    plan = factory.getCachePlan(2219);
    System.out.println(gson.toJson(plan));
    
    System.out.println("used " + (System.currentTimeMillis()-begin_time) + "ms");
  }
}
