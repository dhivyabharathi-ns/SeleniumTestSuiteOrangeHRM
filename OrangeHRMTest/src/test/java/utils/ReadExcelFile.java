package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadExcelFile {

	/*public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		ReadExcelFile obj=new ReadExcelFile();	
		String testdata[][]=obj.getData("login");
		
		for(String[] row:testdata)
		{
			
		System.out.println(Arrays.toString(row));
	
	}
	}*/
	
	@DataProvider(name="testdata")
	public String[][] getData(Method m) throws InvalidFormatException, IOException
	{
		String excelSheetName=m.getName();
		File f= new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testdata.xlsx");
		FileInputStream fis= new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(f);
		XSSFSheet sheet = wb.getSheet(excelSheetName);
		
		int totalrows=sheet.getLastRowNum();
		System.out.println(totalrows);
		Row rowcells=sheet.getRow(0);
		int totalcols=rowcells.getLastCellNum();
		System.out.println(totalcols);
		DataFormatter format=new DataFormatter();
		
		String testdata[][]=new String[totalrows][totalcols];
		for (int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
		{
			
				
				FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			testdata[i-1][j]=format.formatCellValue(sheet.getRow(i).getCell(j), evaluator);
			System.out.println(testdata[i-1][j]);
		}
		
	}
		
		return testdata;
}
}
