package spring.util;

import java.lang.reflect.Method;

/**
 * <p>Title: 服务信息类</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Oct 11, 2010
 */
public class ServiceInfo {
  
  public ServiceInfo(Method service, Object server){
    this.service = service;
    this.server = server;
  }
  
  private Method service;
  
  private Object server;

  public Method getService() {
    return service;
  }

  public void setService(Method service) {
    this.service = service;
  }

  public Object getServer() {
    return server;
  }

  public void setServer(Object server) {
    this.server = server;
  }
  
  public String toString() {
    return "[service:"+service+", server:"+server+"]";
  }
}
