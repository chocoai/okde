package net.cedu.action.basesetting.enrollmentsource;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewEnrollmentSourceAction extends BaseAction{

	private static final long serialVersionUID = 2390582048808883748L;

	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	
	private int sourceid;//费用科目id
	private List<EnrollmentSource> enrollmentsourcelist;//招生途径列表
	private List<FeeSubject> feesubjectlist;
	private Integer [] feeSubjectIds;//已设置过的费用科目id集合
	private boolean lstrltbool=true; //判断页面加载列表是否正常
	private boolean subjectlstrltbool=true; //判断页面加载列表是否正常
	
	/**
	 * 查询所有招生途径信息  BY  HXJ
	 */
	@Action(value="listenrollmentsource",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()throws Exception{
		try {
			enrollmentsourcelist = enrollmentSourceBiz.findAllEnrollmentSources();
		} catch (Exception e) {
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	
	/**
	 * 查询所有招生途径信息(delete_flag=0)  BY  HXJ
	 */
	@Action(value="listenrollmentsourcebydeleteflag",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								"excludeProperties",
								"enrollmentsourcelist.*.createdTime,"+
								"enrollmentsourcelist.*.updatedTime"
								}			
					   )
			})	
	public String enrollmentSourceListByDeleteFlag()throws Exception{
		try {
			enrollmentsourcelist = enrollmentSourceBiz
				.findSubjectNamesBySubjectIds(enrollmentSourceBiz.findAllEnrollmentSourceByDeleteFlag());
			if(enrollmentsourcelist==null||enrollmentsourcelist.size()<=0){
				lstrltbool=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	/**
	 * 读取科目设置信息 
	 * @return
	 */
	@Action(value="listfeesubjectbyflag",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								"excludeProperties",
								"feesubjectlist.*.createdTime,"+
								"feesubjectlist.*.updatedTime,"+
								"feesubjectlist.*.batchType,"+
								"feesubjectlist.*.code,"+
								"feesubjectlist.*.isMultiplePayment,"+
								"feesubjectlist.*.creatorId,"+
								"feesubjectlist.*.updaterId"
								}			
					   )
			})	
	public String feeSubjectListByDeleteFlag()throws Exception{
		try {
			
		
			if(sourceid>0){
				//加载全部费用科目
				feesubjectlist = feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
				feeSubjectIds = enrollmentSourceBiz.changeStringIdsIntoIntArray(sourceid);
			}else{
				subjectlstrltbool=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			subjectlstrltbool=false;
		}
		
		return "success";
	}
	//-------------------------get and set mothods-------------------
	
	public List<EnrollmentSource> getEnrollmentsourcelist() {
		return enrollmentsourcelist;
	}
	public void setEnrollmentsourcelist(List<EnrollmentSource> enrollmentsourcelist) {
		this.enrollmentsourcelist = enrollmentsourcelist;
	}
	public boolean isLstrltbool() {
		return lstrltbool;
	}
	public void setLstrltbool(boolean lstrltbool) {
		this.lstrltbool = lstrltbool;
	}
	public List<FeeSubject> getFeesubjectlist() {
		return feesubjectlist;
	}
	public void setFeesubjectlist(List<FeeSubject> feesubjectlist) {
		this.feesubjectlist = feesubjectlist;
	}
	public boolean isSubjectlstrltbool() {
		return subjectlstrltbool;
	}
	public void setSubjectlstrltbool(boolean subjectlstrltbool) {
		this.subjectlstrltbool = subjectlstrltbool;
	}
	public int getSourceid() {
		return sourceid;
	}
	public void setSourceid(int sourceid) {
		this.sourceid = sourceid;
	}
	public Integer[] getFeeSubjectIds() {
		return feeSubjectIds;
	}
	public void setFeeSubjectIds(Integer[] feeSubjectIds) {
		this.feeSubjectIds = feeSubjectIds;
	}
	
}
