package net.cedu.action.finance.managerinvoice;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.finance.PostalParcelBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.finance.PostalParcel;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 邮寄单
 * 
 * @author gaolei
 * 
 */

public class ViewPostalParcelCeduAction extends BaseAction {

	
	@Autowired
	private PostalParcelBiz postalparcelbiz;              //邮寄单Biz
	@Autowired
	private BranchBiz branchbiz;                          //学习中心Biz
	private int id;                                       //主键Id
	private PostalParcel postalparcel;                    //邮寄单
	
	@Action(results = { @Result(name = "input", location = "view_post_parcel_cedu.jsp")
	})
	public String excute() throws Exception
	{
		//执行get请求
		if(super.isGetRequest())
		{
				postalparcel=postalparcelbiz.findPostalParcelById(id);
				if(postalparcel!=null)
				{
					Branch branch=branchbiz.findBranchById(postalparcel.getBranchId());
					if(branch!=null)
					{
						postalparcel.setBranchName(branch.getName());
					}
				}
				
				
				
				
				return INPUT;
		}
		return INPUT;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PostalParcel getPostalparcel() {
		return postalparcel;
	}

	public void setPostalparcel(PostalParcel postalparcel) {
		this.postalparcel = postalparcel;
	}

	
}
