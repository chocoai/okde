package net.cedu.common.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import net.cedu.model.mail.MailSenderInfo;

/** */
/**
 * 简单邮件（不带附件的邮件）
 */
public class SimpleMailSender {
	/** */
	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 * @return
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
//		// 这个类主要是设置邮件　　
//		MailSenderInfo mailInfo = new MailSenderInfo();
//		mailInfo.setMailServerHost("smtp.126.com");
//		mailInfo.setMailServerPort("25");
//		mailInfo.setValidate(true);
//		mailInfo.setUserName("devsun@126.com");
//		mailInfo.setPassword("devsun_cedu");// 您的邮箱密码
//		mailInfo.setFromAddress("devsun@126.com");
//		mailInfo.setToAddress("454555037@qq.com");
//		mailInfo.setSubject("测试邮件wwwww1");
//		mailInfo.setContent("测试邮件wwwww1");
//		// 这个类主要来发送邮件　　
//		SimpleMailSender sms = new SimpleMailSender();
//		sms.sendTextMail(mailInfo);// 发送文体格式
//		sms.sendHtmlMail(mailInfo);// 发送html格式　
//		System.out.println("1111111");
		List<String> idsList=new ArrayList<String>();
		List<String> newIdsList=new ArrayList<String>();
		
		Integer [] studentIds=new Integer[]{1,2,3,4,5,6,7,8,9,0,10};
		
		
		int yuCount=studentIds.length%3;
		
		System.out.println(yuCount);
		
		Integer [] mainStudentIds=new Integer[studentIds.length-yuCount];
		
		Integer [] yuStudentIds=new Integer[yuCount];
		
		
		for (int i = 0; i < mainStudentIds.length; i++) {
			mainStudentIds[i]=studentIds[i];
		}
		
		for (int i =(studentIds.length-1),j=0; j < yuStudentIds.length; i--,j++) {
			yuStudentIds[j]=studentIds[i];
		}
		
		String ids=",";
		for (int i = 0; i < mainStudentIds.length; i++) {
			int id = mainStudentIds[i];
			if(ids.equals(",")){
				ids=id+"";
			}else{
				ids+=","+id;
			}
			if((i+1)%(studentIds.length/3)==0){
				idsList.add(ids);
				ids=",";
			}
		}
		for (int i = 0; i < idsList.size(); i++) {
			String id = idsList.get(i);
			if(i<yuStudentIds.length){
				newIdsList.add(id+","+yuStudentIds[i]);
			}else{
				newIdsList.add(id);
			}
			
		}

		for (String id : newIdsList) {
			System.out.println(id);
		}
		
	}
}