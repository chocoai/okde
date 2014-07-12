package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.StockTransferBiz;
import net.cedu.biz.book.StoreroomBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.book.StockTransferDao;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.StockTransfer;
import net.cedu.entity.book.Storeroom;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockTransferBizImpl  implements StockTransferBiz{
	
	@Autowired
	private StockTransferDao stockTransferDao; //移库数据层
	@Autowired
	private  StoreroomBiz  storeroomBiz; //库房业务层
	@Autowired
	private BookBiz bookBiz; //教材业务层
	@Autowired
	private BranchDao branchDao; //学习中心数据称
	/**
	 * 分页方法 数量
	 */
	public int countStockTransferByParams(StockTransfer stockTransfer,
			PageResult<StockTransfer> pr) throws Exception {
		 
		PageParame p = new PageParame(pr); 
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>();
		if (null != stockTransfer) {
			//移出库房名称
			if (0 < stockTransfer.getFromId()) {
				hqlparam += " and  fromId= " + Constants.PLACEHOLDER;
				paramList.add(stockTransfer.getFromId());
			}
			//移出库房位置
			if(StringUtils.isNotBlank(stockTransfer.getFromname()))
			{
				Long [] ids=storeroomBiz.findBookStoreroomByNames(stockTransfer.getFromname());			
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
			//移入库房名称
			if (0 < stockTransfer.getToId()) {
				hqlparam += " and  toId= " + Constants.PLACEHOLDER;
				paramList.add(stockTransfer.getToId());
			}
			//移入库房位置
			if(StringUtils.isNotBlank(stockTransfer.getToname()))
			{
				Long [] ids=storeroomBiz.findBookStoreroomByNames(stockTransfer.getToname());			
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
			if(StringUtils.isNotBlank(stockTransfer.getBookname()))
			{
				Long [] ids=bookBiz.findBookByLikeNames(stockTransfer.getBookname());			
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
					 hqlparam+="and bookId in ("+Constants.PLACEHOLDER+")";
					 paramList.add("$"+sb.toString());	 					 
				}
			}
			//教材版次
			if(StringUtils.isNotBlank(stockTransfer.getBookedition()))
			{
				Long [] ids=bookBiz.findBookByLikeeditions(stockTransfer.getBookedition());		
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
				
					 hqlparam+="and bookId in ("+Constants.PLACEHOLDER+")";
					 paramList.add("$"+sb.toString());	 					 
				}
			}
 
		}
		int count = stockTransferDao.getCounts(p);
		return count;
		
	}
	
	/**
	 * 分页方法集合
	 */
	public List<StockTransfer> findStockTransferByParams(
			StockTransfer stockTransfer, PageResult<StockTransfer> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		List<StockTransfer>  stockTransferlist = new ArrayList<StockTransfer>();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>();
		if (null != stockTransfer) {
			//移出库房名称
			if (0 < stockTransfer.getFromId()) {
				hqlparam += " and  fromId= " + Constants.PLACEHOLDER;
				paramList.add(stockTransfer.getFromId());
			}
			//移出库房位置
			if(StringUtils.isNotBlank(stockTransfer.getFromname()))
			{
				Long [] ids=storeroomBiz.findBookStoreroomByNames(stockTransfer.getFromname());			
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
			//移入库房名称
			if (0 < stockTransfer.getToId()) {
				hqlparam += " and  toId= " + Constants.PLACEHOLDER;
				paramList.add(stockTransfer.getToId());
			}
			//移入库房位置
			if(StringUtils.isNotBlank(stockTransfer.getToname()))
			{
				Long [] ids=storeroomBiz.findBookStoreroomByNames(stockTransfer.getToname());			
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
			if(StringUtils.isNotBlank(stockTransfer.getBookname()))
			{
				Long [] ids=bookBiz.findBookByLikeNames(stockTransfer.getBookname());			
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
					 hqlparam+="and bookId in ("+Constants.PLACEHOLDER+")";
					 paramList.add("$"+sb.toString());	 					 
				}
			}
			//教材版次
			if(StringUtils.isNotBlank(stockTransfer.getBookedition()))
			{
				Long [] ids=bookBiz.findBookByLikeeditions(stockTransfer.getBookedition());		
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
				
					 hqlparam+="and bookId in ("+Constants.PLACEHOLDER+")";
					 paramList.add("$"+sb.toString());	 					 
				}
			}
 
			p.setHqlConditionExpression(hqlparam);
			p.setValues(paramList.toArray());
		}
		//查询主键集合
		Long[] TransferIds = stockTransferDao.getIDs(p);
		if (TransferIds != null && TransferIds.length != 0) {
			//循环赋值
			for (int i = 0; i < TransferIds.length; i++) {
				StockTransfer st = stockTransferDao.findById(Integer.parseInt(TransferIds[i].toString()));
				if (st != null) {
					StockTransfer sTransfer=st;
					Book book=bookBiz.findBookById(sTransfer.getBookId());
					 Storeroom ss=storeroomBiz.findpositionByStoreroom((sTransfer.getFromId()));
					 Storeroom srr=storeroomBiz.findpositionByStoreroom(sTransfer.getToId());
					
					if(book!=null)
					{
						sTransfer.setBookname(book.getName());
						sTransfer.setBookedition(book.getEdition());
					}					
					if(ss!=null)
					{
						sTransfer.setFromname(ss.getName());
						Branch br=branchDao.findById(ss.getPosition());
						if(null!=br){
						sTransfer.setFromweizhi(br.getName());
						}
					}
					if(srr!=null)
					{
						sTransfer.setToname(srr.getName());
						Branch br=branchDao.findById(srr.getPosition());
						if(null!=br){
						sTransfer.setToweizhi(br.getName());
						}
					}
					stockTransferlist.add(sTransfer);
				}
			}
		}

		return stockTransferlist;
	}
	/**
	 * 增加方法
	 */
	public void addStockTransfer(StockTransfer stockTransfer)
			throws Exception {
		stockTransferDao.save(stockTransfer);
		
	}

}
