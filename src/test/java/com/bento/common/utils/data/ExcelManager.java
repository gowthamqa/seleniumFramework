package com.bento.common.utils.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bento.common.dto.ClientDTO;
import com.bento.common.dto.EmployeeDTO;
import com.bento.common.dto.ProductDTO;

public class ExcelManager {

	Workbook workbook = null;
	Sheet sheet = null;

	public ExcelManager() {
		init("productdata.xlsx", "Sheet2");
	}

	/*public ExcelManager(String filePath) {
		init(filePath, "Sheet1");
	}*/
	
	public ExcelManager(String sheetName) {
		init("productdata.xlsx", sheetName);
	}

	public ExcelManager(String filePath, String sheetName) {
		init(filePath, sheetName);
	}

	private void init(String filePath, String sheetName) {

		File file = new File(filePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			if(filePath.contains(".xlsx")) {
				workbook = new XSSFWorkbook(fis);
			}else {
				workbook = new HSSFWorkbook(fis);
			}
			sheet = workbook.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int totalRows() {
		return sheet.getPhysicalNumberOfRows();
	}
	
	public int totalColumns() {
		Row row = sheet.getRow(0);
		return row.getLastCellNum();
	}

	public void readData(int rowNumber) {

		Row row = sheet.getRow(rowNumber);
		Cell cell = null;

		for (int i = 0; i < row.getLastCellNum(); i++) {
			cell = row.getCell(i);
			String value = getData(cell);
			System.out.println(value);
		}

	}
	
	public String readData(int rowNumber, String colName) {
		
		Row indexRow = sheet.getRow(0);
		Row row = sheet.getRow(rowNumber);
		Cell cell = null;
		
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if(indexRow.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
				cell = row.getCell(i);
				String value = getData(cell);
				System.out.println(value);				
			}			
		}
		System.out.println(getData(cell));	
		return getData(cell);
		
	}
	
	public ClientDTO getClientDTO(int rowNumber) {
		ClientDTO dto = new ClientDTO();

		Row row = sheet.getRow(rowNumber);

		dto.setCompanyName(getData(row.getCell(1)));
		dto.setAddress1(getData(row.getCell(2)));
		dto.setAddress2(getData(row.getCell(3)));
		dto.setCity(getData(row.getCell(4)));
		dto.setState(getData(row.getCell(5)));
		dto.setZipcode(getData(row.getCell(6)));
		dto.setAdminFirstName(getData(row.getCell(7)));
		dto.setAdminLastName(getData(row.getCell(8)));
		dto.setAdminEmailID(getData(row.getCell(9)));
		dto.setAdminPhoneNumber(getData(row.getCell(10)));
		//System.out.println(dto);
		return dto;
	}
	
	public EmployeeDTO getEmployeeDTO(int rowNumber) {
		EmployeeDTO dto = new EmployeeDTO();

		Row row = sheet.getRow(rowNumber);

		dto.setMemberFirstName(getData(row.getCell(1)));
		dto.setMemberMiddleName(getData(row.getCell(2)));
		dto.setMemberLastName(getData(row.getCell(3)));
		dto.setMemberEmailID(getData(row.getCell(4)));
		dto.setMemberPhoneNumber(getData(row.getCell(5)));
		dto.setMemberSSN(getData(row.getCell(6)));
		dto.setMemberDOB(getData(row.getCell(7)));
		dto.setStartDate(getData(row.getCell(8)));
		dto.setEndDate(getData(row.getCell(9)));
		
		return dto;
	}
	
	

	public ProductDTO getProductDTO(int rowNumber) {
		ProductDTO dto = new ProductDTO();

		Row row = sheet.getRow(rowNumber);

		dto.setProductTitle(getData(row.getCell(1)));
		dto.setProductDescription(getData(row.getCell(2)));
		dto.setPrice(getData(row.getCell(3)));
		dto.setShippingDays(getData(row.getCell(4)));
		dto.setTotalQty(getData(row.getCell(5)));
		dto.setMaxQty(getData(row.getCell(6)));
		dto.setKeywords(getData(row.getCell(7)));
		System.out.println(dto);
		return dto;
	}

	@SuppressWarnings("deprecation")
	private String getData(Cell cell) {
		String value = "";
		CellType cellT = cell.getCellTypeEnum();
		switch (cellT) {
		case NUMERIC:
			int db = (int) cell.getNumericCellValue();
			value = String.valueOf(db);
			break;
		case STRING:
			value = cell.getStringCellValue();
			break;
		case BOOLEAN:
			boolean b = cell.getBooleanCellValue();
			value = String.valueOf(b);
			break;
		case BLANK:
			value = "";
			break;
		case FORMULA:
			value = cell.getCellFormula();
			break;
		default:
			value = "";
			break;
		}

		return value;
	}

	public static void main(String[] args) {
		ExcelManager ex = new ExcelManager();
		System.out.println(ex.totalRows());
		System.out.println(ex.totalColumns());
		ex.readData(1);
		ex.readData(1,"totalQty");
	}

	
}
