package net.cedu.action.basesetting.basedict;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.entity.basesetting.BaseDict;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonDeleteBaseDictAction extends BaseAction{

	private static final long serialVersionUID = -2974486090370965831L;

	@Autowired
	private BaseDictBiz basedicebiz;
	
	private int basedictid;//基础字典ID
	
	private boolean delrltbool=false;//判断删除操作是否成功

	/**
	 * 按id删除基础字典(物理删除)
	 * @returndelete_base_dict
	 */
	@Action(value="delete_base_dict",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		BaseDict basedict = new BaseDict();
		try{
			basedict = basedicebiz.deleteConfigBaseDict(basedictid);
		}catch (Exception e) {
			e.printStackTrace();
			delrltbool=false;
		}
		if(basedict!=null){
			delrltbool=true;
		}
		return SUCCESS;
	}
	


//	/**
//	 * 按id删除基础字典(逻辑删除)
//	 * @return
//	 */
//	@Action(value="delete_base_dict_status",
//			results={
//				@Result(type="json", name = "success",
//						params={"contentType","text/json"}
//					   )
//			})	
//	public String deleteModeByStatus(){
//		int num=0;
//		try {
//			num=basedicebiz.deleteBaseDictByFlag(basedictid);
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
	//-------------------------------------------------GET AND SET methods-------------------------------------

	public int getBasedictid() {
		return basedictid;
	}

	public void setBasedictid(int basedictid) {
		this.basedictid = basedictid;
	}

	public boolean isDelrltbool() {
		return delrltbool;
	}

	public void setDelrltbool(boolean delrltbool) {
		this.delrltbool = delrltbool;
	}
	
	
}
