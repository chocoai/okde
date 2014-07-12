package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加学生优惠政策明细
 * @author lixiaojun
 *
 */
public class AddStudentDiscountDetailAction extends BaseAction
{
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//招生途径业务接口(合作方类型)
	private List<EnrollmentSource> channeltypelist=new ArrayList<EnrollmentSource>();//招生途径集合
	
	@Autowired
	private BaseDictBiz baseDictBiz;//数据字典_业务层接口
	private List<BaseDict> studslist=new ArrayList<BaseDict>(); //数据来源集合


	public String execute() throws Exception 
	{		
		if(super.isGetRequest())
		{	
			academylist=this.academyBiz.findAllAcademyByFlagFalse();
			if(academylist!=null && academylist.size()>0)
			{
				Collections.sort(academylist, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						Comparator cmp = Collator
								.getInstance(java.util.Locale.CHINA);
						String name1 = ((Academy) arg0).getName();
						String name2 = ((Academy) arg1).getName();
						return cmp.compare(name1, name2);
					}
				});
			}
			feesubjectlist=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
			channeltypelist=this.enrollmentSourceBiz.findAllEnrollmentSourceByDeleteFlag();
			studslist=this.baseDictBiz.findAllBaseDictsByType(Constants.BASEDICT_STYLE_STUDATASOURCE);
			return INPUT;
		}

		return SUCCESS;
	}


	public List<Academy> getAcademylist() {
		return academylist;
	}


	public void setAcademylist(List<Academy> academylist) {
		this.academylist = academylist;
	}


	public List<FeeSubject> getFeesubjectlist() {
		return feesubjectlist;
	}


	public void setFeesubjectlist(List<FeeSubject> feesubjectlist) {
		this.feesubjectlist = feesubjectlist;
	}


	public List<EnrollmentSource> getChanneltypelist() {
		return channeltypelist;
	}


	public void setChanneltypelist(List<EnrollmentSource> channeltypelist) {
		this.channeltypelist = channeltypelist;
	}


	public List<BaseDict> getStudslist() {
		return studslist;
	}


	public void setStudslist(List<BaseDict> studslist) {
		this.studslist = studslist;
	}	
	
}