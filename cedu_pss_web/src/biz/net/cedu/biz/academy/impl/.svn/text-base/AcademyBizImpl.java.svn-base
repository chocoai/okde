package net.cedu.biz.academy.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.Constants;
import net.cedu.dao.academy.AcademyDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.User;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校业务逻辑 gaolei
 * */
@Service
public class AcademyBizImpl implements AcademyBiz {

	@Autowired
	private AcademyDao academydao; // 院校接口
	@Autowired
	private UserBiz userBiz; // 用户Biz

	/*
	 * 获取院校IDs
	 * 
	 * @see
	 * net.cedu.biz.academy.AcademyBiz#getAcademyIds(net.cedu.model.page.PageParame
	 * )
	 */
	public Long[] getAcademyIds(PageParame pageParame) throws Exception {
		return academydao.getIDs(pageParame);
	}

	/*
	 * 查询所有院校
	 * 
	 * @see net.cedu.biz.academy.AcademyBiz#findAllAcademys()
	 */
	public List<Academy> findAllAcademys() throws Exception {

		return academydao.findAll();
	}

	/*
	 * 查询所有院校（逻辑删除）（已启用）
	 * 
	 * @see net.cedu.biz.academy.AcademyBiz#findAllAcademyByFlagFalse()
	 */
	public List<Academy> findAllAcademyByFlagFalse() throws Exception {
		return academydao.getByProperty(" and deleteFlag="
				+ Constants.PLACEHOLDER + " and usingStatus="
				+ Constants.PLACEHOLDER, Constants.DELETE_FALSE,
				Constants.STATUS_ENABLED);
	}

	/*
	 * 查询所有院校（非逻辑删除）（已启用）
	 * 
	 * @see net.cedu.biz.academy.AcademyBiz#findAllAcademyByFlagTrue()
	 */
	public List<Academy> findAllAcademyByFlagTrue() throws Exception {

		return academydao.getByProperty(" and deleteFlag="
				+ Constants.PLACEHOLDER + " and usingStatus="
				+ Constants.PLACEHOLDER, Constants.DELETE_TRUE,
				Constants.STATUS_ENABLED);
	}

	/*
	 * 院校新增
	 * 
	 * @see
	 * net.cedu.biz.academy.AcademyBiz#addAcademy(net.cedu.entity.academy.Academy
	 * )
	 */
	public boolean addAcademy(Academy academy) throws Exception {
		academydao.save(academy);
		return true;
	}

	/*
	 * 查询院校按主键ID
	 * 
	 * @see net.cedu.biz.academy.AcademyBiz#findAcademyById(int)
	 */
	public Academy findAcademyById(int id) throws Exception {
		return academydao.findById(id);
	}

	/*
	 * 查询院校总数量
	 * 
	 * @see
	 * net.cedu.biz.academy.AcademyBiz#findAllAcademyCount(net.cedu.model.page
	 * .PageResult)
	 */
	public int findAllAcademyCount(PageResult<Academy> pr) throws Exception {
		PageParame p = new PageParame(pr);

		p.setHqlConditionExpression(" and deleteFlag = "
				+ Constants.PLACEHOLDER);
		p.setValues(new Object[] { Constants.DELETE_FALSE });
		return academydao.getCounts(p);
	}

	/*
	 * 查询院校信息分页
	 * 
	 * @see
	 * net.cedu.biz.academy.AcademyBiz#findAllAcademy(net.cedu.model.page.PageResult
	 * )
	 */
	public List<Academy> findAllAcademy(PageResult<Academy> pr)
			throws Exception {

		List<Academy> academys = null;
		// Ids集合
		PageParame p = new PageParame(pr);
		p.setHqlConditionExpression(" and deleteFlag = "
				+ Constants.PLACEHOLDER);
		p.setValues(new Object[] { Constants.DELETE_FALSE });
		Long[] academyIds = academydao.getIDs(p);
		if (academyIds != null && academyIds.length != 0) {
			academys = new ArrayList<Academy>();
			for (int i = 0; i < academyIds.length; i++) {
				Academy academy = academydao.findById(Integer
						.parseInt(academyIds[i].toString()));
				if (academy != null) {
					Academy academyobj = academy;
					User user = userBiz.findUserById(academyobj
							.getProjectManagerId());
					if (user != null) {
						academyobj.setProjectManagerName(user.getFullName());
					}
					academys.add(academyobj);
				}
			}
		}

		return academys;

	}

	/*
	 * 删除院校
	 * 
	 * @see net.cedu.biz.academy.AcademyBiz#deleteAcademy(int)
	 */
	public boolean deleteAcademy(int id) throws Exception {

		Object obj = academydao.deleteConfig(id);
		if (obj != null) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * 修改院校
	 * 
	 * @see
	 * net.cedu.biz.academy.AcademyBiz#updateAcademy(net.cedu.entity.academy
	 * .Academy)
	 */
	public boolean updateAcademy(Academy academy) throws Exception {

		Object obj = academydao.update(academy);
		if (obj != null) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * 查询院校总数量未审核
	 * 
	 * @see
	 * net.cedu.biz.academy.AcademyBiz#findAllAcademyByAuditStatus(net.cedu.
	 * model.page.PageResult)
	 */
	public List<Academy> findAllAcademyByAuditStatus(PageResult<Academy> pr)
			throws Exception {
		List<Academy> academys = null;
		// Ids集合
		PageParame p = new PageParame(pr);
		p.setHqlConditionExpression("and audit_status>" + Constants.PLACEHOLDER
				+ " and deleteFlag = " + Constants.PLACEHOLDER);
		p.setValues(new Object[] { Constants.AUDIT_STATUS_INIT,
				Constants.DELETE_FALSE });
		Long[] academyIds = academydao.getIDs(p);
		if (academyIds != null && academyIds.length != 0) {
			academys = new ArrayList<Academy>();
			for (int i = 0; i < academyIds.length; i++) {
				Academy academy = academydao.findById(Integer
						.parseInt(academyIds[i].toString()));
				if (academy != null) {
					Academy academyobj = academy;
					User user = userBiz.findUserById(academyobj
							.getProjectManagerId());
					if (user != null) {
						academyobj.setProjectManagerName(user.getFullName());
					}
					academys.add(academyobj);
				}
			}
		}

		return academys;
	}

	/*
	 * 查询院校信息分页未审核
	 * 
	 * @see
	 * net.cedu.biz.academy.AcademyBiz#findAllAcademyCountByAuditStatus(net.
	 * cedu.model.page.PageResult)
	 */
	public int findAllAcademyCountByAuditStatus(PageResult<Academy> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		p.setHqlConditionExpression("and audit_status>" + Constants.PLACEHOLDER
				+ " and deleteFlag = " + Constants.PLACEHOLDER);
		p.setValues(new Object[] { Constants.AUDIT_STATUS_INIT,
				Constants.DELETE_FALSE });
		return academydao.getCounts(p);
	}

	/*
	 * 院校重复校验
	 * 
	 * @see
	 * net.cedu.biz.academy.AcademyBiz#findAcademyByNameAndTelephone(java.lang
	 * .String, java.lang.String)
	 */
	public int findAcademyByNameAndTelephone(String name, String telePhone)
			throws Exception {
		PageParame p = new PageParame();
		p.setHqlConditionExpression("and name=" + Constants.PLACEHOLDER
				+ " and telephone = " + Constants.PLACEHOLDER
				+ " and deleteFlag=" + Constants.PLACEHOLDER);
		p.setValues(new Object[] { name, telePhone, Constants.DELETE_FALSE });
		return academydao.getCounts(p);
	}

	/*
	 * 招生计划院校列表(数量)
	 * 
	 * @see
	 * net.cedu.biz.academy.AcademyBiz#countAcademyByStatus(net.cedu.model.page
	 * .PageResult)
	 */
	public int countAcademyByStatus(PageResult<Academy> pr) {
		PageParame p = new PageParame(pr);
		p.setHqlConditionExpression(" and auditStatus=" + Constants.PLACEHOLDER
				+ " and deleteFlag = " + Constants.PLACEHOLDER);
		p.setValues(new Object[] { Constants.AUDIT_STATUS_TRUE,
				Constants.DELETE_FALSE });
		return academydao.getCounts(p);
	}

	/*
	 * 招生计划院校列表(数据)
	 * 
	 * @see
	 * net.cedu.biz.academy.AcademyBiz#findAcademyByStatus(net.cedu.model.page
	 * .PageResult)
	 */
	public List<Academy> findAcademyByStatus(PageResult<Academy> pr) {
		List<Academy> academys = null;
		PageParame p = new PageParame(pr);
		p.setHqlConditionExpression(" and auditStatus=" + Constants.PLACEHOLDER
				+ " and deleteFlag = " + Constants.PLACEHOLDER);
		p.setValues(new Object[] { Constants.AUDIT_STATUS_TRUE,
				Constants.DELETE_FALSE });
		Long[] academyIds = academydao.getIDs(p);
		if (academyIds != null && academyIds.length != 0) {
			academys = new ArrayList<Academy>();
			for (int i = 0; i < academyIds.length; i++) {
				Academy academy = academydao.findById(Integer
						.parseInt(academyIds[i].toString()));
				if (academy != null) {
					academys.add(academy);
				}
			}
		}
		return academys;
	}

	/*
	 * 查询院校实体通过名称
	 * 
	 * @see net.cedu.biz.academy.AcademyBiz#findAcademyByName(java.lang.String)
	 */
	public Academy findAcademyByName(String academyName) {
		return academydao.getObjByProperty("name", academyName);
	}

	/**
	 * 查询院校IDs通过强制收费状态
	 * 
	 * @see net.cedu.biz.academy.AcademyBiz#findAcademyIdsByForceFeePolicyStatus(int)
	 */
	public String findAcademyIdsByForceFeePolicyStatus(int status)
			throws Exception {
		Long[] idArray = academydao.getIDs(" and is_force_fee_policy="
				+ Constants.PLACEHOLDER + " and deleteFlag = "
				+ Constants.PLACEHOLDER, new Object[] { status,
				Constants.DELETE_FALSE });
		StringBuffer idsSB = new StringBuffer(",");
		if (idArray != null) {
			for (Long id : idArray) {
				if (idsSB.toString().equals(",")) {
					idsSB = new StringBuffer(id+"");
				} else {
					idsSB.append("," + id);
				}
			}
		}
		if (idsSB.toString().equals(",")) {
			idsSB = new StringBuffer("0");
		}
		String ids = idsSB.toString();
		//System.out.println(ids);
		return ids;
	}

	/*
	 * 通过条件查询院校集合(删除用户功能的查询方法)
	 * @see net.cedu.biz.academy.AcademyBiz#findAcademyListByParams(net.cedu.entity.academy.Academy, int)
	 */
	public List<Academy> findAcademyListByParams(Academy academy,int count)
			throws Exception {
		List<Academy> academysList = null;
		PageResult<Academy> pr = new PageResult<Academy>();
		pr.setPageSize(count);
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = null;
		if(academy!=null){
			list = new ArrayList<Object>();
			//项目经理
			if(academy.getProjectManagerId()!=0){
				hqlparam += " and projectManagerId = " + Constants.PLACEHOLDER;
				list.add(academy.getProjectManagerId());
			}
			
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
			Long[] academyIds = academydao.getIDs(p);
			if (academyIds != null && academyIds.length != 0){
				academysList = new ArrayList<Academy>();
				Academy a = null;
				for(Long id : academyIds){
					a = academydao.findById(id);
					if(a!=null){
						academysList.add(a);
					}
				}
				return academysList;
			}
		}
		return null;
	}
}
