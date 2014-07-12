package net.cedu.action.basesetting.financeproperty;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeBatchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.entity.basesetting.FeeBatch;
import net.cedu.entity.basesetting.FeeSubject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonUpdateFinancePropertyAction extends BaseAction{

	private static final long serialVersionUID = 5844930603254343481L;
	@Autowired
	private FeeBatchBiz feeBatchBiz;//缴费次数BIZ
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目BIZ
	
	private FeeBatch feebatch;//缴费次数实体
	private FeeSubject feesubject;//费用科目实体
	
	private int feebatchid;//缴费次数ID
	private int feesubjectid;///费用科目ID
	
	private boolean updaterltbool=true;//判断修改操作是否成功
	private boolean sameinfobool=true;
	
	/**
	 * 修改缴费次数信息  BY HXJ
	 */
	@Action(value="updatefinanceproperty",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		FeeBatch newfeebatch= new FeeBatch();
		FeeSubject newfeesubject= new FeeSubject();
		
		int userid = super.getUser().getUserId();
		
//		if(feebatchid>0&&feebatch!=null&&!("").equals(feebatch)){
//			try {
//				//按ID先获得实体的属性
//				newfeebatch = feeBatchBiz.findFeeBatchById(feebatchid);
//				//赋参数
//				newfeebatch.setName(feebatch.getName());
//				newfeebatch.setCode(feebatch.getCode());
//				newfeebatch.setCreatedTime(new Date());
//				newfeebatch.setUpdatedTime(new Date());
//				newfeebatch.setStartTime(feebatch.getStartTime());
//				newfeebatch.setEndTime(feebatch.getEndTime());
//				newfeebatch.setBelongYear(feebatch.getBelongYear());
//				newfeebatch.setUpdaterId(userid);
//				//执方法行Update
//				updaterltbool=feeBatchBiz.modifyFeeBatch(newfeebatch);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				updaterltbool=false;
//			}
//		}

		if(feesubjectid>0&&feesubject!=null&&!("").equals(feesubject)){
			try {
				//按ID先获得实体的属性
				newfeesubject = feeSubjectBiz.findFeeSubjectById(feesubjectid);
				//赋参数
				newfeesubject.setName(feesubject.getName());
				newfeesubject.setBatchType(feesubject.getBatchType());
				newfeesubject.setIsMultiplePayment(feesubject.getIsMultiplePayment());
				newfeesubject.setUpdatedTime(new Date());
				newfeesubject.setUpdaterId(feesubject.getUpdaterId());
				//执方法行Update
				sameinfobool = feeSubjectBiz.modifyFeeSubject(newfeesubject);
			} catch (Exception e) {
				e.printStackTrace();
				updaterltbool=false;
			}
				
		}else{
			updaterltbool=false;
		}
		return SUCCESS;
	}
	
//----------------------------------------get and set methods---------------------------------

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

	public int getFeebatchid() {
		return feebatchid;
	}

	public void setFeebatchid(int feebatchid) {
		this.feebatchid = feebatchid;
	}

	public int getFeesubjectid() {
		return feesubjectid;
	}

	public void setFeesubjectid(int feesubjectid) {
		this.feesubjectid = feesubjectid;
	}

	public boolean isUpdaterltbool() {
		return updaterltbool;
	}

	public void setUpdaterltbool(boolean updaterltbool) {
		this.updaterltbool = updaterltbool;
	}

	public boolean isSameinfobool() {
		return sameinfobool;
	}

	public void setSameinfobool(boolean sameinfobool) {
		this.sameinfobool = sameinfobool;
	}
	
}
