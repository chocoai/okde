package net.cedu.student.report.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.Constants;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.dao.academy.AcademyDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.academy.Academy;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.TuitionStandardReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @功能：学费标准汇总表
 * 
 * @作者： 董溟浩
 * @作成时间：2012-06-29 下午14:42:33
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
@Repository
public class TuitionStandardReportImpl extends BaseReportDaoImpl implements TuitionStandardReport {

	private  JdbcTemplatePlus jdbcTemplatePlus = null;
	@Autowired
	private AcademyDao academyDao;
	
	public Map statistics(Map<String, Integer> params) {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		final int school = params.get("school");// 学院
		final int batch = params.get("batch");// 全局批次
//		final int serachStudentDataSource = params.get("studentDataSource");// 数据来源
//		final int way = params.get("way");// 市场途径
//		final int source = params.get("source");// 招生途径
//		final int quyuId = params.get("manager");// 区域经理ID
		final int xuexiId = params.get("branch");// 学习中心
//		final int fuwuId = params.get("fuwu");// 服务站ID
//		final int userId = params.get("user");// 用户ID
		
		//初始化map集合
		//Map(key:中心ID_院校ID value:标准ID)
		final Map<String,List<Integer>> key_a_b_value_policy_fee = getPolicyFeeByBatch(batch, xuexiId, school);
		//Map(key:标准ID value:费用总和_学分)
		final Map<String,String> key_a_b_value_fee_standard = getFeeStandard();
		
		//生成%数
		final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
		format.setMinimumFractionDigits(2);// 设置小数位
		//金钱
		final DecimalFormat format_=new DecimalFormat();
		format_.setMinimumFractionDigits(2);
		
		//获取所有中心
		String branchSql=" select id,name,short_name from tb_e_branch where delete_flag="+Constants.DELETE_FALSE+" and id>"+BranchEnum.Admin.value()+" ";
		if(xuexiId!=-2){
			branchSql+=" and id="+xuexiId;
		}
		
		// 获取所有院校
		String academySql = " and id in "
			+" ( "
			+" select academyId from AcademyEnrollBatch "
			+" where deleteFlag= "+Constants.DELETE_FALSE+" ";
		if(batch!=-2){
			academySql+=" and globalEnrollBatchId = "+batch+" ";
		}
		academySql+=" ) ";
		if(school!=-2){
			academySql+=" and id = "+school+" ";
		}
		final List<Academy> academyList = academyDao.getByProperty(academySql);
		
		// 遍历中心
		List branchList = jdbcTemplatePlus.query(branchSql, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int index)throws SQLException {
				Map<String, Object> branchMap = new HashMap<String, Object>();
				branchMap.put("branch_id", resultSet.getInt("id"));
				branchMap.put("branch_name", resultSet.getString("name"));
				branchMap.put("branch_city", resultSet.getString("short_name"));
				
				// 遍历院校
				List<String> tuitionStandard = new ArrayList<String>();
				if(academyList!=null && academyList.size()>0){
					for(Academy academy : academyList){
						tuitionStandard.add(getTuitionStandard(key_a_b_value_policy_fee.get(resultSet.getInt("id")+"_"+academy.getId()),key_a_b_value_fee_standard));
					}
				}
				branchMap.put("tuitionStandard", tuitionStandard);
				return branchMap;
			}
		});
		
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put("academyList", academyList);
		resultMap.put("branchList", branchList);
		
		return resultMap;
	}
	
	/*
	 * 根据批次中心获取中心院校政策的标准id
	 * Map(key:中心ID_院校ID value:标准IDList)
	 * 如果批次为空，则返回空
	 */
	private Map<String,List<Integer>> getPolicyFeeByBatch(int batchId,int branchId,int academyId){
		Map<String,List<Integer>> mapResult=new HashMap<String,List<Integer>>();
		String sql = " select branch_id,academy_id,policy_fee_id from tb_e_policy_fee_detail "
					+" where 1=1 "
					+" and audit_status = "+Constants.AUDIT_STATUS_TRUE+" "
					+" and fee_subject_id = "+FeeSubjectEnum.TuitionFee.value()+" ";
		if(branchId!=-2){
			sql += " and branch_id = "+branchId+" ";
		}
		if(academyId!=-2){
			sql += " and academy_id = "+academyId+" ";
		}
		sql +=" and batch_id in "
			+ " ( "
			+ " select id from tb_e_academy_enroll_batch "
			+ " where delete_flag=0 ";
		if(batchId!=-2){
			sql += " and global_enroll_batch_id = "+batchId+" ";
		}
		else{
			return mapResult;
		}
		sql +=" ) group by branch_id,academy_id,policy_fee_id ";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("policy_fee_id", resultSet.getInt("policy_fee_id"));
						return map;
					}
				});
		List<Integer> policyFeeIds=null;
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				if(!mapResult.containsKey(map.get("branch_id")+"_"+map.get("academy_id"))){
					policyFeeIds=new ArrayList<Integer>();
					policyFeeIds.add(map.get("policy_fee_id"));
					mapResult.put(map.get("branch_id")+"_"+map.get("academy_id"), policyFeeIds);
				}else{
					mapResult.get(map.get("branch_id")+"_"+map.get("academy_id")).add(map.get("policy_fee_id"));
				}
			}
		}
		return mapResult;
	}
	
	/*
	 * 获取所有收费标准费用总和
	 * Map(key:标准ID value:费用总和_学分费用)
	 * 如果学分不为0，则收费标准为学分否则为年度
	 */
	private Map<String,String> getFeeStandard(){
		Map<String,String> mapResult=new HashMap<String,String>();
		String sql = " select policy_fee_detail_id,sum(charging_floor) as jin_e,credit_fee as xue_fen "
					+" from tb_e_fee_standard "
					+" where 1=1 "
					+" group by policy_fee_detail_id,credit_fee ";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("policy_fee_detail_id", resultSet.getString("policy_fee_detail_id"));
						map.put("jin_e", resultSet.getString("jin_e"));
						map.put("xue_fen", resultSet.getString("xue_fen"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, String> map : listResult) {
				mapResult.put(map.get("policy_fee_detail_id")+"", map.get("jin_e")+"_"+map.get("xue_fen"));
			}
		}
		return mapResult;
	}
	
	private String getTuitionStandard(List<Integer> list,Map<String,String> map){
		String tuitionStandardStr = "--";
		DecimalFormat format_=new DecimalFormat();
		format_.setMinimumFractionDigits(2);
		if(list!=null && list.size()>0){
			tuitionStandardStr = "/";
			for(Integer integer : list){
				String str = map.get(integer+"");
				double jinE = Double.parseDouble(str.substring(0,str.indexOf("_")));
				double xueFen = Double.parseDouble(str.substring(str.indexOf("_")+1));
				//如果有学分则叠加学分
				if(xueFen!=0){
					// 重复则不添加
					if(tuitionStandardStr.indexOf("/"+format_.format(xueFen)+"/")<0){
						tuitionStandardStr+=format_.format(xueFen)+"/";
					}
				//没有学分则叠加金额
				}else{
					// 重复则不添加
					if(tuitionStandardStr.indexOf("/"+format_.format(jinE)+"/")<0){
						tuitionStandardStr+=format_.format(jinE)+"/";
					}
				}
			}
		}
		//去除两边的"/"
		if(!tuitionStandardStr.equals("--") && tuitionStandardStr.length()>1){
			tuitionStandardStr = tuitionStandardStr.substring(1,tuitionStandardStr.length()-1);
		}
		return tuitionStandardStr;
	}

}
