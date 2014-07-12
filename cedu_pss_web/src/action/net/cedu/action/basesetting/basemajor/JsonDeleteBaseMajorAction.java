package net.cedu.action.basesetting.basemajor;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseMajorBiz;
import net.cedu.entity.basesetting.BaseMajor;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonDeleteBaseMajorAction extends BaseAction{

	private static final long serialVersionUID = -2992542137611469508L;

	@Autowired
	private BaseMajorBiz baseMajorBiz;
	
	private int baseMajorId;//需要删除专业的ID
	private boolean delrltbool=false;//判断删除操作是否成功
	
	/**
	 * 删除专业(物理删除)
	 */
	@Action(value="delete_base_major",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute (){
		BaseMajor BaseMajor=new BaseMajor();
		try{
			BaseMajor = baseMajorBiz.deleteConfigBaseMajor(baseMajorId);
		}catch (Exception e) {
			e.printStackTrace();
			delrltbool=false;
		}
		if(BaseMajor!=null&&!("").equals(BaseMajor)){
			delrltbool=true;
		}
		return SUCCESS;
	}
	
//	/**
//	 * 删除专业(逻辑删除)
//	 */
//	@Action(value="delete_base_major_by_flag",
//			results={
//				@Result(type="json", name = "success",
//						params={"contentType","text/json"}
//					   )
//			})	
//	public String deleteModeByStatus(){
//		int num=0;
//		try {
//			num = baseMajorBiz.deleteBaseMajorByflag(baseMajorId);
//		} catch (Exception e) {
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
	//----------------------------------------get and set methods------------------------------
	public int getBaseMajorId() {
		return baseMajorId;
	}
	public void setBaseMajorId(int baseMajorId) {
		this.baseMajorId = baseMajorId;
	}
	public boolean isDelrltbool() {
		return delrltbool;
	}
	public void setDelrltbool(boolean delrltbool) {
		this.delrltbool = delrltbool;
	}
	
	
}
