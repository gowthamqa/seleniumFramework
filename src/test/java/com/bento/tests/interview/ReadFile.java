package com.bento.tests.interview;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadFile {

	
	
	public void test1() {
		
		File file =new File("D:\\selenium_classes\\batch29\\framework_notes.txt");
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			int t = 0;
			while( (t = bis.read())!=-1 ) {
				
				char ch  = (char)t;
				System.out.print(ch);
			}
			bis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void test2() {
		File file =new File("D:\\selenium_classes\\batch29\\newfile.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String l = "";
			
			while( (l = br.readLine())!=null ) {
				System.out.println(l);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test3() {
		File file =new File("D:\\selenium_classes\\batch29\\newfile.txt");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			String st = "bharath";
			byte[]  b = st.getBytes();
			bos.write(b);
			bos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test4() {
		File file =new File("D:\\selenium_classes\\batch29\\newfile.txt");
		try {
			FileWriter wr = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(wr);
			String st = "I am bharath";
			bw.write(st);
			bw.close();
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void test5() {
		File file =new File("D:\\selenium_classes\\JBR_ShopVox.xls");
		try {
			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook book = new HSSFWorkbook(fis);
			HSSFSheet sheet = book.getSheetAt(0);
			
			HSSFRow row = sheet.getRow(0);
			HSSFCell cell = row.getCell(0);
			int totalCells = row.getPhysicalNumberOfCells();
			int totalRows = sheet.getPhysicalNumberOfRows();
			
			for (int i = 0; i < totalRows; i++) {
				row = sheet.getRow(i);
				for (int j = 0; j < totalCells; j++) {
					cell = row.getCell(j);
					System.out.print(cell + "    ");
				}
				System.out.println();
			}
			book.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ReadFile rf  = new ReadFile();
		rf.test5();
	}
}
