package net.cedu.action.crm;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.crm.StudentEnrollmentSourceChangeLogBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.crm.Student;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改学生信息
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonUpdateStudentinfoAction extends BaseAction
{
	
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private StudentEnrollmentSourceChangeLogBiz studentEnrollmentSourceChangeLogBiz;
	
	private Student student;

	//招生途径 市场途径
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private BaseDictBiz baseDictBiz;
	// 招生途径
	private List<EnrollmentSource> enrollmentSources = new ArrayList<EnrollmentSource>();
	// 市场途径字典
	private LinkedHashMap<String,List<BaseDict>> enrollmentWaysMap=new LinkedHashMap<String,List<BaseDict>>();
	private int branchId;//学习中心Id
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校招生批次与层次的关系
	
	/**
	 * 呼叫中心更新学生
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_student_info_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updateStudentinfo() throws Exception {
		// 更新学生信息

		if (student != null && student.getId() != 0) {
			Student s = studentBiz.findStudentById(student.getId());
			if (s != null) {
				//招生途径变更
				if(student.getEnrollmentSource()!=0&&student.getEnrollmentSource()!=s.getEnrollmentSource()&&s.getEnrollmentSource()!=0){
					studentEnrollmentSourceChangeLogBiz.addStudentEnrollmentSourceChangeLog(s.getId(), super.getUser().getUserId(), s.getEnrollmentSource(), student.getEnrollmentSource());				
				}
				//名称
				s.setName(student.getName());
				//证件号
				s.setCertNo(student.getCertNo());
				// 证件类型
				s.setCertType(student.getCertType());
				
				s.setMobile(student.getMobile());
				
				s.setPhone(student.getPhone());

				s.setGender(student.getGender());
				s.setLivingPlace(student.getLivingPlace());

				s.setMsn(student.getMsn());
				s.setQq(student.getQq());
				s.setEmail(student.getEmail());
				s.setRemark(student.getRemark());

				// 招生途径
				s.setEnrollmentSource(student.getEnrollmentSource());
				// 合作方ID
				s.setChannelId(student.getChannelId());
				// 市场途径
				s.setEnrollmentWay(student.getEnrollmentWay());
				//数据来源
				s.setStudentDataSource(student.getStudentDataSource());

				//s.setServiceTime(student.getServiceTime());// 希望联系时间
				s.setWorkUnitInfo(student.getWorkUnitInfo());// 单位信息
				
				//学历
				s.setDegree(student.getDegree());
				
				// 最后修改人
				s.setUpdaterId(super.getUser().getUserId());
				// 最后修改时间
				s.setModifiedTime(new Date());
				
				
				
				
				studentBiz.updateStudentInfo(s);
			}

		}

		return SUCCESS;
	}
	
	/**
	 * 招生途径复核——更新学生
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_student_channeltype_checked_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updateStudentChanneltype() throws Exception 
	{
		// 更新学生信息

		if (student != null && student.getId() != 0) {
			Student s = studentBiz.findStudentById(student.getId());
			if (s != null) {
				
				//招生途径变更
				if(student.getEnrollmentSource()!=0&&student.getEnrollmentSource()!=s.getEnrollmentSource()&&s.getEnrollmentSource()!=0){
					studentEnrollmentSourceChangeLogBiz.addStudentEnrollmentSourceChangeLog(s.getId(), super.getUser().getUserId(), s.getEnrollmentSource(), student.getEnrollmentSource());				
				}
				//名称
				s.setName(student.getName());
				//证件号
				s.setCertNo(student.getCertNo());
				// 证件类型
				s.setCertType(student.getCertType());
				
				s.setMobile(student.getMobile());
				
				s.setPhone(student.getPhone());

				s.setGender(student.getGender());
				s.setLivingPlace(student.getLivingPlace());

				s.setMsn(student.getMsn());
				s.setQq(student.getQq());
				s.setEmail(student.getEmail());
				s.setRemark(student.getRemark());

				// 招生途径
				s.setEnrollmentSource(student.getEnrollmentSource());
				// 合作方ID
				s.setChannelId(student.getChannelId());
				// 市场途径
				s.setEnrollmentWay(student.getEnrollmentWay());
				//数据来源
				s.setStudentDataSource(student.getStudentDataSource());
				//招生途径复核
				s.setIsChannelTypeChecked(student.getIsChannelTypeChecked());
				
				//s.setServiceTime(student.getServiceTime());// 希望联系时间
				s.setWorkUnitInfo(student.getWorkUnitInfo());// 单位信息
				
				//全局批次
				if(student.getGlobalBatch()!=0)
				{
					s.setGlobalBatch(student.getGlobalBatch());
				}
				
//				//院校
//				s.setAcademyId(student.getAcademyId());
//				//招生批次
//				s.setEnrollmentBatchId(student.getEnrollmentBatchId());
//				//层次
//				AcademyLevel alevel=this.academyLevelBiz.findById(student.getLevelId());
//				if(student.getLevelId()!=0 && alevel!=null)
//				{
//					s.setLevelId(alevel.getLevelId());
//				}
//				else
//				{
//					s.setLevelId(0);
//				}
//				//专业
//				s.setMajorId(student.getMajorId());

				// 最后修改人
				s.setUpdaterId(super.getUser().getUserId());
				// 最后修改时间
				s.setModifiedTime(new Date());
				
				studentBiz.updateStudentInfo(s);
			}

		}

		return SUCCESS;
	}
	
	/**
	 * 市场途径，招生途径
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_student_way_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updateStudentWayList() throws Exception {
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
		
	    
		List<BaseDict> enrollmentWays = baseDictBiz.findBaseDictsByType(Constants.BASEDICT_STYLE_ENROLLMENTWAY,branchId);
		//如果不是总部，则附加总部部分的市场途径
		if(branchId!=1)
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
		
		return SUCCESS;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}

	public List<EnrollmentSource> getEnrollmentSources() {
		return enrollmentSources;
	}

	public void setEnrollmentSources(List<EnrollmentSource> enrollmentSources) {
		this.enrollmentSources = enrollmentSources;
	}

	public LinkedHashMap<String, List<BaseDict>> getEnrollmentWaysMap() {
		return enrollmentWaysMap;
	}

	public void setEnrollmentWaysMap(
			LinkedHashMap<String, List<BaseDict>> enrollmentWaysMap) {
		this.enrollmentWaysMap = enrollmentWaysMap;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	
}
