package spring.handler;

import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * XML请求的解析类
 * <reuqest>
 *   <service_name></service_name>
 *   <content>
 *     <plan_id><plan_id>
 *   </content>
 * </request>
 * @author WizarD
 *
 */
public class XmlHandler extends AbstractHandler {

  @Override
  public void treatRequest(Object request) {
    try {
      SAXReader saxreader = new SAXReader();
      Document document = saxreader.read(new StringReader(request.toString()));
      Element xmlobj = document.getRootElement();
      service_name = xmlobj.element("service_name").getText();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
