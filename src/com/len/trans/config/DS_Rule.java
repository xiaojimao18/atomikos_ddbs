package com.len.trans.config;

import javax.xml.bind.annotation.XmlAttribute;

public class DS_Rule {
	
	private String dataSourceName;
	private String ruleType;
	private String ruleValue;
	
	public String getDataSourceName() {
		return dataSourceName;
	}
	@XmlAttribute(name = "name")
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
	public String getRuleType() {
		return ruleType;
	}
	@XmlAttribute(name = "rule")
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getRuleValue() {
		return ruleValue;
	}
	@XmlAttribute(name = "value")
	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	
	
}
