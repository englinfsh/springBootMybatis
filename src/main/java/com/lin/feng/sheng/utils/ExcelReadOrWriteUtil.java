package com.lin.feng.sheng.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelReadOrWriteUtil {

	private static Logger logger = Logger.getLogger(ExcelReadOrWriteUtil.class);

	public static List<List<String>> readExcel(File file,String passwd,int fromRow,
			int fromColumn,int lastColumn) throws Exception {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
				.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file,passwd, fromRow,
					 fromColumn,lastColumn);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file,passwd, fromRow,
					 fromColumn,lastColumn);
		} else {
			throw new IOException("文件格式不正确.");
		}
	}


	/**
	 *
	 * @param file
	 * @param passwd 密码
	 * @param fromRow 开始行
	 * @param fromColumn 开始列
	 * @param lastColumn 结束列
	 * @return
	 * @throws Exception
	 */
	private static List<List<String>> read2003Excel(File file,String passwd,int fromRow,
			int fromColumn,int lastColumn)
			throws Exception {
		List<List<String>> list = new LinkedList<List<String>>();
		FileInputStream  fileInputStream= null;
		HSSFWorkbook hwb = null;
		try {
			fileInputStream= new FileInputStream(file) ;
			POIFSFileSystem pfs = new POIFSFileSystem(fileInputStream);
			if(!"".equals(passwd==null?"":passwd)){
				Biff8EncryptionKey.setCurrentUserPassword(passwd);
			}
			hwb = new HSSFWorkbook(pfs);
			HSSFSheet sheet = hwb.getSheetAt(0);
			Object value = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			int counter = 0;
			if(fromRow<=0){
				fromRow=sheet.getFirstRowNum();
			}
			counter=fromRow;
			for (int i = fromRow; counter < sheet
					.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				} else {
					counter++;
				}
				List<String> linked = new LinkedList<String>();
				if(fromColumn<=0){
					fromColumn=row.getFirstCellNum();
				}
				if(lastColumn<0){
					lastColumn =row.getLastCellNum();
				}
				for (int j = fromColumn; j <lastColumn; j++) {
					cell = row.getCell(j);
					if (cell == null) {
						linked.add("");
						continue;
					}
					value = cell.toString();
					linked.add(value.toString());
				}
				linked.add(String.valueOf(i+1));
				list.add(linked);
			}
		} catch (Exception e) {
			logger.error("读取文件失败",e);
		}finally {
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (Exception e) {
					logger.error("异常:",e);
				}
			}
			if(hwb!=null){
				try {
					hwb.close();
				} catch (Exception e) {
					logger.error("异常hwb:",e);
				}
			}
		}

		return list;
	}


	/**
	 *
	 * @param file
	 * @param password 密码
	 * @param fromRow 开始行
	 * @param fromColumn 开始列
	 * @param lastColumn 结束列
	 * @return
	 * @throws Exception
	 */
	private static List<List<String>> read2007Excel(File file,String password,int fromRow,
			int fromColumn,int lastColumn)
			throws Exception {
		List<List<String>> list = new LinkedList<List<String>>();

		FileInputStream  fileInputStream=null;
		XSSFWorkbook wb = null;
		try {
			fileInputStream= new FileInputStream(file) ;
			if("".equals(password==null?"":password)){
				wb = new XSSFWorkbook(fileInputStream);
			}else{
				POIFSFileSystem pfs = new POIFSFileSystem(fileInputStream);
				EncryptionInfo info = new EncryptionInfo(pfs);
				Decryptor d = Decryptor.getInstance(info);
				d.verifyPassword(password);
				wb = new XSSFWorkbook(d.getDataStream(pfs));

			}

			XSSFSheet sheet = wb.getSheetAt(0);//默认处理第一个表格
			Object value = null;
			XSSFRow row = null;
			XSSFCell cell = null;
			int counter = 0;
			if(fromRow<=0){
				fromRow=sheet.getFirstRowNum();
			}
			counter=fromRow;
			for (int i = fromRow; counter < sheet
					.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				} else {
					counter++;
				}
				List<String> linked = new LinkedList<String>();
				if(fromColumn<0){
					fromColumn= row.getFirstCellNum();
				}
				if(lastColumn<0){
					lastColumn =row.getLastCellNum();
				}
				for (int j =fromColumn; j <lastColumn; j++) {
					cell = row.getCell(j);
					if (cell == null) {
						linked.add("");
						continue;
					}
					value = cell.toString();
					if (value == null || "".equals(value)) {
						value="";
					}
					linked.add(value.toString());

				}
				linked.add(String.valueOf(i+1));
				list.add(linked);
			}
		} catch (Exception e) {
			logger.error("读取文件失败A",e);
		}finally {
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (Exception e) {
					logger.error("异常A",e);
				}
			}
			if(wb!=null){
				try {
					wb.close();
				} catch (Exception e) {
					logger.error("异常wb.A",e);
				}
			}
		}
		return list;
	}
	public static void main(String[] args) {
		try {

			List<List<String>> oo=  readExcel(new File("E:\\mm.xlsx"),"",0,0,-1);
			logger.info(oo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}