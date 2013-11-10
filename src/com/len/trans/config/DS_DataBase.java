package com.len.trans.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "database")
public class DS_DataBase {
	@XmlAttribute(name = "name")
	private String databaseName;
	
	@XmlAttribute(name = "localDataSource")
	private String localDataSource;
	
	@XmlElementWrapper(name="tables")  
    @XmlElement(name="table")
	private List<DS_Table> tables = new ArrayList<DS_Table>();

	public String getDatabaseName() {
		return databaseName;
	}
	
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public List<DS_Table> getTables() {
		return tables;
	}
	
	public void setTables(List<DS_Table> tables) {
		this.tables = tables;
	}

	public String getLocalDataSource() {
		return localDataSource;
	}

	public void setLocalDataSource(String localDataSource) {
		this.localDataSource = localDataSource;
	}
	
}
