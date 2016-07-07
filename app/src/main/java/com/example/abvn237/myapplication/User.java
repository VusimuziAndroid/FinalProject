package com.example.abvn237.myapplication;

/**
 * Created by ABVN237 on 4/5/2016.
 */
public class User {

    String userId;
    String userName;
    String passWord;

   /* public User(){


    }*/
    public User(String username, String password){

      //  this.UserId = userId;
        this.userName = username;
        this.passWord = password;
    }

  /*  public void setUserId(String userId) {
        UserId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/

    public String getUserId(){

        return this.userId;
    }

    public String getUsername(){
        return this.userName;
    }

    public String getPassword(){

        return this.passWord;
    }

}
