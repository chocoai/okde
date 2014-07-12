package net.cedu.common.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

/**
 * 针对javax.servlet.jsp.jstl.sql.Result类型转换工具类
 * 
 * @author Jack
 * 
 */
public class ResultUtil {
	/**
	 * 将Result转换成List<Map>方式
	 * 
	 * @param result
	 * @return
	 */
	public static List<Map<String, String>> ConvertResultToMap(Result result) {
		List<Map<String, String>> lst = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		try {
			String s[] = result.getColumnNames();
			String key = "";
			String value = "";
			for (int i = 0; i < result.getRowCount(); i++) {
				map = new HashMap<String, String>();
				for (int j = 0; j < s.length; j++) {
					key = s[j];
					if (null != result.getRows()[i].get(s[j]))
						value = result.getRows()[i].get(s[j]).toString();
					else
						value = "";
					map.put(key, value);
				}
				lst.add(map);
			}
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
