package net.cedu.action.basesetting.financeproperty;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeBatchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.basesetting.FeeBatch;
import net.cedu.entity.basesetting.FeeSubject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddFinancePropertyAction extends BaseAction{

	private static final long serialVersionUID = 6376456984009891619L;
	
	@Autowired
	private FeeBatchBiz feeBatchBiz;//缴费次数BIZ
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目BIZ
	@Autowired
	private BuildCodeBiz buildCodeBiz;
		
	private FeeBatch feebatch;//缴费次数实体
	private FeeSubject feesubject;//费用科目实体
	public String code;//code生成器生成的code(4位的)
	private boolean addrltbool=true;//判断添加是否成功
	private boolean errormsg=true;//有重复数据时的判断
		
	/**
	 * 添加财务参数各模块信息  BY HXJ
	 */
	@Action(value="addfinanceproperty",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		int userid = super.getUser().getUserId();
		
//		if(feebatch!=null){
//			feebatch.setStatus(1);
//			feebatch.setCreatedTime(new Date());
//			feebatch.setUpdatedTime(new Date());
//			feebatch.setCreatorId(userid);
//			feebatch.setUpdaterId(userid);
//			feebatch.setDeleteFlag(Constants.DELETE_FALSE);
//			try {
//				addrltbool = feeBatchBiz.addFeeBatch(feebatch);
//			} catch (Exception e) {
//				e.printStackTrace();
//				addrltbool = false;
//			}
//		}
		
		try {
			if (feesubject != null) {
				code=buildCodeBiz.getShortCodes(CodeEnum.FeeSubjectTB.getCode(),CodeEnum.FeeSubject.getCode());
				feesubject.setCode(code);
				feesubject.setCreatedTime(new Date());
				feesubject.setUpdatedTime(new Date());
				feesubject.setCreatorId(userid);
				feesubject.setUpdaterId(userid);
				feesubject.setDeleteFlag(Constants.DELETE_FALSE);
				errormsg = feeSubjectBiz.addFeeSubject(feesubject);

			} else {
				addrltbool = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addrltbool = false;
		}
		return SUCCESS;
	}
	
	//-------------------------------------------get and set methods------------------------------
	public FeeBatch getFeebatch() {
		return feebatch;
	}
	public void setFeebatch(FeeBatch feebatch) {
		this.feebatch = feebatch;
	}
	public FeeSubject getFeesubject() {
		return feesubject;
	}
	public void setFeesubject(FeeSubject feesubject) {
		this.feesubject = feesubject;
	}
	public boolean isAddrltbool() {
		return addrltbool;
	}
	public void setAddrltbool(boolean addrltbool) {
		this.addrltbool = addrltbool;
	}
	public boolean isErrormsg() {
		return errormsg;
	}
	public void setErrormsg(boolean errormsg) {
		this.errormsg = errormsg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
		
}
