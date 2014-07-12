package net.cedu.action.basesetting.enrollmentsource;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.entity.basesetting.EnrollmentSource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonUpdateEnrollmentSourceAction extends BaseAction{

	private static final long serialVersionUID = 7769605910629131072L;

	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	
	private String enrollmentSourceName;//招生途径名称
	private String enrollmentSourceCode;//编码
	private int enrollmentSourceType;//类别
	private int enrollmentSourceIsneedRebates;//是否需要渠道返款    0-否,1-是
	private String enrollmentSourceSourceRebatesFeesubject;//渠道返款费用科目
	
	private boolean updaterltbol=true;//判断添加招生途径是否成功
	private boolean sameinfobool=true;
	private int enrollmentSourceid;//EnrollmentSource的ID
	
	/**
	 * 修改招生途径信息  BY HXJ
	 */
	@Action(value="updateenrollmentsource",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		EnrollmentSource oenrollmentSource = null;
		
		int userid;
		try {
			userid = super.getUser().getUserId();
			oenrollmentSource = new EnrollmentSource();
			oenrollmentSource=enrollmentSourceBiz.findEnrollmentSourceById(enrollmentSourceid);
			
			//根据传递过来的参数修改改实体
			if(enrollmentSourceSourceRebatesFeesubject!=null&&!("").equals(enrollmentSourceSourceRebatesFeesubject)){
				//处理页面传递过来的费用科目id字符串(处理成_id1_id2_格式)
				StringBuffer sb= new StringBuffer();
				sb.append("_");
				sb.append(enrollmentSourceSourceRebatesFeesubject);
				sb.append("_");
				oenrollmentSource.setSourceRebatesFeesubject(sb.toString());
			}else{
				oenrollmentSource.setName(enrollmentSourceName);
				oenrollmentSource.setType(enrollmentSourceType);
				oenrollmentSource.setIsneedRebates(enrollmentSourceIsneedRebates);
			}
			oenrollmentSource.setUpdaterId(userid);
			oenrollmentSource.setUpdatedTime(new Date());
			
			sameinfobool = enrollmentSourceBiz.modifyEnrollmentSource(oenrollmentSource);
			
		} catch (Exception e) {
			e.printStackTrace();
			updaterltbol = false;
		}
		return SUCCESS;
	}
	//---------------------------------------------get and set methods------------------------------------
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
	public boolean isUpdaterltbol() {
		return updaterltbol;
	}
	public void setUpdaterltbol(boolean updaterltbol) {
		this.updaterltbol = updaterltbol;
	}
	public int getEnrollmentSourceid() {
		return enrollmentSourceid;
	}
	public void setEnrollmentSourceid(int enrollmentSourceid) {
		this.enrollmentSourceid = enrollmentSourceid;
	}
	public boolean isSameinfobool() {
		return sameinfobool;
	}
	public void setSameinfobool(boolean sameinfobool) {
		this.sameinfobool = sameinfobool;
	}
}
