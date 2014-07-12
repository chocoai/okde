package net.cedu.action.enrollment;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.entity.academy.Academy;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款政策
 * 
 * @author Sauntor
 *
 */
public class ViewPolicyChannelAction extends BaseAction
{
	private static final long serialVersionUID = -4130535704428454124L;

	private List<Academy> academys;
	
	@Autowired
	private AcademyBiz academyBiz;
	
	@Override
	public String execute() throws Exception
	{
		academys = academyBiz.findAllAcademyByFlagFalse();
		
		return SUCCESS;
	}

	public List<Academy> getAcademys() {
		return academys;
	}
}
