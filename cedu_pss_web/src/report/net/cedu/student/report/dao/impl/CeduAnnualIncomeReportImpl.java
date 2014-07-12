package net.cedu.student.report.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.common.string.MoneyUtil;
import net.cedu.dao.academy.AcademyDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.academy.Academy;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.CeduAnnualIncomeReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * 年收入情况统计表（院校返款）
 * @author yangdongdong
 * （重构） dongminghao
 *
 */
@Repository
public class CeduAnnualIncomeReportImpl  extends BaseReportDaoImpl  implements CeduAnnualIncomeReport {

	@Autowired
	private AcademyDao academyDao;
	
	private  JdbcTemplatePlus jdbcTemplatePlus = null;
	@SuppressWarnings({ "unchecked" })
	public Map statistics(Map<String, Integer> params,Map<String, String> strParams,Map<String, Date> dateParams) {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		
		final int branchId = params.get("branch");// 学习中心
		final int school = params.get("school");// 学院
		final int gbatch = params.get("batch");// 全局批次
		final String feeSubjectIds = strParams.get("feeSubjectIds");// 费用科目ids
		
		final Date startDate=dateParams.get("startDate");//开始日期
		final Date endDate=dateParams.get("endDate");//结束日期
		
		Map<String,Object> mapResult=new HashMap<String,Object>();
		mapResult.put("start_date", DateUtil.getDate(startDate, "yyyy-MM-dd"));
		mapResult.put("end_date", DateUtil.getDate(endDate, "yyyy-MM-dd"));
		
		//学习中心ID_=院校IDs
		final Map<String,List<Integer>> key_a_value_bids_map=initBranchAcademys(school, gbatch, branchId);
		
		//缴费人数 key:学习中心id_院校id value:缴费人数
		final Map<String,Integer> jiaofeiCountMap = getJiaoFeiCount(school, gbatch, branchId, startDate, endDate);
		
		// 应返_返款单明细 key:学习中心Id_院校Id_费用科目Id value:金额
		final Map<String,BigDecimal> yingfan_feePaymentDetailMap = getFeePaymentDetail(branchId, school, gbatch, 0, startDate, endDate, feeSubjectIds);
		// 应返_院校返款追加记录 key:学习中心Id_院校Id_费用科目Id value:金额
		final Map<String,BigDecimal> yingfan_academyRebateAddRecordsMap = getAcademyRebateAddRecords(branchId, school, gbatch, 0, startDate, endDate, feeSubjectIds);
		// 应返_院校返款调整金额分配给学习中心 key:学习中心Id_院校Id value:金额
		final Map<String,BigDecimal> yingfan_academyRebateFenPeiBranchMap = getAcademyRebateFenPeiBranch(branchId, school, gbatch, 0, startDate, endDate, feeSubjectIds);
		// 应返_应收院校款 key:学习中心Id_院校Id value:金额
		final Map<String,BigDecimal> yingfan_planedAcademyBillMap = getPlanedAcademyBill(branchId, school, gbatch, 0, startDate, endDate, feeSubjectIds);
		
		// 实返_返款单明细 key:学习中心Id_院校Id_费用科目Id value:金额
		final Map<String,BigDecimal> shifan_feePaymentDetailMap = getFeePaymentDetail(branchId, school, gbatch, Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN, startDate, endDate, feeSubjectIds);
		// 实返_院校返款追加记录 key:学习中心Id_院校Id_费用科目Id value:金额
		final Map<String,BigDecimal> shifan_academyRebateAddRecordsMap = getAcademyRebateAddRecords(branchId, school, gbatch, Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN, startDate, endDate, feeSubjectIds);
		// 实返_院校返款调整金额分配给学习中心 key:学习中心Id_院校Id value:金额
		final Map<String,BigDecimal> shifan_academyRebateFenPeiBranchMap = getAcademyRebateFenPeiBranch(branchId, school, gbatch, Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN, startDate, endDate, feeSubjectIds);
		// 实返_应收院校款 key:学习中心Id_院校Id value:金额
		final Map<String,BigDecimal> shifan_planedAcademyBillMap = getPlanedAcademyBill(branchId, school, gbatch, Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN, startDate, endDate, feeSubjectIds);
		// 费用科目idList
		final List<Integer> feeSubjectIdsList = new ArrayList<Integer>();
		if(feeSubjectIds!=null && !feeSubjectIds.equals("")){
			String[] idStrs = feeSubjectIds.split(",");
			if(idStrs!=null && idStrs.length>0){
				for(String idStr : idStrs){
					if(idStr!=null && !idStr.equals("")){
						feeSubjectIdsList.add(getIntByString(idStr));
					}
				}
			}
		}
		// 合计
		final List<Integer> jiaofeiCountSumList=new ArrayList<Integer>();//缴费人数
		final Map<String,List<BigDecimal>> yingfanMoneySumList=new HashMap<String, List<BigDecimal>>();//应返_各费用科目费用
		final Map<String,List<BigDecimal>> shifanMoneySumList=new HashMap<String, List<BigDecimal>>();//应返_各费用科目费用
		final List<BigDecimal> yingfanTiaozhengHejiMoneySumList=new ArrayList<BigDecimal>();//应返_调整金额
		final List<BigDecimal> shifanTiaozhengHejiMoneySumList=new ArrayList<BigDecimal>();//实返_调整金额
		final List<BigDecimal> yingfanYingshouyuanxiaoHejiMoneySumList=new ArrayList<BigDecimal>();//应返_应收院校款
		final List<BigDecimal> shifanYingshouyuanxiaoHejiMoneySumList=new ArrayList<BigDecimal>();//实返_应收院校款
		final List<BigDecimal> yingfanHejiMoneySumList=new ArrayList<BigDecimal>();//应返_合计
		final List<BigDecimal> shifanHejiMoneySumList=new ArrayList<BigDecimal>();//实返_合计
		
		//生成%数
		final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
		format.setMinimumFractionDigits(2);// 设置小数位
		//金钱
		final DecimalFormat format_=new DecimalFormat();
		format_.setMinimumFractionDigits(2);
		//2.获取所有学习中心
		String branchSql=" select id,name from tb_e_branch where delete_flag="+Constants.DELETE_FALSE+" and id>"+BranchEnum.Admin.value()+" ";
		if(branchId!=-2){
			branchSql+=" and id="+branchId;
		}
		List branchList = jdbcTemplatePlus.query(branchSql, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int index)throws SQLException {
				Map<String, Object> branchMap = new HashMap<String, Object>();
				branchMap.put("branch_id", resultSet.getInt("id"));
				branchMap.put("branch_name", resultSet.getString("name"));
				
				//3,获取所有学习中心下的院校（如果为空，查询历史所有的院校）
				List resultAcademyList=new ArrayList();
				List<Integer> academyIdList=key_a_value_bids_map.get(resultSet.getInt("id")+"_");
				
				final List<Integer> branch_jiaofeiCountSumList=new ArrayList<Integer>();
				final Map<String,List<BigDecimal>> branch_yingfanMoneySumList=new HashMap<String, List<BigDecimal>>();
				final Map<String,List<BigDecimal>> branch_shifanMoneySumList=new HashMap<String, List<BigDecimal>>();
				final List<BigDecimal> branch_yingfanTiaozhengHejiMoneySumList=new ArrayList<BigDecimal>();//应返_调整金额
				final List<BigDecimal> branch_shifanTiaozhengHejiMoneySumList=new ArrayList<BigDecimal>();//实返_调整金额
				final List<BigDecimal> branch_yingfanYingshouyuanxiaoHejiMoneySumList=new ArrayList<BigDecimal>();//应返_应收院校款
				final List<BigDecimal> branch_shifanYingshouyuanxiaoHejiMoneySumList=new ArrayList<BigDecimal>();//实返_应收院校款
				final List<BigDecimal> branch_yingfanHejiMoneySumList=new ArrayList<BigDecimal>();
				final List<BigDecimal> branch_shifanHejiMoneySumList=new ArrayList<BigDecimal>();
				
				Map<String, Object> academyMap=null;
				Academy academy=null;
				if(academyIdList!=null){
					for (Integer academyId : academyIdList) {
						//academyMap.put("date_", new Date());
						
						academyMap = new HashMap<String, Object>();
						academyMap.put("academy_id", academyId);
						// 名称
						academy=academyDao.findById(academyId);
						if(academy!=null){
							academyMap.put("academy_name", academy.getName());
						}else{
							academyMap.put("academy_name", "--");
						}
						
						BigDecimal yingfan_sum = null;
						BigDecimal shifan_sum = null;
						
						// 缴费人数
						int jiaofeiCount = getIntByInteger(jiaofeiCountMap.get(academyId+"_"+resultSet.getInt("id")));
						academyMap.put("jiaofeiCount", jiaofeiCount);
						// (计算)合计
						jiaofeiCountSumList.add(jiaofeiCount);
						branch_jiaofeiCountSumList.add(jiaofeiCount);
						
						// 费用科目
						if(feeSubjectIdsList!=null && feeSubjectIdsList.size()>0){
							for(Integer tempInt : feeSubjectIdsList){
								// 应返_费用明细
								BigDecimal yingfan_mingxi = yingfan_feePaymentDetailMap.get(resultSet.getInt("id")+"_"+academyId+"_"+tempInt);
//								yingfan_mingxi = getBigDecimalByNull(yingfan_mingxi);
								// 应返_追加记录
								BigDecimal yingfan_zhuijia = yingfan_academyRebateAddRecordsMap.get(resultSet.getInt("id")+"_"+academyId+"_"+tempInt);
//								yingfan_zhuijia = getBigDecimalByNull(yingfan_zhuijia);
								// 实返_费用明细
								BigDecimal shifan_mingxi = shifan_feePaymentDetailMap.get(resultSet.getInt("id")+"_"+academyId+"_"+tempInt);
//								shifan_mingxi = getBigDecimalByNull(shifan_mingxi);
								// 实返_追加记录
								BigDecimal shifan_zhuijia = shifan_academyRebateAddRecordsMap.get(resultSet.getInt("id")+"_"+academyId+"_"+tempInt);
//								shifan_zhuijia = getBigDecimalByNull(shifan_zhuijia);
								// 实返/应返 （明细+追加）
								academyMap.put("feeSubject_"+tempInt, getStringByBigDecimal(getBigDecimalAdd(shifan_mingxi,shifan_zhuijia))+"/"
																		+getStringByBigDecimal(getBigDecimalAdd(yingfan_mingxi,yingfan_zhuijia)));
								
								// (计算)合计
								yingfan_sum = getBigDecimalAdd(yingfan_sum,yingfan_mingxi);
								yingfan_sum = getBigDecimalAdd(yingfan_sum,yingfan_zhuijia);
								shifan_sum = getBigDecimalAdd(shifan_sum,shifan_mingxi);
								shifan_sum = getBigDecimalAdd(shifan_sum,shifan_zhuijia);
								// (计算)总各费用科目
								// 应返
								if(branch_yingfanMoneySumList.containsKey("list_feeSubject_"+tempInt)){
									branch_yingfanMoneySumList.get("list_feeSubject_"+tempInt).add(getBigDecimalAdd(yingfan_mingxi,yingfan_zhuijia));
								}else{
									List<BigDecimal> bdList = new ArrayList<BigDecimal>();
									bdList.add(getBigDecimalAdd(yingfan_mingxi,yingfan_zhuijia));
									branch_yingfanMoneySumList.put("list_feeSubject_"+tempInt, bdList);
								}
								if(yingfanMoneySumList.containsKey("list_feeSubject_"+tempInt)){
									yingfanMoneySumList.get("list_feeSubject_"+tempInt).add(getBigDecimalAdd(yingfan_mingxi,yingfan_zhuijia));
								}else{
									List<BigDecimal> bdList = new ArrayList<BigDecimal>();
									bdList.add(getBigDecimalAdd(yingfan_mingxi,yingfan_zhuijia));
									yingfanMoneySumList.put("list_feeSubject_"+tempInt, bdList);
								}
								// 实返
								if(branch_shifanMoneySumList.containsKey("list_feeSubject_"+tempInt)){
									branch_shifanMoneySumList.get("list_feeSubject_"+tempInt).add(getBigDecimalAdd(shifan_mingxi,shifan_zhuijia));
								}else{
									List<BigDecimal> bdList = new ArrayList<BigDecimal>();
									bdList.add(getBigDecimalAdd(shifan_mingxi,shifan_zhuijia));
									branch_shifanMoneySumList.put("list_feeSubject_"+tempInt, bdList);
								}
								if(shifanMoneySumList.containsKey("list_feeSubject_"+tempInt)){
									shifanMoneySumList.get("list_feeSubject_"+tempInt).add(getBigDecimalAdd(shifan_mingxi,shifan_zhuijia));
								}else{
									List<BigDecimal> bdList = new ArrayList<BigDecimal>();
									bdList.add(getBigDecimalAdd(shifan_mingxi,shifan_zhuijia));
									shifanMoneySumList.put("list_feeSubject_"+tempInt, bdList);
								}
								
							}
						}
						// 应返_调整金额
						BigDecimal yingfan_tiaozheng = yingfan_academyRebateFenPeiBranchMap.get(resultSet.getInt("id")+"_"+academyId);
//						yingfan_tiaozheng = getBigDecimalByNull(yingfan_tiaozheng);
						branch_yingfanTiaozhengHejiMoneySumList.add(yingfan_tiaozheng);
						yingfanTiaozhengHejiMoneySumList.add(yingfan_tiaozheng);
						// 应返_应收院校
						BigDecimal yingfan_yuanxiao = yingfan_planedAcademyBillMap.get(resultSet.getInt("id")+"_"+academyId);
//						yingfan_yuanxiao = getBigDecimalByNull(yingfan_yuanxiao);
						branch_yingfanYingshouyuanxiaoHejiMoneySumList.add(yingfan_yuanxiao);
						yingfanYingshouyuanxiaoHejiMoneySumList.add(yingfan_yuanxiao);
						// 实返_调整金额
						BigDecimal shifan_tiaozheng = shifan_academyRebateFenPeiBranchMap.get(resultSet.getInt("id")+"_"+academyId);
//						shifan_tiaozheng = getBigDecimalByNull(shifan_tiaozheng);
						branch_shifanTiaozhengHejiMoneySumList.add(shifan_tiaozheng);
						shifanTiaozhengHejiMoneySumList.add(shifan_tiaozheng);
						// 实返_应收院校
						BigDecimal shifan_yuanxiao = shifan_planedAcademyBillMap.get(resultSet.getInt("id")+"_"+academyId);
//						shifan_yuanxiao = getBigDecimalByNull(shifan_yuanxiao);
						branch_shifanYingshouyuanxiaoHejiMoneySumList.add(shifan_yuanxiao);
						shifanYingshouyuanxiaoHejiMoneySumList.add(shifan_yuanxiao);
						// 调整金额 实返/应返
						academyMap.put("tiaozhengSum", getStringByBigDecimal(shifan_tiaozheng)+"/"
														+getStringByBigDecimal(yingfan_tiaozheng));
						// 应收院校 实返/应返
						academyMap.put("yingshouyuanxiaoSum", getStringByBigDecimal(shifan_yuanxiao)+"/"
								+getStringByBigDecimal(yingfan_yuanxiao));
						
						yingfan_sum = getBigDecimalAdd(yingfan_sum,yingfan_tiaozheng);
						yingfan_sum = getBigDecimalAdd(yingfan_sum,yingfan_yuanxiao);
						shifan_sum = getBigDecimalAdd(shifan_sum,shifan_tiaozheng);
						shifan_sum = getBigDecimalAdd(shifan_sum,shifan_yuanxiao);
						
						// 应返合计
						academyMap.put("yingfan_sum", getStringByBigDecimal(yingfan_sum));
						branch_yingfanHejiMoneySumList.add(yingfan_sum);
						yingfanHejiMoneySumList.add(yingfan_sum);
						// 实返合计
						academyMap.put("shifan_sum", getStringByBigDecimal(shifan_sum));
						branch_shifanHejiMoneySumList.add(shifan_sum);
						shifanHejiMoneySumList.add(shifan_sum);
						// 未返合计(应返合计-实返合计)
						academyMap.put("weifan_sum", getStringByBigDecimal(getBigDecimalSubtract(yingfan_sum,shifan_sum)));
						
						// 其他费用(暂无作用)
						academyMap.put("otherMoney", "--/--");
						
						resultAcademyList.add(academyMap);
					}
				}
				//中心没有对应院校
				else{
					academyMap = new HashMap<String, Object>();
					// 名称
					academyMap.put("academy_name", "无授权院校");
					// 缴费人数
					academyMap.put("jiaofeiCount", "0");
					// 费用科目
					if(feeSubjectIdsList!=null && feeSubjectIdsList.size()>0){
						for(Integer tempInt : feeSubjectIdsList){
							// 实返/应返 （明细+追加）
							academyMap.put("feeSubject_"+tempInt, "--/--");
						}
					}
					// 调整金额 实返/应返
					academyMap.put("tiaozhengSum", "--/--");
					// 应收院校 实返/应返
					academyMap.put("yingshouyuanxiaoSum", "--/--");
					// 应返合计
					academyMap.put("yingfan_sum", "--");
					// 实返合计
					academyMap.put("shifan_sum", "--");
					// 未返合计(应返合计-实返合计)
					academyMap.put("weifan_sum", "--");
					// 其他费用(暂无作用)
					academyMap.put("otherMoney", "--/--");
					
					resultAcademyList.add(academyMap);
					
				}
				// 总缴费人数
				int b_jiaofeiCount = getIntListSum(branch_jiaofeiCountSumList);
				branchMap.put("branch_jiaofeiCount", b_jiaofeiCount);
				// 总各费用科目
				if(feeSubjectIdsList!=null && feeSubjectIdsList.size()>0){
					for(Integer tempInt : feeSubjectIdsList){
						branchMap.put("branch_feeSubject_"+tempInt, getStringByBigDecimal(getBigDecimalListSum(branch_shifanMoneySumList.get("list_feeSubject_"+tempInt)))+"/" 
																	+getStringByBigDecimal(getBigDecimalListSum(branch_yingfanMoneySumList.get("list_feeSubject_"+tempInt))));
					}
				}
				// 总调整金额
				BigDecimal b_yingfan_tiaozhengSum = getBigDecimalListSum(branch_yingfanTiaozhengHejiMoneySumList);
				BigDecimal b_shifan_tiaozhengSum = getBigDecimalListSum(branch_shifanTiaozhengHejiMoneySumList);
				branchMap.put("branch_tiaozhengSum", getStringByBigDecimal(b_shifan_tiaozhengSum)+"/"+getStringByBigDecimal(b_yingfan_tiaozhengSum));
				// 总应收院校款
				BigDecimal b_yingfan_yingshouyuanxiaoSum = getBigDecimalListSum(branch_yingfanYingshouyuanxiaoHejiMoneySumList);
				BigDecimal b_shifan_yingshouyuanxiaoSum = getBigDecimalListSum(branch_shifanYingshouyuanxiaoHejiMoneySumList);
				branchMap.put("branch_yingshouyuanxiaoSum", getStringByBigDecimal(b_shifan_yingshouyuanxiaoSum)+"/"+getStringByBigDecimal(b_yingfan_yingshouyuanxiaoSum));
				// 总应返
				BigDecimal b_yingfanSum = getBigDecimalListSum(branch_yingfanHejiMoneySumList);
				branchMap.put("branch_yingfanSum", getStringByBigDecimal(b_yingfanSum));
				// 总实返
				BigDecimal b_shifanSum = getBigDecimalListSum(branch_shifanHejiMoneySumList);
				branchMap.put("branch_shifanSum", getStringByBigDecimal(b_shifanSum));
				// 总未返
				BigDecimal b_weifanSum = getBigDecimalSubtract(b_yingfanSum,b_shifanSum);
				branchMap.put("branch_weifanSum", getStringByBigDecimal(b_weifanSum));
				
				// 总其他费用(暂无作用)
				branchMap.put("branch_otherMoney", "--/--");
				
				branchMap.put("branch_academy_list", resultAcademyList);
				return branchMap;
			}
		});
		
		// 全部中心合计
		
		// 缴费人数
		int all_jiaofeiCount = getIntListSum(jiaofeiCountSumList);
		mapResult.put("all_jiaofeiCount", all_jiaofeiCount);
		// 各费用科目
		if(feeSubjectIdsList!=null && feeSubjectIdsList.size()>0){
			for(Integer tempInt : feeSubjectIdsList){
				mapResult.put("all_feeSubject_"+tempInt, getStringByBigDecimal(getBigDecimalListSum(shifanMoneySumList.get("list_feeSubject_"+tempInt)))+"/" 
															+getStringByBigDecimal(getBigDecimalListSum(yingfanMoneySumList.get("list_feeSubject_"+tempInt))));
			}
		}
		// 调整金额
		BigDecimal all_yingfan_tiaozhengSum = getBigDecimalListSum(yingfanTiaozhengHejiMoneySumList);
		BigDecimal all_shifan_tiaozhengSum = getBigDecimalListSum(shifanTiaozhengHejiMoneySumList);
		mapResult.put("all_tiaozhengSum", getStringByBigDecimal(all_shifan_tiaozhengSum)+"/"+getStringByBigDecimal(all_yingfan_tiaozhengSum));
		
		// 应收院校款
		BigDecimal all_yingfan_yingshouyuanxiaoSum = getBigDecimalListSum(yingfanYingshouyuanxiaoHejiMoneySumList);
		BigDecimal all_shifan_yingshouyuanxiaoSum = getBigDecimalListSum(shifanYingshouyuanxiaoHejiMoneySumList);
		mapResult.put("all_yingshouyuanxiaoSum", getStringByBigDecimal(all_shifan_yingshouyuanxiaoSum)+"/"+getStringByBigDecimal(all_yingfan_yingshouyuanxiaoSum));
		
		// 应返
		BigDecimal all_yingfanSum = getBigDecimalListSum(yingfanHejiMoneySumList);
		mapResult.put("all_yingfanSum", getStringByBigDecimal(all_yingfanSum));
		// 实返
		BigDecimal all_shifanSum = getBigDecimalListSum(shifanHejiMoneySumList);
		mapResult.put("all_shifanSum", getStringByBigDecimal(all_shifanSum));
		// 未返
		BigDecimal all_weifanSum = getBigDecimalSubtract(all_yingfanSum,all_shifanSum);
		mapResult.put("all_weifanSum", getStringByBigDecimal(all_weifanSum));
		
		// 其他费用(暂无作用)
		mapResult.put("all_otherMoney", "--/--");
		
		mapResult.put("branchList", branchList);
		return mapResult;
	}
	
	/*
	 * 根据状态，院校，起止日期，查询院校返款单ids
	 * 如果没有任何条件则返回""
	 */
//	private String getPayAcademyCedu(int status,int academyId,Date startDate,Date endDate){
//		String ids = "";
//		// 如果无条件则返回空（即查询全部）
//		if(status==0 && academyId==-2 && startDate==null && endDate ==null){
//			return ids;
//		}
//		String sql = " select id "
//					+" from tb_e_pay_academy_cedu "
//					+" where 1=1 ";
//		// 返款状态
//		if(status!=0){
//			sql += " and status = " + status;
//		}
//		// 院校
//		if(academyId!=-2){
//			sql += " and remitter = " + academyId;
//		}
//		// 返款单起止时间
//		if(startDate!=null){
//			sql += " and created_time >= '" + DateUtil.getDate(startDate, "yyyy-MM-dd") + " 00:00:00' ";
//		}
//		if(endDate!=null){
//			sql += " and created_time <= '" +DateUtil.getDate(endDate, "yyyy-MM-dd")+ " 23:59:59' ";
//		}
//		System.out.println(sql);
//		@SuppressWarnings("unchecked")
//		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
//				new RowMapper() {
//					public Map<String, Integer> mapRow(ResultSet resultSet,
//							int index) throws SQLException {
//						Map<String, Integer> map = new HashMap<String, Integer>();
//						map.put("id", resultSet.getInt("id"));
//						return map;
//					}
//				});
//		if(listResult!=null){
//			for (Map<String, Integer> map : listResult) {
//				if(ids.equals("")){
//					ids += map.get("id")+"";
//				}else{
//					ids += ","+map.get("id");
//				}
//			}
//		}
//		return ids;
//	}
	
	/**
	 * 根据状态，院校，起止日期，查询院校返款单的Sql语句
	 * 如果没有任何条件则返回""
	 * 此方法为了避免 in 太多导致堆栈溢出
	 */
	private String getPayAcademyCeduSql(int status,int academyId,Date startDate,Date endDate){
		String sql = "";
		// 如果无条件则返回空（即查询全部）
		if(status==0 && academyId==-2 && startDate==null && endDate ==null){
			return sql;
		}
		sql = " select id "
			+ " from tb_e_pay_academy_cedu "
			+ " where 1=1 ";
		// 返款状态
		if(status!=0){
			sql += " and status >= " + status;
		}
		// 院校
		if(academyId!=-2){
			sql += " and remitter = " + academyId;
		}
		// 返款单起止时间
		if(startDate!=null){
			sql += " and created_time >= '" + DateUtil.getDate(startDate, "yyyy-MM-dd") + " 00:00:00' ";
		}
		if(endDate!=null){
			sql += " and created_time <= '" +DateUtil.getDate(endDate, "yyyy-MM-dd")+ " 23:59:59' ";
		}
		return sql;
	}
	
	/**
	 * 根据条件查询返款单明细中各费用科目的金额
	 * @param branchId
	 * @param academyId
	 * @param batchId
	 * @param status
	 * @param startDate
	 * @param endDate
	 * @param feeSubjectIds
	 * @return Map(key:branchId_academyId_feeSubjectId value:sum_money)
	 */
	private Map<String,BigDecimal> getFeePaymentDetail(int branchId,int academyId,int batchId,int status,Date startDate,Date endDate,String feeSubjectIds){
		Map<String,BigDecimal> mapResult = new HashMap<String, BigDecimal>();
		String sql = " select s.branch_id,s.academy_id,d.fee_subject_id,IFNULL(sum(d.pay_academy_cedu),0) as sum_money "
					+" from tb_e_fee_payment_detail d,tb_e_student s "
					+" where 1=1 "
					+" and d.student_id = s.id "
					+" and d.pay_academy_cedu <> 0 "
					+" and d.order_academy_cedu_id > 0 "
					+" and d.status >= " + Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN;
		// 返款单sql
		String payAcademyCeduSql = getPayAcademyCeduSql(status,academyId,startDate,endDate);
		if(!payAcademyCeduSql.equals("")){
			sql += " and d.order_academy_cedu_id in ( " + payAcademyCeduSql + " ) ";
		}
		// 中心
		if(branchId!=-2){
			sql += " and s.branch_id = " + branchId;
		}
		// 批次
		if(batchId!=-2){
			sql += " and s.global_batch = " + batchId;
		}
		// 费用科目
		if(feeSubjectIds!=null && !feeSubjectIds.equals("")){
			sql += " and d.fee_subject_id in ( " + feeSubjectIds + " ) ";
		}
		sql += " group by s.branch_id,s.academy_id,d.fee_subject_id ";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("branch_id", resultSet.getString("branch_id"));
						map.put("academy_id", resultSet.getString("academy_id"));
						map.put("fee_subject_id", resultSet.getString("fee_subject_id"));
						map.put("sum_money", resultSet.getString("sum_money"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, String> map : listResult) {
				mapResult.put(map.get("branch_id")+"_"+map.get("academy_id")+"_"+map.get("fee_subject_id"),getBigDecimalByString(map.get("sum_money")));
			}
		}
		return mapResult;
	}
	
	/**
	 * 根据条件查询院校返款追加记录中各费用科目的金额
	 * @param branchId
	 * @param academyId
	 * @param batchId
	 * @param status
	 * @param startDate
	 * @param endDate
	 * @param feeSubjectIds
	 * @return Map(key:branchId_academyId_feeSubjectId value:sum_money)
	 */
	private Map<String,BigDecimal> getAcademyRebateAddRecords(int branchId,int academyId,int batchId,int status,Date startDate,Date endDate,String feeSubjectIds){
		Map<String,BigDecimal> mapResult = new HashMap<String, BigDecimal>();
		String sql = " select s.branch_id,s.academy_id,d.fee_subject_id,sum(a.add_money) as sum_money "
					+" from tb_r_academy_rebate_add_records a,tb_e_student s,tb_e_fee_payment_detail d "
					+" where 1=1 "
					+" and a.student_id = s.id "
					+" and a.fee_payment_detail_id = d.id ";
		// 返款单sql
		String payAcademyCeduSql = getPayAcademyCeduSql(status,academyId,startDate,endDate);
		if(!payAcademyCeduSql.equals("")){
			sql += " and a.pay_academy_cedu_id in ( " + payAcademyCeduSql + " ) ";
		}
		// 中心
		if(branchId!=-2){
			sql += " and s.branch_id = " + branchId;
		}
		// 批次
		if(batchId!=-2){
			sql += " and s.global_batch = " + batchId;
		}
		// 费用科目
		if(feeSubjectIds!=null && !feeSubjectIds.equals("")){
			sql += " and d.fee_subject_id in (" + feeSubjectIds + " ) ";
		}
		sql += " group by s.branch_id,s.academy_id,d.fee_subject_id ";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("branch_id", resultSet.getString("branch_id"));
						map.put("academy_id", resultSet.getString("academy_id"));
						map.put("fee_subject_id", resultSet.getString("fee_subject_id"));
						map.put("sum_money", resultSet.getString("sum_money"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, String> map : listResult) {
				mapResult.put(map.get("branch_id")+"_"+map.get("academy_id")+"_"+map.get("fee_subject_id"),getBigDecimalByString(map.get("sum_money")));
			}
		}
		return mapResult;
	}
	
	/**
	 * 根据条件查询院校返款调整金额分配给学习中心的金额
	 * @param branchId
	 * @param academyId
	 * @param batchId 暂时无此查询
	 * @param status
	 * @param startDate
	 * @param endDate
	 * @param feeSubjectIds 暂时无此查询
	 * @return Map(key:branchId_academyId value:sum_money)
	 */
	private Map<String,BigDecimal> getAcademyRebateFenPeiBranch(int branchId,int academyId,int batchId,int status,Date startDate,Date endDate,String feeSubjectIds){
		Map<String,BigDecimal> mapResult = new HashMap<String, BigDecimal>();
		String sql = " select a.branch_id,p.remitter as academy_id,sum(a.add_money) as sum_money "
					+" from tb_r_academy_rebate_fen_pei_branch a,tb_e_pay_academy_cedu p "
					+" where 1=1 "
					+" and p.id = a.pay_academy_cedu_id ";
		// 返款状态
		if(status!=0){
			sql += " and p.status >= " + status;
		}
		// 院校
		if(academyId!=-2){
			sql += " and p.remitter = " + academyId;
		}
		// 返款单起止时间
		if(startDate!=null){
			sql += " and p.created_time >= '" + DateUtil.getDate(startDate, "yyyy-MM-dd") + " 00:00:00' ";
		}
		if(endDate!=null){
			sql += " and p.created_time <= '" +DateUtil.getDate(endDate, "yyyy-MM-dd")+ " 23:59:59' ";
		}
		// 中心
		if(branchId!=-2){
			sql += " and a.branch_id = " + branchId;
		}
		sql += " group by a.branch_id,p.remitter ";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("branch_id", resultSet.getString("branch_id"));
						map.put("academy_id", resultSet.getString("academy_id"));
						map.put("sum_money", resultSet.getString("sum_money"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, String> map : listResult) {
				mapResult.put(map.get("branch_id")+"_"+map.get("academy_id"),getBigDecimalByString(map.get("sum_money")));
			}
		}
		return mapResult;
	}
	
	/**
	 * 根据条件查询应收院校款的金额
	 * @param branchId
	 * @param academyId
	 * @param batchId 暂时无此查询
	 * @param status
	 * @param startDate
	 * @param endDate
	 * @param feeSubjectIds 暂时无此查询
	 * @return Map(key:branchId_academyId value:sum_money)
	 */
	private Map<String,BigDecimal> getPlanedAcademyBill(int branchId,int academyId,int batchId,int status,Date startDate,Date endDate,String feeSubjectIds){
		Map<String,BigDecimal> mapResult = new HashMap<String, BigDecimal>();
		String sql = " select p.branch_id,p.academy_id,sum(p.academy_rebate_id) as sum_money "
					+" from tb_e_planed_academy_bill p "
					+" where 1=1 ";
		// 返款单sql
		String payAcademyCeduSql = getPayAcademyCeduSql(status,academyId,startDate,endDate);
		if(!payAcademyCeduSql.equals("")){
			sql += " and p.academy_rebate_id in ( " + payAcademyCeduSql + " ) ";
		}
		// 中心
		if(branchId!=-2){
			sql += " and p.branch_id = " + branchId;
		}
		sql += " group by p.branch_id,p.academy_id ";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("branch_id", resultSet.getString("branch_id"));
						map.put("academy_id", resultSet.getString("academy_id"));
						map.put("sum_money", resultSet.getString("sum_money"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, String> map : listResult) {
				mapResult.put(map.get("branch_id")+"_"+map.get("academy_id"),getBigDecimalByString(map.get("sum_money")));
			}
		}
		return mapResult;
	}
	
	/**
	 * 初始化学习中心下的院校
	 * @param school
	 * @param gbatch
	 * @param branchId
	 * @return
	 */
	private Map<String,List<Integer>> initBranchAcademys(int school,int gbatch,int branchId){
		Map<String,List<Integer>> mapResult=new HashMap<String,List<Integer>>();
		
		String sql=" select academy_id,branch_id from tb_r_academy_batch_branch where 1=1 ";
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//学习中心
		if(branchId!=-2){
			sql+=" and branch_id="+branchId;
		}
		//批次
		if(gbatch!=-2){
			sql+=" and global_id in ("+gbatch+")";
		}
		sql+=" group by academy_id,branch_id ";
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult_ = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						return map;
					}
				});
		List<Integer> branchIds=null;
		if(listResult_!=null){
			for (Map<String, Integer> map : listResult_) {
				if(map!=null){
					if(!mapResult.containsKey(map.get("branch_id")+"_")){
						branchIds=new ArrayList<Integer>();
						branchIds.add(map.get("academy_id"));
						mapResult.put(map.get("branch_id")+"_", branchIds);
					}else{
						mapResult.get(map.get("branch_id")+"_").add(map.get("academy_id"));
					}
				}
			}
		}
		return mapResult;
	}
	
//	/**
//	 * 获取缴费数量
//	 * @param branchId
//	 * @param academyId
//	 * @param batchId
//	 * @return
//	 */
//	private Map<String,Integer> getJiaoFeiCount(int branchId,int academyId,int batchId){
//		//缴费人数map key:branchId_academyId  value:缴费人数
//		Map<String,Integer> branchId_academyId_map=new HashMap<String, Integer>();
//		String sql =" select branch_id,academy_id,count(id) as jiaofei_count "
//					+" from tb_e_student "
//					+" where status >= " + Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI + " "
//					+" and tuition_status > "+Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI+" ";
//		// 中心
//		if(branchId>0){
//			sql += " and branch_id = " + branchId;
//		}
//		// 院校
//		if(academyId>0){
//			sql += " and academy_id = " + academyId;
//		}
//		// 全局批次
//		if(batchId>0){
//			sql += " and global_batch = " + batchId;
//		}
//		sql += " group by branch_id,academy_id";
//		@SuppressWarnings("unchecked")
//		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,new RowMapper() {
//			public Map<String,String> mapRow(ResultSet resultSet, int index)throws SQLException {
//				Map<String,String> map =new HashMap<String,String>();
//				map.put("branch_id", resultSet.getString("branch_id"));
//				map.put("academy_id", resultSet.getString("academy_id"));
//				map.put("jiaofei_count", resultSet.getString("jiaofei_count"));
//				return map;
//			}
//		});
//		if(list!=null){
//			for (Map<String, String> map : list) {
//				branchId_academyId_map.put(map.get("branch_id")+"_"+map.get("academy_id"), getIntByString(map.get("jiaofei_count")));
//			}
//		}
//		return branchId_academyId_map;
//	}
	
	/**
	 * 获取用户对应的缴费学生数量Map(学费)
	 * 
	 * @return  key:academyId_branchId   value:缴费学生人数
	 */
	final public Map<String,Integer> getJiaoFeiCount(int school,int gbatch,int branchId,Date startDate,Date endDate){
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql1 ="select DISTINCT student_id from tb_e_fee_payment_detail where fee_subject_id="+FeeSubjectEnum.TuitionFee.value() + " "
					+" and types = "+Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE+" "
					+" and status >= "+Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN+" "
					+" and delete_flag = "+Constants.DELETE_FALSE+" ";
		//时间
		if(startDate!=null&&endDate!=null){
			sql1+=" and created_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		if(startDate!=null&&endDate==null){
			sql1+=" and created_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"'";
		}
		if(startDate==null&&endDate!=null){
			sql1+=" and created_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		
		String sql ="select branch_id,academy_id,count(id) as jiaofei_count from tb_e_student where  status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" and id in ("+sql1+")  and tuition_status>"+Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI+" ";		
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//学习中心
		if(branchId!=-2){
			sql+=" and branch_id="+branchId;
		}
		//批次
		if(gbatch!=-2){
			sql+=" and enrollment_batch_id in ( "+getEnrollmentBatchId(school,gbatch)+" ) ";
		}
		sql+=" group by branch_id,academy_id";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("jiaofei_count", resultSet.getInt("jiaofei_count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("academy_id")+"_"+map.get("branch_id"), map.get("jiaofei_count"));
			}
		}
		return mapResult;
	}
	
	/**
	 * 通过全局批次，以及院校ID查询院校的招生批次Id字符串
	 * @param school院校
	 * @param globalEnrollBatchId 全局批次ID
	 * @return
	 */
	final public String getEnrollmentBatchId(int school,int globalEnrollBatchId){
		
		if(globalEnrollBatchId==-2){
			return "0";
		}
		
		String ids=",";
		String sql="select IFNULL(id,0) as id from tb_e_academy_enroll_batch where global_enroll_batch_id="+globalEnrollBatchId;
		if(school!=-2){
			sql+=" and academy_id="+school;
			return jdbcTemplatePlus.queryForInt(sql)+"";
		}
		
		List<HashMap<String,Long>> list=jdbcTemplatePlus.queryForList(sql);
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				Map<String,Long> map=list.get(i);
				if(ids.equals(",")){
					ids=map.get("id")+"";
				}else{
					ids+=","+map.get("id");
				}
			}
		}
		if(ids.equals(","))
			ids="0";
		
		return ids;
	}
	
	/*-----------------------------------工具---------------------------------------*/
	
	// String转double
//	final private double getDoubleByString(String dou){
//		if(dou!=null){
//			try {
//				return Double.parseDouble(dou);
//			} catch (NumberFormatException e) {
//				return 0.00f;
//			}
//		}
//		return 0.00f;
//	}
	
	// Integer转int
	final private int getIntByInteger(Integer i){
		if(i!=null){
			try {
				return i;
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 0;
	}
	
	// String转int
	final private int getIntByString(String i){
		if(i!=null){
			try {
				return Integer.parseInt(i);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 0;
	}
	
	// String转BigDecimal
	final private BigDecimal getBigDecimalByString(String i){
		if(i!=null){
			try {
				return new BigDecimal(i);
			} catch (NumberFormatException e) {
				return new BigDecimal(0);
			}
		}
		return new BigDecimal(0);
	}
	
	// BigDecimal转String
	final private String getStringByBigDecimal(BigDecimal bigDecimal){
		if(bigDecimal==null){
			return "--";
		}
		else{
			return MoneyUtil.formatMoney(bigDecimal.toString());
		}
	}
	
	// BigDecimal转非空
//	final private BigDecimal getBigDecimalByNull(BigDecimal bigDecimal){
//		if(bigDecimal==null){
//			return new BigDecimal(0);
//		}else{
//			return bigDecimal;
//		}
//	}
	
	// 计算List<Integer>总和
	final private int getIntListSum(List<Integer> list){
		int temp=0;
		if(list!=null && list.size()>0){
			for (Integer i : list) {
				if(i!=null){
					temp+=i;
				}
			}
		}
		return temp;
	}
	
	// 计算List<BigDecimal>总和
	final private BigDecimal getBigDecimalListSum(List<BigDecimal> list){
		BigDecimal temp= null;
		if(list!=null && list.size()>0){
			for (BigDecimal b : list) {
				temp = getBigDecimalAdd(temp,b);
			}
		}
		return temp;
	}
	
	// 计算BigDecimal加法
	final private BigDecimal getBigDecimalAdd(BigDecimal bd1,BigDecimal bd2){
		if(bd1!=null && bd2!=null){
			return bd1.add(bd2);
		}else if(bd1!=null && bd2==null){
			return bd1;
		}else if(bd1==null && bd2!=null){
			return bd2;
		}else{
			return null;
		}
	}
	
	// 计算BigDecimal减法
	final private BigDecimal getBigDecimalSubtract(BigDecimal bd1,BigDecimal bd2){
		if(bd1!=null && bd2!=null){
			return bd1.subtract(bd2);
		}else if(bd1!=null && bd2==null){
			return bd1;
		}else if(bd1==null && bd2!=null){
			return new BigDecimal(0).subtract(bd2);
		}else{
			return null;
		}
	}
}
