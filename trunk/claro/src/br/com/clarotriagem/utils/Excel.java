package br.com.clarotriagem.utils;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class Excel {

	public static void main(String[] args) {
		
	}
	public static HSSFWorkbook outro() {
		try {
//			FileOutputStream fileOut = new FileOutputStream("c:/poi-test.xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("POI Worksheet");

			// index from 0,0... cell A1 is cell(0,0)
			HSSFRow row1 = worksheet.createRow(0);

			HSSFCell cellA1 = row1.createCell(0);
			cellA1.setCellValue("Hello");
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellA1.setCellStyle(cellStyle);

			HSSFCell cellB1 = row1.createCell(1);
			cellB1.setCellValue("Goodbye");
			cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellB1.setCellStyle(cellStyle);

			HSSFCell cellC1 = row1.createCell(2);
			cellC1.setCellValue(true);

			HSSFCell cellD1 = row1.createCell(3);
			cellD1.setCellValue(new Date());
			cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
			cellD1.setCellStyle(cellStyle);
			return workbook;
//			workbook.write(fileOut);
//			fileOut.flush();
//			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void primeiro() {
		try {
			String filename = "c:/hello.xls";
			HSSFWorkbook hwb = new HSSFWorkbook();
			HSSFSheet sheet = hwb.createSheet("new sheet");

			HSSFRow rowhead = sheet.createRow(0);
			rowhead.createCell(0).setCellValue("SNo");
			rowhead.createCell(1).setCellValue("First Name");
			rowhead.createCell(2).setCellValue("Last Name");
			rowhead.createCell(3).setCellValue("Username");
			rowhead.createCell(4).setCellValue("E-mail");
			rowhead.createCell(5).setCellValue("Country");

			HSSFRow row = sheet.createRow(1);
			row.createCell(0).setCellValue("1");
			row.createCell(1).setCellValue("Rose");
			row.createCell(2).setCellValue("India");
			row.createCell(3).setCellValue("roseindia");
			row.createCell(4).setCellValue("hello@roseindia.net");
			row.createCell(5).setCellValue("India");

			FileOutputStream fileOut = new FileOutputStream(filename);
			hwb.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated!");

		} catch (Exception ex) {
			System.out.println(ex);

		}
	}
}