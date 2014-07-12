package net.cedu.biz.crm;

import java.util.List;
import java.util.Map;

import net.cedu.entity.crm.FollowUp;
import net.cedu.entity.crm.OperationLog;
import net.cedu.entity.crm.Student;
import net.cedu.model.crm.ImportResult;
import net.cedu.model.page.PageResult;

/**
 * 学生Biz
 * 
 * @author yangdongdong
 * 
 */
public interface StudentBiz {
	/**
	 * 新建学生以及跟进纪录,以及操作日志
	 * 
	 * @param student
	 * @param followUp
	 * @param operationLog
	 * @throws Exception
	 */
	public int addStudent(Student student, FollowUp followUp,
			OperationLog operationLog) throws Exception;

	/**
	 * 
	 * @功能：删除学生
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-31 下午05:32:44
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param id
	 * @return 返回错误代码
	 * @throws Exception
	 */
	public String deleteStudentById(int id) throws Exception;

	/**
	 * 更新学生
	 * 
	 * @param student
	 *            学生实体
	 * @throws Exception
	 */
	public void updateStudent(Student student, FollowUp followUp,
			OperationLog operationLog) throws Exception;

	/**
	 * 查看学生
	 * 
	 * @param id
	 *            学生ID
	 * @return
	 * @throws Exception
	 */
	public Student findStudentById(int id) throws Exception;

	/**
	 * 查询学生总条数
	 * 
	 * @param student
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws Exception
	 */
	public int findStudentsPageCount(Student student, PageResult<Student> pr)
			throws Exception;

	/**
	 * 查询学生集合
	 * 
	 * @param student
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws Exception
	 */
	public List<Student> findStudentsPageList(Student student,
			PageResult<Student> pr) throws Exception;

	/**
	 * 查询学生信息通过电话号码
	 * 
	 * @param phone
	 * @return
	 */
	public List<Student> findStudentsByPhone(String phone);

	/**
	 * 查询学生集合查询条件（姓名,院校，批次，层次，专业）
	 * 
	 * @param student
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws Exception
	 */
	public List<Student> findStudentsByParam(Student student) throws Exception;

	/**
	 * 统计学生各状态的数量
	 * 
	 * @param userId
	 * @param status
	 * @param call_status
	 * @return
	 * @throws Exception
	 */
	public int searchStatusCounts(int userId, int status, int call_status)
			throws Exception;

	/**
	 * 统计分配的学生人数
	 * 
	 * @param userId用户ID
	 * @return
	 * @throws Exception
	 */
	public int distributionStudentCount(int userId) throws Exception;

	/**
	 * 分配学生给中心用户
	 * 
	 * @param userId被分配用户的ID
	 * @param studentIds学生ID集合
	 */
	public void addUserStudent(int userId, String studentIds) throws Exception;

	/**
	 * 按中心所有用户平均分配学生
	 * @param studentImportRecordId  导入纪录ID
	 * @param branchId		学习中心ID
	 * @return 
	 * @throws Exception
	 */
	public String updateAverageDistribution(int studentImportRecordId,int branchId) throws Exception;

	/**
	 * 转移学生
	 * 
	 * @param shiftUserId
	 *            被转移用户ID
	 * @param studentIds学生ID集合
	 */
	public void shiftStudent(int shiftUserId, String studentIds)
			throws Exception;

	/**
	 * 更新学生状态
	 * 
	 * @param studentIds
	 *            学生ID集合
	 * @param status
	 *            状态
	 */
	public void updateStudentStatus(String studentIds, int status)
			throws Exception;

	/**
	 * 查询学生信息通过姓名或者身份证号
	 * 
	 * @param phone
	 * @return
	 */
	public Student findStudentsByNameOrCertNo(String name, String certNo)
			throws Exception;

	/**
	 * 查询没有创建账户，状态大于17，未删除的学生信息
	 * 
	 * @param phone
	 * @return
	 */
	public Long[] findStudentIdsByHasAccount() throws Exception;

	/**
	 * 查询学生是否存在
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public Student isExistStudent(Student student) throws Exception;

	/**
	 * 精确查找学生
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public List<Student> findSyudentByExact(Student student) throws Exception;

	/**
	 * 查询学生按招生批次(多)学生状态(数量)
	 * 
	 * @param enrollBatchIds
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int findStudentByEnrollBatchIdsStatus(String enrollBatchIds,
			int status) throws Exception;

	/**
	 * 查询学生完成数量数量通过全局批次，院校ID和用户ID
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public int findStudentCount(int batchId, int academyId, int userId)
			throws Exception;

	/**
	 * 查询学生集合(xiao)
	 * 
	 * @param student
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<Student> findStudentListsByParams(Student student,
			PageResult<Student> pr) throws Exception;

	/**
	 * 更新学生实体
	 * 
	 * @param stuid
	 * @throws Exception
	 */
	public void updateStudentInfo(Student stu) throws Exception;
	
	/**
	 * 批量更新学生开课状态
	 * @param stuIds 学生Ids字符串集合
	 * @param startStatus 开课状态
	 * @throws Exception
	 */
	public void updateBatchStudentStartStatus(String stuIds,int startStatus) throws Exception;

	/**
	 * 导入学生信息
	 * 
	 * @param student
	 * @param ir
	 * @return
	 * @throws Exception
	 */
	public ImportResult<Student> importStudents(Student student,
			ImportResult<Student> ir) throws Exception;

	/**
	 * 导入学习中心用户
	 * 
	 * @param student
	 * @param ir
	 * @return
	 * @throws Exception
	 */
	public ImportResult<Student> importBranchStudents(Student student,
			ImportResult<Student> ir) throws Exception;
	/**
	 * 
	 * 
	 * @功能：修复学生专业
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-9 下午07:09:17
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *@throws Exception 
	 */
	public void updateRepairProfessionalStudents() throws Exception;
	// /**
	// * 通过状态查询学生
	// * @param start 起始状态ID
	// * @param end 结束状态ID
	// * @return 如果起始状态ID＝结束状态ID，则返回当前相等状态ID的学生集合；
	// * 如果起始状态ID＝0,结束状态ID>0;则为无穷小
	// * 如果起始状态ID>0,结束状态ID=0;则为无穷大
	// * 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
	// * @throws Exception
	// */
	// public List<Student> findStudentByStatusList(int start,int
	// end,PageResult<Student> pr)throws Exception;
	//
	// /**
	// * 通过状态查询学生数量
	// * @param start 起始状态ID
	// * @param end 结束状态ID
	// * @return 如果起始状态ID＝结束状态ID，则返回当前相等状态ID的学生总数量；
	// * 如果起始状态ID＝0,结束状态ID>0;则为无穷小
	// * 如果起始状态ID>0,结束状态ID=0;则为无穷大
	// * 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
	// * @throws Exception
	// */
	// public int findStudentByStatusCount(int start,int end,PageResult<Student>
	// pr)throws Exception;
	
	
	
	
	/**
	 * 按照学生来源查询报名人数
	 * @param student 学生数据
	 * @return
	 * @throws Exception
	 */
	public int countStudentByWayAndStatus(Student student)throws Exception;
	
	/**
	 * 按照学生来源查询报名人数
	 * @param student 学生数据
	 * @return
	 * @throws Exception
	 */
	public List<Student> findStudentByWayAndStatus(Student student)throws Exception;
	
	
	/**
	 * 查询学生总数量(通过最基本的查询条件)
	 * @param student 学生实体
	 * @return
	 * @throws Exception
	 */
	public int findStudentsPageCountByBaseConditions(Student student) throws Exception;
	
	
	/**
	 * 查询学生总集合(通过最基本的查询条件)
	 * @param student 学生实体
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<Student> findStudentsPageListByBaseConditions(Student student,
			PageResult<Student> pr) throws Exception;
	
	/**
	 * 
	 * @功能：通过数量,以及学习中心ID随机获取学生IDs集合
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-5 下午05:44:23
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param branchId
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public String randomStudentIdsByCount(int branchId,int count) throws Exception;
	/**
	 * 
	 * @功能：未分配学生数量
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-5 下午06:22:43
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public int findNoDistributeStudentCount(int branchId)throws Exception;
	
	/**
	 * 
	 * @功能：通过查询条件查询学生ID集合
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-17 上午10:37:22
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param student
	 * @return
	 */
	public String findStudentIdsByStudentParams(Student student)throws Exception;
	
	/**
	 * 根据院校Id和招生批次Id查询已报名的学生人数 （院校返款，匹配政策标准用）
	 * @param academyId 院校Id
	 * @param enrollmentBatchId 招生批次Id
	 * @return
	 * @throws Exception
	 */
	public int findHaveSignedUpStudentCountByAcademyIdenrollmentBatchId(int academyId,int enrollmentBatchId) throws Exception;
	
	/**
	 * 根据院校Id和招生批次Id、合作方Id查询已报名的学生人数(学生已开课且已缴学费) （招生返款，匹配政策标准用）
	 * @param academyId 院校Id
	 * @param enrollmentBatchId 招生批次Id
	 * @param channelId 合作方Id
	 * @param glbatchId 全局批次Id （使用通用政策，匹配招生人数用到）
	 * @return
	 * @throws Exception
	 */
	public int findHaveSignedUpStudentCountByAcademyIdEnrollmentBatchIdChannelId(int academyId,int enrollmentBatchId,int channelId,int glbatchId) throws Exception;
	
	/**
	 * 根据院校Id和招生批次Id、主合作方Id、次合作方Id查询已报名的学生人数 (学生已开课且已缴学费)（招生返款，匹配政策标准用）__特殊合作方
	 * @param academyId 院校Id
	 * @param enrollmentBatchId 招生批次Id
	 * @param channelId 主合作方Id
	 * @param minorChannel 次合作方Id
	 * @param glbatchId 全局批次Id  （使用通用政策，匹配招生人数用到）
	 * @return
	 * @throws Exception
	 */
	public int findHaveSignedUpStudentCountByAcademyIdEnrollmentBatchIdChannelId(int academyId,int enrollmentBatchId,int channelId,int minorChannel,int glbatchId) throws Exception;
	
	/**
	 * 批量录取学生
	 * @param studentIds  学生ids字符串
	 * @param status     学生状态
	 * @throws Exception
	 */
	public void updateStudentSenrolled(String studentIds, int status) throws Exception;

	/**
	 * 
	 * @功能：根据学院id查询学生
	 *
	 * @作者： 杨阳
	 * @作成时间：2012-2-21 下午05:12:44
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param id
	 * @return student
	 * @throws Exception
	 */
	public Student findAcademyIdByStudent(int id) throws Exception;
	/**
	 * 
	 * @功能：根据学生姓名查询学生id数组
	 *
	 * @作者： 杨阳
	 * @作成时间：2012-2-21 下午05:12:44
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param name
	 * @return ids
	 * @throws Exception
	 */
	public Long[] findStudentByNames(String name) throws Exception;
	
	/**
	 * 新增学生（老师续读功能用、其他慎用、无验证）
	 * @param student 学生实体
	 * @return
	 * @throws Exception
	 */
	public boolean addStudentForLaoShengXuDu(Student student) throws Exception;
	
	/**
	 * 查询学生总数量(招生途径复核用)
	 * @param student 学生实体
	 * @return
	 * @throws Exception
	 */
	public int findStudentsPageCountByChannelTypeChecked(Student student)
			throws Exception;
	
	/**
	 * 查询学生总集合(招生途径复核用)
	 * @param student 学生实体
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<Student> findStudentsPageListByChannelTypeChecked(Student student,
			PageResult<Student> pr) throws Exception;
	
	/**
	 * 按照中心预申请条件查询学生
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public List<Student> findStudentByPrePurchaseCenter(Student student) throws Exception;
	
	/**
	 * (重写)删除学生(删除所有学生相关表)(前提:对应的缴费单必须全部作废)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String deleteStudentAllById(int id) throws Exception;
	
	/**
	 * 是否存在老生(老生续读新生id查老生信息)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Student isExistOldStudent(int id) throws Exception;
	
	/**
	 * 根据全局批次ids查询学生报名数
	 * @param globalEnrollBatchIds
	 * @return key:全局批次id value:学生数
	 * @throws Exception
	 */
	public Map<String,Integer> getBaoMingCountByGlobalEnrollBatchIds(String globalEnrollBatchIds) throws Exception;
	
	/**
	 * 根据全局批次ids查询学生缴费数(学费)
	 * @param globalEnrollBatchIds
	 * @return key:全局批次id value:学生数
	 * @throws Exception
	 */
	public Map<String,Integer> getJiaoFeiCountByGlobalEnrollBatchIds(String globalEnrollBatchIds) throws Exception;
	
	/**
	 * 根据全局批次ids查询学生录取数
	 * @param globalEnrollBatchIds
	 * @return key:全局批次id value:学生数
	 * @throws Exception
	 */
	public Map<String,Integer> getLuQuCountByGlobalEnrollBatchIds(String globalEnrollBatchIds) throws Exception;
	
	/**
	 * @功能：通过条件查询学生集合(删除用户功能的查询方法)
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-06-11 上午10:28:15
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param student
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<Student> findStudentListByParams(Student student,int count) throws Exception;
	
	/**
	 * 批量更新学生复核
	 * @param studentIds
	 * @throws Exception
	 */
	public void updateStudentIsChannelTypeChecked(String studentIds) throws Exception;
	
	/**
	 * 根据招生批次Id、层次Id、专业Id查询已报名学生人数 （删除专业用到）
	 * @param enrollmentBatchId 招生批次Id
	 * @param levelId 层次Id
	 * @param majorId 专业Id
	 * @return
	 * @throws Exception
	 */
	public int findStudentCountByDeleteAcademyMajor(int enrollmentBatchId,int levelId,int majorId) throws Exception;
	
	
}
