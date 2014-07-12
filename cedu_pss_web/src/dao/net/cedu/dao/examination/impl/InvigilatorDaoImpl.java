package net.cedu.dao.examination.impl;
import org.springframework.stereotype.Repository;
import net.cedu.dao.examination.InvigilatorDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.examination.Invigilator;
/**
 * 
 * 监考老师数据层实现类
 * 
 * */
@Repository
public class InvigilatorDaoImpl extends BaseMDDaoImpl<Invigilator> implements
		InvigilatorDao {
}
