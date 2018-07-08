package com.example.demo.Util;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
public class MailUtils {
	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {//email是收件人,emailMsg是激活信息
		//设置邮箱服务器
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.host", "smtp.126.com");
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true
		//设置发件人用户名和授权密码
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("HuangZhenHao97", "hzh1997");
			}
		};
		Session session = Session.getInstance(props, auth);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("HuangZhenHao97@126.com")); //发件人
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); //设置发送方式与收件人
		message.setSubject("用户激活码");
		message.setContent(emailMsg, "text/html;charset=utf-8");
		Transport.send(message);//创建 Transport用于将邮件发送
	}

}
