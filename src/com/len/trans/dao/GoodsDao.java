package com.len.trans.dao;

import java.util.List;

import com.len.trans.pojo.Goods;

public interface GoodsDao {
	public List<Goods> getGoodsList();
	public void updatePrice(int goodsId, float goodsPrice) throws Exception;
}
