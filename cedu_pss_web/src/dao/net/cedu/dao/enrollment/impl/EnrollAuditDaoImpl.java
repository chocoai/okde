package net.cedu.dao.enrollment.impl;

import org.springframework.stereotype.Repository;

import net.cedu.dao.enrollment.EnrollAuditDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.crm.Student;

/**
 * 报名审核
 * @author HXJ
 *
 */
@Repository
public class EnrollAuditDaoImpl extends BaseMDDaoImpl<Student> implements EnrollAuditDao{

}
