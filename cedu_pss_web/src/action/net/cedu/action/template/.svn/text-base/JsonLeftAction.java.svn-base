package net.cedu.action.template;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UGroupUserBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.admin.privilege.GroupPrivilegeBiz;
import net.cedu.biz.admin.privilege.ModularBiz;
import net.cedu.biz.admin.privilege.PrivilegeBiz;
import net.cedu.biz.admin.privilege.PrivilegeSetBiz;
import net.cedu.biz.admin.privilege.SubSystemBiz;
import net.cedu.common.string.StringUtil;
import net.cedu.entity.admin.UGroupUser;
import net.cedu.entity.admin.User;
import net.cedu.entity.admin.privilege.GroupPrivilege;
import net.cedu.entity.admin.privilege.Modular;
import net.cedu.entity.admin.privilege.Privilege;
import net.cedu.entity.admin.privilege.PrivilegeSet;
import net.cedu.entity.admin.privilege.SubSystem;
import net.cedu.model.admin.TreeModular;
import net.cedu.model.admin.TreePrivilegeSet;
import net.cedu.model.admin.TreeSubSystem;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 左侧菜单
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonLeftAction extends BaseAction 
{
	private static final long serialVersionUID = 817750947028944890L;
	
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private UGroupUserBiz uGroupUserBiz;
	@Autowired
	private SubSystemBiz subSystemBiz;
	@Autowired
	private ModularBiz modularBiz;
	@Autowired
	private PrivilegeSetBiz privilegeSetBiz;
	@Autowired
	private PrivilegeBiz privilegeBiz;
	@Autowired
	private GroupPrivilegeBiz groupPrivilegeBiz;
	
	List<TreeSubSystem> trslist=new ArrayList<TreeSubSystem>();
	
	@Action(value = "tree_left", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			User user=userBiz.findUserById(getUser().getUserId());
			createLeft(user);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 创建左侧菜单
	 * @param user
	 * @throws Exception
	 */
	private void createLeft(User user)throws Exception
	{
		UGroupUser uGrouUser=new UGroupUser();
		uGrouUser.setUserId(user.getId());
		
		String subSystemIds="";
		String modualrIds="";
		String pSetIds="";
		String privilegeIds="";
		
		List<String> subSystemIdlst=new ArrayList<String>();
		List<String> modularIdlst=new ArrayList<String>();
		List<String> pSetIdlst=new ArrayList<String>();
		List<String> privialteIdlst=new ArrayList<String>();
		
		List<UGroupUser> ugulist=uGroupUserBiz.findUGroupUserListByCondition(uGrouUser);
		
		for(int i=0;i<ugulist.size();i++)
		{
			GroupPrivilege gp=groupPrivilegeBiz.findBygroupId(ugulist.get(i).getGroupId());
			if(null!=gp)
			{
				List<String> slst=StringUtil.strToList(gp.getSubSystemIds());
				List<String> mlst=StringUtil.strToList(gp.getModularIds());
				List<String> pslst=StringUtil.strToList(gp.getSetIds());
				List<String> plst=StringUtil.strToList(gp.getPrivilegeIds());
			
				subSystemIdlst.removeAll(slst);  
				subSystemIdlst.addAll(slst);  
				
				modularIdlst.removeAll(mlst);
				modularIdlst.addAll(mlst);
				
				pSetIdlst.removeAll(pslst);
				pSetIdlst.addAll(pslst);
				
				privialteIdlst.removeAll(plst);
				privialteIdlst.addAll(plst);
			}		
		}
		subSystemIds=StringUtil.strlists(subSystemIdlst);
		modualrIds=StringUtil.strlists(modularIdlst);
		pSetIds=StringUtil.strlists(pSetIdlst);
		privilegeIds=StringUtil.strlists(privialteIdlst);
		
		initTreeSubSystem(subSystemIds,modualrIds,pSetIds,privilegeIds);
	}
	
	/**
	 * 生成菜单树
	 * @param subSystemIds
	 * @param modularIds
	 * @param psIds
	 * @param pIds
	 * @throws Exception
	 */
	private void initTreeSubSystem(String subSystemIds,String modularIds,String psIds,String pIds)throws Exception
	{
		int ssId=0;
		//获取子系统
		List<SubSystem> sslist=subSystemBiz.findListByCondition(new SubSystem());
		//循环子系统
		for(int i=0,ic=sslist.size();i<ic;i++)
		{
			int sid=sslist.get(i).getId();
			if(0<=subSystemIds.indexOf("_"+sid+"_"))
			{
				TreeSubSystem trs=new TreeSubSystem();
				//存储子系统树
				trs.setSubSystem(sslist.get(i));
				//获取子系统ID
				ssId=trs.getSubSystem().getId();
				//根据子系统ID获取模块集合
				List<Modular> list=modularBiz.findModularBySubSystemId(ssId);
				//获取模块树集合
				List<TreeModular> trmlist=initTreeModular(list,modularIds,psIds,pIds);
				if(0<trmlist.size())
				{
					//存储模块集合
					trs.setMlist(trmlist);
					//存储子系统树集合
					trslist.add(trs);
				}
			}
		}
	}
	
	/**
	 * 获取模块树集合
	 * @param mlist
	 * @return
	 * @throws Exception
	 */
	private List<TreeModular> initTreeModular(List<Modular> mlist,String modularIds,String psIds,String pIds)throws Exception
	{
		List<TreeModular> trmlist=new LinkedList<TreeModular>();
		int mId=0;
		//循环模块
		for(int j=0,jc=mlist.size();j<jc;j++)
		{
			int mid=mlist.get(j).getId();
			if(0<=modularIds.indexOf("_"+mid+"_"))
			{
				TreeModular trm=new TreeModular();
				//存储模块树
				trm.setModular(mlist.get(j));
				//获取模块ID
				mId=trm.getModular().getId();
				//根据模块ID获取权限集集合
				List<PrivilegeSet> pslist=privilegeSetBiz.findPrivilegeSetByModularId(mId);
				//根据权限集获取权限
				List<TreePrivilegeSet> trpslist=initTreePrivilegeSet(pslist,psIds,pIds);
				if(0<trpslist.size())
				{
					//存储权限集树集合
					trm.setPslist(trpslist);
					trmlist.add(trm);
				}
			}
		}
		return trmlist;
	}
	
	/**
	 * 获取权限集合树集合
	 * @param pslist
	 * @return
	 * @throws Exception
	 */
	private List<TreePrivilegeSet> initTreePrivilegeSet(List<PrivilegeSet> pslist,String psIds,String pIds)throws Exception
	{
		List<TreePrivilegeSet> trpslist=new LinkedList<TreePrivilegeSet>();
		int psId=0;
		//循环权限集
		for(int k=0,kc=pslist.size();k<kc;k++)
		{
			int psid=pslist.get(k).getId();
			if(0<=psIds.indexOf("_"+psid+"_"))
			{
				TreePrivilegeSet trps=new TreePrivilegeSet();
				//存储权限集树
				trps.setPrivilegeSet(pslist.get(k));
				//获取权限集ID
				psId=trps.getPrivilegeSet().getId();
				//根据权限集ID获取权限集合
				List<Privilege> plist=privilegeBiz.findPrivilegeBySetId(psId);
				//存储权限集合
				if(null!=plist&&0<plist.size())
				{
					List<Privilege> adplist=new LinkedList<Privilege>();
					boolean flag=false;
					for(int s=0,sc=plist.size();s<sc;s++)
					{
						int pid=plist.get(s).getId();
						if(0<=pIds.indexOf("_"+pid+"_"))
						{
							if(1==plist.get(s).getIsShow())
							{
								flag=true;
								adplist.add(plist.get(s));
							}
							
						}
					}
//					boolean flag=false;
//					for(int p=0,pc=adplist.size();p<pc;p++)
//					{
//						if(1==adplist.get(p).getIsShow())
//						{
//							flag=true;
//						}
//					}
					if(flag)
					{
						trps.setPlist(adplist);
						trpslist.add(trps);
					}
				}
			}
		}
		return trpslist;
	}

	public List<TreeSubSystem> getTrslist() {
		return trslist;
	}
}
