package com.len.trans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.len.trans.dao.GoodsDao;
import com.len.trans.pojo.Goods;
import com.len.trans.service.GoodsService;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{

	@Autowired
	@Qualifier("goodsDao")
	private GoodsDao goodsDao;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public List<Goods> getGoods() throws Exception{
		return goodsDao.getGoodsList();
//		throw new Exception("³ö´íÁË£¡£¡£¡");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void updatePrice(int goodsId, float goodsPrice) throws Exception {
		goodsDao.updatePrice(goodsId, goodsPrice);		
	}
}
