package poi.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class PoiTest {
  public static void main(String[] args) {
    try {
      File f = new File("c:\\planTaskTest.xls");
      InputStream in = new ByteArrayInputStream("qeqe".getBytes());
      POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\planTaskTest.xls"));
      HSSFWorkbook workBook = new HSSFWorkbook(fs);
      System.out.println("工作表个数 :"+workBook.getNumberOfSheets()+"<br>");
      for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
        System.out.println("<font color='red'> "+i+" ***************工作表名称："+workBook.getSheetName(i)+"  ************</font><br>");
      
        HSSFSheet sheet = workBook.getSheetAt(i);
        int rows = sheet.getPhysicalNumberOfRows(); // 获得行数
        if (rows > 0) {
            sheet.getMargin(HSSFSheet.TopMargin);
            for (int j = 0; j < rows; j++) { // 行循环
                HSSFRow row = sheet.getRow(j);
                if (row != null) {
                    int cells = row.getLastCellNum();//获得列数
                    for (int k = 0; k < cells; k++) { // 列循环
                        HSSFCell cell = row.getCell(k);
                        // /////////////////////
                        if (cell != null) {
                            String value = "";
                            switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
                                 if (HSSFDateUtil.isCellDateFormatted(
                                 cell)) {
                                 //如果是date类型则 ，获取该cell的date值
                                 value = HSSFDateUtil.getJavaDate(
                                 cell.getNumericCellValue()).
                                 toString();
                                 System.out.println("第"+j+"行,第"+k+"列值："+value+"<br>");
                                 }else{//纯数字
                                 
                                value = String.valueOf(cell
                                        .getNumericCellValue());
                                System.out.println("第"+j+"行,第"+k+"列值："+value+"<br>");
                                 }
                                break;
                            /* 此行表示单元格的内容为string类型 */
                            case HSSFCell.CELL_TYPE_STRING: // 字符串型
                                value = cell.getRichStringCellValue()
                                        .toString();
                                System.out.println("第"+j+"行,第"+k+"列值："+value+"<br>");
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA://公式型
                                //读公式计算值
                                 value = String.valueOf(cell.getNumericCellValue());
                                 if(value.equals("NaN")){//如果获取的数据值为非法值,则转换为获取字符串
                                     
                                     value = cell.getRichStringCellValue().toString();
                                 }
                                 //cell.getCellFormula();读公式
                                 System.out.println("第"+j+"行,第"+k+"列值："+value+"<br>");
                            break;
                            case HSSFCell.CELL_TYPE_BOOLEAN://布尔
                                 value = " "
                                 + cell.getBooleanCellValue();
                                 System.out.println("第"+j+"行,第"+k+"列值："+value+"<br>");
                             break;
                            /* 此行表示该单元格值为空 */
                            case HSSFCell.CELL_TYPE_BLANK: // 空值
                                value = "";
                                System.out.println("第"+j+"行,第"+k+"列值："+value+"<br>");
                                break;
                            case HSSFCell.CELL_TYPE_ERROR: // 故障
                                value = "";
                                System.out.println("第"+j+"行,第"+k+"列值："+value+"<br>");
                                break;
                            default:
                                value = cell.getRichStringCellValue().toString();
                            System.out.println("第"+j+"行,第"+k+"列值："+value+"<br>");
                            }
                        }
                    }
                }
            }
        }
      }
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}
