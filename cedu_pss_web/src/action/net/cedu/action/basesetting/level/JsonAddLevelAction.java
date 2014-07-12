package net.cedu.action.basesetting.level;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.Level;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddLevelAction extends BaseAction{

	private static final long serialVersionUID = -6673121193121566572L;

	@Autowired
	private LevelBiz levelbiz;
	
	private Level level;//层次实体
	private boolean addrltbool=true;//判断添加操作是否成功
	private boolean errormsg=true;//有重复数据时的判断

	/**
	 * 添加层次信息  BY HXJ
	 */
	@Action(value="add_level",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
	
		int userid = super.getUser().getUserId();
	
		try{
			level.setDeleteFlag(Constants.DELETE_FALSE);
			level.setCreatorId(userid);
			level.setCreatedTime(new Date());
			level.setUpdaterId(userid);
			level.setUpdatedTime(new Date());
			
			errormsg = levelbiz.addLevel(level);
		}catch (Exception e) {
			
			e.printStackTrace();
			addrltbool=false;
		}
		
		return SUCCESS;
	}
	
	//----------------------------------------get and set methods--------------------------------
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
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
