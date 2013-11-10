package com.len.trans.test;

import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

import com.len.trans.config.DS_DataBase;

public class Test {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		DS_DataBase database = new DS_DataBase();
		//database.setDatabaseName("dbname");
		InputStream is = Test.class.getResourceAsStream("/db_structure.xml");
		
		database = JAXB.unmarshal(is, DS_DataBase.class);
        System.out.println(database);
	}

}
