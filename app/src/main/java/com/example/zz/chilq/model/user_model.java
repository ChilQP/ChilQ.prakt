package com.example.zz.chilq.model;

public class user_model {
    private long id;
    private String s_uid;

    public user_model(long id, String s_uid){
        this.id=id;
        this.s_uid=s_uid;
    }


    public user_model(){ }

    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setS_uid(String s_uid) {
        this.s_uid = s_uid;
    }

    public String getS_uid() {
        return s_uid;
    }
}
