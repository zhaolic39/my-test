package xml.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class XmlPostTest {
  public static void main(String[] args) {
    String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?><operation_in>   <sysfunc_id>10105</sysfunc_id>    <process_code>ob_query_dept</process_code>    <content>     <dept_name>福州</dept_name>   </content> </operation_in>";
    String url = "http://10.1.0.142:16301/workflow/workflow_api.jsp";
    String res = sendGetXml(url, "sendmsg", xml, "GBK");
    System.out.println(XmlUtil.strChangeToXML(res));
  }
  

  public static String sendGetXml(String strUrl, String paramName, String strXml, String charSet) {
    StringBuffer smsRes = new StringBuffer();
    try {
        if(strUrl.indexOf("?") < 0) strUrl += "?";
        
        StringBuffer params = new StringBuffer();
        params.append(paramName);
        params.append("=");
        params.append(URLEncoder.encode(strXml, charSet));
        System.out.println("paramName:" + params);
        strUrl += params;
        
        URL url = new URL(strUrl);
        HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
        urlcon.setDoInput(true);
//        urlcon.setDoOutput(true);
        urlcon.setRequestProperty("Content-Type", "text/xml; charset=" + charSet);
//        urlcon.setRequestProperty("method", "post"); //设置提交方式为POST
        urlcon.setRequestMethod("GET");
        urlcon.setReadTimeout(5000);//（单位[dan wei]：毫秒）jdk 1.5换成这个

//        byte[] b = params.toString().getBytes();
//        OutputStream out = urlcon.getOutputStream();
//        out.write(b, 0, b.length);
//        out.flush();
//        out.close();

        InputStream ins = urlcon.getInputStream();

        while (true) {
            int all = ins.available();
            byte[] bContent = new byte[all];
            int iReadNum = ins.read(bContent);
            if (iReadNum == -1) {
                break;
            }
            smsRes.append(new String(bContent, 0, iReadNum, charSet));
        }
        ins.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
//    System.out.println(smsRes);
    return smsRes.toString();
  }
  
  public static String sendPoseXml(String strUrl, String paramName, String strXml, String charSet) {
    StringBuffer smsRes = new StringBuffer();
    try {
        URL url = new URL(strUrl);
        HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
        urlcon.setDoInput(true);
        urlcon.setDoOutput(true);
        urlcon.setRequestProperty("Content-Type", "text/xml; charset=" + charSet);
//        urlcon.setRequestProperty("method", "post"); //设置提交方式为POST
        urlcon.setRequestMethod("POST");
        urlcon.setReadTimeout(5000);//（单位[dan wei]：毫秒）jdk 1.5换成这个

        StringBuffer params = new StringBuffer();
        params.append(paramName);
        params.append("=");
        params.append(URLEncoder.encode(strXml, charSet));
        System.out.println("paramName:" + params);

        byte[] b = params.toString().getBytes();
        OutputStream out = urlcon.getOutputStream();
        out.write(b, 0, b.length);
        out.flush();
        out.close();

        InputStream ins = urlcon.getInputStream();

        while (true) {
            int all = ins.available();
            byte[] bContent = new byte[all];
            int iReadNum = ins.read(bContent);
            if (iReadNum == -1) {
                break;
            }
            smsRes.append(new String(bContent, 0, iReadNum, charSet));
        }
        ins.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
//    System.out.println(smsRes);
    return smsRes.toString();
  }
  
  public static String sendXml(){

    StringBuffer smsRes = new StringBuffer();
    String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?><operation_in>  <verify_code>619376739843651819122433</verify_code>  <sysfunc_id>10105</sysfunc_id>  <process_code>ob_query_dept</process_code>  <accept_info><accept_city>593</accept_city><accept_org_id>3010000</accept_org_id><accept_province>5910</accept_province></accept_info><request_source>201028</request_source><request_time>20100913160346</request_time><operator_id>3014015</operator_id>  <content><dept_id></dept_id><dept_name>福州</dept_name>   </content></operation_in>";
    
    xml = "<?xml version=\"1.0\" encoding=\"GBK\"?><operation_in>  <verify_code>619376739843651819122433</verify_code>  <sysfunc_id>10103</sysfunc_id>  <process_code>ob_update_project_task</process_code>  <accept_info>    <accept_city>593</accept_city>   <accept_org_id>3010000</accept_org_id>   <accept_province>5910</accept_province> </accept_info> <request_source>201028</request_source> <request_time>20100913160346</request_time> <operator_id>3014015</operator_id> <content>   <project_id>P201010202001</project_id>   <task_id>110</task_id>    <task_area>592</task_area>    <task_name>测试名称</task_name>    <task_type>SELL</task_type>    <begin_time>20101010000000</begin_time>    <end_time>20101231000000</end_time>   <exe_unit>unit111</exe_unit>    <task_staff>staff2010</task_staff>    <busi_name>业务名称222</busi_name>    <caller_nbr>10086</caller_nbr>    <exe_num>30</exe_num>    <exe_succ_num>20</exe_succ_num>    <succ_tag>成功标志</succ_tag>    <task_offer>优惠政策2222</task_offer>  <boss_code>bosscode2222</boss_code>   <remark>备注信息。。。。</remark>   <create_staff>create111</create_staff>    <attachment>      <row>        <attachment_name>附件名称333</attachment_name>        <attachment_file_name>附件文件名称333</attachment_file_name>      </row>    </attachment>  </content></operation_in>";
    try {
      URL url = new URL("http://10.1.0.142:16301/workflow/workflow_api.jsp");
      URLConnection urlcon = url.openConnection();
      urlcon.setDoInput(true);
      urlcon.setDoOutput(true);
      urlcon.setRequestProperty("Content-Type", "text/xml; charset=gbk");
      //urlcon.setRequestProperty("method", "post"); //设置提交方式为POST
  
      OutputStream out = urlcon.getOutputStream();
      out.write(xml.getBytes());
      out.flush();
      out.close();
  
      InputStream ins = urlcon.getInputStream();
      byte[] bContent = new byte[2048];
      while (true) {
        int iReadNum = ins.read(bContent);
        if (iReadNum == -1) {
          break;
        }
        smsRes.append(new String(bContent, 0, iReadNum));
      }
      ins.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    System.out.println(smsRes);
  
    return "";
  }
}
