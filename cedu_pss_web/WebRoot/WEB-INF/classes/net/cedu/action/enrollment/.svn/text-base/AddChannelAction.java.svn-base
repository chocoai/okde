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
import net.cedu.biz.enrollment.ChannelPolicyChannelBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.ChannelPolicyChannel;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加渠道
 * @author lixiaojun
 *
 */

public class AddChannelAction extends BaseAction 
{
	@Autowired
	private ChannelBiz channelBiz;                                           //渠道业务层
	@Autowired 
	private BranchBiz branchBiz;                                             //学习中心业务层
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;                         //招生途径biz
	private Channel channel=new Channel();                                   //渠道实体
	private ChannelPolicyChannel channelpolicychannel=new ChannelPolicyChannel();
	private Branch branch;                                                   //学习中心实体
	//private String name;
	private List<EnrollmentSource> feelist=new ArrayList<EnrollmentSource>();//招生途径集合
	private List<Branch> branchlist=new ArrayList<Branch>();                 //学习中心集合
	private int isall;                                                       //是否全国性合作方
	private int sertificateType;                                             //证件类型
	private int type;                                                        //合作方类型
	private int branchId;                                                    //学习中心
	private File files;                                                      //附件
	private String filesFileName;                                            //附件文件名
	private String savepath;                                                 //附件路径
	private boolean bol;                                                     //条件变量
	private int num;
	private int id;
	
	
	
	/**
	 * 添加合作方
	 * 
	 * @return	
	 */
	@Action(results = {@Result(name="input",location="add_channel.jsp",type="dispatcher"),
			@Result(name="index",location="list_channel",type="redirectAction"),
			@Result(name="indexs",location="enrollment/chnlplcy/add_chnl_plcy_dtl?channelId=${id}",type="redirect")})
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			List<EnrollmentSource> list = enrollmentSourceBiz.findAllEnrollmentSourceByIsNeedRebatesAndFlag(Constants.ISNEED_REBATES_TRUE);
			for(EnrollmentSource e : list)
			{
				if(e.getId() == Constants.WEB_STU_LAO_SHENG_XU_DU)
				{
					continue;
				}
				if(super.getUser().getOrgId()!=BranchEnum.Admin.value() && (e.getId() == Constants.WEB_STU_QV_DAO || e.getId() == Constants.WEB_STU_GONG_JIAN))
				{
					continue;
				}
				feelist.add(e);
			}
			branchlist=branchBiz.findListById(super.getUser().getOrgId());
			if(branchlist!=null && branchlist.size()>0)
			{
				Collections.sort(branchlist, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						Comparator cmp = Collator
								.getInstance(java.util.Locale.CHINA);
						String name1 = ((Branch) arg0).getName();
						String name2 = ((Branch) arg1).getName();
						return cmp.compare(name1, name2);
					}
				});
			}
			return INPUT;
		}

		int count=channelBiz.countChannelByName(channel.getName(),null);
		if(count==0)
		{
			if(files!=null)
			{
				channel.setAttachmentUrl(uploadFile(filesFileName));
			}else
			{
				channel.setAttachmentName(null);
			}
			channel.setType(type);
			channel.setBranchId(branchId);
			channel.setSertificateType(sertificateType);
			if(branchId>1)
			{
				channel.setIsAll(Constants.IS_ALL_TRUE);
			}else
			{
				channel.setIsAll(Constants.IS_ALL_FALSE);
			}
			
			channel.setCreatorId(super.getUser().getUserId());
			channel.setCreatedTime(new Date());
			//添加合作方
			channelBiz.addChannel(channel);
			id=channel.getId();
			bol=false;
		}else
		{
			bol=true;
			return INPUT;
		}
		
		if(num==1)
		{
			return "index";
		}else
		{
			return "indexs";
		}
		
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
	
	
	public Channel getModel() {
		return channel;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	
	public List<EnrollmentSource> getFeelist() {
		return feelist;
	}

	public void setFeelist(List<EnrollmentSource> feelist) {
		this.feelist = feelist;
	}



	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	

	public int getIsall() {
		return isall;
	}

	public void setIsall(int isall) {
		this.isall = isall;
	}

	public int getSertificateType() {
		return sertificateType;
	}

	public void setSertificateType(int sertificateType) {
		this.sertificateType = sertificateType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public List<Branch> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(List<Branch> branchlist) {
		this.branchlist = branchlist;
	}


	public ChannelPolicyChannel getChannelpolicychannel() {
		return channelpolicychannel;
	}

	public void setChannelpolicychannel(ChannelPolicyChannel channelpolicychannel) {
		this.channelpolicychannel = channelpolicychannel;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
