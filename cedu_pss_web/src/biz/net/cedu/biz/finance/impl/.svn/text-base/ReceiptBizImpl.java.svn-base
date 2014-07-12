package net.cedu.biz.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.ReceiptBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.ReceiptDao;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.Receipt;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 收据 业务逻辑实现
 * 
 * @author gaole
 *
 */

@Service
public class ReceiptBizImpl implements ReceiptBiz {
	
	@Autowired
	private ReceiptDao receiptDao;                     //邮寄包接口
	@Autowired
	private StudentBiz studentBiz;                     //学生Biz
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;//院校招生批次Biz
	@Autowired
	private LevelBiz levelBiz;                          //层次 Biz
	@Autowired
	private MajorBiz majorBiz;                          //专业 Biz
	
	@Autowired
	private FeePaymentBiz feePaymentBiz;                //缴费单 Biz
	
	
	
	
	/*
	 * 根据Id查询收据
	 * @see net.cedu.biz.finance.ReceiptBiz#findReceiptById(int)
	 */
	public Receipt findReceiptById(int id)throws Exception
	{
		
		return receiptDao.findById(id);
	}
	
	/*
	 * 修改收据
	 * @see net.cedu.biz.finance.ReceiptBiz#updateReceiptById(net.cedu.entity.finance.Receipt)
	 */
	public boolean updateReceiptById(Receipt receipt)throws Exception
	{
		receiptDao.update(receipt);
		return true;
		
	}
	
	
	/*
	 * 根据收据号段查询收据
	 * @see net.cedu.biz.finance.ReceiptBiz#countReceiptByInvoiceBookId(int, int, int, net.cedu.model.page.PageResult)
	 */
	public int countReceiptByInvoiceBookId(int invoiceBookId,int status,int isCannel,PageResult<Receipt> pr)throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam="";
		String params="";
		if(invoiceBookId!=0)
		{
			hqlparam+=" and  invoiceBookId="+Constants.PLACEHOLDER;
			params+=invoiceBookId+",";
		}
		if(status>=Constants.PAYMENT_STATUS_ZUO_FEI)
		{
			hqlparam+=" and  status="+Constants.PLACEHOLDER;
			params+=status+",";
		}
		if(isCannel>Constants.PAYMENT_STATUS_ZUO_FEI)
		{
			hqlparam+=" and  isCanceled="+Constants.PLACEHOLDER;
			params+=isCannel+",";
		}
		hqlparam+=" and deleteFlag = "+Constants.PLACEHOLDER;
		params+=Constants.DELETE_FALSE;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(params.split(","));
		}
		
		return receiptDao.getCounts(p);
		
		
	}

	/*
	 * 根据收据号段查询收据
	 * @see net.cedu.biz.finance.ReceiptBiz#findReceiptByInvoiceBookId(int, int, int, net.cedu.model.page.PageResult)
	 */
	public List<Receipt> findReceiptByInvoiceBookId(int invoiceBookId, int status,
			int isCannel, PageResult<Receipt> pr) throws Exception {
		
		List<Receipt> receiptst = null;
		PageParame p = new PageParame(pr);
		String hqlparam="";
		String params="";
		if(invoiceBookId!=0)
		{
			hqlparam+=" and  invoiceBookId="+Constants.PLACEHOLDER;
			params+=invoiceBookId+",";
		}
		if(status>Constants.PAYMENT_STATUS_ZUO_FEI)
		{
			hqlparam+=" and status="+Constants.PLACEHOLDER;
			params+=status+",";
		}
		if(isCannel>Constants.PAYMENT_STATUS_ZUO_FEI)
		{
			hqlparam+=" and isCanceled="+Constants.PLACEHOLDER;
			params+=isCannel+",";
		}
		hqlparam+=" and deleteFlag = "+Constants.PLACEHOLDER;
		params+=Constants.DELETE_FALSE;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(params.split(","));
		}
		
		Long[] recepits=receiptDao.getIDs(p);
		if (recepits != null && recepits.length != 0) {
			receiptst=new ArrayList<Receipt>();
			for(int i = 0; i < recepits.length; i++)
			{
				Receipt ri=receiptDao.findById(Integer.valueOf(recepits[i].toString()));
				Receipt receipt=ri;
				//Student student=studentBiz.findStudentById(receipt.getStudentId());
				FeePayment fp=this.feePaymentBiz.findFeePaymentByBarCode(receipt.getCode());
				Level level=new Level();
				Major major=new Major();
				AcademyEnrollBatch academyenrollbatc=new AcademyEnrollBatch();
				if(fp!=null && studentBiz.findStudentById(fp.getStudentId())!=null)
				{
					Student student=studentBiz.findStudentById(fp.getStudentId());
				    academyenrollbatc=academyenrollbatchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId());
				    level=levelBiz.findLevelById(student.getLevelId());
					major=majorBiz.findMajorById(student.getMajorId());
					if(academyenrollbatc!=null)
					{
						receipt.setBatchName(academyenrollbatc.getEnrollmentName());
					}
					if(level!=null)
					{
						receipt.setLevelName(level.getName());
					}
					if(major!=null)
					{
						receipt.setMajorName(major.getName());
					}	
					receipt.setStudentName(student.getName());
					receipt.setCertNo(student.getCertNo());
					receipt.setStudentId(student.getId());
					receipt.setFeePaymentId(fp.getId());
				}
				receiptst.add(receipt);
			}	
		}
		return receiptst;
	}

	/*
	 * 修改收据使用状态  返回值 1.修改成功  2.数据重复  3.数据不存在  4.操作失败
	 * @see net.cedu.biz.finance.ReceiptBiz#updateReceiptStatusByCode(java.lang.String)
	 */
	public int updateReceiptStatusByCode(String code) throws Exception {
		String hqlparam="";
		List<Object> list = new ArrayList<Object>();
		if(code!=null && !code.equals(""))
		{
			hqlparam+=" and code="+Constants.PLACEHOLDER;
			list.add(code);	
		}
		List<Receipt> receiptlst=receiptDao.getByProperty(hqlparam, list);
		if(receiptlst==null)
		{
			return 3;
		}else
		{
			if(receiptlst.size()>0)
			{
				Receipt receipt=receiptlst.get(0);
				if(receipt!=null)
				{
					if(receipt.getStatus()!=Constants.STATUS_DISABLE)
					{
						return 2;
					}else
					{
						receipt.setStatus(Constants.STATUS_ENABLED);
						this.updateReceiptById(receipt);
						return 1;
					}
				}
			}
		}
		return 4;
	}

	/*
	 * 作废收据
	 * @see net.cedu.biz.finance.ReceiptBiz#updateReceiptByCode(java.lang.String)
	 */
	public boolean updateReceiptByCode(String code) throws Exception {
		
		String hqlparam="";
		List<Object> list = new ArrayList<Object>();
		if(code!=null && !code.equals(""))
		{
			hqlparam+=" and code="+Constants.PLACEHOLDER+" and status>"+Constants.PLACEHOLDER;
			list.add(code);	
			list.add(Constants.PAYMENT_STATUS_ZUO_FEI);
		}
		List<Receipt> receiptlst=receiptDao.getByProperty(hqlparam, list);
		if(receiptlst!=null && receiptlst.size()>0)
		{
			Receipt receipt=receiptlst.get(0);
			receipt.setStatus(Constants.AUDIT_STATUS_INIT);   //作废
			this.updateReceiptById(receipt);
			return true;
		}
		return false;
	}
	
	/*
	 * 释放收据号
	 * 
	 * @see net.cedu.biz.finance.ReceiptBiz#updateReceiptStatusByCodeForShiFang(java.lang.String)
	 */
	public boolean updateReceiptStatusByCodeForShiFang(String code) throws Exception 
	{		
		String hqlparam="";
		List<Object> list = new ArrayList<Object>();
		if(code!=null && !code.equals(""))
		{
			hqlparam+=" and code="+Constants.PLACEHOLDER+" and status="+Constants.PLACEHOLDER;
			list.add(code);	
			list.add(Constants.PAYMENT_STATUS_ZUO_FEI);
		}
		else
		{
			return false;
		}
		List<Receipt> receiptlst=receiptDao.getByProperty(hqlparam, list);
		if(receiptlst!=null && receiptlst.size()>0)
		{
			Receipt receipt=receiptlst.get(0);
			receipt.setStatus(Constants.AUDIT_STATUS_FALSE);  //未使用状态
			receipt.setFeePaymentId(0);
			receipt.setStudentId(0);
			this.updateReceiptById(receipt);
			return true;
		}
		return false;
	}
	
	/*
	 * 添加收据
	 * @see net.cedu.biz.finance.ReceiptBiz#addReceipt(net.cedu.entity.finance.Receipt)
	 */
	public boolean addReceipt(Receipt receipt) throws Exception {
		receiptDao.save(receipt);
		return true;
	}
	
	/*
	 *	查询可以使用的收据
	 *
	 * @see net.cedu.biz.finance.ReceiptBiz#findReceiptCanUsing(java.lang.String)
	 */
	public boolean findReceiptCanUsing(String code) throws Exception 
	{
		boolean isback=false;
		String hqlparam="";
		List<Object> list = new ArrayList<Object>();
		if(code!=null && !code.equals(""))
		{
			hqlparam+=" and code="+Constants.PLACEHOLDER;
			list.add(code);	
		}
		hqlparam+=" and status="+Constants.PLACEHOLDER;
		list.add(Constants.STATUS_DISABLE);	
		List<Receipt> receiptlst=receiptDao.getByProperty(hqlparam, list);
		if(receiptlst!=null && receiptlst.size()>0)
		{
			isback=true;
		}
		return isback;
	}
	
	/*
	 * 使用收据
	 * @see net.cedu.biz.finance.ReceiptBiz#updateReceiptUsedByPayment(java.lang.String)
	 */
	 
	public boolean updateReceiptUsedByPayment(String code) throws Exception 
	{
		boolean isback=false;
		String hqlparam="";
		List<Object> list = new ArrayList<Object>();
		if(code!=null && !code.equals(""))
		{
			hqlparam+=" and code="+Constants.PLACEHOLDER;
			list.add(code);	
		}
		hqlparam+=" and status="+Constants.PLACEHOLDER;
		list.add(Constants.STATUS_DISABLE);	
		List<Receipt> receiptlst=receiptDao.getByProperty(hqlparam, list);
		if(receiptlst!=null && receiptlst.size()>0)
		{
			Receipt receipt=receiptlst.get(0);
			if(receipt!=null)
			{
				receipt.setStatus(Constants.STATUS_ENABLED);
				this.updateReceiptById(receipt);
				isback=true;
			}
		}
		return isback;
	}

	/**
	 * 通过收据code 字符串查询收据ID集合
	  * @see net.cedu.biz.finance.ReceiptBiz#findReceiptIdArrayByCodeIds(java.lang.String)
	 */
	public Long[] findReceiptIdArrayByCodeIds(String codeIds) throws Exception {
		
		return receiptDao.getIDs(" and code in ("+Constants.PLACEHOLDER+")", new Object[]{"$"+codeIds});
	}

	/*
	 * 根据机构、状态，是否核销查询全部的收据总数
	 * @see net.cedu.biz.finance.ReceiptBiz#findAllCountByBranchAndStatusAndIsCannel(int, int, int, int)
	 */
	public int findAllCountByBranchAndStatusAndIsCannel(int branch, int status,
			int isCannel,int invoiceBookStatus) throws Exception {
		return receiptDao.findAllCountByBranchAndStatusAndIsCannel(branch, status, isCannel ,invoiceBookStatus);
	}
	
	
}
