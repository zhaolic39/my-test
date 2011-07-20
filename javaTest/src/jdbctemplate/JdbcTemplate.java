package jdbctemplate;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>Title: jdbc调用的模版类</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Jul 20, 2010
 */
public class JdbcTemplate{
  
  /**
   * 取得数据库连接
   * @return
   */
  private Connection getConnection(){
    String url = "jdbc:oracle:thin:@10.1.0.145:1521:ora9";
    String driver = "oracle.jdbc.driver.OracleDriver";
    String user = "crm";
    String pwd = "crm";
    
    Driver d;
    try {
      d = (Driver) Class.forName(driver).newInstance();
      DriverManager.registerDriver(d);
      
      return DriverManager.getConnection(url, user, pwd);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
  public final Object query(JdbcCallBack action) throws SQLException{
    Connection con = DataSourcePool.getInstance().getConnection();//getConnection();
    Statement stmt = null;  
    try {  
        stmt = con.createStatement();  
//        ResultSet rs = stmt.executeQuery(sql);  
        Object result = action.doInStatement(stmt);  
        return result;  
    }  
    catch (SQLException ex) {  
         ex.printStackTrace();  
         throw ex;  
    }  
    finally {  
        try {  
            stmt.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        try {  
            if(!con.isClosed()){  
                try {  
                    con.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  }
  
}
