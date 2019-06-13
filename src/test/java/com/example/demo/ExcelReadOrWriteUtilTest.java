package com.example.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.lin.feng.sheng.utils.ExcelReadOrWriteUtil;

public class ExcelReadOrWriteUtilTest {

	private static Logger logger = Logger.getLogger(ExcelReadOrWriteUtilTest.class);
	
	private  ExcelReadOrWriteUtil excelReadOrWriteUtil= new  ExcelReadOrWriteUtil();



	
	@Test
	public  void testMa() {
		try {

			List<List<String>> oo = ExcelReadOrWriteUtil.readExcel(new File("E:\\mm.xlsx"), "", 0,
					0, -1);
			logger.info("\n" + oo);

			List<List<Object>> ddList  = new ArrayList<List<Object>>();
			for (List<String> list : oo) {
				List<Object> obj = new ArrayList<Object>();
				for (String string : list) {
					obj.add(string);
				}
				ddList.add(obj);
			}
			ExcelReadOrWriteUtil.createExcel2003Sheet( "E:\\mmm2003.xls","",ddList);
			excelReadOrWriteUtil.createExcel2007Sheet( "E:\\mmm2007","",ddList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
