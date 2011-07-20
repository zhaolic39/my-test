import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class TimeTest{
  
  public static void main(String[] args) throws UnsupportedEncodingException {
    String source_type = "-2,-1,1";
    StringBuffer sbSql = new StringBuffer();
    sbSql.append(" and (");
    String[] sources = source_type.split(",");
    if(Arrays.binarySearch(sources, "-1") > -1){//界面采编
      sbSql.append(" a.source_id = '-1' or");
    }
    if(Arrays.binarySearch(sources, "-2") > -1){//批量导入
      sbSql.append(" a.source_id = '-2' or");
    }
    if(Arrays.binarySearch(sources, "1") > -1){//数据源导入
      sbSql.append(" a.source_id > 0 or");
    }
    sbSql.setLength(sbSql.length() - 3);
    sbSql.append(")");
    System.out.println(sbSql);
  }
  
  /**
   * 将给定的字符串按着给定的截取长度截取
   * <br>
   * 注意一个汉字占2个字节
   * @param str
   * @param subSLength
   * @return 截取后的字符串
   * @throws UnsupportedEncodingException 
   */
  public static String subStr(String str, int subSLength)
          throws UnsupportedEncodingException
  {
      
      if (str == null)
          return null;
      else
      {
          int tempSubLength = subSLength;//截取字节数
          
          String subStr = str.substring(0, subSLength);//截取的子串
          
          int subStrByetsL = subStr.getBytes("GBK").length;//截取子串的字节长度
          
          // 说明截取的字符串中包含有汉字
          while (subStrByetsL > tempSubLength)
          {
              subStr = str.substring(0, --subSLength);
              subStrByetsL = subStr.getBytes("GBK").length;
          }
          return subStr;
      }
      
  }

}
