package jdbctemplate;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>Title: 调用数据库的回调方法接口类</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Jul 20, 2010
 */
public interface JdbcCallBack <T> {
  
  /**
   * 查询结果的实现方法
   * @param rs
   * @return
   * @throws SQLException 
   */
  public T doInStatement(Statement stat) throws SQLException;
}
