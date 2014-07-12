package net.cedu.action.basesetting.level;

import java.util.List;
import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.entity.basesetting.Level;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewLevelAction extends BaseAction {

	private static final long serialVersionUID = 6651530149499942737L;

	@Autowired
	private LevelBiz levelbiz;
	
	private List<Level> levellst;

	private boolean lstrltbool=true;//判断页面加载列表是否正常

	/**
	 * 查询所有专业信息   BY  HXJ
	 */
	@Action(value="list_level",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()throws Exception{
		try {
			levellst = levelbiz.findAllLevels();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	
	/**
	 * 查询所有专业信息(delete_flag=0)   BY  HXJ
	 */
	@Action(value="list_level_by_deleteflag",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								"excludeProperties",
								"levellst.*.createdTime,"+
								"levellst.*.updatedTime"
								}			
				 		)
			})	
	public String showModeListByDeleteFlag()throws Exception{
		try {
			levellst = levelbiz.findAllLevelsByDeleteFlag();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	
	//------------------------------------------get and set methods-----------------------------------
	
	public List<Level> getLevellst() {
		return levellst;
	}

	public void setLevellst(List<Level> levellst) {
		this.levellst = levellst;
	}

	public boolean isLstrltbool() {
		return lstrltbool;
	}

	public void setLstrltbool(boolean lstrltbool) {
		this.lstrltbool = lstrltbool;
	}
	
}
