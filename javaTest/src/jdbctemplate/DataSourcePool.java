package jdbctemplate;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * <p>Title: 数据库连接池管理类</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Aug 2, 2010
 */
public class DataSourcePool {

  private static DataSourcePool instance = new DataSourcePool();
  
  private ComboPooledDataSource cpds = null;
  
  /**
   * 单例方法
   * @return
   */
  public static DataSourcePool getInstance(){
    return instance;
  }
  
  private DataSourcePool(){
    init();
  }
  
  /**
   * 初始化 c3p0连接池
   */
  private void init(){
    try {
      System.out.println("c3p0 init datasource...");
      cpds = new ComboPooledDataSource();    
      cpds.setDriverClass("oracle.jdbc.driver.OracleDriver");   
      cpds.setJdbcUrl("jdbc:oracle:thin:@10.1.0.145:1521:ora9");    
      cpds.setUser("crm");    
      cpds.setPassword("crm");    
      cpds.setMinPoolSize(3);
      cpds.setMaxStatements(0);    
      cpds.setMaxPoolSize(100);
    }
    catch (PropertyVetoException e) {
      e.printStackTrace();
    }   
  }
  
  public Connection getConnection() throws SQLException{
    return cpds.getConnection();
  }
}
