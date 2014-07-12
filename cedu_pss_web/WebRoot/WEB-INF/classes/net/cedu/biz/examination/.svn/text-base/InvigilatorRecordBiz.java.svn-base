package net.cedu.biz.examination;

import java.util.List;


import net.cedu.entity.examination.InvigilatorRecord;
import net.cedu.model.page.PageResult;

public interface InvigilatorRecordBiz {
	public boolean createNew(InvigilatorRecord invigilatorrecord) throws Exception ;
	 public List<InvigilatorRecord> page(PageResult<InvigilatorRecord> result)throws Exception;
	 public int findAllrecordCount(PageResult<InvigilatorRecord> pr) throws Exception ;
	 public Object deleteInvigilatorRecordById(int id)throws Exception;
	 public InvigilatorRecord findByInvigilatorRecordId(int id)throws Exception;
	 public Object updateInvigilatorrecord(InvigilatorRecord invigilatorrecord)throws Exception;
	public List<InvigilatorRecord> findByConditions(int id,InvigilatorRecord invigilatorrecord, PageResult<InvigilatorRecord> pr)
	throws Exception;
    public int findInvigilatorRecordPageCount(int id,InvigilatorRecord invigilatorrecord, PageResult<InvigilatorRecord> pr)
	throws Exception;

}
