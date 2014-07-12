package net.cedu.biz.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.finance.InvoiceManagementBiz;
import net.cedu.biz.finance.PostalParcelBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.StringUtil;
import net.cedu.dao.finance.InvoiceManagementDao;
import net.cedu.dao.finance.PostalParcelDao;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.finance.InvoiceManagement;
import net.cedu.entity.finance.PostalParcel;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 邮寄包  业务逻辑实现
 * 
 * @author gaole
 *
 */

@Service
public class PostalParcelBizImpl implements PostalParcelBiz {

	
	@Autowired
	private  PostalParcelDao  postalParcelDao;      //邮寄包接口
	@Autowired
	private  BranchBiz  branchbiz;                  //学习中心Biz
	@Autowired
	private  InvoiceManagementBiz  invoicemanagementbiz;   //发票Biz
	@Autowired
	private  InvoiceManagementDao  invoiceManagementDao;   //发票Dao

	
	/*
	 * 添加邮寄单
	 * @see net.cedu.biz.finance.PostalParcelBiz#addPostalParcel(net.cedu.entity.finance.PostalParcel)
	 */
	public boolean addPostalParcel(PostalParcel postalparcel) throws Exception {
		postalParcelDao.save(postalparcel);
		return true;
	}

	
	/*
	 * 查询邮寄单(分页数量)
	 * @see net.cedu.biz.finance.PostalParcelBiz#countPostalParcelByBranchId(int, net.cedu.model.page.PageResult)
	 */
	public int countPostalParcelByBranchId(int branchId,String statusIds,
			PageResult<PostalParcel> pr) throws Exception {
	
		// Ids集合
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (branchId != 0) {
			hqlparam += " and  branchId= " + Constants.PLACEHOLDER;
			list.add(branchId);
		}
		if (statusIds != null) {
			hqlparam += " and  status in ( " + Constants.PLACEHOLDER+")";
			list.add("$"+statusIds);
		}
		hqlparam+=" and deleteFlag=";
		list.add(Constants.DELETE_FALSE);
		
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return postalParcelDao.getCounts(p);
		
	}

	/*
	 * 查询邮寄单(分页集合)
	 * @see net.cedu.biz.finance.PostalParcelBiz#findPostalParcelByBranchId(int, net.cedu.model.page.PageResult)
	 */
	public List<PostalParcel> findPostalParcelByBranchId(int branchId,String statusIds,
			PageResult<PostalParcel> pr) throws Exception {
		List<PostalParcel> postalparcels = null;
		// Ids集合
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (branchId != 0) {
			hqlparam += " and  branchId= " + Constants.PLACEHOLDER;
			list.add(branchId);
		}
		if (statusIds != null) {
			hqlparam += " and  status in ( " + Constants.PLACEHOLDER+")";
			list.add("$"+statusIds);
		}
		hqlparam+=" and deleteFlag=";
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] postalparcelIds = postalParcelDao.getIDs(p);
		if (postalparcelIds != null && postalparcelIds.length != 0) {
			postalparcels = new ArrayList<PostalParcel>();
			for (int i = 0; i < postalparcelIds.length; i++) {
				PostalParcel pp = this.findPostalParcelById(Integer.valueOf(postalparcelIds[i]
						.toString()));
				PostalParcel postalparcel = pp;
				Branch branch=branchbiz.findBranchById(postalparcel.getBranchId());
				if(branch!=null)
				{
					postalparcel.setBranchName(branch.getName());	
				}
				
				Object[] invoiceobj=StringUtil.strToObject(postalparcel.getInvoiceIds());
				int stuSignCount=0;
				if(invoiceobj!=null && invoiceobj.length>0)
				{
					postalparcel.setInvoiceNumber(invoiceobj.length);
					
					for(int j=0;j<invoiceobj.length;j++)
					{
						InvoiceManagement invoicemanagement=invoicemanagementbiz.findInvoiceManagementById(Integer.valueOf(invoiceobj[j].toString()));
						if(invoicemanagement!=null)
						{
							if(invoicemanagement.getIsSign()==1)
							{
								stuSignCount++;
							}
						}
					}
				}
				postalparcel.setStuSignNumber(stuSignCount);
				postalparcel.setStuSignNoNumber(postalparcel.getInvoiceNumber()-postalparcel.getStuSignNumber());
				postalparcels.add(postalparcel);
			}
		}
		return postalparcels;
	}
	/*
	 * 查询邮寄单按Id
	 * @see net.cedu.biz.finance.PostalParcelBiz#findPostalParcelById(int)
	 */
	public PostalParcel findPostalParcelById(int id) throws Exception {
		
		return postalParcelDao.findById(id);
	}


	/*
	 * 修改邮寄单
	 * @see net.cedu.biz.finance.PostalParcelBiz#updatePostalParcel(net.cedu.entity.finance.PostalParcel)
	 */
	public boolean updatePostalParcel(PostalParcel postalparcel)
			throws Exception {
		postalParcelDao.update(postalparcel);
		return true;
	}


	/*
	 * 查询邮寄单按Id
	 * @see net.cedu.biz.finance.PostalParcelBiz#findPostalParcelByCode(java.lang.String)
	 */
	public List<PostalParcel> findPostalParcelsByCode(String code) throws Exception {
	
		return postalParcelDao.getByProperty(" and code like "+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER,"%"+code+"%",Constants.DELETE_FALSE);
	}


	/**
	 * 通过学习中心ID获取邮寄单邮寄过来的发票IDs（邮寄单已签收）
	  * @see net.cedu.biz.finance.PostalParcelBiz#findPostalParcelInvoiceIdsByBranchId(int)
	 */
	public String findPostalParcelInvoiceIdsByBranchId(int branchId)
			throws Exception {
		//已配送,未签收
		List<PostalParcel> postalParcelList=postalParcelDao.getByProperty(" and status = 2 and branchId="+Constants.PLACEHOLDER, new Object[]{branchId});
		String ids=",";
		if(postalParcelList!=null&&postalParcelList.size()!=0){
			for (PostalParcel postalParcel : postalParcelList) {
				if(postalParcel.getInvoiceIds()!=null){
					String [] array=postalParcel.getInvoiceIds().split("_");
					for (String string : array) {
						if(string!=null&&!string.equals("")){
							if(ids.equals(",")){
								ids=string;
							}else{
								ids+=","+string;
							}
						}
					}
				}
			}
		}
		
		Long [] idArray=invoiceManagementDao.getIDs(" and registrationInvoiceType=2 and branchId="+Constants.PLACEHOLDER, new Object[]{branchId});
		if(idArray!=null){
			for (Long l : idArray) {
				if(ids.equals(",")){
					ids=""+l;
				}else{
					ids+=","+l;
				}
			}
		}
		
		
		if(ids.equals(",")){
			ids="0";
		}
		return ids;
	}

	
	
}
