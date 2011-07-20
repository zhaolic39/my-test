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
      System.out.println("��������� :"+workBook.getNumberOfSheets()+"<br>");
      for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
        System.out.println("<font color='red'> "+i+" ***************���������ƣ�"+workBook.getSheetName(i)+"  ************</font><br>");
      
        HSSFSheet sheet = workBook.getSheetAt(i);
        int rows = sheet.getPhysicalNumberOfRows(); // �������
        if (rows > 0) {
            sheet.getMargin(HSSFSheet.TopMargin);
            for (int j = 0; j < rows; j++) { // ��ѭ��
                HSSFRow row = sheet.getRow(j);
                if (row != null) {
                    int cells = row.getLastCellNum();//�������
                    for (int k = 0; k < cells; k++) { // ��ѭ��
                        HSSFCell cell = row.getCell(k);
                        // /////////////////////
                        if (cell != null) {
                            String value = "";
                            switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_NUMERIC: // ��ֵ��
                                 if (HSSFDateUtil.isCellDateFormatted(
                                 cell)) {
                                 //�����date������ ����ȡ��cell��dateֵ
                                 value = HSSFDateUtil.getJavaDate(
                                 cell.getNumericCellValue()).
                                 toString();
                                 System.out.println("��"+j+"��,��"+k+"��ֵ��"+value+"<br>");
                                 }else{//������
                                 
                                value = String.valueOf(cell
                                        .getNumericCellValue());
                                System.out.println("��"+j+"��,��"+k+"��ֵ��"+value+"<br>");
                                 }
                                break;
                            /* ���б�ʾ��Ԫ�������Ϊstring���� */
                            case HSSFCell.CELL_TYPE_STRING: // �ַ�����
                                value = cell.getRichStringCellValue()
                                        .toString();
                                System.out.println("��"+j+"��,��"+k+"��ֵ��"+value+"<br>");
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA://��ʽ��
                                //����ʽ����ֵ
                                 value = String.valueOf(cell.getNumericCellValue());
                                 if(value.equals("NaN")){//�����ȡ������ֵΪ�Ƿ�ֵ,��ת��Ϊ��ȡ�ַ���
                                     
                                     value = cell.getRichStringCellValue().toString();
                                 }
                                 //cell.getCellFormula();����ʽ
                                 System.out.println("��"+j+"��,��"+k+"��ֵ��"+value+"<br>");
                            break;
                            case HSSFCell.CELL_TYPE_BOOLEAN://����
                                 value = " "
                                 + cell.getBooleanCellValue();
                                 System.out.println("��"+j+"��,��"+k+"��ֵ��"+value+"<br>");
                             break;
                            /* ���б�ʾ�õ�Ԫ��ֵΪ�� */
                            case HSSFCell.CELL_TYPE_BLANK: // ��ֵ
                                value = "";
                                System.out.println("��"+j+"��,��"+k+"��ֵ��"+value+"<br>");
                                break;
                            case HSSFCell.CELL_TYPE_ERROR: // ����
                                value = "";
                                System.out.println("��"+j+"��,��"+k+"��ֵ��"+value+"<br>");
                                break;
                            default:
                                value = cell.getRichStringCellValue().toString();
                            System.out.println("��"+j+"��,��"+k+"��ֵ��"+value+"<br>");
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
