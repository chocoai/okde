package net.cedu.action.academy;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.User;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json院校管理
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonAcademyAction extends BaseAction {

	@Autowired
	private AcademyBiz academybiz;              //院校Biz
	@Autowired
	private UserBiz userbiz;                    //系统用户Biz
	
	// 分页结果
	private PageResult<Academy> result = new PageResult<Academy>();
	private List<User> userlist=new ArrayList<User>();       //系统用户集合
	private List<Academy> academylist=new ArrayList<Academy>();       //院校集合
	private Academy academy=new Academy();                   //院校实体
	private int id;                                          //院校ID
	private int type;                                        //类型
	private int usingstatus;                                 //启用状况
	private int auditstatus;                                 //审核状态

	
	
	
	/**
	 * 查询院校(数量)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countacademy", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*"
	}) })
	public String CountAcademy() throws Exception {
		try {
				// 查询数量
				result.setRecordCount(academybiz.findAllAcademyCount(result));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return SUCCESS;
	}

	
	/**
	 * 查询院校(数据)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listacademy", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties",
			"result.*",
			"excludeProperties",
			"result.*.introduction,"+
			"result.*.address,"+
			"result.*.website,"
			
	}) })
	public String AcademyList() throws Exception {
		// 查询集合
		result.setList(academybiz.findAllAcademy(result));
		return SUCCESS;
	}
	
	/**
	 * 查询院校(数据)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listacademyfalse", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties",
			"academylist.*",
			"excludeProperties",
			"academylist.*.account,academylist.*.address,academylist.*.auditStatus," +
			"academylist.*.auditedDate,academylist.*.auditer,academylist.*.code," +
			"academylist.*.complete,academylist.*.contractEndtime,academylist.*.createdTime," +
			"academylist.*.creatorId,academylist.*.deleteFlag,academylist.*.expectTarget," +
			"academylist.*.foundedYear,academylist.*.interfaceSettingStatus,academylist.*.introduction," +
			"academylist.*.isForceFeePolicy,academylist.*.pictureUrl,academylist.*.projectManagerId," +
			"academylist.*.projectManagerName,academylist.*.rebatesFeesubject,academylist.*.scale," +
			"academylist.*.shortName,academylist.*.target,academylist.*.telephone," +
			"academylist.*.updatedTime,academylist.*.updaterId,academylist.*.usingStatus," +
			"academylist.*.website"
			
	}) })
	public String AcademyListFalse() throws Exception {
		// 查询集合
		academylist=academybiz.findAllAcademyByFlagFalse();
		Collections.sort(academylist, new Comparator() {
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
	 *  删除院校
	 * @return
	 * @throws Exception
	 */
	@Action(value = "deleteacademy", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String DeleteAcademy() throws Exception {
		// 查询集合
		try {
			//执行删除操作
			academybiz.deleteAcademy(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 修改院校启用状态
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updateacademyusingstatus", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String UpdateAcademyUsingStatus() throws Exception {
		
		try {
			
			Academy upacademy=new Academy();
			//获取原始详细数据
			upacademy=academybiz.findAcademyById(id);
			upacademy.setUsingStatus(usingstatus);
			upacademy.setUpdaterId(super.getUser().getUserId());
			upacademy.setUpdatedTime(new Date());
			//执行修改操作
			academybiz.updateAcademy(upacademy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 修改院校审核状态
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updateacademyauditstatus", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String UpdateAcademyAuditStatus() throws Exception {
		
		try {
			Academy upacademy=new Academy();
			//获取原始详细数据
			upacademy=academybiz.findAcademyById(id);
			upacademy.setAuditStatus(Constants.AUDIT_STATUS_FALSE);
			upacademy.setAuditer(super.getUser().getUserId());
			upacademy.setUpdaterId(super.getUser().getUserId());
			upacademy.setUpdatedTime(new Date());
			//执行修改操作
			academybiz.updateAcademy(upacademy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 查询所有项目经理
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listfindmanager", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"userlist.*",
			"excludeProperties",
			"userlist.*.academylst,userlist.*.code,userlist.*.createdTime," +
			"userlist.*.creatorId,userlist.*.deleteFlag,userlist.*.department," +
			"userlist.*.departmentId,userlist.*.email," +
			"userlist.*.job,userlist.*.jobId,userlist.*.mobile," +
			"userlist.*.org,userlist.*.orgId,userlist.*.password," +
			"userlist.*.photoUrl,userlist.*.status,userlist.*.telephone," +
			"userlist.*.type,userlist.*.updatePasswordTime,userlist.*.updatedTime," +
			"userlist.*.updaterId,userlist.*.userName"
	}) })
	public String FindManagerList() throws Exception {
		
		try {
			//获取系统用户集合
			userlist=userbiz.findUsersForAdmin();
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

	public PageResult<Academy> getResult() {
		return result;
	}

	public void setResult(PageResult<Academy> result) {
		this.result = result;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUsingstatus() {
		return usingstatus;
	}

	public void setUsingstatus(int usingstatus) {
		this.usingstatus = usingstatus;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public int getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(int auditstatus) {
		this.auditstatus = auditstatus;
	}

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}

	public List<Academy> getAcademylist() {
		return academylist;
	}

	public void setAcademylist(List<Academy> academylist) {
		this.academylist = academylist;
	}

	 
	
}
