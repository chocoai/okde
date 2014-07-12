package net.cedu.biz.basesetting.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.common.Constants;
import net.cedu.dao.basesetting.LevelDao;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyLevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 层次
 * @author HXJ
 */
@Service
public class LevelBizImpl implements LevelBiz{

	@Autowired
	private LevelDao leveldao;

	/*	增加层次
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.LevelBiz#addLevel(net.cedu.entity.basesetting.Level)
	 */
	public boolean addLevel(Level level) throws Exception{

		if (0>=this.findTotalByProperty(level)) {
			leveldao.save(level);
			return true;
		}
		return false;
	}

//	//按主键删除(逻辑删除)
//	public int deleteLevelByFlag(int id) {
//		
//		return leveldao.deleteByFlag(id);
//	}
//
//	//按主键删除(物理删除)
//	public Level deleteLevelById(Serializable id) {
//		
//		return leveldao.deleteById(id);
//	}

	/*
	 * 查询所有层次列
	 */
	public List<Level> findAllLevels() throws Exception {
		return leveldao.findAll();
	}

	/* 查询所有层次列(delete_flag=0)
 	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.LevelBiz#findAllLevelsByDeleteFlag()
	 */
	public List<Level> findAllLevelsByDeleteFlag() throws Exception {
		
		return leveldao.getByProperty("deleteFlag", Constants.DELETE_FALSE);
	}

	/*	按主键id查询level
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.LevelBiz#findLevelById(java.io.Serializable)
	 */
	public Level findLevelById(Serializable id) {
		
		return leveldao.findById(id);
	}

	/* 修改层次
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.LevelBiz#modifyLevel(net.cedu.entity.basesetting.Level)
	 */
	public boolean modifyLevel(Level level) throws Exception {
		if(0>=findTotalByProperty(level)){
			leveldao.update(level);
			return true;
		}
		return false;
	}
	
	/* 根据层次(院校批次)的属性levelId 查询对应的Level实体  然后循环给赋List<AcademyLevel>
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.LevelBiz#addLevelforAcademyLevel(java.util.List)
	 */
	public List<AcademyLevel> addLevelforAcademyLevel(List<AcademyLevel> academylevel){
		if(academylevel!=null&&academylevel.size()>0){
			for (int i=0;i<academylevel.size();i++){
				academylevel.get(i).setLevel(this.findLevelById(academylevel.get(i).getLevelId()));
			}
		}
		return academylevel;
	}
	
	/* 在基础数据中查询某院校批次下未设置过的层次
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.LevelBiz#findOtherLevels(java.lang.Object[])
	 */
	public List<Level> findOtherLevels(Object...objects) throws Exception{
		String sql="";
		if(null!=objects&&objects.length>0){
			sql=" and deleteFlag="+Constants.PLACEHOLDER+ "and id not in ('-1'";
			for(int i=0;i<objects.length-1;i++){
				sql+=","+Constants.PLACEHOLDER;
			}
			sql+=") order by id desc";
		}else{
			return this.findAllLevels();
		}
		
		return leveldao.getByProperty(sql, objects);
	}
	
	/*查询数据库中是否有重复的数据
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.LevelBiz#findTotalByProperty(net.cedu.entity.basesetting.Level)
	 */
	@SuppressWarnings("unchecked")
	public int findTotalByProperty(Level level)throws Exception{
		String sql="";
		List list = new ArrayList();
		
		if(level.getId()>0){
			sql+=" and id <> "+Constants.PLACEHOLDER;
			list.add(level.getId());
		}
			sql+=" and (name = "+Constants.PLACEHOLDER+" or code = "+Constants.PLACEHOLDER+")";
			list.add(level.getName());
			list.add(level.getCode());
		return leveldao.findCountByProperty(sql, list);
	}
	
	/*	删除(读取配置文件)
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.LevelBiz#deleteConfigLevel(int)
	 */
	public Level deleteConfigLevel(int id){
		return leveldao.deleteConfig(id);
	}
	/*
	 * 查询层次
	 * @see net.cedu.biz.basesetting.LevelBiz#findLevelByName(java.lang.String)
	 */
	public Level findLevelByName(String name) {
		return leveldao.getObjByProperty("name", name);
	}
}
