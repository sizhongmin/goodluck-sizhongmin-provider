package com.bw.service;

/**
 * Created by a on 2017/7/15.
 */
//发送邮件的接口
public interface mailservice {
    //发送邮件的方法
    //向谁发送邮件，邮件的主题是什么，邮件的内容是什么
    public void sendSaimpleMail(String to, String subject, String content);
}
