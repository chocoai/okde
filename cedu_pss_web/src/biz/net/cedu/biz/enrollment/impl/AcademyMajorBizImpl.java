package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyMajorDao;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.Major;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademyMajorBizImpl implements AcademyMajorBiz
{
	@Autowired
	private AcademyMajorDao academyMajorDao;
	@Autowired
	private MajorBiz majorBiz;//专业biz
	@Autowired
	private AcademyLevelBiz academyLevelBiz;
	
	/**
	 * 根据Id查询专业
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyMajor findById(int id) throws Exception
	{
		return academyMajorDao.findById(id);
	}
	
	/**
	 * 查询某层次下的所有专业
	 * 
	 * @param levelId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyMajor> findByLevel(int levelId) throws Exception
	{
		String sql=" and academyLevelId = "+Constants.PLACEHOLDER+" and deleteFlag = "+Constants.PLACEHOLDER+" order by majorId";
		return academyMajorDao.getByProperty(sql,new Object[]{levelId,Constants.DELETE_FALSE});
	}
	
	/**
	 * 查询某层次下的所有专业_定时任务
	 * 
	 * @param levelId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyMajor> findByLevelForTask(List<Integer> idList) throws Exception
	{
		String sql=" and deleteFlag = "+Constants.PLACEHOLDER;
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(Constants.DELETE_FALSE);
		if(null!=idList)
		{
			sql+=" and academyLevelId in(";
			StringBuffer idsSB = new StringBuffer("0");
			for(int i=0;i<idList.size();i++)
			{
				if(0==i){
//					sql+=Constants.PLACEHOLDER;
					idsSB = new StringBuffer(Constants.PLACEHOLDER);
				}
				else{
//					sql+=","+Constants.PLACEHOLDER;
					idsSB.append(","+Constants.PLACEHOLDER);
				}
				paramList.add(idList.get(i));
			}
			sql+=idsSB.toString();
			sql+=")";
		}
		sql+=" order by majorId";
		return academyMajorDao.getByProperty(sql,paramList.toArray());
	}
	
	/**
	 * 查询某层次下的所有基础专业（返回的是基础专业集合）
	 * 
	 * @param levelId
	 * @return
	 * @throws Exception
	 */
	public List<Major> findMajorListByLevelId(int levelId) throws Exception
	{
		List<AcademyMajor> list=this.findByLevel(levelId);
		if(list==null) return null;
		List<Major> majorlist=new ArrayList<Major>();
		for(AcademyMajor acamajor:list)
		{
			Major major=this.majorBiz.findMajorById(acamajor.getMajorId());
			majorlist.add(major);
		}
		return majorlist;
	}
	
	/**
	 * 查询某层次下的所有基础专业（返回的是基础专业集合）定时任务
	 * 
	 * @param levelId
	 * @return
	 * @throws Exception
	 */
	public List<Major> findMajorListByLevelIdForTask(List<Integer> idList) throws Exception
	{
		List<AcademyMajor> list=this.findByLevelForTask(idList);
		if(list==null) return null;
		List<Major> majorlist=new ArrayList<Major>();
		for(AcademyMajor acamajor:list)
		{
			Major major=this.majorBiz.findMajorById(acamajor.getMajorId());
			majorlist.add(major);
		}
		return majorlist;
	}
	
	//查询某层次下的所有基础专业名称
	public String findMajorNameByLevelId(int levelId) throws Exception{
		List<Major> majorlist=new ArrayList<Major>();
		majorlist = this.findMajorListByLevelId(levelId);
		
		StringBuffer sb= new StringBuffer();
		
			for(int i=0;i<majorlist.size();i++){
				sb.append(majorlist.get(i).getName());
				if (i!=majorlist.size()-1) {
					sb.append(",");
				}
			}
			return sb.toString();
		
	}
	
	//添加
	public Object addAcademyMajor(AcademyMajor academyMajor){
		return academyMajorDao.save(academyMajor);
	}

	
	/*
	 * 查询某层次下的所有专业 
	 * @see net.cedu.biz.enrollment.AcademyMajorBiz#findAcademyMajorByLevel(int)
	 */
	public List<AcademyMajor> findAcademyMajorByLevel(int academyLevelId)
			throws Exception {
		
		List<AcademyMajor> academyMajorlst = null;
		PageParame p = new PageParame();
		String hqlparam="";
		String params="";
		if(academyLevelId!=0)
		{
			hqlparam+=" and  academyLevelId="+Constants.PLACEHOLDER;
			params+=academyLevelId+",";
		}
		else
		{
			return null;
		}
		hqlparam+=" and deleteFlag = "+Constants.PLACEHOLDER;
		params+=Constants.DELETE_FALSE;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(params.split(","));
		}
		Long[] academyMajorits=academyMajorDao.getIDs(p);
		if (academyMajorits != null && academyMajorits.length != 0) {
			academyMajorlst=new ArrayList<AcademyMajor>();
			for(int i = 0; i < academyMajorits.length; i++)
			{
				AcademyMajor am=this.findById(Integer.valueOf(academyMajorits[i].toString()));
				AcademyMajor academymajor=am;
				Major major=majorBiz.findMajorById(academymajor.getMajorId());
				if (major!=null) {
					academymajor.setMajorName(major.getName());
					academymajor.setMajorCode(major.getCode());
				}
				academyMajorlst.add(academymajor);
			}	
		}
		
		return academyMajorlst;
	}
	
	/*根据多个院校层次ID删除每个院校层次对应的院校专业
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyMajorBiz#deleteAcademyMajorByAcademyLevelId(int)
	 */
	public int deleteAcademyMajorByAcademyLevelId(List<AcademyLevel> collegelist){
		String sql="";
		Object [] objs=null;
		if(null!=collegelist&&collegelist.size()>0){
			objs=new Object[collegelist.size()+1];
			sql=" and academyLevelId in ('-1'";
			StringBuffer idsSB = new StringBuffer("");
			for(int i=0,len=collegelist.size();i< len;i++){
				objs[i]=collegelist.get(i).getId();
//				sql+=","+Constants.PLACEHOLDER;
				idsSB.append(","+Constants.PLACEHOLDER);
			}
			sql+=idsSB.toString();
			sql+=") order by majorId";
			return academyMajorDao.deleteByProperty(sql, objs);
		}else{
			return 0;
		}
	}
	
	/*按照院校层次查询对应院校基础专业的ID集合
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyMajorBiz#findAcademyMajorIdsByAcademyLevelId(int)
	 */
	public int[] findAcademyMajorIdsByAcademyLevelId(int academylevelid) throws Exception {
		List<AcademyMajor> schmajorlst= new ArrayList<AcademyMajor>();
		schmajorlst=this.findByLevel(academylevelid);
		int [] academymajorids = null;
		if(schmajorlst!=null&&schmajorlst.size()>0){
			academymajorids = new int[schmajorlst.size()];
			for(int i = 0,len = schmajorlst.size();i<len;i++){
				academymajorids[i] = schmajorlst.get(i).getMajorId();
			}
		}
		return academymajorids;
	}
	
	/*根据院校层次id删除对应专业
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyMajorBiz#deleteAcademyMajorByAcademyLevelId(int)
	 */
	@SuppressWarnings("unchecked")
	public int deleteAcademyMajorByAcademyLevelId(int id){
		List list = new ArrayList();	
		list.add(id);
		String sql=" and academyLevelId= "+Constants.PLACEHOLDER;
		return academyMajorDao.deleteByProperty(sql, list);
	}
	
	/*批量添加院校专业
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.AcademyMajorBiz#addSeveralAcademyMajor(net.cedu.entity.enrollment.AcademyMajor, int[])
	 */
	public void addSeveralAcademyMajor(AcademyMajor academyMajor,int [] allmajorids)throws Exception{
		
		if (academyMajor!=null&&allmajorids != null && allmajorids.length > 0) {
			for (int i = 0, len = allmajorids.length; i < len; i++) {
				AcademyMajor schmajor = new AcademyMajor();
				schmajor.setAcademyLevelId(academyMajor.getAcademyLevelId());
				schmajor.setDeleteFlag(Constants.DELETE_FALSE);
				schmajor.setMajorId(allmajorids[i]);
				schmajor.setCreatedTime(new Date());
				schmajor.setUpdatedTime(new Date());
				schmajor.setCreatorId(academyMajor.getCreatorId());
				schmajor.setUpdaterId(academyMajor.getUpdaterId());
				
				this.addAcademyMajor(schmajor);
			}
		}
	}
	
	/**
	 * 查询院校某批次下对应基础层次ID为levelId的MajorLevel下的所有基础专业
	 * @param batchId 院校招生批次ID
	 * @param levelId 基础层次ID
	 * @return
	 * @throws Exception
	 */
	public List<AcademyMajor> findAcademyMajorByLevelId(int batchId, int levelId) throws Exception
	{
		AcademyLevel al = academyLevelBiz.findByBatchAndLevel(batchId, levelId);
		
		if(al==null) return null;
		
		List<AcademyMajor> list = academyMajorDao.getByProperty(" and academyLevelId="+Constants.PLACEHOLDER+" and deleteFlag = "+Constants.PLACEHOLDER+" order by majorId", new Object[]{ al.getId(),Constants.DELETE_FALSE });
		
		return list;
	}
}
