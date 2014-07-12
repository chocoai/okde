package net.cedu.action.basesetting.level;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.entity.basesetting.Level;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonDeleteLevelAction extends BaseAction{

	private static final long serialVersionUID = -4676309891434157849L;

	@Autowired
	private LevelBiz levelbiz;
	
	private int levelid;
	
	private boolean delrltbool=false;//判断删除操作是否成功
	
	/**
	 * 按id删除层次(物理删除)
	 * @return
	 */
	@Action(value="delete_level",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		Level level=new Level();
		try{
			level = levelbiz.deleteConfigLevel(levelid);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			delrltbool=false;
		}
		if(level!=null&&!("").equals(level)){
			delrltbool=true;
		}
		return SUCCESS;
	}
	


//	/**
//	 * 按id删除层次(逻辑删除)
//	 * @return
//	 */
//	@Action(value="delete_level_status",
//			results={
//				@Result(type="json", name = "success",
//						params={"contentType","text/json"}
//					   )
//			})	
//	public String deleteModeByStatus(){
//		int num=0;
//		try {
//			num = levelbiz.deleteLevelByFlag(levelid);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			delrltbool=false;
//		}
//		
//		//判断是否修改成功
//		if(num>0){
//			delrltbool=true;
//		}
//		return SUCCESS;
//	}
//	
	//-----------------------------------------get and set methods---------------------------------

	public int getLevelid() {
		return levelid;
	}

	public void setLevelid(int levelid) {
		this.levelid = levelid;
	}

	public boolean isDelrltbool() {
		return delrltbool;
	}

	public void setDelrltbool(boolean delrltbool) {
		this.delrltbool = delrltbool;
	}
	
	
}
