package net.cedu.biz.academy.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.academy.AcademyCommunicationRecordBiz;
import net.cedu.biz.academy.AcademyLinkManBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.Constants;
import net.cedu.dao.academy.AcademyCommunicationRecordDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.AcademyCommunicationRecord;
import net.cedu.entity.academy.AcademyLinkMan;
import net.cedu.entity.admin.User;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 院校沟通记录业务逻辑
 * @author gaolei
 * */
@Service
public class AcademyCommunicationRecordBizImpl implements AcademyCommunicationRecordBiz {

	@Autowired
	private AcademyCommunicationRecordDao acrdao; //沟通记录接口
	@Autowired
	private AcademyLinkManBiz academylinkmanbiz;  //院校联系人接口
	@Autowired
	private UserBiz userbiz;                      //用户biz
	
	@Autowired
	private AcademyBiz academyBiz;                //院校biz

	
	  /*
	   * 新增沟通记录
	   * @see net.cedu.biz.academy.AcademyCommunicationRecordBiz#addAcademy(net.cedu.entity.academy.AcademyCommunicationRecord)
	   */
	   public boolean addAcademy(AcademyCommunicationRecord acr)throws Exception

	   {
			    boolean bol=false;
				acrdao.save(acr);
				bol=true;
				return bol;
		
	   }

		/*
		 * 删除沟通记录
		 * @see net.cedu.biz.academy.AcademyCommunicationRecordBiz#deleteAcademy(int)
		 */
		public boolean deleteAcademy(int id)throws Exception
		{
			
				acrdao.deleteById(id);
				return true;
			
		}
		
		/*
		 * 修改沟通记录
		 * @see net.cedu.biz.academy.AcademyCommunicationRecordBiz#updateAcademy(net.cedu.entity.academy.AcademyCommunicationRecord)
		 */
		public boolean updateAcademy(AcademyCommunicationRecord acr)throws Exception
		{
			
				acrdao.update(acr);
				return true;
			
		}
	 
		/*
		 *  查询沟通记录按Id
		 * @see net.cedu.biz.academy.AcademyCommunicationRecordBiz#findAcademyCommunicationRecordById(int)
		 */
		public AcademyCommunicationRecord findAcademyCommunicationRecordById(int id)throws Exception
		{
			return acrdao.findById(id);
		}
		
	 
	    /*
	     * 查询沟通记录条数 
	     * @see net.cedu.biz.academy.AcademyCommunicationRecordBiz#findAcademyCommunicationRecordCount(int, int, java.lang.String, java.lang.String, java.util.Date, net.cedu.model.page.PageResult)
	     */
		public int findAcademyCommunicationRecordCount(int academyId,int linkmanId,
			String subject, String result, String starttime,String endtime,
			PageResult<AcademyCommunicationRecord> pr) throws Exception{
			PageParame p = new PageParame(pr);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			String hqlparam="";
			String params="";
			if(linkmanId!=0)
			{
				hqlparam+=" and academyLinkmanId= "+Constants.PLACEHOLDER;
				params+=linkmanId+",";
			}
			if(subject!=null && !subject.equals(""))
			{
				hqlparam+=" and subject like "+Constants.PLACEHOLDER;
				params+="%"+subject+"%"+",";
			}
			if(result!=null && !result.equals(""))
			{
				hqlparam+=" and result like "+Constants.PLACEHOLDER;
				params+="%"+result+"%"+",";
			}
			if(starttime!=null && !starttime.equals(""))
			{
				hqlparam+=" and comtime >= "+Constants.PLACEHOLDER;
				params+=starttime+" 00:00:00"+",";
			}
			if(endtime!=null && !endtime.equals(""))
			{
				hqlparam+=" and comtime <= "+Constants.PLACEHOLDER;
				params+=endtime+" 23:59:59"+",";
			}
			if(academyId!=0){
				hqlparam+=" and academyId = "+Constants.PLACEHOLDER;
				params+=academyId+",";
			}
			hqlparam+=" and deleteFlag = "+Constants.PLACEHOLDER;
			params+=Constants.DELETE_FALSE;
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(hqlparam);
				p.setValues(params.split(","));
			}
			return acrdao.getCounts(p);
	}
	
		/*
		 * 查询沟通记录（分页） 
		 * @see net.cedu.biz.academy.AcademyCommunicationRecordBiz#findAcademyCommunicationRecordList(int, int, java.lang.String, java.lang.String, java.util.Date, net.cedu.model.page.PageResult)
		 */
		public List<AcademyCommunicationRecord> findAcademyCommunicationRecordList(
			int academyId,int linkmanId, String subject, String result, String starttime,String endtime,
			PageResult<AcademyCommunicationRecord> pr)throws Exception {
		
		
			List<AcademyCommunicationRecord> acrlst = null;
			PageParame p = new PageParame(pr);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			String hqlparam="";
			String params="";
			if(linkmanId!=0)
			{
				hqlparam+=" and academyLinkmanId= "+Constants.PLACEHOLDER;
				params+=linkmanId+",";
			}
			if(subject!=null && !subject.equals(""))
			{
				hqlparam+=" and subject like "+Constants.PLACEHOLDER;
				params+="%"+subject+"%"+",";
			}
			if(result!=null && !result.equals(""))
			{
				hqlparam+=" and result like "+Constants.PLACEHOLDER;
				params+="%"+result+"%"+",";
			}
			if(starttime!=null && !starttime.equals(""))
			{
				hqlparam+=" and comtime >= "+Constants.PLACEHOLDER;
				params+=starttime+" 00:00:00"+",";
			}
			if(endtime!=null && !endtime.equals(""))
			{
				hqlparam+=" and comtime <= "+Constants.PLACEHOLDER;
				params+=endtime+" 23:59:59"+",";
			}
			if(academyId!=0){
				hqlparam+=" and academyId = "+Constants.PLACEHOLDER;
				params+=academyId+",";
			}
			hqlparam+=" and deleteFlag = "+Constants.PLACEHOLDER;
			params+=Constants.DELETE_FALSE;
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(hqlparam);
				p.setValues(params.split(","));
			}
			
			Long[] communicationIds = acrdao.getIDs(p);
			
			if (communicationIds != null && communicationIds.length != 0) {
				acrlst=new ArrayList<AcademyCommunicationRecord>();
				for (int i = 0; i < communicationIds.length; i++) {
					// 内存获取
					AcademyCommunicationRecord acr=acrdao.findById(Integer.valueOf(communicationIds[i].toString()));
					if(acr!=null)
					{
						AcademyCommunicationRecord acrs=acr;
						User user=userbiz.findUserById(acr.getCommunicationUserId());
						if(user!=null)
						{
							acrs.setCommunicationUserName(user.getFullName());
						}
						AcademyLinkMan alm=academylinkmanbiz.findAcademyLinkManId(acrs.getAcademyLinkmanId());
						if(alm!=null)
						{
							acrs.setAcademylinkman(alm.getName());
						}
						//院校
						Academy academy=this.academyBiz.findAcademyById(acr.getAcademyId());
						if(academy!=null)
						{
							acrs.setAcademyName(academy.getName());
						}
						
						acrlst.add(acrs);
					}
				}	
			}
			return acrlst;
	
	}
	
	
}
