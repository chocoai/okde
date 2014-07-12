package net.cedu.action.enrollment.returningvisit;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.basesetting.MonitorResults;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.Major;

import org.springframework.beans.factory.annotation.Autowired;

public class ListReturningVisitAction extends BaseAction{

	private static final long serialVersionUID = -4028772195152889533L;

	@Autowired
	private StudentBiz studentbiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired
	private MonitorResultsBiz monitorResultsBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentsourceBiz;
	@Autowired
	private BaseDictBiz basedictBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private GlobalEnrollBatchBiz globalenrollbatchBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private MajorBiz majorBiz;
	
	
	private int stuid;
	private Student stu;
	private List<MonitorResults> monitorResultslist;

	/**
	 * 加载学生信息以及 所有基础数据的监控结果
	 */
	public String execute()throws Exception{
		String batchname="";
		AcademyEnrollBatch academyEnrollBatch = new AcademyEnrollBatch();
		stu = studentbiz.findStudentById(stuid);
		if(stu!=null&&stu.getEnrollmentBatchId()>0){
			academyEnrollBatch = academyEnrollBatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
			if(academyEnrollBatch!=null&&!("").equals(academyEnrollBatch.getEnrollmentName())&&academyEnrollBatch.getEnrollmentName()!=null){
				batchname = academyEnrollBatch.getEnrollmentName();
				stu.setAcademyenrollbatchName(batchname);
			}
			//招生途径
			EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(stu.getEnrollmentSource());
			if(enrollmentsource!=null)
			{
				stu.setEnrollmentSourceName(enrollmentsource.getName());
			}
			else
			{
				stu.setEnrollmentSourceName("无");
			}
			//合作方
			Channel channel=channelBiz.findChannel(stu.getChannelId());
			if(channel!=null)
			{
				stu.setChannelName(channel.getName());
			}
			else
			{
				stu.setChannelName("无");
			}
			//市场途径
			BaseDict basedict = basedictBiz.findBaseDictById(stu.getEnrollmentWay());
			if(basedict!=null)
			{
				stu.setEnrollmentWayName(basedict.getName());
			}
			else
			{
				stu.setEnrollmentWayName("无");
			}
			//学习中心
			Branch branch = branchBiz.findBranchById(stu.getBranchId());
			if(branch!=null)
			{
				stu.setBranchName(branch.getName());
			}
			else
			{
				stu.setBranchName("无");
			}
			//批次
			GlobalEnrollBatch globalenrollbatch = globalenrollbatchBiz.findGlobalEnrollBatchById(stu.getGlobalBatch());
			if(globalenrollbatch!=null)
			{
				stu.setGlobalBatchName(globalenrollbatch.getBatch());
			}
			else
			{
				stu.setGlobalBatchName("无");
			}
			//院校
			Academy academy = academyBiz.findAcademyById(stu.getAcademyId());
			if(academy!=null)
			{
				stu.setSchoolName(academy.getName());
			}
			else
			{
				stu.setSchoolName("无");
			}
			//招生批次
			AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
			if(academyenrollbatch!=null)
			{
				stu.setEnrollmentBatchName(academyenrollbatch.getEnrollmentName());
			}
			else
			{
				stu.setEnrollmentBatchName("无");
			}
			//层次
			Level level = levelBiz.findLevelById(stu.getLevelId());
			if(level!=null)
			{
				stu.setLevelName(level.getName());
			}
			else
			{
				stu.setLevelName("无");
			}
			//专业
			Major major = majorBiz.findMajorById(stu.getMajorId());
			if(major!=null)
			{
				stu.setMajorName(major.getName());
			}
			else
			{
				stu.setMajorName("无");
			}
			//数据来源
		    BaseDict bd=this.basedictBiz.findBaseDictById(stu.getStudentDataSource());
		    if(bd!=null)
		    {
		    	stu.setStudentDataSourceName(bd.getName());
		    }
		    else
		    {
		    	stu.setStudentDataSourceName("无");
		    }
		}
		monitorResultslist = monitorResultsBiz.findAllMonitorResults();
		return SUCCESS;
	}

	public Student getStu() {
		return stu;
	}

	public void setStu(Student stu) {
		this.stu = stu;
	}

	public int getStuid() {
		return stuid;
	}

	public void setStuid(int stuid) {
		this.stuid = stuid;
	}

	public List<MonitorResults> getMonitorResultslist() {
		return monitorResultslist;
	}

	public void setMonitorResultslist(List<MonitorResults> monitorResultslist) {
		this.monitorResultslist = monitorResultslist;
	}
	
}
