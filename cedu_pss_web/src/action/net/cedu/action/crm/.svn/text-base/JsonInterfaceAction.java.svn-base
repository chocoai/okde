package net.cedu.action.crm;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UGroupUserBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.BaseMajorBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.basesetting.StuStatusBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.PingYingHanZiUtil;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.BaseMajor;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.basesetting.StudentStatus;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.Channel;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 外部接口
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonInterfaceAction extends BaseAction {

	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyLevelBiz academyLevelBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;
	@Autowired
	private AcademyMajorBiz academyMajorBiz;
	@Autowired
	private StuStatusBiz stuStatusBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private BaseDictBiz baseDictBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private BaseMajorBiz baseMajorBiz;
	@Autowired
	private AcademyBatchBranchBiz academyBztchBranchBiz;// 院校授权中心
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private UGroupUserBiz uGroupUserBiz;

	// 学习中心集合
	private List<Branch> branchlist = new ArrayList<Branch>();

	// 院校集合
	private List<Academy> academysAcademies = new ArrayList<Academy>();
	// 批次集合
	private List<AcademyEnrollBatch> academyEnrollBatchs = new ArrayList<AcademyEnrollBatch>();
	// 批次集合
	private List<GlobalEnrollBatch> globalEnrollBatchs = new ArrayList<GlobalEnrollBatch>();
	// 层次集合
	private List<AcademyLevel> academyLevels = new ArrayList<AcademyLevel>();
	// 专业集合
	private List<AcademyMajor> academyMajors = new ArrayList<AcademyMajor>();
	// 学生状态集合
	private List<StudentStatus> studentStatus = new ArrayList<StudentStatus>();
	// 学生状态集合
	private List<StudentStatus> studentStatusStage = new ArrayList<StudentStatus>();
	// 招生途径
	private List<EnrollmentSource> enrollmentSources = new ArrayList<EnrollmentSource>();
	// 市场途径字典
	//private List<BaseDict> enrollmentWays = new ArrayList<BaseDict>();
	private LinkedHashMap<String,List<BaseDict>> enrollmentWaysMap=new LinkedHashMap<String,List<BaseDict>>();
	// 用户集合
	private List<User> users = new ArrayList<User>();
	//合作方
	private List<Channel> channels = new ArrayList<Channel>();
	//层次
	private List<Level> levellst = new ArrayList<Level>();
	//层次
	private List<BaseMajor> majorlst = new ArrayList<BaseMajor>();
	
	private int id;
	private int branchId;
	private int academyId;
	private String stageCode;

	/**
	 * 查询所有学习中心
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "all_branch_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties",
			"branchlist.*"
			
	}) })
	public String AllBranchList() throws Exception {
		branchlist=branchBiz.findBranchForModel();
		Collections.sort(branchlist, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Branch) arg0).getName();
				String name2 = ((Branch) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		return SUCCESS;
	}
	
	/**
	 * 市场途径，招生途径
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "student_way_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"enrollmentSources.*,enrollmentWaysMap.*"
	}) })
	public String studentWayList() throws Exception {
		List<EnrollmentSource> list = enrollmentSourceBiz.findAllEnrollmentSources();
		for(EnrollmentSource e : list)
		{
			if(e.getId() == Constants.WEB_STU_LAO_SHENG_XU_DU)
				continue;
			enrollmentSources.add(e);
		}
		Collections.sort(enrollmentSources, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((EnrollmentSource) arg0).getName();
				String name2 = ((EnrollmentSource) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		
	    LinkedHashMap<BaseDict,List<BaseDict>> map=new LinkedHashMap<BaseDict,List<BaseDict>>();
		
		List<BaseDict> enrollmentWays = baseDictBiz.findBaseDictsByType(Constants.BASEDICT_STYLE_ENROLLMENTWAY,getUser().getOrgId());
		//如果不是总部，则附加总部部分的市场途径
		if(getUser().getOrgId()!=1)
		{
			List<BaseDict> enroolmentWaysZB = baseDictBiz.findBaseDictsByType(Constants.BASEDICT_STYLE_ENROLLMENTWAY,1);
			for(BaseDict bd : enroolmentWaysZB)
			{
				enrollmentWays.add(bd);
			}
		}
		
		for (int i = 0; i < enrollmentWays.size(); i++) {
			BaseDict baseDict=enrollmentWays.get(i);
			if(baseDict.getParentNode()==0){
				map.put(baseDict, new ArrayList<BaseDict>());
			}
		}
		Set<BaseDict> key = map.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
        	BaseDict baseDictKey = (BaseDict) it.next();
        	List<BaseDict> baseDictList=map.get(baseDictKey);
        	for (int i = 0; i < enrollmentWays.size(); i++) {
    			BaseDict baseDict=enrollmentWays.get(i);
    			if(baseDict.getParentNode()==baseDictKey.getId()){
    				baseDictList.add(baseDict);
    			}
    		}
        	
        }
        Set<BaseDict> key1 = map.keySet();
        for (Iterator it = key1.iterator(); it.hasNext();) {
        	BaseDict baseDictKey = (BaseDict) it.next();
        	List<BaseDict> baseDictList=map.get(baseDictKey);
        	if(baseDictList.size()==0){
        		baseDictList.add(baseDictKey);
        	}
        	enrollmentWaysMap.put(baseDictKey.getName(), baseDictList);
        }
		
//		Collections.sort(enrollmentWays, new Comparator() {
//			public int compare(Object arg0, Object arg1) {
//				Comparator cmp = Collator
//						.getInstance(java.util.Locale.CHINA);
//				String name1 = ((BaseDict) arg0).getName();
//				String name2 = ((BaseDict) arg1).getName();
//				return cmp.compare(name1, name2);
//			}
//		});
		return SUCCESS;
	}

	/**
	 * 根据合作方类别和学习中心查询所有合作方
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Action(value = "student_channel_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"channels.*,id,branchId"
	}) })
	public String studentChannelList() throws Exception {		
		if(branchId>0)
		{
			channels = channelBiz.findAllChannelByChannelTypeIdAndBranchId(id,branchId);
		}else
		{
			channels = channelBiz.findAllChannelByChannelTypeIdAndBranchId(id,super.getUser().getOrgId());
		}
		if(channels!=null){
			for (Channel channel : channels) {
				channel.setName(PingYingHanZiUtil.getNameFirstZiMuToUpperCaseCase(channel.getName())+channel.getName());
			}
			Collections.sort(channels, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Channel) arg0).getName();
					String name2 = ((Channel) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		}
	
		
		return SUCCESS;
	}
	
	/**
	 * 根据合作方类别和学习中心及其下属查询所有合作方（复核页面用）
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Action(value = "student_channel_list_checked", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"channels.*,id,branchId"
	}) })
	public String studentChannelListChecked() throws Exception {		
		if(branchId>0)
		{
			
		}else
		{
			branchId = super.getUser().getOrgId();
		}
		
		List<Branch> listBranch = branchBiz.findListById(branchId);
		String branchIds = "";
		if(listBranch!=null && listBranch.size()>0)
		{
			for(Branch branch : listBranch)
			{
				if(branchIds.equals(""))
				{
					branchIds = branch.getId()+"";
				}
				else
				{
					branchIds += ","+branch.getId();
				}
			}
		}
		channels = channelBiz.findAllChannelByChannelTypeIdAndBranchIds(id,branchIds);
		
		if(channels!=null){
			for (Channel channel : channels) {
				channel.setName(PingYingHanZiUtil.getNameFirstZiMuToUpperCaseCase(channel.getName())+channel.getName());
			}
			Collections.sort(channels, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Channel) arg0).getName();
					String name2 = ((Channel) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		}
	
		
		return SUCCESS;
	}

	/**
	 * 学生状态
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "student_status_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentStatusList() throws Exception {
		try {
			studentStatus = stuStatusBiz.findAllStudentStatusByDeleteFlag();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 学生状态(按状态阶段)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "student_status", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentStatus() throws Exception {
		try {
			studentStatus = stuStatusBiz.findStatusNamesByStageCode(stageCode);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	
	
	/**
	 * 状态阶段
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "student_status_stage_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentStatusStageList() throws Exception {
		try {
			studentStatusStage =stuStatusBiz.findStatusStage();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	

	/**
	 * 院校集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "academys_academie_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"excludeProperties",
			"academysAcademies.*.address,academysAcademies.*.updatedTime,"+
			"academysAcademies.*.account,academysAcademies.*.auditStatus,"+
			"academysAcademies.*.auditedDate,academysAcademies.*.auditer,"+
			"academysAcademies.*.complete,academysAcademies.*.contractEndtime,"+
			"academysAcademies.*.createdTime,academysAcademies.*.introduction,"+
			"academysAcademies.*.pictureUrl,academysAcademies.*.website",
			"includeProperties",
			"academysAcademies.*"
	}) })
	public String academysAcademieList() throws Exception {
		academysAcademies = academyBiz.findAllAcademyByFlagFalse();
		Collections.sort(academysAcademies, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Academy) arg0).getName();
				String name2 = ((Academy) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		return SUCCESS;
	}

	/**
	 * 批次集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "academy_enroll_batch_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"academyEnrollBatchs.*,id"
	}) })
	public String academyEnrollBatchsList() throws Exception {
		academyEnrollBatchs = academyEnrollBatchBiz.findByAcademyIdAndFlag(id);
		return SUCCESS;
	}
	
	/**
	 * 全局批次集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "gl_enroll_batch_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties",
			"globalEnrollBatchs.*"
	}) })
	public String glEnrollBatchList() throws Exception {
		globalEnrollBatchs = globalEnrollBatchBiz.findAllGlobalEnrollBatchByDeleteFlag();
		return SUCCESS;
	}
	
	
	/**
	 * 批次集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "academy_enroll_batch_list_input", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"academyEnrollBatchs.*,id"
	}) })
	public String academyEnrollBatchsListInput() throws Exception {
		academyEnrollBatchs = academyEnrollBatchBiz.findByAcademyIdAndFlagAndIsEnable(id,Constants.STATUS_ENABLED);
		return SUCCESS;
	}

	/**
	 * 层次集合以及学习中心
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "level_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"academyLevels.*,branchlist.*,id,academyId"
	}) })
	public String levelList() throws Exception {
		academyLevels = academyLevelBiz.findBatchId(id);
		Level level = null;
		if (academyLevels != null && academyLevels.size() > 0) {
			for (AcademyLevel academylevel : academyLevels) {
				level = new Level();
				level.setName(levelBiz.findLevelById(academylevel.getLevelId())
						.getName());
				level.setId(levelBiz.findLevelById(academylevel.getLevelId())
						.getId());
				academylevel.setLevel(level);
			}
		}
		branchlist = this.academyBztchBranchBiz
				.findGrantedBranch(academyId, id);
		return SUCCESS;
	}
	
	
	/**
	 * 基础层次集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "gl_level_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String glLevelList() throws Exception {
		academyLevels = academyLevelBiz.findBatchId(id);
		 levellst = levelBiz.findAllLevelsByDeleteFlag();
		
		return SUCCESS;
	}
	
	
	

	/**
	 * 专业集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "base_majors_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"academyMajors.*,id"
	}) })
	public String baseMajorList() throws Exception {
		academyMajors = academyMajorBiz.findAcademyMajorByLevel(id);

		return SUCCESS;
	}
	
	/**
	 * 全局专业集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "gl_majors_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String glMajorList() throws Exception {
		majorlst = baseMajorBiz.findBaseMajorByFlag();

		return SUCCESS;
	}
	
	/**
	 * 跟进人集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "gj_user_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String gjUserList() throws Exception {
		users = userBiz.findUsersForBranch();
		return SUCCESS;
	}
	
	
	/**
	 * 所有总部人员
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "zong_bu_user_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"users.*,academyId",
			"excludeProperties",
			"users.*.code,users.*.createdTime,users.*.creatorId," +
			"users.*.deleteFlag,users.*.department,users.*.departmentId," +
			"users.*.email,users.*.job," +
			"users.*.jobId,users.*.mobile,users.*.org," +
			"users.*.orgId,users.*.password,users.*.photoUrl," +
			"users.*.status,users.*.telephone,users.*.type," +
			"users.*.updatePasswordTime,users.*.updatedTime,users.*.updaterId," +
			"users.*.userName,users.*.academylst"
	}) })
	public String zongBuUserList() throws Exception {
		//users = userBiz.findUserFollowUpListByOrgId(BranchEnum.Admin.value());
		users=uGroupUserBiz.findUserByUserGroupId(Constants.CC_GROUP_ID);
		return SUCCESS;
	}
	

	/**
	 * 查询当前中心所有用户
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_user_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmUserList() throws Exception {
		users = userBiz.findUsersByOrgId(this.getUser().getOrgId());
		return SUCCESS;
	}

	// /**
	// * 院校招生批次与学习中心级联
	// *
	// * @return
	// * @throws Exception
	// */
	// @Action(value = "cascade_branch_list", results = { @Result(name =
	// "success", type = "json", params = {
	// "contentType", "text/json" }) })
	// public String cascadeBranch() throws Exception {
	// branchlist = this.academyBztchBranchBiz
	// .findGrantedBranch(academyId, id);
	// return SUCCESS;
	// }

	public List<Academy> getAcademysAcademies() {
		return academysAcademies;
	}

	public void setAcademysAcademies(List<Academy> academysAcademies) {
		this.academysAcademies = academysAcademies;
	}

	public List<AcademyEnrollBatch> getAcademyEnrollBatchs() {
		return academyEnrollBatchs;
	}

	public void setAcademyEnrollBatchs(
			List<AcademyEnrollBatch> academyEnrollBatchs) {
		this.academyEnrollBatchs = academyEnrollBatchs;
	}

	public List<AcademyLevel> getAcademyLevels() {
		return academyLevels;
	}

	public void setAcademyLevels(List<AcademyLevel> academyLevels) {
		this.academyLevels = academyLevels;
	}

	public List<AcademyMajor> getAcademyMajors() {
		return academyMajors;
	}

	public void setAcademyMajors(List<AcademyMajor> academyMajors) {
		this.academyMajors = academyMajors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<StudentStatus> getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(List<StudentStatus> studentStatus) {
		this.studentStatus = studentStatus;
	}

	public List<EnrollmentSource> getEnrollmentSources() {
		return enrollmentSources;
	}

	public void setEnrollmentSources(List<EnrollmentSource> enrollmentSources) {
		this.enrollmentSources = enrollmentSources;
	}

	

	public List<Branch> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(List<Branch> branchlist) {
		this.branchlist = branchlist;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public List<GlobalEnrollBatch> getGlobalEnrollBatchs() {
		return globalEnrollBatchs;
	}

	public void setGlobalEnrollBatchs(List<GlobalEnrollBatch> globalEnrollBatchs) {
		this.globalEnrollBatchs = globalEnrollBatchs;
	}

	public List<Level> getLevellst() {
		return levellst;
	}

	public void setLevellst(List<Level> levellst) {
		this.levellst = levellst;
	}

	public List<BaseMajor> getMajorlst() {
		return majorlst;
	}

	public void setMajorlst(List<BaseMajor> majorlst) {
		this.majorlst = majorlst;
	}

	public List<StudentStatus> getStudentStatusStage() {
		return studentStatusStage;
	}

	public void setStudentStatusStage(List<StudentStatus> studentStatusStage) {
		this.studentStatusStage = studentStatusStage;
	}

	public String getStageCode() {
		return stageCode;
	}

	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}

	public LinkedHashMap<String, List<BaseDict>> getEnrollmentWaysMap() {
		return enrollmentWaysMap;
	}

	public void setEnrollmentWaysMap(
			LinkedHashMap<String, List<BaseDict>> enrollmentWaysMap) {
		this.enrollmentWaysMap = enrollmentWaysMap;
	}

	
	
}
