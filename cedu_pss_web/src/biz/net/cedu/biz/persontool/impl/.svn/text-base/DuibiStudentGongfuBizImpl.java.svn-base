package net.cedu.biz.persontool.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.persontool.DuibiStudentGongfuBiz;
import net.cedu.dao.persontool.DuibiStudentGongfuDao;
import net.cedu.entity.persontool.DuibiStudentGongfu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 对比文件实现类
 * 
 * @author yangdongdong
 * 
 */
@Service
public class DuibiStudentGongfuBizImpl implements DuibiStudentGongfuBiz {

	@Autowired
	private DuibiStudentGongfuDao duibiStudentGongfuDao;
	

	/**
	 * 新建对比文件以及跟进纪录
	 */
	public int addDuibiFile(DuibiStudentGongfu duibiStudentGongfu) throws Exception {
		duibiStudentGongfuDao.save(duibiStudentGongfu);
		return 1;
	}

	/**
	 * 删除对比文件
	 */
	public String deleteById(int id) throws Exception {
		duibiStudentGongfuDao.deleteById(id);
		return "";

	}
	
	/**
	 * 删除对比结果
	 */
	public void deleteByAll() throws Exception {
		duibiStudentGongfuDao.deleteAll();
	}
	
	/**
	 * 修改对比文件
	 */
	public String updateById(DuibiStudentGongfu duibiStudentGongfu) throws Exception {
		duibiStudentGongfuDao.update(duibiStudentGongfu);
		return "";

	}

	/**
	 * 查看对比文件
	 */
	public DuibiStudentGongfu findStudentById(int id) throws Exception {
		return duibiStudentGongfuDao.findById(id);
	}
	
	/**
	 * 按照中心预申请条件查询对比文件
	 */
	public List<DuibiStudentGongfu> findStudentByPrePurchaseCenter() throws Exception {
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
				
//		hqlcon+=" order by creatOn desc";
		
		return duibiStudentGongfuDao.getByProperty(hqlcon,paramList);
	}
}
