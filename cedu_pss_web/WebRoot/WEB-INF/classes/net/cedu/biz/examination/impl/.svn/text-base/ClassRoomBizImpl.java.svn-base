package net.cedu.biz.examination.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.examination.ClassRoomBiz;
import net.cedu.common.Constants;
import net.cedu.dao.examination.ClassRoomDao;

import net.cedu.entity.examination.ClassRoom;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassRoomBizImpl implements ClassRoomBiz {
	@Autowired
	private ClassRoomDao classroomdao;
 
	public boolean createNew(ClassRoom classroom) throws Exception {
		if(null==findByName(classroom.getName()))
		{
			classroomdao.save(classroom);
			return true;
		}
		return false;
		}

	public Object deleteClassRoomById(int id) throws Exception {
		// TODO Auto-generated method stub
		return classroomdao.deleteById(id);
	}
	
	public List<ClassRoom> findByConditions(ClassRoom classroom,
			PageResult<ClassRoom> pr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public ClassRoom findByName(String name) throws Exception {
		String hqlcon = "";
		List<ClassRoom> list = null;
		List<Object> paramList = new ArrayList<Object>();
		// 名称
		if (StringUtils.isNotBlank(name)) {
			hqlcon += " and name=" + Constants.PLACEHOLDER;
			paramList.add(name);
		}
		if (0 < hqlcon.length())
			list = classroomdao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size())
			return list.get(0);
		return null;
	}
	public int findClassRoomPageCount(ClassRoom classroom,
			PageResult<ClassRoom> pr) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ClassRoom> page(PageResult<ClassRoom> result) throws Exception {
		List<ClassRoom> classroomlist =new ArrayList<ClassRoom>();

		PageParame pp = new PageParame(result);
		Long[]list=classroomdao.getIDs(pp);
		
		for(int i=0;i<list.length;i++)
		{
			classroomlist.add(classroomdao.findById(list[i].intValue()));
		}
		return classroomlist;
	}
	public ClassRoom findClassroomById(int id)throws Exception{
		return classroomdao.findById(id);
	}

	public Object updateClassroom(ClassRoom classroom) throws Exception {
		// TODO Auto-generated method stub
		return classroomdao.update(classroom);
	}
	public int findAllClassRoomCount(PageResult<ClassRoom> pr) throws Exception {
		PageParame p = new PageParame(pr);
		
		return classroomdao.getCounts(p);
	}
	public List<ClassRoom> findAllclassroom()throws Exception{
		return classroomdao.findAll();
	}

}
