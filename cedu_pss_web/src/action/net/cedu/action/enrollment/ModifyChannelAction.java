package net.cedu.action.enrollment;

import java.io.File;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.common.Constants;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.enrollment.Channel;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 修改渠道
 * @author lixiaojun
 *
 */
@Results( {
	@Result(name = "copy", location = "list_channel_copy",type="redirectAction"),
	@Result(name = "default", location = "list_channel",type="redirectAction"),
	@Result(name = "confirm", location = "list_channel_confirm",type="redirectAction")
})
public class ModifyChannelAction extends BaseAction implements ModelDriven<Channel>
{
	@Autowired
	private ChannelBiz channelBiz;                            //渠道业务层
	
	private Channel channel=new Channel();                    //渠道实体
	
	@Autowired 
	private BranchBiz branchBiz;//学习中心biz
	private List<Branch> branchlist=new ArrayList<Branch>();  //学习中心集合
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;          //招生途径biz
	private List<EnrollmentSource> feelist=new ArrayList<EnrollmentSource>();//招生途径集合
	private File files;            //附件
	private String filesFileName;  //文件名
	private String savepath;       //文件路径
	private int id;//渠道ID
	private String url;
	
	private String actionResult="default";
	

	
	public String execute() throws Exception 
	{
//		url = this.getReferer();
		if(super.isGetRequest())
		{	
			int ids=id;
			channel=channelBiz.findChannel(Integer.parseInt(super.request.getParameter("id")));
			feelist=this.enrollmentSourceBiz.findAllEnrollmentSourceByDeleteFlag();
			branchlist=branchBiz.findListById(super.getUser().getOrgId());
			Collections.sort(branchlist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
			return INPUT;
		}
		Channel oldchannel=channelBiz.findChannel(Integer.parseInt(super.request.getParameter("id")));
		if(channel.getName()!=null && !channel.getName().equals(""))
		{
			oldchannel.setName(channel.getName());
		}if(channel.getContractNo()!=null && !channel.getContractNo().equals(""))
		{
			oldchannel.setContractNo(channel.getContractNo());
		}if(channel.getType()>0)
		{
			oldchannel.setType(channel.getType());
		}if(channel.getBranchId()>0)
		{
			oldchannel.setBranchId(channel.getBranchId());
			if(channel.getBranchId()>1)
			{
				oldchannel.setIsAll(Constants.IS_ALL_TRUE);
			}else
			{
				oldchannel.setIsAll(Constants.IS_ALL_FALSE);
			}
		}if(channel.getOfficeAddress()!=null && !channel.getOfficeAddress().equals(""))
		{
			oldchannel.setOfficeAddress(channel.getOfficeAddress());
		}if(channel.getAccountBank()!=null && !channel.getAccountBank().equals(""))
		{
			oldchannel.setAccountBank(channel.getAccountBank());
		}if(channel.getAccountName()!=null && !channel.getAccountName().equals(""))
		{
			oldchannel.setAccountName(channel.getAccountName());
		}if(channel.getAccount()!=null && !channel.getAccount().equals(""))
		{
			oldchannel.setAccount(channel.getAccount());
		}if(channel.getLinkman()!=null && !channel.getLinkman().equals(""))
		{
			oldchannel.setLinkman(channel.getLinkman());
		}if(channel.getSertificateType()>=0)
		{
			oldchannel.setSertificateType(channel.getSertificateType());
		}if(channel.getSertificateNo()!=null && !channel.getSertificateNo().equals(""))
		{
			oldchannel.setSertificateNo(channel.getSertificateNo());
		}if(channel.getTelephone()!=null && !channel.getTelephone().equals(""))
		{
			oldchannel.setTelephone(channel.getTelephone());
		}if(channel.getAttachmentName()!=null && !channel.getAttachmentName().equals(""))
		{
			oldchannel.setAttachmentName(channel.getAttachmentName());
		}
		if(files!=null)
		{
			String delpath=ServletActionContext.getServletContext().getRealPath(oldchannel.getAttachmentUrl());
			deleteFile(delpath);
			oldchannel.setAttachmentUrl(uploadFile(filesFileName));
		}
	    oldchannel.setUpdatedTime(new Date());
		oldchannel.setUpdaterId(super.getUser().getUserId());
		channelBiz.modifyChannel(oldchannel);
		return actionResult;
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
			savepath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("enrollment","uploadenrollment"));
			return FileUtil.FileUploads(savepath,name,files);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 删除源附件
	 * @param filepath
	 * @throws Exception
	 */
	private void deleteFile(String filepath)throws Exception 
	{
		File file=new File(filepath);
		file.delete();
	}
	
	
	
	public Channel getModel() {
		return channel;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Branch> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(List<Branch> branchlist) {
		this.branchlist = branchlist;
	}

	public List<EnrollmentSource> getFeelist() {
		return feelist;
	}

	public void setFeelist(List<EnrollmentSource> feelist) {
		this.feelist = feelist;
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

	public String getSavepath() {
		return savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public String getActionResult() {
		return actionResult;
	}

	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}

	
	

	
}
