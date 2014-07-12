package net.cedu.action.meterial.meterialcategory;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialStoreroomBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 删除库房设置
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonDeleteMeterialStoreroomAction extends BaseAction {
 
 
	private static final long serialVersionUID = -5981336975418829377L;
	@Autowired
	private MeterialStoreroomBiz meterialStoreroomBiz; //库房设置业务层
	private int id; //库房设置id
	private boolean results; //判断参数
	
	@Action(value="delete_meterialstoreroom",results={@Result(
			name="success",type="json",params={
					"contentType","text/json","includeProperties","results"
			})})
	public String execute()
	{
		try {
			//删除ajax方法
			meterialStoreroomBiz.deleteMeterialStoreroom(id);
			results=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}




	
	
}
