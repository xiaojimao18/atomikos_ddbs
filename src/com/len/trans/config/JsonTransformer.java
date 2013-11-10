package com.len.trans.config;

import java.util.Date;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class JsonTransformer {
	
	public String getJsonForm(DataGrid dataGrid){
		JSONSerializer serializer = new JSONSerializer().transform(new DateTransformer("MM/dd/yyyy"), Date.class);;
		return serializer.exclude("*.class").include("*.rows").deepSerialize(dataGrid);
	}
}
