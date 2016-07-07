package com.example.abvn237.myapplication;

import java.util.Date;

/**
 * Created by Vusi Ngwenya on 4/4/2016.
 */

//The class for receiving values from the Accounts table
public class Account {

    //declaring variables

    private String accountNr;
    private String cardNumber;
    private String pinNumber;
    private String dateOpened;
    private String accTypeId;
    private String idNr;


    //The constructor
  /*  public Account(String accountNr,String cardNumber, String pinNumber,String dateOpened){

        this.AccountNr = accountNr;
        this.CardNumber = cardNumber;
        this.PinNumber = pinNumber;
        this.DateOpened = dateOpened;

    }*/

    //The method for setting values
    public Account(String accountnr, String cardNr, String pinNr, String acctypeid,String idNumber){

        this.accountNr = accountnr;
        this.cardNumber = cardNr;
        this.pinNumber = pinNr;
   //     this.dateOpened = dateopened;
        this.accTypeId = acctypeid;
        this.idNr=idNumber;


      //  DateOpened = dateOpened;
    }

   /* public void setAccountNr(String accountNr){

        this.AccountNr = accountNr;
    }

    public void setCardNumber(String cardNumber){
        this.CardNumber = cardNumber;
    }

    public void setPinNumber(String pinNumber){
        this.PinNumber = pinNumber;
    }

    public void setDateOpened(String dateOpened){
        this.DateOpened = dateOpened;
    }

    public void setAccTypeId(String accTypeId){
        this.AccTypeId = accTypeId;
    }

    public void setIDNumber(String IdNumber){
        this.IDNumber = IdNumber;
    }*/

    //The method for returning the Account Number
    public String getAccountNr(){
        return this.accountNr;
    }


    //The method for returning the Card Number
    public String getCardNumber(){

        return this.cardNumber;
    }

    //The method for returning the Pin Number
    public String getPinNumber(){
        return this.pinNumber;
    }


    //The method for returning the Date Opened
    public String getDateOpened(){
        return this.dateOpened;
    }

    public String getAccTypeId() { return this.accTypeId; }

    public String getIdNumber(){

        return this.idNr;
    }

    /*public String getIDNumber(){
        return IDNumber;
    }*/


}
