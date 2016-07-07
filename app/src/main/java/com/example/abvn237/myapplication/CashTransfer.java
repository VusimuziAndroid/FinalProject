package com.example.abvn237.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.telephony.SmsManager;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Random;

public class CashTransfer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button b1Hundred,b2Hundred,b5Hundred,b1Thousand,b2Thousand;
    EditText etAccountNumber;
    Trend trend;
    DatabaseHelper myDB; // declaring the database handler
    SQLiteDatabase db; // declaring the sqlite database variable

    TextView user_name,user_email;
    ImageView imgUpload,user_picture;
    NavigationView navigation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_transfer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setNavigationHeaderCT();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



      //  myDB = new DatabaseHelper(this);

        b1Hundred= (Button) findViewById(R.id.b1Hundred);
        b2Hundred = (Button) findViewById(R.id.b2Hundred);
        b5Hundred = (Button) findViewById(R.id.b5Hundred);
        b1Thousand = (Button) findViewById(R.id.b1Thousand);
        b2Thousand = (Button) findViewById(R.id.b2Thousand);

        etAccountNumber  = (EditText) findViewById(R.id.etAccountNumber);
    }



   // The onClick method for transferring the cash to another account
    public void onClickTransfer(View view){

        String accNr = etAccountNumber.getText().toString();

       eventHandler(view,accNr);

    }

    public void transferAmount(String currentAmt, String accNr){

       // db=openOrCreateDatabase("BusinessBankingApp29.sql",MODE_PRIVATE,null);

        double finalAmount,previousAmount=0,currentAmount,InterestRates=10.5;


        long date5 = System.currentTimeMillis();


        SimpleDateFormat sdf10 = new SimpleDateFormat("MMM MM dd,yyyy h:mm a");
        String dateTime5 = sdf10.format(date5);


        SimpleDateFormat sdf11 = new SimpleDateFormat("MMM MM dd,yyyy ");
        String dateString5 = sdf11.format(date5);

       // String dateString5 = trend.getDate(date5);
       // String dateTime5 = trend.getTime(date5);


        /*db=openOrCreateDatabase("BusinessBankingApp29.sql",MODE_PRIVATE,null);
        db=myDB.getWritableDatabase();*/

       // getTrendInformation(currentAmt,dateString5,dateTime5,accNr);

        double vat =1.14;

        String strVAT=Double.toString(vat);



      //  AccType accType = new AccType(accTypeDesc);
       // accType.setAccTypeId(accTypeId);

        setSessions(dateString5,dateTime5,currentAmt,accNr,strVAT);


        db = myDB.getWritableDatabase();
        db = openOrCreateDatabase("BusinessBankingApp34.sql", MODE_PRIVATE, null);

       // AccType accType= new AccType();

       // myDB.addTransaction(trend,accType,db);

      //  trend.sendSMSNotification();
        Toast.makeText(getApplicationContext(), dateString5+" & "+dateTime5+" & "+currentAmt+" & "+strVAT,
                Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(),currentAmt+" was transferred in the account number of "+accNr,
                Toast.LENGTH_SHORT).show();

    }

    public String getTime(long time){


        SimpleDateFormat sdf10 = new SimpleDateFormat("MMM MM dd,yyyy h:mm a");
        String dateTime5 = sdf10.format(time);

        return dateTime5;

    }

    public String getDate(long date){

        SimpleDateFormat sdf11 = new SimpleDateFormat("MMM MM dd,yyyy ");
        String dateString5 = sdf11.format(date);

        return dateString5;

    }


    public void setSessions(String dateString5,String dateTime5,String currentAmt, String accNr,String strVAT){
        Context context=null;
        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String accTypeId = pref.getString("AccTypeId", null);
        String accTypeDesc= pref.getString("AccType", null);

        trend = new Trend(dateString5,dateTime5,currentAmt,accNr,strVAT);
    }


    public void getTrendInformation(String currentAmt,String dateString5,String dateTime5,String accNr)
    {

        double vat = 1.14;

        String strVAT=Double.toString(vat);
        trend = new Trend(dateString5,dateTime5,currentAmt,accNr,strVAT);




        Cursor c = myDB.getTrends(trend.getTrendDate(),trend.getTrendTime(),trend.getBalance(),trend.getAccountNumber(),db);

        while(c.moveToNext()){


            String TrendNr = c.getString(0);
            String TrendDate = c.getString(1);
            String TrendTime = c.getString(2);
            String Balance = c.getString(3);
            String Amount = c.getString(4);


           double previousAmount=Double.parseDouble(Amount.toString());
           double  currentAmount = Double.parseDouble(currentAmt.toString());
           double InterestRates=0;
            double transAmount=previousAmount-currentAmount;
           double finalAmount=transAmount+(transAmount*InterestRates);



            String strAmount=Double.toString(finalAmount);



           // db= myDB.getWritableDatabase();


           // trend = new Trend(dateString5,dateTime5,strAmount);
           // myDB.addTransaction();


           // myDB.addTransaction( trend.getTrendDate(), trend.getTrendTime(), trend.getAmount(), db);

          /*  SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("0726882993",null,"This is my first message",null,null);*/

            //  balance.add(TransDate+"         "+Balance);

        }
    }

    public void eventHandler(View view,String accNr)
    {

        switch(view.getId()){
            case R.id.b1Hundred:


                String transAmt=b1Hundred.getText().toString();

                transferAmount(transAmt,accNr);

                /*Toast.makeText(getApplicationContext(), "One Hundred",
                        Toast.LENGTH_SHORT).show();*
                break;

            case R.id.b2Hundred:


                String transAmt2=b2Hundred.getText().toString();
                transferAmount(transAmt2,accNr);

               /* Toast.makeText(getApplicationContext(), "Two Hundred",
                        Toast.LENGTH_SHORT).show();*/


                break;

            case R.id.b5Hundred:


                String transAmt3=b5Hundred.getText().toString();
                transferAmount(transAmt3,accNr);

               /* Toast.makeText(getApplicationContext(), "Five Hundred",
                        Toast.LENGTH_SHORT).show();*/

                break;

            case R.id.b1Thousand:

                String transAmt4=b1Thousand.getText().toString();
                transferAmount(transAmt4,accNr);

               /* Toast.makeText(getApplicationContext(), "Thousand Hundred",
                        Toast.LENGTH_SHORT).show();*/

                break;

            case R.id.b2Thousand:

                String transAmt5=b2Thousand.getText().toString();
                transferAmount(transAmt5,accNr);

               /* Toast.makeText(getApplicationContext(), "Two Thousand",
                        Toast.LENGTH_SHORT).show();*/


                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cash_transfer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           // return true;
            Intent newsFeed = new Intent(CashTransfer.this,NewsFeeds.class);
            startActivity(newsFeed);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setNavigationHeaderCT(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_cash_transfer,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvNavCashTrans);
        Context context=null;

        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String cardNumber = pref.getString("CardNr", null);
        String Name=pref.getString("Name",null);
        String Surname=pref.getString("Surname", null);


        edit.commit();


        String text=Name+" "+Surname;

        user_name.setText(text);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            // Handle the camera action
            Intent home = new Intent(CashTransfer.this,Home.class);
            startActivity(home);
        } else if (id == R.id.Profile) {
            Intent profile = new Intent(CashTransfer.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(CashTransfer.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.CashDeposit) {
            Intent CashDeposit = new Intent(CashTransfer.this,CashDeposit.class);
            startActivity(CashDeposit);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            Intent socialMedia = new Intent(CashTransfer.this,SocialMedia.class);
            startActivity(socialMedia);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
