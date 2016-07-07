package com.example.abvn237.myapplication;

/**
 * Created by ABVN237 on 4/4/2016.
 */
//The method for setting and returning values from the database
public class AccountHolder {

    //declaring the variables for the class
    private String accountHolderId;
    private String accountHolderName;
    private String accountHolderSurname;
    private String address;
    private String idNumber;
    private String accountNumber;
    private String userId;

    //The constructor method for the Account Holder class

  /*  public AccountHolder(String accountHolderId,String accountHolderName, String accountHolderSurname, String address, String idNumber, String accountNumber, String userId){

        this.AccountHolderId=accountHolderId;
        this.AccountHolderName=accountHolderName;
        this.AccountHolderSurname=accountHolderSurname;
        this.Address=address;
        this.IDNumber=idNumber;
        this.AccountNumber=accountNumber;
        this.UserId=userId;
    }*/


    //The method for setting the values for the Account Holder class
  /*  public AccountHolder()
    {


    }*/

    //The method for setting the values for the Account Holder class
    public AccountHolder(String accholderid,String accHolderName, String accHolderSurname, String accHolderAddress, String idNr,String accNr)
    {

        this.accountHolderId=accholderid;
        this.accountHolderName=accHolderName;
        this.accountHolderSurname=accHolderSurname;
        this.address=accHolderAddress;
        this.idNumber=idNr;
        this.accountNumber=accNr;

    }
    public void setName(String name){

        this.accountHolderName=name;

    }

    public void setSurname(String surname){


        this.accountHolderSurname=surname;
    }

   /* public void setAccountHolderName(String accountHolderName){

        this.AccountHolderName=accountHolderName;
    }

    public void setAccountNumber(String accountNumber){

        this.AccountNumber = accountNumber;
    }

    public void setAccountHolderSurname(String accountHolderSurname){

        this.AccountHolderSurname=accountHolderSurname;
    }

    public void setAddress(String address){
        this.Address = address;
    }

    public void setIDNumber(String idNumber){
       this.IDNumber = idNumber;
    }*/

    //The method for returning the Account Holder Id
    public String getAccountHolderId(){

        return this.accountHolderId;
    }

    //The method for returning the Account Holder name
    public String getAccountHolderName()
    {
        return this.accountHolderName;
    }

    //The method for returning the Account Holder Surname
    public String getAccountHolderSurname()
    {
        return this.accountHolderSurname;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getIDNumber()
    {
        return this.idNumber;
    }
    public String getAccountNumber()
    {
        return this.accountNumber;
    }
    public String UserId()
    {
        return this.userId;
    }

}
