package spring.handler;

import spring.util.ServerContext;

/**
 * 外部请求处理抽象类
 * @author WizarD
 *
 */
public abstract class AbstractHandler {
  
  String service_name = "";
  
  Object request_param = null;
  
  /**
   * 根据请求格式获得处理方式
   * @param format
   * @return
   */
  public static AbstractHandler getHandler(String format) {
    AbstractHandler handler = null;
    if("XML".equals(format)) {
      handler = new XmlHandler();
    }
    else {
    
    }
    return handler;
  }
  
  /**
   * 调用请求
   * @return
   */
  public Object invokeService(Object request) {
    treatRequest(request);
    Object response = ServerContext.getInstance().invokeService(service_name, request_param);
    return response;
  }
  
  /**
   * 解析外部请求的方法，将service_name与request_param参数从请求中获得
   * @param request
   */
  public abstract void treatRequest(Object request);
  
  public String toString() {
    return "[service_name:"+service_name+", request_param:"+request_param+"]";
  }
}
