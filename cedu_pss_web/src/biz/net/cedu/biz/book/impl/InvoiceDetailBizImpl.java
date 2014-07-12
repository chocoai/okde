package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.InvoiceDetailBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.InvoiceDetailDao;
import net.cedu.entity.book.InvoiceDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 总部订购明细业务层
 * @author YY
 *
 */
@Service
public class InvoiceDetailBizImpl implements InvoiceDetailBiz {

	@Autowired
	private InvoiceDetailDao  invoiceDetailDao;//数据层
	/**
	 * 根据总部订购id查询订购单明细
	 */
	public List<InvoiceDetail> findorderIdByInvoiceDetail(int code)throws Exception {
		 String sql="";
		 List<InvoiceDetail> detaillist=new ArrayList<InvoiceDetail>();
		 List<Object> list=new ArrayList<Object>();
		 if(0<code)
		 {
			 sql+=" and invoiceId = "+Constants.PLACEHOLDER;
			 list.add(code);
		 }
		 detaillist=invoiceDetailDao.getByProperty(sql,list);
		 return detaillist;
	}
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InvoiceDetail findByid(int id) throws Exception
	{
		return invoiceDetailDao.findById(id);
	}
	/**
	 * 按照id集合查詢出訂單集合
	 * @param ids
	 * @return list
	 * @throws Exception
	 */
	public List<InvoiceDetail> findIdsByInvoiceDetail(int[] ids)throws Exception {
		List<InvoiceDetail> list=new ArrayList<InvoiceDetail>();
		for (int id : ids) {
			InvoiceDetail invoiceDetail=invoiceDetailDao.findById(id);
			list.add(invoiceDetail);
		} 
		return list;
	}
	/**
	 * 增加方法
	 */
	public void addInvoiceDetail(InvoiceDetail invoiceDetail) {
		invoiceDetailDao.save(invoiceDetail);
		
	}
}
