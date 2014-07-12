package net.cedu.action.template;

import net.cedu.action.BaseAction;
import net.cedu.common.mail.SimpleMailSender;
import net.cedu.common.properties.Config;
import net.cedu.model.mail.MailSenderInfo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 * 发送外部邮件
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonMailAction extends BaseAction {

	private String eamilAddress;
	private String title;
	private String context;

	/**
	 * 发送外部邮件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "send_mail", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String sendMail() throws Exception {
		// 这个类主要是设置邮件　　
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(Config.getProperty("mail.server.host"));
		mailInfo.setMailServerPort(Config.getProperty("mail.server.port"));
		mailInfo.setValidate(true);
		mailInfo.setUserName(Config.getProperty("email.administrator.address"));
		mailInfo
				.setPassword(Config.getProperty("email.administrator.password"));// 您的邮箱密码
		mailInfo.setFromAddress(Config
				.getProperty("email.administrator.address"));
		mailInfo.setToAddress(eamilAddress);
		mailInfo.setSubject(title);
		mailInfo.setContent(context);
		// 发送邮件　　
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
		return SUCCESS;
	}

	public String getEamilAddress() {
		return eamilAddress;
	}

	public void setEamilAddress(String eamilAddress) {
		this.eamilAddress = eamilAddress;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
