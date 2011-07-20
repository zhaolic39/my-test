import java.util.Hashtable;
import java.util.regex.Pattern;


public class SortTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    String a = "地你的1234";
    System.out.println(a.getBytes().length);
    
    Hashtable ht = new Hashtable();
    ht.put(null, "123");
    System.out.println(1);
  }
  
  
  
  //精读校验
  private static boolean isDecimal(String sText, int intLength, int intDecimal){
    String temp_sText = sText;
    if(sText.indexOf("-") == 0){
      temp_sText = sText.substring(1);
    }

    String[] temp_data = temp_sText.split("\\.");
    
    int intIntegerLength = intLength - intDecimal;
    
    if(temp_data.length == 0 || temp_data.length > 2){
      System.out.println(1);
      return false;
    }

    if(!CheckAllInt(temp_data[0])){
      System.out.println(2);
      return false;
    }
    
    if(temp_data[0].length() > intIntegerLength){   
      System.out.println(3);
      return false;
    }
    
    if(temp_data.length > 1){
      if(temp_data[1].length() > intDecimal){   
        System.out.println(3);
        return false;
      }
    }
    
    return true;
  }
  
//检查是否为整数(包括负整数)
  private static boolean CheckAllInt(String strTemp){  
    Pattern pattern = Pattern.compile("[0-9]*");
    return pattern.matcher(strTemp).matches();
  }

}
