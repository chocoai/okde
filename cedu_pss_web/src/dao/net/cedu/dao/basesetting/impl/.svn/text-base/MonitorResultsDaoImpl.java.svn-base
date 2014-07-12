package net.cedu.dao.basesetting.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.dao.basesetting.MonitorResultsDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.basesetting.MonitorResults;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 监控结果
 * @author HXJ
 *
 */
@Repository
public class MonitorResultsDaoImpl extends BaseMDDaoImpl<MonitorResults> implements MonitorResultsDao{

	public Map<String, String> findAllMap() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "select id,name from tb_e_monitor_results";
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = super.getJdbcTemplatePlus().query(sql,
				new RowMapper() {
					public Map<String,String> mapRow(ResultSet resultSet, int index) throws SQLException
					{
						Map<String,String> map =new HashMap<String,String>();
						map.put(resultSet.getInt("id")+"", resultSet.getString("name"));
						return map;
					}
		});
		if(list!=null){
			for (Map<String, String> map2 : list) {
				for(Map.Entry<String, String> entry:map2.entrySet()){
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return map;
	}

}
