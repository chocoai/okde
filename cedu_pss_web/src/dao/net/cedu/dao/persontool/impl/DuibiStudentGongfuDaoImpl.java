package net.cedu.dao.persontool.impl;

import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.dao.persontool.DuibiStudentGongfuDao;
import net.cedu.entity.persontool.DuibiStudentGongfu;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 公服对比数据层实现类
 * 
 * @author 谭必庆
 * 
 */
@Repository
public class DuibiStudentGongfuDaoImpl extends BaseMDDaoImpl<DuibiStudentGongfu> implements
 DuibiStudentGongfuDao {

//	public void deleteAll()throws Exception
//	{
//		super.delete
//		JdbcTemplate jt = this.getJdbcTemplate();
//		int mid = jt.update("TRUNCATE TABLE tb_e_person_tool_gongfu");
//		System.out.println(mid);
//	}
}
