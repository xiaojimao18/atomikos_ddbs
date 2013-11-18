package com.len.trans.config;

import java.io.InputStream;

import javax.xml.bind.JAXB;

import com.len.trans.test.Test;

public class DS_Config {
	private DS_DataBase database;
	public DS_Config(){
		database = new DS_DataBase();
		InputStream is = Test.class.getResourceAsStream("/db_structure.xml");
		
		database = JAXB.unmarshal(is, DS_DataBase.class);
	}
	public DS_DataBase getDS_DataBase(){
		return database;
	}
}
