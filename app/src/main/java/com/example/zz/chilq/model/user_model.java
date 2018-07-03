package com.example.zz.chilq.model;

public class user_model {
    private long id;
    private String s_uid;
    private String settings_id;

    public user_model(long id, String s_uid, String settings_id){
        this.id=id;
        this.s_uid=s_uid;
        this.settings_id=settings_id;
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

    public void setSettings_id(String settings_id) {
        this.settings_id = settings_id;
    }

    public String getSettings_id() {
        return settings_id;
    }
}
