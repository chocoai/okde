package net.cedu.action.finance.payacademycedu;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.finance.PayAcademyCedu;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校返款单(分页查询)
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonListPayAcademyCeduForPageAction extends BaseAction
{
	
	@Autowired 
	private PayAcademyCeduBiz payAcademyCeduBiz;//院校返款_业务层接口
	// 分页结果
	private PageResult<PayAcademyCedu> result = new PageResult<PayAcademyCedu>();
	
	private PayAcademyCedu payAcademyCedu=new PayAcademyCedu();//院校返款单实体
	private String starttime;//汇款起始时间
	private String endtime;//汇款结束时间
	private String amount;//实返金额
	
	//***********  统计院校返款的总金额    ********************//
	private String allacademymoney;
	
	//*************返款款单回退*********************//
	private int pacId;//返款单Id
	private int pacTypes;//返款单回退状态
	private boolean isback;//返回参数
	
	/**
	 * 院校返款单数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_pay_academy_cedu_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String pacForNowCount() throws Exception 
	{
		result.setRecordCount(this.payAcademyCeduBiz.findPayAcademyCeduCountByPage(payAcademyCedu, starttime, endtime, amount));
		return SUCCESS;
	}
	
	/**
	 * 院校返款单列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_pay_academy_cedu_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String pacForNowList() throws Exception 
	{	
		result.setList(this.payAcademyCeduBiz.findPayAcademyCeduListByPage(payAcademyCedu, starttime, endtime, amount, result));
		return SUCCESS;
	}
	
	/**
	 * 统计院校返款单总金额
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_pay_academy_cedu_all_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyCountAllMoney() throws Exception 
	{
		
		allacademymoney=this.payAcademyCeduBiz.countPayAcademyCeduAllMoneyByConditions(payAcademyCedu, starttime, endtime, amount);
		if(allacademymoney==null || allacademymoney.equals(""))
		{
			allacademymoney="0";
		}
		return SUCCESS;
	}

	/**
	 * 返款单回退
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_pay_academy_cedu_types_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String uppactypes() throws Exception 
	{
		if(pacId>0)
		{
			PayAcademyCedu pac=this.payAcademyCeduBiz.findById(pacId);
			pac.setTypes(pacTypes);
			pac.setUpdaterId(super.getUser().getUserId());
			pac.setUpdatedTime(new Date());
			isback=this.payAcademyCeduBiz.updatePayAcademyCedu(pac);
		}
		return SUCCESS;
	}
	
	public PageResult<PayAcademyCedu> getResult() {
		return result;
	}

	public void setResult(PageResult<PayAcademyCedu> result) {
		this.result = result;
	}

	public PayAcademyCedu getPayAcademyCedu() {
		return payAcademyCedu;
	}

	public void setPayAcademyCedu(PayAcademyCedu payAcademyCedu) {
		this.payAcademyCedu = payAcademyCedu;
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

	public String getAllacademymoney() {
		//转换金额
		allacademymoney=MoneyUtil.formatMoney(allacademymoney);
		return allacademymoney;
	}

	public void setAllacademymoney(String allacademymoney) {
		this.allacademymoney = allacademymoney;
	}

	public int getPacId() {
		return pacId;
	}

	public void setPacId(int pacId) {
		this.pacId = pacId;
	}

	public int getPacTypes() {
		return pacTypes;
	}

	public void setPacTypes(int pacTypes) {
		this.pacTypes = pacTypes;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}
	
	
	
}
