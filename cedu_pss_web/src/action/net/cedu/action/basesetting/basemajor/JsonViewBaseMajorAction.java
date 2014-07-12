package net.cedu.action.basesetting.basemajor;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseMajorBiz;
import net.cedu.entity.basesetting.BaseMajor;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewBaseMajorAction extends BaseAction {

	private static final long serialVersionUID = -4533334834863026377L;

	@Autowired
	private BaseMajorBiz baseMajorBiz;

	private List<BaseMajor> basemajorlist;// 专业列表
	private boolean lstrltbool = true;// 判断页面加载列表是否正常

	/**
	 * 查询所有专业信息 BY HXJ
	 */
	@Action(value = "list_base_major", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String execute() throws Exception {
		try {
			basemajorlist = baseMajorBiz.findAllBaseMajors();
		} catch (Exception e) {

			e.printStackTrace();
			lstrltbool = false;
		}

		return "success";
	}

	/**
	 * 查询所有专业信息(delete_flag=0) BY HXJ
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "list_base_major_by_flag", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json", "excludeProperties",
			"basemajorlist.*.createdTime," + "basemajorlist.*.updatedTime" }) })
	public String showModeListByDeleteFlag() throws Exception {
		try {
			basemajorlist = baseMajorBiz.findBaseMajorByFlag();
			Collections.sort(basemajorlist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((BaseMajor) arg0).getName();
					String name2 = ((BaseMajor) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		} catch (Exception e) {

			e.printStackTrace();
			lstrltbool = false;
		}

		return "success";
	}

	// ------------------------------------------get and set
	// methods-----------------------------------
	public List<BaseMajor> getBasemajorlist() {
		return basemajorlist;
	}

	public void setBasemajorlist(List<BaseMajor> basemajorlist) {
		this.basemajorlist = basemajorlist;
	}

	public boolean isLstrltbool() {
		return lstrltbool;
	}

	public void setLstrltbool(boolean lstrltbool) {
		this.lstrltbool = lstrltbool;
	}

}
