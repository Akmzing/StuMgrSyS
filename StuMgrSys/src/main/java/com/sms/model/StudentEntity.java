package com.sms.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Student", schema = "", catalog = "test")
public class StudentEntity {
    private String sno;
    private String sname;
    private String ssex;
    private String spassword;
    private String sage;
    @Transient
    private Map<String,String> errors;

    @Transient
    public boolean isValidate(){
        if(errors == null) errors = new HashMap<String,String>();
        boolean flag = true;
        if(!this.sno.matches("^.*[0-9A-Za-z]{6,15}.*$")){
            flag = false;
            this.errors.put("errSno","学号必须为6-15个字符") ;
        }

        if(!this.sname.matches("^[a-zA-Z0-9\\u4E00-\\u9FA5]{1,15}+$")) {
            flag = false;
            this.errors.put("errSname","姓名必须为一个以上的汉字或字符") ;
        }


        if(!this.ssex.equals("男")&&!this.ssex.equals("女")){
            flag = false;
            this.errors.put("errSsex","性别只能为男或女") ;
        }

        if(!this.spassword.matches("^.*[a-zA-Z0-9]{6,15}+.*$")) {
            flag = false;
            this.errors.put("errSpassword","密码必须为6到15个正常字符");
        }

        if(!this.sage.matches("^[1-9][0-9]{0,2}$")){
            flag = false;
            this.errors.put("errSage","年龄不符合范围");
        }
        return flag;
    }

    @Transient
    public String getErrorMsg(String key){
        String value = this.errors.get(key) ;
        return value==null?"":value ;
    }

    @Id
    @Column(name = "sno", nullable = false, insertable = true, updatable = true, length = 15)
    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    @Basic
    @Column(name = "sname", nullable = false, insertable = true, updatable = true, length = 15)
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Basic
    @Column(name = "ssex", nullable = false, insertable = true, updatable = true, length = 45)
    public String getSsex() {
        return this.ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    @Basic
    @Column(name = "spassword", nullable = false, insertable = true, updatable = true, length = 45)
    public String getSpassword() {
        return this.spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }


    @Basic
    @Column(name = "sage", nullable = false, insertable = true, updatable = true, length = 15)
    public String getSage() {
        return this.sage;
    }

    public void setSage(String sage) {
        this.sage = sage;
    }
}
