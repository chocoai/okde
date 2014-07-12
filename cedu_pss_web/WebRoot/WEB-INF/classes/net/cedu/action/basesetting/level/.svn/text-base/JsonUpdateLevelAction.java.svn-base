package net.cedu.action.basesetting.level;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.entity.basesetting.Level;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonUpdateLevelAction extends BaseAction{
	
	private static final long serialVersionUID = 5940774303845034843L;

	@Autowired
	private LevelBiz levelbiz;
	
	private Level level;//层次实体
	private int levelid;
	
	private boolean updaterltbool=true;//判断修改操作是否成功
	private boolean sameinfobool=true;
	/**
	 * 修改层次信息  BY HXJ
	 */
	@Action(value="update_level",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		
		Level oldlevel = null;
		int userid;
		try {
			userid = super.getUser().getUserId();
			//按ID先获得实体的属性
			oldlevel = new Level();
			oldlevel = levelbiz.findLevelById(levelid);
			
			//根据传递过来的参数修改实体
			oldlevel.setCode(level.getCode());
			oldlevel.setName(level.getName());
			oldlevel.setUpdatedTime(new Date());
			oldlevel.setUpdaterId(userid);
			
			sameinfobool = levelbiz.modifyLevel(oldlevel);
		} catch (Exception e) {
			e.printStackTrace();
			updaterltbool=false;
		}
		return SUCCESS;
	}
	//--------------------------------------------get and set methods---------------------------------
	
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public int getLevelid() {
		return levelid;
	}
	public void setLevelid(int levelid) {
		this.levelid = levelid;
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
