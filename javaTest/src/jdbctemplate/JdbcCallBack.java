package jdbctemplate;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>Title: �������ݿ�Ļص������ӿ���</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Jul 20, 2010
 */
public interface JdbcCallBack <T> {
  
  /**
   * ��ѯ�����ʵ�ַ���
   * @param rs
   * @return
   * @throws SQLException 
   */
  public T doInStatement(Statement stat) throws SQLException;
}
