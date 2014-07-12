package net.cedu.biz.enrollment.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.BaseMajorBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.MajorDao;
import net.cedu.entity.basesetting.BaseMajor;
import net.cedu.entity.enrollment.Major;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 专业(院校)
 * 
 * @author HXJ
 */
@Service
public class MajorBizImpl implements MajorBiz {

	@Autowired
	private MajorDao majordao;
	@Autowired
	private BaseMajorBiz basemajorbiz;

	// 增加专业
	public boolean addMajor(Major major) throws Exception {
		if (0 >= findTotalByProperty(major)) {
			majordao.save(major);
			return true;
		}
		return false;
	}

	// 修改专业
	public boolean modifyMajor(Major major) throws Exception {
		if (0 >= findTotalByProperty(major)) {
			majordao.update(major);
			return true;
		}
		return false;
	}

	// //按主键删除(物理删除)
	// public Major deleteMajorById(Serializable id){
	// return majordao.deleteById(id);
	// }
	//
	// //按主键删除(逻辑删除)
	// public int deleteMajorByFlag(int id){
	// return majordao.deleteByFlag(id);
	// }

	// 查询所有专业列
	public List<Major> findAllMajors(int academyId) throws Exception {
		return majordao.getByProperty("and academyId=" + Constants.PLACEHOLDER
				+ " order by name", new Object[] { academyId });
	}

	// 查询所有专业列(delete_flag=0)
	public List<Major> findAllMajorsByDeleteFlag(int academyId)
			throws Exception {
		String sql = " and academyId=" + Constants.PLACEHOLDER
				+ " and deleteFlag=" + Constants.PLACEHOLDER + " order by code asc";
		return majordao.getByProperty(sql, new Object[] { academyId,
				Constants.DELETE_FALSE });
	}

	// 按主键id查询major
	public Major findMajorById(Serializable id) {
		return majordao.findById(id);
	}

	// 在基础数据中查询某院校批次下未设置过的专业
	public List<Major> findOtherMajors(int academyId, Object... objects)
			throws Exception {
		String sql = "";
		if (null != objects && objects.length > 0) {
			sql = " and academyId=" + Constants.PLACEHOLDER
					+ " and deleteFlag=" + Constants.PLACEHOLDER
					+ " and id not in ('-1'";
			for (int i = 0; i < objects.length - 2; i++) {
				sql += "," + Constants.PLACEHOLDER;
			}
			sql += ") order by name";
		} else {
			return this.findAllMajorsByDeleteFlag(academyId);
		}

		return majordao.getByProperty(sql, objects);
	}

	// 循环查询major所属basemajor的名称
	public List<Major> findAllBelongMajorNames(List<Major> majorlist)
			throws Exception {
		if (majorlist != null && majorlist.size() > 0) {
			BaseMajor basemajor = new BaseMajor();
			for (int i = 0; i < majorlist.size(); i++) {
				basemajor = basemajorbiz.findBaseMajorbyId(majorlist.get(i)
						.getBelongBaseMajorId());
				if (basemajor != null && !("").equals(basemajor)) {
					majorlist.get(i).setBasemajorname(basemajor.getName());
				}
			}
		}
		return majorlist;
	}

	/*
	 * 删除(读取配置文件) (non-Javadoc)
	 * 
	 * @see net.cedu.biz.enrollment.MajorBiz#deleteConfigMajor(int)
	 */
	public Major deleteConfigMajor(int id) {
		return majordao.deleteConfig(id);
	}

	/**
	 * 查询数据中是否有重复的数据
	 */
	@SuppressWarnings("unchecked")
	private int findTotalByProperty(Major major) throws Exception {
		String hql = "";
		List objs = new ArrayList();

		if (major.getId() > 0) {
			hql += " and id <> " + Constants.PLACEHOLDER;
			objs.add(major.getId());
		}

		if (StringUtils.isNotBlank(major.getCode())) {
			hql += " and code = " + Constants.PLACEHOLDER + " and academyId = "
					+ Constants.PLACEHOLDER;
			objs.add(major.getCode());
			objs.add(major.getAcademyId());
		}

		return majordao.findCountByProperty(hql, objs);
	}

	/**
	 * 查询专业
	 * 
	 * @see net.cedu.biz.enrollment.MajorBiz#findMajorByName(int,
	 *      java.lang.String, java.lang.String)
	 */
	public Major findMajorByName(int academyId, String majorName,String levelName) {
		if(majorName.indexOf("高起专")!=-1||majorName.indexOf("专升本")!=-1||majorName.indexOf("高起本")!=-1){
			return majordao.getObjByProperty(" and academyId="+ Constants.PLACEHOLDER + "  and (name like "+ Constants.PLACEHOLDER + " and name like "+ Constants.PLACEHOLDER + ")", new Object[] { academyId,"%" + majorName + "%", "%" + levelName + "%" });
		}
		return majordao.getObjByProperty(" and academyId="+ Constants.PLACEHOLDER + "  and name like "+ Constants.PLACEHOLDER, new Object[] { academyId,"%" + majorName + "%" });
	}
	
	/*
	 * 查询基础专业下的院校专业
	 * 
	 * @see net.cedu.biz.enrollment.MajorBiz#findMajorListByBaseMajorId(int)
	 */
	public List<Major> findMajorListByBaseMajorId(int baseMajorId) throws Exception
	{
		return majordao.getByProperty(" and belongBaseMajorId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER,new Object[]{baseMajorId,Constants.DELETE_FALSE});
	}
}
