package com.freecrm.qa.excelreader;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader {
	
	String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	
	
	public Excel_Reader(String path){
		
		this.path=path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public String[][] getDataFromSheet(String excelName, String sheetName){
		 
		//This 2D array is for storing rows and columns data
		String dataSets[][] = null;
		
		try{
			
			sheet = workbook.getSheet(sheetName);
			
			// It gives number of rows in a sheet
			int totalRows = sheet.getLastRowNum() + 1;
			
			//count number of active columns in a row thats why we took 0 in getRow(0)
			//It gives total columns in a row
			int totalCols = sheet.getRow(0).getLastCellNum();
			
			//create array of rows and columns
			dataSets = new String[totalRows-1][totalCols];
			
			//Run for loop and store data in 2D array
			//This for loop will run on rows
			// Here we are running loop from i=1 because we want to leave first row which is header
			for(int i=1; i<totalRows; i++){
				 //Here we are getting active row
				XSSFRow rows = sheet.getRow(i);
				
				//This for loop will run on columns of active row
				for(int j=0; j<totalCols; j++){
					
					//This will get cell
					XSSFCell cell = rows.getCell(j);
					
					//If cell is of type String, then store the value in 2D array
					if(cell.getCellType() == Cell.CELL_TYPE_STRING){
						
						dataSets[i-1][j] = cell.getStringCellValue();
						
					}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
						String cellText = String.valueOf(cell.getNumericCellValue());
						dataSets[i-1][j] = cellText;
					}else{
						
						dataSets[i-1][j] = String.valueOf(cell.getBooleanCellValue());
					}
										
				}
				
				
			}

			return dataSets;
			
		}catch(Exception e){
			
			System.out.println("Exception in reading excel data : "+e.getMessage());
		}
						
		return dataSets;

	}
	
	
	public String getCellData(String sheetName, String colName, int rowNum){
		
		
		try{
			
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			
			for(int i=0; i<row.getLastCellNum(); i++){
				
				if(row.getCell(i).getStringCellValue().equals(colName)){
					col_Num = i;
					break;
				}
				
			}
			
			row = sheet.getRow(rowNum - 1);
			
			XSSFCell cell = row.getCell(col_Num);
			if(cell.getCellType() == Cell.CELL_TYPE_STRING){
				
				return cell.getStringCellValue();
			}else if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
				return "";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return null;
		
		
		
		
	}
	
	
	public static void main(String args[]){
		
		Excel_Reader excel = new Excel_Reader(System.getProperty("user.dir")+"/src/main/java/com/freecrm/qa/data/TestData.xlsx");
		String cellData = excel.getCellData("LoginTestData", "runmode", 8);
		System.out.println(cellData);
		System.out.println(excel.getDataFromSheet("TestData.xslx", "LoginTestData"));
		 
	}

}
