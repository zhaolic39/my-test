package spring.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.annotation.Service;

/**
 * <p>Title: ������ܿ���</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Oct 11, 2010
 */
public class ServerContext {
  private Logger logger = Logger.getLogger(ServerContext.class);
  
  private static ServerContext instance = new ServerContext();
  
  private Map<String, ServiceInfo> serInfoMap = new HashMap<String, ServiceInfo>();
  
  public AbstractRefreshableApplicationContext context = null;
  
  public static ServerContext getInstance(){
    return instance;
  }
  
  private ServerContext(){
    initServer();
  }

  /**
   * ��ʼ����1.ע��spring��bean, 2.��ȡ���񷽷�
   */
  private void initServer() {
    context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
//    context = (AbstractRefreshableApplicationContext)context;
    
    logger.info("��ʼע�����....");
    String[] beanNames = context.getBeanDefinitionNames();
    for(String name:beanNames){
      Object o = context.getBean(name);
      Method[] methods = o.getClass().getMethods();
//      Method[] methods = context.getType(name).getMethods();
//      System.out.println(Arrays.toString(methods));
      for(Method m:methods){
        Annotation[] annos = m.getAnnotations();
        for(Annotation a:annos){
//          System.out.println(a.annotationType().getName());
          if(a.annotationType().getName().equals(Service.class.getName())){ //ע��Ϊ����ķ���
            ServiceInfo serviceInfo = new ServiceInfo(m, o);
            String service_name = ((Service)a).serviceName();
            serInfoMap.put(service_name, serviceInfo);
            logger.info("[service_name:"+service_name+", "+o+"]");
            break;
          }
        }
      }
    }
    logger.info("ע��������!");
  }
  
  /**
   * ���÷���
   * @param serviceName
   * @return
   */
  public Object invokeService(String serviceName, Object param){
    ServiceInfo si = serInfoMap.get(serviceName);
    Object response = null;
    try {
      response = si.getService().invoke(si.getServer(), param);
    }
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    
    return response;
  }
  
  
}
