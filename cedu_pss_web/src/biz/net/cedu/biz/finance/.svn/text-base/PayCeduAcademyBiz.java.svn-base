package net.cedu.biz.finance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayCeduAcademy;
import net.cedu.model.common.Range;
import net.cedu.model.page.PageResult;


/**
 * 院校打款单 业务接口
 * 
 * @author Sauntor
 *
 */
public interface PayCeduAcademyBiz
{
	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PayCeduAcademy findById(int id) throws Exception;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int add(PayCeduAcademy entity) throws Exception;
	
	/**
	 * 根据ID删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception;
	
	/**
	 * 更新
	 * @param entity
	 * @throws Exception
	 */
	public void updateById(PayCeduAcademy entity) throws Exception;
	
	/**
	 * 更新打款单
	 * @param pca
	 * @return
	 * @throws Exception
	 */
	public boolean updatePayCeduAcademy(PayCeduAcademy pca) throws Exception;
	
	/**
	 * 分页查询
	 * 
	 * @param example 条件
	 * @param remittanceDateRange 返款日期范围
	 * @param result
	 * @param searchCase 查询的使用情形（用于什么目的）
	 * @return
	 * @throws Exception
	 */
	public List<PayCeduAcademy> find(PayCeduAcademy example, Range<Date> remittanceDateRange, PageResult<PayCeduAcademy> result, SearchCase searchCase) throws Exception;
	
	/**
	 * 条件查询
	 * 
	 * @param example 条件
	 * @param remittanceDateRange 返款日期范围
	 * @param result
	 * @param searchCase 查询用于什么目的
	 * @return
	 * @throws Exception
	 */
	public int count(PayCeduAcademy example, Range<Date> remittanceDateRange, PageResult<PayCeduAcademy> result, SearchCase searchCase) throws Exception;
	
	/**
	 * 计算院校打款单院校应返款
	 * @param payCeduAcademy 院校打款单
	 * @return 已分组计算的缴费单明细及其适用政策
	 * @throws Exception
	 */
	public Map<AcademyRebatePolicyDetail, List<FeePaymentDetail>> calculateMoneyToCedu(PayCeduAcademy payCeduAcademy) throws Exception;
	
	/**
	 * 查询目的 枚举，表示查询打款院校单所应用于的情况/目的
	 * @author Sauntor
	 *
	 */
	public enum SearchCase
	{
		AddPayAcademyCedu, //添加院校返款单
		ViewPayAcademyCedu //查看院校返款单
	}
	
	/**
	 * 查询打款单数量
	 * @author xiao
	 * @param payCeduAcademy
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findPayCeduAcademyCountByDetails(PayCeduAcademy payCeduAcademy,String starttime,String endtime,int status,String amount, PageResult<PayCeduAcademy> pr) throws Exception;
	
	/**
	 * 查询打款单列表
	 * @author xiao 
	 * @param payCeduAcademy
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<PayCeduAcademy> findPayCeduAcademyListByDetails(PayCeduAcademy payCeduAcademy,String starttime,String endtime,int status,String amount, PageResult<PayCeduAcademy> pr) throws Exception;
	
	/**
	 * 新增打款单及其修改相应的缴费单明细和缴费单
	 * @param entity  打款单实体
	 * @param feePaymentDetailList 缴费单明细集合
	 * @param feePaymentList 缴费单集合
	 * @return 
	 * @throws Exception
	 */
	public boolean addPayCeduAcademyFeePaymentDetails(PayCeduAcademy entity,List<FeePaymentDetail> feePaymentDetailList,List<FeePayment> feePaymentList) throws Exception;
	
	/**
	 * 新增打款单及其修改相应的缴费单明细(相应缴费单暂时不做修改)
	 * @param entity  打款单实体
	 * @param feePaymentDetailList  缴费单明细集合
	 * @return
	 * @throws Exception
	 */
	public boolean addPayCeduAcademyUpdateFeePaymentDetails(PayCeduAcademy entity,List<FeePaymentDetail> feePaymentDetailList) throws Exception;
	
	/**
	 * 删除打款单及其修改相应的缴费单明细
	 * @param payCeduAcademyId 打款单Id
	 * @param feePaymentDetailList 缴费单明细集合
	 * @return
	 * @throws Exception
	 */
	public boolean deletePayCeduAcademyUpdateFeePaymentDetails(int payCeduAcademyId,List<FeePaymentDetail> feePaymentDetailList) throws Exception;
	
	/**
	 * 更新打款单及其相应的缴费单明细
	 * @param payCeduAcademy  打款单实体
	 * @param feePaymentDetailList  缴费单明细集合
	 * @return
	 * @throws Exception
	 */
	public boolean updatePayCeduAcademyUpdateFeePaymentDetails(PayCeduAcademy payCeduAcademy,List<FeePaymentDetail> feePaymentDetailList) throws Exception;
	
	/**
	 * 更新打款单及其相应的缴费单明细和缴费单明细对应的退费单明细（退费单明细状态为审核通过待确认的）---方法有问题    session报错   别用
	 * @param payCeduAcademy 打款单实体
	 * @param feePaymentDetailList 缴费单明细集合
	 * @param refundFpdList 退费单明细实体集合
	 * @return
	 * @throws Exception
	 */
	public boolean updatePayCeduAcademyFeePaymentDetailAndRefundfpdlist(PayCeduAcademy payCeduAcademy,List<FeePaymentDetail> feePaymentDetailList,List<FeePaymentDetail> refundFpdList) throws Exception;
	
	/**
	 * 统计院校打款单总金额
	 * @param payCeduAcademy 院校打款单实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param status 备用状态查询条件
	 * @param amount 打款金额
	 * @return
	 * @throws Exception
	 */
	public String countPayCeduAcademyAllMoneyByConditions(PayCeduAcademy payCeduAcademy,String starttime,String endtime,int status,String amount) throws Exception;
	
}
