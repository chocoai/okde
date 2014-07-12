package net.cedu.action.academy;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.entity.academy.Academy;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院历--查看
 * 
 * @author yangdongdong
 * 
 */
public class SchoolCalendarViewHomeAction extends BaseAction {

	@Autowired
	private AcademyBiz academybiz; // 院校Biz

	private List<Academy> academys = new ArrayList<Academy>();
	// 院校ID
	private int id;

	private Academy academy;

	@Override
	public String execute() throws Exception {
		// 查询所有院校
		academys = academybiz.findAllAcademyByFlagFalse();
		Collections.sort(academys, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Academy) arg0).getName();
				String name2 = ((Academy) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		academy = academybiz.findAcademyById(id);
		return SUCCESS;
	}

	public List<Academy> getAcademys() {

		return academys;
	}

	public void setAcademys(List<Academy> academys) {
		this.academys = academys;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

}
