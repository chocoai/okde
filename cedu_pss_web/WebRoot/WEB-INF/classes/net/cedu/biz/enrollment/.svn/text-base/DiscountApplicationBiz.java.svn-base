package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.DiscountApplication;
import net.cedu.model.page.PageResult;

/**
 * 优惠卷(优惠申请)
 * 
 * @author lixiaojun
 * 
 */
public interface DiscountApplicationBiz 
{
	/**
	 * 根据Id查找优惠卷
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DiscountApplication findDiscountApplicationById(int id)throws Exception;
	
	/**
	 * 添加优惠卷
	 * @param policyFee
	 * @return
	 * @throws Exception
	 */
	public boolean addDiscountApplication(DiscountApplication discountApplication) throws Exception;
	
	/**
	 * 批量添加优惠卷
	 * @param discountlist
	 * @return
	 * @throws Exception
	 */
	public boolean addBatchDiscountApplication(List<DiscountApplication> discountlist) throws Exception;
	
	/**
	 * 删除优惠卷(读取配置文件)
	 * @param 
	 */
	public boolean deleteConfigDiscountApplicationById(int id) throws Exception;
	
//	/**
//	 * 删除优惠卷(物理删除)
//	 * @param 
//	 */
//	public boolean deleteDiscountApplicationById(int id) throws Exception;
//	
//	/**
//	 * 删除优惠卷(逻辑删除)
//	 * @param 
//	 */
//	public boolean deleteDiscountApplicationFlag(int id) throws Exception;
	
	/**
	 * 修改优惠卷
	 * @param feeBatch
	 */
	public boolean updateDiscountApplication(DiscountApplication discountApplication) throws Exception;
	
	/**
	 * 获取优惠卷数量
	 * @param student
	 * @param discountApplication
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findDiscountApplicationCountByDetails(Student student,DiscountApplication discountApplication,PageResult<DiscountApplication> pr) throws Exception;
	
	/**
	 * 获取优惠卷列表
	 * @param student
	 * @param discountApplication
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<DiscountApplication> findDiscountApplicationListByDetails(Student student,DiscountApplication discountApplication,PageResult<DiscountApplication> pr) throws Exception;

	/**
	 * 根据Id查找优惠卷详细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DiscountApplication findDiscountApplicationDetailsById(int id)throws Exception;
	
	/**
	 * 根据学生Id和优惠标准Id查询优惠卷的数量
	 * @param studentId
	 * @param discountPolicyId
	 * @return
	 * @throws Exception
	 */
	public int findDiscountApplicationcountsByStudentIdAndDiscountPolicyId(int studentId,int discountPolicyId)throws Exception;
	
	/**
	 * 根据优惠标准Id和使用状态查询优惠卷的数量
	 * @param discountPolicyId
	 * @return
	 * @throws Exception
	 */
	public int findDaCountForHasUseByDpId(int discountPolicyId ,int status)throws Exception;
	
	/**
	 * 根据学生Id和使用状态查询所有优惠卷
	 * @param studentId
	 * @param discountPolicyId
	 * @return  返回的是所有优惠标准
	 * @throws Exception
	 */
	public List<DiscountApplication> findDiscountApplicationByStudentId(int studentId,int usageFlag)throws Exception;
	
	/**
	 * 根据优惠标准Id查询相关的优惠卷
	 * @param discountPolicyId
	 * @return
	 * @throws Exception
	 */
	public List<DiscountApplication> findDiscountApplicationListByDiscountPolicyId(int discountPolicyId)throws Exception;
	
	/**
	 * 根据学生Id、费用科目Id、缴费次数查询当前时间下该学生能够使用的优惠卷
	 * @param studentId
	 * @param feeSubjectId
	 * @param feePaymentId
	 * @return
	 * @throws Exception
	 */
	public List<DiscountApplication> findDiscountApplicationListByStudentIdFeeSubjectIdFeePaymentId(int studentId,int feeSubjectId,int feePaymentId)throws Exception;
	
}
