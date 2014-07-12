package net.cedu.action.enrollment;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyBatchFeeSubjectBiz;
import net.cedu.entity.enrollment.AcademyBatchFeeSubject;

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
public class SaveAcademyBatchFeeSubjectAction extends BaseAction
{
	/**
	 * @date 2011-08-05 18:02
	 */
	private static final long serialVersionUID = 3421460299607926640L;
	
	private static final String ACTION_NAME = "save_academy_batch_fee_subject";
	
	private int academyId;
	private int batchId;
	private int stage; //招生／非招生
	private int[] feeSubjectIds;
	
	private int errno = 0;
	private String errMsg;
	
	@Autowired
	private AcademyBatchFeeSubjectBiz academyBatchFeeSubjectBiz;
	
	@Action(ACTION_NAME)
	public String execute() throws Exception
	{
		//此处执行清空对应   院校、批次  的所有院校费用科目
		List<AcademyBatchFeeSubject> abfsList = academyBatchFeeSubjectBiz.findByAcademyAndBatchAndStage(academyId, batchId, stage);
		
		try {
			int num = academyBatchFeeSubjectBiz.deleteList(abfsList);
			if(num != abfsList.size()){
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
		
		List<AcademyBatchFeeSubject> list = new LinkedList<AcademyBatchFeeSubject>();
		
		Date now = new Date();
		
		for(int i=0; i<feeSubjectIds.length; i++){
			AcademyBatchFeeSubject entity = new AcademyBatchFeeSubject();
			entity.setAcademyId(academyId);
			entity.setCreatedTime(now);
			entity.setCreatorId(getUser().getUserId());
			entity.setBatchId(batchId);
			entity.setFeeSubjectId(feeSubjectIds[i]);
			entity.setStage(stage);
			
			list.add(entity);
		}
		
		try {
			academyBatchFeeSubjectBiz.addList(list);
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
		setIl8nName("enrollment");
		
		errMsg = getText("save_academy_batch_fee_subject.errno."+errno);
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

	public void setStage(int stage) {
		this.stage = stage;
	}
}
