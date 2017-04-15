package com.lucky.dao;

/**
 * Created by 风萧萧兮 on 2017/4/14.
 */
public class Teacher {
    private String id;
    private String teaName;
    private String teaPass;
    private String sex;
    private String profesRanks;
    private String telphone;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaPass() {
        return teaPass;
    }

    public void setTeaPass(String teaPass) {
        this.teaPass = teaPass;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfesRanks() {
        return profesRanks;
    }

    public void setProfesRanks(String profesRanks) {
        this.profesRanks = profesRanks;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", teaName='" + teaName + '\'' +
                ", teaPass='" + teaPass + '\'' +
                ", sex='" + sex + '\'' +
                ", profesRanks='" + profesRanks + '\'' +
                ", telphone='" + telphone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
