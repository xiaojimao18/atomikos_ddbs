package com.len.trans.service;

import java.util.List;

import com.len.trans.pojo.Goods;

public interface GoodsService {

	public List<Goods> getGoods() throws Exception;
	public void updatePrice(int goodsId, float goodsPrice) throws Exception;
}
