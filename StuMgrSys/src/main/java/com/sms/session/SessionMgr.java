package com.sms.session;

public class SessionMgr {
    private static SessionMgr instance = null;

    private SessionMgr(){}

    public static synchronized SessionMgr getInstance(){
        if(instance == null){
            instance = new SessionMgr();
        }
        return instance;
    }

    public String GetSessionId(){
        
        return "";
    }
}
