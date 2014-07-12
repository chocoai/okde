package net.cedu.action.examination.examroom;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;

import org.springframework.beans.factory.annotation.Autowired;

public class ListExamplanAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3891115342993229552L;

	
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//院校招生(学籍)批次  业务接口
	private List<AcademyEnrollBatch> batchlist=new ArrayList<AcademyEnrollBatch>();//批次集合
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String execute()throws Exception
	{
		if(super.isGetRequest())
		{	
			System.out.println(id+","+name);
			batchlist=academyEnrollBatchBiz.findByAcademyIdAndFlag(id);
			return INPUT;
		}
		return SUCCESS;
	}
	public List<AcademyEnrollBatch> getBatchlist() {
		return batchlist;
	}
	public void setBatchlist(List<AcademyEnrollBatch> batchlist) {
		this.batchlist = batchlist;
	}
	


}
