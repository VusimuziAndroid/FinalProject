package com.example.abvn237.myapplication;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Vusi Ngwenya on 3/16/2016.
 */
public class Database {



   // public static final String TABLE_NAME="";

    public static final String DATABASE_NAME="BusinessBankingApp34.sql";
    public static final int DATABASE_VERSION = 1;





    public static abstract class AccountHolder{


        public static final String TABLE_NAME="Account_Holder";
        public static final String ACC_HOLDER_ID="ACC_HOLDER_ID";
        public static final String ACC_HOLDER_NAME="ACC_HOLDER_NAME";
        public static final String ACC_HOLDER_SURNAME="ACC_HOLDER_SURNAME";
        public static final String ADDESS="ADDRESS";
        public static final String ID_NUMBER="ID_NUMBER";
        public static final String ACC_NR="ACC_NR";
        public static final String USER_ID="USER_ID";
       // public static final String TABLE_NAME7="Spending_Pattern";


    }

    public static abstract class Trend {

        public static final String TABLE_NAME = "Trend";
        public static final String TREND_NR = "TREND_NR";
        public static final String TREND_DATE = "TREND_DATE";
        public static final String TREND_TIME = "TREND_TIME";
        public static final String BALANCE = "BALANCE";
        public static final String ACC_TYPE_ID="ACC_TYPE_ID";
        public static final String ACC_NR="ACC_NR";

    }


    public static abstract class Account{
        // Declaring values for the Account's table
        public static final String TABLE_NAME = "Account";
        public static final String ACC_NR ="ACC_NR";
        public static final String CARD_NR ="CARD_NR";
        public static final String PIN_NUMBER ="PIN_NUMBER";
        public static final String DATE_OPENED="DATE_OPENED";
        public static final String ACC_TYPE_ID="ACC_TYPE_ID";

    }



    //declaring values for the Account Type
    public static abstract class AccType {

        public static final String TABLE_NAME = "AccType";
        public static final String ACC_TYPE_ID ="ACC_TYPE_ID";
        public static final String ACC_TYPE_DESC="ACC_TYPE_DESC";

    }


    public static abstract class Trends{

        public static final String TABLE_NAME = "Trend";
        public static final String TRANSACTION_NR="Transaction_Nr";
        public static final String TRANSACTION_DATE="Transaction_Date";
        public static final String TRANSACTION_TIME="Transaction_Time";
        public static final String TRANSACTION_BALANCE="Transaction_Amount";
        public static final String ACC_NR="ACC_NR";
    }

    //Declaring values for the Augmented Reality
    public static abstract class Spending_Category {


        public static final String TABLE_NAME = "Spending_Category";
        public static final String SC_ID ="SC_ID";
        public static final String SC_DESCRIPTION="SC_DESCRIPTION";

    }

    public static abstract class Spending_Pattern {

        public static final String TABLE_NAME = "Spending_Pattern";
        public static final String SP_ID ="SP_ID";
        public static final String AMOUNT_SPENT="AMOUNT_SPENT";
        public static final String PERCENTAGE="PERCENTAGE";
        public static final String SC_ID="SC_ID";
        public static final String ACC_NR="ACC_NR";

    }

    public static abstract class News_Feeds {


        public static final String TABLE_NAME = "News_Feeds";
        public static final String NF_ID ="NF_ID";
        public static final String Messages="Messages";

    }


    //declaring values for the chat messages
    public static abstract class Chat_Messages {

        public static final String TABLE_NAME = "Chat_Messages";
        public static final String CHAT_ID ="CH_ID";
        public static final String CH_DATE="CH_DATE";
        public static final String MESSAGE="MESSAGE";
        public static final String USERNAME="USERNAME";

    }

    public static abstract class User {


        public static final String TABLE_NAME = "User";
        public static final String USER_ID ="USER_ID";
        public static final String USERNAME ="USERNAME";
        public static final String PASSWORD="PASSWORD";

    }

    public static abstract class Gallery {

        public static final String TABLE_NAME = "Gallery";
        public static final String Picture_Id ="Picture_Id";
        public static final String Picture="Picture";

    }

    public static abstract class NewsFeeds {


        public static final String NF_ID ="NF_ID";
        public static final String Messages="Messages";

    }

   // public static abstract class DataSource {

        SQLiteDatabase db;
        public  void createDatabase(){


            db.execSQL("create table " + Database.Account.TABLE_NAME+ " (ACC_NR INTEGER PRIMARY KEY,START_DATE TEXT,CARD_NR INTEGER,ACC_TYPE_ID INTEGER,PIN_NUMBER INTEGER,ID_NUMBER TEXT, USER_ID INTEGER);");
            db.execSQL("create table " + Database.AccountHolder.TABLE_NAME+ " (ACC_HOLDER_ID INTEGER PRIMARY KEY,ACC_HOLDER_NAME TEXT,ACC_HOLDER_SURNAME TEXT,ADDRESS  TEXT,ID_NUMBER TEXT,ACC_NR INTEGER, USER_ID INTEGER);");
            db.execSQL("create table " + Database.Trend.TABLE_NAME+ " (TREND_NR INTEGER PRIMARY KEY,TREND_DATE TEXT,TREND_TIME TEXT,BALANCE  DECIMAL,ACC_TYPE_ID INTEGER,ACC_NR INTEGER);");
            db.execSQL("create table " + Database.Spending_Category.TABLE_NAME+ " (SC_ID INTEGER PRIMARY KEY,SC_DESCRIPTION TEXT);");
            db.execSQL("create table " + Database.Spending_Pattern.TABLE_NAME+ " (SP_ID INTEGER PRIMARY KEY,AMOUNT_SPENT DECIMAL,PERCENTAGE DECIMAL,SC_ID  INTEGER,ACC_NR INTEGER);");
            db.execSQL("create table " + Database.AccType.TABLE_NAME+ " (ACC_TYPE_ID INTEGER PRIMARY KEY,ACC_TYPE_DESC TEXT);");
            db.execSQL("create table " + Database.Gallery.TABLE_NAME+ " (Picture_Id INTEGER PRIMARY KEY,Picture BLOB);");
            db.execSQL("create table " + Database.Chat_Messages.TABLE_NAME+ " (Chat_Id INTEGER PRIMARY KEY ,Chat_Date TEXT,Chat_Message TEXT,Username TEXT);");
            db.execSQL("create table " + Database.News_Feeds.TABLE_NAME + " (NF_ID INTEGER PRIMARY KEY ,Messages TEXT);");
            db.execSQL("create table " + Database.User.TABLE_NAME+ " (USER_ID INTEGER PRIMARY KEY ,USERNAME TEXT, PASSWORD TEXT);");

        }



   // }




}
