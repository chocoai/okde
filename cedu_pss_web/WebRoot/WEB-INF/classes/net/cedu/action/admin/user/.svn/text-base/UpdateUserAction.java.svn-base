package net.cedu.action.admin.user;

import java.io.File;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.common.md5.PwdCoder;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 修改用户
 * @author Jack
 *
 */
public class UpdateUserAction extends BaseAction implements ModelDriven<User> 
{
	private static final long serialVersionUID = -1059865364657531438L;

	@Autowired
	private UserBiz userBiz;
	@Autowired
	private BranchBiz branchBiz;
	
	private List<Branch> plist=new ArrayList<Branch>();
	private User users=new User();
	private File files;                           //文件 
	private String filesFileName;                 //原始文件名称
	
	private String savePath;                      //服务器路径
	
	private boolean results=false;
	
	public String execute()
	{
		try
		{
			initBranchList();
			if(isGetRequest())
			{			
				users=userBiz.findUserById(users.getId());
				return INPUT;
			}
			users.setUpdaterId(users.getCreatorId());
			User old=userBiz.findUserById(users.getId());
			users.setUserName(old.getUserName());
			users.setCreatorId(old.getCreatorId());
			users.setCreatedTime(old.getCreatedTime());
			users.setType(old.getType());
			users.setIsManager(old.getIsManager());
			users.setDeleteFlag(old.getDeleteFlag());
			if(StringUtils.isBlank(users.getPassword()))
			{
				users.setPassword(old.getPassword());
				users.setUpdatePasswordTime(old.getUpdatePasswordTime());
			}
			else
			{
				users.setPassword(PwdCoder.getMD5(users.getPassword()));
				users.setUpdatePasswordTime(new Date());
			}
			if(null==files)
				users.setPhotoUrl(old.getPhotoUrl());
			else
				users.setPhotoUrl(uploadFile(filesFileName));
			users.setUpdaterId(super.getUser().getUserId());
			users.setUpdatedTime(new Date());
			userBiz.modify(users);
			results=true;
			addActionMessage(ResourcesTool.getText("admin","update.success"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionMessage(ResourcesTool.getText("admin","update.error"));
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
	
	private void initBranchList()throws Exception
	{
		plist=branchBiz.findListComposite(getUser().getOrgId(),null,null,BranchEnum.Default);
		if(plist!=null && plist.size()>0)
		{
			Collections.sort(plist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
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

	public List<Branch> getPlist() {
		return plist;
	}

	public void setPlist(List<Branch> plist) {
		this.plist = plist;
	}
	
}
