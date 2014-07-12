package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.StudentDiscountDetailBiz;
import net.cedu.entity.enrollment.StudentDiscountDetail;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改学生优惠政策详细
 * @author lixiaojun
 *
 */
public class UpdateStudentDiscountDetailAction extends BaseAction
{
	
	@Autowired
	private StudentDiscountDetailBiz studentDiscountDetailBiz;//优惠政策业务接口
	private StudentDiscountDetail studentDiscountDetail=new StudentDiscountDetail();//优惠政策实体
	
	//跳转参数
	private int id;//优惠政策Id
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			if(id!=0)
			{
				studentDiscountDetail=this.studentDiscountDetailBiz.findStudentDiscountDetailDetailsById(id);			
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
