package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.RefundBranch;
import net.cedu.model.page.PageResult;


/**
 * 总部/院校退中心费用
 * @author xiao
 *
 */
public interface RefundBranchBiz 
{
	
	/**
	 * 添加总部/院校退中心费用
	 * @param refundBranch 
	 * @return
	 * @throws Exception
	 */
	public boolean addRefundBranch(RefundBranch refundBranch) throws Exception;
	
	/**
	 * 删除总部/院校退中心费用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRefundBranchById(int id) throws Exception;
	
	/**
	 * 修改总部/院校退中心费用
	 * @param refundBranch
	 * @return
	 * @throws Exception
	 */
	public boolean updateRefundBranch(RefundBranch refundBranch) throws Exception;
	
	/**
	 * 根据Id查找总部/院校退中心费用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RefundBranch findRefundBranchById(int id)throws Exception;
	
	/**
	 * 查询总部/院校退中心费用数量
	 * @param refundBranch
	 * @param student
	 * @param feePayment
	 * @param starttime
	 * @param endtime
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findRefundBranchPageCount(RefundBranch refundBranch,Student student,FeePayment feePayment,String starttime,String endtime,PageResult<RefundBranch> pr)throws Exception;
	
	/**
	 * 查询总部/院校退中心费用集合
	 * @param refundBranch
	 * @param student
	 * @param feePayment
	 * @param starttime
	 * @param endtime
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<RefundBranch> findRefundBranchPageList(RefundBranch refundBranch,Student student,FeePayment feePayment,String starttime,String endtime,PageResult<RefundBranch> pr)throws Exception;
	
	/**
	 * 查询总部/院校退中心退费总和
	 * @param refundBranch
	 * @param student
	 * @param feePayment
	 * @param starttime
	 * @param endtime
	 * @return
	 * @throws Exception
	 */
	public double findPaymentSum(RefundBranch refundBranch,Student student,FeePayment feePayment,String starttime,String endtime)throws Exception;
	
	/**
	 * 批量修改费用状态
	 * @param ids
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public boolean updateRefundBranchStatusByIds(String ids,int status,int userId)throws Exception;
	
	/**
	 * 查询ids总退费费用
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public double findRefundBranchSumPaymentByIds(String ids)throws Exception;
	
	/**
	 * 根据条件查询费用表集合
	 * @param refundBranch
	 * @return
	 * @throws Exception
	 */
	public List<RefundBranch> findRefundBranchByParam(RefundBranch refundBranch)throws Exception;
	
	/**
	 * 根据ids查询费用确认集合
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<RefundBranch> findRefundBranchByIds(String ids)throws Exception;
}
