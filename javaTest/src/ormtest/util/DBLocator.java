package ormtest.util;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 * ����Դ��λ, �ṩ getConnection() ����
 *
 * @author Jason
 * @version 1.0
 * @version 1.1 �ڶ�����ģʽ�£�����ʹ��c3p0���ӳصķ�ʽ��  by zhaoli 2010-8-2
 */
public class DBLocator {
    /** Logger */
    private static Logger logger = Logger.getLogger(DBLocator.class);

    /** �����ļ���*/
    private static final String CONFIG_XML = "db-locator.xml";

    /** ���� */
    private static DBLocator instance;

    /** ����Դ���� */
    private String dataSourceName;

    /** ����Դ */
    private DataSource dataSource;

    /** �������������� */
    private String driver;

    /** ������ URL */
    private String url;

    /** �������û��� */
    private String user;

    /** ���������� */
    private String pwd;
    
    /** �Ƿ�ʹ�����ӳ� */
    private String pooluse;
    
    /** ���ӳ���С�� */
    private int minpoolsize;
    
    /** ���ӳ������ */
    private int maxpoolsize;
    
    /** ��ʼ�������� */
    private int initpoolsize;

    private DBLocator() {
    }

    public static DBLocator getInstance() {
        if (instance == null) {
            init();
        }
        return instance;
    }

    private static synchronized void init() {
        if (instance != null)
            return;

        instance = new DBLocator();

        InputStream in = null;
        try {
            //in = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_XML);
            File file = new File("./config/" + CONFIG_XML);
            in = new FileInputStream(file);
            
            Document doc = new SAXBuilder().build(in);
            Element root = doc.getRootElement();

            Element ds = root.getChild("data-source");
            if (ds != null) {
                instance.dataSourceName = ds.getChildText("name");
            }

            Element sl = root.getChild("short-link");
            if (sl != null) {
                instance.driver = sl.getChildText("driver");
                instance.url = sl.getChildText("url");
                instance.user = sl.getChildText("user");
                instance.pwd = sl.getChildText("pwd");
                if (!StringUtils.isEmpty(instance.driver)) {
//                    Driver driver = (Driver) Class.forName(instance.driver).newInstance();
//                    DriverManager.registerDriver(driver);
                    Class.forName(instance.driver);
                }
                
                //���ӳ�����
                Element pool = sl.getChild("ds-pool");
                if(pool != null){
                  instance.pooluse = pool.getChildText("pooluse");
                  instance.minpoolsize = NumberUtils.toInt(pool.getChildText("minsize"), 1);
                  instance.maxpoolsize = NumberUtils.toInt(pool.getChildText("maxsize"), 5);
                  instance.initpoolsize = NumberUtils.toInt(pool.getChildText("initsize"), 3);
                }
            }
        }
        catch (Exception ex) {
            instance = null;
            logger.error(ex);
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException ex) {
                    logger.error(ex);
                }
            }
        }
    }

    public Connection getConnection() throws SQLException {
        // ����Դ�ѳ�ʼ��, ������Դȡ����
        if (dataSource != null) {
            return dataSource.getConnection();
        }
        //����Դ�������ڣ���ʼ������Դ
        initDataSource();
        return dataSource.getConnection();
    }
    
    /**
     * �����Ѵ���������Դ
     * @return
     */
    public DataSource getDataSource() throws SQLException{
      if(dataSource != null){
        return dataSource;
      }
      //����Դ�������ڣ���ʼ������Դ
      initDataSource();
      return dataSource;
    }

    /**
     * ��ʼ������Դ
     * @throws SQLException
     */
    private void initDataSource() throws SQLException{
      // ����Դδ��ʼ��������Դ����Ϊ��, ��ʼ������Դ�ٴ�����Դȡ����
      if (dataSource == null && !StringUtils.isEmpty(dataSourceName)) {
          dataSource = initDsJndi();
      }
      //ʹ�����ӳصĶ���������Դ
      else if(dataSource == null && "true".equals(pooluse)){ 
          dataSource = initDsPool();
      }
      //���ʹ�������ӳص�datasource
      else{
          dataSource = DataSources.unpooledDataSource(url, user, pwd);
      }
      
      if(dataSource == null){
        throw new SQLException("data source initializtion failed!");
      }
    }
    
    /**
     * ��ʼ��jndi����Դ
     */
    private DataSource initDsJndi() {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
//            dataSource = (DataSource) ctx.lookup(dataSourceName);
            return (DataSource) ctx.lookup(dataSourceName);
        }
        catch (Exception ex) {
            logger.error(ex);
            return null;
        }
        finally {
            if (ctx != null) {
                try {
                    ctx.close();
                }
                catch (Exception ex) {
                    logger.error(ex);
                }
            }
        }
    }

    /**
     * ��ʼ��c3p0���ӳ�
     */
    private DataSource initDsPool() {
      try {
        logger.info("c3p0 init datasource...");
        ComboPooledDataSource cpds = new ComboPooledDataSource();    
        cpds.setDriverClass(driver);   
        cpds.setJdbcUrl(url);    
        cpds.setUser(user);    
        cpds.setPassword(pwd);    
        cpds.setMinPoolSize(minpoolsize);
        cpds.setInitialPoolSize(initpoolsize);
        cpds.setMaxStatements(0);    
        cpds.setMaxPoolSize(maxpoolsize);
        cpds.setAcquireIncrement(1);
        cpds.setAutoCommitOnClose(true);
        
        return cpds;
      }
      catch (PropertyVetoException e) {
        e.printStackTrace();
        return null;
      }
      
  }
    
    public String toString() {
        return getClass().getName() + "[dataSourceName=" + dataSourceName + ",driver=" + driver
                + ",url=" + url + ",user=" + user + ",pwd=" + pwd + "]";
    }

    public static void main(String[] args) {
      System.out.println(getInstance().driver);
    }
}
