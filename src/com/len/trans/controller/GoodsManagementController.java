package com.len.trans.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.len.trans.config.DataGrid;
import com.len.trans.config.JsonTransformer;
import com.len.trans.pojo.Goods;
import com.len.trans.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsManagementController {
	@Autowired
	@Qualifier("goodsService")
	private GoodsService goodsService;
	
	@Autowired
	@Qualifier("jsonTransformer")
	private JsonTransformer jsonTransformer;
	
	@RequestMapping(value="/management")
	public String goodsManagent(){
		return "/goods/goodsManagement";
	}
	
	@RequestMapping(value="getGoodsList/all")
	public void getGoodsList(HttpServletResponse response) throws Exception{
		
		List <Goods> goodsList = goodsService.getGoods();
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(goodsList);
		String jsonString = jsonTransformer.getJsonForm(dataGrid);
		//jsonString="{'total':+"+Integer.valueOf(goodsList.size())+",'rows':"+jsonString+"}";
		response.setCharacterEncoding("UTF-8");
		//response.setContentType("application/json");
		response.getWriter().print(jsonString);
	}
	
	@RequestMapping(value="editPrice")
	public void editPrice(HttpServletResponse response, @RequestParam int goodsId, @RequestParam float price) throws IOException{
		boolean flag =false;
		try {
			goodsService.updatePrice(goodsId, price);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		//response.setContentType("application/json");
		response.getWriter().print(flag);
	}
}
