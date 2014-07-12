package net.cedu.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.entity.basesetting.BaseMajor;

/**
 * 专业代码表
 * 
 * @author yangdongdong
 * 
 */
public class ConstantsMajorMap {

private static Map<String, Integer> majorCodeMap=null;
	
	public static Integer getCode(String name){
		if(majorCodeMap==null)
			return 0;
		return majorCodeMap.get(name)==null?0:majorCodeMap.get(name);
	}
	
	public static void init(){
		if(majorCodeMap==null){
			majorCodeMap = new HashMap<String, Integer>();
			
			List<BaseMajor> majorList=new ArrayList<BaseMajor>();
			for (BaseMajor major : majorList) {
				if(major!=null){
					majorCodeMap.put(major.getName(), major.getId());
				}
			}

		}
	}
}
