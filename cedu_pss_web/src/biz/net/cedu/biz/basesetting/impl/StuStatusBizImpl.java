package net.cedu.biz.basesetting.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.StuStatusBiz;
import net.cedu.common.Constants;
import net.cedu.dao.basesetting.StuStatusDao;
import net.cedu.entity.basesetting.StudentStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuStatusBizImpl implements StuStatusBiz{

	@Autowired
	private StuStatusDao stustatusdao;

	/* 查询全部
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.StuStatusBiz#findAllStudentStatus()
	 */
	public List<StudentStatus> findAllStudentStatus() throws Exception {
		
		return stustatusdao.findAll();
	}

	/*查询全部deleteFlag=0的列表
	 * \(non-Javadoc)
	 * @see net.cedu.biz.basesetting.StuStatusBiz#findAllStudentStatusByDeleteFlag()
	 */
	public List<StudentStatus> findAllStudentStatusByDeleteFlag()
			throws Exception {
	
		return stustatusdao.getByProperty("deleteFlag", Constants.DELETE_FALSE);
	}
	
	/* 按阶段查询学生状态
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.StuStatusBiz#findStatusNamesByStage(java.lang.String)
	 */
	public List<StudentStatus> findStatusNamesByStage(String stage) throws Exception{
		String sql=" and stage like "+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER;
		return stustatusdao.getByProperty(sql, new Object[]{"%"+stage+"%",Constants.DELETE_FALSE});
	}

	/*
	 * 查询学生阶段
	 * @see net.cedu.biz.basesetting.StuStatusBiz#findStatusStage()
	 */
	public List<StudentStatus> findStatusStage() throws Exception {
		
		return stustatusdao.getByProperty(" and deleteFlag ="+Constants.PLACEHOLDER+" group by stageCode ",new Object[]{Constants.DELETE_FALSE});
	}

	
	/*
	 * 按阶段查询学生状态
	 * @see net.cedu.biz.basesetting.StuStatusBiz#findStatusNamesByStageCode(java.lang.String)
	 */
	public List<StudentStatus> findStatusNamesByStageCode(String stageCode)
			throws Exception {
		String sql="";
		List<Object> list=new ArrayList<Object>();
		if(stageCode==null)
		{
			sql+=" and deleteFlag ="+Constants.PLACEHOLDER +" order by stageCode ";
			list.add(Constants.DELETE_FALSE);
			
		}else
		{
			if("0".equals(stageCode))
			{
				sql+=" and deleteFlag ="+Constants.PLACEHOLDER+" order by stageCode ";
				list.add(Constants.DELETE_FALSE);
			}else{
			
				sql+=" and stageCode ="+Constants.PLACEHOLDER+" and deleteFlag ="+Constants.PLACEHOLDER+" order by stageCode ";
				list.add(stageCode);
				list.add(Constants.DELETE_FALSE);
			}
		}
		return stustatusdao.getByProperty(sql, list);
	}
	
	/*
	 * 根据Id查询学生状态
	 * 
	 * @see net.cedu.biz.basesetting.StuStatusBiz#findStudentStatusById(int)
	 */
	public StudentStatus findStudentStatusById(int id) throws Exception
	{
		return this.stustatusdao.findById(id);
	}
	
	/*
	 * 查询两个id之间的学生状态
	 * @see net.cedu.biz.basesetting.StuStatusBiz#findStatusNamesByStartIdAndEndId(int, int)
	 */
	public List<StudentStatus> findStatusNamesByStartIdAndEndId(int startId,int endId) throws Exception 
	{
		String sql="";
		List<Object> list=new ArrayList<Object>();
		if(startId!=0)
		{
			sql+=" and id >"+Constants.PLACEHOLDER;
			list.add(startId);
			
		}
		if(endId!=0)
		{
			sql+=" and id <"+Constants.PLACEHOLDER;
			list.add(endId);
		}
		sql+=" and deleteFlag ="+Constants.PLACEHOLDER+" order by stageCode ";
		list.add(Constants.DELETE_FALSE);
		return stustatusdao.getByProperty(sql, list);
	}
}
