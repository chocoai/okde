package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.StockBiz;
import net.cedu.biz.book.StoreroomBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.UserEnum;
import net.cedu.dao.book.StockDao;
import net.cedu.entity.book.Stock;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * 物料库存 业务逻辑实现层
 * @author YY
 * 
 * */
public class StockBizImpl implements StockBiz {

	@Autowired
	private  StockDao stockDao;  //库存数据接口
	@Autowired
	private  StoreroomBiz  storeroomBiz;//库房位置业务层接口
	@Autowired
	private BookBiz bookBiz;   //教材业务层接口
	

	/*
	 * 条件查询 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialStockBiz#findall(net.cedu.entity.meterial
	 * .MeterialStock)
	 */
	public List<Stock> findStockidByStock(Stock stock) throws Exception {
		List<Stock>  Stocklist = new ArrayList<Stock>();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>();
		if (null != stock) {
			//库房位置
			if (0 < stock.getStoreroomId()) {
				hqlparam += " and  storeroomId= " + Constants.PLACEHOLDER;
				paramList.add(stock.getStoreroomId());
			}
			//库房名称
			if(StringUtils.isNotBlank(stock.getStoreroomname()))
			{
				Long [] ids=storeroomBiz.findBookStoreroomByNames(stock.getStoreroomname());			
				if(null!=ids && ids.length>0)
				{
					 StringBuffer sb = new StringBuffer();
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
					 hqlparam+="and storeroomId in ("+Constants.PLACEHOLDER+")";
					 paramList.add("$"+sb.toString());	 					 
				}					 
				 
			}
			//教材名称
			if(StringUtils.isNotBlank(stock.getBookname()))
			{
				Long [] ids=bookBiz.findBookByLikeNames(stock.getBookname());			
				if(null!=ids && ids.length>0)
				{
					 StringBuffer sb = new StringBuffer();
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
					 hqlparam+="and storeroomId in ("+Constants.PLACEHOLDER+")";
					 paramList.add("$"+sb.toString());	 					 
				}
			}
			//版次
			if(StringUtils.isNotBlank(stock.getBookedition()))
			{
				Long [] ids=bookBiz.findBookByLikeeditions(stock.getBookedition());		
				if(null!=ids && ids.length>0)
				{
					 StringBuffer sb = new StringBuffer();
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
					 hqlparam+="and storeroomId in ("+Constants.PLACEHOLDER+")";
					 paramList.add("$"+sb.toString());	 					 
				}
			}
			hqlparam += " and deleteFlag=" + Constants.PLACEHOLDER;
			paramList.add(UserEnum.DeleteNo.value());	
		}
		
		Stocklist = stockDao.getByProperty(hqlparam, paramList.toArray());
		return Stocklist;
	}

	/**
	 * 根据库房id查询库房物料库存
	 */
	public Stock findStockByroomId(int id) throws Exception{
		Stock stock = new Stock();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>();
		if (0 != id) {
			hqlparam += " and  storeroomId= " + Constants.PLACEHOLDER;
			paramList.add(id);
		}
		hqlparam += " and deleteFlag=" + Constants.PLACEHOLDER;
		paramList.add(UserEnum.DeleteNo.value());

		stock = stockDao.getObjByProperty(hqlparam, paramList.toArray());
		return stock;
	}
	
	/**
	 * 根据库房id查询库房物料库存
	 */
	public Stock findStockByBookId(int bookid) throws Exception{
		Stock stock = new Stock();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>();
		if (0 != bookid) {
			hqlparam += " and  bookId= " + Constants.PLACEHOLDER;
			paramList.add(bookid);
		}
		hqlparam += " and deleteFlag=" + Constants.PLACEHOLDER;
		paramList.add(UserEnum.DeleteNo.value());

		stock = stockDao.getObjByProperty(hqlparam, paramList.toArray());
		return stock;
	}
	 

}
