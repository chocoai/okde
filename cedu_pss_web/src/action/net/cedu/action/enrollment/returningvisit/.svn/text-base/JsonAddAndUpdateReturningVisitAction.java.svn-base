package net.cedu.action.enrollment.returningvisit;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.ReturningVisitBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.MonitorResults;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.ReturningVisit;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddAndUpdateReturningVisitAction extends BaseAction{

	private static final long serialVersionUID = -5403440088122657863L;
	
	@Autowired
	private StudentBiz stubiz;
	@Autowired
	private ReturningVisitBiz returningVisitBiz;
	
	private Student student;//学生信息实体
	private MonitorResults moniterresults;//监控结果实体
	private ReturningVisit returningvisit;//回访记录实体
	private boolean addrltbool=true;//判断添加操作是否成功
	
	/**
	 * 修改
	 */
	@Action(value="update_add_stu_and_returning_visit",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		try {
			int userid=super.getUser().getUserId();
			Student stu = new Student();
			
			if (student!=null&&student.getId()>0) {
				if (moniterresults != null && moniterresults.getId() > 0) {
					returningvisit.setMonitorResults(moniterresults.getId());
					returningvisit.setStudentId(student.getId());
					returningvisit.setCreatorId(userid);
					returningvisit.setUpdaterId(userid);
					returningvisit.setCreatedTime(new Date());
					returningvisit.setUpdatedTime(new Date());
					returningvisit.setDeleteFlag(Constants.DELETE_FALSE);
					returningvisit.setTypes(Constants.RETURNING_VISIT_SSTATUS);
					//returningvisit.setContent(content);
					
					
					returningVisitBiz.addReturningVisit(returningvisit);
					 // 最新监控结果
					student.setLastMonitorResult(moniterresults.getId());
				}
					stu = stubiz.findStudentById(student.getId());
					
					if (stu!=null) {
						stu.setLivingPlace(student.getLivingPlace());
						stu.setMsn(student.getMsn());
						stu.setQq(student.getQq());
						stu.setEmail(student.getEmail());
						stu.setRemark(student.getRemark());
						stu.setWorkUnitInfo(student.getWorkUnitInfo());
//						if (student.getStatus()>0) {
//							stu.setStatus(student.getStatus());
//						}
						stu.setMonitorStatus(student.getMonitorStatus());//监控状态
						stu.setLastMonitorResult(student.getLastMonitorResult());
						stu.setUpdaterId(userid);
						//2012-10-21 屏蔽
						////2012.5.17上午   金彦老师要求学生回访增加招生复核选项 
						//stu.setIsChannelTypeChecked(student.getIsChannelTypeChecked());
						
						stu.setModifiedTime(new Date());
						
						stubiz.updateStudentInfo(stu);
					}else{
						addrltbool=false;
					}
			}else{
				addrltbool=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addrltbool=false;
		}
		return SUCCESS;
	}
	
	//---------------------------------------get and set methods----------------------------------------
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public MonitorResults getMoniterresults() {
		return moniterresults;
	}
	public void setMoniterresults(MonitorResults moniterresults) {
		this.moniterresults = moniterresults;
	}
	public ReturningVisit getReturningvisit() {
		return returningvisit;
	}
	public void setReturningvisit(ReturningVisit returningvisit) {
		this.returningvisit = returningvisit;
	}
	public boolean isAddrltbool() {
		return addrltbool;
	}
	public void setAddrltbool(boolean addrltbool) {
		this.addrltbool = addrltbool;
	}
}
