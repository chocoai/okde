package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.RefundCeduAcademy;
import net.cedu.model.page.PageResult;


/**
 * 总部替院校垫付退费
 * 
 * @author xiao
 *
 */
public interface RefundCeduAcademyBiz
{
	
	/**
	 * 添加总部替院校垫付退费
	 * @param refundCeduAcademy 
	 * @return
	 * @throws Exception
	 */
	public boolean addRefundCeduAcademy(RefundCeduAcademy refundCeduAcademy) throws Exception;
	
	/**
	 * 删除总部替院校垫付退费
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRefundCeduAcademyById(int id) throws Exception;
	
	/**
	 * 修改总部替院校垫付退费
	 * @param refundCeduAcademy
	 * @return
	 * @throws Exception
	 */
	public boolean updateRefundCeduAcademy(RefundCeduAcademy refundCeduAcademy) throws Exception;
	
	/**
	 * 根据Id查找总部替院校垫付退费
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RefundCeduAcademy findRefundCeduAcademyById(int id)throws Exception;
	
	/**
	 * 查询总部替院校垫付退费数量
	 * @param refundAcademyCedu
	 * @param student
	 * @param feePayment
	 * @param starttime
	 * @param endtime
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findRefundCeduAcademyPageCount(RefundCeduAcademy refundCeduAcademy,Student student,FeePayment feePayment,String starttime,String endtime,PageResult<RefundCeduAcademy> pr)throws Exception;
	
	/**
	 * 查询总部替院校垫付退费集合
	 * @param refundCeduAcademy
	 * @param student
	 * @param feePayment
	 * @param starttime
	 * @param endtime
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<RefundCeduAcademy> findRefundCeduAcademyPageList(RefundCeduAcademy refundCeduAcademy,Student student,FeePayment feePayment,String starttime,String endtime,PageResult<RefundCeduAcademy> pr)throws Exception;
	
	/**
	 * 查询总部替院校垫付退费总和
	 * @param refundCeduAcademy
	 * @param student
	 * @param feePayment
	 * @param starttime
	 * @param endtime
	 * @return
	 * @throws Exception
	 */
	public double findPaymentSum(RefundCeduAcademy refundCeduAcademy,Student student,FeePayment feePayment,String starttime,String endtime)throws Exception;
}
