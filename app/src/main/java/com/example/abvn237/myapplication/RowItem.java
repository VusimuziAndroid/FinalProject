package com.example.abvn237.myapplication;

/**
 * Created by ABVN237 on 4/4/2016.
 */
public class RowItem {

    private String name;
    private int profile_pic_id;
    private String status;

    public RowItem(String name, int profile_pic_id,String status){

        this.name=name;
        this.profile_pic_id=profile_pic_id;
        this.status=status;
    }

    public RowItem(String name, int profile_pic_id) {
        this.name=name;
        this.profile_pic_id=profile_pic_id;
    }

    public String getName(){

        return name;
    }

    public void setName(String Name){

        name=Name;
    }
    public int getProfile_pic_id(){

        return profile_pic_id;
    }

    public String getStatus(){

        return status;
    }


}



