package net.cedu.action.meterial.meterialcategory;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.meterial.biz.MeterialStoreroomBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.meterial.MeterialStoreroom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 库房设置查询全部
 * @author YY
 *
 */
public class IndexMeterialstoreroomAction extends BaseAction{
 
	private static final long serialVersionUID = -6905343407844316592L;

	@Autowired
	private MeterialStoreroomBiz meterialstoreroombiz; //库房实体
	
	private List<Branch> branchlst=new ArrayList<Branch>();	 //学习中心数组
	
	@Autowired
	private BranchBiz branchBiz;  //学习中心Biz 
	
	List<MeterialStoreroom> list= new ArrayList<MeterialStoreroom>(); //库房实体

	@Action(value="index_meterialstoreroom",results={@Result(name="success",location="index_meterialstoreroom.jsp")})
	public String execute(){
		
		try {
			//所有学习中心
			branchlst=branchBiz.findBranchForModel();
			//显示所有库房实体
			List<MeterialStoreroom> lists=meterialstoreroombiz.finMeterialStoreroomall();
			if(0<lists.size())
			{	
				//循环给库房名称赋值
				for (MeterialStoreroom Storeroom : lists) {
					 Branch br=branchBiz.findBranchById(Storeroom.getPosition());
					 if(null!=br)
					 {
						Storeroom.setPositionName(br.getName());
						list.add(Storeroom);
					 }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<MeterialStoreroom> getList() {
		return list;
	}

	public void setList(List<MeterialStoreroom> list) {
		this.list = list;
	}

	public List<Branch> getBranchlst() {
		return branchlst;
	}

	public void setBranchlst(List<Branch> branchlst) {
		this.branchlst = branchlst;
	}

 
	
	
}
