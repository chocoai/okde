package net.cedu.biz.teaching.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.teaching.TeachingNoticeBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.dao.teaching.TeachingNoticeDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.book.Book;
import net.cedu.entity.teaching.TeachingNotice;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;
/**
 * 教学公告业务逻辑
 * @author wangmingjie
 *
 */
@Service
public class TeachingNoticeBizImpl implements TeachingNoticeBiz {
	@Autowired
	private TeachingNoticeDao  teachingNoticeDao;
	@Autowired
	private AcademyBiz academyBiz;// 院校
			/**
			 * 教学公告总数查询
			 */
		public int findTeachingNoticePageCountByConditions(PageResult<TeachingNotice> result, TeachingNotice  teachingNotice)
				throws Exception {
			StringBuffer hqlSb = new StringBuffer();//查询语句
			PageParame pp = new PageParame(result);
			List<Object> paramList=new ArrayList<Object>();//保存查询参数
			if(teachingNotice!=null)
			{
				if(teachingNotice.getTitle()!=null && !teachingNotice.getTitle().equals(""))
				{
					hqlSb.append(" and title like "+Constants.PLACEHOLDER);
					paramList.add('%'+teachingNotice.getTitle()+'%');
				}
				if(StringUtils.isNotBlank(teachingNotice.getContent()))
				{
					hqlSb.append(" and content like "+Constants.PLACEHOLDER);
					paramList.add('%'+teachingNotice.getContent()+'%');
				}
				if (teachingNotice.getPublishStartTime() ==null && teachingNotice.getPublishEndTime()!=null) {
					hqlSb.append( " and  publishTime <= "
							+ Constants.PLACEHOLDER);
					paramList.add(DateUtil.getDate(teachingNotice.getPublishEndTime(), "yyyy-MM-dd")+" 23:59:59");
				}
				if (teachingNotice.getPublishStartTime() !=null && teachingNotice.getPublishEndTime()==null) {
					hqlSb.append(" and  publishTime >="
							+ Constants.PLACEHOLDER);
					paramList.add(DateUtil.getDate(teachingNotice.getPublishStartTime(), "yyyy-MM-dd")+" 00:00:00");
				}
				if (teachingNotice.getPublishStartTime()!=null && teachingNotice.getPublishEndTime()!=null) {
					hqlSb.append( " and  publishTime>= "
							+ Constants.PLACEHOLDER + " and publishTime<="
							+ Constants.PLACEHOLDER);
					paramList.add(DateUtil.getDate(teachingNotice.getPublishStartTime(), "yyyy-MM-dd")+" 00:00:00");
					paramList.add(DateUtil.getDate(teachingNotice.getPublishEndTime(), "yyyy-MM-dd")+" 23:59:59");
				}
				pp.setHqlConditionExpression(hqlSb.toString());
				pp.setValues(paramList.toArray());
			}
			    Long[] teachingNoticeIds=teachingNoticeDao.getIDs(pp);
			    if(teachingNoticeIds==null)
			    {
			    	return 0;
			    }
			    return teachingNoticeIds.length;
		}
		/*
		 * 查询教学公告总集合(通过查询条件)
		 */
		public List<TeachingNotice> findTeachingNoticePageListByConditions(PageResult<TeachingNotice> result, TeachingNotice  teachingNotice)
		throws Exception {
			
			List<TeachingNotice> teachingNoticeList = new ArrayList<TeachingNotice>();//专题列表
			StringBuffer hqlSb = new StringBuffer();//查询语句
			PageParame pp = new PageParame(result);
			List<Object> paramList=new ArrayList<Object>();//保存查询参数
			if(teachingNotice!=null)
			{
				if(teachingNotice.getTitle()!=null && !teachingNotice.getTitle().equals(""))
				{
					hqlSb.append(" and title like "+Constants.PLACEHOLDER);
					paramList.add('%'+teachingNotice.getTitle()+'%');
				}
				if(StringUtils.isNotBlank(teachingNotice.getContent()))
				{
					hqlSb.append(" and content like "+Constants.PLACEHOLDER);
					paramList.add('%'+teachingNotice.getContent()+'%');
				}
				if (teachingNotice.getPublishStartTime() ==null && teachingNotice.getPublishEndTime()!=null) {
					hqlSb.append( " and  publishTime <= "
							+ Constants.PLACEHOLDER);
					paramList.add(DateUtil.getDate(teachingNotice.getPublishEndTime(), "yyyy-MM-dd")+" 23:59:59");
				}
				if (teachingNotice.getPublishStartTime() !=null && teachingNotice.getPublishEndTime()==null) {
					hqlSb.append(" and  publishTime >="
							+ Constants.PLACEHOLDER);
					paramList.add(DateUtil.getDate(teachingNotice.getPublishStartTime(), "yyyy-MM-dd")+" 00:00:00");
				}
				if (teachingNotice.getPublishStartTime()!=null && teachingNotice.getPublishEndTime()!=null) {
					hqlSb.append( " and  publishTime>= "
							+ Constants.PLACEHOLDER + " and publishTime<="
							+ Constants.PLACEHOLDER);
					paramList.add(DateUtil.getDate(teachingNotice.getPublishStartTime(), "yyyy-MM-dd")+" 00:00:00");
					paramList.add(DateUtil.getDate(teachingNotice.getPublishEndTime(), "yyyy-MM-dd")+" 23:59:59");
				}
				hqlSb.append(" order by id desc");
				pp.setHqlConditionExpression(hqlSb.toString());
				pp.setValues(paramList.toArray());
			}
			
			Long[] teachingNoticeIds =teachingNoticeDao.getIDs(pp);
			if(teachingNoticeIds!=null && teachingNoticeIds.length!=0)
			{
				for(int i=0;i <teachingNoticeIds.length;i++){
					
					TeachingNotice  t=teachingNoticeDao.findById(Integer.parseInt(teachingNoticeIds[i].toString()));
					Academy academy = academyBiz.findAcademyById(t.getAcademyId());

					if (academy != null) {
						t.setSchoolName(academy.getName());
					}
					if(t!=null)
					{
						teachingNoticeList.add(t);
					}
				}
			}
			return teachingNoticeList;	
			
		}
		
		/**
		 * 增加
		 */
		public void addTeachingNotice(TeachingNotice teachingNotice) throws Exception {
			if(teachingNotice!=null)
			{
				teachingNoticeDao.save(teachingNotice);
			}
			
		}
		
		/**
		 * 按ID查询
		 */
		public TeachingNotice findTeachingNoticeById(int id) throws Exception {
			return teachingNoticeDao.findById(id);
		}
		
}
