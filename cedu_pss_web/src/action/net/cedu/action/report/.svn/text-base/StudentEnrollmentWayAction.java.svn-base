/**
 * 文件名：StudentEnrollmentWayAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-26 下午04:08:35
 *
 */
package net.cedu.action.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.PingYingHanZiUtil;
import net.cedu.entity.basesetting.BaseDict;

import org.springframework.beans.factory.annotation.Autowired;

public class StudentEnrollmentWayAction extends BaseAction {
	@Autowired
	private BaseDictBiz baseDictBiz;

	private List<BaseDict> studentEnrollmentWayList = new LinkedList<BaseDict>();

	@Override
	public String execute() throws Exception {
		LinkedHashMap<String,List<BaseDict>> enrollmentWaysMap=new LinkedHashMap<String,List<BaseDict>>();
		
		LinkedHashMap<String,List<BaseDict>> enrollmentWaysMap_=new LinkedHashMap<String,List<BaseDict>>();
		
		LinkedHashMap<BaseDict, List<BaseDict>> map = new LinkedHashMap<BaseDict, List<BaseDict>>();

		List<BaseDict> enrollmentWays = baseDictBiz.findBaseDictsByType(Constants.BASEDICT_STYLE_ENROLLMENTWAY);

		for (int i = 0; i < enrollmentWays.size(); i++) {
			BaseDict baseDict = enrollmentWays.get(i);
			if (baseDict.getParentNode() == 0) {
				map.put(baseDict, new ArrayList<BaseDict>());
			}
		}
		Set<BaseDict> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			BaseDict baseDictKey = (BaseDict) it.next();
			List<BaseDict> baseDictList = map.get(baseDictKey);
			for (int i = 0; i < enrollmentWays.size(); i++) {
				BaseDict baseDict = enrollmentWays.get(i);
				if (baseDict.getParentNode() == baseDictKey.getId()) {
					baseDictList.add(baseDict);
				}
			}
		}
		Set<BaseDict> key1 = map.keySet();
		for (Iterator it = key1.iterator(); it.hasNext();) {
			BaseDict baseDictKey = (BaseDict) it.next();
			List<BaseDict> baseDictList = map.get(baseDictKey);
			if (baseDictList.size() == 0) {
				baseDictList.add(baseDictKey);
			}
			enrollmentWaysMap.put(baseDictKey.getName(), baseDictList);

			//市场途径名称转换拼音
			List<BaseDict> baseDictList_=new ArrayList<BaseDict>();
			if(baseDictList!=null){
				BaseDict baseDict_=null;
				for (BaseDict baseDict : baseDictList) {
					baseDict_=new BaseDict();
					baseDict_.setName(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName()));
					baseDictList_.add(baseDict_);
				}
			}
			enrollmentWaysMap_.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDictKey.getName()), baseDictList_);
		}
		request.setAttribute("enrollmentWaysMap", enrollmentWaysMap);
		request.setAttribute("enrollmentWaysMapPingYin", enrollmentWaysMap_);
		return SUCCESS;
	}

	public List<BaseDict> getStudentEnrollmentWayList() {
		return studentEnrollmentWayList;
	}

	public void setStudentEnrollmentWayList(
			List<BaseDict> studentEnrollmentWayList) {
		this.studentEnrollmentWayList = studentEnrollmentWayList;
	}

}
