package net.cedu.action.enrollment;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRegisterFeeSubjectBiz;
import net.cedu.entity.enrollment.AcademyRegisterFeeSubject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 保存 院校费用科目(招生阶段)
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({@Result(name="success", type="json"), @Result(name="error", type="json")})
public class SaveAcademyNonEnrollFeeSubjectAction extends BaseAction
{
	/**
	 * @date 2011-08-05 18:02
	 */
	private static final long serialVersionUID = 3421460299607926640L;
	
	private static final String ACTION_NAME = "save_academy_non_enroll_batch_fee_subject";
	
	private int academyId;
	private int batchId;
	private int[] feeSubjectIds;
	
	private int errno = 0;
	private String errMsg;
	
	@Autowired
	private AcademyRegisterFeeSubjectBiz academyRegisterFeeSubjectBiz;
	
	@Action(ACTION_NAME)
	public String execute() throws Exception
	{
		//此处执行清空对应   院校、批次  的所有院校费用科目
		List<AcademyRegisterFeeSubject> aefsList = academyRegisterFeeSubjectBiz.findByAcademyAndBatch(academyId, batchId);
		
		try {
			int num = academyRegisterFeeSubjectBiz.deleteList(aefsList);
			if(num != aefsList.size()){
				errno = -1;
				
				return ERROR;
			}
		}catch(Exception ex){
			errno = -2;
			
			return ERROR;
		}
		
		
		
		if(feeSubjectIds==null){
			errno = 1; //仅清空费用科目而没有保存新的费用科目
			return SUCCESS;
		}
		
		List<AcademyRegisterFeeSubject> list = new LinkedList<AcademyRegisterFeeSubject>();
		
		Date now = new Date();
		
		for(int i=0; i<feeSubjectIds.length; i++){
			AcademyRegisterFeeSubject entity = new AcademyRegisterFeeSubject();
			entity.setAcademyId(academyId);
			entity.setCreatedTime(now);
			entity.setCreatorId(getUser().getUserId());
			entity.setRegisterBatchId(batchId);
			entity.setFeeSubjectId(feeSubjectIds[i]);
			
			list.add(entity);
		}
		
		try {
			academyRegisterFeeSubjectBiz.addList(list);
		} catch (Exception e){
			e.printStackTrace();
			errno = -3;
		}
		
		return SUCCESS;
	}

	public int getErrno() {
		return errno;
	}

	public String getErrMsg() {
		errMsg = getText(ACTION_NAME+".errno."+errno, "");
		return errMsg;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public void setFeeSubjectIds(int[] feeSubjectIds) {
		this.feeSubjectIds = feeSubjectIds;
	}
}
