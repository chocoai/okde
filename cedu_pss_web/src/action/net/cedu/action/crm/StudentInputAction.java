package net.cedu.action.crm;

import net.cedu.action.BaseAction;
import net.cedu.biz.code.BuildCodeBiz;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生录入（潜在学生）
 * 
 * @author yangdongdong
 * 
 */
public class StudentInputAction extends BaseAction {

	@Autowired
	private BuildCodeBiz BuildCodeBiz;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
//		System.out.println(BuildCodeBiz.getCodes("tb_p_e_user", "tb_p_e_user")+"-------------");
		return super.execute();
	}
}