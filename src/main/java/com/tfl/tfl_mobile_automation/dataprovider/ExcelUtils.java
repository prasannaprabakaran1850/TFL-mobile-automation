package com.tfl.tfl_mobile_automation.dataprovider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.atmecs.falcon.automation.util.parser.XlsReader;

public class ExcelUtils {
	
private static XlsReader excelReader;
	
	
	public static Object[][] getData(String filePath, String sheetName) {
	    excelReader=new XlsReader();
	    Object[][] obj = null;
        int rowCount;
        int col;
        try {
        	excelReader.setPath(filePath);
            rowCount = excelReader.getRowCount(sheetName);
            col = excelReader.getColumnCount(sheetName);
            System.out.println("rowcount="+rowCount+"   columnCount="+col);
            obj=new Object[rowCount][col];
            for (int i = 0; i < rowCount; i++) {
            	for (int j = 0; j < col; j++) {
            		String value = excelReader.getCellDataByColumnIndex(sheetName, j, i+1);
            		System.out.println("printing value=="+value);
            		System.out.println("valuefor("+i+","+j+")="+value);
            		obj[i][j] = value;
            		System.out.println(obj[i][j]);
                }
            }            
          
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return obj;
    }

	
	@DataProvider(name="logindata")
	public static Object[][] getLoginData() {
		String fileName = "login (1).xlsx";
		String filePath = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+fileName;
		Object[][] loginData = getData(filePath, "login");
		return loginData;
	}

}
