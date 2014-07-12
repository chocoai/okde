package net.cedu.action.basesetting.financeproperty;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeBatchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.entity.basesetting.FeeSubject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonDeleteFinancePropertyAction extends BaseAction{

	private static final long serialVersionUID = -6088218316093532380L;
	
	@Autowired
	private FeeBatchBiz feeBatchBiz;//缴费次数BIZ
	@Autowired
	private FeeSubjectBiz feesubjectbiz;//费用科目BIZ
	
	private int feebatchid;//缴费次数ID
	private int feesubjectid;//费用科目ID
	
	private boolean delrltbool=false;//判断删除操作是否成功
	
//	/**
//	 * 按id删除财务参数各模块信息(物理删除)
//	 * @return
//	 */
//	@Action(value="deletefinanceproperty",
//			results={
//				@Result(type="json", name = "success",
//						params={"contentType","text/json"}
//					   )
//			})	
//	public String execute(){
//		FeeSubject feesubject = new FeeSubject();
//		
//		if(feebatchid>0){
//			try{
//				delrltbool=feeBatchBiz.deleteConfigFeeBatchById(feebatchid);
//			}catch (Exception e) {
//				e.printStackTrace();
//				delrltbool=false;
//			}
//		}else if(feesubjectid>0){
//			try{
//				feesubject=feesubjectbiz.deleteConfigFeeSubject(feesubjectid);
//			}catch (Exception e) {
//				e.printStackTrace();
//				delrltbool=false;
//			}
//			delrltbool=isDelSuccess(feesubject);
//		}
//		
//		return SUCCESS;
//	}
	


	/**
	 * 按id删除财务参数各模块信息(逻辑删除)
	 * @return
	 */
	@Action(value="deletefinancepropertystatus",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		FeeSubject feesubject = new FeeSubject();
		if(feebatchid>0){
			try {
				delrltbool=feeBatchBiz.deleteConfigFeeBatchById(feebatchid);
			} catch (Exception e) {
				e.printStackTrace();
				delrltbool=false;
			}
		}else if(feesubjectid>0){
			try{
				feesubject=feesubjectbiz.deleteConfigFeeSubject(feesubjectid);
				delrltbool = isDelSuccess(feesubject);
			}catch(Exception e){
				e.printStackTrace();
				delrltbool=false;
			}
		}

		return SUCCESS;
	}
	
//----------------------------------------------辅助方法----------------------------
	//判断删除操作是否成功
	private boolean isDelSuccess(Object obj){
		if(obj!=null&&!("").equals(obj)){
			return true;
		}
		return false;
	}
//-----------------------------------------get and set methods--------------------------------
	
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
	public boolean isDelrltbool() {
		return delrltbool;
	}
	public void setDelrltbool(boolean delrltbool) {
		this.delrltbool = delrltbool;
	}
	
}
