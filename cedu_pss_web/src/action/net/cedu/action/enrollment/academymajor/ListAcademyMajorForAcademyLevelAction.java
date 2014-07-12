package net.cedu.action.enrollment.academymajor;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.Major;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询院校层次下所有专业
 */
@ParentPackage("json-default")
@Results({@Result(name="success", type="json"), @Result(name="success", type="json")})
public class ListAcademyMajorForAcademyLevelAction extends BaseAction
{
	private static final long serialVersionUID = -116926300887038333L;

	private int academyLevelId;
	
	private List<Major> list;
	
	@Autowired
	private AcademyMajorBiz academyMajorBiz;
//	@Autowired
//	private AcademyLevelBiz academyLevelBiz;
	@Autowired
	private MajorBiz majorBiz;
	
	@Override
	public String execute() throws Exception
	{
//		AcademyLevel level = academyLevelBiz.findById(academyLevelId);
//		
//		if(level == null) return ERROR;
		
		List<AcademyMajor> amList = academyMajorBiz.findByLevel(academyLevelId);
		if(amList != null)
		{
			list = new LinkedList<Major>();
			Iterator<AcademyMajor> iter = amList.iterator();
			while(iter.hasNext())
			{
				AcademyMajor am = iter.next();
				Major major = majorBiz.findMajorById(am.getMajorId());
				list.add(major);
				Collections.sort(list, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						Comparator cmp = Collator
								.getInstance(java.util.Locale.CHINA);
						String name1 = ((Major) arg0).getName();
						String name2 = ((Major) arg1).getName();
						return cmp.compare(name1, name2);
					}
				});
			}
		}
		
		return SUCCESS;
	}

	public List<Major> getList() {
		return list;
	}

	public void setAcademyLevelId(int academyLevelId) {
		this.academyLevelId = academyLevelId;
	}
}
