package com.example.abvn237.myapplication;

/**
 * Created by Vusi Ngwenya on 4/4/2016.
 */

//The class for receiving and returning the Type of Account
public class AccType {
    //declaring variables
    String accTypeId;
    String accTypeDesc;

    //The constructor for the AccType class
    public AccType(String acctypeId,String acctypeDesc){


        this.accTypeId=acctypeId;
        this.accTypeDesc=acctypeDesc;
    }

    //The method for setting the values to the AccType class

   /* public void setAccType(String accTypeId, String accTypeDesc){

        AccTypeId = accTypeId;
        AccTypeDesc=accTypeDesc;

    } */

 /*   public void setAccTypeId(String accTypeId){
        this.AccTypeId=accTypeId;
    }*/

    //The method for returning the AccTypeId
    public String getAccTypeId(){

        return this.accTypeId;
    }

    //The method for returning the AccTypeDesc

    public String getAccTypeDesc(){

        return this.accTypeDesc;
    }
}
