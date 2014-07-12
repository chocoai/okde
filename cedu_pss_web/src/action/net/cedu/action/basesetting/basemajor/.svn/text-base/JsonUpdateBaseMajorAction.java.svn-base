package net.cedu.action.basesetting.basemajor;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseMajorBiz;
import net.cedu.entity.basesetting.BaseMajor;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonUpdateBaseMajorAction extends BaseAction{

	private static final long serialVersionUID = -1524729509023637943L;
	
	@Autowired
	private BaseMajorBiz baseMajorBiz;

	private BaseMajor baseMajor;//专业(基础设置)实体
	private int baseMajorId;//专业的ID
	private boolean updaterltbool=true;//判断修改操作是否成功
	private boolean sameinfobool=true;
	
	//修改专业
	@Action(value="update_base_major",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		
		BaseMajor oldBaseMajor = null;
		int userid;//获取登陆者ID
		try {
			oldBaseMajor = new BaseMajor();
			userid = super.getUser().getUserId();
			//按ID先获得实体的属性
			oldBaseMajor = baseMajorBiz.findBaseMajorbyId(baseMajorId);
			
			//根据传递过来的参数修改实体
			oldBaseMajor.setCode(baseMajor.getCode());
			oldBaseMajor.setName(baseMajor.getName());
			oldBaseMajor.setUpdatedTime(new Date());
			oldBaseMajor.setUpdaterId(userid);
			
			sameinfobool = baseMajorBiz.modifyBaseMajor(oldBaseMajor);
		} catch (Exception e) {
			e.printStackTrace();
			updaterltbool = false;
		}
		return SUCCESS;
	}
	
	//--------------------------------------------get and set methods---------------------------------
	public BaseMajor getBaseMajor() {
		return baseMajor;
	}
	public void setBaseMajor(BaseMajor baseMajor) {
		this.baseMajor = baseMajor;
	}
	public int getBaseMajorId() {
		return baseMajorId;
	}
	public void setBaseMajorId(int baseMajorId) {
		this.baseMajorId = baseMajorId;
	}
	public boolean isUpdaterltbool() {
		return updaterltbool;
	}
	public void setUpdaterltbool(boolean updaterltbool) {
		this.updaterltbool = updaterltbool;
	}
	public boolean isSameinfobool() {
		return sameinfobool;
	}
	public void setSameinfobool(boolean sameinfobool) {
		this.sameinfobool = sameinfobool;
	}
}
