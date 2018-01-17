package com.sms.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Course", schema = "", catalog = "test")
public class CourseEntity {
    private String cno;
    private String cname;
    private String ccredit;
    @Transient
    private Map<String,String> errors;

    @Transient
    public boolean isValidate(){
        if(errors == null) errors = new HashMap<String,String>();
        boolean flag = true;
        if(!this.cno.matches("^.*[0-9A-Za-z]{1,15}.*$")){
            flag = false;
            this.errors.put("errCno","课程号必须为1-15个字符") ;
        }

        if(!this.cname.matches("^[a-zA-Z0-9\\u4E00-\\u9FA5]{1,15}+$")) {
            flag = false;
            this.errors.put("errCname","课程名必须为一个以上的汉字或字符") ;
        }


        if(!this.ccredit.matches("^[1-9][0-9]*$")){
            flag = false;
            this.errors.put("errCcredit","学分只能为正整数") ;
        }

        return flag;
    }

    @Transient
    public String getErrorMsg(String key){
        String value = this.errors.get(key) ;
        return value==null?"":value ;
    }

    @Id
    @Column(name = "cno", nullable = false, insertable = true, updatable = true, length = 15)
    public String getCno(){
        return this.cno;
    }

    public void setCno(String cno){
        this.cno = cno;
    }

    @Basic
    @Column(name = "cname", nullable = false, insertable = true, updatable = true, length = 15)
    public String getCname(){
        return this.cname;
    }

    public void setCname(String cname){
        this.cname = cname;
    }

    @Basic
    @Column(name = "ccredit", nullable = false, insertable = true, updatable = true, length = 15)
    public String getCcredit(){
        return this.ccredit;
    }

    public void setCcredit(String ccredit){
        this.ccredit = ccredit;
    }
}
