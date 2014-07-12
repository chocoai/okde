package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.UserEnrollQuotaBiz;
import net.cedu.common.Constants;
import net.cedu.dao.crm.StudentDao;
import net.cedu.dao.enrollment.UserEnrollQuotaDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.User;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.enrollment.UserEnrollQuota;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEnrollQuotaBizImpl implements UserEnrollQuotaBiz {

	@Autowired
	private UserEnrollQuotaDao userenrollquotaDao;    //中心人员指标接口
	@Autowired
	private AcademyBatchBranchBiz academybatchbranchBiz;  //院校授权学习中心Biz
	@Autowired
	private UserBiz userBiz;                          //用户Biz
	@Autowired
	private StudentBiz studentBiz;                    //学生Biz
	@Autowired
	private StudentDao studentDao;					  //学生Dao
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz; //全局批次biz
	/*
	 * 添加中心人员指标
	 * 
	 * @see net.cedu.biz.enrollment.UserEnrollQuotaBiz#addUserEnrollQuota(int,
	 * int, int, int,int ,int, int)
	 */
	public boolean addUserEnrollQuota(int batchId, int branchId, int academyId,
			int target, int completed, int userId, int managerId)
			throws Exception {
		UserEnrollQuota ueq = null;
		if (batchId != 0 && branchId != 0 && academyId != 0 && userId != 0) {
			this.deleteUserEnrollQuotaBybranchIdAndAcademyId(batchId, branchId,
					academyId, userId);
			ueq = new UserEnrollQuota();
			ueq.setBatchId(batchId);
			ueq.setBranchId(branchId);
			ueq.setAcademyId(academyId);
			ueq.setUserId(userId);
			ueq.setTarget(target);
			ueq.setExpectTarget(completed);
			ueq.setCreatorId(managerId);
			ueq.setCreatedTime(new Date());
			userenrollquotaDao.save(ueq);
			return true;
		}
		return false;
	}

	/*
	 * 删除中心人员指标
	 * 
	 * @seenet.cedu.biz.enrollment.UserEnrollQuotaBiz#
	 * deleteUserEnrollQuotaBybranchIdAndAcademyId(int, int, int, int)
	 */
	public boolean deleteUserEnrollQuotaBybranchIdAndAcademyId(int batchId,
			int branchId, int academyId, int userId) throws Exception {

		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		hqlparam += " and batchId=" + Constants.PLACEHOLDER;
		list.add(batchId);
		hqlparam += " and branchId=" + Constants.PLACEHOLDER;
		list.add(branchId);
		hqlparam += " and academyId=" + Constants.PLACEHOLDER;
		list.add(academyId);
		hqlparam += " and userId=" + Constants.PLACEHOLDER;
		list.add(userId);
		userenrollquotaDao.deleteByProperty(hqlparam, list);
		return true;
	}

	/*
	 * 查询中心人员分配指标按批次
	 * 
	 * @seenet.cedu.biz.enrollment.UserEnrollQuotaBiz#
	 * findUserEnrollQuotaByBatchIdAndBranchId(int, int,
	 * net.cedu.model.page.PageResult)
	 */
	public List<User> findUserEnrollQuotaByBatchIdAndBranchId(int batchId,
			int branchId, PageResult<User> pr) throws Exception {
		List<User> userlst = null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (branchId != 0) {
			hqlparam += " and orgId=" + Constants.PLACEHOLDER;
			list.add(branchId);
		}
		//未删除 和 启用状态的用户
		hqlparam += " and deleteFlag=" + Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		hqlparam += " and status=" + Constants.PLACEHOLDER;
		list.add(0);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());

		Long[] userIds = userBiz.findUserIds(p);

		
		if (userIds != null && userIds.length != 0) {
			userlst = new ArrayList<User>();
			List<Academy> academylst = academybatchbranchBiz
					.findAcademyByBranchIdAndGlobalBatchId(branchId, batchId);
			//获取当前批次的全部指标Map key:branchId_academyId_userId value:target_expectTarget
			Map<String,String> ueqMap = userenrollquotaDao.findUserEnrollQuotaMapByBatchIdAndBranchId(batchId,branchId);
			//获取当前批次的全部完成指标数Map key:branchId_academyId_userId value:完成指标数
			Map<String,Integer> comMap = studentDao.getCompleteCountAllByGlobalEnrollBatchIdAndBranchId(batchId,branchId);
			for (int i = 0; i < userIds.length; i++) {
				User userinfo = userBiz.findUserById(Integer.valueOf(userIds[i]
						.toString()));
				List<Academy> alist = new ArrayList<Academy>();
				if (academylst != null && academylst.size() > 0) {

					for (int j = 0; j < academylst.size(); j++) {
						Academy aca = entityGouZao(academylst.get(j));
						//取消一些不必要的数据在页面上
						aca.setIntroduction("");
						aca.setAddress("");
						
//						UserEnrollQuota ueq = this.findUserEnrollQuotaByAcademyId(aca.getId(),userinfo.getId(),branchId);
//						if (ueq != null) {
//							aca.setTarget(ueq.getTarget());
//							aca.setExpectTarget(ueq.getExpectTarget());
//							// 完成数量
//							aca.setComplete(studentBiz.findStudentCount(
//									batchId, aca.getId(), userinfo.getId()));
//						}
						String targetStr = ueqMap.get(branchId+"_"+aca.getId()+"_"+userinfo.getId());
						if(targetStr!=null)
						{
							aca.setTarget(Integer.parseInt(targetStr.substring(0,targetStr.indexOf("_"))));
							aca.setExpectTarget(Integer.parseInt(targetStr.substring(targetStr.indexOf("_")+1)));
						}
						else
						{
							aca.setTarget(0);
							aca.setExpectTarget(0);
						}
						//aca.setComplete(studentBiz.findStudentCount(batchId, aca.getId(), userinfo.getId()));
						Integer completeInt = comMap.get(branchId+"_"+aca.getId()+"_"+userinfo.getId());
						aca.setComplete(completeInt==null?0:completeInt);
						alist.add(aca);
					}
					userinfo.setAcademylst(alist);
				}
				userlst.add(userinfo);
			}
		}

		return userlst;
	}

	/**
	 * 
	 * @param academy
	 * @return
	 */
	private Academy entityGouZao(Academy academy) {
		Academy a = new Academy();
		a.setId(academy.getId());
		a.setCode(academy.getCode());
		a.setName(academy.getName());
		a.setPictureUrl(academy.getPictureUrl());
		a.setShortName(academy.getShortName());
		a.setFoundedYear(academy.getFoundedYear());
		a.setWebsite(academy.getWebsite());
		a.setScale(academy.getScale());
		a.setIntroduction(academy.getIntroduction());
		a.setAuditStatus(academy.getAuditStatus());
		a.setUsingStatus(academy.getUsingStatus());
		a.setContractEndtime(academy.getContractEndtime());
		a.setAuditer(academy.getAuditer());
		a.setAuditedDate(academy.getAuditedDate());
		a.setInterfaceSettingStatus(academy.getInterfaceSettingStatus());
		a.setAddress(academy.getAddress());
		a.setAccount(academy.getAccount());
		a.setIsForceFeePolicy(academy.getIsForceFeePolicy());
		a.setDeleteFlag(academy.getDeleteFlag());
		a.setCreatorId(academy.getCreatorId());
		a.setCreatedTime(academy.getCreatedTime());
		a.setUpdaterId(academy.getUpdaterId());
		a.setUpdatedTime(academy.getUpdatedTime());
		a.setTelephone(academy.getTelephone());
		a.setRebatesFeesubject(academy.getRebatesFeesubject());
		a.setProjectManagerName(academy.getProjectManagerName());
		a.setProjectManagerId(academy.getProjectManagerId());
		a.setTarget(academy.getTarget());
		a.setExpectTarget(academy.getExpectTarget());
		return a;
	}

	/*
	 * 查询中心人员指标按院校Id
	 * 
	 * @see
	 * net.cedu.biz.enrollment.UserEnrollQuotaBiz#findUserEnrollQuotaByAcademyId
	 * (int)
	 */
	public UserEnrollQuota findUserEnrollQuotaByAcademyId(int academy,
			int userId) throws Exception {
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (academy != 0) {
			hqlparam += " and academyId=" + Constants.PLACEHOLDER;
			list.add(academy);
		}
		if (userId != 0) {
			hqlparam += " and userId=" + Constants.PLACEHOLDER;
			list.add(userId);
		}
		List<UserEnrollQuota> UserEnrollQuotalst = userenrollquotaDao
				.getByProperty(hqlparam, list);
		if (UserEnrollQuotalst != null && UserEnrollQuotalst.size() > 0) {
			return UserEnrollQuotalst.get(0);
		}
		return null;
	}
	
	/*
	 * 查询中心人员指标按院校Id按中心id
	 * 
	 * @see
	 * net.cedu.biz.enrollment.UserEnrollQuotaBiz#findUserEnrollQuotaByAcademyId
	 * (int,int,int)
	 */
	public UserEnrollQuota findUserEnrollQuotaByAcademyId(int academy,
			int userId,int branchId, int batchId) throws Exception {
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (academy != 0) {
			hqlparam += " and academyId=" + Constants.PLACEHOLDER;
			list.add(academy);
		}
		if (userId != 0) {
			hqlparam += " and userId=" + Constants.PLACEHOLDER;
			list.add(userId);
		}
		if (branchId !=0) {
			hqlparam += " and branchId=" + Constants.PLACEHOLDER;
			list.add(branchId);
		}
		if (batchId !=0) {
			hqlparam += " and batchId=" + Constants.PLACEHOLDER;
			list.add(batchId);
		}
		List<UserEnrollQuota> UserEnrollQuotalst = userenrollquotaDao
				.getByProperty(hqlparam, list);
		System.out.println(hqlparam);
		if (UserEnrollQuotalst != null && UserEnrollQuotalst.size() > 0) {
			return UserEnrollQuotalst.get(0);
		}
		return null;
	}

	/*
	 * 查询中心人员分配指标按批次用户Id
	 * 
	 * @seenet.cedu.biz.enrollment.UserEnrollQuotaBiz#
	 * findUserEnrollQuotaByBatchIdAndUserId(int, int,
	 * net.cedu.model.page.PageResult)
	 */
	public List<User> findUserEnrollQuotaByBatchIdAndUserId(int batchId,
			int userId, PageResult<User> pr) throws Exception {

		List<User> userlst = null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (userId != 0) {
			hqlparam += " and  id=" + Constants.PLACEHOLDER;
			list.add(userId);
		}
		hqlparam += " and deleteFlag=" + Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());

		Long[] userIds = userBiz.findUserIds(p);

		if (userIds != null && userIds.length != 0) {
			userlst = new ArrayList<User>();

			for (int i = 0; i < userIds.length; i++) {
				User userinfo = userBiz.findUserById(Integer.valueOf(userIds[i]
						.toString()));
				List<Academy> academylst = academybatchbranchBiz
						.findAcademyByBranchIdAndGlobalBatchId(userinfo
								.getOrgId(), batchId);
				List<Academy> alist = new ArrayList<Academy>();
				if (academylst != null && academylst.size() > 0) {
					for (int j = 0; j < academylst.size(); j++) {
						Academy aca = entityGouZao(academylst.get(j));
						UserEnrollQuota tempUeq = findUserEnrollQuotaByAcademyId(aca.getId(),userinfo.getId(),userinfo.getOrgId(),batchId);
						if (tempUeq != null) {
							aca.setTarget(tempUeq.getTarget());
							aca.setExpectTarget(tempUeq.getExpectTarget());
							// 完成数量
							aca.setComplete(studentBiz.findStudentCount(
									batchId, aca.getId(), userId));

						}
						alist.add(aca);
					}
					userinfo.setAcademylst(alist);
				}
				userlst.add(userinfo);
			}
		}

		return userlst;
	}

	/*
	 * 查询中心人员指标总数按用户Id(并且按用户所属中心id)
	 * @see net.cedu.biz.enrollment.UserEnrollQuotaBiz#findUserEnrollQuotaSumByUserId(int)
	 */
	public int findUserEnrollQuotaSumByUserId(int userId) throws Exception {
		return userenrollquotaDao.findUserEnrollQuotaSumByUserId(userId);
	}

	/*
	 * 通过条件查询中心人员指标集合(删除用户功能的查询方法)
	 * @see net.cedu.biz.enrollment.UserEnrollQuotaBiz#findUserEnrollQuotaListByParams(net.cedu.entity.enrollment.UserEnrollQuota, int)
	 */
	public List<UserEnrollQuota> findUserEnrollQuotaListByParams(
			UserEnrollQuota userEnrollQuota,int count) throws Exception {
		List<UserEnrollQuota> userEnrollQuotasList = null;
		PageResult<UserEnrollQuota> pr = new PageResult<UserEnrollQuota>();
		pr.setPageSize(count);
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = null;
		if(userEnrollQuota!=null){
			list = new ArrayList<Object>();
			// 人员
			if(userEnrollQuota.getUserId()!=0){
				hqlparam += " and userId = " + Constants.PLACEHOLDER;
				list.add(userEnrollQuota.getUserId());
			}
			// 中心
			if(userEnrollQuota.getBranchId()!=0){
				hqlparam += " and branchId = " + Constants.PLACEHOLDER;
				list.add(userEnrollQuota.getBranchId());
			}
			// 指标不为0
			hqlparam += " and ( target > 0 or expect_target > 0 )";
			// 未删除
			hqlparam += " and deleteFlag = " + Constants.PLACEHOLDER;
			list.add(Constants.DELETE_FALSE);
			// 按批次分组
			hqlparam += " group by batchId ";
			
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
			Long[] userEnrollQuotaIds = userenrollquotaDao.getIDs(p);
			if (userEnrollQuotaIds != null && userEnrollQuotaIds.length != 0){
				userEnrollQuotasList = new ArrayList<UserEnrollQuota>();
				UserEnrollQuota u = null;
				for(Long id : userEnrollQuotaIds){
					u = userenrollquotaDao.findById(id);
					if(u!=null){
						if(u.getBatchId()!=0){
							GlobalEnrollBatch globalEnrollBatch = globalEnrollBatchBiz.findGlobalEnrollBatchById(u.getBatchId());
							u.setBatchName(globalEnrollBatch==null?"批次:"+u.getBatchId()+"有误，请找相关技术人员处理":globalEnrollBatch.getBatch());
						}
						userEnrollQuotasList.add(u);
					}
				}
				return userEnrollQuotasList;
			}
		}
		return null;
	}

	/*
	 * 通过条件删除中心人员指标(删除用户功能用的方法)
	 * @see net.cedu.biz.enrollment.UserEnrollQuotaBiz#deleteUserEnrollQuotaByParams(net.cedu.entity.enrollment.UserEnrollQuota)
	 */
	public void deleteUserEnrollQuotaByParams(UserEnrollQuota userEnrollQuota) throws Exception {
		String hqlparam = "";
		List<Object> list = null;
		if(userEnrollQuota!=null){
			list = new ArrayList<Object>();
			if(userEnrollQuota.getUserId()!=0){
				hqlparam += " and userId = " + Constants.PLACEHOLDER;
				list.add(userEnrollQuota.getUserId());
			}
			
			if(!hqlparam.equals("")){
				userenrollquotaDao.deleteByProperty(hqlparam, list);
			}
		}
	}

}
