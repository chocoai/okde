package net.cedu.action.academy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.academy.AcademyCommunicationRecordBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.AcademyCommunicationRecord;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json沟通记录管理
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonAcademyCommunicationAction extends BaseAction {

	@Autowired
	private AcademyCommunicationRecordBiz acrbiz;              //院校沟通Biz
	@Autowired
	private AcademyBiz academyBiz;							   //院校biz
	// 分页结果
	private PageResult<AcademyCommunicationRecord> results = new PageResult<AcademyCommunicationRecord>();
	private Academy academy;                                 //院校实体
	private AcademyCommunicationRecord acr=new AcademyCommunicationRecord();                  //沟通记录实体
	private int id;                                          //院校沟通ID
	private int academyId;                                   //院校ID
	private int projectManagerId;                            //项目经理ID
	private int linkManId;                                   //联系人ID
	private int linkMan;                                     //查询联系人ID
	private String title;                                    //标题 
	private String subject;                                  //查询标题
 	private String comresult;                                //查询结果
	private String content;                                  //内容
	private String result;                                   //结果
	private String starttime;       
	private String endtime;   								 //时间
	//分页参数
	private int count;                                      
	private int pindex;                                      
	private int psize;                                       
	private List<AcademyCommunicationRecord> acrlst=new ArrayList<AcademyCommunicationRecord>();  //数据集合
	

	/**
	 * 新增沟通记录
	 * @return
	 * @throws Exception
	 */
	@Action(value = "addacademycomunicationrecord", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"
			}) })
	public String AddAcademyComunicationRecord() throws Exception {
		
		try {
			if(title!=null)
			{
				acr.setSubject(title);
			}
			if(content!=null)
			{
				acr.setContent(content);
			}
			if(result!=null)
			{
				acr.setResult(result);
			}
			acr.setAcademyLinkmanId(linkManId);	
			acr.setAcademyId(academyId);
			//获取对应院校联系人
			Academy temp = academyBiz.findAcademyById(academyId);
			if(temp!=null)
			{
				acr.setCommunicationUserId(temp.getProjectManagerId());
			}
			acr.setComtime(new Date());
			acr.setCreatorId(super.getUser().getUserId());
			acr.setCreatedTime(new Date());
			//执行添加操作
			acrbiz.addAcademy(acr);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 删除沟通记录
	 * @return
	 * @throws Exception
	 */
	@Action(value = "deleteacademycomunicationrecord", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String DeleteAcademyComunicationRecord() throws Exception {
		
		try {
			//执行删除操作
			acrbiz.deleteAcademy(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 修改沟通记录
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updateacademycomunicationrecord", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String UpdateAcademyComunicationRecord() throws Exception {
		
		try {
			AcademyCommunicationRecord acr=new AcademyCommunicationRecord();
			//获取沟通记录原始数据
			acr=acrbiz.findAcademyCommunicationRecordById(id);
			if(subject!=null && subject!="")
			{
				acr.setSubject(subject);
			}
			if(content!=null && content!="")
			{
				acr.setContent(content);
			}
			if(result!=null && result!="")
			{
				acr.setResult(result);
			}
			acr.setAcademyLinkmanId(linkManId);	
			acr.setAcademyId(academyId);
			//获取对应院校联系人
			Academy temp = academyBiz.findAcademyById(academyId);
			if(temp!=null)
			{
				acr.setCommunicationUserId(temp.getProjectManagerId());
			}
			acr.setComtime(new Date());
			acr.setUpdaterId(super.getUser().getUserId());
			acr.setUpdatedTime(new Date());
			//执行修改操作
			acrbiz.updateAcademy(acr);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 查询沟通记录(数量)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countacademycomunicationrecord", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"results.*,academyId,linkMan,subject,comresult,time"
	}) })
	public String AcademyComunicationRecordCount() throws Exception
	{
		
		try {
			//查询数量
			results.setRecordCount(acrbiz.findAcademyCommunicationRecordCount(academyId,linkMan, subject, comresult, starttime,endtime, results));
			//count=results.getRecordCount();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
			
	/**
	 * 查询沟通记录(数据)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listacademycomunicationrecord", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"acrlst.*.updaterId,"+
			"acrlst.*.updatedTime,"+
			"acrlst.*.user.createdTime,"+
			"acrlst.*.user.creatorId,"+
			"acrlst.*.user.deleteFlag,"+
			"acrlst.*.user.email,"+
			"acrlst.*.user.mobile,"+
			"acrlst.*.user.orgId,"+
			"acrlst.*.user.password,"+
			"acrlst.*.user.photoUrl,"+
			"acrlst.*.user.status,"+
			"acrlst.*.user.telephone,"+
			"acrlst.*.user.type,"+
			"acrlst.*.user.userName,"+
			"acrlst.*.user.updaterId,"+
			"acrlst.*.user.updatedTime",
			"includeProperties",
			"results.*,academyId,linkMan,subject,comresult,time"
			
	}) })
	public String AcademyComunicationRecordList() throws Exception
	{
		try {
			//获取数据集合			
			results.setPage(true);
			results.setList(acrbiz.findAcademyCommunicationRecordList(academyId,linkMan, subject, comresult, starttime,endtime, results));
			//acrlst=results.getList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public PageResult<AcademyCommunicationRecord> getResults() {
		return results;
	}


	public void setResults(PageResult<AcademyCommunicationRecord> results) {
		this.results = results;
	}


	public List<AcademyCommunicationRecord> getAcrlst() {
		return acrlst;
	}

	public void setAcrlst(List<AcademyCommunicationRecord> acrlst) {
		this.acrlst = acrlst;
	}

	public AcademyCommunicationRecord getAcr() {
		return acr;
	}


	public void setAcr(AcademyCommunicationRecord acr) {
		this.acr = acr;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public int getProjectManagerId() {
		return projectManagerId;
	}

	public void setProjectManagerId(int projectManagerId) {
		this.projectManagerId = projectManagerId;
	}


	public int getLinkManId() {
		return linkManId;
	}


	public void setLinkManId(int linkManId) {
		this.linkManId = linkManId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	

	public String getStarttime() {
		return starttime;
	}


	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}


	public String getEndtime() {
		return endtime;
	}


	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getComresult() {
		return comresult;
	}

	public void setComresult(String comresult) {
		this.comresult = comresult;
	}


	public int getLinkMan() {
		return linkMan;
	}


	public void setLinkMan(int linkMan) {
		this.linkMan = linkMan;
	}


	public int getPindex() {
		return pindex;
	}


	public void setPindex(int pindex) {
		this.pindex = pindex;
	}


	public int getPsize() {
		return psize;
	}
	
	public void setPsize(int psize) {
		this.psize = psize;
	}


	public int getAcademyId() {
		return academyId;
	}


	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}
	
	
	
}
