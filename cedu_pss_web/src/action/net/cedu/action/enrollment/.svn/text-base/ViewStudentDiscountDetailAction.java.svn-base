package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.StudentDiscountDetailBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.enrollment.StudentDiscountDetail;
import net.cedu.entity.enrollment.StudentDiscountPolicy;

/**
 * 学生优惠政策详细
 * @author lixiaojun
 *
 */
public class ViewStudentDiscountDetailAction extends BaseAction
{
	
	@Autowired
	private StudentDiscountDetailBiz studentDiscountDetailBiz;//优惠政策业务接口
	private StudentDiscountDetail studentDiscountDetail=new StudentDiscountDetail();//优惠政策实体
	
	@Autowired
	private StudentDiscountPolicyBiz studentDiscountpolicyBiz;//优惠标准业务接口
	private List<StudentDiscountPolicy> discountPolicyList=new ArrayList<StudentDiscountPolicy>();//优惠标准实体集合
	
	
	//跳转参数
	private int id;//优惠政策Id
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			if(id!=0)
			{
				studentDiscountDetail=this.studentDiscountDetailBiz.findStudentDiscountDetailDetailsById(id);
				String[] ids=studentDiscountDetail.getDiscountPolicyId().split(",");
				for(int i=0;i<ids.length;i++)
				{
					StudentDiscountPolicy sdp=this.studentDiscountpolicyBiz.findStudentDiscountPolicyDetailsById(Integer.valueOf(ids[i]));
					if(sdp!=null)
					{
						sdp.setTargetObjectName(ResourcesTool.getText("enrollment","policy.target.object."+sdp.getTargetObjectId()));
						discountPolicyList.add(sdp);
					}
				}
			}
			return INPUT;
		}
		return SUCCESS;
	}

	public StudentDiscountDetail getStudentDiscountDetail() {
		return studentDiscountDetail;
	}

	public void setStudentDiscountDetail(StudentDiscountDetail studentDiscountDetail) {
		this.studentDiscountDetail = studentDiscountDetail;
	}

	public List<StudentDiscountPolicy> getDiscountPolicyList() {
		return discountPolicyList;
	}

	public void setDiscountPolicyList(List<StudentDiscountPolicy> discountPolicyList) {
		this.discountPolicyList = discountPolicyList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
