package net.cedu.action.meterial.meterial;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialBiz;
import net.cedu.entity.meterial.Meterial;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 物料分页
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonMeterialAction extends BaseAction implements ModelDriven<Meterial>  {
	private static final long serialVersionUID = 2418356563313648198L;

	@Autowired
	private MeterialBiz meterialbiz; //物料业务层

	PageResult<Meterial> result = new PageResult<Meterial>(); //分页参数

	private Meterial meterial = new Meterial();//物料实体

	private List<Meterial> list = new ArrayList<Meterial>();//物料集合

	/*
	 * 分页 （集合）(non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	//@SuppressWarnings("unchecked")
	@Action(value = "list_meterial", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String execute() throws Exception {

		try {
			list = meterialbiz.findMeterialPageListByCodeApplication(meterial,
					result);
 
			result.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/*
	 * 查询显示行数ajax方法
	 */
	@Action(value = "count_meterial", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String count() {
		try {
			result.setRecordCount(meterialbiz
					.findMeterialPageCountByCodeApplication(meterial, result));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PageResult<Meterial> getResult() {
		return result;
	}

	public void setResult(PageResult<Meterial> result) {
		this.result = result;
	}

	public Meterial getMeterial() {
		return meterial;
	}

	public void setMeterial(Meterial meterial) {
		this.meterial = meterial;
	}

	public List<Meterial> getList() {
		return list;
	}

	public void setList(List<Meterial> list) {
		this.list = list;
	}

	public Meterial getModel() {
		 
		return meterial;
	}

}
