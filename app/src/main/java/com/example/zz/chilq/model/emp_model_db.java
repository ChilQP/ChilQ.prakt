package com.example.zz.chilq.model;

/**
 * Created by Pavel on 28.06.2018.
 */

public class emp_model_db {
    private String idParent;
    private String info_emp;
    private int count_bc;
    private String name_emp;
    private String short_info_emp;

    public emp_model_db(String idParent, String info_emp, int count_bc, String name_emp, String short_info_emp){
        this.idParent=idParent;
        this.info_emp=info_emp;
        this.count_bc=count_bc;
        this.name_emp=name_emp;
        this.short_info_emp=short_info_emp;
    }


    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public void setInfo_emp(String info_emp) {
        this.info_emp = info_emp;
    }

    public void setCount_bc(int count_bc) {
        this.count_bc = count_bc;
    }

    public void setName_emp(String name_emp) {
        this.name_emp = name_emp;
    }

    public void setShort_info_emp(String short_info_emp) {
        this.short_info_emp = short_info_emp;
    }

    public String getIdParent() {
        return idParent;
    }

    public String getInfo_emp() {
        return info_emp;
    }

    public int getCount_bc() {
        return count_bc;
    }

    public String getName_emp() {
        return name_emp;
    }

    public String getShort_info_emp() {
        return short_info_emp;
    }
}
