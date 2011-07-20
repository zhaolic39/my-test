package ormtest.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

/**
 * <p>Title: DB����������</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Sep 10, 2010
 */
public class DBSql {
  
  /**
   * ʹ��Ĭ������Դ������Դ������
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
