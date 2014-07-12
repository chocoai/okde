package net.cedu.action.finance.payacademycedu;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.entity.academy.Academy;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校返款单列表确认页面
 * 
 * @author xiao
 *
 */
public class ListPayAcademyCeduConfirmAction extends BaseAction
{
	private List<Academy> academies;
	
	@Autowired
	private AcademyBiz academyBiz;
	
	@Override
	public String execute() throws Exception
	{
		academies = academyBiz.findAllAcademyByFlagFalse();
		Collections.sort(academies, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Academy) arg0).getName();
				String name2 = ((Academy) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		return SUCCESS;
	}

	public List<Academy> getAcademies() {
		return academies;
	}
}
