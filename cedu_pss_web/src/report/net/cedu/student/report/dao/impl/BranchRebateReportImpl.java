package net.cedu.student.report.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.string.MoneyUtil;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.Branch;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.BranchRebateReport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 学习中心返款
 * @author yangdongdong
 *
 */
@Repository
public class BranchRebateReportImpl extends BaseReportDaoImpl implements BranchRebateReport {

	private final Log log=LogFactory.getLog(BranchRebateReportImpl.class);
	
	@Autowired
	private BranchDao branchDao;
	
	
	private  JdbcTemplatePlus jdbcTemplatePlus = null;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map statistics(Map<String,Integer> params,Map<String, Date> dateParams) {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		final int gbatch = params.get("batch");// 全局批次
		final int enrollmentSource = params.get("source");// 招生途径
		final int branchId = params.get("branch");// 学习中心
		final Date startDate=dateParams.get("startDate");//开始日期
		final Date endDate=dateParams.get("endDate");//结束日期
		//返回的List结果＋合计
		Map<String,Object> mapResult=new HashMap<String,Object>();
		//返回的List结果
		List<Object> result=new ArrayList<Object>();
		
		
		
		try {
			String branchSql = " and parentId >0 ";
			if(branchId!=-2){
				branchSql += " and id = " + branchId;
			}
			List<Branch> branchList=branchDao.getByProperty(branchSql, new Object[]{});
			if(branchList!=null){
				//通过返款日期获取学生集合(全部)
				final String studentIdsAll=getStudentIDByDateAll(branchId,enrollmentSource,startDate,endDate);
				//全部 key: branchId_enrollmentSource    value: studentId List
				final Map<String,List<Integer>> branchId_enrollmentSourceAllMap=getKeyBranchValueStudentIdListByStudentIds(gbatch,studentIdsAll);
				//通过返款日期获取学生集合(已汇款)
				final String studentIdsYiHuiKuan=getStudentIDByDateYiHuiKuan(branchId,enrollmentSource,startDate,endDate);
				//已汇款 key: branchId_enrollmentSource    value: studentId List
				final Map<String,List<Integer>> branchId_enrollmentSourceYiHuiKuanMap=getKeyBranchValueStudentIdListByStudentIds(gbatch,studentIdsYiHuiKuan);
				//全部_调整金额Map key:学习中心ID value:调整金额
				final Map<String,BigDecimal> adjustPaiedAllMap = getAdjustPaiedByAll(branchId, enrollmentSource, startDate, endDate);
				//已返款_调整金额Map key:学习中心ID value:调整金额
				final Map<String,BigDecimal> adjustPaiedYiHuiKuanMap = getAdjustPaiedByYiHuiKuan(branchId, enrollmentSource, startDate, endDate);
				
				//全部_调整金额合计List
				final List<BigDecimal> yingAdjustPaiedList = new ArrayList<BigDecimal>();
				//已返款_调整金额合计List
				final List<BigDecimal> yiAdjustPaiedList = new ArrayList<BigDecimal>();
				
				Map obj_=null;
				int i=1;
				for (Branch branch : branchList) {
					obj_=new HashMap();
					obj_.put("number", i++);
					//学习中心名称
					obj_.put("branchName", branch.getName());
					//返款开始日期
					obj_.put("startDate", DateUtil.getDate(startDate, "yyyy-MM-dd"));
					//返款结束日期
					obj_.put("endDate",DateUtil.getDate(endDate, "yyyy-MM-dd"));
					//渠道
					obj_.put("qudaoMoney", getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get(branch.getId()+"_"+Constants.WEB_STU_QV_DAO)))
							+"/"+getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get(branch.getId()+"_"+Constants.WEB_STU_QV_DAO))));
					//老带新
					obj_.put("laodaixinMoney", getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get(branch.getId()+"_"+Constants.WEB_STU_ENROLLMENT_SOURCE)))
							+"/"+getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get(branch.getId()+"_"+Constants.WEB_STU_ENROLLMENT_SOURCE))));
					//大客户
					obj_.put("dakehuMoney", getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get(branch.getId()+"_"+Constants.WEB_STU_DA_KE_HU)))
							+"/"+getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get(branch.getId()+"_"+Constants.WEB_STU_DA_KE_HU))));
					//加盟
					obj_.put("jiamengMoney", getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get(branch.getId()+"_"+Constants.WEB_STU_JIA_MENG)))
							+"/"+getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get(branch.getId()+"_"+Constants.WEB_STU_JIA_MENG))));
					//共建
					obj_.put("gongjianMoney", getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get(branch.getId()+"_"+Constants.WEB_STU_GONG_JIAN)))
							+"/"+getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get(branch.getId()+"_"+Constants.WEB_STU_GONG_JIAN))));
					//其他
					obj_.put("otherMoney", "--/--");
					//调整金额
					BigDecimal all_adjustPaied = adjustPaiedAllMap.get(branch.getId()+"");
					if(all_adjustPaied!=null){
						yingAdjustPaiedList.add(all_adjustPaied);
					}
					BigDecimal yi_adjustPaied = adjustPaiedYiHuiKuanMap.get(branch.getId()+"");
					if(yi_adjustPaied!=null){
						yiAdjustPaiedList.add(yi_adjustPaied);
					}
					obj_.put("tiaozhengMoney", getStringByBigDecimal(yi_adjustPaied)
							+"/"+getStringByBigDecimal(all_adjustPaied));
					//应返款额合计
					BigDecimal yingHejiMoney = getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get(branch.getId()+"_b"));
					if(yingHejiMoney!=null && all_adjustPaied!=null){
						yingHejiMoney = yingHejiMoney.add(all_adjustPaied);
					}else if(yingHejiMoney==null && all_adjustPaied!=null){
						yingHejiMoney = all_adjustPaied;
					}
					obj_.put("yingHejiMoney", getStringByBigDecimal(yingHejiMoney));
					//已汇款额合计
					BigDecimal yiHejiMoney = getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get(branch.getId()+"_b"));
					if(yiHejiMoney!=null && yi_adjustPaied!=null){
						yiHejiMoney = yiHejiMoney.add(yi_adjustPaied);
					}else if(yiHejiMoney==null && yi_adjustPaied!=null){
						yiHejiMoney = yi_adjustPaied;
					}
					obj_.put("yiHejiMoney", getStringByBigDecimal(yiHejiMoney));
					//未返款额合计（应返-已返）
					BigDecimal weiHejiMoney = null;
					if(yingHejiMoney!=null && yiHejiMoney!=null){
						weiHejiMoney = yingHejiMoney.subtract(yiHejiMoney);
					}
					else if(yingHejiMoney!=null && yiHejiMoney==null){
						weiHejiMoney = yingHejiMoney;
					}
					obj_.put("weiHejiMoney", getStringByBigDecimal(weiHejiMoney));
					result.add(obj_);
				}
				mapResult.put("reportList", result);
				//合计
				//渠道
				mapResult.put("qudaoMoneySum", getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get(Constants.WEB_STU_QV_DAO+"_e")))
						+"/"+getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get(Constants.WEB_STU_QV_DAO+"_e"))));
				//老带新
				mapResult.put("laodaixinMoneySum", getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get(Constants.WEB_STU_ENROLLMENT_SOURCE+"_e")))
						+"/"+getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get(Constants.WEB_STU_ENROLLMENT_SOURCE+"_e"))));
				//大客户
				mapResult.put("dakehuMoneySum", getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get(Constants.WEB_STU_DA_KE_HU+"_e")))
						+"/"+getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get(Constants.WEB_STU_DA_KE_HU+"_e"))));
				//加盟
				mapResult.put("jiamengMoneySum", getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get(Constants.WEB_STU_JIA_MENG+"_e")))
						+"/"+getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get(Constants.WEB_STU_JIA_MENG+"_e"))));
				//共建
				mapResult.put("gongjianMoneySum", getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get(Constants.WEB_STU_GONG_JIAN+"_e")))
						+"/"+getStringByBigDecimal(getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get(Constants.WEB_STU_GONG_JIAN+"_e"))));
				//其他
				mapResult.put("otherMoneySum", "--/--");
				//调整金额
				BigDecimal ying_tiaozhengSum = getBigDecimalListSum(yingAdjustPaiedList);
				BigDecimal yi_tiaozhengSum = getBigDecimalListSum(yiAdjustPaiedList);
				mapResult.put("tiaozhengMoneySum", getStringByBigDecimal(yi_tiaozhengSum)
						+"/"+getStringByBigDecimal(ying_tiaozhengSum));
				//应返款额合计
				BigDecimal yingHejiMoneySum = getMoneyByStudentIdList(branchId_enrollmentSourceAllMap.get("all_"));
				if(yingHejiMoneySum!=null && ying_tiaozhengSum!=null){
					yingHejiMoneySum = yingHejiMoneySum.add(ying_tiaozhengSum);
				}else if(yingHejiMoneySum==null && ying_tiaozhengSum!=null){
					yingHejiMoneySum = ying_tiaozhengSum;
				}
				mapResult.put("yingHejiMoneySum", getStringByBigDecimal(yingHejiMoneySum));
				//已汇款额合计
				BigDecimal yiHejiMoneySum = getMoneyByStudentIdList(branchId_enrollmentSourceYiHuiKuanMap.get("all_"));
				if(yiHejiMoneySum!=null && yi_tiaozhengSum!=null){
					yiHejiMoneySum = yiHejiMoneySum.add(yi_tiaozhengSum);
				}else if(yiHejiMoneySum==null && yi_tiaozhengSum!=null){
					yiHejiMoneySum = yi_tiaozhengSum;
				}
				mapResult.put("yiHejiMoneySum", getStringByBigDecimal(yiHejiMoneySum));
				//未返款额合计（应返-已返）
				BigDecimal weiHejiMoneySum = null;
				if(yingHejiMoneySum!=null && yiHejiMoneySum!=null){
					weiHejiMoneySum = yingHejiMoneySum.subtract(yiHejiMoneySum);
				}
				else if(yingHejiMoneySum!=null && yiHejiMoneySum==null){
					weiHejiMoneySum = yingHejiMoneySum;
				}
				mapResult.put("weiHejiMoneySum", getStringByBigDecimal(weiHejiMoneySum));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("学习中心返款统计表异常,错误信息为:"+e.getMessage());
			return mapResult;
		}
		
		return mapResult;
	}
	
	// 通过中心ID，招生途径，返款时间段获取学生IDs(全部返款)
	private String getStudentIDByDateAll(int branchId,int enrollmentSourceId,Date startDate,Date endDate){
		return getStudentIDByDate(branchId,enrollmentSourceId,startDate,endDate,0);
	}
	
	// 通过中心ID，招生途径，返款时间段获取学生IDs(已汇款)
	private String getStudentIDByDateYiHuiKuan(int branchId,int enrollmentSourceId,Date startDate,Date endDate){
		return getStudentIDByDate(branchId,enrollmentSourceId,startDate,endDate,Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
	}
	
	/**
	 * 通过返款时间段获取学生IDs(已汇款)
	 * @param start
	 * @param end
	 * @return
	 */
	private String getStudentIDByDate(int branchId,int enrollmentSourceId,Date startDate,Date endDate,int status){
		//返款单ID
		final StringBuffer sb_=new StringBuffer();
		//学生ID
		final StringBuffer sb__=new StringBuffer();
		String sql_="select id from tb_e_order_cedu_channel where 1=1 ";
		       if(status!=0){
		    	   sql_+=" and status="+status;
		       }
			   // 中心
			   if(branchId>-2){
				   sql_+=" and branch_id = "+branchId;
			   }
			   // 招生途经
			   if(enrollmentSourceId!=-2){
				   sql_+=" and channel_type = "+enrollmentSourceId;
			   }
			   // 时间
			   if(startDate!=null&&endDate!=null){
					sql_+=" and created_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59'";
			   }
			   if(startDate!=null&&endDate==null){
					sql_+=" and created_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00'";
			   }
			   if(startDate==null&&endDate!=null){
					sql_+=" and created_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59'";
			   }
			   
				@SuppressWarnings("unchecked")
				List<Map<String,String>> list = jdbcTemplatePlus.query(sql_,new RowMapper() {
					public Map<String,String> mapRow(ResultSet resultSet, int index)throws SQLException {
						Map<String,String> map =new HashMap<String,String>();
						if(sb_.toString().equals("")){
							sb_.append(resultSet.getString("id"));
						}else{
							sb_.append(",");
							sb_.append(resultSet.getString("id"));
						}
						return map;
					}
				});
				System.out.println(sql_);
				//没有返款单
				if(sb_.toString().equals("")){
					return "0";
				}	
				String sql__ = "select student_id from tb_e_fee_payment_detail where order_cedu_channel_id in ("+sb_.toString()+") ";
				@SuppressWarnings("unchecked")
				List<Map<String,String>> list_ = jdbcTemplatePlus.query(sql__,new RowMapper() {
					public Map<String,String> mapRow(ResultSet resultSet, int index)throws SQLException {
						Map<String,String> map =new HashMap<String,String>();
						if(sb__.toString().equals("")){
							sb__.append(resultSet.getString("student_id"));
						}else{
							sb__.append(",");
							sb__.append(resultSet.getString("student_id"));
						}
						return map;
					}
				});
				if(sb__.toString().equals("")){
					return "0";
				}	
				
		return sb__.toString();
	}
	/**
	 * 获取学习中心_招生途径所对应的学生Id集合
	 * @param studentIds 通过返款时间段获取学生IDs
	 * @return key: branchId_enrollmentSource    value: studentId List
	 */
	private Map<String,List<Integer>> getKeyBranchValueStudentIdListByStudentIds(int gbatchId,String studentIds){
		final Map<String,List<Integer>> result=new HashMap<String, List<Integer>>();
		String enrollmentSourceId=Constants.WEB_STU_ENROLLMENT_SOURCE+","+Constants.WEB_STU_DA_KE_HU+","+Constants.WEB_STU_JIA_MENG+","+Constants.WEB_STU_GONG_JIAN+","+Constants.WEB_STU_QV_DAO;
		String sql_="select branch_id,enrollment_source,id from tb_e_student where id in ("+studentIds+") and status>="+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+" and enrollment_source in ("+enrollmentSourceId+")";
		if(gbatchId>-2){
			sql_+= " and global_batch = "+gbatchId;
		}
		sql_+=" order by id";
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = jdbcTemplatePlus.query(sql_,new RowMapper() {
			String branchId=null;
			String enrollmentSource=null;
			List<Integer> idList=null;
			public Map<String,String> mapRow(ResultSet resultSet, int index)throws SQLException {
				Map<String,String> map =new HashMap<String,String>();
				branchId=resultSet.getString("branch_id");
				enrollmentSource=resultSet.getString("enrollment_source");
				if(result.containsKey(branchId+"_"+enrollmentSource)){
					result.get(branchId+"_"+enrollmentSource).add(resultSet.getInt("id"));
				}else{
					idList=new ArrayList<Integer>();
					idList.add(resultSet.getInt("id"));
					result.put(branchId+"_"+enrollmentSource, idList);
				}
				
				if(result.containsKey(enrollmentSource+"_e")){
					result.get(enrollmentSource+"_e").add(resultSet.getInt("id"));
				}else{
					idList=new ArrayList<Integer>();
					idList.add(resultSet.getInt("id"));
					result.put(enrollmentSource+"_e", idList);
				}
				
				if(result.containsKey(branchId+"_b")){
					result.get(branchId+"_b").add(resultSet.getInt("id"));
				}else{
					idList=new ArrayList<Integer>();
					idList.add(resultSet.getInt("id"));
					result.put(branchId+"_b", idList);
				}
				
				if(result.containsKey("all_")){
					result.get("all_").add(resultSet.getInt("id"));
				}else{
					idList=new ArrayList<Integer>();
					idList.add(resultSet.getInt("id"));
					result.put("all_", idList);
				}
				return map;
			}
		});
		
		return result;
	}
	
	/**
	 * 获取返款金额
	 * @param studentIdList  通过学习中心ID和招生途径获取的学生Id集合
	 * @return 返款金额
	 */
	private BigDecimal getMoneyByStudentIdList(List<Integer> studentIdList){
		if(studentIdList!=null&&studentIdList.size()!=0){
			StringBuffer sb_=new StringBuffer();
			for (Integer id : studentIdList) {
				if(id!=null){
					if(sb_.toString().equals("")){
						sb_.append(id);
					}else{
						sb_.append(",");
						sb_.append(id);
					}
				}
				
			}
			if(sb_.toString().equals("")){
				return null;
			}
			String sql="select sum(payment_by_channel) as money_ from tb_e_fee_payment_detail where student_id in ("+sb_.toString()+")";
			Map<String,BigDecimal> map  =jdbcTemplatePlus.queryForMap(sql);
			if(map!=null&&map.get("money_")!=null){
				return map.get("money_");
			}
		}
		return null;
	}
	
	/*
	 * 根据条件查询调整金额（全部）
	 */
	private Map<String,BigDecimal> getAdjustPaiedByAll(int branchId,int enrollmentSourceId,Date startDate,Date endDate){
		return getAdjustPaied(branchId, enrollmentSourceId, startDate, endDate, 0);
	}
	
	/*
	 * 根据条件查询调整金额（已汇款）
	 */
	private Map<String,BigDecimal> getAdjustPaiedByYiHuiKuan(int branchId,int enrollmentSourceId,Date startDate,Date endDate){
		return getAdjustPaied(branchId, enrollmentSourceId, startDate, endDate, Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
	}
	
	/**
	 * 根据条件查询调整金额
	 * @param branchId
	 * @param enrollmentSourceId
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @return key: 学习中心ID value:调整金额
	 */
	private Map<String,BigDecimal> getAdjustPaied(int branchId,int enrollmentSourceId,Date startDate,Date endDate,int status){
		Map<String,BigDecimal> result = new HashMap<String, BigDecimal>();
		String sql = " select branch_id,sum(adjust_paied) as sum_money "
					+" from tb_e_order_cedu_channel "
					+" where 1=1 "
					+" and adjust_paied<>0 ";
		if(status!=0){
			sql+=" and status="+status;
		}
		// 中心
		if(branchId>-2){
			sql+=" and branch_id = "+branchId;
		}
		// 招生途经
		if(enrollmentSourceId!=-2){
			sql+=" and channel_type = "+enrollmentSourceId;
		}
		// 时间
		if(startDate!=null&&endDate!=null){
			sql+=" and created_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59'";
		}
		if(startDate!=null&&endDate==null){
			sql+=" and created_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00'";
		}
		if(startDate==null&&endDate!=null){
			sql+=" and created_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59'";
		}
		sql +=" group by branch_id ";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("branch_id", resultSet.getString("branch_id"));
						map.put("sum_money", resultSet.getString("sum_money"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, String> map : listResult) {
				result.put(map.get("branch_id")+"",getBigDecimalByString(map.get("sum_money")));
			}
		}
		return result;
	}
	
	private String getStringByBigDecimal(BigDecimal bigDecimal){
		if(bigDecimal==null){
			return "--";
		}
		else{
			return MoneyUtil.formatMoney(bigDecimal.toString());
		}
	}
	
	// String转BigDecimal
	private BigDecimal getBigDecimalByString(String i){
		if(i!=null){
			try {
				return new BigDecimal(i);
			} catch (NumberFormatException e) {
				return new BigDecimal(0);
			}
		}
		return new BigDecimal(0);
	}
	
	// 计算List<BigDecimal>总和
	final private BigDecimal getBigDecimalListSum(List<BigDecimal> list){
		BigDecimal temp= new BigDecimal(0);
		if(list!=null && list.size()>0){
			for (BigDecimal b : list) {
				temp = temp.add(b);
			}
		}
		return temp;
	}
	
}
