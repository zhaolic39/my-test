import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class TimeTest{
  
  public static void main(String[] args) throws UnsupportedEncodingException {
    String source_type = "-2,-1,1";
    StringBuffer sbSql = new StringBuffer();
    sbSql.append(" and (");
    String[] sources = source_type.split(",");
    if(Arrays.binarySearch(sources, "-1") > -1){//����ɱ�
      sbSql.append(" a.source_id = '-1' or");
    }
    if(Arrays.binarySearch(sources, "-2") > -1){//��������
      sbSql.append(" a.source_id = '-2' or");
    }
    if(Arrays.binarySearch(sources, "1") > -1){//����Դ����
      sbSql.append(" a.source_id > 0 or");
    }
    sbSql.setLength(sbSql.length() - 3);
    sbSql.append(")");
    System.out.println(sbSql);
  }
  
  /**
   * ���������ַ������Ÿ����Ľ�ȡ���Ƚ�ȡ
   * <br>
   * ע��һ������ռ2���ֽ�
   * @param str
   * @param subSLength
   * @return ��ȡ����ַ���
   * @throws UnsupportedEncodingException 
   */
  public static String subStr(String str, int subSLength)
          throws UnsupportedEncodingException
  {
      
      if (str == null)
          return null;
      else
      {
          int tempSubLength = subSLength;//��ȡ�ֽ���
          
          String subStr = str.substring(0, subSLength);//��ȡ���Ӵ�
          
          int subStrByetsL = subStr.getBytes("GBK").length;//��ȡ�Ӵ����ֽڳ���
          
          // ˵����ȡ���ַ����а����к���
          while (subStrByetsL > tempSubLength)
          {
              subStr = str.substring(0, --subSLength);
              subStrByetsL = subStr.getBytes("GBK").length;
          }
          return subStr;
      }
      
  }

}
