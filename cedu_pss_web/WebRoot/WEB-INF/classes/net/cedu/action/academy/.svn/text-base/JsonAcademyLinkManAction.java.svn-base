package net.cedu.action.academy;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyLinkManBiz;
import net.cedu.entity.academy.AcademyLinkMan;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json院校联系人管理
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonAcademyLinkManAction extends BaseAction {

	@Autowired
	private AcademyLinkManBiz academylinkmanbiz;              //院校Biz
	private AcademyLinkMan academylinkman;                    //院校联系人实体
	private List<AcademyLinkMan> academylinkmanlist=new ArrayList<AcademyLinkMan>();
	private int id;                                           //院校ID
	private int linkmanId;                                    //联系人Id
	private int academyId;                                    //院校ID

	/**
	 * 删除院校联系人
	 * @return
	 * @throws Exception
	 */
	@Action(value = "deleteacademylinkman", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String DeleteAcademyLinkMan() throws Exception {
		// 查询集合
		try {
			//执行删除操作
			academylinkmanbiz.deleteAcademyLinkMan(linkmanId);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
		/**
		 * 按照院校查询联系人
		 * @return
		 * @throws Exception
		 */
		@Action(value = "list_link_man", results = { @Result(name = "success", type = "json", params = {
				"contentType", "text/json" }) })
		public String ListLinkMan() throws Exception {
			// 查询集合
			try 
			{
				if(academyId>0)
				{
					academylinkmanlist=academylinkmanbiz.findAcademyLinkManByAcademyId(academyId);
				}
				if(academylinkmanlist!=null && academylinkmanlist.size()>0)
				{
					Collections.sort(academylinkmanlist, new Comparator() {
						public int compare(Object arg0, Object arg1) {
							Comparator cmp = Collator
									.getInstance(java.util.Locale.CHINA);
							String name1 = ((AcademyLinkMan) arg0).getName();
							String name2 = ((AcademyLinkMan) arg1).getName();
							return cmp.compare(name1, name2);
						}
					});
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return SUCCESS;
		}
	
	
	
	
	
	
	
	
	
	/**
	 * 修改院校联系人
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updateacademylinkman", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String UpdateAcademyUsingStatus() throws Exception {
		
		try {
			//获取修改前的数据数据
			AcademyLinkMan academylm=academylinkmanbiz.findAcademyLinkManId(linkmanId);
			if(academylinkman.getName()!=null && !academylinkman.getName().equals(""))
			{academylm.setName(academylinkman.getName());};
			if(academylinkman.getPosition()!=null && !academylinkman.getPosition().equals(""))
			{academylm.setPosition(academylinkman.getPosition());};
			if(academylinkman.getDuty()!=null && !academylinkman.getDuty().equals(""))
			{academylm.setDuty(academylinkman.getDuty());};
			if(academylinkman.getTelephone()!=null && !academylinkman.getTelephone().equals(""))
			{academylm.setTelephone(academylinkman.getTelephone());};
			if(academylinkman.getMobile()!=null && !academylinkman.getMobile().equals(""))
			{academylm.setMobile(academylinkman.getMobile());};
			if(academylinkman.getEmail()!=null && !academylinkman.getEmail().equals(""))
			{academylm.setEmail(academylinkman.getEmail());};
            academylm.setUpdaterId(super.getUser().getUserId());
			academylm.setUpdatedTime(new Date());
			//执行修改操作
			academylinkmanbiz.updateAcademyLinkMan(academylm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLinkmanId() {
		return linkmanId;
	}

	public void setLinkmanId(int linkmanId) {
		this.linkmanId = linkmanId;
	}

	public AcademyLinkMan getAcademylinkman() {
		return academylinkman;
	}

	public void setAcademylinkman(AcademyLinkMan academylinkman) {
		this.academylinkman = academylinkman;
	}


	public List<AcademyLinkMan> getAcademylinkmanlist() {
		return academylinkmanlist;
	}


	public void setAcademylinkmanlist(List<AcademyLinkMan> academylinkmanlist) {
		this.academylinkmanlist = academylinkmanlist;
	}

	public int getAcademyId() {
		return academyId;
	}
	
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	


	 
	
}
