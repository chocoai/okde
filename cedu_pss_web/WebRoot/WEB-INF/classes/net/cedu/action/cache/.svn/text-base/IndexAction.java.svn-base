package net.cedu.action.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.common.properties.Config;
import net.cedu.common.reflection.ClassUtil;
import net.cedu.model.cache.CacheEntity;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.devsun.cache.Cache;
import com.devsun.cache.entry.CacheDetails;

/**
 * 缓存页
 * 
 * @author yangdongdong
 * 
 */
@Results({ @Result(name = "success", location = "/WEB-INF/content/cache/index.jsp") })
public class IndexAction extends BaseAction {

	@Autowired
	private Cache cacheClient;

	private String searchEntry;

	// 操作类型
	private String type;
	// 键
	private String key;
	// 缓存实体
	private List<CacheDetails> cacheDetails = new ArrayList<CacheDetails>();
	// select下拉选择
	private List<CacheEntity> cacheEntitys = new ArrayList<CacheEntity>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Action(value = "index")
	public String index() {
		List<String> clss = ClassUtil.getClassNames("net.cedu.entity");
		CacheEntity c = null;
		for (String s : clss) {
			c = new CacheEntity();
			c.setClassName(s);
			c.setClassTextName(ClassUtil.getTable(s));
			cacheEntitys.add(c);
		}
		Collections.sort(cacheEntitys, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				String ce1 = ((CacheEntity) arg0).getClassTextName();
				String ce2 = ((CacheEntity) arg1).getClassTextName();
				if (ce1.startsWith("tb_e_") || ce2.startsWith("tb_e_")) {
					ce1 = ce1.replaceAll("tb_e_", "");
					ce2 = ce2.replaceAll("tb_e_", "");
				} else if (ce1.startsWith("tb_p_e_")
						|| ce2.startsWith("tb_p_e_")) {
					ce1 = ce1.replaceAll("tb_p_e_", "");
					ce2 = ce2.replaceAll("tb_p_e_", "");
				} else if (ce1.startsWith("tb_p_r_")
						|| ce2.startsWith("tb_p_r_")) {
					ce1 = ce1.replaceAll("tb_p_r_", "");
					ce2 = ce2.replaceAll("tb_p_r_", "");
				} else if (ce1.startsWith("tb_r_") || ce2.startsWith("tb_r_")) {
					ce1 = ce1.replaceAll("tb_r_", "");
					ce2 = ce2.replaceAll("tb_r_", "");
				}
				if ((ce1!=null&&ce2!=null&&ce1.toCharArray().length!=0&&ce2.toCharArray().length!=0)&&(ce1.toCharArray()[0] > ce2.toCharArray()[0])) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		try {
			// 是否启用缓存
			if (Config.getBoolProperty("use.cache")) {
				if (type != null) {
					// 删除单条
					if (type.equals("delete")) {
						cacheClient.deleteObject(key);
					}

					if (type.equals("p_delete")) {
						// 批量删除
						cacheClient.deleteCacheByKey(searchEntry);
					}
					if (type.equals("p_clear")) {
						// 清空
						// cacheClient.clear();
						for (String s : clss) {
							cacheClient.deleteCacheByKey(s);
						}

						cacheClient.clear();
					}
				}
				// 查询
				if (searchEntry != null && !searchEntry.equals("")) {
					cacheDetails = cacheClient.findCacheByKey(searchEntry);
					if (cacheDetails != null && cacheDetails.size() != 0) {
						Collections.sort(cacheDetails, new SortCacheDetails());
					}

				}
			} else {
				request.setAttribute("message",
						this.getText("cache.error.message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", this.getText("cache.error.message"));
		}

		return SUCCESS;
	}

	public List<CacheDetails> getCacheDetails() {
		return cacheDetails;
	}

	public void setCacheDetails(List<CacheDetails> cacheDetails) {
		this.cacheDetails = cacheDetails;
	}

	public String getSearchEntry() {
		return searchEntry;
	}

	public void setSearchEntry(String searchEntry) {
		this.searchEntry = searchEntry;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<CacheEntity> getCacheEntitys() {
		return cacheEntitys;
	}

	public void setCacheEntitys(List<CacheEntity> cacheEntitys) {
		this.cacheEntitys = cacheEntitys;
	}

}

/**
 * 排序内部类
 * 
 * @author yangdongdong
 * 
 */
class SortCacheDetails implements Comparator {
	public int compare(Object obj1, Object obj2) {
		CacheDetails c1 = (CacheDetails) obj1;
		CacheDetails c2 = (CacheDetails) obj2;
		if (((int) Integer.parseInt(c1.getKey().substring(
				c1.getKey().indexOf("_") + 1))) > ((int) Integer.parseInt(c2
				.getKey().substring(c2.getKey().indexOf("_") + 1))))
			return 1;
		else
			return 0;
	}
}
