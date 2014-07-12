package net.cedu.biz.teaching;

import java.util.List;

import net.cedu.entity.teaching.TeachingNotice;
import net.cedu.model.page.PageResult;

/**
 * 教学公告管理业务逻辑
 * @author wangmingjie
 *
 */
public interface TeachingNoticeBiz {
	/**
	 * 教学公告总数查询
	 */
public int findTeachingNoticePageCountByConditions(PageResult<TeachingNotice> result, TeachingNotice  teachingNotice)throws Exception;

/*
 * 查询教学公告总集合(通过查询条件)
 */
public List<TeachingNotice> findTeachingNoticePageListByConditions(PageResult<TeachingNotice> result, TeachingNotice  teachingNotice)throws Exception;

/**
 * 增加
 */
public void addTeachingNotice(TeachingNotice teachingNotice) throws Exception;
/**
 * 按ID查询
 */
public TeachingNotice findTeachingNoticeById(int id) throws Exception;
}
