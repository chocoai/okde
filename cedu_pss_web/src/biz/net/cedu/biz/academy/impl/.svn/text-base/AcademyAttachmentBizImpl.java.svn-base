package net.cedu.biz.academy.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyAttachmentBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.Constants;
import net.cedu.dao.academy.AcademyAttachmentDao;
import net.cedu.entity.academy.AcademyAttachment;
import net.cedu.entity.admin.User;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 附件业务逻辑
 * @author gaolei
 * */
@Service
public class AcademyAttachmentBizImpl implements  AcademyAttachmentBiz {

	@Autowired
	private  AcademyAttachmentDao  academyattachmentdao;   //院校附件接口
	@Autowired
	private UserBiz userbiz;                               //用户biz
	
	
	/*
	 * 添加附件
	 * @see net.cedu.biz.academy.AcademyAttachmentBiz#addAcademyAttachment(net.cedu.entity.academy.AcademyAttachment)
	 */
	public boolean addAcademyAttachment(AcademyAttachment academyattachment)throws Exception {
				academyattachmentdao.save(academyattachment);
			    return true;
	
	}
	
	/*
	 * 查看附件按ID
	 * @see net.cedu.biz.academy.AcademyAttachmentBiz#findAcademyAttachmentById(int)
	 */
	public AcademyAttachment findAcademyAttachmentById(int id)throws Exception
	{
		return academyattachmentdao.findById(id);
	}

	/*
	 * 查询附件总数量
	 * @see net.cedu.biz.academy.AcademyAttachmentBiz#findAllAcademyAttachmentCount(int, net.cedu.model.page.PageResult)
	 */
	public int findAllAcademyAttachmentCount(int academyId,PageResult<AcademyAttachment> pr)throws Exception
	{
		PageParame p = new PageParame(pr);
		p.setHqlConditionExpression(" and deleteFlag = "+Constants.PLACEHOLDER+" and academyId = "+Constants.PLACEHOLDER);
		p.setValues(new Object[]{Constants.DELETE_FALSE,academyId});
		
		return academyattachmentdao.getCounts(p);
		
	}
	
	
	
	/*
	 * 查询附件信息分页
	 * @see net.cedu.biz.academy.AcademyAttachmentBiz#findAllAcademyAttachment(int, net.cedu.model.page.PageResult)
	 */
	public List<AcademyAttachment> findAllAcademyAttachment(int academyId, PageResult<AcademyAttachment> pr)throws Exception
	{
		
			List<AcademyAttachment> academyattachmentlst = null;
			
			PageParame p = new PageParame(pr);
			p.setHqlConditionExpression(" and deleteFlag = "+Constants.PLACEHOLDER+" and academyId = "+Constants.PLACEHOLDER);
			p.setValues(new Object[]{Constants.DELETE_FALSE,academyId});
			// Ids集合
			Long[] academyIds = academyattachmentdao.getIDs(p);
			if (academyIds != null && academyIds.length != 0) {
				academyattachmentlst = new ArrayList<AcademyAttachment>();
				for (int i = 0; i < academyIds.length; i++) {
					AcademyAttachment  academyattachments= academyattachmentdao.findById(Integer.parseInt(academyIds[i].toString()));
					if (academyattachments != null) {
						AcademyAttachment academyattachmentobj=academyattachments;
						User user=userbiz.findUserById(academyattachmentobj.getUploaderUid());
						if(user!=null)
						{
							academyattachmentobj.setUserName(user.getFullName());
						}
						academyattachmentlst.add(academyattachmentobj);
					}
				}
			}

			return academyattachmentlst;
		

	
	}
	
	/*
	 * 删除附件
	 * @see net.cedu.biz.academy.AcademyAttachmentBiz#deleteAcademyAttachment(int)
	 */
	public boolean deleteAcademyAttachment(int id)throws Exception
	{
		
			Object obj= academyattachmentdao.deleteById(id);
			
			if(obj!=null)
			{
				return true;
			}else
			{
				return false;
			}
		
	}
	
	/*
	 * 修改附件
	 * @see net.cedu.biz.academy.AcademyAttachmentBiz#updateAcademyAttachment(net.cedu.entity.academy.AcademyAttachment)
	 */
	public boolean updateAcademyAttachment(AcademyAttachment academyattachment)throws Exception
	{
		
			
			Object obj= academyattachmentdao.update(academyattachment);
			if(obj!=null)
			{
				return true;
			}else
			{
				return false;
			}
		
	}

	

	
	
	
}
