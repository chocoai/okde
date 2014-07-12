package net.cedu.action.orgstructure.job;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.orgstructure.JobLevelBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.orgstructure.JobLevel;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 岗位
 * @author 谭必庆
 *
 */
public class IndexJobAction extends BaseAction{
	private static final long serialVersionUID = 6806905641865948436L;
	
	@Autowired
	private BranchBiz branchBiz;
	private List<Branch> blist=new ArrayList<Branch>();
	
	@Autowired
	private JobLevelBiz jobLevelBiz;
	private List<JobLevel> jllist=new ArrayList<JobLevel>();
	
	public String execute()
	{
		try
		{
			blist=branchBiz.findListById(getUser().getOrgId());
			Collections.sort(blist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
			jllist=jobLevelBiz.findAll();
			setIl8nName("admin");
			JobLevel jl=new JobLevel();
			jl.setId(-1);
			jl.setName(getText("select_default"));
			jllist.add(0,jl);
		}
		catch(Exception e)
		{e.printStackTrace();}
		return SUCCESS;
	}

	public List<Branch> getBlist() {
		return blist;
	}

	public List<JobLevel> getJllist() {
		return jllist;
	}
}
