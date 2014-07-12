package net.cedu.student.report.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.Branch;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.EnrollmentMonitorReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentMonitorReportImpl extends BaseReportDaoImpl implements EnrollmentMonitorReport {

	@Autowired
	private FeePaymentDetailDao feePaymentDetailDao;
	@Autowired
	private BranchBiz branchBiz;
	
	private JdbcTemplatePlus jdbcTemplatePlus = null;
	
	public Map statistics(Map<String, Integer> params, Map<String, Date> dateParams) {
		
		jdbcTemplatePlus = getJdbcTemplatePlus();
		
		final int school = params.get("school");// 学院
		final int gbatch = params.get("batch");// 全局批次
		final int studentDataSource = params.get("studentDataSource");// 数据来源
		final int way = params.get("way");// 市场途径
		final int enrollmentSource = params.get("source");// 招生途径
		final int branchId = params.get("branch");// 学习中心
		final Date startDate = dateParams.get("startDate");// 开始日期
		final Date endDate = dateParams.get("endDate");// 结束日期
		
		//1.初始化map集合
		// 获取学习中心及其下属服务站id
		String branchIds = "";
		if(branchId>0){
			try {
				List<Branch> branchList = branchBiz.findListById(branchId);
				if(branchList!=null && branchList.size()>0){
					for(Branch branch : branchList){
						if(branchIds.equals("")){
							branchIds = branch.getId()+"";
						}else{
							branchIds += ","+branch.getId();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 学习中心ID＝招生指标数
		final Map<String,Integer> key_a_b_value_zhaoshengzhibiao_map=initZhaoShengZhiBiaoMap(gbatch, branchIds);
		
		// 学习中心ID＝本周招生人数
		final Map<String,Integer> key_a_b_value_zhaoshengrenshu_week_map=getZhaoShengRenShuMapByWeek(school,gbatch, studentDataSource, way, enrollmentSource, branchIds, startDate, endDate);
		// 学习中心ID＝本周缴费人数
		final Map<String,Integer> key_a_b_value_jiaofeirenshu_week_map=getJiaoFeiRenShuMapByWeek(school,gbatch, studentDataSource, way, enrollmentSource, branchIds, startDate, endDate);
		// 学习中心ID_缴费科目＝本周缴费金额
		final Map<String,Double> key_a_b_value_jiaofeijine_week_map=getSumPaymentByStudentAndFPDWeek(school,gbatch, studentDataSource, way, enrollmentSource, branchIds, startDate, endDate);
		
		// 学习中心ID＝当前批次招生人数
		final Map<String,Integer> key_a_b_value_zhaoshengrenshu_batch_map=getZhaoShengRenShuMapByBatch(school,gbatch, studentDataSource, way, enrollmentSource, branchIds);
		// 学习中心ID＝当前批次缴费人数
		final Map<String,Integer> key_a_b_value_jiaofeirenshu_batch_map=getJiaoFeiRenShuMapByBatch(school,gbatch, studentDataSource, way, enrollmentSource, branchIds);
		// 学习中心ID_缴费科目＝当前批次缴费金额
		final Map<String,Double> key_a_b_value_jiaofeijine_batch_map=getSumPaymentByStudentAndFPDBatch(school,gbatch, studentDataSource, way, enrollmentSource, branchIds);
		
		//生成%数
		final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
		format.setMinimumFractionDigits(2);// 设置小数位
		//金钱
		final DecimalFormat format_=new DecimalFormat();
		format_.setMinimumFractionDigits(2);
		//2.获取所有中心(不包括服务站)
		String branchSql=" select id,name from tb_e_branch where delete_flag="+Constants.DELETE_FALSE+" and id>"+BranchEnum.Admin.value()+" and levels <= 1";
		//如果单独查询中心则不排除会查询服务站，所以sql重写
		if(branchId!=-2){
			branchSql=" select id,name from tb_e_branch where delete_flag="+Constants.DELETE_FALSE+" and id>"+BranchEnum.Admin.value()+" ";
			branchSql+=" and id="+branchId;
		}
		
		//合计List
		final List<Integer> zhiBaoList=new ArrayList<Integer>();
		
		final List<Integer> weekZhaoShengRenShuCountList=new ArrayList<Integer>();
		final List<Integer> weekJiaoFeiRenShuCountList=new ArrayList<Integer>();
		final List<Double> weekXueFeiSumList=new ArrayList<Double>();
		final List<Double> weekBaoMingFeiSumList=new ArrayList<Double>();
		final List<Double> weekCeShiFeiSumList=new ArrayList<Double>();
		final List<Double> weekJiaoCaiFeiSumList=new ArrayList<Double>();
		final List<Double> weekTongKaoPeiXunFeiSumList=new ArrayList<Double>();
		
		final List<Integer> batchZhaoShengRenShuCountList=new ArrayList<Integer>();
		final List<Integer> batchJiaoFeiRenShuCountList=new ArrayList<Integer>();
		final List<Double> batchXueFeiSumList=new ArrayList<Double>();
		final List<Double> batchBaoMingFeiSumList=new ArrayList<Double>();
		final List<Double> batchCeShiFeiSumList=new ArrayList<Double>();
		final List<Double> batchJiaoCaiFeiSumList=new ArrayList<Double>();
		final List<Double> batchTongKaoPeiXunFeiSumList=new ArrayList<Double>();
		
		List branchList = jdbcTemplatePlus.query(branchSql, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int index)throws SQLException {
				Map<String, Object> branchMap = new HashMap<String, Object>();
				branchMap.put("branch_id", resultSet.getInt("id"));
				branchMap.put("branch_name", resultSet.getString("name"));
				
				final List<Integer> weekZhaoShengRenShuList = new ArrayList<Integer>();
				final List<Integer> weekJiaoFeiRenShuList = new ArrayList<Integer>();
				final List<Double> weekXueFeiList = new ArrayList<Double>();
				final List<Double> weekBaoMingFeiList = new ArrayList<Double>();
				final List<Double> weekCeShiFeiList = new ArrayList<Double>();
				final List<Double> weekJiaoCaiFeiList = new ArrayList<Double>();
				final List<Double> weekTongKaoPeiXunFeiList = new ArrayList<Double>();
				
				final List<Integer> branchZhiBiaoList = new ArrayList<Integer>();
				final List<Integer> batchZhaoShengRenShuList = new ArrayList<Integer>();
				final List<Integer> batchJiaoFeiRenShuList = new ArrayList<Integer>();
				final List<Double> batchXueFeiList = new ArrayList<Double>();
				final List<Double> batchBaoMingFeiList = new ArrayList<Double>();
				final List<Double> batchCeShiFeiList = new ArrayList<Double>();
				final List<Double> batchJiaoCaiFeiList = new ArrayList<Double>();
				final List<Double> batchTongKaoPeiXunFeiList = new ArrayList<Double>();
				
				
				// 本周招生人数
				weekZhaoShengRenShuList.add(getIntValue(key_a_b_value_zhaoshengrenshu_week_map,resultSet.getInt("id")+""));
				// 本周缴费人数
				weekJiaoFeiRenShuList.add(getIntValue(key_a_b_value_jiaofeirenshu_week_map,resultSet.getInt("id")+""));
				// 本周缴费金额（学费）
				weekXueFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_week_map,resultSet.getInt("id")+"_"+FeeSubjectEnum.TuitionFee.value()));
				// 本周缴费金额（报名费）
				weekBaoMingFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_week_map,resultSet.getInt("id")+"_"+FeeSubjectEnum.RegistrationFee.value()));
				// 本周缴费金额（测试费）
				weekCeShiFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_week_map,resultSet.getInt("id")+"_"+FeeSubjectEnum.TestFee.value()));
				// 本周缴费金额（教材费）
				weekJiaoCaiFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_week_map,resultSet.getInt("id")+"_"+FeeSubjectEnum.Textbooks.value()));
				// 本周缴费金额（统考培训费）
				weekTongKaoPeiXunFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_week_map,resultSet.getInt("id")+"_"+FeeSubjectEnum.TongKaoPeiXunFee.value()));
				
				// 指标
				branchZhiBiaoList.add(getIntValue(key_a_b_value_zhaoshengzhibiao_map,resultSet.getInt("id")+""));
				// 当前批次招生人数
				batchZhaoShengRenShuList.add(getIntValue(key_a_b_value_zhaoshengrenshu_batch_map,resultSet.getInt("id")+""));
				// 当前批次缴费人数
				batchJiaoFeiRenShuList.add(getIntValue(key_a_b_value_jiaofeirenshu_batch_map,resultSet.getInt("id")+""));
				// 当前批次缴费金额（学费）
				batchXueFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_batch_map,resultSet.getInt("id")+"_"+FeeSubjectEnum.TuitionFee.value()));
				// 当前批次缴费金额（报名费）
				batchBaoMingFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_batch_map,resultSet.getInt("id")+"_"+FeeSubjectEnum.RegistrationFee.value()));
				// 当前批次缴费金额（测试费）
				batchCeShiFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_batch_map,resultSet.getInt("id")+"_"+FeeSubjectEnum.TestFee.value()));
				// 当前批次缴费金额（教材费）
				batchJiaoCaiFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_batch_map,resultSet.getInt("id")+"_"+FeeSubjectEnum.Textbooks.value()));
				// 当前批次缴费金额（统考培训费）
				batchTongKaoPeiXunFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_batch_map,resultSet.getInt("id")+"_"+FeeSubjectEnum.TongKaoPeiXunFee.value()));
				
				// 学习中心下面的服务站
				String fuwuSql="select id as fuwuId from tb_e_branch where parent_id="+ resultSet.getInt("id");
				jdbcTemplatePlus.query(fuwuSql, new RowMapper() {
					public Object mapRow(ResultSet resultSet, int index)throws SQLException {
						// 本周招生人数
						weekZhaoShengRenShuList.add(getIntValue(key_a_b_value_zhaoshengrenshu_week_map,resultSet.getInt("fuwuId")+""));
						// 本周缴费人数
						weekJiaoFeiRenShuList.add(getIntValue(key_a_b_value_jiaofeirenshu_week_map,resultSet.getInt("fuwuId")+""));
						// 本周缴费金额（学费）
						weekXueFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_week_map,resultSet.getInt("fuwuId")+"_"+FeeSubjectEnum.TuitionFee.value()));
						// 本周缴费金额（报名费）
						weekBaoMingFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_week_map,resultSet.getInt("fuwuId")+"_"+FeeSubjectEnum.RegistrationFee.value()));
						// 本周缴费金额（测试费）
						weekCeShiFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_week_map,resultSet.getInt("fuwuId")+"_"+FeeSubjectEnum.TestFee.value()));
						// 本周缴费金额（教材费）
						weekJiaoCaiFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_week_map,resultSet.getInt("fuwuId")+"_"+FeeSubjectEnum.Textbooks.value()));
						// 本周缴费金额（统考培训费）
						weekTongKaoPeiXunFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_week_map,resultSet.getInt("fuwuId")+"_"+FeeSubjectEnum.TongKaoPeiXunFee.value()));
						
						// 指标
						branchZhiBiaoList.add(getIntValue(key_a_b_value_zhaoshengzhibiao_map,resultSet.getInt("fuwuId")+""));
						// 当前批次招生人数
						batchZhaoShengRenShuList.add(getIntValue(key_a_b_value_zhaoshengrenshu_batch_map,resultSet.getInt("fuwuId")+""));
						// 当前批次缴费人数
						batchJiaoFeiRenShuList.add(getIntValue(key_a_b_value_jiaofeirenshu_batch_map,resultSet.getInt("fuwuId")+""));
						// 当前批次缴费金额（学费）
						batchXueFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_batch_map,resultSet.getInt("fuwuId")+"_"+FeeSubjectEnum.TuitionFee.value()));
						// 当前批次缴费金额（报名费）
						batchBaoMingFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_batch_map,resultSet.getInt("fuwuId")+"_"+FeeSubjectEnum.RegistrationFee.value()));
						// 当前批次缴费金额（测试费）
						batchCeShiFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_batch_map,resultSet.getInt("fuwuId")+"_"+FeeSubjectEnum.TestFee.value()));
						// 当前批次缴费金额（教材费）
						batchJiaoCaiFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_batch_map,resultSet.getInt("fuwuId")+"_"+FeeSubjectEnum.Textbooks.value()));
						// 当前批次缴费金额（统考培训费）
						batchTongKaoPeiXunFeiList.add(getDoubleValue(key_a_b_value_jiaofeijine_batch_map,resultSet.getInt("fuwuId")+"_"+FeeSubjectEnum.TongKaoPeiXunFee.value()));
						return null;
					}
				});
				
				// 本周招生人数
				int weekZhaoShengRenShu = getListSum(weekZhaoShengRenShuList);
				branchMap.put("branch_week_zhaoshengrenshu",weekZhaoShengRenShu);
				// 本周缴费人数
				int weekJiaoFeiRenShu = getListSum(weekJiaoFeiRenShuList);
				branchMap.put("branch_week_jiaofeirenshu", weekJiaoFeiRenShu);
				// 本周缴费金额（学费）
				double weekXueFei = getListSum_(weekXueFeiList);
				branchMap.put("branch_week_xuefei", format_.format(weekXueFei));
				// 本周缴费金额（报名费）
				double weekBaoMingFei = getListSum_(weekBaoMingFeiList);
				branchMap.put("branch_week_baomingfei", format_.format(weekBaoMingFei));
				// 本周缴费金额（测试费）
				double weekCeShiFei = getListSum_(weekCeShiFeiList);
				branchMap.put("branch_week_ceshifei", format_.format(weekCeShiFei));
				// 本周缴费金额（教材费）
				double weekJiaoCaiFei = getListSum_(weekJiaoCaiFeiList);
				branchMap.put("branch_week_jiaocaifei", format_.format(weekJiaoCaiFei));
				// 本周缴费金额（统考培训费）
				double weekTongKaoPeiXunFei = getListSum_(weekTongKaoPeiXunFeiList);
				branchMap.put("branch_week_tongkaopeixunfei", format_.format(weekTongKaoPeiXunFei));
				
				// 指标
				int zhiBiao = getListSum(branchZhiBiaoList);
				branchMap.put("branch_zhibiao", zhiBiao);
				// 当前批次招生人数
				int batchZhaoShengRenShu = getListSum(batchZhaoShengRenShuList);
				branchMap.put("branch_batch_zhaoshengrenshu", batchZhaoShengRenShu);
				// 指标完成率
				branchMap.put("branch_batch_wanchenglv", getP(format, batchZhaoShengRenShu,zhiBiao));
				// 当前批次缴费人数
				int batchJiaoFeiRenShu = getListSum(batchJiaoFeiRenShuList);
				branchMap.put("branch_batch_jiaofeirenshu", batchJiaoFeiRenShu);
				// 缴费率
				branchMap.put("branch_batch_jiaofeilv", getP(format, batchJiaoFeiRenShu,batchZhaoShengRenShu));
				// 当前批次缴费金额（学费）
				double batchXueFei = getListSum_(batchXueFeiList);
				branchMap.put("branch_batch_xuefei", format_.format(batchXueFei));
				// 当前批次缴费金额（报名费）
				double batchBaoMingFei = getListSum_(batchBaoMingFeiList);
				branchMap.put("branch_batch_baomingfei", format_.format(batchBaoMingFei));
				// 当前批次缴费金额（测试费）
				double batchCeShiFei = getListSum_(batchCeShiFeiList);
				branchMap.put("branch_batch_ceshifei", format_.format(batchCeShiFei));
				// 当前批次缴费金额（教材费）
				double batchJiaoCaiFei = getListSum_(batchJiaoCaiFeiList);
				branchMap.put("branch_batch_jiaocaifei", format_.format(batchJiaoCaiFei));
				// 当前批次缴费金额（统考培训费）
				double batchTongKaoPeiXunFei = getListSum_(batchTongKaoPeiXunFeiList);
				branchMap.put("branch_batch_tongkaopeixunfei", format_.format(batchTongKaoPeiXunFei));
				
				// 本周
				weekZhaoShengRenShuCountList.add(weekZhaoShengRenShu);
				weekJiaoFeiRenShuCountList.add(weekJiaoFeiRenShu);
				weekXueFeiSumList.add(weekXueFei);
				weekBaoMingFeiSumList.add(weekBaoMingFei);
				weekCeShiFeiSumList.add(weekCeShiFei);
				weekJiaoCaiFeiSumList.add(weekJiaoCaiFei);
				weekTongKaoPeiXunFeiSumList.add(weekTongKaoPeiXunFei);
				
				// 指标
				zhiBaoList.add(zhiBiao);
				
				// 当前批次
				batchZhaoShengRenShuCountList.add(batchZhaoShengRenShu);
				batchJiaoFeiRenShuCountList.add(batchJiaoFeiRenShu);
				batchXueFeiSumList.add(batchXueFei);
				batchBaoMingFeiSumList.add(batchBaoMingFei);
				batchCeShiFeiSumList.add(batchCeShiFei);
				batchJiaoCaiFeiSumList.add(batchJiaoCaiFei);
				batchTongKaoPeiXunFeiSumList.add(batchTongKaoPeiXunFei);
				
				return branchMap;
			}
		});
		
		Map<String,Object> sumMap=new HashMap<String, Object>();
		//总合计
		sumMap.put("sum_branch_week_zhaoshengrenshu", getListSum(weekZhaoShengRenShuCountList));
		sumMap.put("sum_branch_week_jiaofeirenshu", getListSum(weekJiaoFeiRenShuCountList));
		sumMap.put("sum_branch_week_xuefei", format_.format(getListSum_(weekXueFeiSumList)));
		sumMap.put("sum_branch_week_baomingfei", format_.format(getListSum_(weekBaoMingFeiSumList)));
		sumMap.put("sum_branch_week_ceshifei", format_.format(getListSum_(weekCeShiFeiSumList)));
		sumMap.put("sum_branch_week_jiaocaifei", format_.format(getListSum_(weekJiaoCaiFeiSumList)));
		sumMap.put("sum_branch_week_tongkaopeixunfei", format_.format(getListSum_(weekTongKaoPeiXunFeiSumList)));
		
		sumMap.put("sum_branch_zhibiao", getListSum(zhiBaoList));
		
		sumMap.put("sum_branch_batch_zhaoshengrenshu", getListSum(batchZhaoShengRenShuCountList));
		sumMap.put("sum_branch_batch_wanchenglv", getP(format, getListSum(batchZhaoShengRenShuCountList),getListSum(zhiBaoList)));
		sumMap.put("sum_branch_batch_jiaofeirenshu", getListSum(batchJiaoFeiRenShuCountList));
		sumMap.put("sum_branch_batch_jiaofeilv", getP(format, getListSum(batchJiaoFeiRenShuCountList),getListSum(batchZhaoShengRenShuCountList)));
		sumMap.put("sum_branch_batch_xuefei", format_.format(getListSum_(batchXueFeiSumList)));
		sumMap.put("sum_branch_batch_baomingfei", format_.format(getListSum_(batchBaoMingFeiSumList)));
		sumMap.put("sum_branch_batch_ceshifei", format_.format(getListSum_(batchCeShiFeiSumList)));
		sumMap.put("sum_branch_batch_jiaocaifei", format_.format(getListSum_(batchJiaoCaiFeiSumList)));
		sumMap.put("sum_branch_batch_tongkaopeixunfei", format_.format(getListSum_(batchTongKaoPeiXunFeiSumList)));
		
		sumMap.put("branchList", branchList);
		return sumMap;
	}
	
	// 获取本周招生人数
	private Map<String,Integer> getZhaoShengRenShuMapByWeek(int school,
			int gbatch, int studentDataSource, int way, int enrollmentSource,
			String branchIds, Date startDate, Date endDate){
		if(startDate!=null){
//			Date date1 = DateUtil.getMonday(startDate);//获取周一
//			Date date2 = DateUtil.getSunday(startDate);//获取周日
			return initZhaoShengRenShuMap(school,gbatch, studentDataSource, way, enrollmentSource, branchIds, startDate, endDate);
		}
		return new HashMap<String,Integer>();
	}
	
	// 获取当前批次招生人数
	private Map<String,Integer> getZhaoShengRenShuMapByBatch(int school,
			int gbatch, int studentDataSource, int way, int enrollmentSource,
			String branchIds){
		if(gbatch>0){
			return initZhaoShengRenShuMap(school,gbatch, studentDataSource, way, enrollmentSource, branchIds, null, null);
		}
		return new HashMap<String,Integer>();
	}
	
	/*
	 * 中心招生人数(key:学习中心ID value:招生人数)
	 */
	private Map<String,Integer> initZhaoShengRenShuMap(int school,
			int gbatch, int studentDataSource, int way, int enrollmentSource,
			String branchIds, Date startDate, Date endDate) {
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql = " select branch_id,count(id) as stu_count "
					+" from tb_e_student "
					+" where 1=1 "
					+" and status >= "+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+" "
					+" and status <= "+Constants.STU_CALL_STATUS_QU_XIAO_XUE_JI+" ";
		if(school>0)
		{
			sql +=" and academy_id = "+school+" ";
		}
		if(gbatch>0)
		{
			sql +=" and enrollment_batch_id in ( "+getEnrollmentBatchId(gbatch)+" ) ";
		}
		if(studentDataSource>0)
		{
			sql +=" and student_data_source = "+studentDataSource+" ";
		}
		if(way>0)
		{
			sql +=" and enrollment_way = "+way+" ";
		}
		if(enrollmentSource>0)
		{
			sql +=" and enrollment_source = "+enrollmentSource+" ";
		}
		if(!branchIds.equals(""))
		{
			sql +=" and branch_id in ( "+branchIds+" ) ";
		}
		if(startDate!=null)
		{
			sql +=" and registration_time >= '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00' ";
		}
		if(endDate!=null)
		{
			sql +=" and registration_time <= '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59' ";
		}
		sql +=" group by branch_id ";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("stu_count", resultSet.getInt("stu_count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("branch_id")+"", map.get("stu_count"));
			}
		}
		return mapResult;
	}
	
	// 获取本周缴费人数
	private Map<String,Integer> getJiaoFeiRenShuMapByWeek(int school,
			int gbatch, int studentDataSource, int way, int enrollmentSource,
			String branchIds, Date startDate, Date endDate){
		if(startDate!=null){
//			Date date1 = DateUtil.getMonday(startDate);//获取周一
//			Date date2 = DateUtil.getSunday(startDate);//获取周日
			return initJiaoFeiRenShuMap(school,gbatch, studentDataSource, way, enrollmentSource, branchIds, startDate, endDate);
		}
		return new HashMap<String,Integer>();
	}
	
	// 获取当前批次缴费人数
	private Map<String,Integer> getJiaoFeiRenShuMapByBatch(int school,
			int gbatch, int studentDataSource, int way, int enrollmentSource,
			String branchIds){
		if(gbatch>0){
			return initJiaoFeiRenShuMap(school,gbatch, studentDataSource, way, enrollmentSource, branchIds, null, null);
		}
		return new HashMap<String,Integer>();
	}
	
	/*
	 * 缴费人数(学费)(key:学习中心ID value:招生人数)
	 */
	private Map<String,Integer> initJiaoFeiRenShuMap(int school,
			int gbatch, int studentDataSource, int way, int enrollmentSource,
			String branchIds, Date startDate, Date endDate) {
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql = " select a.branch_id,count(stu) as stu_count from "
					+" ( "
					+" select "
					+" s.branch_id, "
					+" count(f.id) as stu "
					+" from "
					+" tb_e_fee_payment_detail as f,tb_e_student as s "
					+" where "
					+" f.student_id = s.id "
					+" and f.fee_subject_id = "+FeeSubjectEnum.TuitionFee.value()+" "
					+" and f.types = "+Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE+" "
					+" and f.status >= "+Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN+" "
					+" and f.delete_flag = "+Constants.DELETE_FALSE+" ";
					
		if(school>0)
		{
			sql +=" and s.academy_id = "+school+" ";
		}
		if(gbatch>0)
		{
			sql +=" and s.enrollment_batch_id in ( "+getEnrollmentBatchId(gbatch)+" ) ";
		}
		if(studentDataSource>0)
		{
			sql +=" and s.student_data_source = "+studentDataSource+" ";
		}
		if(way>0)
		{
			sql +=" and s.enrollment_way = "+way+" ";
		}
		if(enrollmentSource>0)
		{
			sql +=" and s.enrollment_source = "+enrollmentSource+" ";
		}
		if(!branchIds.equals(""))
		{
			sql +=" and s.branch_id in ( "+branchIds+" ) ";
		}
		if(startDate!=null)
		{
			sql +=" and f.created_time >= '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00' ";
		}
		if(endDate!=null)
		{
			sql +=" and f.created_time <= '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59' ";
		}
		sql +=" group by s.branch_id,s.id "
			+ " ) as a "
			+ " group by a.branch_id ";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("stu_count", resultSet.getInt("stu_count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("branch_id")+"", map.get("stu_count"));
			}
		}
		return mapResult;
	}
	
	/*
	 * 中心招生指标(key:学习中心ID value:指标数)
	 */
	private Map<String,Integer> initZhaoShengZhiBiaoMap(int gbatch,String branchIds) {
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql = " select branch_id,target "
					+" from tb_e_branch_enroll_quota "
					+" where 1=1 "
					+" and delete_flag="+Constants.DELETE_FALSE+" ";
		if(gbatch>0){
			sql += " and batch_id= "+gbatch;
		}
		if(!branchIds.equals("")){
			sql += " and branch_id in ( "+branchIds+" ) ";
		}
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("target", resultSet.getInt("target"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("branch_id")+"", map.get("target"));
			}
		}
		return mapResult;
	}
	
	// 获取本周缴费金额
	private Map<String, Double> getSumPaymentByStudentAndFPDWeek(int school,
			int gbatch, int studentDataSource, int way, int enrollmentSource,
			String branchIds, Date startDate, Date endDate){
		if(startDate!=null){
//			Date date1 = DateUtil.getMonday(startDate);//获取周一
//			Date date2 = DateUtil.getSunday(startDate);//获取周日
			return findSumPaymentByStudentAndFPD(school,gbatch, studentDataSource, way, enrollmentSource, branchIds, startDate, endDate);
		}
		return new HashMap<String,Double>();
	}
	
	// 获取当前批次缴费金额
	private Map<String, Double> getSumPaymentByStudentAndFPDBatch(int school,
			int gbatch, int studentDataSource, int way, int enrollmentSource,
			String branchIds){
		if(gbatch>0){
			return findSumPaymentByStudentAndFPD(school,gbatch, studentDataSource, way, enrollmentSource, branchIds, null, null);
		}
		return new HashMap<String,Double>();
	}
	
	/*
	 * 缴费金额(Map key:学习中心ID_缴费科目 value:缴费金额)
	 */
	private Map<String, Double> findSumPaymentByStudentAndFPD(int school,
			int gbatch, int studentDataSource, int way, int enrollmentSource,
			String branchIds, Date startDate, Date endDate) {
		Map<String,Double> map = new HashMap<String,Double>();
		String sql = " select "
					+" s.branch_id,"
					+" f.fee_subject_id,"
					+" sum((f.amount_paied+f.recharge_amount-f.refund_amount)) as fsum "
					+"from "
					+" tb_e_fee_payment_detail as f,tb_e_student as s "
					+"where "
					+" f.student_id = s.id "
					+" and f.types = "+Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE+" "
					+" and f.status >= "+Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN+" "
					+" and f.delete_flag = "+Constants.DELETE_FALSE+" ";
		if(school>0)
		{
			sql +=" and s.academy_id = "+school+" ";
		}
		if(gbatch>0)
		{
			sql +=" and s.enrollment_batch_id in ( "+getEnrollmentBatchId(gbatch)+" ) ";
		}
		if(studentDataSource>0)
		{
			sql +=" and s.student_data_source = "+studentDataSource+" ";
		}
		if(way>0)
		{
			sql +=" and s.enrollment_way = "+way+" ";
		}
		if(enrollmentSource>0)
		{
			sql +=" and s.enrollment_source = "+enrollmentSource+" ";
		}
		if(!branchIds.equals(""))
		{
			sql +=" and s.branch_id in ( "+branchIds+" ) ";
		}
		if(startDate!=null)
		{
			sql +=" and f.created_time >= '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00' ";
		}
		if(endDate!=null)
		{
			sql +=" and f.created_time <= '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59' ";
		}
		sql +=" group by s.branch_id,f.fee_subject_id";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String,Double>> list = super.getJdbcTemplatePlus().query(sql,
				new RowMapper() {
					public Map<String,Double> mapRow(ResultSet resultSet, int index) throws SQLException
					{
						Map<String,Double> map =new HashMap<String,Double>();
						map.put(resultSet.getInt("branch_id")+"_"+resultSet.getInt("fee_subject_id"), resultSet.getDouble("fsum"));
						return map;
					}
		});
		if(list!=null){
			for (Map<String, Double> map2 : list) {
				for(Map.Entry<String, Double> entry:map2.entrySet()){
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return map;
	}
	
	//通过全局批次查询招生批次Id字符串
	final public String getEnrollmentBatchId(int globalEnrollBatchId){
		
		if(globalEnrollBatchId==-2){
			return "0";
		}
		
		String ids=",";
		String sql="select IFNULL(id,0) as id from tb_e_academy_enroll_batch where global_enroll_batch_id="+globalEnrollBatchId;
		
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
	
	final private int getIntValue(Map<String,Integer> map ,String key){
		return map.get(key)!=null?map.get(key):0;
	}
	final private double getDoubleValue(Map<String,Double> map ,String key){
		return map.get(key)!=null?map.get(key):0.00d;
	}
	final private String getP(final NumberFormat format,int number1,int number2){
		if(number2==0){
			return "-";
		}
		return format.format(new Float(number1)/ new Float(getDayuOne(getDayuOne(number2))));
	}
	final private int getDayuOne(int number){
		return number==0?1:number;
	}
	final private int getListSum(List<Integer> list){
		int count=0;
		if(list!=null){
			for (Integer c : list) {
				count+=c;
			}
		}
		return count;
	}
	final private double getListSum_(List<Double> list){
		double count=0;
		if(list!=null){
			for (Double c : list) {
				count+=c;
			}
		}
		return count;
	}

}
