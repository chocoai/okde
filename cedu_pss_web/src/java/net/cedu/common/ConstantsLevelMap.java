package net.cedu.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.entity.basesetting.Level;

/**
 * 层次代码表
 * 
 * @author yangdongdong
 * 
 */
public class ConstantsLevelMap {

private static Map<String, Integer> levelCodeMap=null;
	
	public static Integer getCode(String name){
		if(levelCodeMap==null)
			return 0;
		return levelCodeMap.get(name)==null?0:levelCodeMap.get(name);
	}
	
	public static void init(){
		if(levelCodeMap==null){
			levelCodeMap = new HashMap<String, Integer>();
			
			List<Level> levelList=new ArrayList<Level>();
			for (Level level : levelList) {
				if(level!=null){
					levelCodeMap.put(level.getName(), level.getId());
				}
			}

		}
	}
}
