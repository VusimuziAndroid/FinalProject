package com.example.abvn237.myapplication;

/**
 * Created by Vusi Ngwenya on 5/4/2016.
 */
public class Gallery {

    String pictureId;
    String pictureName;
    String url;


    public Gallery(String picId, String picName, String picUrl)
    {


        this.pictureId=picId;
        this.pictureName = picName;
        this.url = picUrl;
    }

    public String getPictureId(){

        return this.pictureId;
    }

    public String getPictureName(){

        return this.pictureName;
    }

    public String getUrl(){

        return this.url;
    }


}
