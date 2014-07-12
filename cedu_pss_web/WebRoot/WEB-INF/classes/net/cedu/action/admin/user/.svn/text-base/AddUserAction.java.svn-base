package net.cedu.action.admin.user;

import java.io.File;
import java.text.SimpleDateFormat;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.admin.User;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 添加用户
 * @author Jack
 *
 */
public class AddUserAction extends BaseAction implements ModelDriven<User> 
{
	private static final long serialVersionUID = -6269671244358345372L;

	@Autowired
	private UserBiz userBiz;
	@Autowired
	private BranchBiz branchBiz;
	
	private User users=new User();
	private File files;                           //文件 
	private String filesFileName;                 //原始文件名称
	
	private String savePath;                      //服务器路径
	
	private boolean results=false;
	
	public String execute()
	{
		try
		{
			if(isGetRequest())
			{			
				users.setOrg(branchBiz.findBranchById(users.getOrgId()));
				return INPUT;
			}
			if(null!=files)
			users.setPhotoUrl(uploadFile(filesFileName));
			users.setCreatorId(getUser().getUserId());
			users.setUpdaterId(users.getCreatorId());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			users.setUpdatePasswordTime(sdf.parse("2011-01-01 00:00:00"));
			results=userBiz.createNew(users);
			if(results)
				addActionMessage(ResourcesTool.getText("admin","add.success"));
			else
				addActionMessage(ResourcesTool.getText("admin", "username.repeat.error"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionMessage(ResourcesTool.getText("admin","add.error"));
		}
		return SUCCESS;
	}
	
	/**
	 * 附件上传_返回
	 * @param name 实际文件名
	 * @return	存储的相对路径
	 */
	private String uploadFile(String name)
	{
		try 
		{
			savePath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("admin","uploadpath"));
			return FileUtil.FileUploads(savePath,name,files);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}
	
	public User getModel() {
		return users;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public boolean getResults() {
		return results;
	}

	public File getFiles() {
		return files;
	}

	public void setFiles(File files) {
		this.files = files;
	}

	public String getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String filesFileName) {
		this.filesFileName = filesFileName;
	}
}
