package net.cedu.biz.basesetting.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.BranchEnrollmentWayBiz;
import net.cedu.common.Constants;
import net.cedu.dao.basesetting.BaseDictDao;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.BranchEnrollmentWay;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基础字典
 * 
 * @author HXJ
 */
@Service
public class BaseDictBizImpl implements BaseDictBiz {

	@Autowired
	private BaseDictDao basedictdao;
	@Autowired
	private BranchEnrollmentWayBiz branchEnrollmentWayBiz;

	/*
	 * 增加基础字典 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.basesetting.BaseDictBiz#addBaseDict(net.cedu.entity.basesetting
	 * .BaseDict)
	 */
	public boolean addBaseDict(BaseDict basedict) throws Exception {

		if (0 >= this.findTotalByProperty(basedict)) {
			basedictdao.save(basedict);
			return true;
		}
		return false;
	}

	/*
	 * 修改基础字典 (non-Javadoc)
	 * 
	 * @seenet.cedu.biz.basesetting.BaseDictBiz#modifyBaseDict(net.cedu.entity.
	 * basesetting.BaseDict)
	 */
	public boolean modifyBaseDict(BaseDict basedict) throws Exception {

		if (0 >= findTotalByProperty(basedict)) {
			basedictdao.update(basedict);
			return true;
		}
		return false;
	}

	// /*按主键删除(物理删除)
	// * (non-Javadoc)
	// * @see
	// net.cedu.biz.basesetting.BaseDictBiz#deleteBaseDictById(java.io.Serializable)
	// */
	// public BaseDict deleteBaseDictById(Serializable id) {
	//	
	// return basedictdao.deleteById(id);
	// }
	//
	// /*按主键删除(逻辑删除)
	// * (non-Javadoc)
	// * @see net.cedu.biz.basesetting.BaseDictBiz#deleteBaseDictByFlag(int)
	// */
	// public int deleteBaseDictByFlag(int id) {
	//	
	// return basedictdao.deleteByFlag(id);
	// }

	/*
	 * 按type查询基础字典列表(delete_flag=0) (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.basesetting.BaseDictBiz#findAllBaseDictsByTypeAndFlag(int)
	 */
	public List<BaseDict> findAllBaseDictsByTypeAndFlag(int type)
			throws Exception {

		String sql = " and type=" + Constants.PLACEHOLDER + " and parentNode="
				+ Constants.PLACEHOLDER + " and deleteFlag="
				+ Constants.PLACEHOLDER + " order by orderNumber";
		List<BaseDict> bdlist = basedictdao.getByProperty(sql, new Object[] {
				type, 0, Constants.DELETE_FALSE });
		List<BaseDict> bdlst = null;
		if (bdlist != null && bdlist.size() > 0) {
			for (int i = 0; i < bdlist.size(); i++) {
				bdlst = new ArrayList<BaseDict>();
				bdlst = findBaseDictsByTypeAndParentNodeAndDeleteFlag(
						Constants.BASEDICT_STYLE_ENROLLMENTWAY, bdlist.get(i)
								.getId());
				if (bdlst != null && bdlst.size() > 0) {
					bdlist.get(i).setBaseDictList(bdlst);
				}
			}
		}

		return bdlist;
	}

	/*
	 * 按type查询基础字典列表 (non-Javadoc)
	 * 
	 * @see net.cedu.biz.basesetting.BaseDictBiz#findAllBaseDictsByType(int)
	 */
	public List<BaseDict> findAllBaseDictsByType(int type) throws Exception {

		return basedictdao.getByProperty("type", type);
	}

	/*
	 * 按主键id查询基础字典 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.basesetting.BaseDictBiz#findBaseDictById(java.io.Serializable
	 * )
	 */
	public BaseDict findBaseDictById(Serializable id) {

		return basedictdao.findById(id);
	}

	/*
	 * 查询数据中是否有重复的数据 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.basesetting.BaseDictBiz#findTotalByProperty(net.cedu.entity
	 * .basesetting.BaseDict)
	 */
	@SuppressWarnings("unchecked")
	private int findTotalByProperty(BaseDict baseDict) throws Exception {
		String hql = "";
		List objs = new ArrayList();

		if (baseDict.getId() > 0) {
			hql += " and id <> " + Constants.PLACEHOLDER;
			objs.add(baseDict.getId());
		}

		if (StringUtils.isNotBlank(baseDict.getName())) {
			hql += " and name = " + Constants.PLACEHOLDER;
			objs.add(baseDict.getName());
		}

		if (baseDict.getType() > 0) {
			hql += " and type =" + Constants.PLACEHOLDER;
			objs.add(baseDict.getType());
		}
		return basedictdao.findCountByProperty(hql, objs);
	}

	/*
	 * 删除(读取配置文件) (non-Javadoc)
	 * 
	 * @see net.cedu.biz.basesetting.BaseDictBiz#deleteConfigBaseDict(int)
	 */
	public BaseDict deleteConfigBaseDict(int id) throws Exception {

		BaseDict baseDict = basedictdao.deleteConfig(id);
		if (baseDict != null) {

			List<BaseDict> bdlst = this
					.findBaseDictsByTypeAndParentNodeAndDeleteFlag(
							Constants.BASEDICT_STYLE_ENROLLMENTWAY, baseDict
									.getId());
			if (bdlst != null && bdlst.size() > 0) {
				BaseDict bd = null;
				for (int i = 0; i < bdlst.size(); i++) {
					bd = new BaseDict();
					bd = bdlst.get(i);
					bd.setParentNode(0);
					bd.setLogicNode("_" + bd.getParentNode() + "_" + bd.getId()
							+ "_");
					this.modifyBaseDict(bd);
				}
			}
		}
		return baseDict;
	}

	/*
	 * 根据字典类型&父级
	 * 
	 * @see net.cedu.biz.basesetting.BaseDictBiz#findAllBaseDictsByType(int,
	 * int)
	 */
	public List<BaseDict> findBaseDictsByTypeAndParentNodeAndDeleteFlag(
			int type, int parentNode) throws Exception {
		return basedictdao.getByProperty(" and type=" + Constants.PLACEHOLDER
				+ " and parentNode=" + Constants.PLACEHOLDER
				+ " and deleteFlag=" + Constants.PLACEHOLDER+ " order by orderNumber", new Object[] {
				type, parentNode, Constants.DELETE_FALSE });
	}

	/*
	 * 按类型查询字典树形结构
	 * 
	 * @see net.cedu.biz.basesetting.BaseDictBiz#findBaseDictsByType(int)
	 */
	public List<BaseDict> findBaseDictsByType(int type) throws Exception {
		List<BaseDict> baseDictList = basedictdao.getByProperty(" and type="
				+ Constants.PLACEHOLDER + " and deleteFlag="
				+ Constants.PLACEHOLDER + " order by orderNumber", new Object[] {
				type, Constants.DELETE_FALSE });
		
//		for (BaseDict baseDict : baseDictList) {
//			if (baseDict != null) {
//				if (baseDict.getParentNode() == 0) {
//					baseDict.setName("|--" + baseDict.getName());
//				} else {
//					baseDict.setName("|----" + baseDict.getName());
//				}
//
//			}
//		}
		return baseDictList;
	}
	
	/*
	 * 按类型查询字典树形结构
	 * 
	 * @see net.cedu.biz.basesetting.BaseDictBiz#findBaseDictsByType(int)
	 */
	public List<BaseDict> findBaseDictsByType(int type,int branchId) throws Exception {
		String sql="";
		List<Object> paramList=new ArrayList<Object>();
		BranchEnrollmentWay bew=branchEnrollmentWayBiz.findByBranchId(branchId);
		
		if(null!=bew)
		{
			if(null!=bew.getEnrollmentWays()&&1<bew.getEnrollmentWays().length())
			{
				String ids=bew.getEnrollmentWays();
				
				ids=ids.replace('@',',');
				
				sql+=" and id in("+ Constants.PLACEHOLDER+")";
				paramList.add("$"+ids.substring(1, (ids.length()-1)));
			}else{
				return new ArrayList<BaseDict>();
			}
		}else{
			return new ArrayList<BaseDict>();
		}
		
		
		sql+="and type="+ Constants.PLACEHOLDER;
		paramList.add(type);
		
		sql+="and deleteFlag="+ Constants.PLACEHOLDER;
		paramList.add(Constants.DELETE_FALSE);
		
		
		sql+=" order by orderNumber";
		
		List<BaseDict> baseDictList = basedictdao.getByProperty(sql, paramList);
		
		if(null==baseDictList||0==baseDictList.size())
			baseDictList=this.findAllBaseDictsByType(type);
		
		return baseDictList;
	}

	/*
	 * 按typeId & 名称查询基础字典
	 * 
	 * @see net.cedu.biz.basesetting.BaseDictBiz#findBaseDictsByTypeAndName(int,
	 * java.lang.String)
	 */
	public BaseDict findBaseDictsByTypeAndName(int type, String name)
			throws Exception {
		List<BaseDict> bdlst = basedictdao.getByProperty(" and type="
				+ Constants.PLACEHOLDER + " and name=" + Constants.PLACEHOLDER
				+ " and deleteFlag=" + Constants.PLACEHOLDER+ " order by orderNumber", new Object[] {
				type, name, Constants.DELETE_FALSE });
		if (null != bdlst && bdlst.size() > 0) {
			return bdlst.get(0);
		}
		return null;
	}
}
