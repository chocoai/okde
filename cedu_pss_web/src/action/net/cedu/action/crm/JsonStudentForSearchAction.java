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
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.BaseMajorBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.basesetting.StuStatusBiz;
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
import net.cedu.entity.enrollment.Channel;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生查询条件ajax_action
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonStudentForSearchAction extends BaseAction {
	@Autowired
	private BranchBiz branchBiz;// 学习中心_业务层接口
	private List<Branch> branchList = new ArrayList<Branch>();// 学习中心集合(不包括总部)

	@Autowired
	private AcademyBiz academyBiz;// 院校_业务层接口
	private List<Academy> academyList = new ArrayList<Academy>();// 院校集合

	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;// 全局批次_业务层接口
	private List<GlobalEnrollBatch> globalBatchList = new ArrayList<GlobalEnrollBatch>();// 全局批次集合

	@Autowired
	private LevelBiz levelBiz;// 层次_业务层接口
	private List<Level> levelList = new ArrayList<Level>();// 层次集合

	@Autowired
	private BaseMajorBiz baseMajorBiz;// 基础专业_业务层接口
	private List<BaseMajor> baseMajorList = new ArrayList<BaseMajor>();// 基础专业集合

	@Autowired
	private BaseDictBiz baseDictBiz;// 基础字典_业务层接口
	private List<BaseDict> studentDataSourceList = new ArrayList<BaseDict>();// 数据来源集合

	private List<BaseDict> enrollmentWayList = new ArrayList<BaseDict>();// 市场途径集合

	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;// 招生途径_业务层接口
	private List<EnrollmentSource> enrollmentSourceList = new ArrayList<EnrollmentSource>();// 招生途径集合

	@Autowired
	private StuStatusBiz stuStatusBiz;// 学生状态_业务层接口
	private List<StudentStatus> stuStatusStageList = new ArrayList<StudentStatus>();// 学生阶段集合
	private String stuStatusStage;// 阶段编码
	private List<StudentStatus> stuStatusList = new ArrayList<StudentStatus>();// 学生状态集合

	@Autowired
	private UserBiz userBiz;// 用户_业务层接口
	private List<User> userList = new ArrayList<User>();// 跟进人集合
	private int branchId;// 学习中心ID

	// 市场途径字典
	private LinkedHashMap<String, List<BaseDict>> enrollmentWaysMap = new LinkedHashMap<String, List<BaseDict>>();
	
	@Autowired
	private ChannelBiz channelBiz;//合作方_业务层接口
	private int channelType; //合作方类别
	private List<Channel> channelList=new ArrayList<Channel>(); //合作方集合
	

	/**
	 * 学习中心集合(不包含总部的所有启用未删除的数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "branch_all_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"branchList.*",
			"excludeProperties",
			"branchList.*.batchComplete,branchList.*.batchTarget,branchList.*.branchIds," +
			"branchList.*.code,branchList.*.createdTime,branchList.*.creator," +
			"branchList.*.creatorId,branchList.*.currentBatchTarget,branchList.*.deleteFlag," +
			"branchList.*.level,branchList.*.logicNode,branchList.*.parent," +
			"branchList.*.updatedTime,branchList.*.updater,branchList.*.updaterId," +
			"branchList.*.userCount,branchList.*.shortName,branchList.*.parentId,"+
			"branchList.*.type"
			
	}) })
	public String branchAllList() throws Exception {
		branchList = this.branchBiz.findBranchForModel();
		Collections.sort(branchList, new Comparator() {
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
	 * 院校集合(所有启用未删除的数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "academy_all_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String academyAllList() throws Exception {
		academyList = this.academyBiz.findAllAcademyByFlagFalse();
		Collections.sort(academyList, new Comparator() {
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
	 * 全局批次集合(所有启用未删除的数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "global_batch_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String globalBatchList() throws Exception {
		globalBatchList = this.globalEnrollBatchBiz
				.findAllGlobalEnrollBatchByDeleteFlag();
		return SUCCESS;
	}

	/**
	 * 层次集合(所有启用未删除的数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "level_all_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String levelAllList() throws Exception {
		levelList = this.levelBiz.findAllLevelsByDeleteFlag();
		return SUCCESS;
	}

	/**
	 * 基础专业集合(所有启用未删除的数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "base_major_all_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String baseMajorAllList() throws Exception {
		baseMajorList = this.baseMajorBiz.findBaseMajorByFlag();
		return SUCCESS;
	}

	/**
	 * 数据来源集合(所有启用未删除的数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "data_source_all_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String dataSourceAllList() throws Exception {
		studentDataSourceList = this.baseDictBiz
				.findAllBaseDictsByType(Constants.BASEDICT_STYLE_STUDATASOURCE);
		return SUCCESS;
	}

	/**
	 * 市场途径集合(所有启用未删除的数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "enrollment_way_all_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String enrollmentWayAllList() throws Exception {
		enrollmentWayList = this.baseDictBiz.findBaseDictsByType(
				Constants.BASEDICT_STYLE_ENROLLMENTWAY, getUser().getOrgId());
		return SUCCESS;
	}

	/**
	 * 招生途径集合(所有启用未删除的数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "enrollment_source_all_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String enrollmentSourceAllList() throws Exception {
		enrollmentSourceList = this.enrollmentSourceBiz
				.findAllEnrollmentSourceByDeleteFlag();
		return SUCCESS;
	}

	/**
	 * 学生阶段集合(所有启用未删除的数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "stu_status_stage_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String stuStatusStageList() throws Exception {
		stuStatusStageList = this.stuStatusBiz.findStatusStage();
		return SUCCESS;
	}

	/**
	 * 学生状态集合(所有启用未删除的数据) 与学生阶段级联
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "stu_status_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			
			"includeProperties",
			"stuStatusList.*,stuStatusStage"}) })
	public String stuStatusList() throws Exception {
		stuStatusList = this.stuStatusBiz
				.findStatusNamesByStageCode(stuStatusStage);
		return SUCCESS;
	}

	/**
	 * 跟进人集合(所有启用未删除的数据) 与学习中心级联
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "stu_followup_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"userList.*.code,userList.*.createdTime,userList.*.creatorId," +
			"userList.*.deleteFlag,userList.*.department,userList.*.departmentId," +
			"userList.*.email,userList.*.job," +
			"userList.*.jobId,userList.*.mobile,userList.*.org," +
			"userList.*.orgId,userList.*.password,userList.*.photoUrl," +
			"userList.*.status,userList.*.telephone,userList.*.type," +
			"userList.*.updatePasswordTime,userList.*.updatedTime,userList.*.updaterId," +
			"userList.*.userName,userList.*.academylst",
			"includeProperties",
			"userList.*,branchId"
    }) })
	public String stuFollowUpList() throws Exception {
		userList = this.userBiz.findUserFollowUpListByOrgId(branchId);
		Collections.sort(userList, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((User) arg0).getUserName();
				String name2 = ((User) arg1).getUserName();
				return cmp.compare(name1, name2);
			}
		});
		return SUCCESS;
	}

	/**
	 * 学生查询共有的ajax集合 院校、全局批次、层次、基础专业、数据来源、市场途径、招生途径、学生阶段
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "for_stu_search_all_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"academyList.*.address,academyList.*.introduction,academyList.*.account," +
			"academyList.*.auditStatus,academyList.*.auditedDate,academyList.*.auditer," +
			"academyList.*.code,academyList.*.complete,academyList.*.contractEndtime," +
			"academyList.*.createdTime,academyList.*.creatorId,academyList.*.deleteFlag," +
			"academyList.*.expectTarget,academyList.*.foundedYear,academyList.*.interfaceSettingStatus," +
			"academyList.*.isForceFeePolicy,academyList.*.pictureUrl,academyList.*.projectManagerId," +
			"academyList.*.projectManagerName,academyList.*.rebatesFeesubject,academyList.*.scale," +
			"academyList.*.shortName,academyList.*.target,academyList.*.telephone," +
			"academyList.*.updatedTime,academyList.*.updaterId,academyList.*.usingStatus," +
			"academyList.*.website," +
			"baseMajorList.*.code,baseMajorList.*.createdTime,baseMajorList.*.creatorId," +
			"baseMajorList.*.deleteFlag,baseMajorList.*.updatedTime,baseMajorList.*.updaterId," +
			"enrollmentSourceList.*.baomingNum,enrollmentSourceList.*.code,enrollmentSourceList.*.createdTime," +
			"enrollmentSourceList.*.creatorId,enrollmentSourceList.*.deleteFlag,enrollmentSourceList.*.isneedRebates," +
			"enrollmentSourceList.*.jiaofeiMoney,enrollmentSourceList.*.jiaofeiNum,enrollmentSourceList.*.jiaofeilv," +
			"enrollmentSourceList.*.luquNum,enrollmentSourceList.*.luqulv,enrollmentSourceList.*.sourceRebatesFeesubject," +
			"enrollmentSourceList.*.subjectnames,enrollmentSourceList.*.type,enrollmentSourceList.*.updatedTime," +
			"enrollmentSourceList.*.updaterId," +
			"globalBatchList.*.batch,globalBatchList.*.belongYear,globalBatchList.*.createdTime," +
			"globalBatchList.*.creatorId,globalBatchList.*.deleteFlag,globalBatchList.*.updatedTime," +
			"globalBatchList.*.updaterId," +
			"levelList.*.code,levelList.*.createdTime,levelList.*.creatorId," +
			"levelList.*.deleteFlag,levelList.*.updatedTime,levelList.*.updaterId," +
			"stuStatusStageList.*.code,stuStatusStageList.*.createdTime,stuStatusStageList.*.creatorId," +
			"stuStatusStageList.*.deleteFlag,stuStatusStageList.*.updatedTime,stuStatusStageList.*.updaterId," +
			"studentDataSourceList.*.baseDictList,studentDataSourceList.*.code,studentDataSourceList.*.createdTime," +
			"studentDataSourceList.*.creatorId,studentDataSourceList.*.deleteFlag,studentDataSourceList.*.logicNode," +
			"studentDataSourceList.*.orderNumber,studentDataSourceList.*.parentNode,studentDataSourceList.*.status," +
			"studentDataSourceList.*.type,studentDataSourceList.*.updatedTime,studentDataSourceList.*.updaterId",
			"includeProperties",
			"academyList.*,globalBatchList.*,levelList.*,baseMajorList.*,studentDataSourceList.*," +
			"enrollmentWaysMap.*,enrollmentSourceList.*,stuStatusStageList.*"
			
	}) })
	public String stuAllAjaxList() throws Exception {
		academyList = this.academyBiz.findAllAcademyByFlagFalse();// 院校集合
		Collections.sort(academyList, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Academy) arg0).getName();
				String name2 = ((Academy) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		globalBatchList = this.globalEnrollBatchBiz
				.findAllGlobalEnrollBatchByDeleteFlag();// 全局批次集合
		levelList = this.levelBiz.findAllLevelsByDeleteFlag();// 层次集合
		baseMajorList = this.baseMajorBiz.findBaseMajorByFlag();// 基础专业集合
		studentDataSourceList = this.baseDictBiz
				.findAllBaseDictsByType(Constants.BASEDICT_STYLE_STUDATASOURCE);// 数据来源集合
		// enrollmentWayList=this.baseDictBiz.findBaseDictsByType(Constants.BASEDICT_STYLE_ENROLLMENTWAY,getUser().getOrgId());//市场途径集合
		//市场途径
		LinkedHashMap<BaseDict, List<BaseDict>> map = new LinkedHashMap<BaseDict, List<BaseDict>>();

		List<BaseDict> enrollmentWays = baseDictBiz.findBaseDictsByType(10,
				getUser().getOrgId());

		for (int i = 0; i < enrollmentWays.size(); i++) {
			BaseDict baseDict = enrollmentWays.get(i);
			if (baseDict.getParentNode() == 0) {
				map.put(baseDict, new ArrayList<BaseDict>());
			}
		}
		Set<BaseDict> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			BaseDict baseDictKey = (BaseDict) it.next();
			List<BaseDict> baseDictList = map.get(baseDictKey);
			for (int i = 0; i < enrollmentWays.size(); i++) {
				BaseDict baseDict = enrollmentWays.get(i);
				if (baseDict.getParentNode() == baseDictKey.getId()) {
					baseDictList.add(baseDict);
				}
			}

		}
		Set<BaseDict> key1 = map.keySet();
		for (Iterator it = key1.iterator(); it.hasNext();) {
			BaseDict baseDictKey = (BaseDict) it.next();
			List<BaseDict> baseDictList = map.get(baseDictKey);
			if (baseDictList.size() == 0) {
				baseDictList.add(baseDictKey);
			}
			enrollmentWaysMap.put(baseDictKey.getName(), baseDictList);
		}

		enrollmentSourceList = this.enrollmentSourceBiz
				.findAllEnrollmentSourceByDeleteFlag();// 招生途径集合
		stuStatusStageList = this.stuStatusBiz.findStatusStage();// 学生阶段集合

		return SUCCESS;
	}
		
	/**
	 * 学生查询共有的ajax集合 院校、全局批次、层次、基础专业
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "for_stu_recharge_search_half_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"academyList.*.introduction" }) })
	public String stuRechargeAllAjaxList() throws Exception {
		academyList = this.academyBiz.findAllAcademyByFlagFalse();// 院校集合
		Collections.sort(academyList, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Academy) arg0).getName();
				String name2 = ((Academy) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		globalBatchList = this.globalEnrollBatchBiz
				.findAllGlobalEnrollBatchByDeleteFlag();// 全局批次集合
		levelList = this.levelBiz.findAllLevelsByDeleteFlag();// 层次集合
		baseMajorList = this.baseMajorBiz.findBaseMajorByFlag();// 基础专业集合
		return SUCCESS;
	}
	
	/**
	 * 合作方级联（通过学习中心Id和合作方类别Id）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "channel_list_for_stu_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String findbcla() throws Exception 
	{
		if(channelType!=0 && branchId!=0)
		{
			channelList=this.channelBiz.findChannelListByChannelTypeIdAndBranchId(channelType, branchId);
			if(channelList!=null && channelList.size()>0)
			{
				for (Channel channel : channelList)
				{
					channel.setName(PingYingHanZiUtil.getNameFirstZiMuToUpperCaseCase(channel.getName())+channel.getName());
				}
				Collections.sort(channelList, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						Comparator cmp = Collator
								.getInstance(java.util.Locale.CHINA);
						String name1 = ((Channel) arg0).getName();
						String name2 = ((Channel) arg1).getName();
						return cmp.compare(name1, name2);
					}
				});
			}
		}
		return SUCCESS;
	}


	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}

	public List<Academy> getAcademyList() {
		return academyList;
	}

	public void setAcademyList(List<Academy> academyList) {
		this.academyList = academyList;
	}

	public List<GlobalEnrollBatch> getGlobalBatchList() {
		return globalBatchList;
	}

	public void setGlobalBatchList(List<GlobalEnrollBatch> globalBatchList) {
		this.globalBatchList = globalBatchList;
	}

	public List<Level> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<Level> levelList) {
		this.levelList = levelList;
	}

	public List<BaseMajor> getBaseMajorList() {
		return baseMajorList;
	}

	public void setBaseMajorList(List<BaseMajor> baseMajorList) {
		this.baseMajorList = baseMajorList;
	}

	public List<BaseDict> getStudentDataSourceList() {
		return studentDataSourceList;
	}

	public void setStudentDataSourceList(List<BaseDict> studentDataSourceList) {
		this.studentDataSourceList = studentDataSourceList;
	}

	public List<BaseDict> getEnrollmentWayList() {
		return enrollmentWayList;
	}

	public void setEnrollmentWayList(List<BaseDict> enrollmentWayList) {
		this.enrollmentWayList = enrollmentWayList;
	}

	public List<EnrollmentSource> getEnrollmentSourceList() {
		return enrollmentSourceList;
	}

	public void setEnrollmentSourceList(
			List<EnrollmentSource> enrollmentSourceList) {
		this.enrollmentSourceList = enrollmentSourceList;
	}

	public List<StudentStatus> getStuStatusStageList() {
		return stuStatusStageList;
	}

	public void setStuStatusStageList(List<StudentStatus> stuStatusStageList) {
		this.stuStatusStageList = stuStatusStageList;
	}

	public String getStuStatusStage() {
		return stuStatusStage;
	}

	public void setStuStatusStage(String stuStatusStage) {
		this.stuStatusStage = stuStatusStage;
	}

	public List<StudentStatus> getStuStatusList() {
		return stuStatusList;
	}

	public void setStuStatusList(List<StudentStatus> stuStatusList) {
		this.stuStatusList = stuStatusList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public LinkedHashMap<String, List<BaseDict>> getEnrollmentWaysMap() {
		return enrollmentWaysMap;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public List<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

}
