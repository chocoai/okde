package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.RefundAcademyCedu;
import net.cedu.model.page.PageResult;


/**
 * 院校替总部垫付退费
 * 
 * @author xiao
 *
 */
public interface RefundAcademyCeduBiz 
{

	/**
	 * 添加院校替总部垫付退费
	 * @param refundAcademyCedu 
	 * @return
	 * @throws Exception
	 */
	public boolean addRefundAcademyCedu(RefundAcademyCedu refundAcademyCedu) throws Exception;
	
	/**
	 * 删除院校替总部垫付退费
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRefundAcademyCeduById(int id) throws Exception;
	
	/**
	 * 修改院校替总部垫付退费
	 * @param refundAcademyCedu
	 * @return
	 * @throws Exception
	 */
	public boolean updateRefundAcademyCedu(RefundAcademyCedu refundAcademyCedu) throws Exception;
	
	/**
	 * 根据Id查找院校替总部垫付退费
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RefundAcademyCedu findRefundAcademyCeduById(int id)throws Exception;
	
	/**
	 * 查询院校替总部垫付退费数量
	 * @param refundAcademyCedu
	 * @param student
	 * @param feePayment
	 * @param starttime
	 * @param endtime
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findRefundAcademyCeduPageCount(RefundAcademyCedu refundAcademyCedu,Student student,FeePayment feePayment,String starttime,String endtime,PageResult<RefundAcademyCedu> pr)throws Exception;
	
	/**
	 * 查询院校替总部垫付退费集合
	 * @param refundAcademyCedu
	 * @param student
	 * @param feePayment
	 * @param starttime
	 * @param endtime
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<RefundAcademyCedu> findRefundAcademyCeduPageList(RefundAcademyCedu refundAcademyCedu,Student student,FeePayment feePayment,String starttime,String endtime,PageResult<RefundAcademyCedu> pr)throws Exception;
	
	/**
	 * 查询院校替总部垫付退费总和
	 * @param refundAcademyCedu
	 * @param student
	 * @param feePayment
	 * @param starttime
	 * @param endtime
	 * @return
	 * @throws Exception
	 */
	public double findPaymentSum(RefundAcademyCedu refundAcademyCedu,Student student,FeePayment feePayment,String starttime,String endtime)throws Exception;
}
