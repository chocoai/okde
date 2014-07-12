package net.cedu.dao.crm.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.Constants;
import net.cedu.common.StudentConstants;
import net.cedu.common.string.IdcardUtil;
import net.cedu.dao.admin.UGroupUserDao;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.admin.UserGroupDao;
import net.cedu.dao.crm.StudentDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.UGroupUser;
import net.cedu.entity.admin.User;
import net.cedu.entity.admin.UserGroup;
import net.cedu.entity.crm.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 学生实现类
 * 
 * @author yangdongdong
 * 
 */
@Repository
public class StudentDaoImpl extends BaseMDDaoImpl<Student> implements
		StudentDao {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserGroupDao userGroupDao;
	@Autowired
	private UGroupUserDao uGroupUserDao;
	private  JdbcTemplatePlus jdbcTemplatePlus = null;
	/**
	 * 修复学生专业
	 * 
	 * @throws Exception
	 * @see net.cedu.dao.crm.StudentDao#repairProfessionalStudents()
	 */
	public void repairProfessionalStudents() throws Exception {
		JdbcTemplate jt = this.getJdbcTemplate();
		List<Student> studentList = super.getByProperty(" and majorId!="
				+ Constants.PLACEHOLDER, new Object[] { 0 });
		// 集合
		if (studentList != null && studentList.size() != 0)

			for (Student student : studentList) {
				try {
					// 专业ID
					int mid = jt
							.queryForInt("select id from tb_e_major where name=(select name from tb_e_major where id in (select major_id from tb_e_student where id="
									+ student.getId()
									+ ")) and academy_id=(select academy_id from tb_e_student where id="
									+ student.getId() + ") limit 1");
					student.setMajorId(mid);

					super.update(student);
				} catch (Exception e) {
					// e.printStackTrace();
					System.out.println(student.getId() + "__" + e.getMessage());
					continue;
				}
			}

	}

	/**
	 * 查询完成指标
	  * @see net.cedu.dao.crm.StudentDao#getCompleteCountAll()
	 */
	public Map<String, Integer> getCompleteCountAll() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String sql = "select branch_id,"
				+ "(select global_enroll_batch_id from tb_e_academy_enroll_batch a where id=enrollment_batch_id) as global_enroll_batch_id,"
				+ "academy_id,count(*) as ea_count "
				+ "from tb_e_student s where  status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+"  and academy_id <> 0 and enrollment_batch_id <> 0 "
				+ "group by academy_id,enrollment_batch_id,branch_id";
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> list = super.getJdbcTemplate().query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						// 学习中心ID
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("global_enroll_batch_id",
								resultSet.getInt("global_enroll_batch_id"));
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("ea_count", resultSet.getInt("ea_count"));
						return map;
					}
				});
		if (list != null) {
			for (Map<String, Integer> map2 : list) {
				map.put(map2.get("branch_id") + "_"
						+ map2.get("global_enroll_batch_id") + "_"
						+ map2.get("academy_id"), map2.get("ea_count"));
			}
		}
		return map;
	}
	
	/*
	 * (重载)根据全局批次查询完成指标 key:学习中心ID_院校ID value:完成指标
	 * @see net.cedu.dao.crm.StudentDao#getCompleteCountAll()
	 */
	public Map<String, Integer> getCompleteCountAll(int batchId) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String sql = "select branch_id, "+
					" s.academy_id, "+
					" count(*) as ea_count "+
					" from tb_e_student s,tb_e_academy_enroll_batch a "+
					" where 1=1 "+
					" and a.id = s.enrollment_batch_id "+
					" and status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" "+
					" and s.academy_id <> 0 "+
					" and s.enrollment_batch_id <> 0 "+
					" and a.global_enroll_batch_id = "+batchId+" "+
					" group by s.academy_id,s.enrollment_batch_id,s.branch_id ";
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> list = super.getJdbcTemplate().query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						// 学习中心ID
						map.put("branch_id", resultSet.getInt("branch_id"));
						// 院校ID
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("ea_count", resultSet.getInt("ea_count"));
						return map;
					}
				});
		if (list != null) {
			for (Map<String, Integer> map2 : list) {
				map.put(map2.get("branch_id") + "_" + map2.get("academy_id"), map2.get("ea_count"));
			}
		}
		return map;
	}

	/**
	 * 修复学生性别
	 * 
	 * @see net.cedu.dao.crm.StudentDao#repairStudentsSex()
	 */
	@SuppressWarnings("unchecked")
	public void repairStudentsSex() throws Exception {
		int $page = 1;// 起始索引
		int $pageSize = 5000;// 每次5000条数据
		boolean only=true;
		try{
		List<Student> studentList=null;
		
		while (only||(studentList != null && studentList.size() != 0)) {
			String sql = " select id,cert_no from tb_e_student order by id limit "+ (($page - 1) * $pageSize) + "," + $pageSize;
			studentList = super.getJdbcTemplate().query(sql,
					new RowMapper() {
						public Student mapRow(ResultSet resultSet, int index)
								throws SQLException {
							Student s = new Student();
							s.setId(resultSet.getInt("id"));
							s.setCertNo(resultSet.getString("cert_no"));
							return s;
						}
					});
			
			// 女孩学生IDs
			String grilIds = ",";
			StringBuffer grilIdsSB = new StringBuffer(",");
			// 男孩
			String boyIds = ",";
			StringBuffer boyIdsSB = new StringBuffer(",");

			for (Student student : studentList) {
				if (student.getCertNo() != null
						&& !student.getCertNo().trim().equals("")&&(student.getCertNo().trim().length()==18||student.getCertNo().trim().length()==15)) {
					
					// 男
					if (Constants.SEX_MALE == IdcardUtil.getSexFromCard(student.getCertNo())) {
//						if (boyIds.equals(",")) {
//							boyIds = student.getId() + "";
//						} else {
//							boyIds += "," + student.getId();
//						}
						if (boyIdsSB.toString().equals(",")) {
							boyIdsSB = new StringBuffer(student.getId() + "");
						} else {
							boyIdsSB.append("," + student.getId());
						}
					}else if(Constants.SEX_FAMALE == IdcardUtil.getSexFromCard(student.getCertNo())) {// 女
//						if (grilIds.equals(",")) {
//							grilIds = student.getId() + "";
//						} else {
//							grilIds += "," + student.getId();
//						}
						if (grilIdsSB.toString().equals(",")) {
							grilIdsSB = new StringBuffer(student.getId() + "");
						} else {
							grilIdsSB.append("," + student.getId());
						}
					}
				}
			}
			boyIds = boyIdsSB.toString();
			grilIds = grilIdsSB.toString();
			if (!boyIds.equals(",")) {
				// 更新男孩
				String updateBoySexSql = "update tb_e_student set gender="
						+ Constants.SEX_MALE+" where id in ("+boyIds+")";
				
				super.getJdbcTemplate().execute(updateBoySexSql);
			}
			if (!grilIds.equals(",")) {
				// 更新女孩
				String updateGrilSexSql = "update tb_e_student set gender="
						+ Constants.SEX_FAMALE+" where id in ("+grilIds+")";;
				super.getJdbcTemplate().execute(updateGrilSexSql);
			}
			System.out.println($page);
			if(studentList.size()<$pageSize){
				break;
			}
			$page++;
			only=false;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 修复学生跟进人
	 */
	public void repairStudentsUserId() throws Exception {
		int $page = 1;// 起始索引
		int $pageSize = 10000;// 每次10000条数据
		boolean only=true;
		try{
		List<Student> studentList=null;
		
		while (only||(studentList != null && studentList.size() != 0)) {
			String sql = " select id,user_id,branch_id from tb_e_student order by id limit "+ (($page - 1) * $pageSize) + "," + $pageSize;
			studentList = super.getJdbcTemplate().query(sql,
					new RowMapper() {
						public Student mapRow(ResultSet resultSet, int index)
								throws SQLException {
							Student s = new Student();
							s.setId(resultSet.getInt("id"));
							s.setUserId(resultSet.getInt("user_id"));
							s.setBranchId(resultSet.getInt("branch_id"));
							return s;
						}
					});
			
			for (Student student : studentList) {
				if(student.getBranchId()!=0&&student.getBranchId()!=1){
					User user=userDao.getObjByProperty(" and id="+Constants.PLACEHOLDER+" and orgId="+Constants.PLACEHOLDER, new Object[]{student.getUserId(),student.getBranchId()});
					if(user==null){
						//用户组
						UserGroup userGroup=userGroupDao.getObjByProperty(" and name="+Constants.PLACEHOLDER+" and orgId="+Constants.PLACEHOLDER, new Object[]{"中心主任组",student.getBranchId()});
						if(userGroup!=null){
							//用户组与用户的关系
							List<UGroupUser> uGroupuserList=uGroupUserDao.getByProperty(" and groupId="+Constants.PLACEHOLDER, new Object[]{userGroup.getId()});
							if(uGroupuserList!=null&&uGroupuserList.size()!=0){
								super.getJdbcTemplate().execute("update tb_e_student set user_id="+uGroupuserList.get(0).getUserId()+" where branch_id="+student.getBranchId()+" and user_id="+student.getUserId());
							}
							
						}
						
						
					}
				}
				
			}
			
			$page++;
			only=false;
			if(studentList.size()<$pageSize){
				break;
			}
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * 获取重复学生Ids
	 * @see net.cedu.dao.crm.StudentDao#repeatStudentIds()
	 */
	public String repeatStudentIds(int isEmphasisVerification,Student student,String gbatchIds,String glevelIds,String glmajors) throws Exception {
		if(isEmphasisVerification==StudentConstants.IS_EMPHASIS_VERIFICATION_MOBILE){
			StringBuffer sb=null;
//			StringBuffer sb_=null;
//			String sql_="select s1.mobile as mobile from tb_e_student as s1 where "+getStudentSearch(student,gbatchIds, glevelIds, glmajors)+" and s1.mobile<>'NULL' and s1.mobile is not null and s1.mobile<>''  group by s1.mobile  having count(s1.mobile) > 1";
//			
//			List<Map<String,String>> studentMobileRepeatIdList_=super.getJdbcTemplatePlus().queryForList(sql_);
//			if(studentMobileRepeatIdList_!=null&&studentMobileRepeatIdList_.size()!=0){
//				for (Map<String, String> map : studentMobileRepeatIdList_) {
//					if(sb_==null){
//						sb_=new StringBuffer();
//						sb_.append("'");
//						sb_.append(map.get("mobile"));
//						sb_.append("'");
//					}else{
//						sb_.append(",'");
//						sb_.append(map.get("mobile"));
//						sb_.append("'");
//					}
//				}
//			}
//			if(sb_!=null){
				//List<Map<String,Integer>> studentMobileRepeatIdList=super.getJdbcTemplatePlus().queryForList("select id from tb_e_student where "+getStudentSearch(student,gbatchIds, glevelIds, glmajors)+" and mobile in ("+sb_.toString()+")");
			List<Map<String,Integer>> studentMobileRepeatIdList=super.getJdbcTemplatePlus().queryForList("select b.id from ( select s1.mobile as mobile from tb_e_student as s1 where "+getStudentSearch(student,gbatchIds, glevelIds, glmajors)+" and  s1.mobile<>'NULL' and s1.mobile is not null and s1.mobile<>'' group by mobile having count(*) >1) as p inner join  tb_e_student b on b.mobile=p.mobile");
				if(studentMobileRepeatIdList!=null&&studentMobileRepeatIdList.size()!=0){
					for (Map<String, Integer> map : studentMobileRepeatIdList) {
						if(sb==null){
							sb=new StringBuffer();
							sb.append(map.get("id"));
						}else{
							sb.append(","+map.get("id"));
						}
					}
				}
//			}
			if(sb==null){
				sb=new StringBuffer();
				sb.append("0");
			}
			return sb.toString();
		}else if(isEmphasisVerification==StudentConstants.IS_EMPHASIS_VERIFICATION_BRANCH_STUDENT_NAME){
			StringBuffer sb=null;
//			StringBuffer sb_=null;
//			StringBuffer sb__=null;
//			String sql_="select s1.name as name,s1.branch_id as branch_id from tb_e_student as s1 where "+getStudentSearch(student,gbatchIds, glevelIds, glmajors)+" and s1.name<>'NULL' and s1.name is not null and s1.name<>'' and s1.branch_id!=0 group by name,branch_id having count(*) >1";
//			List<Map<String,Object>> studentMobileRepeatIdList_=super.getJdbcTemplatePlus().queryForList(sql_);
//			if(studentMobileRepeatIdList_!=null&&studentMobileRepeatIdList_.size()!=0){
//				for (Map<String, Object> map : studentMobileRepeatIdList_) {
//					if(sb_==null){
//						sb_=new StringBuffer();
//						sb_.append("'");
//						sb_.append(map.get("name").toString());
//						sb_.append("'");
//					}else{
//						sb_.append(",'");
//						sb_.append(map.get("name").toString());
//						sb_.append("'");
//					}
//					if(sb__==null){
//						sb__=new StringBuffer();
//						sb__.append("'");
//						sb__.append(map.get("branch_id").toString());
//						sb__.append("'");
//					}else{
//						sb__.append(",'");
//						sb__.append(map.get("branch_id").toString());
//						sb__.append("'");
//					}
//				}
//			}
//			if(sb_!=null&&sb__!=null){
			
				//List<Map<String,Integer>> studentMobileRepeatIdList=super.getJdbcTemplatePlus().queryForList("select id from tb_e_student where "+getStudentSearch(student,gbatchIds, glevelIds, glmajors)+" and name in ("+sb_.toString()+") and "+" branch_id in ("+sb__.toString()+")");
			//System.out.println("select b.name,b.branch_id from ( select id from tb_e_student as s1 where "+getStudentSearch(student,gbatchIds, glevelIds, glmajors)+" and  s1.name<>'NULL' and s1.name is not null and s1.name<>'' and s1.branch_id!=0 group by name,branch_id having count(*) >1) as p inner join tb_e_student b on b.name=p.name and b.branch_id=p.branch_id");
			List<Map<String,Integer>> studentMobileRepeatIdList=super.getJdbcTemplatePlus().queryForList("select b.id as id from ( select s1.name as name,s1.branch_id as branch_id from tb_e_student as s1 where "+getStudentSearch(student,gbatchIds, glevelIds, glmajors)+" and  s1.name<>'NULL' and s1.name is not null and s1.name<>'' and s1.branch_id!=0 group by name,branch_id having count(*) >1) as p inner join tb_e_student b on b.name=p.name and b.branch_id=p.branch_id");
				if(studentMobileRepeatIdList!=null&&studentMobileRepeatIdList.size()!=0){
					for (Map<String, Integer> map : studentMobileRepeatIdList) {
						if(sb==null){
							sb=new StringBuffer();
							sb.append(map.get("id"));
						}else{
							sb.append(","+map.get("id"));
						}
					}
				}
//			}
			if(sb==null){
				sb=new StringBuffer();
				sb.append("0");
			}
			return sb.toString();
		}
		return "0";
		

//		List<Map<String,Integer>> studentNameAndBranchRepeatIdList=super.getJdbcTemplatePlus().queryForList("select id from tb_e_student as s1 where s1.name<>'NULL' and s1.name is not null and s1.name<>'' and s1.branch_id!=0 group by name,branch_id having count(*) >1");
//		if(studentNameAndBranchRepeatIdList!=null&&studentNameAndBranchRepeatIdList.size()!=0){
//			for (Map<String, Integer> map : studentNameAndBranchRepeatIdList) {
//				if(sb==null){
//					sb=new StringBuffer();
//					sb.append(map.get("id"));
//				}else{
//					sb.append(","+map.get("id"));
//				}
//			}
//		}
		
	}
	
	public Map<String,Integer> getCompleteCountAllByGlobalEnrollBatchIdAndBranchId(int globalEnrollBatchId,int branchId)
	{
		jdbcTemplatePlus = getJdbcTemplatePlus();
		String sql = "select count(id) as count,branch_id,academy_id,user_id from tb_e_student where "
					+ " enrollment_batch_id in (select id from tb_e_academy_enroll_batch where global_enroll_batch_id = "+globalEnrollBatchId+" and delete_flag="+Constants.DELETE_FALSE+" and is_enable <> "+Constants.STATUS_FINISHED+") "
					+ " and status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" ";
		if(branchId!=0)
		{
			sql += " and branch_id="+branchId+" ";
		}
		sql += " group by branch_id,academy_id,user_id ";
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
			public Map<String,String> mapRow(ResultSet resultSet, int index)
					throws SQLException {
				Map<String,String> map =new HashMap<String,String>();
				map.put("key", resultSet.getString("branch_id")+"_"+resultSet.getString("academy_id")+"_"+resultSet.getString("user_id"));
				map.put("value", resultSet.getString("count"));
				return map;
			}
		});
		if(list!=null){
			Map<String,Integer> resultMap =new HashMap<String,Integer>();
			for (Map<String, String> m : list) {
				resultMap.put(m.get("key"), Integer.valueOf(m.get("value")));
			}
			return resultMap;
		}
		return new HashMap<String, Integer>();
	}
	
	
	private String getStudentSearch(Student student,String gbatchIds,String glevelIds,String glmajors){
		String hqlConditionExpression=" 1=1 ";
		if (student != null) {
//			// 学号
//			if (student.getNumber() != null && !student.getNumber().equals("")) {
//				hqlConditionExpression += " and number like "+ "'%" + student.getNumber() + "%'";
//			}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression += " and s1.name like "+"'%" + student.getName() + "%'";
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
				hqlConditionExpression += " and s1.cert_no like "+ "'%" + student.getCertNo() + "%'";
			}
			// 学历
			if (student.getDegree() != 0) {
				hqlConditionExpression += " and s1.degree ="+student.getDegree();
			}

			// 状态
			if (student.getStatus() != 0) {
				hqlConditionExpression += " and s1.status=" + student.getStatus();
			}
//			// 性别
//			if (student.getGender() != -1) {
//				hqlConditionExpression += " and gender=" + student.getGender();
//			}

			// 数据来源
			if (student.getStudentDataSource() != 0) {
				hqlConditionExpression += " and s1.student_data_source="+ student.getStudentDataSource();
			}
			// 批量数据来源
			if (student.getStudentDataSourceIds() !=null && !student.getStudentDataSourceIds().equals("")){
				hqlConditionExpression += " and s1.student_data_source in (" + student.getStudentDataSourceIds() + ") ";
			}
			
			// 学生招生途径
			if (student.getEnrollmentSource() != 0) {
				hqlConditionExpression += " and s1.enrollment_source="
						+ student.getEnrollmentSource();
			}
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += " and s1.academy_id="
						+ student.getAcademyId();
			}
			// 院校Id集合
			if (student.getAcademyIds() != null
					&& !student.getAcademyIds().equals("")) {
				hqlConditionExpression += " and s1.academy_id in ("
						+ student.getAcademyIds() + ") ";
			}
			
			// 用户
			if (student.getUserId() == -1) {
				hqlConditionExpression += " and s1.user_id=" +0;
			} else {
				if (student.getUserId() != 0) {
					hqlConditionExpression += " and s1.user_id="
							+ student.getUserId();
				}
			}
			// 手机
			if (student.getMobile() != null && !student.getMobile().equals("")) {
				hqlConditionExpression += " and  s1.mobile like "
						+ "'%" + student.getMobile() + "%'";
			}

			// 手机或座机
			if (student.getPhone() != null && !student.getPhone().equals("")) {
				hqlConditionExpression += " and (s1.phone like "
						+ "'%" + student.getPhone() + "%'" + "or s1.mobile like "
						+ "'%" + student.getPhone() + "%'" + ")";
			}

			// 邮箱
			if (student.getEmail() != null && !"".equals(student.getEmail())) {
				hqlConditionExpression += " and s1.email like "+ "'%" + student.getEmail() + "%'";
			}

			// 所在城市
			if (student.getLivingPlace() != null
					&& !"0".equals(student.getLivingPlace())
					&& !"".equals(student.getLivingPlace())) {

				hqlConditionExpression += "and s1.living_place= '"+ student.getLivingPlace()+"'";
			}

			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hqlConditionExpression += " and  s1.enrollment_batch_id= "+ student.getEnrollmentBatchId();
			}
			// 全局批次
			if (student.getGlbtach() != 0) {
				hqlConditionExpression += " and ( s1.enrollment_batch_id in ( "+ gbatchIds.substring(1, gbatchIds.length()) + " ) ";
				hqlConditionExpression += " or  s1.global_batch = "+ student.getGlbtach()+") ";
			}
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression += " and  s1.level_id= "+ student.getLevelId();
			}
			// 全局层次
			if (student.getGllevel() != 0) {
				hqlConditionExpression += " and  s1.level_id in ( "+ glevelIds.substring(1, glevelIds.length()) + " ) ";
			}

			// 专业
			if (student.getMajorId() != 0) {
				hqlConditionExpression += " and  s1.major_id= "+ student.getMajorId();
			}
			// 基础专业
			if (student.getGlmajor() != 0) {
				hqlConditionExpression += " and  s1.major_id in ( "+ glmajors.substring(1, glmajors.length()) + " ) ";
			}

			// 市场途径
			if (student.getEnrollmentWay() != 0) {
				hqlConditionExpression += " and  s1.enrollment_way= "+ student.getEnrollmentWay();
			}
			// 监控状态
			if (student.getMonitorStatus() != -1) {
				hqlConditionExpression += " and  s1.monitor_status= "
						+ student.getMonitorStatus();
			}
			// 缴费状态
			if (student.getTuitionStatus() != -1) {
				if (student.getTuitionStatus() > 1
						&& student.getTuitionStatus() < 9999) {
					hqlConditionExpression += " and  s1.tuition_status > "
							+ Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI + " and s1.tuition_status<"
							+ Constants.STU_TUITION_STATUS_JIAO_FEI_WAN_CHENG;

				} else {
					hqlConditionExpression += " and  s1.tuition_status= "
							+ student.getTuitionStatus();
				}
			}

			// 批量查询学生集合
			if (student.getStatusIds() != null
					&& !"".equals(student.getStatusIds())) {
				hqlConditionExpression += " and  s1.status in("
						+ student.getStatusIds() + ")";
			}

			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  s1.status <"
						+ student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
				hqlConditionExpression += " and  s1.status >"
						+ student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  s1.status> "
						+ student.getStartStatusId() + " and status<"
						+ student.getEndStatusId();

			}

			// qq msn
			if (student.getQq() != null && !student.getQq().equals("")) {
				hqlConditionExpression += " and (s1.qq like "
						+ "'%" + student.getQq() + "%'" + "or s1.msn like "
						+ "'%" + student.getQq() + "%'" + ")";

			}

			// 学习中心
			if (student.getBranchId() != 0) {
				hqlConditionExpression += " and  s1.branch_id= "
						+ student.getBranchId();
			}
			// 跟进人
			if (student.getFollowUpId() != 0) {
				hqlConditionExpression += " and  s1.follow_up_id= "
						+ student.getFollowUpId();
			}

			// 学生监控状态
			if (student.getMonitorStatus() != -1) {
				hqlConditionExpression += " and  s1.monitor_status= "+ student.getMonitorStatus();
			}
			if (student.getMonitorStatusIds() != null
					&& !"".equals(student.getStatusIds())) {
				hqlConditionExpression += " and  s1.monitor_status in("
						+ student.getMonitorStatusIds() + ")";
			}
			
			//最新监控状态
			if(student.getLastMonitorResult()!=0){
				hqlConditionExpression += " and  s1.last_monitor_result = "+ student.getLastMonitorResult();
			}
			//不包括的监控结果
			if(student.getNoLastMonitorResults()!=null){
				hqlConditionExpression += " and s1.last_monitor_result not in ("+ student.getNoLastMonitorResults()+")";
			}

		}
		return hqlConditionExpression;
	}

	/*
	 * 根据全局批次查询完成指标（全局批次为0则返回空） Map(key:branchId value:完成指标数)
	 * @see net.cedu.dao.crm.StudentDao#getCompleteCountAllByGlobalEnrollBatchId(int)
	 */
	public Map<String, Integer> getCompleteCountAllByGlobalEnrollBatchId(
			int globalEnrollBatchId) throws Exception {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		String sql = "select count(id) as count,branch_id from tb_e_student where "
					+ " enrollment_batch_id in (select id from tb_e_academy_enroll_batch where global_enroll_batch_id = "+globalEnrollBatchId+" and delete_flag="+Constants.DELETE_FALSE+" and is_enable <> "+Constants.STATUS_FINISHED+") "
					+ " and status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" ";
		sql += " group by branch_id ";
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
			public Map<String,String> mapRow(ResultSet resultSet, int index)
					throws SQLException {
				Map<String,String> map =new HashMap<String,String>();
				map.put("key", resultSet.getString("branch_id"));
				map.put("value", resultSet.getString("count"));
				return map;
			}
		});
		if(list!=null){
			Map<String,Integer> resultMap =new HashMap<String,Integer>();
			for (Map<String, String> m : list) {
				resultMap.put(m.get("key"), Integer.valueOf(m.get("value")));
			}
			return resultMap;
		}
		return new HashMap<String, Integer>();
	}

	/*
	 * 根据全局批次ids查询学生报名数
	 * @see net.cedu.dao.crm.StudentDao#getBaoMingCountByGlobalEnrollBatchIds(java.lang.String)
	 */
	public Map<String, Integer> getBaoMingCountByGlobalEnrollBatchIds(
			String globalEnrollBatchIds) throws Exception {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		Map<String, Integer> mapResult = new HashMap<String, Integer>();
		String sql = " select global_batch,IFNULL(count(id),0) as count " +
					 " from tb_e_student " +
					 " where status <> " + Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI +
					 " and status > " + Constants.STU_CALL_STATUS_YI_DAO_MING;
		if(globalEnrollBatchIds!=null 
				&& !globalEnrollBatchIds.equals("") 
				&& !globalEnrollBatchIds.equals("0"))
		{
			sql += " and global_batch in ( " + globalEnrollBatchIds + " ) ";
		}
		sql += " group by global_batch ";
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("global_batch", resultSet.getInt("global_batch"));
						map.put("count", resultSet.getInt("count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("global_batch")+"", map.get("count"));
			}
		}
		return mapResult;
	}

	/*
	 * 根据全局批次ids查询学生缴费数(学费)
	 * @see net.cedu.dao.crm.StudentDao#getJiaoFeiCountByGlobalEnrollBatchIds(java.lang.String)
	 */
	public Map<String, Integer> getJiaoFeiCountByGlobalEnrollBatchIds(
			String globalEnrollBatchIds) throws Exception {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		Map<String, Integer> mapResult = new HashMap<String, Integer>();
		String sql = " select global_batch,IFNULL(count(id),0) as count " +
					 " from tb_e_student " +
					 " where status > " + Constants.STU_CALL_STATUS_YI_DAO_MING +
					 " and tuition_status > " + Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI;
		if(globalEnrollBatchIds!=null 
				&& !globalEnrollBatchIds.equals("") 
				&& !globalEnrollBatchIds.equals("0"))
		{
			sql += " and global_batch in ( " + globalEnrollBatchIds + " ) ";
		}
		sql += " group by global_batch ";
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("global_batch", resultSet.getInt("global_batch"));
						map.put("count", resultSet.getInt("count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("global_batch")+"", map.get("count"));
			}
		}
		return mapResult;
	}

	/*
	 * 根据全局批次ids查询学生录取数
	 * @see net.cedu.dao.crm.StudentDao#getLuQuCountByGlobalEnrollBatchIds(java.lang.String)
	 */
	public Map<String, Integer> getLuQuCountByGlobalEnrollBatchIds(
			String globalEnrollBatchIds) throws Exception {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		Map<String, Integer> mapResult = new HashMap<String, Integer>();
		String sql = " select global_batch,IFNULL(count(id),0) as count " +
					 " from tb_e_student " +
					 " where status=13 ";
		if(globalEnrollBatchIds!=null 
				&& !globalEnrollBatchIds.equals("") 
				&& !globalEnrollBatchIds.equals("0"))
		{
			sql += " and global_batch in ( " + globalEnrollBatchIds + " ) ";
		}
		sql += " group by global_batch ";
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("global_batch", resultSet.getInt("global_batch"));
						map.put("count", resultSet.getInt("count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("global_batch")+"", map.get("count"));
			}
		}
		return mapResult;
	}
	
	/*
	 * (重写)save方法 增加创建日期验证
	 * @see net.cedu.dao.impl.BaseMDDaoImpl#save(java.lang.Object)
	 */
	public Object save(Student student){
		if(student.getCreateDate()==null){
			student.setCreateDate(new Date());
		}
		return super.save(student);
	}

}
