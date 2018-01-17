package com.sms.model;

import java.io.Serializable;

public class SCEntityPK implements Serializable {
    private String sno;
    private String cno;
    public SCEntityPK(){

    }
    public SCEntityPK(String sno,String cno){
        this.sno = sno;
        this.cno = cno;
    }

    public String getSno(){
        return this.sno;
    }
    public void setSno(String sno){
        this.sno = sno;
    }
    public String getCno(){
        return this.cno;
    }
    public void setCno(String cno){
        this.cno = cno;
    }

    @Override
    public int hashCode(){
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((sno == null) ? 0 : sno.hashCode());
        result = PRIME * result + ((cno == null) ? 0 : cno.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final SCEntityPK other = (SCEntityPK) obj;
        if(sno == null){
            if(other.sno != null){
                return false;
            }
        }else if(!sno.equals(other.sno)){
            return false;
        }
        if(cno == null){
            if(other.cno != null){
                return false;
            }
        }else if(!cno.equals(other.cno)){
            return false;
        }

        return true;
    }

}
