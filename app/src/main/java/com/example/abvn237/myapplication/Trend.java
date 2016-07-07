package com.example.abvn237.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;

import java.text.SimpleDateFormat;

/**
 * Created by ABVN237 on 4/4/2016.
 */
//The class for setting and returning values from the database
public class Trend {

   private String trendNumber;
   private String trendDate;
   private String trendTime;
   private String amount;
   private String accTypeId;
   private String accountNumber;
   private String trendBalance;
    private String percentage;
    private String purchaseAmount;
    private String totalAmount;
    private String vat;
    private String newPercentage;
    private String newAmountSpent;
    DatabaseHelper myDB;
    SQLiteDatabase db;



    //The constructor for the Trend class
    public Trend(String date, String time, String balance,String accountNr,String strVat){

     //   this.TrendNumber=trendNumber;
        this.trendDate=date;
        this.trendTime=time;
        this.trendBalance=balance;
        this.accountNumber=accountNr;
        this.vat=strVat;
    }

    //The method for setting the values to the Trend class
  /*  public void setTrend(String trendDate,String trendTime, String balance,String amount){


        this.TrendDate=trendDate;
        this.TrendTime=trendTime;
        this.Balance=balance;
        this.Amount=amount;

    }*/

   /* public void setTrendDate(String trendDate){
        this.TrendDate=trendDate;
    }

    public void setTrendTime(String trendTime){
        this.TrendTime=trendTime;
    }

    public void setBalance(String balance){
        this.Balance=balance;
    }

    public void setAmount(String amount){
        this.Amount=amount;
    }
    public void setPercentage(String Percentage){
        this.percentage = Percentage;
    }

    public void setPurchaseAmount(String PurchaseAmount){

        this.purchaseAmount = PurchaseAmount;

    }*/

    public String getTime(long time){

        SimpleDateFormat sdf10 = new SimpleDateFormat("MMM MM dd,yyyy h:mm a");
        String dateTime5 = sdf10.format(time);

        return dateTime5;

    }

    public void getTrendInformation(String currentAmt,String transDate,String transTime,String AccNr, String balance) {

        double finalAmount, previousAmount = 0,vat=1.14, currentAmount, InterestRates = 10.5;

        String strVAT = Double.toString(vat);




        Trend trend = new Trend(transDate, transTime, balance,AccNr,strVAT);

        Cursor c = myDB.getTrends(trend.getTrendDate(), trend.getTrendTime(), trend.getBalance(), trend.getAccountNumber(), db);

        while (c.moveToNext()) {


            String TrendNr = c.getString(0);
            String TrendDate = c.getString(1);
            String TrendTime = c.getString(2);
            String Amount = c.getString(3);
            String accNr = c.getString(4);

            previousAmount = Double.parseDouble(Amount.toString());
            currentAmount = Double.parseDouble(currentAmt.toString());


            finalAmount = (currentAmount + previousAmount) + InterestRates;


            String strAmount = Double.toString(finalAmount);



           // myDB.addTransaction(trend.getTrendDate(), trend.getTrendTime(), trend.getBalance(),trend, db);





        }
    }

    public String getTotalAmount(String strVat,String strBalance){

       // double vat = Double.parseDouble(strVat);
        double vat = 25.0;
       // double balance = Double.parseDouble(strBalance);
        double balance = 350.0;
        double totalAmount = vat*balance+vat;

        String strTotalAmount= Double.toString(totalAmount);

        return strTotalAmount;


    }
    public String getDate(long date){

        SimpleDateFormat sdf11 = new SimpleDateFormat("MMM MM dd,yyyy ");
        String dateString5 = sdf11.format(date);

        return dateString5;

    }



    public void sendSMSNotification(){

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("0726882993",null,"The amount was deposited in your account",null,null);
    }


  /*  public void setAccountNumber(String accountNumber){
        this.AccountNumber=accountNumber;
    }*/

    //The method for returning the Trend Number
    public String getTrendNumber(){
        return this.trendNumber;
    }

    //The method for returning the Trend Date
    public String getTrendDate(){

        return this.trendDate;
    }

    public String getPurchaseAmount(){

        return this.purchaseAmount;
    }
    //The method for returning the Trend Time
    public String getTrendTime(){

        return this.trendTime;
    }

    //The method for returning the amount
    public String getAmount(){
        return this.amount;
    }

    //The method for returning the AccTypeId
    public String getAccTypeId(){

        return this.accTypeId;
    }

    //The method for returning the Account Number
    public String getAccountNumber(){

        return this.accountNumber;
    }

    public String getBalance(){

        return this.trendBalance;
    }

    public String getVAT(){

        return this.vat;
    }
}
