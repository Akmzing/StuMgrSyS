package com.sms.model;

public class AvgGrade {
    private String cno;
    private String cname;
    private String avgGrade;
    private String maxGrade;
    private String minGrade;

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public String getCno() {
        return cno;
    }

    public String getAvgGrade() {
        return avgGrade;
    }

    public String getMaxGrade() {
        return maxGrade;
    }

    public String getMinGrade() {
        return minGrade;
    }

    public void setAvgGrade(String avgGrade) {
        this.avgGrade = avgGrade;
    }

    public void setMaxGrade(String maxGrade) {
        this.maxGrade = maxGrade;
    }

    public void setMinGrade(String minGrade) {
        this.minGrade = minGrade;
    }
}
