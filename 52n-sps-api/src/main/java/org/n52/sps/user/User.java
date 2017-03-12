package org.n52.sps.user;

import java.util.Calendar;

/**
 * Created by xiangbaoqing on 2017/3/13.
 */
public class User {
    private Long id;

    private String userID;

    private String passWord;

    private String email;

    private String xmpp;

    private String phone;

    private String fax;

    private String sms;

    private Calendar createdTime;

    private Calendar updatedTime;

    private Calendar latestLoginTime;

    public User() {
    }

    public User(Long id, String userID) {
        this.id = id;
        this.userID = userID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getXmpp() {
        return xmpp;
    }

    public void setXmpp(String xmpp) {
        this.xmpp = xmpp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public Calendar getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Calendar createdTime) {
        this.createdTime = createdTime;
    }

    public Calendar getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Calendar updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Calendar getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(Calendar latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
    }
}
