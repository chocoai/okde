/**
 * 文件名：BranchStudentStatusReportImpl.java
 * 包名：net.cedu.student.report.dao.impl
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2012-2-6 下午04:21:22
 *
 */
package net.cedu.student.report.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.Branch;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.BranchStudentStatusReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BranchStudentStatusReportImpl extends BaseReportDaoImpl implements
		BranchStudentStatusReport {

	@Autowired
	private BranchDao branchDao;

	private JdbcTemplatePlus jdbcTemplatePlus = null;

	public List statistics(Map<String, Integer> params,
			Map<String, Date> dateParams) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		jdbcTemplatePlus = getJdbcTemplatePlus();
		final int school = params.get("school");// 学院
		final int batch = params.get("batch");// 全局批次
		final int studentDataSource = params.get("studentDataSource");// 数据来源
		final int way = params.get("way");// 市场途径
		final int source = params.get("source");// 学生来源
		final int xuexiId = params.get("branch");// 学习中心
		final int city = params.get("city");// 城市
		final int status = params.get("status");// 状态(1,呼叫中心未处理、2,学习中心未处理、3,跟进、4,无意愿、5,已报名未缴学费、6,已报名已缴学费)

		final Date pushStartDate = dateParams.get("pushStartDate");// 推送开始日期
		final Date pushEndDate = dateParams.get("pushEndDate");// 推送结束日期
		
		//生成%数
		final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
		format.setMinimumFractionDigits(2);// 设置小数位

		try {
			// 获取所有学习中心
			List<Branch> branchList = null;
			if (xuexiId == -2) {
				branchList = branchDao.findAllNotDeleted();
			} else {
				branchList = new ArrayList<Branch>();
				branchList.add(branchDao.findById(xuexiId));
			}
			int hujiaoSum = 0;
			int xuexizhongxinSum = 0;
			int genjinSum = 0;
			int wuyiyuanSum = 0;
			int yibaomingSum = 0;
			int yijiaofeiSum = 0;
			int quanbuxinxinSum = 0;
			if (branchList != null) {
				// 获取统计数
				Map<String, Integer> branch_status_count_map = getAllBranchStudentStatusCount(school, batch, studentDataSource, way, source, xuexiId,city, status, pushStartDate, pushEndDate);
				// 获取学习中心所对应的缴费人数
				//Map<Integer, Integer> branch_jiaofei_count_map = getBranchJiaofeiStudentCount(school, batch, studentDataSource, way, source, xuexiId,city, status, pushStartDate, pushEndDate);

				
				for (Branch branch : branchList) {
					// 屏蔽roo以及总部
					if (branch.getLevel() != 0 && branch.getLevel() != -1) {
						Map<String, Object> mapObject = new HashMap<String, Object>();
						// 城市名称
						mapObject.put("cityName", branch.getShortName());
						// 学习中心
						mapObject.put("branchName", branch.getName());
						// 呼叫中心未处理的
						int hujiao=getCCWeiChuLiStudent(school, batch,studentDataSource, way, source,branch.getId(), branch.getId(), status,pushStartDate, pushEndDate);
						mapObject.put("cc_weichuli_count",hujiao);
						hujiaoSum+=hujiao;
						// 学习中心未处理的
						Integer lc_weichuli_count = 0;
						mapObject.put("lc_weichuli_count",
										lc_weichuli_count = (branch_status_count_map
												.get(branch.getId()
														+ "_"
														+ Constants.STU_CALL_STATUS_YI_DAO_RU) == null ? 0
												: branch_status_count_map
														.get(branch.getId()
																+ "_"
																+ Constants.STU_CALL_STATUS_YI_DAO_RU)));
						xuexizhongxinSum+=lc_weichuli_count;
						// 跟进状态的
						Integer genjingzhong_count = 0;
						mapObject.put("genjingzhong_count",genjingzhong_count = 
								(branch_status_count_map.get(branch.getId()+ "_"+ Constants.STU_CALL_STATUS_YI_FENG_PEI) == null ? 0: branch_status_count_map.get(branch.getId()+ "_"+ Constants.STU_CALL_STATUS_YI_FENG_PEI))
								+(branch_status_count_map.get(branch.getId()+ "_"+ Constants.STU_CALL_STATUS_GENG_JIN_ZHONG) == null ? 0: branch_status_count_map.get(branch.getId()+ "_"+ Constants.STU_CALL_STATUS_GENG_JIN_ZHONG))
								+(branch_status_count_map.get(branch.getId()+ "_"+ Constants.STU_CALL_STATUS_YI_DAO_MING) == null ? 0: branch_status_count_map.get(branch.getId()+ "_"+ Constants.STU_CALL_STATUS_YI_DAO_MING))
								
								);
						genjinSum+=genjingzhong_count;
						// 无意愿
						Integer wuyiyuan_count = 0;
						mapObject.put("wuyiyuan_count",wuyiyuan_count = (
								((branch_status_count_map.get(branch.getId()+ "_"+ Constants.STU_CALL_STATUS_WU_YI_YUAN) == null ? 0: branch_status_count_map.get(branch.getId()+ "_"+ Constants.STU_CALL_STATUS_WU_YI_YUAN))) ));
//						(branch_status_count_map
//								.get(branch.getId()
//										+ "_"
//										+ Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI) == null ? 0
//								: branch_status_count_map
//										.get(branch.getId()
//												+ "_"
//												+ Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI))
						wuyiyuanSum+=wuyiyuan_count;
						// 已报名
						Integer yibaoming_count = 0;
//						mapObject
//								.put("yibaoming_count",
//										yibaoming_count = (branch_status_count_map
//												.get(branch.getId()
//														+ "_"
//														+ Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI) == null ? 0
//												: branch_status_count_map
//														.get(branch.getId()
//																+ "_"
//																+ Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI)));
						mapObject
						.put("yibaoming_count",
								yibaoming_count = getYIBAOMINGStudent(school, batch,studentDataSource, way, source,branch.getId(), branch.getId(), status,pushStartDate, pushEndDate));
						yibaomingSum+=yibaoming_count;
//						// 已缴费
//						Integer yijiaofei_count = 0;
//						mapObject.put(
//								"yijiaofei_count",
//								yijiaofei_count = (branch_jiaofei_count_map
//										.get(branch.getId()) == null ? 0
//										: branch_jiaofei_count_map.get(branch
//												.getId())));
//						yijiaofeiSum+=yijiaofei_count;
						Integer quanbu=0;
						// 全部信息
//						mapObject.put("all_count",quanbu=(lc_weichuli_count
//								+ genjingzhong_count + wuyiyuan_count
//								+ yibaoming_count + yijiaofei_count));
						
						// 全部信息
						mapObject.put("all_count",quanbu=(lc_weichuli_count+ genjingzhong_count + wuyiyuan_count+ yibaoming_count ));
						//招生转换率
						if(quanbu!=null&&!quanbu.equals(0)){
							mapObject.put("yibaoming_count_p", format.format(Double.valueOf(yibaoming_count)/Double.valueOf(quanbu)));
						}else{
							mapObject.put("yibaoming_count_p", "-");
						}
						
						
						quanbuxinxinSum+=quanbu;
						resultList.add(mapObject);
					}

				}
			}
			// 合计
			Map<String, Object> mapObject = new HashMap<String, Object>();
			// 城市名称
			mapObject.put("cityName", "");
			// 学习中心
			mapObject.put("branchName", "合计");
			// 呼叫中心未处理的
			mapObject.put("cc_weichuli_count", hujiaoSum);
			mapObject.put("lc_weichuli_count", xuexizhongxinSum);
			mapObject.put("genjingzhong_count", genjinSum);
			mapObject.put("wuyiyuan_count", wuyiyuanSum);
			mapObject.put("yibaoming_count", yibaomingSum);
//			mapObject.put("yijiaofei_count", yijiaofeiSum);//
			// 全部信息
			mapObject.put("all_count", quanbuxinxinSum);
			
			//招生转换率
			if(quanbuxinxinSum!=0){
				mapObject.put("yibaoming_count_p", format.format(Double.valueOf(yibaomingSum)/Double.valueOf(quanbuxinxinSum)));
			}else{
				mapObject.put("yibaoming_count_p", "-");
			}

			resultList.add(mapObject);
			jdbcTemplatePlus.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
			jdbcTemplatePlus.releaseConnection();
			return resultList;
		}
		return resultList;
	}

	/**
	 * 
	 * @功能：获取学习中心 学生状态数量 Map
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2012-2-6 下午04:28:46
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return key：学习中心ID_学生状态 value:数量
	 */
	final public Map<String, Integer> getAllBranchStudentStatusCount(
			int school, int batch, int studentDataSource, int way, int source,
			int xuexiId, int city, int status, Date pushStartDate,
			Date pushEndDate) {
		// String sql =
		// "select branch_id,status,IFNULL(count(id),0) as count from tb_e_student where student_data_source in ("+Constants.STU_SOURCE_CC+","+Constants.STU_SOURCE_NP+") and  tuition_status="+Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI+" group by branch_id,status";
		String sql = "select branch_id,status,IFNULL(count(id),0) as count from tb_e_student where  tuition_status="
				+ Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI + " ";

		// 院校
		if (school != -2) {
			sql += " and academy_id=" + school;
		}

		// 招生批次
		if (batch != -2) {
			sql += " and ( enrollment_batch_id in ("
					+ getEnrollmentBatchId(school, batch) + ")";
		}
		// 全局批次
		if (batch != -2) {
			sql += " or global_batch ="+batch +")";
		}
		// 数据来源
		if (studentDataSource != -2) {
			sql += " and student_data_source=" + studentDataSource;
		} else {
			sql += " and student_data_source in (" + Constants.STU_SOURCE_CC
					+ "," + Constants.STU_SOURCE_NP + ")";
		}

		// 招生途径
		if (source != -2) {
			sql += " and enrollment_source=" + source;
		}
		// 市场途径
		if (way != -2) {
			sql += " and enrollment_way=" + way;
		}
		// 学习中心
		if (xuexiId != -2) {
			sql += " and branch_id=" + xuexiId;
		}
		// 城市
		if (city != -2) {
			Branch branch=branchDao.findById(city);
			if(branch!=null){
				sql += " and (branch_id=" + city + " or living_place like '%"
				+ branch.getShortName()+ "%')";
			}else{
				sql += " and branch_id=" + city ;
			}
			
		}
		// 状态(1,呼叫中心未处理、2,学习中心未处理、3,跟进、4,无意愿、5,已报名未缴学费、6,已报名已缴学费、7,已报名)
		if (status != -2) {
			switch (status) {
			case 1:
				sql += " and status=" + Constants.STU_CALL_STATUS_YU_BAO_MING;
				break;
			case 2:
				sql += " and status=" + Constants.STU_CALL_STATUS_YI_DAO_RU;// 已导入未分配
				break;
			case 3:
				sql += " and status="
						+ Constants.STU_CALL_STATUS_GENG_JIN_ZHONG;// 跟进中
				break;
			case 4:
				sql += " and status in ("
						+ Constants.STU_CALL_STATUS_WU_YI_YUAN + ","
						+ Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI + ")";
				break;
			default:
				break;
			}
		}

		// 推送时间
		if (pushStartDate != null && pushEndDate != null) {
			sql += " and ( created_time between '"
					+ DateUtil.getDate(pushStartDate, "yyyy-MM-dd")
					+ " 00:00:00' and '"
					+ DateUtil.getDate(pushEndDate, "yyyy-MM-dd")
					+ " 23:23:59' ) ";
		}
		if (pushStartDate != null && pushEndDate == null) {
			sql += " and created_time >='"
					+ DateUtil.getDate(pushStartDate, "yyyy-MM-dd")
					+ " 00:00:00'";
		}
		if (pushStartDate == null && pushEndDate != null) {
			sql += " and created_time <='"
					+ DateUtil.getDate(pushEndDate, "yyyy-MM-dd")
					+ " 23:23:59'";
		}

		sql += " group by branch_id,status";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("key", resultSet.getInt("branch_id") + "_"
								+ resultSet.getString("status"));
						map.put("value", resultSet.getString("count"));

						return map;
					}
				});
		if (list != null) {
			Map<String, Integer> resultMap = new HashMap<String, Integer>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key").toString(),
						Integer.valueOf(map.get("value")));
			}
			return resultMap;
		}
		return new HashMap<String, Integer>();
	}

	/**
	 * 获取学习中心对应的缴费学生Map
	 * 
	 * @return key:branchId value:缴费学生人数
	 */
	final public Map<Integer, Integer> getBranchJiaofeiStudentCount(int school,
			int batch, int studentDataSource, int way, int source, int xuexiId,
			int city, int status, Date pushStartDate, Date pushEndDate) {
		Map<Integer, Integer> mapResult = new HashMap<Integer, Integer>();
		// String sql
		// ="select branch_id,count(id) as jiaofei_count from tb_e_student where student_data_source in ("+Constants.STU_SOURCE_CC+","+Constants.STU_SOURCE_NP+")  and  id in (select DISTINCT student_id from tb_e_fee_payment_detail where fee_subject_id="+FeeSubjectEnum.TuitionFee.value()+")  and tuition_status>"+Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI+" group by branch_id";
//		String sql = "select branch_id,count(id) as jiaofei_count from tb_e_student where  id in (select DISTINCT student_id from tb_e_fee_payment_detail where fee_subject_id="+ FeeSubjectEnum.TuitionFee.value() + ") ";
		String sql = "select branch_id,count(id) as jiaofei_count from tb_e_student  where  tuition_status>="+ Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI + " ";
		
		// 院校
		if (school != -2) {
			sql += " and academy_id=" + school;
		}

		// 招生批次
		if (batch != -2) {
			sql += " and ( enrollment_batch_id in ("
					+ getEnrollmentBatchId(school, batch) + ")";
		}
		// 全局批次
		if (batch != -2) {
			sql += " or global_batch ="+batch +")";
		}
		// 数据来源
		if (studentDataSource != -2) {
			sql += " and student_data_source=" + studentDataSource;
		} else {
			sql += " and student_data_source in (" + Constants.STU_SOURCE_CC
					+ "," + Constants.STU_SOURCE_NP + ")";
		}

		// 招生途径
		if (source != -2) {
			sql += " and enrollment_source=" + source;
		}
		// 市场途径
		if (way != -2) {
			sql += " and enrollment_way=" + way;
		}
		// 学习中心
		if (xuexiId != -2) {
			sql += " and branch_id=" + xuexiId;
		}
		// 城市
		if (city != -2) {
			Branch branch=branchDao.findById(city);
			if(branch!=null){
				sql += " and (branch_id=" + city + " or living_place like '%"
				+ branch.getShortName()+ "%')";
			}else{
				sql += " and branch_id=" + city ;
			}
			
		}
		// 状态(1,呼叫中心未处理、2,学习中心未处理、3,跟进、4,无意愿、5,已报名未缴学费、6,已报名已缴学费、7,已报名)
		if (status != -2) {
			switch (status) {
			case 1:
				sql += " and status=" + Constants.STU_CALL_STATUS_YU_BAO_MING;
				break;
			case 2:
				sql += " and status=" + Constants.STU_CALL_STATUS_YI_DAO_RU;// 已导入未分配
				break;
			case 3:
				sql += " and status="
						+ Constants.STU_CALL_STATUS_GENG_JIN_ZHONG;// 跟进中
				break;
			case 4:
				sql += " and status in ("
						+ Constants.STU_CALL_STATUS_WU_YI_YUAN + ","
						+ Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI + ")";
				break;
			case 5:
//				sql += " and tuition_status="
//						+ Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI;
				sql += " and status>="
						+ Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI;
				break;
			case 6:
//				sql += " and tuition_status>"
//						+ Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI;
				sql += " and status>="
						+ Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI;

				break;
			default:
				break;
			}
		}

		// 推送时间
		if (pushStartDate != null && pushEndDate != null) {
			sql += " and ( created_time between '"
					+ DateUtil.getDate(pushStartDate, "yyyy-MM-dd")
					+ " 00:00:00' and '"
					+ DateUtil.getDate(pushEndDate, "yyyy-MM-dd")
					+ " 23:23:59' ) ";
		}
		if (pushStartDate != null && pushEndDate == null) {
			sql += " and created_time >='"
					+ DateUtil.getDate(pushStartDate, "yyyy-MM-dd")
					+ " 00:00:00'";
		}
		if (pushStartDate == null && pushEndDate != null) {
			sql += " and created_time <='"
					+ DateUtil.getDate(pushEndDate, "yyyy-MM-dd")
					+ " 23:23:59'";
		}

		sql += "  group by branch_id";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("jiaofei_count",
								resultSet.getInt("jiaofei_count"));
						return map;
					}
				});
		if (listResult != null) {
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("branch_id"), map.get("jiaofei_count"));
			}
		}
		return mapResult;
	}

	/**
	 * 呼叫中心未处理的学生数
	 * 
	 * @param livePlace所在城市
	 * @return
	 */
	final public int getCCWeiChuLiStudent(int school, int batch,
			int studentDataSource, int way, int source, int xuexiId, int city,
			int status, Date pushStartDate, Date pushEndDate) {
		// 预报名
		String sql = "select count(*) from tb_e_student where 1=1 ";
		// 院校
		if (school != -2) {
			sql += " and academy_id=" + school;
		}

//		// 招生批次
//		if (batch != -2) {
//			sql += " and enrollment_batch_id in ("
//					+ getEnrollmentBatchId(school, batch) + ")";
//		}
		// 全局批次
		if (batch != -2) {
			sql += " and global_batch ="+batch;
		}
		// 数据来源
		if (studentDataSource != -2) {
			sql += " and student_data_source=" + studentDataSource;
		} else {
			sql += " and student_data_source in (" + Constants.STU_SOURCE_CC
					+ "," + Constants.STU_SOURCE_NP + ")";
		}

		// 招生途径
		if (source != -2) {
			sql += " and enrollment_source=" + source;
		}
		// 市场途径
		if (way != -2) {
			sql += " and enrollment_way=" + way;
		}
		// 学习中心
		if (xuexiId != -2) {
			sql += " and branch_id=" + xuexiId;
		}
		// 城市
		if (city != -2) {
			Branch branch=branchDao.findById(city);
			if(branch!=null){
				sql += " and (branch_id=" + city + " or living_place like '%"
				+ branch.getShortName()+ "%')";
			}else{
				sql += " and branch_id=" + city ;
			}
			
		}
		// 推送时间
		if (pushStartDate != null && pushEndDate != null) {
			sql += " and ( created_time between '"
					+ DateUtil.getDate(pushStartDate, "yyyy-MM-dd")
					+ " 00:00:00' and '"
					+ DateUtil.getDate(pushEndDate, "yyyy-MM-dd")
					+ " 23:23:59' ) ";
		}
		if (pushStartDate != null && pushEndDate == null) {
			sql += " and created_time >='"
					+ DateUtil.getDate(pushStartDate, "yyyy-MM-dd")
					+ " 00:00:00'";
		}
		if (pushStartDate == null && pushEndDate != null) {
			sql += " and created_time <='"
					+ DateUtil.getDate(pushEndDate, "yyyy-MM-dd")
					+ " 23:23:59'";
		}
		
		sql += " and status=" + Constants.STU_CALL_STATUS_YU_BAO_MING;
		
		//System.out.println(sql);
		return jdbcTemplatePlus.queryForInt(sql);
	}
	
	final public int getYIBAOMINGStudent(int school, int batch,
			int studentDataSource, int way, int source, int xuexiId, int city,
			int status, Date pushStartDate, Date pushEndDate) {
		// 预报名
		String sql = "select count(*) from tb_e_student where 1=1 ";
		// 院校
		if (school != -2) {
			sql += " and academy_id=" + school;
		}

//		// 招生批次
//		if (batch != -2) {
//			sql += " and enrollment_batch_id in ("
//					+ getEnrollmentBatchId(school, batch) + ")";
//		}
		// 全局批次
		if (batch != -2) {
			sql += " and global_batch ="+batch;
		}
		// 数据来源
		if (studentDataSource != -2) {
			sql += " and student_data_source=" + studentDataSource;
		} else {
			sql += " and student_data_source in (" + Constants.STU_SOURCE_CC
					+ "," + Constants.STU_SOURCE_NP + ")";
		}

		// 招生途径
		if (source != -2) {
			sql += " and enrollment_source=" + source;
		}
		// 市场途径
		if (way != -2) {
			sql += " and enrollment_way=" + way;
		}
		// 学习中心
		if (xuexiId != -2) {
			sql += " and branch_id=" + xuexiId;
		}
		// 城市
		if (city != -2) {
			Branch branch=branchDao.findById(city);
			if(branch!=null){
				sql += " and (branch_id=" + city + " or living_place like '%"
				+ branch.getShortName()+ "%')";
			}else{
				sql += " and branch_id=" + city ;
			}
			
		}
		// 推送时间
		if (pushStartDate != null && pushEndDate != null) {
			sql += " and ( created_time between '"
					+ DateUtil.getDate(pushStartDate, "yyyy-MM-dd")
					+ " 00:00:00' and '"
					+ DateUtil.getDate(pushEndDate, "yyyy-MM-dd")
					+ " 23:23:59' ) ";
		}
		if (pushStartDate != null && pushEndDate == null) {
			sql += " and created_time >='"
					+ DateUtil.getDate(pushStartDate, "yyyy-MM-dd")
					+ " 00:00:00'";
		}
		if (pushStartDate == null && pushEndDate != null) {
			sql += " and created_time <='"
					+ DateUtil.getDate(pushEndDate, "yyyy-MM-dd")
					+ " 23:23:59'";
		}
		
		sql += " and status>=" + Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI;
		sql += " and status<>" + Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI;
		
		//System.out.println(sql);
		return jdbcTemplatePlus.queryForInt(sql);
	}

	// 通过全局批次，以及院校ID查询院校的招生批次Id字符串
	final public String getEnrollmentBatchId(int school, int globalEnrollBatchId) {

		if (globalEnrollBatchId == -2) {
			return "0";
		}

		String ids = ",";
		String sql = "select IFNULL(id,0) as id from tb_e_academy_enroll_batch where global_enroll_batch_id="
				+ globalEnrollBatchId;
		if (school != -2) {
			sql += " and academy_id=" + school;
			try {
				return jdbcTemplatePlus.queryForInt(sql) + "";
			} catch (Exception e) {
				return "-1";
			}
		}

		List<HashMap<String, Long>> list = jdbcTemplatePlus.queryForList(sql);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Long> map = list.get(i);
				if (ids.equals(",")) {
					ids = map.get("id") + "";
				} else {
					ids += "," + map.get("id");
				}
			}
		}
		if (ids.equals(","))
			ids = "0";

		return ids;
	}

}
