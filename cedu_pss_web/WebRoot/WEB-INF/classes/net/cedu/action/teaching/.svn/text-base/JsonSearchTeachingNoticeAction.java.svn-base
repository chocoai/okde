package net.cedu.action.teaching;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.teaching.TeachingNoticeBiz;
import net.cedu.common.Constants;
import net.cedu.entity.teaching.TeachingNotice;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 教学公告查询列表（条件查询）
 * @author wangmingjie
 *
 */
@ParentPackage("json-default")
public class JsonSearchTeachingNoticeAction extends BaseAction {
	@Autowired
	private  TeachingNoticeBiz  teachingNoticeBiz;
	// 分页结果
	private PageResult<TeachingNotice> result = new PageResult<TeachingNotice>();
	TeachingNotice  teachingNotice=new TeachingNotice();
	
	
	/**
	 * 查询教学公告数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_teaching_notice_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String teachingNoticeCount() throws Exception 
	{
		result.setRecordCount(teachingNoticeBiz.findTeachingNoticePageCountByConditions(result, teachingNotice));
		return SUCCESS;
	}
	
	/**
	 * 查询教学公告列表集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_teaching_notice_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String teachingNoticeList() throws Exception
	{
		result.setList(teachingNoticeBiz.findTeachingNoticePageListByConditions(result, teachingNotice));
		return SUCCESS;
	}
	
	
	/**
	 * 添加教学公告
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_teaching_notice_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String addTeachingNotice() throws Exception
	{
		System.out.println(teachingNotice.getAcademyId()+"   aaaaaaaaaaaaaaa");
		teachingNotice.setPublisher(super.getUser().getUserId());
		teachingNotice.setPublishTime(new Date());
		teachingNotice.setDeleteFlag(Constants.DELETE_FALSE);
		teachingNotice.setCreatorId(super.getUser().getUserId());
		teachingNotice.setCreatedTime(new Date());
		teachingNotice.setUpdaterId(super.getUser().getUserId());
		teachingNotice.setUpdatedTime(new Date());
		teachingNoticeBiz.addTeachingNotice(teachingNotice);
		return SUCCESS;
	}

	public PageResult<TeachingNotice> getResult() {
		return result;
	}
	public void setResult(PageResult<TeachingNotice> result) {
		this.result = result;
	}
	public TeachingNotice getTeachingNotice() {
		return teachingNotice;
	}
	public void setTeachingNotice(TeachingNotice teachingNotice) {
		this.teachingNotice = teachingNotice;
	}

	
	
}
