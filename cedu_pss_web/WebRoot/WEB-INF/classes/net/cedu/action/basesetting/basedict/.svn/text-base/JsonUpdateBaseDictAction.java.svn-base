package net.cedu.action.basesetting.basedict;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.entity.basesetting.BaseDict;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonUpdateBaseDictAction extends BaseAction{

	private static final long serialVersionUID = 1853502157455275187L;
	
	@Autowired
	private BaseDictBiz basedictbiz;
	
	private BaseDict basedict;//基础字典实体
	private int basedictid;//基础字典ID
	private boolean updaterltbool=true;//判断修改操作是否成功
	private boolean sameinfobool=true;
	
	/**
	 * 修改基础字典信息  BY HXJ
	 */
	@Action(value="update_base_dict",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
	
		BaseDict oldbasedict = null;
		
		try {
			oldbasedict = new BaseDict();
			int userid = super.getUser().getUserId();
			//按ID先获得实体的属性
			oldbasedict = basedictbiz.findBaseDictById(basedictid);
			//根据传递过来的参数修改实体
			oldbasedict.setName(basedict.getName());
			oldbasedict.setOrderNumber(basedict.getOrderNumber());
			oldbasedict.setUpdatedTime(new Date());
			oldbasedict.setUpdaterId(userid);
			sameinfobool = basedictbiz.modifyBaseDict(oldbasedict);
		} catch (Exception e) {
			e.printStackTrace();
			updaterltbool = false;
		}
		
		return SUCCESS;
	}
	//----------------------------------------------------get and set methods---------------------------------------------------
	public BaseDict getBasedict() {
		return basedict;
	}
	public void setBasedict(BaseDict basedict) {
		this.basedict = basedict;
	}
	public int getBasedictid() {
		return basedictid;
	}
	public void setBasedictid(int basedictid) {
		this.basedictid = basedictid;
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
