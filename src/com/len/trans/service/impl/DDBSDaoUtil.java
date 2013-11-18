package com.len.trans.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.len.trans.config.DS_Config;
import com.len.trans.config.DS_Rule;
import com.len.trans.config.DS_Table;
import com.len.trans.config.SpringUtil;

public class DDBSDaoUtil {
	
	@Autowired
	@Qualifier("databaseDSConfig")
	private DS_Config databaseDSConfig;//数据库的表结构及分片情况
	
	@Autowired
	@Qualifier("springUtil")
	private SpringUtil springUtil;
	
	public List<DS_Rule> getNeededDataSources(String tableName){
		List<DS_Rule> ruleList = new ArrayList<DS_Rule>();
		if(tableName != null && tableName != ""){
			List<DS_Table> tables = databaseDSConfig.getDS_DataBase().getTables();
			for(DS_Table t : tables){
				if(tableName.equalsIgnoreCase(t.getTableName())){					
					if(t.isSharding()){						
						return t.getRules();
					}
				}
			}
		}		
		return ruleList;	
	}
	public String getShardingField(String tableName){		
		if(tableName != null && tableName != ""){
			List<DS_Table> tables = databaseDSConfig.getDS_DataBase().getTables();
			for(DS_Table t : tables){
				if(tableName.equalsIgnoreCase(t.getTableName())){					
					if(t.isSharding()){						
						return t.getShardingField();
					}
				}
			}
		}		
		return null;	
	}
	public String getFieldsStr(String [] fields){
		String fieldsStr = null;
		if(fields != null){
			fieldsStr = fields[0];
			for(int i = 1; i < fields.length; i ++){
				fieldsStr = fieldsStr + "," +fields[i];
			}
		}
		return fieldsStr;
	}
	public int getFieldIndex(String [] fields, String field){
		int index = -1;
		for(int i = 0; i < fields.length; i ++){
			if(fields[i].equalsIgnoreCase(field)){
				return i;
			}
		}
		return index;		
	}
	public List<JdbcTemplate> getUpdateJdbcTemplateList(String tableName, String fields[], Object params []){
		List <JdbcTemplate> jdbcTemplateList = new ArrayList<JdbcTemplate>();
		List<DS_Rule> ruleList = getNeededDataSources(tableName);
		if(ruleList != null && ruleList.size() != 0){
			for(int i = 0; i < ruleList.size(); i ++){
				//找到dataSource后还要再比较rule（分片规则），如果rule不为空，说明有水平分片
				//而且是分布在不同的站点上
				DS_Rule ds_rule = ruleList.get(i);
				String rule = ds_rule.getRuleType();
				String value = ds_rule.getRuleValue();
				String shardingField = getShardingField(tableName);
				int fieldIndex = getFieldIndex(fields, shardingField);
				
				if(rule != null && rule.equalsIgnoreCase("EQUALS") && fields != null && params != null){//暂时只考虑EQUALS关系，所以如果配置了其他关系会自动忽略
//					if(fieldIndex == -1 ){
//						throw new  Exception("出现异常： 没有找到表中的列 : "+shardingField+"，来自 表： " + tableName);
//					}
					if(value.equals(params[fieldIndex])){
						JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource)springUtil.getBean(ds_rule.getDataSourceName()));
						jdbcTemplateList.add(jdbcTemplate);//执行Sql的JdbcTempalte列表，以为可能涉及到更新到多个数据源，所以JdbcTempalte有多个
					}
				} else {
					JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource)springUtil.getBean(ds_rule.getDataSourceName()));
					jdbcTemplateList.add(jdbcTemplate);//执行Sql的JdbcTempalte列表，以为可能涉及到更新到多个数据源，所以JdbcTempalte有多个
				}
				
			}
		}
		return jdbcTemplateList;
	}
	public List<JdbcTemplate> getQueryJdbcTemplateList(String tableName, String fields[], Object params []){
		List<JdbcTemplate> jdbcTemplateList = new ArrayList<JdbcTemplate>();
		List<DS_Rule> ruleList = getNeededDataSources(tableName);
		if(ruleList != null && ruleList.size() != 0){
			for(int i = 0; i < ruleList.size(); i ++){
				//找到dataSource后还要再比较rule（分片规则），如果rule不为空，说明有水平分片
				//而且是分布在不同的站点上
				DS_Rule ds_rule =  ruleList.get(i);
				String rule = ds_rule.getRuleType();
				String value = ds_rule.getRuleValue();
				String shardingField = getShardingField(tableName);

				if(rule != null && rule.equalsIgnoreCase("EQUALS") && fields != null && params !=null){//暂时只考虑EQUALS关系，所以如果配置了其他关系会自动忽略
					int fieldIndex = getFieldIndex(fields, shardingField);
//					if(fieldIndex == -1 ){
//						throw new  Exception("出现异常： 没有找到表中的列 : "+shardingField+"，来自表： " + tableName);
//					}
					if(value.equals(params[fieldIndex])){
						JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource)springUtil.getBean(ds_rule.getDataSourceName()));
						jdbcTemplateList.add(jdbcTemplate);//执行Sql的JdbcTempalte
					}
				}else{
					JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource)springUtil.getBean(ds_rule.getDataSourceName()));
					jdbcTemplateList.add(jdbcTemplate);//执行Sql的JdbcTempalte列表
				}
				
			}
		}
		return jdbcTemplateList;
	}
}

