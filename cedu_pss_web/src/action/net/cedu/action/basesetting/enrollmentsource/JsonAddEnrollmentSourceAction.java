package net.cedu.action.basesetting.enrollmentsource;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.basesetting.EnrollmentSource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddEnrollmentSourceAction extends BaseAction{
	
	private static final long serialVersionUID = 5968462794902775426L;

	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	
	private String enrollmentSourceName;//招生途径名称
	private String enrollmentSourceCode;//编码
	private int enrollmentSourceType;//类别
	private int enrollmentSourceIsneedRebates;//是否需要渠道返款    0-否,1-是
	private String enrollmentSourceSourceRebatesFeesubject;//渠道返款费用科目
	private boolean addrltbol=true;//判断添加方法是否成功
	private boolean errormsg=true;//有重复数据时的判断
	
	/**
	 * 添加招生途径
	 * @return
	 */
	@Action(value="addenrollmentsource",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
			public String execute(){
				
				//创建EnrollmentSource对象
				EnrollmentSource enrollmentSource = null;;
			
				
				try {
					//获取当前登陆者ID
					int userid=super.getUser().getUserId();
					
					enrollmentSource = new EnrollmentSource();
					
					//给招生途径实体对象 enrollmentSource 赋值
					enrollmentSourceCode = buildCodeBiz.getShortCodes(CodeEnum.EnrollmentSourceTB
							.getCode(), CodeEnum.EnrollmentSource.getCode());
					enrollmentSource.setName(enrollmentSourceName);
					enrollmentSource.setCode(enrollmentSourceCode);
					enrollmentSource.setType(enrollmentSourceType);
					enrollmentSource.setIsneedRebates(enrollmentSourceIsneedRebates);
					enrollmentSource.setSourceRebatesFeesubject(enrollmentSourceSourceRebatesFeesubject);
					
					enrollmentSource.setCreatorId(userid);
					enrollmentSource.setCreatedTime(new Date());
					enrollmentSource.setUpdaterId(userid);
					enrollmentSource.setUpdatedTime(new Date());
					enrollmentSource.setDeleteFlag(Constants.DELETE_FALSE);// 0-未删除  1-已删除
					//执行增加方法
					errormsg=enrollmentSourceBiz.addEnrollmentSource(enrollmentSource);
				} catch (Exception e) {
					e.printStackTrace();
					addrltbol=false;
				}
				return SUCCESS;
		}

	//-------------------------------------get and set methods------------------------------------------------
	
	public String getEnrollmentSourceName() {
		return enrollmentSourceName;
	}

	public void setEnrollmentSourceName(String enrollmentSourceName) {
		this.enrollmentSourceName = enrollmentSourceName;
	}

	public String getEnrollmentSourceCode() {
		return enrollmentSourceCode;
	}

	public void setEnrollmentSourceCode(String enrollmentSourceCode) {
		this.enrollmentSourceCode = enrollmentSourceCode;
	}

	public int getEnrollmentSourceType() {
		return enrollmentSourceType;
	}

	public void setEnrollmentSourceType(int enrollmentSourceType) {
		this.enrollmentSourceType = enrollmentSourceType;
	}

	public int getEnrollmentSourceIsneedRebates() {
		return enrollmentSourceIsneedRebates;
	}

	public void setEnrollmentSourceIsneedRebates(int enrollmentSourceIsneedRebates) {
		this.enrollmentSourceIsneedRebates = enrollmentSourceIsneedRebates;
	}

	public String getEnrollmentSourceSourceRebatesFeesubject() {
		return enrollmentSourceSourceRebatesFeesubject;
	}

	public void setEnrollmentSourceSourceRebatesFeesubject(
			String enrollmentSourceSourceRebatesFeesubject) {
		this.enrollmentSourceSourceRebatesFeesubject = enrollmentSourceSourceRebatesFeesubject;
	}

	public boolean isAddrltbol() {
		return addrltbol;
	}

	public void setAddrltbol(boolean addrltbol) {
		this.addrltbol = addrltbol;
	}

	public boolean isErrormsg() {
		return errormsg;
	}

	public void setErrormsg(boolean errormsg) {
		this.errormsg = errormsg;
	}
	
}
