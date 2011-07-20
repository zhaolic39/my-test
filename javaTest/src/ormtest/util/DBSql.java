package ormtest.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

/**
 * <p>Title: DB操作工具类</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Sep 10, 2010
 */
public class DBSql {
  
  /**
   * 使用默认数据源的数据源操作类
   * @return
   * @throws SQLException
   */
  public static QueryRunner getRunner(){
    try {
      return new QueryRunner(DBLocator.getInstance().getDataSource());
    }
    catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
  
}
