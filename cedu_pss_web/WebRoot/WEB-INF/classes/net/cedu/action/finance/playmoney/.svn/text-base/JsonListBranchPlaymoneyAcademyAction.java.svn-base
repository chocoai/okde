package net.cedu.action.finance.playmoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayCeduAcademyBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayCeduAcademy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校打款(中心)
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonListBranchPlaymoneyAcademyAction extends BaseAction
{
	
	// 分页结果
	private PageResult<PayCeduAcademy> result = new PageResult<PayCeduAcademy>();
	
	@Autowired
	private PayCeduAcademyBiz payCeduAcademyBiz;//打款单业务接口
	@Autowired
	private FeePaymentDetailBiz fpdBiz;//缴费单明细_业务层接口
	
	//分页参数
	private PayCeduAcademy payCeduAcademy;//打款单实体
	private String starttime;//汇款日期起
	private String endtime;//汇款日期止
	private int status;//大于等于某个状态
	private String amount;//汇款金额
	
	//***********  统计院校打款单的总金额    ********************//
	private String allplaymoney;
	
	//*************打款单回退*********************//
	private int pcaId;//打款单Id
	private int pcaTypes;//打款单回退状态
	private boolean isback;//返回参数
	
	//**********还原总部确认后的汇款单
	private int count=0;
	
	
	
	
	/**
	 * 获取打款单数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_branch_playmoney_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,payCeduAcademy, starttime, endtime,status,amount"
	}) })
	public String playmoneyCount() throws Exception 
	{
		result.setRecordCount(this.payCeduAcademyBiz.findPayCeduAcademyCountByDetails(payCeduAcademy, starttime, endtime,status,amount, result));
		return SUCCESS;
	}

	/**
	 * 获取打款单列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_branch_playmoney_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties",
			"result.*,payCeduAcademy, starttime, endtime,status,amount"
			}) })
	public String playmoneyList() throws Exception 
	{	
		result.setList(this.payCeduAcademyBiz.findPayCeduAcademyListByDetails(payCeduAcademy, starttime, endtime,status, amount, result));
		return SUCCESS;
	}
	
	/**
	 * 统计院校打款单总金额
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_pay_cedu_academy_all_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"allplaymoney.*,payCeduAcademy, starttime, endtime, status, amount"
			}) })
			
	public String playmoneyCountAllMoney() throws Exception 
	{
		
		allplaymoney=this.payCeduAcademyBiz.countPayCeduAcademyAllMoneyByConditions(payCeduAcademy, starttime, endtime, status, amount);
		if(allplaymoney==null || allplaymoney.equals(""))
		{
			allplaymoney="0";
		}
		return SUCCESS;
	}

	/**
	 * 打款单回退
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_pay_cedu_academy_types_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String uppcatypes() throws Exception 
	{
		if(pcaId>0)
		{
			PayCeduAcademy pca=this.payCeduAcademyBiz.findById(pcaId);
			pca.setTypes(pcaTypes);
			pca.setUpdaterId(super.getUser().getUserId());
			pca.setUpdatedTime(new Date());
			isback=this.payCeduAcademyBiz.updatePayCeduAcademy(pca);
		}
		return SUCCESS;
	}
	
	/**
	 * 还原总部确认后的打款院校单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "fallback_xianj_hui_academy_status_for_cedu_confirm_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String fallbackdstatusforceduList() throws Exception 
	{
		if(pcaId>0)
		{
			boolean isfail=false;
			PayCeduAcademy pca=this.payCeduAcademyBiz.findById(pcaId);;
			List<FeePaymentDetail> fpdlist=this.fpdBiz.findFeePaymentDetailListByPayCeduAcademyId(pcaId);
			if(pca!=null && pca.getStatus()==Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO && fpdlist!=null && fpdlist.size()>0)
			{
				pca.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN);
				pca.setTypes(Constants.FALLBACK_TYPES_ROLLED_BACK);
				pca.setUpdaterId(super.getUser().getUserId());
				pca.setUpdatedTime(new Date());
				for(FeePaymentDetail fpd:fpdlist)
				{
					if(fpd.getStatus()==Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO && fpd.getRefundAmount()==0 && fpd.getRefundLock()==Constants.LOCKING_FALSE && fpd.getRebateStatus()<=Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN)
					{
						
						fpd.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN); //回退一个状态
						fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_CHU_SHI_ZHUANG_TAI);//返款  初始状态
						
						//总部确认时间和操作相关人员
						fpd.setCeduConfirmTime(null);
						fpd.setCeduConfirmId(0);
								
						//账户金额变动   回退到原来状态
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).add(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).subtract(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
						fpd.setUpdaterId(super.getUser().getUserId());
						fpd.setUpdatedTime(new Date());
					}
					else
					{
						isfail=true;
						if(fpd.getStatus()>Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO)
						{
							count=1;
						}
						else if(fpd.getRefundAmount()>0 || fpd.getRefundLock()==Constants.LOCKING_TRUE)
						{
							count=2;
						}
						else if(fpd.getRebateStatus()>Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN)
						{
							count=3;
						}
						break;
					}
				}
			}
			if(!isfail)
			{
				isback=this.payCeduAcademyBiz.updatePayCeduAcademyUpdateFeePaymentDetails(pca, fpdlist);
			}
		}
		return SUCCESS;
	}
	
	public PageResult<PayCeduAcademy> getResult() {
		return result;
	}

	public void setResult(PageResult<PayCeduAcademy> result) {
		this.result = result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PayCeduAcademy getPayCeduAcademy() {
		return payCeduAcademy;
	}

	public void setPayCeduAcademy(PayCeduAcademy payCeduAcademy) {
		this.payCeduAcademy = payCeduAcademy;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAllplaymoney() {
		//转换金额
		allplaymoney=MoneyUtil.formatMoney(allplaymoney);
		return allplaymoney;
	}

	public void setAllplaymoney(String allplaymoney) {
		this.allplaymoney = allplaymoney;
	}

	public int getPcaId() {
		return pcaId;
	}

	public void setPcaId(int pcaId) {
		this.pcaId = pcaId;
	}

	public int getPcaTypes() {
		return pcaTypes;
	}

	public void setPcaTypes(int pcaTypes) {
		this.pcaTypes = pcaTypes;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
