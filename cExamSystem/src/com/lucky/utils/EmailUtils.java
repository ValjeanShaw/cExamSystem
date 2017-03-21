package com.lucky.utils;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 *  Created by yuanshenghan on 2015/11/26.
 *  发送邮件
 */

public class EmailUtils {

    /**
     * 发送HTML格式邮件
     * @param username 帐号
     * @param password 密码
     * @param from 发信人
     * @param to 收信人
     * @param smtp 服务器
     * @param htmlcontent html内容
     * @param subject 邮件标题
     * @return ("3212652147",
     * "xeodwsflvgptdchb",
     * "3212652147@qq.com",
     * "1049053036@qq.com",
     * "smtp.qq.com",
     * "emailContent",
     * "激活验证");
     */

    public static boolean SendHtmlEmail(String username,String password,String from,String to, String smtp,String htmlcontent,String subject){
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth", "true");//指定是否需要SMTP验证
        prop.setProperty("mail.smtp.host", smtp);//指定SMTP服务器
        prop.put("mail.transport.protocol","smtp");
        Session session = Session.getDefaultInstance(prop);
        session.setDebug(true);//是否在控制台显示debug信息


        try {
            /*创建邮件模块*/
            Address fromAddress = new InternetAddress(from);
            Address toAddress = new InternetAddress(to);

            Message message = new MimeMessage(session);
            message.setFrom(fromAddress);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            message.setSentDate(new Date());
            message.setSubject(subject);
            message.setContent(htmlcontent, "text/html;charset = utf-8");
            message.saveChanges();

            /*发送邮件模块*/
            Transport transport = session.getTransport("smtp");
            transport.connect(smtp, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 发送Text格式邮件
     * @param username 帐号
     * @param password 密码
     * @param from 发信人
     * @param to 收信人
     * @param smtp 服务器
     * @param content   邮件内容
     * @param subject 邮件标题
     * @return
     */
    public static boolean SendTextEmail(String username,String password,String from,String to, String smtp,String content,String subject){

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.host", smtp);
        prop.put("mail.transport.protocol","smtp");
        Session session = Session.getDefaultInstance(prop);
        session.setDebug(true);


        try {
            /*创建邮件模块*/
            Address fromAddress = new InternetAddress(from);
            Address toAddress = new InternetAddress(to);

            Message message = new MimeMessage(session);
            message.setFrom(fromAddress);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            message.setSentDate(new Date());
            message.setSubject(subject);
            message.setText(content);
            message.saveChanges();

            /*发送邮件模块*/
            Transport transport = session.getTransport("smtp");
            transport.connect(smtp, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args){
        String s = "<a href='http://www.baidu.com'>百度</a>";
        EmailUtils.SendHtmlEmail("neunavyll@139.com","139Email_1303","neunavyll@139.com","2178202462@qq.com","smtp.139.com",s,"激活验证");
    }
    /**
     * neunavysendemail@126.com
     * neunavy_203
     */
}
