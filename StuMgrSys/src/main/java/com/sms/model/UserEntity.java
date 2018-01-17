package com.sms.model;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "User", schema = "", catalog = "test")
public class UserEntity {
    private String userAccount;
    private String password;
    private int userType;
    @Transient
    private Map<String,String> errors;

    @Transient
    public boolean isValidate(){
        if(errors == null) errors = new HashMap<String,String>();
        boolean flag = true;
        if(!this.userAccount.matches("^.*[0-9A-Za-z]{6,15}.*$")){
            flag = false;
            this.errors.put("errUserAccount","用户名必须为6-15个字符") ;
        }

        if(!this.password.matches("^.*[a-zA-Z0-9]{6,15}+.*$")){
            flag =false;
            this.errors.put("errPWD","密码只能为6-15个字符");
        }

        return flag;
    }

    @Transient
    public String getErrorMsg(String key){
        String value = this.errors.get(key) ;
        return value==null?"":value ;
    }

    @Id
    @Column(name = "useraccount", nullable = false, insertable = true, updatable = true, length = 15)
    public String getUserAccount(){
        return this.userAccount;
    }

    public void setUserAccount(String userAccount){
        this.userAccount = userAccount;
    }

    @Basic
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 20)
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Basic
    @Column(name = "usertype", nullable = true, insertable = true, updatable = true)
    public int getUserType(){
        return this.userType;
    }

    public void setUserType(int userType){
        this.userType = userType;
    }


}
