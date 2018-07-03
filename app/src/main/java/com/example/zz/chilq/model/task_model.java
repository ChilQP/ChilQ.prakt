package com.example.zz.chilq.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Pavel on 28.06.2018.
 */

public class task_model {

    private String name_task;
    private String desc_task;
    private String date_rec;
    private String date_complete;
    private String pic_task;
    private String pic_compl_task;
    private String reward;
    private int task_compl;
    private String name_child;
    private String name_parent;
    private long id_task;



    public task_model(String name_task, String desc_task , String date_complete,  String pic_task, String pic_compl_task, String reward, String date_rec, int task_compl, String name_child, String namme_parent, long id_task){
        this.name_task=name_task;
        this.desc_task=desc_task;
        this.date_complete=date_complete;
        this.pic_task=pic_task;
        this.pic_compl_task=pic_compl_task;
        this.reward=reward;
        this.date_rec=date_rec;
        this.task_compl=task_compl;
        this.name_child=name_child;
        this.name_parent=namme_parent;
        this.id_task=id_task;
    }
    public task_model(){
//        DateFormat df = new SimpleDateFormat("dd.MM.yy");
//        date_rec = df.format(Calendar.getInstance().getTime());

    }


    public void setName_task(String name_task) {
        this.name_task = name_task;
    }

    public void setDesc_task(String desc_task) {
        this.desc_task = desc_task;
    }

    public void setDate_rec(String date_rec) {
        this.date_rec = date_rec;
    }

    public void setDate_complete(String date_complete) {
        this.date_complete = date_complete;
    }

    public void setPic_task(String pic_task) {
        this.pic_task = pic_task;
    }

    public void setPic_compl_task(String pic_compl_task) {
        this.pic_compl_task = pic_compl_task;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getName_task() {
        return name_task;
    }

    public String getDesc_task() {
        return desc_task;
    }

    public String getDate_rec() {
        return date_rec;
    }

    public String getDate_complete() {
        return date_complete;
    }

    public String getPic_task() {
        return pic_task;
    }

    public String getPic_compl_task() {
        return pic_compl_task;
    }

    public String getReward() {
        return reward;
    }

    public void setTask_compl(int task_compl) {
        this.task_compl = task_compl;
    }

    public int getTask_compl() {
        return task_compl;
    }

    public void setName_child(String name_child) {
        this.name_child = name_child;
    }

    public String getName_child() {
        return name_child;
    }

    public void setName_parent(String name_parent) {
        this.name_parent = name_parent;
    }

    public String getName_parent() {
        return name_parent;
    }

    public void setId_task(long id_task) {
        this.id_task = id_task;
    }

    public long getId_task() {
        return id_task;
    }
}
