package com.example.abvn237.myapplication;

import android.app.AlertDialog;
import android.app.ListActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

//import java.net.HttpCookie;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by Vusi Ngwenya on 4/29/2016.
 */
public class Datasource {

    DatabaseHelper myDB;
    ArrayAdapter<String> adapterChats2;
    Account account;
    ChatMessages chatMessages;
    AccountHolder accountHolder;
    Trend trend;
    AccType accountType;




    public ArrayList<String>  DisplayChatMessages(ArrayList<String> arrlstchats2, SQLiteDatabase db){
        String strQuery1=" SELECT ch.Chat_Id Chat_Id, ch.Chat_Date Chat_Date,ch.Chat_Message Chat_Message, ch.USER_ID USER_ID,ah.ACC_HOLDER_ID ACC_HOLDER_ID,ah.ACC_HOLDER_NAME ACC_HOLDER_NAME,ah.ACC_HOLDER_SURNAME ACC_HOLDER_SURNAME ";
        String strQuery2=" FROM Chat_Messages ch,Account_Holder ah ";
        String strQuery3=" WHERE ch.ACC_HOLDER_ID=ah.ACC_HOLDER_ID ";

        arrlstchats2= new ArrayList<String>();

      //  SQLiteDatabase db;
       // db = myDB.getReadableDatabase();


       Cursor c = db.rawQuery(strQuery1+
                strQuery2+
                strQuery3, null);

      //  Cursor c = myDB.getChatMessages(db);


        while(c.moveToNext()){

            String chatId = c.getString(0);
            String chatDate = c.getString(1);
            String chatmessages = c.getString(2);
            String userId = c.getString(3);
            String accHolderId = c.getString(4);
            String name = c.getString(5);
            String surname = c.getString(6);


            // String img = Integer.parseInt(images[]);






           /* arrlstchats2.add(Images.toString());
            arrlstchats2.toArray();

            customadapter = new CustomAdapter(this,strChats,strUsername,Images);*/


           /* arrlstchats2.add(name+" "+surname+"        "+chatmessages);
            arrlstchats2.add(chatDate);*/

            accountHolder.setName(name);
            accountHolder.setSurname(surname);

            chatMessages = new ChatMessages(chatmessages,chatDate);

            arrlstchats2.add(accountHolder.getAccountHolderName()+" "+accountHolder.getAccountHolderSurname()+"        "+chatMessages.getChatMessage());
            arrlstchats2.add(chatDate);






        }


        return arrlstchats2;
    }




    public ArrayList<String> displayThreeMonthsExpenditures(ArrayList<String> threeMonths,String accNumber,SQLiteDatabase db){

        String startDate ="01 January 2013";
        String endDate = "01 March 2015";
        String dates = "01 January 2013' AND t.TREND_DATE <='11 February 2014'";

        threeMonths= new ArrayList<String>();

        //Declaring the cursor to retrieve values from the Spending_Category and Spending_Pattern table


      /*  String strQuery1= " SELECT DISTINCT sp.AMOUNT_SPENT AMOUNT_SPENT, sp.PERCENTAGE PERCENTAGE,sc.SC_DESCRIPTION SC_DESCRIPTION,sp.ACC_NR ACC_NR ";
        String strQuery2= "FROM Spending_Category sc, Spending_Pattern sp, Trend t, Account a ";
        String strQuery3= "WHERE  sc.SC_ID = sp.SC_ID AND t.ACC_NR = sp.ACC_NR AND a.ACC_NR = t.ACC_NR AND";
        String strQuery4= " t.TREND_DATE='";*/
        String strQuery1= " SELECT DISTINCT sp.AMOUNT_SPENT AMOUNT_SPENT, sp.PERCENTAGE PERCENTAGE,sc.SC_DESCRIPTION SC_DESCRIPTION,sp.ACC_NR ACC_NR ";
        String strQuery2= " FROM Spending_Category sc, Spending_Pattern sp, Trend t ";
        String strQuery3= " WHERE  sc.SC_ID = sp.SC_ID AND t.ACC_NR = sp.ACC_NR ";
        String strQuery4= " AND t.TREND_DATE >='"+dates+" ";


        Cursor c = db.rawQuery(strQuery1+
                strQuery2+
                strQuery3+strQuery4,null);


        threeMonths.add("Category         Amount         Percentage");


        while(c.moveToNext()){


            String AmountSpent = c.getString(0);
            String Percentage = c.getString(1);
            String Description = c.getString(2);
            String AccNr = c.getString(3);



            threeMonths.add(Description+"                  "+AmountSpent+"         "+Percentage);

        }

        return threeMonths;
    }

    public ArrayList<String> displayThreeWeeksExpenditures(ArrayList<String> threeWeeks2,String accNumber,SQLiteDatabase db){

        String startDate ="29 March 2016";
        String endDate = "19 April 2015";
        String dates = "29 March 2016' AND t.TREND_DATE <='19 April 2015'";

        threeWeeks2= new ArrayList<String>();


        String strQuery1= " SELECT DISTINCT sp.AMOUNT_SPENT AMOUNT_SPENT, sp.PERCENTAGE PERCENTAGE,sc.SC_DESCRIPTION SC_DESCRIPTION,sp.ACC_NR ACC_NR ";
        String strQuery2= "FROM Spending_Category sc, Spending_Pattern sp, Trend t ";
        String strQuery3= "WHERE  sc.SC_ID = sp.SC_ID AND t.ACC_NR = sp.ACC_NR ";
        String strQuery4= " AND t.TREND_DATE >='"+dates+" ";


        Cursor c = db.rawQuery(strQuery1+
                strQuery2+
                strQuery3+strQuery4,null);
        //  db=myDB.getReadableDatabase();
        // db=openOrCreateDatabase("BusinessBankingApp29.sql",MODE_PRIVATE,null);
        //  Cursor c = myDB.getThreeWeeksExpenditure(db);

        threeWeeks2.add("Category         Amount         Percentage");
        while(c.moveToNext()){


            String AmountSpent = c.getString(0);
            String Percentage = c.getString(1);
            String Description = c.getString(2);
            String AccNr = c.getString(3);



            threeWeeks2.add(Description+"                  "+AmountSpent+"         "+Percentage);



        }

        return threeWeeks2;

    }

    public ArrayList<String> displayThreeYearsExpenditures(ArrayList<String> threeYears,String accNumber,SQLiteDatabase db){

        threeYears= new ArrayList<String>();
        String startDate="01 January 2013";
        String endDate ="10 March 2015";

        String dates = "01 January 2013' AND t.TREND_DATE <='10 March 2015'";


        String strQuery1= " SELECT DISTINCT sp.AMOUNT_SPENT AMOUNT_SPENT, sp.PERCENTAGE PERCENTAGE,sc.SC_DESCRIPTION SC_DESCRIPTION,sp.ACC_NR ACC_NR ";
        String strQuery2= "FROM Spending_Category sc, Spending_Pattern sp, Trend t ";
        String strQuery3= "WHERE  sc.SC_ID = sp.SC_ID AND t.ACC_NR = sp.ACC_NR ";
       // String strQuery4= " AND t.TREND_DATE >='"+dates+" ";
        String strQuery4= " AND t.TREND_DATE >='"+startDate+"' AND t.TREND_DATE <='"+endDate+"' ";


        Cursor c = db.rawQuery(strQuery1+
                strQuery2+
                strQuery3+strQuery4,null);

        threeYears.add("Category         Amount         Percentage");
        while(c.moveToNext()){


            String AmountSpent = c.getString(0);
            String Percentage = c.getString(1);
            String Description = c.getString(2);
            String AccNr = c.getString(3);


            threeYears.add(Description+"                  "+AmountSpent+"         "+Percentage);

        }

        return threeYears;
    }

    public ArrayList<String> displayThreeWeeksStatement(ArrayList<String> arrlstThreeWeekStatement,String accNumber,SQLiteDatabase db){

        String startDate = "11 April 2015";
        String endDate = "14 April 2015";

        String dates = " AND t.TREND_DATE >='01 January 2013' AND t.TREND_DATE <='10 March 2015'";

        arrlstThreeWeekStatement= new ArrayList<String>();


        String strQuery1=" SELECT t.TREND_DATE TREND_DATE, t.TREND_TIME TREND_TIME,t.BALANCE BALANCE,t.ACC_NR ACC_NR, at.ACC_TYPE_DESC ACC_TYPE_DESC";
        String strQuery2=" FROM Trend t, AccType at";
        String strQuery3=" WHERE t.ACC_TYPE_ID = at.ACC_TYPE_ID";
        //String strQuery4=" AND t.TREND_DATE >="+dates+" ";


        Cursor c = db.rawQuery(strQuery1+
                strQuery2+
                strQuery3+dates,null);
        //  Cursor c= myDB.getThreeWeekStatement(db);


        arrlstThreeWeekStatement.add("Date                                                 Balance");
        while(c.moveToNext()){


            String trendDate = c.getString(0);
            String trendTime = c.getString(1);
            String balance = c.getString(2);
            String accNr = c.getString(3);
            String AccType = c.getString(4);

            arrlstThreeWeekStatement.add(trendDate+"     "+trendTime+"        "+ balance);

            double Balance = 250.0;
            double vat =Balance*1.14+Balance;
            String strVAT = Double.toString(vat);
            Trend trend;
            trend = new Trend(trendDate,trendTime,balance,accNr,strVAT);

        }

        arrlstThreeWeekStatement.add("                                       ");
        arrlstThreeWeekStatement.add("Account Number :  "+trend.getAccountNumber());
        arrlstThreeWeekStatement.add("Account Type   :  "+accountType.getAccTypeDesc());
        arrlstThreeWeekStatement.add("VAT :"+trend.getVAT()+"             "+" Total Amount :R"+trend.getTotalAmount(trend.getVAT(),trend.getBalance()));

        return arrlstThreeWeekStatement;

    }

    public ArrayList<String> displayThreeMonthsStatement(ArrayList<String> arrlstThreeMonthStatement,String accNumber,SQLiteDatabase db){

        arrlstThreeMonthStatement= new ArrayList<String>();


        String startDate = "'01 March 2015'";
        String endDate = "25 March 2015";

        String dates = "'01 January 2013' AND t.TREND_DATE < '01 March 2013'";


        String strQuery1=" SELECT DISTINCT t.TREND_DATE TREND_DATE, t.TREND_TIME TREND_TIME,t.BALANCE BALANCE,t.ACC_NR ACC_NR, at.ACC_TYPE_DESC ACC_TYPE_DESC";
        String strQuery2=" FROM Trend t, AccType at";
        String strQuery3=" WHERE t.ACC_TYPE_ID = at.ACC_TYPE_ID";
       // String strQuery4 = " WHERE "+"'01 January 2013' AND t.TREND_DATE < '01 March 2013'";
        String strQuery4= " AND t.TREND_DATE >='"+startDate+"' AND t.TREND_DATE <='"+endDate+"' ";
       // String strQuery4= " AND t.TREND_DATE  >='"+dates+" ";
       // String strQuery=strQuery1+strQuery2+strQuery3;


      //  Cursor c = myDB.getThreeMonthsStatement(db);
        Cursor c = db.rawQuery(strQuery1+
                strQuery2+
                strQuery3+strQuery4,new String[]{startDate,endDate},null);

        arrlstThreeMonthStatement.add("Date                                                 Balance");
        while(c.moveToNext()){


            String trendDate = c.getString(0);
            String trendTime = c.getString(1);
            String strBalance = c.getString(2);
            String accNr = c.getString(3);
            String AccType = c.getString(4);

            arrlstThreeMonthStatement.add(trendDate+"     "+trendTime+"        "+ strBalance);




            double balance = 250.0;
            double vat =balance*1.14+balance;
            String strVAT = Double.toString(vat);
            Trend trend;
            trend = new Trend(trendDate,trendTime,strBalance,accNr,strVAT);

        }

        arrlstThreeMonthStatement.add("                                       ");
        arrlstThreeMonthStatement.add("Account Number :  "+trend.getAccountNumber());
        arrlstThreeMonthStatement.add("Account Type   :  "+accountType.getAccTypeDesc());
        arrlstThreeMonthStatement.add("VAT :"+trend.getVAT()+"             "+" Total Amount :R"+trend.getTotalAmount(trend.getVAT(),trend.getBalance()));

        return arrlstThreeMonthStatement;
    }

    public ArrayList<String> displayThreeYearsStatement( ArrayList<String> arrlstViewStatement,String accNumber,SQLiteDatabase db){


        String startDate = "01 January 2013";
        String endDate = "01 January 2015";

        String dates = ">=01 January 2013 AND t.TREND_DATE <=10 March 2015";

        arrlstViewStatement= new ArrayList<String>();

        String strQuery1=" SELECT t.TREND_DATE TREND_DATE, t.TREND_TIME TREND_TIME,t.BALANCE BALANCE,t.ACC_NR ACC_NR,at.ACC_TYPE_ID ACC_TYPE_ID, at.ACC_TYPE_DESC ACC_TYPE_DESC";
        String strQuery2=" FROM Trend t, AccType at";
        String strQuery3=" WHERE t.ACC_TYPE_ID = at.ACC_TYPE_ID";
        String strQuery4= "AND t.TREND_DATE='01 January 2013'";




        Cursor c = db.rawQuery(" SELECT TREND_DATE,TREND_TIME,BALANCE,ACC_NR FROM Trend ",null);


        arrlstViewStatement.add("Date                                                 Balance");
        while(c.moveToNext()){


            String trendDate = c.getString(0);
            String trendTime = c.getString(1);
            String balance = c.getString(2);
            String accNr = c.getString(3);
            String accTypeId = c.getString(4);
            String accTypeDesc = c.getString(5);

            arrlstViewStatement.add(trendDate+"     "+trendTime+"        "+ balance);

            //sp = new Spending_Pattern(AmountSpent,Percentage,Description,AccNr);

               accountType = new AccType(accTypeId,accTypeDesc);

            //  double Balance=Double.parseDouble(balance);
            double Balance = 250.0;
            double vat =Balance*1.14+Balance;
            String strVAT = Double.toString(vat);

            trend = new Trend(trendDate,trendTime,balance,accNr,strVAT);

        }

        arrlstViewStatement.add("                                       ");
        //   arrlstViewStatement.add("Account Number :  "+trend.getAccountNumber());
        arrlstViewStatement.add("Account Type   :  "+accountType.getAccTypeDesc());
        arrlstViewStatement.add("VAT :"+trend.getVAT()+"             "+" Total Amount :R"+trend.getTotalAmount(trend.getVAT(),trend.getBalance()));

        return arrlstViewStatement;
    }

    public Account verifyLoginDetails(User user,AccType accountType,String accNr,SQLiteDatabase db) {


        Cursor c = myDB.getAccountDetails(accNr, db);
        while (c.moveToNext()) {


            String accNumber = c.getString(0);
            String cardNr = c.getString(1);
            String pinNumber = c.getString(2);
            String idNr = c.getString(3);
            String acTypeId = c.getString(4);
            String accTypeDesc = c.getString(5);
            String userName = c.getString(6);
            String passWord = c.getString(7);
            String name = c.getString(8);
            String surName = c.getString(9);


            // String Username=c.getString(3);

            Account account = new Account(accNumber, cardNr, pinNumber, acTypeId, idNr);

            accountType = new AccType(acTypeId, accTypeDesc);
            //  accType.setAccTypeId(AccTypeId);


            user = new User(userName, passWord);
        }
        return account;
    }
}
