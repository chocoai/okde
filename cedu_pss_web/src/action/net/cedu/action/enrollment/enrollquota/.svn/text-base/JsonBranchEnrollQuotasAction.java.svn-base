package net.cedu.action.enrollment.enrollquota;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollQuotaBiz;
import net.cedu.biz.enrollment.BranchEnrollQuotaBiz;
import net.cedu.biz.enrollment.EnrollQuotaBatchBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.enrollment.AcademyEnrollQuota;
import net.cedu.entity.enrollment.BranchEnrollQuota;
import net.cedu.entity.enrollment.EnrollQuotaBatch;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JSON 学习中心总指标
 * @author gaolei
 *
 */

@ParentPackage("json-default")
public class JsonBranchEnrollQuotasAction extends BaseAction{

	private static final long serialVersionUID = 7240008523958420022L;
	
	@Autowired
	private BranchEnrollQuotaBiz branchenrollquotaBiz;
	
	private PageResult<Branch> result = new PageResult<Branch>();
	private int batchId;   //批次Id
	
	
	
	/**
	 * 学习中心分配指标
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Action(value="listbranchquota",results={@Result(type="json", name = "success",
						params={"contentType","text/json"})})	
	public String ListBranchQuota() throws Exception {
		try {
			
			result.setList(branchenrollquotaBiz.findBranchByBtachId(batchId, result));
			Collections.sort(result.getList(), new Comparator() {
				public int compare(Object obj0, Object obj1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) obj0).getName();
					String name2 = ((Branch) obj1).getName();
					return cmp.compare(name1, name2);
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	
	
	
	
	
	public PageResult<Branch> getResult() {
		return result;
	}


	public void setResult(PageResult<Branch> result) {
		this.result = result;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	

	//--------------------------------------get and set methods-----------------------------------------

	
}
