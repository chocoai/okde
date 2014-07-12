package net.cedu.biz.finance.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.finance.InvoiceBookBiz;
import net.cedu.biz.finance.ReceiptBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.InvoiceBookDao;
import net.cedu.dao.finance.ReceiptDao;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.finance.InvoiceBook;
import net.cedu.entity.finance.Receipt;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 票本  业务逻辑实现
 * 
 * @author gaole
 *
 */

@Service
public class InvoiceBookBizImpl implements InvoiceBookBiz {

	@Autowired
	private InvoiceBookDao invoicebookDao;      //票本接口
	
	@Autowired
	private ReceiptBiz receiptBiz;              //收据接口
	@Autowired
	private ReceiptDao receiptDao;
	
	@Autowired
	private BranchBiz branchBiz;                //学习中心Biz
	
	
	
	
	
	/*
	 *  查询票本收据按照授权状态 或中心机构(分页数量)
	 * @see net.cedu.biz.finance.InvoiceBookBiz#countInoviceBookAndReceiptByStatus(int, int, net.cedu.model.page.PageResult)
	 */
	public int countInoviceBookAndReceiptByStatus(int branchId, int status,
			PageResult<InvoiceBook> pr) throws Exception {
			PageParame p = new PageParame(pr);
			String hqlparam="";
			String params="";
			if(branchId>0)
			{
				hqlparam+=" and usedBy="+Constants.PLACEHOLDER;
				params+=branchId+",";
			}
			hqlparam+=" and status="+Constants.PLACEHOLDER;
			params+=status+",";
			hqlparam+=" and deleteFlag = "+Constants.PLACEHOLDER;
			params+=Constants.DELETE_FALSE;
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(hqlparam);
				p.setValues(params.split(","));
			}
		return invoicebookDao.getCounts( p);
		
	}
	
	/*
	 * 查询票本收据按照授权状态 或中心机构(分页数据)
	 * @see net.cedu.biz.finance.InvoiceBookBiz#findInoviceBookAndReceiptByStatus(int, int, net.cedu.model.page.PageResult, boolean)
	 */
	public List<InvoiceBook> findInoviceBookAndReceiptByStatus(int branchId, int status,
			PageResult<InvoiceBook> pr,boolean bol) throws Exception {
		List<InvoiceBook> iblst = null;
		PageParame p = new PageParame(pr);
		String hqlparam="";
		String params="";
		if(branchId>0)
		{
			hqlparam+=" and usedBy="+Constants.PLACEHOLDER;
			params+=branchId+",";
		}
		hqlparam+=" and status="+Constants.PLACEHOLDER;
		params+=status+",";
		hqlparam+=" and deleteFlag = "+Constants.PLACEHOLDER;
		params+=Constants.DELETE_FALSE;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(params.split(","));
		}
		
		Long[] ibids=invoicebookDao.getIDs(p);
		if (ibids != null && ibids.length != 0) {
			iblst=new ArrayList<InvoiceBook>();
			Map<String,Integer> UseNumberMap = receiptDao.findCountByBranchAndStatusAndIsCannel(branchId, Constants.Fee_STATUS_TRUE, Constants.AUDIT_STATUS_INIT,status);
			Map<String,Integer> CancelNumberMap = receiptDao.findCountByBranchAndStatusAndIsCannel(branchId,Constants.AUDIT_STATUS_INIT-1,Constants.Fee_STATUS_TRUE,status);
			Map<String,Integer> InvalidNumberMap = receiptDao.findCountByBranchAndStatusAndIsCannel(branchId,Constants.AUDIT_STATUS_INIT,Constants.AUDIT_STATUS_INIT,status);
			for(int i = 0; i < ibids.length; i++)
			{
				InvoiceBook ib=invoicebookDao.findById(Integer.valueOf(ibids[i].toString()));
				InvoiceBook invoicebook=ib;
				invoicebook.setUseNumber(UseNumberMap.get(invoicebook.getId()+"")==null?0:UseNumberMap.get(invoicebook.getId()+""));
				invoicebook.setCancelNumber(CancelNumberMap.get(invoicebook.getId()+"")==null?0:CancelNumberMap.get(invoicebook.getId()+""));
				invoicebook.setInvalidNumber(InvalidNumberMap.get(invoicebook.getId()+"")==null?0:InvalidNumberMap.get(invoicebook.getId()+""));
				Branch branch=branchBiz.findBranchById(invoicebook.getUsedBy());
				if(branch!=null)
				{
					invoicebook.setBranchName(branch.getName());
				}
				
				iblst.add(invoicebook);
			}	
		}
		return iblst;
	}
	
	/*
	 * 查询票本收据按照未授权状态(分页数量)
	 * @see net.cedu.biz.finance.InvoiceBookBiz#countInoviceBookByStatus(int, net.cedu.model.page.PageResult)
	 */
	public int countInoviceBookByStatus(int status, PageResult<InvoiceBook> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		String hqlparam="";
		String params="";
		hqlparam+=" and status="+Constants.PLACEHOLDER;
		params+=status+",";
		hqlparam+=" and deleteFlag = "+Constants.PLACEHOLDER;
		params+=Constants.DELETE_FALSE;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(params.split(","));
		}
	return invoicebookDao.getCounts( p);
		
	}
	
	/*
	 * 查询票本收据按照未授权状态(分页数据)
	 * @see net.cedu.biz.finance.InvoiceBookBiz#findInoviceBookByStatus(int, net.cedu.model.page.PageResult, boolean)
	 */
	public List<InvoiceBook> findInoviceBookByStatus(int status,
			PageResult<InvoiceBook> pr,boolean bol) throws Exception {
		List<InvoiceBook> iblst = null;
		PageParame p = new PageParame(pr);
		String hqlparam="";
		String params="";
		
		hqlparam+=" and status="+Constants.PLACEHOLDER;
		params+=status+",";
		hqlparam+=" and deleteFlag = "+Constants.PLACEHOLDER;
		params+=Constants.DELETE_FALSE;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(params.split(","));
		}
		
		Long[] ibids=invoicebookDao.getIDs(p);
		if (ibids != null && ibids.length != 0) {
			iblst=new ArrayList<InvoiceBook>();
			for(int i = 0; i < ibids.length; i++)
			{
				InvoiceBook ib=invoicebookDao.findById(Integer.valueOf(ibids[i].toString()));
				InvoiceBook invoicebook=ib;
				if(invoicebook.getUsedBy()!=0)
				{
					Branch branch=branchBiz.findBranchById(invoicebook.getUsedBy());
					if(branch!=null)
					{
						invoicebook.setBranchName(branch.getName());
					}	
				}
				iblst.add(invoicebook);
			}	
		}
		return iblst;
	}
	
	/*
	 * 收据授权
	 * @see net.cedu.biz.finance.InvoiceBookBiz#updateInvoiceBookById(net.cedu.entity.finance.InvoiceBook)
	 */
	public boolean updateInvoiceBookById(InvoiceBook invoice) throws Exception {
		invoicebookDao.update(invoice);
		return true;
	}
	
	
	/*
	 * 查询票本按Id
	 * @see net.cedu.biz.finance.InvoiceBookBiz#findInvoiceBookById(int)
	 */
	public InvoiceBook findInvoiceBookById(int id) throws Exception {
		
		return invoicebookDao.findById(id);
	}
	
	
	/*
	 * 收据登记
	 * @see net.cedu.biz.finance.InvoiceBookBiz#addInvoiceBook(net.cedu.entity.finance.InvoiceBook)
	 */
	public boolean addInvoiceBook(InvoiceBook invoiceBook,String strNo,int starNo,int endNo)throws Exception
	{
		int start=starNo;
		int end=endNo;
		
		String codes=",";
		while(starNo<=endNo)
		{
			if(codes.equals(",")){
				codes="'"+getCodeNum(String.valueOf(starNo),invoiceBook.getStartNo().length())+"'";
			}else{
				codes+=",'"+getCodeNum(String.valueOf(starNo),invoiceBook.getStartNo().length())+"'";
			}
			starNo++;	
		}
		if(codes.equals(",")){
			codes="0";
		}
		//已存在的号段
		Long []ids=receiptBiz.findReceiptIdArrayByCodeIds(codes);
		if(ids!=null&&ids.length!=0){
			return false;
		}
		
		starNo=start;
		endNo=end;
		invoicebookDao.save(invoiceBook);
		while(starNo<=endNo)
		{
			Receipt receipt=new Receipt();
			receipt.setInvoiceBookId(invoiceBook.getId());
			receipt.setCreatorId(invoiceBook.getCreatorId());
			receipt.setCreatedTime(new Date());
			receipt.setCode(getCodeNum(String.valueOf(starNo),invoiceBook.getStartNo().length()));
			receiptBiz.addReceipt(receipt);
			starNo++;	
		}
		return true;
	}
	
	

	
	
	/*
	 * 重复性校验票本按开始号段
	 * @see net.cedu.biz.finance.InvoiceBookBiz#findInvoiceBookByStartNo(java.lang.String)
	 */
	public InvoiceBook findInvoiceBookByStartNo(String startNo)
			throws Exception {
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(startNo!=null && !startNo.equals(""))
		{
			hqlparam+=" and startNo ="+Constants.PLACEHOLDER;
			list.add(startNo);	
		}
		List<InvoiceBook> invoicebooklst=invoicebookDao.getByProperty(hqlparam, list);
		if(invoicebooklst!=null && invoicebooklst.size()>0)
		{
			return invoicebooklst.get(0);
		}
		return null;
	}
	
	
	
	public static void main (String args[])
	{
		String qianzhui="0000";
		String starNo="00200";
		String total="10000";
		
		int num1=Integer.parseInt(starNo);
		int num2=Integer.parseInt(total);
		String str="";
		System.out.println(num1+"@"+num2);
		int s=0;
		while(num1<=num2)
		{
			System.out.println(getCodeNum(String.valueOf(num1),(qianzhui+starNo).length()));
			num1++;
			s++;
		}
		
		
		

		
	}
	
	/**
	 * 补零
	 * @param strs
	 * @param count
	 * @return
	 */
	private static String getCodeNum(String strs,int count)
	{
		while(strs.length()<count)
		{
			strs="0"+strs;
		}
		return strs;
	}

	/*
	 * 根据机构查询票本总数
	 * @see net.cedu.biz.finance.InvoiceBookBiz#findAllTotalByBranch(int, int)
	 */
	public int findAllTotalByBranch(int branch, int status) throws Exception {
		return invoicebookDao.findAllTotalByBranch(branch, status);
	}
	
	
	
	
}
