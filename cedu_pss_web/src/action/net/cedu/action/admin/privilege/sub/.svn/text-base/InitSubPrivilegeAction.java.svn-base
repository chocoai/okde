package net.cedu.action.admin.privilege.sub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.SubPrivilegeBiz;
import net.cedu.entity.admin.privilege.SubPrivilege;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationManager;
import com.opensymphony.xwork2.config.RuntimeConfiguration;
import com.opensymphony.xwork2.config.entities.ActionConfig;

/**
 * 初始化权限子项及模块
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class InitSubPrivilegeAction extends BaseAction 
{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SubPrivilegeBiz subPrivilegeBiz;
	
	private List<String> prolst=new ArrayList<String>();
	private List<String> modlst=new ArrayList<String>();
	private SubPrivilege subPrivilege;
	
	private boolean results=false;
	
	@Action(value = "init_sub_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			Dispatcher dispatcher = Dispatcher.getInstance();
	
			ConfigurationManager cm = dispatcher.getConfigurationManager();
	
			Configuration cf = cm.getConfiguration();
	
			RuntimeConfiguration rc = cf.getRuntimeConfiguration();
	
			Map<String, Map<String, ActionConfig>> d = rc.getActionConfigs();
			
			for (String key1 : d.keySet())
			{
				if(!"config-browser".equals(key1.replace("/","")))
					if(null!=key1&&1<key1.length())
						if(!"json".equals(key1.replace("/","")))
							modlst.add(key1);
			}
			
			for (String key1 : d.keySet())
			{
				Map<String, ActionConfig> a = d.get(key1);
				for (String key2 : a.keySet())
				{
					if(!"config-browser".equals(key1.replace("/","")))
						if(null!=key2&&0<key2.length())
							if(!"json".equals(key2))
							{
								//带发布工程名
//								prolst.add(request.getContextPath()+key1+"/"+key2);
								//不带发布工程名
								prolst.add(key1+"/"+key2);
							}
				}
			}
			for(int i=0,ic=prolst.size();i<ic;i++)
			{
				subPrivilege=new SubPrivilege();
				subPrivilege.setFullPath(prolst.get(i));
				subPrivilegeBiz.addNew(subPrivilege);
			}
			System.out.println(prolst.size());
			results=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public boolean getResults() {
		return results;
	}
}
