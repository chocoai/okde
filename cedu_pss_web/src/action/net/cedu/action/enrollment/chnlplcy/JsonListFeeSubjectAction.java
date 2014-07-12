package net.cedu.action.enrollment.chnlplcy;

import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.entity.basesetting.FeeSubject;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 所有费用项目（用于合作方通用政策设置）
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class JsonListFeeSubjectAction extends BaseAction
{
	private static final long serialVersionUID = 1278241427639932567L;

	private List<FeeSubject> list = new LinkedList<FeeSubject>();

	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	
	public String execute() throws Exception
	{
		list = feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		
		return SUCCESS;
	}

	public List<FeeSubject> getList() {
		return list;
	}
}
