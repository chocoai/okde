package net.cedu.action.enrollment.academylevel;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({@Result(name="success", type="json"),@Result(name="error", type="json")})
public class ListAcademyLevelForBatchAction extends BaseAction
{
	/**
	 * @date 2011-08-09 12:39
	 */
	private static final long serialVersionUID = 7845611050411353890L;

	private int batchId;
	
	private List<AcademyLevel> list;
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;

	@Override
	public String execute() throws Exception
	{
		AcademyEnrollBatch batch = academyEnrollBatchBiz.findAcademyEnrollBatchById(batchId);
		
		if(batch == null) return ERROR;
		
		list = new LinkedList<AcademyLevel>();
		
		List<AcademyLevel> alList = academyLevelBiz.findBatchId(batchId);
		if(alList != null)
		{
			Iterator<AcademyLevel> iter = alList.iterator();
			AcademyLevel al = null;
			Level level = null;
			while(iter.hasNext())
			{
				al = iter.next();
				level = levelBiz.findLevelById(al.getLevelId());
				al.setLevel(level);
				list.add(al);
			}
		 
		}
		
		
		return SUCCESS;
	}

	public List<AcademyLevel> getList() {
		return list;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
}
