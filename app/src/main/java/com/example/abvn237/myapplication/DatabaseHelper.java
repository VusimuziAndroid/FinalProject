package com.example.abvn237.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Intent;




import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Vusi Ngwenya on 3/15/2016.
 */


//The Database helpe class
public class DatabaseHelper extends SQLiteOpenHelper {

    //declaring variables
    private static final android.database.sqlite.SQLiteDatabase.CursorFactory MODE_PRIVATE =null ;
    Database database=new Database();
    ArrayList<String> arrlstchats2= new ArrayList<String>();
    Context context;
    SQLiteDatabase db;
    DatabaseHelper myDB;
    Trend trend;
   // Account account  = new Account();




    public DatabaseHelper(Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);

    }

    //The oncreate method for creating the database and its tables
    @Override
    public void onCreate(SQLiteDatabase db) {




          // calling a method for creating the new database
        //  createDatabase();

        db.execSQL("create table " + Database.Account.TABLE_NAME + " (ACC_NR INTEGER PRIMARY KEY,START_DATE TEXT,CARD_NR INTEGER,ACC_TYPE_ID INTEGER,PIN_NUMBER INTEGER,ID_NUMBER TEXT, USER_ID INTEGER);");
        db.execSQL("create table " + Database.AccountHolder.TABLE_NAME+ " (ACC_HOLDER_ID INTEGER PRIMARY KEY,ACC_HOLDER_NAME TEXT,ACC_HOLDER_SURNAME TEXT,ADDRESS  TEXT,ID_NUMBER TEXT,ACC_NR INTEGER, USER_ID INTEGER);");
        db.execSQL("create table " + Database.Trend.TABLE_NAME+ " (TREND_NR INTEGER PRIMARY KEY,TREND_DATE TEXT,TREND_TIME TEXT,BALANCE  DECIMAL,ACC_TYPE_ID INTEGER,ACC_NR INTEGER);");
        db.execSQL("create table " + Database.AccType.TABLE_NAME+ " (ACC_TYPE_ID INTEGER PRIMARY KEY,ACC_TYPE_DESC TEXT);");
        db.execSQL("create table " + Database.User.TABLE_NAME + " (USER_ID INTEGER PRIMARY KEY ,USERNAME TEXT, PASSWORD TEXT);");
        db.execSQL("create table " + Database.Spending_Category.TABLE_NAME+ " (SC_ID INTEGER PRIMARY KEY,SC_DESCRIPTION TEXT);");
        db.execSQL("create table " + Database.Spending_Pattern.TABLE_NAME+ " (SP_ID INTEGER PRIMARY KEY,AMOUNT_SPENT DECIMAL,PERCENTAGE DECIMAL,SC_ID  INTEGER,ACC_NR INTEGER);");
        db.execSQL("create table " + Database.Gallery.TABLE_NAME+ " (Picture_Id INTEGER PRIMARY KEY,Picture BLOB);");
        db.execSQL("create table " + Database.Chat_Messages.TABLE_NAME+ " (Chat_Id INTEGER PRIMARY KEY ,Chat_Date TEXT,Chat_Message TEXT,Username TEXT);");
        db.execSQL("create table " + Database.News_Feeds.TABLE_NAME + " (NF_ID INTEGER PRIMARY KEY ,Messages TEXT);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {




        //db.execSQL("DROP TABLE IF EXISTS "+Database.User.TABLE_NAME);
        //onCreate(db);
        dropDatabase();

    }

    public void createDatabase(){


        db.execSQL("create table " + Database.Account.TABLE_NAME + " (ACC_NR INTEGER PRIMARY KEY,START_DATE TEXT,CARD_NR INTEGER,ACC_TYPE_ID INTEGER,PIN_NUMBER INTEGER,ID_NUMBER TEXT, USER_ID INTEGER);");
      /*  db.execSQL("create table " + Database.AccountHolder.TABLE_NAME+ " (ACC_HOLDER_ID INTEGER PRIMARY KEY,ACC_HOLDER_NAME TEXT,ACC_HOLDER_SURNAME TEXT,ADDRESS  TEXT,ID_NUMBER TEXT,ACC_NR INTEGER, USER_ID INTEGER);");
        db.execSQL("create table " + Database.Trend.TABLE_NAME+ " (TREND_NR INTEGER PRIMARY KEY,TREND_DATE TEXT,TREND_TIME TEXT,BALANCE  DECIMAL,ACC_TYPE_ID INTEGER,ACC_NR INTEGER);");
        db.execSQL("create table " + Database.AccType.TABLE_NAME+ " (ACC_TYPE_ID INTEGER PRIMARY KEY,ACC_TYPE_DESC TEXT);");
        db.execSQL("create table " + Database.User.TABLE_NAME + " (USER_ID INTEGER PRIMARY KEY ,USERNAME TEXT, PASSWORD TEXT);");
        db.execSQL("create table " + Database.Spending_Category.TABLE_NAME+ " (SC_ID INTEGER PRIMARY KEY,SC_DESCRIPTION TEXT);");
        db.execSQL("create table " + Database.Spending_Pattern.TABLE_NAME+ " (SP_ID INTEGER PRIMARY KEY,AMOUNT_SPENT DECIMAL,PERCENTAGE DECIMAL,SC_ID  INTEGER,ACC_NR INTEGER);");
        db.execSQL("create table " + Database.Gallery.TABLE_NAME+ " (Picture_Id INTEGER PRIMARY KEY,Picture BLOB);");
        db.execSQL("create table " + Database.Chat_Messages.TABLE_NAME+ " (Chat_Id INTEGER PRIMARY KEY ,Chat_Date TEXT,Chat_Message TEXT,Username TEXT);");
        db.execSQL("create table " + Database.News_Feeds.TABLE_NAME + " (NF_ID INTEGER PRIMARY KEY ,Messages TEXT);");*/


    }

    public void dropDatabase(){

        db.execSQL("DROP TABLE IF EXISTS " + Database.Account.TABLE_NAME +
                "DROP TABLE IF EXISTS " + Database.AccountHolder.TABLE_NAME +
                "DROP TABLE IF EXISTS " + Database.AccType.TABLE_NAME +
                "DROP TABLE IF EXISTS " + Database.Trend.TABLE_NAME +
                "DROP TABLE IF EXISTS " + Database.Spending_Category.TABLE_NAME +
                "DROP TABLE IF EXISTS " + Database.Spending_Pattern.TABLE_NAME +
                "DROP TABLE IF EXISTS " + Database.Gallery.TABLE_NAME +
                "DROP TABLE IF EXISTS " + Database.Chat_Messages.TABLE_NAME +
                "DROP TABLE IF EXISTS " + Database.News_Feeds.TABLE_NAME);
    }

    //The method for adding the new account to the database
    public void addAccount(String Username, String Password , SQLiteDatabase database){


       //  Context context = null;
        /* database = myDB.getWritableDatabase();
         database.openOrCreateDatabase("BusinessBankingApp29.sql",MODE_PRIVATE,null);*/
       // onCreate(database);

        User user = new User(Username,Password);


        ContentValues values = new ContentValues();

       // String query = "SELECT * FROM User";
      //  Cursor cursor = db.rawQuery(query,null);
      //  int count = cursor.getCount();
        //String strCount =

        //values.put(Database.User.USER_ID,count);
        values.put(Database.User.USERNAME,user.getUsername());
        values.put(Database.User.PASSWORD, user.getPassword());

        database.insert(Database.User.TABLE_NAME, null, values);
        database.close();

    }


    //The method for adding the new transaction to the database
    public void addTransaction(String trendDate,String trendTime,String balance,String accTypeId,String accNumber,SQLiteDatabase db){

      /*  db = this.getWritableDatabase();
        db.openOrCreateDatabase("BusinessBankingApp29.sql",MODE_PRIVATE,null);*/


       /*  ContentValues values = new ContentValues();

        values.put(Database.Trend.TREND_DATE, trendDate);
        values.put(Database.Trend.TREND_TIME, trendTime);
        values.put(Database.Trend.BALANCE,  balance);
        values.put(Database.Trend.ACC_TYPE_ID,  accTypeId);
        values.put(Database.Trend.ACC_NR,  accNumber);

        db.insert(Database.Trend.TABLE_NAME, null, values);
        db.close();*/

       // Log.d("Add Transaction", trend.getTrendDate() + " " + trend.getTrendTime() + " " + trend.getBalance() + "  " + trend.getAccTypeId() + "  " + trend.getAccountNumber());

        // trend = new Trend(TrendDate,TrendTime,Balance,AccNr,VAT);

        String sql = " INSERT INTO Trend (TREND_DATE,TREND_TIME,BALANCE,ACC_TYPE_ID,ACC_NR) VALUES(?,?,?,?,?);";

        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);




        stmt.bindString(1, trendDate);
        stmt.bindString(2, trendTime);
        stmt.bindString(3, balance);
        stmt.bindString(4, accTypeId);
        stmt.bindString(5, accNumber);


        long entryID = stmt.executeInsert();
        stmt.clearBindings();

        db.setTransactionSuccessful();
        db.endTransaction();

        db.close();


    }



    public void addNewChat(String Chat_Date,String Messages,SQLiteDatabase db){



        ContentValues values = new ContentValues();

        values.put(Database.Chat_Messages.CH_DATE, Chat_Date);
        values.put(Database.Chat_Messages.MESSAGE, Messages);

        db.insert(Database.Chat_Messages.TABLE_NAME, null, values);
        db.close();

        Log.d("Insert Chats",Chat_Date+" "+Messages);



        //declaring the string variable for the sql query

      /*  db.openOrCreateDatabase("BusinessBankingApp24.sql",MODE_PRIVATE,null);


        db = this.getWritableDatabase();*/

       /* String sql = " INSERT INTO Chat_Message (CH_MESSAGE,CH_DATE,USERNAME) VALUES(?,?,?);";

        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);


        stmt.bindString(1, Chat_Date);
        stmt.bindString(2, Chat_Message);
        stmt.bindString(3, Username);


        long entryID = stmt.executeInsert();
        stmt.clearBindings();

        db.setTransactionSuccessful();
        db.endTransaction();

        db.close();*/

    }

    public void addPicture(byte[] buffer,SQLiteDatabase db){



        String sql = " INSERT INTO Gallery (Picture) VALUES(?);";

        db.beginTransaction();
        SQLiteStatement stmt = db.compileStatement(sql);


        stmt.bindString(1, buffer.toString());
       /* stmt.bindString(2, ChatDate);
        stmt.bindString(3, Username);*/


        long entryID = stmt.executeInsert();
        stmt.clearBindings();

        db.setTransactionSuccessful();
        db.endTransaction();

        db.close();
      /*  Bitmap bm=null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, out);

        buffer = out.toByteArray();



        ContentValues values = new ContentValues();

        values.put(Database.Gallery.Picture, buffer.toString());

        db.insert(Database.Gallery.TABLE_NAME, null, values);


        Log.d("add picture", " ");


        db.setTransactionSuccessful();*/



    }

    //Method for validating the existance of the account
    public Cursor validateAccount(String AccNr, String CardNr, String IdNr){


        //retrieving data from the database and storing it on the cursor
        db = this.getReadableDatabase();
     //   String[] columns = {Database.Account.ACC_NR,Database.Account.CARD_NR, Database.Account.ID_NUMBER};
        String query = " SELECT ACC_NR, CARD_NR, ID_NUMBER FROM Account";

        String a,b,c,found="not found";

        Cursor cursor;

        cursor = db.rawQuery(query, null);

        // cursor = db.query(Database.Trends.TABLE_NAME,columns,null,null,null,null,null);

        return cursor;

    }

    //Method for receiving statements
    public Cursor getThreeWeekStatement(SQLiteDatabase db){

        //retrieving data from the database and storing it on the cursor

        String startDate = "11 April 2015";
        String endDate = "14 April 2015";
        db = this.getReadableDatabase();
        String[] columns = {Database.Trends.TRANSACTION_NR,Database.Trends.TRANSACTION_DATE, Database.Trends.TRANSACTION_TIME,Database.Trends.TRANSACTION_BALANCE};
        String query = " SELECT t.TREND_DATE TREND_DATE, t.TREND_TIME TREND_TIME,t.BALANCE BALANCE,t.ACC_NR ACC_NR, at.ACC_TYPE_DESC ACC_TYPE_DESC"+
                " FROM Trend t, AccType at"+
                " WHERE t.ACC_TYPE_ID = at.ACC_TYPE_ID AND TREND_DATE='"+startDate+"'";

        String a,b,c,found="not found";

        Cursor cursor;

        cursor = db.rawQuery(query, null);

       // cursor = db.query(Database.Trends.TABLE_NAME,columns,null,null,null,null,null);

        return cursor;
    }

    //Method for receiving statements
    public Cursor getThreeMonthsStatement(SQLiteDatabase db){

        //retrieving data from the database and storing it on the cursor

        String startDate = "11 April 2015";
        String endDate = "14 April 2015";
        db = this.getReadableDatabase();
        String[] columns = {Database.Trends.TRANSACTION_NR,Database.Trends.TRANSACTION_DATE, Database.Trends.TRANSACTION_TIME,Database.Trends.TRANSACTION_BALANCE};
        String query = " SELECT t.TREND_DATE TREND_DATE, t.TREND_TIME TREND_TIME,t.BALANCE BALANCE,t.ACC_NR ACC_NR, at.ACC_TYPE_DESC ACC_TYPE_DESC"+
                " FROM Trend t, AccType at"+
                " WHERE t.ACC_TYPE_ID = at.ACC_TYPE_ID AND TREND_DATE='"+startDate+"'";

        String a,b,c,found="not found";

        Cursor cursor;

        cursor = db.rawQuery(query, null);

        // cursor = db.query(Database.Trends.TABLE_NAME,columns,null,null,null,null,null);

        return cursor;
    }

    //Method for receiving account details
    public Cursor getAccountDetails(String accNr,SQLiteDatabase db){

        //retrieving data from the database and storing it on the cursor

        db = this.getReadableDatabase();
        String[] columns = {Database.Account.ACC_NR,Database.Account.PIN_NUMBER};
        String query = " SELECT a.ACC_NR ACC_NR,a.CARD_NR CARD_NR,a.PIN_NUMBER PIN_NUMBER,a.ID_NUMBER ID_NUMBER, at.ACC_TYPE_ID ACC_TYPE_ID,at.ACC_TYPE_DESC ACC_TYPE_DESC,u.USERNAME USERNAME, u.PASSWORD PASSWORD,ah.ACC_HOLDER_NAME ACC_HOLDER_NAME,ah.ACC_HOLDER_SURNAME ACC_HOLDER_SURNAME FROM Account a, AccType at, User u,Account_Holder ah WHERE a.ACC_TYPE_ID=at.ACC_TYPE_ID AND a.USER_ID=u.USER_ID AND a.ACC_NR = ah.ACC_NR AND a.ACC_NR="+accNr;

        Cursor cursor;

        cursor = db.rawQuery(query,null);

        return cursor;

    }

    public Cursor getTrends(String trendDate,String trendTime, String trendBalance,String accNr,SQLiteDatabase db){


        db = this.getReadableDatabase();


        String query = " SELECT TREND_DATE, TREND_TIME, BALANCE FROM WHERE ACC_NR = "+accNr;

        Cursor cursor;

        cursor = db.rawQuery(query,null);

        return cursor;
    }


    public Cursor getNewsFeedsMessages(SQLiteDatabase db){


        db = this.getReadableDatabase();


        String query = " SELECT NF_ID, Messages FROM News_Feeds";

        Cursor cursor;

        cursor = db.rawQuery(query,null);

        return cursor;
    }

    public Cursor getChatMessages(SQLiteDatabase db){

        String strQuery1=" SELECT ch.Chat_Id Chat_Id, ch.Chat_Date Chat_Date,ch.Chat_Message Chat_Message, ch.USER_ID USER_ID,ah.ACC_HOLDER_ID ACC_HOLDER_ID,ah.ACC_HOLDER_NAME ACC_HOLDER_NAME,ah.ACC_HOLDER_SURNAME ACC_HOLDER_SURNAME ";
        String strQuery2=" FROM Chat_Messages ch,Account_Holder ah ";
        String strQuery3=" WHERE ch.ACC_HOLDER_ID=ah.ACC_HOLDER_ID ";



      //  db = this.getReadableDatabase();


        //String query = " SELECT NF_ID, Messages FROM News_Feeds";

        Cursor cursor;

        cursor = db.rawQuery(strQuery1+
                strQuery2+
                strQuery3,null);

        return cursor;
    }
    //Method for receiving login details
    public Cursor getLoginDetails(String AccNr, String PinNr,SQLiteDatabase db){

        //retrieving data from the database and storing it on the cursor

        db = this.getReadableDatabase();
        String[] columns = {Database.Account.ACC_NR,Database.Account.PIN_NUMBER};
       // String query = " SELECT ACC_NR, PIN_NUMBER FROM "+TABLE_NAME2;

        String a,b,c,found="not found";

        Cursor cursor;

       // cursor = db.query(Database.AccountHolder.TABLE_NAME, columns, null, null, null, null, null);

        return null;

    }

    public void verifyLoginDetails(String AccNr, String PinNr){

      /*  if(accNr.equals(""))
        {

            Toast.makeText(Login.this, "Supply value for the account number",
                    Toast.LENGTH_SHORT).show();
        }
        else if(pinNr.equals(""))
        {
            Toast.makeText(Login.this, "Supply value for the pin number",
                    Toast.LENGTH_SHORT).show();
        }
        else {

            if (accNr.equals(AccNr) && pinNr.equals(PinNr)) {
                Intent()
                Intent home = new Intent(DatabaseHelper.this, Home.class);
                startActivity(home);
            } else {

                Toast.makeText(Login.this, "The credentials supplied does n't exists",
                        Toast.LENGTH_SHORT).show();
            }

        }*/

    }

    public void getMessages(ImageView imgUpload){

        Cursor c = db.rawQuery("SELECT Picture FROM Gallery", null);

        if(c.moveToNext())
        {

            byte[] image = c.getBlob(0);
            Bitmap bmp = BitmapFactory.decodeByteArray(image,0,image.length);
            imgUpload.setImageBitmap(bmp);

           // Toast.makeText(this,"select success",Toast.LENGTH_SHORT).show();



        }

   /*     String strQuery1=" SELECT ch.Chat_Id Chat_Id, ch.Chat_Date Chat_Date,ch.Chat_Message Chat_Message, ch.USER_ID USER_ID,ah.ACC_HOLDER_ID ACC_HOLDER_ID,ah.ACC_HOLDER_NAME ACC_HOLDER_NAME,ah.ACC_HOLDER_SURNAME ACC_HOLDER_SURNAME ";
        String strQuery2=" FROM Chat_Messages ch,Account_Holder ah ";
        String strQuery3=" WHERE ch.ACC_HOLDER_ID=ah.ACC_HOLDER_ID ";

        Cursor c = db.rawQuery(strQuery1+
                strQuery2+
                strQuery3, null);


        while(c.moveToNext()){

            String ChatId = c.getString(0);
            String ChatDate = c.getString(1);
            String ChatMessages = c.getString(2);
            String UserId = c.getString(3);
            String AccHolderId = c.getString(4);
            String Name = c.getString(5);
            String Surname = c.getString(6);


            arrlstchats2.add(Name+" "+Surname+"         "+ChatMessages);
            arrlstchats2.add(ChatDate);


        }*/


    }


    //Closing the Database Connection
    public void close(){
        this.close();
    }

}
