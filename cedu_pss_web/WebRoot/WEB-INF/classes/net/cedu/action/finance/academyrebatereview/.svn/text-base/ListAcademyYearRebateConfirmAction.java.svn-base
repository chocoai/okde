package net.cedu.action.finance.academyrebatereview;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.GlobalEnrollBatch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校年度返款单列表确认页面
 * 
 * @author xiao
 *
 */
public class ListAcademyYearRebateConfirmAction extends BaseAction
{
	private List<Academy> academies;
	
	@Autowired
	private AcademyBiz academyBiz;
	
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;//全局批次_业务接口
	private List<GlobalEnrollBatch> geblist=new ArrayList<GlobalEnrollBatch>();//全局批次集合
	
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
		geblist=this.globalEnrollBatchBiz.findAllGlobalEnrollBatchYearsByDeleteFlag();
		return SUCCESS;
	}

	public List<Academy> getAcademies() {
		return academies;
	}

	public List<GlobalEnrollBatch> getGeblist() {
		return geblist;
	}

	public void setGeblist(List<GlobalEnrollBatch> geblist) {
		this.geblist = geblist;
	}
	
}
