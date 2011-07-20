package spring.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
* ����Apache Commons DBUtil��������ݿ������ 
* ����DBCP��Ϊ����Դ������Դ��Spring���Ѿ����ú� 
* �����Ѿ���Spring�����úã�����Ҫ�ĵط���setע��󼴿ɵ��� 
* <code> 
* private DbUtilsTemplate dbUtilsTemplate; 
* public void setDbUtilsTemplate(DbUtilsTemplate dbUtilsTemplate) { 
*     this.dbUtilsTemplate = dbUtilsTemplate; 
* } 
* </code> 
* @author Sunshine 
* @version 1.0 2009-07-29 
*/ 
public class DbUtilsTemplate { 

  private DataSource dataSource; 
  private QueryRunner queryRunner; 
  private static final Log LOG = LogFactory.getLog(DbUtilsTemplate.class); 

  public void setDataSource(DataSource dataSource) { 
      this.dataSource = dataSource; 
  } 

  /** 
   * ִ��sql��� 
   * @param sql sql��� 
   * @return ��Ӱ������� 
   */ 
  public int update(String sql) { 
      return update(sql, null); 
  } 
    
  /** 
   * ִ��sql��� 
   * <code> 
   * executeUpdate("update user set username = 'kitty' where username = ?", "hello kitty"); 
   * </code> 
   * @param sql sql��� 
   * @param param ���� 
   * @return ��Ӱ������� 
   */ 
  public int update(String sql, Object param) { 
      return update(sql, new Object[] { param }); 
  } 
    
  /** 
   * ִ��sql��� 
   * @param sql sql��� 
   * @param params �������� 
   * @return ��Ӱ������� 
   */ 
  public int update(String sql, Object[] params) { 
      queryRunner = new QueryRunner(dataSource); 
      int affectedRows = 0; 
      try { 
          if (params == null) { 
              affectedRows = queryRunner.update(sql); 
          } else { 
              affectedRows = queryRunner.update(sql, params); 
          } 
      } catch (SQLException e) { 
          LOG.error("Error occured while attempting to update data", e); 
      } 
      return affectedRows; 
  } 
    
  /** 
   * ִ������sql��� 
   * @param sql sql��� 
   * @param params ��ά�������� 
   * @return ��Ӱ������������� 
   */ 
  public int[] batchUpdate(String sql, Object[][] params) { 
      queryRunner = new QueryRunner(dataSource); 
      int[] affectedRows = new int[0]; 
      try { 
          affectedRows = queryRunner.batch(sql, params); 
      } catch (SQLException e) { 
          LOG.error("Error occured while attempting to batch update data", e); 
      } 
      return affectedRows; 
  }     

  /** 
   * ִ�в�ѯ����ÿ�еĽ�����浽һ��Map�����У�Ȼ������Map���󱣴浽List�� 
   * @param sql sql��� 
   * @return ��ѯ��� 
   */ 
  public List<Map<String, Object>> find(String sql) { 
      return find(sql, null); 
  } 
    
  /** 
   * ִ�в�ѯ����ÿ�еĽ�����浽һ��Map�����У�Ȼ������Map���󱣴浽List�� 
   * @param sql sql��� 
   * @param param ���� 
   * @return ��ѯ��� 
   */ 
  public List<Map<String, Object>> find(String sql, Object param) { 
      return find(sql, new Object[] {param}); 
  } 
    
  /** 
   * ִ�в�ѯ����ÿ�еĽ�����浽һ��Map�����У�Ȼ������Map���󱣴浽List�� 
   * @param sql sql��� 
   * @param params �������� 
   * @return ��ѯ��� 
   */ 
  @SuppressWarnings("unchecked") 
  public List<Map<String, Object>> find(String sql, Object[] params) { 
      queryRunner = new QueryRunner(dataSource); 
      List<Map<String, Object>> list = new ArrayList<Map<String,Object>>(); 
      try { 
          if (params == null) { 
              list = (List<Map<String, Object>>) queryRunner.query(sql, new MapListHandler()); 
          } else { 
              list = (List<Map<String, Object>>) queryRunner.query(sql, new MapListHandler(), params); 
          } 
      } catch (SQLException e) { 
          LOG.error("Error occured while attempting to query data", e); 
      } 
      return list; 
  } 
    
  /** 
   * ִ�в�ѯ����ÿ�еĽ�����浽Bean�У�Ȼ������Bean���浽List�� 
   * @param entityClass ���� 
   * @param sql sql��� 
   * @return ��ѯ��� 
   */ 
  public <T> List<T> find(Class<T> entityClass, String sql) { 
      return find(entityClass, sql, null); 
  } 
    
  /** 
   * ִ�в�ѯ����ÿ�еĽ�����浽Bean�У�Ȼ������Bean���浽List�� 
   * @param entityClass ���� 
   * @param sql sql��� 
   * @param param ���� 
   * @return ��ѯ��� 
   */ 
  public <T> List<T> find(Class<T> entityClass, String sql, Object param) { 
      return find(entityClass, sql, new Object[] { param }); 
  } 
    
  /** 
   * ִ�в�ѯ����ÿ�еĽ�����浽Bean�У�Ȼ������Bean���浽List�� 
   * @param entityClass ���� 
   * @param sql sql��� 
   * @param params �������� 
   * @return ��ѯ��� 
   */ 
  @SuppressWarnings("unchecked") 
  public <T> List<T> find(Class<T> entityClass, String sql, Object[] params) { 
      queryRunner = new QueryRunner(dataSource); 
      List<T> list = new ArrayList<T>(); 
      try { 
          if (params == null) { 
              list = (List<T>) queryRunner.query(sql, new BeanListHandler(entityClass)); 
          } else { 
              list = (List<T>) queryRunner.query(sql, new BeanListHandler(entityClass), params); 
          } 
      } catch (SQLException e) { 
          LOG.error("Error occured while attempting to query data", e); 
      } 
      return list; 
  } 
    
  /** 
   * ��ѯ��������еĵ�һ����¼������װ�ɶ��� 
   * @param entityClass ���� 
   * @param sql sql��� 
   * @return ���� 
   */ 
  public <T> T findFirst(Class<T> entityClass, String sql) { 
      return findFirst(entityClass, sql, null); 
  } 
    
  /** 
   * ��ѯ��������еĵ�һ����¼������װ�ɶ��� 
   * @param entityClass ���� 
   * @param sql sql��� 
   * @param param ���� 
   * @return ���� 
   */ 
  public <T> T findFirst(Class<T> entityClass, String sql, Object param) { 
      return findFirst(entityClass, sql, new Object[] { param }); 
  } 
    
  /** 
   * ��ѯ��������еĵ�һ����¼������װ�ɶ��� 
   * @param entityClass ���� 
   * @param sql sql��� 
   * @param params �������� 
   * @return ���� 
   */ 
  @SuppressWarnings("unchecked") 
  public <T> T findFirst(Class<T> entityClass, String sql, Object[] params) { 
      queryRunner = new QueryRunner(dataSource); 
      Object object = null; 
      try { 
          if (params == null) { 
              object = queryRunner.query(sql, new BeanHandler(entityClass)); 
          } else { 
              object = queryRunner.query(sql, new BeanHandler(entityClass), params); 
          } 
      } catch (SQLException e) { 
          LOG.error("Error occured while attempting to query data", e); 
      } 
      return (T) object; 
  } 
    
  /** 
   * ��ѯ��������еĵ�һ����¼������װ��Map���� 
   * @param sql sql��� 
   * @return ��װΪMap�Ķ��� 
   */ 
  public Map<String, Object> findFirst(String sql) { 
      return findFirst(sql, null); 
  } 
    
  /** 
   * ��ѯ��������еĵ�һ����¼������װ��Map���� 
   * @param sql sql��� 
   * @param param ���� 
   * @return ��װΪMap�Ķ��� 
   */ 
  public Map<String, Object> findFirst(String sql, Object param) { 
      return findFirst(sql, new Object[] { param }); 
  } 
    
  /** 
   * ��ѯ��������еĵ�һ����¼������װ��Map���� 
   * @param sql sql��� 
   * @param params �������� 
   * @return ��װΪMap�Ķ��� 
   */ 
  @SuppressWarnings("unchecked") 
  public Map<String, Object> findFirst(String sql, Object[] params) { 
      queryRunner = new QueryRunner(dataSource); 
      Map<String, Object> map = null; 
      try { 
          if (params == null) { 
              map = (Map<String, Object>) queryRunner.query(sql, new MapHandler()); 
          } else { 
              map = (Map<String, Object>) queryRunner.query(sql, new MapHandler(), params); 
          } 
      } catch (SQLException e) { 
          LOG.error("Error occured while attempting to query data", e); 
      } 
      return map; 
  } 
    
  /** 
   * ��ѯĳһ����¼������ָ���е�����ת��ΪObject 
   * @param sql sql��� 
   * @param columnName ���� 
   * @return ������� 
   */ 
  public Object findBy(String sql, String columnName) { 
      return findBy(sql, columnName, null); 
  } 
    
  /** 
   * ��ѯĳһ����¼������ָ���е�����ת��ΪObject 
   * @param sql sql��� 
   * @param columnName ���� 
   * @param param ���� 
   * @return ������� 
   */ 
  public Object findBy(String sql, String columnName, Object param) { 
      return findBy(sql, columnName, new Object[] { param }); 
  } 
    
  /** 
   * ��ѯĳһ����¼������ָ���е�����ת��ΪObject 
   * @param sql sql��� 
   * @param columnName ���� 
   * @param params �������� 
   * @return ������� 
   */ 
  public Object findBy(String sql, String columnName, Object[] params) { 
      queryRunner = new QueryRunner(dataSource); 
      Object object = null; 
      try { 
          if (params == null) { 
              object = queryRunner.query(sql, new ScalarHandler(columnName)); 
          } else { 
              object = queryRunner.query(sql, new ScalarHandler(columnName), params); 
          } 
      } catch (SQLException e) { 
          LOG.error("Error occured while attempting to query data", e); 
      } 
      return object; 
  } 
    
  /** 
   * ��ѯĳһ����¼������ָ���е�����ת��ΪObject 
   * @param sql sql��� 
   * @param columnIndex ������ 
   * @return ������� 
   */ 
  public Object findBy(String sql, int columnIndex) { 
      return findBy(sql, columnIndex, null); 
  } 
    
  /** 
   * ��ѯĳһ����¼������ָ���е�����ת��ΪObject 
   * @param sql sql��� 
   * @param columnIndex ������ 
   * @param param ���� 
   * @return ������� 
   */ 
  public Object findBy(String sql, int columnIndex, Object param) { 
      return findBy(sql, columnIndex, new Object[] { param }); 
  } 
    
  /** 
   * ��ѯĳһ����¼������ָ���е�����ת��ΪObject 
   * @param sql sql��� 
   * @param columnIndex ������ 
   * @param params �������� 
   * @return ������� 
   */ 
  public Object findBy(String sql, int columnIndex, Object[] params) { 
      queryRunner = new QueryRunner(dataSource); 
      Object object = null; 
      try { 
          if (params == null) { 
              object = queryRunner.query(sql, new ScalarHandler(columnIndex)); 
          } else { 
              object = queryRunner.query(sql, new ScalarHandler(columnIndex), params); 
          } 
      } catch (SQLException e) { 
          LOG.error("Error occured while attempting to query data", e); 
      } 
      return object; 
  } 
}