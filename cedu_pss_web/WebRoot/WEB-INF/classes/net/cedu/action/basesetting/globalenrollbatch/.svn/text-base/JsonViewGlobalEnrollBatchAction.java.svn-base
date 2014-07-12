package net.cedu.action.basesetting.globalenrollbatch;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.entity.basesetting.GlobalEnrollBatch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewGlobalEnrollBatchAction extends BaseAction{

	private static final long serialVersionUID = 3484139905883793051L;

	@Autowired
	private GlobalEnrollBatchBiz batchlistbiz;
	
	//全局招生批次列表
	private List<GlobalEnrollBatch> globalbatchlist;
	
	//判断页面加载列表是否正常
	private boolean lstrltbool=true;
	
	/**
	 * 查询所有全局招生批次 BY  HXJ
	 * @return
	 * @throws Exception
	 */
	@Action(value="listglobalbatch",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()throws Exception{
		
		try {
			globalbatchlist=batchlistbiz.findAllGlobalEnrollBatchs();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	
	/**
	 * 查询所有全局招生批次(delete_flag=0)   BY  HXJ
	 */
	@Action(value="listglobalbatchbydeleteflag",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								"excludeProperties",
								"globalbatchlist.*.createdTime,"+
								"globalbatchlist.*.updatedTime"
								}
					   )
			})	
	public String showGlobalBatchListByDeleteFlag()throws Exception{
		
		try {
			globalbatchlist=batchlistbiz.findAllGlobalEnrollBatchByDeleteFlag();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	//-----------------------get and set-------------------

	public List<GlobalEnrollBatch> getGlobalbatchlist() {
		return globalbatchlist;
	}

	public void setGlobalbatchlist(List<GlobalEnrollBatch> globalbatchlist) {
		this.globalbatchlist = globalbatchlist;
	}

	public boolean isLstrltbool() {
		return lstrltbool;
	}

	public void setLstrltbool(boolean lstrltbool) {
		this.lstrltbool = lstrltbool;
	}
	
}
