package net.cedu.action.academy;

import java.util.ArrayList;
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
public class JsonAcademyAuditAction extends BaseAction {

	@Autowired
	private AcademyBiz academybiz;              //院校Biz
	@Autowired
	private UserBiz userbiz;                    //系统用户Biz
	
	// 分页结果
	private PageResult<Academy> result = new PageResult<Academy>();
	private List<User> userlist=new ArrayList<User>();       //系统用户集合
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合
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
	@Action(value = "countacademyaudit", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*"
	}) })
	public String CountAcademy() throws Exception {
		try {
				// 查询数量
				result.setRecordCount(academybiz.findAllAcademyCountByAuditStatus(result));
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
	@Action(value = "listacademyaudit", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"excludeProperties",
			"result.*.pictureUrl,"+
			"result.*.shortName,"+
			"result.*.foundedYear,"+
			"result.*.website,"+
			"result.*.scale,"+
			"result.*.introduction,"+
			"result.*.auditer,"+
			"result.*.auditedDate,"+
			"result.*.account,"+
			"result.*.isForceFeePolicy,"+
			"result.*.deleteFlag,"+
			"result.*.creatorId,"+
			"result.*.createdTime,"+
			"result.*.updaterId,"+
			"result.*.updatedTime,"+
			"result.*.address",
			"includeProperties",
			"result.*,"
			
	}) })
	public String AcademyList() throws Exception {
		// 查询集合
		result.setList(academybiz.findAllAcademyByAuditStatus(result));
		return SUCCESS;
	}
	
	
	

	
	
	
	/**
	 * 修改院校审核状态
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updateacademyaudit", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String UpdateAcademyAuditStatus() throws Exception {
		
		try {
			Academy upacademy=new Academy();
			//获取原始详细数据
			upacademy=academybiz.findAcademyById(id);
			upacademy.setAuditStatus(auditstatus);
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
