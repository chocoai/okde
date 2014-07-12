package net.cedu.biz.persontool.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.persontool.DuibiFileBiz;
import net.cedu.common.Constants;
import net.cedu.dao.persontool.DuibiFileDao;
import net.cedu.entity.persontool.DuibiFile;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 对比文件实现类
 * 
 * @author yangdongdong
 * 
 */
@Service
public class DuibiFileBizImpl implements DuibiFileBiz {

	@Autowired
	private DuibiFileDao duibiFileDao;
	

	/**
	 * 新建对比文件以及跟进纪录
	 */
	public int addDuibiFile(DuibiFile duibiFile) throws Exception {
		duibiFileDao.save(duibiFile);
		return 1;
	}

	/**
	 * 删除对比文件
	 */
	public String deleteById(int id) throws Exception {
		duibiFileDao.deleteById(id);
		return "";

	}
	
	/**
	 * 修改对比文件
	 */
	public String updateById(DuibiFile duibiFile) throws Exception {
		duibiFileDao.update(duibiFile);
		return "";

	}

	/**
	 * 查看对比文件
	 */
	public DuibiFile findStudentById() throws Exception {
		List<DuibiFile> list=findStudentByPrePurchaseCenter();
		if(null==list||0==list.size())
			return null;
		else
			return list.get(0);
	}
	
	/**
	 * 按照中心预申请条件查询对比文件
	 */
	public List<DuibiFile> findStudentByPrePurchaseCenter() throws Exception {
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
				
		hqlcon+=" order by creatOn desc";
		
		return duibiFileDao.getByProperty(hqlcon,paramList);
	}
}
