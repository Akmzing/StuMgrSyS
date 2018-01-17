package com.sms.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Teacher", schema = "", catalog = "test")
public class TeacherEntity {
    private String tno;
    private String tname;
    private String tdept;
    private String tsalary;
    private String tpassword;
    @Transient
    private Map<String,String> errors;

    @Transient
    public boolean isValidate(){
        if(errors == null) errors = new HashMap<String,String>();
        boolean flag = true;
        if(!this.tno.matches("^.*[0-9A-Za-z]{6,15}.*$")){
            flag = false;
            this.errors.put("errTno","工号必须为6-15个字符") ;
        }

        if(!this.tname.matches("^[a-zA-Z0-9\\u4E00-\\u9FA5]{1,15}+$")) {
            flag = false;
            this.errors.put("errTname","姓名必须为一个以上的汉字或字符") ;
        }

        if(!this.tdept.matches("^[a-zA-Z0-9\\u4E00-\\u9FA5]{1,45}+$")){
            flag = false;
            this.errors.put("errTdept","部门必须为一个以上的汉字或字符") ;
        }

        if(!this.tpassword.matches("^.*[a-zA-Z0-9]{6,15}+.*$")) {
            flag = false;
            this.errors.put("errTpassword","密码必须为6到15个正常字符");
        }

        if(!this.tsalary.matches("^\\d+$")){
            flag = false;
            this.errors.put("errTsalary","工资不符合范围");
        }
        return flag;
    }

    @Transient
    public String getErrorMsg(String key){
        String value = this.errors.get(key) ;
        return value==null?"":value ;
    }

    @Id
    @Column(name = "tno", nullable = false, insertable = true, updatable = true, length = 15)
    public String getTno(){
        return this.tno;
    }

    public void setTno(String tno){
        this.tno = tno;
    }

    @Basic
    @Column(name = "tname", nullable = false, insertable = true, updatable = true, length = 15)
    public String getTname(){
        return this.tname;
    }

    public void setTname(String tname){
        this.tname = tname;
    }

    @Basic
    @Column(name = "tdept", nullable = false, insertable = true, updatable = true, length = 45)
    public String getTdept(){
        return this.tdept;
    }

    public void setTdept(String tdept){
        this.tdept = tdept;
    }

    @Basic
    @Column(name = "tsalary", nullable = false, insertable = true, updatable = true, length = 15)
    public String getTsalary(){
        return this.tsalary;
    }

    public void setTsalary(String tsalary){
        this.tsalary = tsalary;
    }

    @Basic
    @Column(name = "tpassword", nullable = false, insertable = true, updatable = true, length = 15)
    public String getTpassword(){
        return this.tpassword;
    }

    public void setTpassword(String tpassword){
        this.tpassword = tpassword;
    }

}
