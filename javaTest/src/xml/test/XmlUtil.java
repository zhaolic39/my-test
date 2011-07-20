package xml.test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import xml.po.Content;
import xml.po.Plan;
import xml.po.Request;


/**
 * xml ת��������
 * @author WizarD
 *
 */
public class XmlUtil {
  public static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
  
  @SuppressWarnings("unchecked")
  private static Class[]  array_clz = {
                                  Integer.class, Float.class, Long.class, 
                                  Integer.TYPE, Float.TYPE, Long.TYPE,
                                  String.class, StringBuffer.class
                                };
  
  public static void main(String[] args) {
//    System.out.println(strChangeToXML("<?xml version=\"1.0\" encoding=\"GBK\"?><operation_out><request_type>10103</request_type><sysfunc_id>10103</sysfunc_id><process_code>ob_set_project_task_sts</process_code><request_seq>20101020113211</request_seq><response_time>20101020113211</response_time><response_seq>20101020113211</response_seq><request_source>201028</request_source><accept_id>20101020113211</accept_id><response><resp_result>0</resp_result><resp_type>0</resp_type><resp_code>0</resp_code><resp_desc></resp_desc></response><content></content></operation_out>"));
    
    Plan[] plans = new Plan[2];
    
    Plan p = new Plan();
    p.setPlan_id(10);
    p.setPlan_name("plan name");
    p.setCreate_time(new Date());
    
    plans[0] = p;
    p = new Plan();
    p.setPlan_id(11);
    p.setPlan_name("plan name11");
    p.setCreate_time(new Date());
    plans[1] = p;
    
    Content con = new Content();
    con.setPlan(plans);
    
    Request req = new Request();
    req.setContent(con);
    
    System.out.println(obj2xml(req));
    
    String xml = "<content><plan><plan_id>10</plan_id><plan_name>plan name</plan_name><create_time>20110302143340</create_time></plan><plan><plan_id>11</plan_id><plan_name>plan name11</plan_name><create_time>20110302143340</create_time></plan></content>";
    
    
    con = xml2obj(xml, Content.class);
    System.out.println(con.toString());
  }
  
  /** 
   * ������xml���ַ���������������������ַ����������xml�ļ��е�Ч��һ�� 
   * @param str 
   * @return 
   */  
  public static String strChangeToXML(String str) {  
     org.dom4j.Document document = null;  
     try {  
         document = DocumentHelper.parseText(str);  
     } catch (DocumentException documentexception) {  
         documentexception.printStackTrace();  
     }  
     OutputFormat outputformat = OutputFormat.createPrettyPrint();  
     // �������ڿ���xml�����ͷ��Ϣ(�磺<?xml version="1.0" encoding="UTF-8"?>)��true ��ʾ������� false ��ʾ���  
     outputformat.setSuppressDeclaration(true);  
     StringWriter stringwriter = new StringWriter();  
     XMLWriter xmlwriter = new XMLWriter(stringwriter, outputformat);   
     try {  
         xmlwriter.write(document);  
     } catch (IOException e) {  
         e.printStackTrace();  
     }  
     return stringwriter.toString().trim();  
  }  
  
  /**
   * ��������ϢתΪxml�ַ���
   * @param o
   * @return
   */
  public static String obj2xml(Object o) {
    StringBuffer cont = new StringBuffer("");
    obj2xml(cont, o);
    return cont.toString();
  }
  
  private static void obj2xml(StringBuffer cont, Object o) {
    if(o == null) return;
    
    Field[] fields = o.getClass().getDeclaredFields();
    
    for(Field f:fields) {
      String filed_name = f.getName();
      try {
        Class clz = PropertyUtils.getPropertyType(o, filed_name);
        Object field_value = PropertyUtils.getProperty(o, filed_name);
        String value = "";
        if(clz.isArray()) { //��������
          Object[] array = (Object[])field_value;
          for(Object arr:array) {
            cont.append("<"+filed_name+">");
            obj2xml(cont, arr);
            cont.append("</"+filed_name+">");
          }
        }
        else {  //��ͨ����
          cont.append("<"+filed_name+">");
          if(isBaseClz(clz)) {
            value = field_value.toString();
          }
          else if(clz == Date.class){ //ʱ�����͵Ĵ���
            value = DateFormatUtils.format((Date)field_value, DEFAULT_DATE_FORMAT);
          }
          else {  //�������͵Ķ��󣬵ݹ�ѭ��
            obj2xml(cont, field_value);
            value = "";
          }
          cont.append(value).append("</"+filed_name+">");
        }

      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  /**
   * �ж��Ƿ�Ϊ��������
   * @param c
   * @return
   */
  private static boolean isBaseClz(Class c) {
    for(Class clz:array_clz) {
      if(clz == c) return true;
    }
    return false;
  }
  
  /**
   * ��xmlתΪָ���������
   * @param <T>
   * @param xml
   * @param type
   * @return
   * @throws Exception 
   */
  public static <T> T xml2obj(String xml, Class<T> type){
    try {
      SAXReader saxreader = new SAXReader();
      Document document = saxreader.read(new StringReader(xml));
      
      T o = type.newInstance();
      xml2obj(document.getRootElement(), o);
      return o;
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  private static void xml2obj(Element content, Object o) {
    Field[] pros = o.getClass().getDeclaredFields(); //�����е�����
    if(pros == null) return;
    
    for(Field f:pros) {
      String field_name = f.getName();  //��������
      try {
        Class clz = PropertyUtils.getPropertyType(o, field_name); //���Ե�����
        
        if(clz.isArray()) { //��������
          List<Element> eles = content.elements(field_name);
          if(eles != null) {
            Object[] objs = (Object[])Array.newInstance(clz.getComponentType(), eles.size());
            
            for(int i=0;i<eles.size();i++) {
              Object o1 = clz.getComponentType().newInstance();
              xml2obj(eles.get(i), o1);
              objs[i] = o1;
            }
            PropertyUtils.setProperty(o, field_name, objs);
          }
        }
        else {  //��ͨ����
          Element ele = content.element(field_name);
          Object value = null;
          if(clz == String.class) {
            value = ele.getText();
          }
          else if(clz == Integer.TYPE) {
            value = NumberUtils.toInt(ele.getText(), 0);
          }
          else if(clz == Date.class) { //��������
            value = DateUtils.parseDate(ele.getText(),new String[] {DEFAULT_DATE_FORMAT});
          }
          else {  //��������
            value = clz.newInstance();
            xml2obj(ele, value);
          }
          PropertyUtils.setProperty(o, field_name, value);
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
