package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyLevelDao;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademyLevelBizImpl implements AcademyLevelBiz
{
	@Autowired
	private AcademyLevelDao academyLevelDao;
	@Autowired
	private LevelBiz levelbiz;
	@Autowired
	private AcademyMajorBiz academyMajorBiz;
	
	/**
	 * 查询所有层次实体
	 * @return
	 * @throws Exception
	 */
	public List<AcademyLevel> findAll() throws Exception
	{
		return academyLevelDao.findAll();
	}
	
	/**
	 * 根据批次ID查询层次
	 * @param batchId
	 * 
	 * @return
	 */
	public List<AcademyLevel> findBatchId(int batchId) throws Exception
	{
		return academyLevelDao.getByProperty(" and deleteFlag="+Constants.PLACEHOLDER+" and academyEnrollBatchId="+Constants.PLACEHOLDER, Constants.DELETE_FALSE, batchId);
	}
	
	/**
	 * 根据批次ID查询所有层次_定时任务
	 * @param batchId
	 * 
	 * @return
	 */
	public List<AcademyLevel> findBatchIdForTask(List<Integer> idList) throws Exception
	{
		List<Object> paramList=new ArrayList<Object>();
		
		String sql=" and deleteFlag="+Constants.PLACEHOLDER;
		paramList.add(Constants.DELETE_FALSE);
		
		sql+=" and academyEnrollBatchId in(";
		StringBuffer idsSB = new StringBuffer("0");
		for(int i=0;i<idList.size();i++)
		{
			if(0==i){
//				sql+=Constants.PLACEHOLDER;
				idsSB = new StringBuffer(Constants.PLACEHOLDER);
			}
			else{
				sql+=","+Constants.PLACEHOLDER;
				idsSB.append(","+Constants.PLACEHOLDER);
			}
			paramList.add(idList.get(i));
		}
		sql+=idsSB.toString();
		sql+=")";
		return academyLevelDao.getByProperty(sql,paramList.toArray());
	}
	
	/**
	 * 根据Id查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyLevel findById(int id) throws Exception
	{
		return academyLevelDao.findById(id);
	}
	
	/*
	 * 批量添加 院校层次
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyLevelBiz#addAcademyLevel(net.cedu.entity.enrollment.AcademyLevel, int[])
	 */
	public boolean addAcademyLevels(int[] levelids,AcademyLevel academyLevel){
		Object obj=null;
		if(academyLevel!=null){
			if(levelids!=null&&levelids.length>0){
				for(int i=0,len=levelids.length;i<len;i++){
					
						AcademyLevel schLevel = new AcademyLevel();
						schLevel.setCreatedTime(new Date());
						schLevel.setUpdatedTime(new Date());
						schLevel.setDeleteFlag(Constants.DELETE_FALSE);
						schLevel.setCreatorId(academyLevel.getCreatorId());
						schLevel.setUpdaterId(academyLevel.getUpdaterId());
						schLevel.setAcademyEnrollBatchId(academyLevel
								.getAcademyEnrollBatchId());
						schLevel.setLevelId(levelids[i]);
						obj = academyLevelDao.save(schLevel);
			
					if(obj==null||("").equals(obj)){
						return false;
					}
				}
			}
		}
		return true;
	}

	/*根据招生批次 查询带层次名称的List<AcademyLevel>
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyLevelBiz#findAcademyLevelNameBatchId(int)
	 */
	public List<AcademyLevel> findAcademyLevelNameBatchId(int batchId)
			throws Exception {
		List<AcademyLevel> academylevellst = null;
		PageParame p = new PageParame();
//		p.setPage(false);
		String hqlparam="";
		String params="";
		if(batchId!=0)
		{
			hqlparam+=" and  academyEnrollBatchId="+Constants.PLACEHOLDER;
			params+=batchId+",";
		}
		hqlparam+=" and deleteFlag = "+Constants.PLACEHOLDER;
		params+=Constants.DELETE_FALSE;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(params.split(","));
		}
		Long[] academylevelits=academyLevelDao.getIDs(p);
		if (academylevelits != null && academylevelits.length != 0) {
			academylevellst=new ArrayList<AcademyLevel>();
			for(int i = 0,len=academylevelits.length; i < len; i++)
			{
				AcademyLevel al=this.findById(Integer.valueOf(academylevelits[i].toString()));
				AcademyLevel academylevel=al;
				Level level=levelbiz.findLevelById(academylevel.getLevelId());
				if (level!=null) {
					academylevel.setLevelName(level.getName());
				}
				academylevellst.add(academylevel);
			}	
		}
		return academylevellst;
	}
	
	/*
	 * (non-Javadoc)通过院校招生批次ID 批量删除院校层次
	 * @see net.cedu.biz.enrollment.AcademyLevelBiz#deleteAcademyLevelByBatchId(int)
	 */
	@SuppressWarnings("unchecked")
	public int deleteAcademyLevelByBatchId(int batchid){
		List list = new ArrayList();	
		list.add(batchid);
		String sql=" and academyEnrollBatchId= "+Constants.PLACEHOLDER;
		return academyLevelDao.deleteByProperty(sql, list);
	}
	
	/*根据招生批次和基础层次ID查询院校层次(批量)
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyLevelBiz#findByBatchIdAndLevelid(int, java.lang.Object[])
	 */
	public List<AcademyLevel> findByBatchIdAndLevelid(int[] ids) throws Exception{
		String sql="";
		Object[] objs = null;
		if(null!=ids&&ids.length>0){
				objs=new Object[ids.length+1];
			sql=" and academyEnrollBatchId="+Constants.PLACEHOLDER +" and levelId in ('-1'";
			StringBuffer idsSB = new StringBuffer("");
			for(int i=0,len=ids.length;i<len;i++){
				objs[i]=ids[i];
				if (i<len-1) {
//					sql += "," + Constants.PLACEHOLDER;
					idsSB.append("," + Constants.PLACEHOLDER);
				}
			}
			sql+=idsSB.toString();
			sql+=") and deleteFlag = "+Constants.PLACEHOLDER;
			objs[objs.length-1]=Constants.DELETE_FALSE;
			
			return academyLevelDao.getByProperty(sql, objs);
		}
		return null;
	}
	
	/*按id批量删除院校层次
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyLevelBiz#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String ids){
		return academyLevelDao.deleteByIds(ids);
	}
	
	/*批量复制其他招生批次的院校层次和专业
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyLevelBiz#addAcademyLevelAndMajorfromOtherEnrollmentBatch()
	 */
	public void addAcademyLevelAndMajorfromOtherEnrollmentBatch(int userid,int presentbatchid,int copybatchid,int[] levelIdsArray) throws Exception{
		if(userid>0&&presentbatchid>0&&copybatchid>0&&levelIdsArray!=null&&levelIdsArray.length>0){
			for (int i = 0,len=levelIdsArray.length; i < len; i++) {
					//获取被复制层次的信息
					List<AcademyLevel> oldacademylevel= new ArrayList<AcademyLevel>();
					oldacademylevel = academyLevelDao.getByProperty("and academyEnrollBatchId="+Constants.PLACEHOLDER +" and levelId="+Constants.PLACEHOLDER, copybatchid,levelIdsArray[i]);
					
					//赋值+添加被复制层次到目标层次下
					AcademyLevel newacademylevel = new AcademyLevel();
					newacademylevel.setLevelId(levelIdsArray[i]);
					newacademylevel.setAcademyEnrollBatchId(presentbatchid);
					newacademylevel.setDeleteFlag(Constants.DELETE_FALSE);
					newacademylevel.setCreatorId(userid);
					newacademylevel.setUpdaterId(userid);
					newacademylevel.setCreatedTime(new Date());
					newacademylevel.setUpdatedTime(new Date());
					
					Object obj=academyLevelDao.save(newacademylevel);
					//获取被复制层次下面所有的专业信息
					List<AcademyMajor> majorlist = new ArrayList<AcademyMajor>();
					if (oldacademylevel!=null&&oldacademylevel.size()>0) {
						majorlist = academyMajorBiz.findByLevel(oldacademylevel
								.get(0).getId());
					}
					
					//赋值+添加被复制层次的专业到目标层次下的院校专业中
					if(majorlist!=null&&majorlist.size()>0&&obj!=null&&!("").equals(obj)){
						for (int j = 0,lenth=majorlist.size(); j < lenth; j++) {
							AcademyMajor academymajor = new AcademyMajor();
							
							academymajor.setAcademyLevelId(Integer.parseInt(obj.toString()));
							academymajor.setMajorId(majorlist.get(j).getMajorId());
							academymajor.setDeleteFlag(Constants.DELETE_FALSE);
							academymajor.setCreatorId(userid);
							academymajor.setUpdaterId(userid);
							academymajor.setCreatedTime(new Date());
							academymajor.setUpdatedTime(new Date());
							
							academyMajorBiz.addAcademyMajor(academymajor);
						}
						
					}
			}
		}
	}
	
	/**
	 * 找出某批次下层次（基础）ID为<code> levelId </code>的院校层次
	 * @param batchId 招生批次ID
	 * @param levelId 基础层次ID
	 * @return
	 * @throws Exception
	 */
	public AcademyLevel findByBatchAndLevel(int batchId, int levelId) throws Exception
	{
		List<AcademyLevel> list = academyLevelDao.getByProperty(" and levelId="+Constants.PLACEHOLDER+" and academyEnrollBatchId="+Constants.PLACEHOLDER, levelId, batchId);
		
		if(list==null) return null;
		
		if(list.size()==1) return list.get(0);
		
		if(list.size()>1) throw new RuntimeException();
		
		return null;
	}

	
	/*
	 * 根据基础层次查询
	 * @see net.cedu.biz.enrollment.AcademyLevelBiz#findByGllevel(int)
	 */
	public List<AcademyLevel> findByGllevel(int gllevelId) throws Exception {
		
		return academyLevelDao.getByProperty(" and levelId="+Constants.PLACEHOLDER+" and deleteFlag = "+Constants.PLACEHOLDER,new Object[]{gllevelId,Constants.DELETE_FALSE});
	}
}
