package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.SubAcademyEnrollBatchBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewAcademyEnrollBatchAction extends BaseAction{

	private static final long serialVersionUID = 1860856168111154778L;

	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired
	private SubAcademyEnrollBatchBiz subAcademyEnrollBatchBiz;
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;
	
	private int academyId;//院校ID
	private int batchid;//当前院校招生批次id
	private List<GlobalEnrollBatch> globalbatchlist;//全局招生批次
	private List<AcademyEnrollBatch> academyEnrollBatchlist;//院校招生批次列表
	private List<AcademyEnrollBatch> academyBatchlist;//院校招生批次列表
	
	private boolean lstrltbool=true;//判断页面加载列表是否正常
	
	/**
	 * 查询院校招生批次信息   BY  HXJ
	 */
	@Action(value="list_view_academy_enroll_batch",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()throws Exception{
		try {
			academyEnrollBatchlist = academyEnrollBatchBiz.findByAcademyId(academyId);
		} catch (Exception e) {
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	
	/**
	 * 查询院校招生批次信息(delete_flag=0)   BY  HXJ
	 */
	@SuppressWarnings("unchecked")
	@Action(value="list_academy_enroll_batch_by_deleteflag",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								"excludeProperties",
								"academyEnrollBatchlist.*.createdTime,"+
								"academyEnrollBatchlist.*.updatedTime"
								}			
				 		)
			})	
	public String showModeListByDeleteFlag()throws Exception{
		try {
			academyEnrollBatchlist = academyEnrollBatchBiz.findByAcademyIdAndFlag(academyId);
			for(int i=0;i<academyEnrollBatchlist.size();i++){
				//添加对应的全局招生批次实体
				academyEnrollBatchlist.get(i).setGlobalEnrollBatch(globalEnrollBatchBiz
						.findGlobalEnrollBatchById(academyEnrollBatchlist.get(i).getGlobalEnrollBatchId()));
				//根据院校招生批次ID查询 子批次的LIST集合 ，添加到对应的院校招生批次实体中
				academyEnrollBatchlist.get(i).setSubAcademyEnrollBatchlist(subAcademyEnrollBatchBiz
						.findSubAcademyEnrollBatchByAcademyEnrollBatchId(academyEnrollBatchlist.get(i).getId()));
			}
			Collections.sort(academyEnrollBatchlist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((AcademyEnrollBatch) arg0).getEnrollmentName();
					String name2 = ((AcademyEnrollBatch) arg1).getEnrollmentName();
					return cmp.compare(name2, name1);
				}
			});
			
		} catch (Exception e) {
	
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	
	//查询未关联（设置）院校招生批次的 全局招生批次
	@Action(value="show_other_global_enroll_batchs",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String findOtherGlobalBatchs()throws Exception{
		List<AcademyEnrollBatch> batchlist = new ArrayList<AcademyEnrollBatch>();
		batchlist = academyEnrollBatchBiz.findByAcademyIdAndFlag(academyId);
		if(batchlist!=null&&batchlist.size()>0){
			Object [] objects= new Object[batchlist.size()+1];
			objects[0] = Constants.DELETE_FALSE;
			for(int i=0;i<batchlist.size();i++){
				objects[i+1]=batchlist.get(i).getGlobalEnrollBatchId();
			}
			globalbatchlist = globalEnrollBatchBiz.findOtherGlobalEnrollBatchs(objects);
		}else{
			globalbatchlist = globalEnrollBatchBiz.findAllGlobalEnrollBatchByDeleteFlag();
		}
		
		return "success";
	}
	
	//根据院校ID查询院校招生批次
	@Action(value="list_academy_enroll_batch_by_academyId",
			results={
			@Result(type="json", name = "success",
					params={"contentType","text/json",
							"excludeProperties",
							"academyBatchlist.*.createdTime,"+
							"academyBatchlist.*.updatedTime,"+
							"academyBatchlist.*.isEnable"
							}			
			 		)
		})	
	public String findsubbatch()throws Exception{
		try {
			academyBatchlist = academyEnrollBatchBiz.findBatchNotInFinishedByAcademyId(academyId);
		} catch (Exception e) {
			e.printStackTrace();
			lstrltbool=false;
		}
		return "success";
	}
	
	//根据院校ID查询当前选中院校招生批次以外的院校招生批次(含结束状态)
	@Action(value="list_other_academy_enroll_batch_by_academyId",
			results={
			@Result(type="json", name = "success",
					params={"contentType","text/json",
							"excludeProperties",
							"academyBatchlist.*.createdTime,"+
							"academyBatchlist.*.updatedTime"
							}			
			 		)
		})	
	public String findOtherBatch()throws Exception{
		try {
			academyBatchlist = academyEnrollBatchBiz.findOtherBatchByAcademyId(academyId, batchid);
		} catch (Exception e) {
			e.printStackTrace();
			lstrltbool=false;
		}
		return "success";
	}
	
	//-------------------------------------------------get and set methods--------------------------------
	
	public int getAcademyId() {
		return academyId;
	}
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}
	public List<AcademyEnrollBatch> getAcademyEnrollBatchlist() {
		return academyEnrollBatchlist;
	}
	public void setAcademyEnrollBatchlist(
			List<AcademyEnrollBatch> academyEnrollBatchlist) {
		this.academyEnrollBatchlist = academyEnrollBatchlist;
	}
	public boolean isLstrltbool() {
		return lstrltbool;
	}
	public void setLstrltbool(boolean lstrltbool) {
		this.lstrltbool = lstrltbool;
	}
	public List<GlobalEnrollBatch> getGlobalbatchlist() {
		return globalbatchlist;
	}
	public void setGlobalbatchlist(List<GlobalEnrollBatch> globalbatchlist) {
		this.globalbatchlist = globalbatchlist;
	}
	public List<AcademyEnrollBatch> getAcademyBatchlist() {
		return academyBatchlist;
	}
	public void setAcademyBatchlist(List<AcademyEnrollBatch> academyBatchlist) {
		this.academyBatchlist = academyBatchlist;
	}
	public int getBatchid() {
		return batchid;
	}
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
}
