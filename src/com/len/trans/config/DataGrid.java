package com.len.trans.config;

import java.util.List;

public class DataGrid {
	
	private int total;
	private List rows;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
		this.total = rows.size();
	}
	
}
