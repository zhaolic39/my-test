package test;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import ormtest.util.DBLocator;
import ormtest.util.DBSql;


/**
 * <p>Title: DBSql≤‚ ‘¿‡</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Sep 10, 2010
 */
public class DBSqlTest extends TestCase{
  
  public void setUp() throws Exception{
  }
  
  public void tearDown() throws Exception{
  }
  
  public void testGetDataSource() throws Exception{
    DataSource ds = DBLocator.getInstance().getDataSource();
    Assert.assertNotNull(ds);
  }
  
  public void testGetConnection() throws Exception{
    Connection con = DBLocator.getInstance().getConnection();
    Assert.assertNotNull(con);
  }
  
  public void testGetQueryRunner() throws Exception{
    QueryRunner qr = DBSql.getRunner();
    Assert.assertNotNull(qr);
    
    List<Map<String,Object>> result = qr.query("select sysdate from dual", new MapListHandler());
    Assert.assertNotNull(result);
    
    Assert.assertNotNull(result.get(0).get("sysdate"));
    System.out.println(result.get(0).get("sysdate"));
  }
}
