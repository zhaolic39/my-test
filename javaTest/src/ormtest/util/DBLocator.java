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
 * 数据源定位, 提供 getConnection() 方法
 *
 * @author Jason
 * @version 1.0
 * @version 1.1 在短连接模式下，增加使用c3p0连接池的方式。  by zhaoli 2010-8-2
 */
public class DBLocator {
    /** Logger */
    private static Logger logger = Logger.getLogger(DBLocator.class);

    /** 配置文件名*/
    private static final String CONFIG_XML = "db-locator.xml";

    /** 单例 */
    private static DBLocator instance;

    /** 数据源名称 */
    private String dataSourceName;

    /** 数据源 */
    private DataSource dataSource;

    /** 短连接驱动程序 */
    private String driver;

    /** 短连接 URL */
    private String url;

    /** 短连接用户名 */
    private String user;

    /** 短连接密码 */
    private String pwd;
    
    /** 是否使用连接池 */
    private String pooluse;
    
    /** 连接池最小数 */
    private int minpoolsize;
    
    /** 连接池最大数 */
    private int maxpoolsize;
    
    /** 初始化连接数 */
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
                
                //连接池配置
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
        // 数据源已初始化, 从数据源取连接
        if (dataSource != null) {
            return dataSource.getConnection();
        }
        //数据源还不存在，初始化数据源
        initDataSource();
        return dataSource.getConnection();
    }
    
    /**
     * 返回已创建的数据源
     * @return
     */
    public DataSource getDataSource() throws SQLException{
      if(dataSource != null){
        return dataSource;
      }
      //数据源还不存在，初始化数据源
      initDataSource();
      return dataSource;
    }

    /**
     * 初始化数据源
     * @throws SQLException
     */
    private void initDataSource() throws SQLException{
      // 数据源未初始化且数据源名不为空, 初始化数据源再从数据源取连接
      if (dataSource == null && !StringUtils.isEmpty(dataSourceName)) {
          dataSource = initDsJndi();
      }
      //使用连接池的短连接数据源
      else if(dataSource == null && "true".equals(pooluse)){ 
          dataSource = initDsPool();
      }
      //最后使用无连接池的datasource
      else{
          dataSource = DataSources.unpooledDataSource(url, user, pwd);
      }
      
      if(dataSource == null){
        throw new SQLException("data source initializtion failed!");
      }
    }
    
    /**
     * 初始化jndi数据源
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
     * 初始化c3p0连接池
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
