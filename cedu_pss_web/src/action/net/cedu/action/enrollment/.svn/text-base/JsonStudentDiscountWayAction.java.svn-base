package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.EnrollmentSource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 市场途径，招生途径
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonStudentDiscountWayAction extends BaseAction
{
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz; //招生途径 _业务层接口
	@Autowired
	private BaseDictBiz baseDictBiz; //数据字典_业务层接口
	// 招生途径
	private List<EnrollmentSource> enrollmentSources = new ArrayList<EnrollmentSource>();
	// 市场途径字典
	private LinkedHashMap<String,List<BaseDict>> enrollmentWaysMap=new LinkedHashMap<String,List<BaseDict>>();
	
	
	/**
	 * 市场途径，招生途径
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "student_discount_enrollment_way_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentWayList() throws Exception 
	{
		enrollmentSources = enrollmentSourceBiz.findAllEnrollmentSources();
		Collections.sort(enrollmentSources, new Comparator()
		{
			public int compare(Object arg0, Object arg1)
			{
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((EnrollmentSource) arg0).getName();
				String name2 = ((EnrollmentSource) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		
	    LinkedHashMap<BaseDict,List<BaseDict>> map=new LinkedHashMap<BaseDict,List<BaseDict>>();
		
		List<BaseDict> enrollmentWays = baseDictBiz.findBaseDictsByType(Constants.BASEDICT_STYLE_ENROLLMENTWAY);
		
		for (int i = 0; i < enrollmentWays.size(); i++)
		{
			BaseDict baseDict=enrollmentWays.get(i);
			if(baseDict.getParentNode()==0)
			{
				map.put(baseDict, new ArrayList<BaseDict>());
			}
		}
		Set<BaseDict> key = map.keySet();
        for (Iterator it = key.iterator(); it.hasNext();)
        {
        	BaseDict baseDictKey = (BaseDict) it.next();
        	List<BaseDict> baseDictList=map.get(baseDictKey);
        	for (int i = 0; i < enrollmentWays.size(); i++) 
        	{
    			BaseDict baseDict=enrollmentWays.get(i);
    			if(baseDict.getParentNode()==baseDictKey.getId())
    			{
    				baseDictList.add(baseDict);
    			}
    		}
        	
        }
        Set<BaseDict> key1 = map.keySet();
        for (Iterator it = key1.iterator(); it.hasNext();)
        {
        	BaseDict baseDictKey = (BaseDict) it.next();
        	List<BaseDict> baseDictList=map.get(baseDictKey);
        	if(baseDictList.size()==0)
        	{
        		baseDictList.add(baseDictKey);
        	}
        	enrollmentWaysMap.put(baseDictKey.getName(), baseDictList);
        }
		
//		Collections.sort(enrollmentWays, new Comparator() {
//			public int compare(Object arg0, Object arg1) {
//				Comparator cmp = Collator
//						.getInstance(java.util.Locale.CHINA);
//				String name1 = ((BaseDict) arg0).getName();
//				String name2 = ((BaseDict) arg1).getName();
//				return cmp.compare(name1, name2);
//			}
//		});
		return SUCCESS;
	}


	public List<EnrollmentSource> getEnrollmentSources() {
		return enrollmentSources;
	}


	public void setEnrollmentSources(List<EnrollmentSource> enrollmentSources) {
		this.enrollmentSources = enrollmentSources;
	}


	public LinkedHashMap<String, List<BaseDict>> getEnrollmentWaysMap() {
		return enrollmentWaysMap;
	}


	public void setEnrollmentWaysMap(
			LinkedHashMap<String, List<BaseDict>> enrollmentWaysMap) {
		this.enrollmentWaysMap = enrollmentWaysMap;
	}
	
}
