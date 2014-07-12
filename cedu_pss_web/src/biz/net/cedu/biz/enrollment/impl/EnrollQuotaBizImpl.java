package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.biz.enrollment.EnrollQuotaBatchBiz;
import net.cedu.biz.enrollment.EnrollQuotaBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.EnrollQuotaDao;
import net.cedu.entity.enrollment.EnrollQuota;
import net.cedu.entity.enrollment.EnrollQuotaBatch;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollQuotaBizImpl implements EnrollQuotaBiz {
	@Autowired
	private EnrollQuotaDao enrollquotaDao;           //年度总指标接口
	@Autowired
	private EnrollQuotaBatchBiz enrollquotabatchBiz; //批次指标Biz
	
	/*
	 * 添加年度总指标
	 * @see net.cedu.biz.enrollment.EnrollQuotaBiz#addEnrollQuota(net.cedu.entity.enrollment.EnrollQuota)
	 */
	public boolean addEnrollQuota(EnrollQuota enrollquota) throws Exception {
		enrollquotaDao.save(enrollquota);
		return true;
	}

	/*
	 * 修改年度总指标
	 * @see net.cedu.biz.enrollment.EnrollQuotaBiz#updateEnrollQuota(net.cedu.entity.enrollment.EnrollQuota)
	 */
	public boolean updateEnrollQuota(EnrollQuota enrollquota) throws Exception {
		enrollquotaDao.update(enrollquota);
		return true;
	}

	/*
	 * 删除年度总指标
	 * @see net.cedu.biz.enrollment.EnrollQuotaBiz#deleteEnrollQuota(int)
	 */
	public boolean deleteEnrollQuota(int id) throws Exception {
		enrollquotaDao.deleteConfig(id);
		return true;
	}

	/*
	 * 查询所有年度总指标
	 * @see net.cedu.biz.enrollment.EnrollQuotaBiz#findEnrollQuotaAll()
	 */
	public List<EnrollQuota> findEnrollQuotaAll() throws Exception {
		
		return enrollquotaDao.findAllNotDeleted();
	}

	
	/*
	 * 查询年度总指标按Id
	 * @see net.cedu.biz.enrollment.EnrollQuotaBiz#findEnrollQuotaById(int)
	 */
	public EnrollQuota findEnrollQuotaById(int id) throws Exception {
		
		return enrollquotaDao.findById(id);
	}

	/*
	 * 查询年度总指标(数量)
	 * @see net.cedu.biz.enrollment.EnrollQuotaBiz#countEnrollQuota(net.cedu.model.page.PageResult)
	 */
	public int countEnrollQuota(PageResult<EnrollQuota> pr) throws Exception {
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		hqlparam+=" and deleteFlag=";
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return enrollquotaDao.getCounts(p);
	}
	
	/*
	 *  查询年度总指标(数据)
	 * @see net.cedu.biz.enrollment.EnrollQuotaBiz#findEnrollQuotalist(net.cedu.model.page.PageResult)
	 */
	public List<EnrollQuota> findEnrollQuotalist(PageResult<EnrollQuota> pr)
			throws Exception {
		List<EnrollQuota> enrollquotalst=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		hqlparam+=" and deleteFlag="+Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		hqlparam+=" order by  enrollYear desc";
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] enrollquotaIds = enrollquotaDao.getIDs(p);
		
		if (enrollquotaIds != null && enrollquotaIds.length != 0) {
			enrollquotalst = new ArrayList<EnrollQuota>();
			for (int i = 0; i < enrollquotaIds.length; i++) {
				EnrollQuota eq = this.findEnrollQuotaById(Integer.valueOf(enrollquotaIds[i]
						.toString()));
				enrollquotalst.add(eq);
			}
		}
		return enrollquotalst;
	}

	/*
	 * 查询年度总指标按年份
	 * @see net.cedu.biz.enrollment.EnrollQuotaBiz#findEnrollQuotaByYear(int)
	 */
	public EnrollQuota findEnrollQuotaByYear(int year)
			throws Exception {
		
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(year!=0)
		{
			hqlparam+=" and enrollYear="+Constants.PLACEHOLDER;
			list.add(year);
		}
		hqlparam+=" and deleteFlag=";
		list.add(Constants.DELETE_FALSE);
		List<EnrollQuota> eq=enrollquotaDao.getByProperty(hqlparam, list);
		if(eq!=null && eq.size()>0)
		{
			return eq.get(0);
		}
		return null;
	}

	/*
	 * 添加年度总指标(计算)
	 * @see net.cedu.biz.enrollment.EnrollQuotaBiz#addEnrollQuotas(int, int, int, int, int, int)
	 */
	public int[] addEnrollQuotas(int year, int branch, int opeaing,
			int major, int target, int userId) throws Exception {
		int [] targetInt=new int[2];
		EnrollQuota enrollquota=null;
		if(year!=0)
		{
			EnrollQuota eq=this.findEnrollQuotaByYear(year-1);
			if(eq==null)
			{
				eq=new EnrollQuota();
				eq.setQuota(0);
			}
				
				enrollquota=new EnrollQuota();
				targetInt[0]=eq.getQuota();
				if(branch==1)
				{
					if(opeaing==1)
					{
						if(major==1)
						{
							
							enrollquota.setQuota(eq.getQuota()+target);
							targetInt[1]=enrollquota.getQuota();
							
						}else
						{
							enrollquota.setQuota(eq.getQuota()+(eq.getQuota()*(target/100)));
							targetInt[1]=enrollquota.getQuota();
						}
					}else if(opeaing==2)
					{
						if(major==1)
						{
							enrollquota.setQuota(eq.getQuota()-target);
							targetInt[1]=enrollquota.getQuota();
						}else
						{
							enrollquota.setQuota(eq.getQuota()-(eq.getQuota()*(target/100)));
							targetInt[1]=enrollquota.getQuota();
							
						}
					}
					else if(opeaing==3)
					{
						if(major==1)
						{
							enrollquota.setQuota(eq.getQuota()*target);
							targetInt[1]=enrollquota.getQuota();
							
						}else
						{
							enrollquota.setQuota(eq.getQuota()*(eq.getQuota()*(target/100)));
							targetInt[1]=enrollquota.getQuota();
							
						}
					}else
					{
						if(major==1)
						{
							if(target==0)
							{
								enrollquota.setQuota(0);
							}else
							{
								enrollquota.setQuota(eq.getQuota()/target);
							}
							targetInt[1]=enrollquota.getQuota();
							
						}else
						{
							double numdou1=(eq.getQuota()*(Double.parseDouble(target+"")/100));
							if(numdou1!=0)
							{
								enrollquota.setQuota(Integer.parseInt((eq.getQuota()/numdou1)+""));
							}else
							{
								enrollquota.setQuota(0);
							}
							targetInt[1]=enrollquota.getQuota();
							
						}
					}
				}else
				{
					int risgerNumber=0;
					List<EnrollQuotaBatch> eqblst= enrollquotabatchBiz.findEnrollQuotaBatchBybatchIds(eq.getId(),null);
					if(eqblst==null)
					{
						
					}else
					{
						for(int i=0;i<eqblst.size();i++)
						{
							risgerNumber+=eqblst.get(i).getRegistNumber();
						}
					}
					eq.setQuota(risgerNumber);
					if(opeaing==1)
					{
						if(major==1)
						{
							enrollquota.setQuota(eq.getQuota()+target);
							targetInt[1]=enrollquota.getQuota();
						}else
						{
							enrollquota.setQuota(eq.getQuota()+(eq.getQuota()*(target/100)));
							targetInt[1]=enrollquota.getQuota();
						}
					}else if(opeaing==2)
					{
						if(major==1)
						{
							enrollquota.setQuota(eq.getQuota()-target);
							targetInt[1]=enrollquota.getQuota();
						}else
						{
							enrollquota.setQuota(eq.getQuota()-(eq.getQuota()*(target/100)));
							targetInt[1]=enrollquota.getQuota();
						}
					}
					else if(opeaing==3)
					{
						if(major==1)
						{
							if(eq.getQuota()!=0)
							{
								enrollquota.setQuota(eq.getQuota()*target);
								targetInt[1]=enrollquota.getQuota();
							}
							enrollquota.setQuota(target);
							targetInt[1]=enrollquota.getQuota();
							
						}else
						{
							if(eq.getQuota()!=0)
							{
								enrollquota.setQuota(eq.getQuota()*(eq.getQuota()*(target/100)));
								targetInt[1]=enrollquota.getQuota();
							}
							enrollquota.setQuota((target/100));
							targetInt[1]=enrollquota.getQuota();
							
						}
					}else
					{
						if(major==1)
						{
							if(target==0)
							{
								enrollquota.setQuota(0);
							}else
							{
								enrollquota.setQuota(eq.getQuota()/target);
							}
							targetInt[1]=enrollquota.getQuota();
						
						}else
						{
							double numdou=(eq.getQuota()*(Double.parseDouble(target+"")/100));
							if(numdou!=0)
							{
								enrollquota.setQuota(Integer.parseInt((eq.getQuota()/numdou)+""));
							}else
							{
								enrollquota.setQuota(0);
							}
							
							targetInt[1]=enrollquota.getQuota();
						}
					}
				}
			
			}
	
		return targetInt;
	}
	/*
	 * 查询最大年份
	 * @see net.cedu.biz.enrollment.EnrollQuotaBiz#findEnrollQuotaMaxYear()
	 */
	public int findEnrollQuotaMaxYear() throws Exception {
		Date date=new Date();
		List <EnrollQuota> eq=this.findEnrollQuotaAll();
		if(eq!=null && eq.size()>0)
		{
			for(int i=0;i<eq.size();i++)
			{
				for(int j=i+1;j<eq.size();j++)
				{
					if(eq.get(i).getEnrollYear()>eq.get(j).getEnrollYear())
					{
						int temp=eq.get(i).getEnrollYear();
						int eqi=eq.get(i).getEnrollYear();
						int eqj=eq.get(j).getEnrollYear();
						eqi=eqj;
						eqj=temp;
					}
				}
			}
			if(eq.size()>1)
			{
				return eq.get(eq.size()-1).getEnrollYear();
			}else
			{
				return eq.get(0).getEnrollYear();
			}
		}
		return date.getYear();
	}
		

	
}
