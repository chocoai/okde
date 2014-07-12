package net.cedu.dao.base.impl;

import net.cedu.dao.base.TaskDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.base.UserTask;

import org.springframework.stereotype.Repository;

@Repository
public class TaskDaoImpl extends BaseMDDaoImpl<UserTask> implements TaskDao {

}
