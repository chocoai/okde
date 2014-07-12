package net.cedu.action.basesetting.basedict;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.entity.basesetting.BaseDict;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewBaseDictAction extends BaseAction{

	private static final long serialVersionUID = -1379999308507414401L;

	@Autowired
	private BaseDictBiz basedicebiz;
	
	private int basedicttype;//类型
	private List<BaseDict> basedictlst;//基础字典列表
	private boolean lstrltbool=true;//判断页面加载列表是否正常
	
	/**
	 * 按type查询所有基础字典信息   BY  HXJ
	 */
	@Action(value="list_base_dict",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()throws Exception{
		try {
			basedictlst = basedicebiz.findAllBaseDictsByType(basedicttype);
		} catch (Exception e) {
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	
	/**
	 * 按type查询所有基础字典信息(delete_flag=0)   BY  HXJ
	 */
	@Action(value="list_base_dict_flag",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								"excludeProperties",
								"basedictlst.*.createdTime,"+
								"basedictlst.*.updatedTime"
								}			
				 		)
			})	
	public String showModeListByDeleteFlag()throws Exception{
		try {
			basedictlst = basedicebiz.findAllBaseDictsByTypeAndFlag(basedicttype);
		} catch (Exception e) {
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	
	//--------------------------------------------------------get and set methods----------------------------------------------------

	public List<BaseDict> getBasedictlst() {
		return basedictlst;
	}
	public void setBasedictlst(List<BaseDict> basedictlst) {
		this.basedictlst = basedictlst;
	}
	public boolean isLstrltbool() {
		return lstrltbool;
	}
	public void setLstrltbool(boolean lstrltbool) {
		this.lstrltbool = lstrltbool;
	}
	public int getBasedicttype() {
		return basedicttype;
	}
	public void setBasedicttype(int basedicttype) {
		this.basedicttype = basedicttype;
	}
}
