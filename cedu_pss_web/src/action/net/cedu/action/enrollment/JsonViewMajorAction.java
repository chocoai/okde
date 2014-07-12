package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.entity.enrollment.Major;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewMajorAction extends BaseAction{

	private static final long serialVersionUID = 4571564408542236854L;

	@Autowired
	private MajorBiz majorbiz;
	
	private int academyId;
	private List<Major> majorlst;//专业list
	private boolean lstrltbool=true;//判断页面加载列表是否正常
	

	/**
	 * 查询所有专业信息   BY  HXJ
	 */
	@SuppressWarnings("unchecked")
	@Action(value="list_view_major",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()throws Exception{
		try {
			majorlst = majorbiz.findAllMajors(academyId);
			Collections.sort(majorlst, new Comparator() {
				public int compare(Object obj0, Object obj1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Major) obj0).getName();
					String name2 = ((Major) obj1).getName();
					return cmp.compare(name1, name2);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}
	
	/**
	 * 查询所有专业信息(delete_flag=0)   BY  HXJ
	 */
	@SuppressWarnings("unchecked")
	@Action(value="list_major_by_deleteflag",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								"excludeProperties",
								"majorlst.*.createdTime,"+
								"majorlst.*.updatedTime," +
								"majorlst.*.academyId," +
								"majorlst.*.creatorId," +
								"majorlst.*.deleteFlag," +
								"majorlst.*.updaterId"
								}			
				 		)
			})	
	public String showModeListByDeleteFlag()throws Exception{
		try {
			majorlst = majorbiz.findAllMajorsByDeleteFlag(academyId);
//			majorlst = majorbiz.findAllBelongMajorNames(majorlst);
//			Collections.sort(majorlst, new Comparator() {
//				public int compare(Object obj0, Object obj1) {
//					Comparator cmp = Collator
//							.getInstance(java.util.Locale.CHINA);
//					String name1 = ((Major) obj0).getName();
//					String name2 = ((Major) obj1).getName();
//					return cmp.compare(name1, name2);
//				}
//			});
		} catch (Exception e) {
	
			e.printStackTrace();
			lstrltbool=false;
		}
		
		return "success";
	}

	
	//-------------------------------------------------get and set methods--------------------------------
	
	public List<Major> getMajorlst() {
		return majorlst;
	}
	public void setMajorlst(List<Major> majorlst) {
		this.majorlst = majorlst;
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
	
	
}
