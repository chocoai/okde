package net.cedu.action.examination.examroom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamAreaBiz;
import net.cedu.biz.examination.ExamBatchBiz;
import net.cedu.entity.examination.ExamArea;
import net.cedu.entity.examination.ExamBatch;

public class IndexScoreSearchAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7324255494077243796L;

	@Autowired
	private ExamBatchBiz exambatchbiz;
	private List<ExamBatch> exambatchlist=new ArrayList<ExamBatch>();
	@Autowired
	private ExamAreaBiz examareabiz;
	private List<ExamArea> examarealist=new ArrayList<ExamArea>();
	public List<ExamArea> getExamarealist() {
		return examarealist;
	}
	public void setExamarealist(List<ExamArea> examarealist) {
		this.examarealist = examarealist;
	}
	public List<ExamBatch> getExambatchlist() {
		return exambatchlist;
	}
	public void setExambatchlist(List<ExamBatch> exambatchlist) {
		this.exambatchlist = exambatchlist;
	}

	public String execute()throws Exception
	{
		
		if(super.isGetRequest())
		{	
			exambatchlist=exambatchbiz.findAllexambatch();
			examarealist=examareabiz.findAllexamarea();
			return INPUT;
		}
		return SUCCESS;
	
	}


}
