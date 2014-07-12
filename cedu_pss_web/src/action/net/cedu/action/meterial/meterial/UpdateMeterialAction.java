package net.cedu.action.meterial.meterial;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialBiz;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.entity.meterial.Meterial;
import net.cedu.entity.meterial.MeterialCategory;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Json数据返回
 * @author YY
 *
 */ 
@ParentPackage("json-default")
public class UpdateMeterialAction extends BaseAction implements ModelDriven<Meterial>
{
	private static final long serialVersionUID = -9007096419088908044L;
	
	@Autowired
	private MeterialBiz meterialbiz; //物料业务层
	@Autowired
	private MeterialCategoryBiz meterialCategoryBiz; //物料分页业务层
	private Meterial meterial=new Meterial(); //物料实体
	private List<MeterialCategory> categoryList=new ArrayList<MeterialCategory>(); //物料分类集合
	private boolean results=false; //修改参数
	
	/**
	 * 获取物料设置集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_meterial", results = { @Result(name = "success", type = "redirect", location = "index_meterial") })
	public String execute() {
		if (isGetRequest()) {
			try {
				//查询全部物料分类集合
				categoryList=meterialCategoryBiz.findall();
				//查询需要修改的物料
				this.meterial = meterialbiz.findById(meterial.getId());
			} catch (Exception e) {
				e.getStackTrace();
			}
			return INPUT;
		}
		try {
			//更新物料
			meterial.setDeleteFlag(0);
			meterial.setCreatorId(this.getUser().getUserId());
			meterial.setUpdatedTime(new Date());
			meterial.setUpdaterId(this.getUser().getUserId());	
//			Meterial me=meterialbiz.findByName(meterial.getName());
//			{
			meterialbiz.updateMeterial(meterial);
//			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return SUCCESS;
	}

	public Meterial getModel() {
		
		return meterial;
	}

	public Meterial getMeterial() {
		return meterial;
	}

	public void setMeterial(Meterial meterial) {
		this.meterial = meterial;
	}

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}

	public List<MeterialCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<MeterialCategory> categoryList) {
		this.categoryList = categoryList;
	}
	 
	
}
