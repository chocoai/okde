package net.cedu.biz.examination;

import java.util.List;



import net.cedu.entity.examination.Invigilator;
import net.cedu.entity.examination.InvigilatorCommunicationRecord;
import net.cedu.model.page.PageResult;

public interface InvigilatorCommunicationRecordBiz {
	 public boolean createNew(InvigilatorCommunicationRecord invigilatorcommunicationrecord) throws Exception ;
	 public List<InvigilatorCommunicationRecord> page(PageResult<InvigilatorCommunicationRecord> result)throws Exception;
	 public int findAllrecordCount(PageResult<InvigilatorCommunicationRecord> pr) throws Exception ;
	 public Object deleteInvigilatorCommunicationRecordById(int id)throws Exception;
	 public InvigilatorCommunicationRecord findByInvigilatorCommunicationRecordId(int id)throws Exception;
	 public Object updateInvigilatorcommunicationrecord(InvigilatorCommunicationRecord invigilatorrecord)throws Exception;
	public List<InvigilatorCommunicationRecord> findByConditions(int id,InvigilatorCommunicationRecord invigilatorrecord, PageResult<InvigilatorCommunicationRecord> pr)
	throws Exception;
     public int findInvigilatorCommunicationRecordPageCount(int id,InvigilatorCommunicationRecord invigilatorrecord, PageResult<InvigilatorCommunicationRecord> pr)
	throws Exception;
	

}
