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
	private DS_Config databaseDSConfig;//���ݿ�ı�ṹ����Ƭ���
	
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
				//�ҵ�dataSource��Ҫ�ٱȽ�rule����Ƭ���򣩣����rule��Ϊ�գ�˵����ˮƽ��Ƭ
				//�����Ƿֲ��ڲ�ͬ��վ����
				DS_Rule ds_rule = ruleList.get(i);
				String rule = ds_rule.getRuleType();
				String value = ds_rule.getRuleValue();
				String shardingField = getShardingField(tableName);
				int fieldIndex = getFieldIndex(fields, shardingField);
				
				if(rule != null && rule.equalsIgnoreCase("EQUALS") && fields != null && params != null){//��ʱֻ����EQUALS��ϵ���������������������ϵ���Զ�����
//					if(fieldIndex == -1 ){
//						throw new  Exception("�����쳣�� û���ҵ����е��� : "+shardingField+"������ �� " + tableName);
//					}
					if(value.equals(params[fieldIndex])){
						JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource)springUtil.getBean(ds_rule.getDataSourceName()));
						jdbcTemplateList.add(jdbcTemplate);//ִ��Sql��JdbcTempalte�б���Ϊ�����漰�����µ��������Դ������JdbcTempalte�ж��
					}
				} else {
					JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource)springUtil.getBean(ds_rule.getDataSourceName()));
					jdbcTemplateList.add(jdbcTemplate);//ִ��Sql��JdbcTempalte�б���Ϊ�����漰�����µ��������Դ������JdbcTempalte�ж��
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
				//�ҵ�dataSource��Ҫ�ٱȽ�rule����Ƭ���򣩣����rule��Ϊ�գ�˵����ˮƽ��Ƭ
				//�����Ƿֲ��ڲ�ͬ��վ����
				DS_Rule ds_rule =  ruleList.get(i);
				String rule = ds_rule.getRuleType();
				String value = ds_rule.getRuleValue();
				String shardingField = getShardingField(tableName);

				if(rule != null && rule.equalsIgnoreCase("EQUALS") && fields != null && params !=null){//��ʱֻ����EQUALS��ϵ���������������������ϵ���Զ�����
					int fieldIndex = getFieldIndex(fields, shardingField);
//					if(fieldIndex == -1 ){
//						throw new  Exception("�����쳣�� û���ҵ����е��� : "+shardingField+"�����Ա� " + tableName);
//					}
					if(value.equals(params[fieldIndex])){
						JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource)springUtil.getBean(ds_rule.getDataSourceName()));
						jdbcTemplateList.add(jdbcTemplate);//ִ��Sql��JdbcTempalte
					}
				}else{
					JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource)springUtil.getBean(ds_rule.getDataSourceName()));
					jdbcTemplateList.add(jdbcTemplate);//ִ��Sql��JdbcTempalte�б�
				}
				
			}
		}
		return jdbcTemplateList;
	}
}

