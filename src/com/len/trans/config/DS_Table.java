package com.len.trans.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class DS_Table {
	
	private String tableName;//����
	private boolean sharding;//�Ƿ��Ƭ
	private String shardingField;//��Ҫ����ˮƽ��Ƭ���ݵ��У�ֻ֧��һ��
	private List<DS_Rule> rules = new ArrayList<DS_Rule>();
	public String getTableName() {
		return tableName;
	}
	@XmlAttribute(name = "name")
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<DS_Rule> getRules() {
		return rules;
	}

	@XmlElementWrapper(name="dataSources", required=false)  
    @XmlElement(name="dataSource")
	public void setRules(List<DS_Rule> rules) {
		this.rules = rules;
	}
	@XmlAttribute(name = "sharding")
	public boolean isSharding() {
		return sharding;
	}
	public void setSharding(boolean sharding) {
		this.sharding = sharding;
	}
	public String getShardingField() {
		return shardingField;
	}
	@XmlAttribute(name = "field")
	public void setShardingField(String shardingField) {
		this.shardingField = shardingField;
	}
}
