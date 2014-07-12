package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.Major;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewAcademyLevelAndMajorAction extends BaseAction{

	private static final long serialVersionUID = 5683205995159606781L;

	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//院校招生批次
	@Autowired
	private AcademyMajorBiz academyMajorBiz;//专业(院校批次)
	@Autowired
	private LevelBiz levelBiz;//层次(基础)
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//层次(院校)
	@Autowired
	private MajorBiz majorBiz;//专业(院校基础设置)
	
	private int academyId;//院校ID
	private int academyBatchId;//院校批次ID
	private int academyLevelId;//层次ID(院校批次)
	private int copyAcademyLevelId;//被复制层次ID(院校批次)
	private int copybatchid;//被复制层次所属招生批次id
	private List<AcademyEnrollBatch> academyEnrollBatchList;//院校招生批次列表
	private int [] academylevelarray;//层次(院校批次)ID集合
	private int [] academymajorarray;//专业(某个院校层次)id集合
	private List<Level> levellist;//层次(基础)列表
	private List<Major> majorlist;//专业(院校基础设置)列表
	private List<AcademyLevel> academylevellist;//院校层次列表
	private List<AcademyMajor> academymajorlist;//院校专业列表
	private boolean lstrltbool=true;//判断页面加载列表是否正常
	private Level level;//基础层次
	
	//院校招生批次+层次(院校批次)+专业(院校批次)列表(flag=0)
	@SuppressWarnings("unchecked")
	@Action(value="list_academy_level_major_by_flag",
			results={
			@Result(type="json", name = "success",
					params={"contentType","text/json",
					"includeProperties",
					"academyEnrollBatchList.*,academyId,lstrltbool"
							}			
			 		)
		})	
		public String findMajorAndLevel()throws Exception{
			try {
				//根据院校ID获取该院校对应的全部院校招生批次
				academyEnrollBatchList = academyEnrollBatchBiz
						.findBatchNotInFinishedByAcademyId(academyId);
				//接收处理后的 专业(院校)名称
				String names = "";
				List<AcademyLevel> academyLeveList = new ArrayList<AcademyLevel>();
				if (academyEnrollBatchList != null
						&& academyEnrollBatchList.size() > 0) {
					//根据获取的院校批次ID 循环获取其对应的层次(院校批次),然后循环赋给List<AcademyEnrollBatch>
					academyEnrollBatchList = academyEnrollBatchBiz
							.addAcademyLevelforAcademyEnrollBatch(academyEnrollBatchList);

					for (int i = 0; i < academyEnrollBatchList.size(); i++) {
						//循环获取每个院校批次下的层次List
						academyLeveList = academyEnrollBatchList.get(i)
								.getAcademyLevelList();
						//调用方法查询层次(院校)对应 层次(基础设置)，并添加到AcademyLevelList里
						levelBiz.addLevelforAcademyLevel(academyLeveList);

						if (academyLeveList != null
								&& academyLeveList.size() > 0) {
							for (int j = 0; j < academyLeveList.size(); j++) {
								//循环每个院校批次下的层次List,获取其对应的全部专业(院校)名称(处理后的)
								names = academyMajorBiz
										.findMajorNameByLevelId(academyLeveList
												.get(j).getId());
								//处理后的 专业(院校)名称 循环赋给对应的层次（院校）
								academyLeveList.get(j).setAcademyMajorNames(
										names);
							}
						}
					}
					
					Collections.sort(academyEnrollBatchList, new Comparator() {
						public int compare(Object arg0, Object arg1) {
							Comparator cmp = Collator
									.getInstance(java.util.Locale.CHINA);
							String name1 = ((AcademyEnrollBatch) arg0).getRegisterName();
							String name2 = ((AcademyEnrollBatch) arg1).getRegisterName();
							return cmp.compare(name2, name1);
						}
					});
					
				} else {
					lstrltbool = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				lstrltbool=false;
			}
		return "success";
	}
	
		//查询设置过的层次(基础设置)
		@Action(value="list_other_levels_by_flag",
				results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								}			
				 		)
			})	
		public String execute()throws Exception{
			List<AcademyLevel> academylevelist = new ArrayList<AcademyLevel>();
			academylevelist = academyLevelBiz.findBatchId(academyBatchId);
			if(academylevelist!=null&&academylevelist.size()>0){
				Object [] objects= new Object[academylevelist.size()+1];
				objects[0]=Constants.DELETE_FALSE;
				for(int i=0;i<academylevelist.size();i++){
					objects[i+1]=academylevelist.get(i).getLevelId();
				}
				levellist = levelBiz.findOtherLevels(objects);
			}else{
				levellist = levelBiz.findAllLevelsByDeleteFlag();
			}
			
			return "success";
	}
		
		//查询设置过的专业(基础设置)
		@Action(value="list_other_Majors_by_flag",
				results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								}			
				 		)
			})	
		public String showOtherMajors()throws Exception{
			List<AcademyMajor> academyMajor = new ArrayList<AcademyMajor>();
			academyMajor = academyMajorBiz.findByLevel(academyLevelId);
			if(academyMajor!=null&&academyMajor.size()>0){
				Object [] objects= new Object[academyMajor.size()+2];
				objects[0]=academyId;
				objects[1]=Constants.DELETE_FALSE;
				for(int i=0;i<academyMajor.size();i++){
					objects[i+2]=academyMajor.get(i).getMajorId();
				}
				
				majorlist = majorBiz.findOtherMajors(academyId, objects);
			}else{
				majorlist = majorBiz.findAllMajorsByDeleteFlag(academyId);
			}
			
			return "success";
	 }
		
		/**
		 * 查询所有层次信息(delete_flag=0)   BY  HXJ
		 */
		@Action(value="list_all_level_by_deleteflag",
				results={
					@Result(type="json", name = "success",
							params={"contentType","text/json",
									"excludeProperties",
									"levellist.*.createdTime,"+
									"levellist.*.updatedTime",
									"includeProperties",
									"academyBatchId,academylevelarray.*,levellist.*,lstrltbool"
									}			
					 		)
				})	
		public String showLevelListByDeleteFlag()throws Exception{
			try {
				List<AcademyLevel> list= new ArrayList<AcademyLevel>();
				list = academyLevelBiz.findBatchId(academyBatchId);
				if(list!=null&&list.size()>0){
					academylevelarray = new int[list.size()];
					for(int i=0;i<list.size();i++){
						academylevelarray[i] = list.get(i).getLevelId();
					}
				}
				levellist = levelBiz.findAllLevelsByDeleteFlag();
			} catch (Exception e) {
			
				e.printStackTrace();
				lstrltbool=false;
			}
			
			return "success";
		}
		
		//查询所有的基础专业(院校基础设置)
		@Action(value="list_all_majors_by_flag",
				results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								"excludeProperties",
								"majorlist.*.createdTime,"+
								"majorlist.*.updatedTime",
								"includeProperties",
								"academymajorarray.*,academyLevelId,majorlist.*,academyId,lstrltbool"
								}			
				 		)
			})	
		@SuppressWarnings("unchecked")
		public String showAllBaseMajors()throws Exception{
			 	
			try {
				academymajorarray = academyMajorBiz.findAcademyMajorIdsByAcademyLevelId(academyLevelId);
				majorlist = majorBiz.findAllMajorsByDeleteFlag(academyId);
				
				Collections.sort(majorlist, new Comparator() {
					public int compare(Object obj0, Object obj1) {
					Comparator cmp = Collator
					.getInstance(java.util.Locale.CHINA);
					String name1 = ((Major) obj0).getName();
					String name2 = ((Major) obj1).getName();
					return cmp.compare(name1, name2);
					}
				});
				
				
			} catch (Exception e) {
				lstrltbool=false;
			}
			return "success";
	 }
		
	 //查询某招生批次对应的全部层次
		@Action(value="list_copy_level_by_deleteflag",
				results={
					@Result(type="json", name = "success",
							params={"contentType","text/json",
									"excludeProperties",
									"academylevellist.*.createdTime,"+
									"academylevellist.*.updatedTime"
									}			
					 		)
				})	
		public String showLevelsByBatchId()throws Exception{
			try {
				List<AcademyLevel> list= new ArrayList<AcademyLevel>();
				list = academyLevelBiz.findBatchId(academyBatchId);
				if(list!=null&&list.size()>0){
					academylevelarray = new int[list.size()];
					for(int i=0;i<list.size();i++){
						academylevelarray[i] = list.get(i).getLevelId();
					}
				}
				academylevellist = academyLevelBiz.findAcademyLevelNameBatchId(copybatchid);
			} catch (Exception e) {
			
				e.printStackTrace();
				lstrltbool=false;
			}
			
			return "success";
		}
		
		 //查询某招生批次对应的全部专业
		@Action(value="list_copy_major_by_deleteflag",
				results={
					@Result(type="json", name = "success",
							params={"contentType","text/json",
									"excludeProperties",
									"academylevellist.*.createdTime,"+
									"academylevellist.*.updatedTime"
									}			
					 		)
				})	
		public String showMajorsByBatchId()throws Exception{
			try {
				academymajorarray= academyMajorBiz.findAcademyMajorIdsByAcademyLevelId(academyLevelId);
				academymajorlist = academyMajorBiz.findAcademyMajorByLevel(copyAcademyLevelId);
			} catch (Exception e) {
			
				e.printStackTrace();
				lstrltbool=false;
			}
			
			return "success";
		}
		
		//查询某招生批次对应的全部层次
		@Action(value="list_levels_by_deleteflag",
				results={
					@Result(type="json", name = "success",
							params={"contentType","text/json",
									"excludeProperties",
									"academylevellist.*.createdTime,"+
									"academylevellist.*.updatedTime"
									}			
					 		)
				})	
		public String showAllLevelsByBatchId()throws Exception{
			try {
				
				if (level!=null&&level.getName()!=null&&!("").equals(level.getName())&&level.getId()>0) {
					AcademyLevel schlevel = new AcademyLevel();
					schlevel = academyLevelBiz.findByBatchAndLevel(copybatchid, level.getId());
					if (schlevel!=null) {
						academylevellist = new ArrayList<AcademyLevel>();
						academylevellist.add(schlevel);
						if (academylevellist!=null&&academylevellist.size()>0) {
							academylevellist.get(0).setLevelName(level.getName());
						}
					}
				}else{
					lstrltbool=false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				lstrltbool=false;
			}
			
			return "success";
		}
		
	//-------------------------------------------------get and set methods--------------------------------

	public List<AcademyEnrollBatch> getAcademyEnrollBatchList() {
		return academyEnrollBatchList;
	}

	public void setAcademyEnrollBatchList(
			List<AcademyEnrollBatch> academyEnrollBatchList) {
		this.academyEnrollBatchList = academyEnrollBatchList;
	}
 
	public int[] getAcademylevelarray() {
		return academylevelarray;
	}

	public void setAcademylevelarray(int[] academylevelarray) {
		this.academylevelarray = academylevelarray;
	}

	public boolean isLstrltbool() {
		return lstrltbool;
	}

	public void setLstrltbool(boolean lstrltbool) {
		this.lstrltbool = lstrltbool;
	}
	
	public int getAcademyId() {
		return academyId;
	}
	
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getAcademyBatchId() {
		return academyBatchId;
	}

	public void setAcademyBatchId(int academyBatchId) {
		this.academyBatchId = academyBatchId;
	}

	public List<Level> getLevellist() {
		return levellist;
	}

	public void setLevellist(List<Level> levellist) {
		this.levellist = levellist;
	}

	public List<Major> getMajorlist() {
		return majorlist;
	}

	public void setMajorlist(List<Major> majorlist) {
		this.majorlist = majorlist;
	}

	public int getAcademyLevelId() {
		return academyLevelId;
	}

	public void setAcademyLevelId(int academyLevelId) {
		this.academyLevelId = academyLevelId;
	}

	public int[] getAcademymajorarray() {
		return academymajorarray;
	}

	public void setAcademymajorarray(int[] academymajorarray) {
		this.academymajorarray = academymajorarray;
	}

	public List<AcademyLevel> getAcademylevellist() {
		return academylevellist;
	}

	public void setAcademylevellist(List<AcademyLevel> academylevellist) {
		this.academylevellist = academylevellist;
	}

	public int getCopybatchid() {
		return copybatchid;
	}

	public void setCopybatchid(int copybatchid) {
		this.copybatchid = copybatchid;
	}

	public int getCopyAcademyLevelId() {
		return copyAcademyLevelId;
	}

	public void setCopyAcademyLevelId(int copyAcademyLevelId) {
		this.copyAcademyLevelId = copyAcademyLevelId;
	}

	public List<AcademyMajor> getAcademymajorlist() {
		return academymajorlist;
	}

	public void setAcademymajorlist(List<AcademyMajor> academymajorlist) {
		this.academymajorlist = academymajorlist;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	} 
}
