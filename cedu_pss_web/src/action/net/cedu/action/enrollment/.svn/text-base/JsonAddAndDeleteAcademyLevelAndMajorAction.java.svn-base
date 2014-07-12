package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.Major;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddAndDeleteAcademyLevelAndMajorAction extends BaseAction{

	private static final long serialVersionUID = 8892040161603356833L;

	@Autowired
	private AcademyLevelBiz academyLevelBiz;
	@Autowired
	private AcademyMajorBiz academyMajorBiz; 
	@Autowired
	private StudentBiz studentBiz;//学生实体_业务层
	@Autowired
	private MajorBiz majorBiz;//专业_业务层
	
	private AcademyLevel academyLevel;//层次实体(院校)
	private AcademyMajor academyMajor;// 专业实体(院校)
	private int alllevelid[];//接收页面传过来的Levelid集合
	private int allmajorids[];//接收页面传过来的majorid集合
	private int copybatchid;//被复制层次所属招生批次id
	private int presentbatchid;//当前层次所属招生批次id
	private int allcopylevelids[];//保存选中的被复制的levelid
	private int allcopymajorids[];//保存选中的被复制的majorid
	private boolean copyrltbool=true;//判断复制操作是否成功
	private boolean addrltbool=true;//判断添加操作是否成功
	
	private boolean isback=false;
	private String majorNames="";//专业名称
	
	
	//添加层次(院校)
	@SuppressWarnings("unchecked")
	@Action(value="add_academy_level",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		int userid = super.getUser().getUserId();
	
		try{
			if(academyLevel!=null){
				//删除某一招生批次下的部分层次对应的专业
				Map cancelmap = new HashMap(); //需要删除的层次
				Map addmap = new HashMap();    //需要添加的层次
				List<AcademyLevel> levellst= new ArrayList<AcademyLevel>();
				levellst=academyLevelBiz.findBatchId(academyLevel.getAcademyEnrollBatchId());
				int []nowobjs = null;
				//把当前批次下现有的院校层次id遍历到数组nowobjs中
				if(levellst!=null&&levellst.size()>0){
					nowobjs = new int[levellst.size()];
					for(int i=0,len=levellst.size();i<len;i++){
						nowobjs[i] = levellst.get(i).getLevelId();
					}
				}
				if (nowobjs!=null&&nowobjs.length>0) {
					//把数组nowobjs存到Map cancelmap中
					for (int k = 0,len=nowobjs.length; k < len; k++) {
						cancelmap.put(nowobjs[k], nowobjs[k]);
					}
					//循环遍历把需要删除和添加的院校层次存到对应的map中
					for(int i=0,len=alllevelid.length;i<len;i++){
						boolean issame=true;
						for(int j=0,lenth=nowobjs.length;j<lenth;j++){
							if(alllevelid[i]==nowobjs[j]){
								cancelmap.remove(alllevelid[i]);
								issame=false;
								break;
							}
						}
						if(issame)
						{
							addmap.put(alllevelid[i], alllevelid[i]);
						}
					}
				}
				
				if (cancelmap!=null&&cancelmap.size()>0) {
					//删除某一招生批次下的部分层次
					//1.查询要删除的院校层次
					List<AcademyLevel> collegelist = new ArrayList<AcademyLevel>();
					//mapToArray()辅助方法把map转成array
					collegelist = academyLevelBiz
							.findByBatchIdAndLevelid(mapToArray(academyLevel
									.getAcademyEnrollBatchId(), cancelmap));
					//2.批量删除院校层次下对应的专业
					academyMajorBiz.deleteAcademyMajorByAcademyLevelId(collegelist);
					//3.把ID拼接成字符串
					StringBuffer sb = new StringBuffer();
					if (collegelist != null && collegelist.size() > 0) {

						for (int i = 0,len=collegelist.size(); i < len; i++) {
							sb.append(collegelist.get(i).getId());
							if (i != collegelist.size() - 1) {
								sb.append(",");
							}
						}
						//4.批量删除院校层次
						academyLevelBiz.deleteByIds(sb.toString());
					}
				}
				
			
					//各实体各参数赋值	
					academyLevel.setDeleteFlag(Constants.DELETE_FALSE);
					academyLevel.setCreatorId(userid);
					academyLevel.setCreatedTime(new Date());
					academyLevel.setUpdaterId(userid);
					academyLevel.setUpdatedTime(new Date());
					
					//某批次数据库院校层次为空
					if(levellst==null||levellst.size()<=0){
						addrltbool = academyLevelBiz.addAcademyLevels(alllevelid, academyLevel);
					}//数据库不为空，且没有需要添加或删除的层次时
					else if((addmap==null||addmap.size()<=0)&&(cancelmap==null||cancelmap.size()<0)){
						addrltbool=true;
					}//其他情况(数据库有数据：1.只添加；2.只删除；3.同时进行添加和删除...)
					else{
						addrltbool = academyLevelBiz.addAcademyLevels(mapToArray(0, addmap), academyLevel);		
					}
			}			
		}catch (Exception e) {
		
			e.printStackTrace();
			addrltbool=false;
		}
		
		return SUCCESS;
	}
		

		//添加专业(院校)
		@Action(value="add_academy_major",
				results={
					@Result(type="json", name = "success",
							params={"contentType","text/json"}
						   )
				})	
		public String saveAcademyMajor(){
			
			try {
				if (academyMajor != null && !("").equals(academyMajor)) {
					//检查专业下是否有学生再更新层次下的专业
					List<AcademyMajor> amlist=academyMajorBiz.findByLevel(academyMajor.getAcademyLevelId());
					if(amlist!=null && amlist.size()>0 && allmajorids.length>0)
					{
						boolean ishave=false;
						for(AcademyMajor am:amlist)
						{
							ishave=false;
							for(int i=0;i<allmajorids.length;i++)
							{
								if(am.getMajorId()==allmajorids[i])
								{
									ishave=true;
									break;
								}
							}
							if(!ishave)
							{
								AcademyLevel aml=this.academyLevelBiz.findById(academyMajor.getAcademyLevelId());
								if(aml!=null)
								{
									int count=this.studentBiz.findStudentCountByDeleteAcademyMajor(aml.getAcademyEnrollBatchId(), aml.getLevelId(), am.getMajorId());
									if(count>0)
									{
										isback=true;
										Major major=this.majorBiz.findMajorById(am.getMajorId());
										if(major!=null)
										{
											if(majorNames.equals(""))
											{
												majorNames=major.getName();
											}
											else
											{
												majorNames+="、"+major.getName();
											}
										}
									}
								}
							}
						}
					}
					if(isback)
					{
						return SUCCESS;
					}
					
					//更新层次下的专业
					int userid = super.getUser().getUserId();
					//删除层次下的所有专业
					academyMajorBiz
							.deleteAcademyMajorByAcademyLevelId(academyMajor
									.getAcademyLevelId());
					//给参数赋值
					academyMajor.setCreatorId(userid);
					academyMajor.setUpdaterId(userid);
					//批量添加选中的专业	
					 academyMajorBiz.addSeveralAcademyMajor(academyMajor,allmajorids);
				}
			} catch (Exception e) {
				e.printStackTrace();
				addrltbool=false;
			}
			return SUCCESS;
		}
	
		//复制层次和专业(院校)
		@Action(value="copy_academy_level_and_major",
				results={
					@Result(type="json", name = "success",
							params={"contentType","text/json"}
						   )
				})	
		public String copyAcademyLevelAndMajor(){
			try {
				int userid = super.getUser().getUserId();
				academyLevelBiz
						.addAcademyLevelAndMajorfromOtherEnrollmentBatch(
								userid, presentbatchid, copybatchid, allcopylevelids);
			} catch (Exception e) {
				e.printStackTrace();
				copyrltbool=false;
			}
			return SUCCESS;
		}
		
		//复制专业(院校)
		@Action(value="copy_academy_major",
				results={
					@Result(type="json", name = "success",
							params={"contentType","text/json"}
						   )
				})	
		public String copyAcademyMajor(){
			try {
				int userid = super.getUser().getUserId();
				academyMajor.setCreatorId(userid);
				academyMajor.setUpdaterId(userid);
				academyMajorBiz
						.addSeveralAcademyMajor(academyMajor, allcopymajorids);
			} catch (Exception e) {
				e.printStackTrace();
				copyrltbool=false;
			}
			return SUCCESS;
		}
	//---------------------------------辅助方法--------------------------------------
		@SuppressWarnings("unchecked")
		//把map转成array
		public int[] mapToArray(int id,Map map){
			int obj[]=null;
			if(id>0){
				obj = new int[map.size()+1];
				obj[0]=id;
				int i=1;
				if (map!=null&&map.size()>0) {
					Iterator   vIt=map.values().iterator();
					while(vIt.hasNext()){
						Object   v   =   vIt.next();
							obj[i]=Integer.parseInt(v.toString());
							i++;
					}
				}
			}else{
				int i=0;
				obj = new int[map.size()];
				if (map!=null&&map.size()>0) {
					Iterator   vIt=map.values().iterator();
					while(vIt.hasNext()){
						Object   v   =   vIt.next();
							obj[i]=Integer.parseInt(v.toString());
							i++;
					}
				}
			}
			
			return obj;
		}
	
	//-----------------------------------------get and set methods------------------------------
	
	public AcademyLevel getAcademyLevel() {
		return academyLevel;
	}

	public void setAcademyLevel(AcademyLevel academyLevel) {
		this.academyLevel = academyLevel;
	}

	public boolean isAddrltbool() {
		return addrltbool;
	}

	public void setAddrltbool(boolean addrltbool) {
		this.addrltbool = addrltbool;
	}

	public AcademyMajor getAcademyMajor() {
		return academyMajor;
	}
	
	public void setAcademyMajor(AcademyMajor academyMajor) {
		this.academyMajor = academyMajor;
	}

	public int[] getAlllevelid() {
		return alllevelid;
	}

	public void setAlllevelid(int[] alllevelid) {
		this.alllevelid = alllevelid;
	}

	public int[] getAllmajorids() {
		return allmajorids;
	}

	public void setAllmajorids(int[] allmajorids) {
		this.allmajorids = allmajorids;
	}

	public int getCopybatchid() {
		return copybatchid;
	}

	public void setCopybatchid(int copybatchid) {
		this.copybatchid = copybatchid;
	}

	public int getPresentbatchid() {
		return presentbatchid;
	}

	public void setPresentbatchid(int presentbatchid) {
		this.presentbatchid = presentbatchid;
	}

	public int[] getAllcopylevelids() {
		return allcopylevelids;
	}

	public void setAllcopylevelids(int[] allcopylevelids) {
		this.allcopylevelids = allcopylevelids;
	}

	public boolean isCopyrltbool() {
		return copyrltbool;
	}

	public void setCopyrltbool(boolean copyrltbool) {
		this.copyrltbool = copyrltbool;
	}

	public int[] getAllcopymajorids() {
		return allcopymajorids;
	}

	public void setAllcopymajorids(int[] allcopymajorids) {
		this.allcopymajorids = allcopymajorids;
	}


	public boolean isIsback() {
		return isback;
	}


	public void setIsback(boolean isback) {
		this.isback = isback;
	}


	public String getMajorNames() {
		return majorNames;
	}


	public void setMajorNames(String majorNames) {
		this.majorNames = majorNames;
	}

}
