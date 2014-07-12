package net.cedu.biz.finance;

import java.util.Date;
import java.util.List;

import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderBranchCedu;
import net.cedu.model.page.PageResult;

/**
 * 汇款总部单  业务接口
 * 
 * @author Sauntor
 *
 */
public interface OrderBranchCeduBiz {
	
	/**
	 * 添加汇款总部单
	 * @param entity 汇款总部单
	 * @param fpdList 缴费单明细
	 * @param userId 操作用户ID
	 * @return 添加成功时返回ID
	 * @throws Exception
	 */
	public int add(OrderBranchCedu entity, List<FeePaymentDetail> fpdList, int userId) throws Exception;
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public void update(OrderBranchCedu entity) throws Exception;
	
	/**
	 * 修改汇款总部单
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrderBranchCedu(OrderBranchCedu order) throws Exception;
	
	/**
	 * 根据ID查询
	 * @return
	 * @throws Exception
	 */
	public OrderBranchCedu findById(int id) throws Exception;
	
	/**
	 * 条件查询
	 * @param order 汇款总部单条件
	 * @param from 汇款日期下限
	 * @param to 汇款日期上限
	 * @param pr 分页载体（分页）
	 * @return
	 * @throws Exception
	 */
	public List<OrderBranchCedu> find(OrderBranchCedu order, Date from, Date to,String amount, PageResult<OrderBranchCedu> pr) throws Exception;
	
	/**
	 * 条件查询数量（分页）
	 * @param order 汇款总部单条件
	 * @param from 汇款日期下限
	 * @param to 汇款日期上限
	 * @return
	 * @throws Exception
	 */
	public int countOrderBranchCeduByPage(OrderBranchCedu order, Date from, Date to ,String amount) throws Exception;
	
	/**
	 * 查询外键
	 * @param list
	 * @throws Exception
	 */
	public void wrap(List<OrderBranchCedu> list) throws Exception;
	
	/**
	 * 根据ID删除<b>汇款总部单</b>
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception;
	
	/**
	 * 根据删除汇款总部单和更新其相关的缴费单明细
	 * @param orderBranchCeduId  汇款单总部Id
	 * @param fpdList 缴费单明细集合
	 * @return
	 * @throws Exception
	 */
	public void deleteOrderBranchCeduUpdateFeePaymentDetails(int orderBranchCeduId,List<FeePaymentDetail> fpdList) throws Exception;
	
	/**
	 * 更新汇款单状态（并更新对应缴费单的状态）
	 * @param order 汇款总部单
	 * @param fpdList 缴费单明细
	 * @throws Exception
	 */
	public void updateStatus(OrderBranchCedu order, List<FeePaymentDetail> fpdList) throws Exception;
	
	/**
	 * 更新汇款单状态（并更新对应缴费单的状态）
	 * @param order 汇款总部单
	 * @param fpdList 缴费单明细
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrderAncFpdStatus(OrderBranchCedu order, List<FeePaymentDetail> fpdList) throws Exception;
	
	
	/**
	 * 更新汇款单状态（并更新对应缴费单的状态及其退费单明细(退费单明细状态为审批过待确认)状态、重新计算缴费单明细各个账户的值和重新分配退费单明细归属问题）
	 * @param order  汇款单实体
	 * @param fpdList  汇款单对应的缴费单明细实体集合
	 * @param refundfpdList 缴费单明细对应的退费单明细实体集合
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrderBranchCeduFpdListRefundfpdList(OrderBranchCedu order, List<FeePaymentDetail> fpdList,List<FeePaymentDetail> refundfpdList) throws Exception;
	
	/**
	 * 添加汇款单
	 * @param orderBranchCedu  汇款单实体
	 * @return
	 * @throws Exception
	 */
	public boolean addOrderBranchCedu(OrderBranchCedu orderBranchCedu) throws Exception;
	
	/**
	 * 添加汇款单更新缴费单明细集合状态
	 * @param orderBranchCedu 汇款单实体
	 * @param feePaymentDetailList  缴费单明细集合
	 * @return
	 * @throws Exception
	 */
	public boolean addOrderBranchCeduUpdateFeePaymentDetails(OrderBranchCedu orderBranchCedu,List<FeePaymentDetail> feePaymentDetailList) throws Exception;
	
	/**
	 * 统计汇款单总金额
	 * @param order 汇款单
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param amount 汇款金额
	 * @return
	 * @throws Exception
	 */
	public String countOrderBranchCeduAllMoneyByConditions(OrderBranchCedu order,String starttime,String endtime,String amount) throws Exception;
	
}
