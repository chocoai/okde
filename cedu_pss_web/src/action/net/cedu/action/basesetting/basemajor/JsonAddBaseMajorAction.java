package net.cedu.action.basesetting.basemajor;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseMajorBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.BaseMajor;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddBaseMajorAction extends BaseAction{

	private static final long serialVersionUID = 6193797569726572261L;
	
	@Autowired
	private BaseMajorBiz baseMajorBiz;
	
	private BaseMajor baseMajor;//专业(基础设置)实体
	private boolean addrltbool=true;//判断添加操作是否成功
	private boolean errormsg=true;//有重复数据时的判断
	
	/**
	 * 添加专业(基础设置)
	 */
	@Action(value="add_base_major",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){

		try {
			if (baseMajor!=null) {
				int userid = super.getUser().getUserId();//获取登陆者ID
				//给专业实体设置默认参数
				baseMajor.setCreatorId(userid);
				baseMajor.setCreatedTime(new Date());
				baseMajor.setUpdaterId(userid);
				baseMajor.setUpdatedTime(new Date());
				baseMajor.setDeleteFlag(Constants.DELETE_FALSE);
				errormsg = baseMajorBiz.addBaseMajor(baseMajor);
			}else{
				addrltbool=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addrltbool=false;
		}

		return SUCCESS;
	}
	//------------------------------------------get and set methods--------------------------------
	public BaseMajor getBaseMajor() {
		return baseMajor;
	}
	public void setBaseMajor(BaseMajor baseMajor) {
		this.baseMajor = baseMajor;
	}
	public boolean isAddrltbool() {
		return addrltbool;
	}
	public void setAddrltbool(boolean addrltbool) {
		this.addrltbool = addrltbool;
	}
	public boolean isErrormsg() {
		return errormsg;
	}
	public void setErrormsg(boolean errormsg) {
		this.errormsg = errormsg;
	}
}
