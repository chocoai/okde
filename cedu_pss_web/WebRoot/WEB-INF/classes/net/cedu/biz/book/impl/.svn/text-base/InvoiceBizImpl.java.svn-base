package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.biz.book.CeduBookOrderBiz;
import net.cedu.biz.book.InvoiceBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.InvoiceDao;
import net.cedu.entity.book.BookSupplier;
import net.cedu.entity.book.CeduBookOrder;
import net.cedu.entity.book.Invoice;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 总部发货单
 * @author YY
 *
 */
@Service
public class InvoiceBizImpl implements InvoiceBiz{
	
	@Autowired
	private  InvoiceDao invoiceDao; //总部发货数据层
	@Autowired
	private BookSupplierBiz bookSupplierBiz; //书商业务层
	@Autowired
	private CeduBookOrderBiz ceduBookOrderBiz; //订购单业务层
	/**
	 * 查询全部
	 * @return
	 * @throws Exception
	 */
	public List<Invoice> findAll() throws Exception {
	 
		return invoiceDao.findAll();
	}
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Invoice findIdByInvoice(int id) throws Exception {
		 
		return invoiceDao.findById(id);
	}
	/**
	 * 分页数量
	 */
	public int findInvoicePageCountByConditions(Invoice invoice,
			PageResult<Invoice> pr) throws Exception {		 
		PageParame p = new PageParame(pr);
		List<Object> paramlist = new ArrayList<Object>();
		String hql = "";
		if (invoice != null) {
			if(StringUtils.isNotBlank(invoice.getCode()))
			{
				hql+="and code like "+Constants.PLACEHOLDER;
				paramlist.add("%"+invoice.getCode()+"%");
			}
			if(StringUtils.isNotBlank(invoice.getSuppliername()))
			{
				Long [] ids=bookSupplierBiz.findBookSupplierByNames(invoice.getSuppliername());
				 StringBuffer sb = new StringBuffer();
				if(null!=ids && ids.length>0)
				{
					
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
					
					 
				}
				 hql+="and supplierId in ("+Constants.PLACEHOLDER+")";
				 paramlist.add("$"+sb.toString());	 
				
			}
			if(-1<invoice.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(invoice.getDeleteFlag());
				 
			}
 
			p.setHqlConditionExpression(hql);	
			p.setValues(paramlist.toArray());
		}
		int count=invoiceDao.getCounts(p);
		
		return count;
	}
	/**
	 * 分页集合
	 */
	public List<Invoice> findInvoicePageListByConditions(Invoice invoice,
			PageResult<Invoice> pr) throws Exception {
		 
		List<Invoice> invoicelist = new ArrayList<Invoice>();
		PageParame p = new PageParame(pr);
		
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (invoice != null) {
			
			if(StringUtils.isNotBlank(invoice.getCode()))
			{
				hql+="and code like "+Constants.PLACEHOLDER;
				paramlist.add("%"+invoice.getCode()+"%");
			}
			if(StringUtils.isNotBlank(invoice.getSuppliername()))
			{
				Long [] ids=bookSupplierBiz.findBookSupplierByNames(invoice.getSuppliername());
				StringBuffer sb = new StringBuffer();
				if(null!=ids && ids.length>0)
				{
					 
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
					
					 
				}
				 hql+="and supplierId in ("+Constants.PLACEHOLDER+")";
				 paramlist.add("$"+sb.toString());	 
				
			}
			if(-1<invoice.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(invoice.getDeleteFlag());
				 
			}
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}
		Long[] userIds = invoiceDao.getIDs(p);
		if (userIds != null && userIds.length != 0) {
			for (int i = 0; i < userIds.length; i++) {
				Invoice in = invoiceDao.findById(Integer.parseInt(userIds[i]
						.toString()));
				if(in!=null)
				{
					Invoice invo=in;
					BookSupplier book=bookSupplierBiz.findBookSupplierById(invo.getSupplierId());
					if(book!=null)
					{
						invo.setSuppliername(book.getName());
					}
					CeduBookOrder cedu=ceduBookOrderBiz.findIdByCeduBookOrder(invo.getOrderId());
					if(null!=cedu)
					{
						invo.setOrdercode(cedu.getCode());
					}
					invoicelist.add(in);
				}
			}
		}
		return invoicelist;
	}
	
	/**
	 * 增加方法
	 */
	public void addInvoice(Invoice invoice) throws Exception {
		 
		   invoiceDao.save(invoice);
	}
 
	

}
