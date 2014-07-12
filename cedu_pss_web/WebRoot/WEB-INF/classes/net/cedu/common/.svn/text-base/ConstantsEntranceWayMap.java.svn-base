package net.cedu.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 入学方式表
 * 
 * @author yangdongdong
 * 
 */
public class ConstantsEntranceWayMap {

private static Map<String, Integer> entranceWayMap=null;// 入学方式表
	
	public static Integer getCode(String name){
		if(entranceWayMap==null){
			init();
		}
		return entranceWayMap.get(name)==null?0:entranceWayMap.get(name);
	}
	
	public static void init(){
		if(entranceWayMap==null){
			entranceWayMap = new HashMap<String, Integer>();
			entranceWayMap.put("免试生", 1);
			entranceWayMap.put("测试生", 2);
			entranceWayMap.put("课程进修生", 3);

		}
	}
}
