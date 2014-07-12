package net.cedu.action.persontool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.persontool.DuibiFileBiz;
import net.cedu.biz.persontool.DuibiStudentGongfuBiz;
import net.cedu.common.file.FileUtil;
import net.cedu.common.file.excel.ImportExcel;
import net.cedu.common.properties.Config;
import net.cedu.entity.persontool.DuibiFile;
import net.cedu.entity.persontool.DuibiStudentGongfu;
import net.cedu.entity.persontool.DuibiStudentYuBaoMing;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生信息导入管理
 * 
 * @author yangdongdong
 * 
 */
@SuppressWarnings("serial")
@ParentPackage("json-default")
public class JsonStudentImportRecordAction extends BaseAction {

	@Autowired
	private DuibiFileBiz duibiFileBiz;
	@Autowired
	private DuibiStudentGongfuBiz duibiStudentGongfuBiz;

	private File file;
	private String fileFileName;
	private String filePath1="";
	private String filePath2="";

	private String message = super.getText("message.import.excel.file.success");

	private String id;// 加密以后的ID
	private String path;
	
	private int fullname=0,mobile=0,idcard=0,tel=0;
	
	private List<DuibiStudentGongfu> lst=new ArrayList<DuibiStudentGongfu>();

	public JsonStudentImportRecordAction() {
		super();
		this.setIl8nName("persontool");
	}

	/**
	 * 上传xls文件，并导入学生信息
	 * 
	 * @return
	 * @throws Exception
	 */

	@Action(value = "stu_duibi_upload", results = {
			@Result(name = "success", type = "json", params = { "contentType",
					"text/html" }),
			@Result(name = "error", type = "json", params = { "contentType",
					"text/html" }) })
	public String crmStudentImportRecordUpload() throws Exception {
		// 导入纪录ID//解密
		message = super.getText("message.import.excel.file.success");
		String sirId = id;//Encryption.decryption(id);
		try {

			if (!fileFileName.endsWith(".xls")) {
				message = this
						.getText("message.import.excel.file.format.error");
				return ERROR;
			}
			if (!sirId.equals("")) {
					path = FileUtil
							.FileUploads(
									ServletActionContext
											.getServletContext()
											.getRealPath(
													Config
															.getProperty("file.path.import.persontool.excel")),
									fileFileName, file);
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = this.getText("message.import.excel.file.exception.error");
		}
		DuibiFile duibiFile=duibiFileBiz.findStudentById();
		if(null==duibiFile)
		{
			duibiFile=new DuibiFile();
			if("1".equals(id))
				duibiFile.setGongfuPath(path);
			if("2".equals(id))
				duibiFile.setYubaomingPath(path);
			duibiFileBiz.addDuibiFile(duibiFile);			
		}
		else
		{
			if("1".equals(id))
				duibiFile.setGongfuPath(path);
			if("2".equals(id))
				duibiFile.setYubaomingPath(path);
			duibiFileBiz.updateById(duibiFile);
		}
		return SUCCESS;
	}


	/**
	 * 导入历史数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "stu_duibi", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmHistoryStudentImportRecordChangeStatus() throws Exception {
		try {
				ImportExcel<DuibiStudentGongfu> test1 = new ImportExcel<DuibiStudentGongfu>(DuibiStudentGongfu.class);
				ImportExcel<DuibiStudentYuBaoMing> test2 = new ImportExcel<DuibiStudentYuBaoMing>(DuibiStudentYuBaoMing.class);
				
				DuibiFile duibiFile=null;
//				if(StringUtils.isBlank(filePath1)&&StringUtils.isBlank(filePath2))
//				{
					duibiFile=duibiFileBiz.findStudentById();
					filePath1=duibiFile.getGongfuPath();
					filePath2=duibiFile.getYubaomingPath();
				//}
				
				File file1 = new File(ServletActionContext.getServletContext()
						.getRealPath(filePath1));
				File file2 = new File(ServletActionContext.getServletContext()
						.getRealPath(filePath2));
				List<DuibiStudentGongfu> results1 = test1.importExcel(file1);
				List<DuibiStudentYuBaoMing> results2 = test2.importExcel(file2);
				List<DuibiStudentGongfu> results=new ArrayList<DuibiStudentGongfu>();
				int i=0;
//				System.out.println(new Date());
//				System.out.println("@@"+fullname+"@@"+mobile+"@@"+tel+"@@"+idcard);
				boolean flag=false;
				for(DuibiStudentGongfu gongfu : results1)
				{
					for(DuibiStudentYuBaoMing yubao : results2)
					{
						try
						{
							if(fullname>0)
							{
								if(yubao.getName()!=null&&gongfu.getName()!=null){
									if(yubao.getName().trim().equals(gongfu.getName().trim()))
										flag=true;
									else
										flag=false;
								}else{
									flag=false;
								}
								
							}
							if(mobile>0)
							{
								if(yubao.getShouJiHaoma()!=null&&gongfu.getShouJiHaoma()!=null){
									if(yubao.getShouJiHaoma().trim().equals(gongfu.getShouJiHaoma().trim()))
										flag=true;
									else
										flag=false;
								}else{
									flag=false;
								}
							}
							if(tel>0)
							{
								if(yubao.getZuojiHaoMa()!=null&&gongfu.getZuoJiHaoMa()!=null){
									if(yubao.getZuojiHaoMa().trim().equals(gongfu.getZuoJiHaoMa().trim()))
										flag=true;
									else
										flag=false;
								}else{
									flag=false;
								}
							}
							if(idcard>0)
							{
								if(yubao.getIdCard()!=null&&gongfu.getShenFengZhengHaoMa()!=null){
									if(yubao.getIdCard().trim().equals(gongfu.getShenFengZhengHaoMa().trim()))
										flag=true;
									else
										flag=false;
								}else{
									flag=false;
								}
							}
							if(flag)
							{
	//							System.out.println(i);
								DuibiStudentGongfu d=gongfu;
	//							System.out.println("@@"+yubao.getRiqi());
								d.setRiQi(yubao.getRiqi());
								d.setHuJiaoZhongXinFrom(yubao.getHuJiaoZhongxinLaiYuan());
								results.add(gongfu);
								i++;
								break;
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
							continue;
						}
					}
				}

//				System.out.println(new Date());
			
			duibiStudentGongfuBiz.deleteByAll();
			for(int s=0,j=results.size();s<j;s++)
			{
//				System.out.println(results.get(s).getName());
//				System.out.println(results.get(s).getShenFengZhengHaoMa());
//				System.out.println(results.get(s).getRiQi());
//				System.out.println(results.get(s).getHuJiaoZhongXinFrom());
				duibiStudentGongfuBiz.addDuibiFile(results.get(s));
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}

	}
	
	@Action(value = "stu_duibi_result", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String getDuibiResult()throws Exception
	{
		lst=duibiStudentGongfuBiz.findStudentByPrePurchaseCenter();
		return SUCCESS;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getMessage() {
		return message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setFilePath1(String filePath1) {
		this.filePath1 = filePath1;
	}

	public void setFilePath2(String filePath2) {
		this.filePath2 = filePath2;
	}

	public List<DuibiStudentGongfu> getLst() {
		return lst;
	}

	public void setFullname(int fullname) {
		this.fullname = fullname;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public void setIdcard(int idcard) {
		this.idcard = idcard;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}
}
