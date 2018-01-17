package com.sms.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SC", schema = "", catalog = "test")
@IdClass(SCEntityPK.class)
public class SCEntity implements Serializable{
    private String sno;
    private String cno;
    private String cname;
    private String ccredit;
    private String grade;

    @Id
    @Column(name = "sno", nullable = false, insertable = true, updatable = true, length = 15)
    public String getSno() {
        return sno;
    }

    @Id
    @Column(name = "cno", nullable = false, insertable = true, updatable = true, length = 15)
    public String getCno() {
        return cno;
    }

    @Basic
    @Column(name = "cname", nullable = false, insertable = true, updatable = true, length = 15)
    public String getCname() {
        return cname;
    }

    @Basic
    @Column(name = "ccredit", nullable = false, insertable = true, updatable = true, length = 15)
    public String getCcredit() {
        return ccredit;
    }

    @Basic
    @Column(name = "grade", nullable = false, insertable = true, updatable = true, length = 15)
    public String getGrade() {
        return grade;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setCcredit(String ccredit) {
        this.ccredit = ccredit;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
