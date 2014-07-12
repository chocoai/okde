package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyBranchFeeWayBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.enrollment.AcademyBranchFeeWay;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({@Result(name="success", type="json"), @Result(name="error", type="json")})
public class ListBranchPaymentWayAction extends BaseAction
{
	private static final long serialVersionUID = -1849483635608672411L;
	
	private int academyId;
	private int batchId;
	
	private List<Map<String,Object>> branchPaymentWay = new LinkedList<Map<String,Object>>();
	
	@Autowired
	private BaseDictBiz baseDictBiz;
	@Autowired
	private AcademyBranchFeeWayBiz academyBranchFeeWayBiz;
	@Autowired
	private AcademyBatchBranchBiz academyBatchBranchBiz;

	@Override
	public String execute() throws Exception
	{
		// 取出授权中心列表
		List<Branch> abbList = academyBatchBranchBiz.findGrantedBranch(academyId, batchId);
		if(abbList!=null && abbList.size()>0)
		{
			Collections.sort(abbList, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		}
		
		if(abbList != null) //若授权中心列表不为空
		{
			// 遍历授权中心列表
			Iterator<Branch> iterBranch = abbList.iterator();
			while(iterBranch.hasNext())
			{
				Branch branch = iterBranch.next();
				//TODO delete 数据库有垃圾数据
				if(branch==null){
					System.err.println("空指针啦！");
					continue;
				}
				
				//查询出授权中心所对应缴费方式
				List<AcademyBranchFeeWay> abfwList = academyBranchFeeWayBiz.findByBatchAndBranch(batchId, branch.getId());
				List<BaseDict> payway = null;
				if(abfwList != null)
				{
					payway = new LinkedList<BaseDict>();
					
					Iterator<AcademyBranchFeeWay> abfwIter = abfwList.iterator();
					
					while(abfwIter.hasNext())
					{
						AcademyBranchFeeWay abfw = abfwIter.next();
						payway.add(baseDictBiz.findBaseDictById(abfw.getFeeWayId()));
					}
				}
				
				// 把授权学习中心与其对应的缴费方式封装在Map中
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("branch", branch);
				item.put("ways", payway);
				
				branchPaymentWay.add(item);
			}
			
//			baseDictBiz.findAllBaseDictsByTypeAndFlag(Constants.BASEDICT_STYLE_FEEWAY);
		}
		
		
		return SUCCESS;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<Map<String, Object>> getBranchPaymentWay() {
		return branchPaymentWay;
	}
	
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
}
