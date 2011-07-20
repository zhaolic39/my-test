package spring.handler;

import spring.util.ServerContext;

/**
 * �ⲿ�����������
 * @author WizarD
 *
 */
public abstract class AbstractHandler {
  
  String service_name = "";
  
  Object request_param = null;
  
  /**
   * ���������ʽ��ô���ʽ
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
   * ��������
   * @return
   */
  public Object invokeService(Object request) {
    treatRequest(request);
    Object response = ServerContext.getInstance().invokeService(service_name, request_param);
    return response;
  }
  
  /**
   * �����ⲿ����ķ�������service_name��request_param�����������л��
   * @param request
   */
  public abstract void treatRequest(Object request);
  
  public String toString() {
    return "[service_name:"+service_name+", request_param:"+request_param+"]";
  }
}
