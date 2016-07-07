package com.example.abvn237.myapplication;

import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
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
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class CashDeposit extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button bOneHundred,bTwoHundred,bFiveHundred,bOneThousand,bTwoThousand; //declaring the buttons

    DatabaseHelper depositHelper; // declaring the database handler
    SQLiteDatabase db; // declaring the sqlite database variable
    Trend trend;
    TextView user_name,user_email;
    ImageView imgUpload,user_picture;
    NavigationView navigation_view;
    /*Sessions sessions = new Sessions();
    Context context=null;
    SharedPreferences accessValue1=getSharedPreferences("MyPrefs",context.MODE_PRIVATE);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_deposit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        setNavigationHeaderCD();

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


        bOneHundred= (Button) findViewById(R.id.bOneHundred);
        bTwoHundred = (Button) findViewById(R.id.bTwoHundred);
        bFiveHundred = (Button) findViewById(R.id.bFiveHundred);
        bOneThousand = (Button) findViewById(R.id.bOneThousand);
        bTwoThousand = (Button) findViewById(R.id.bTwoThousand);
}


    public void OnClickCashDeposit(View view){


    eventHandler(view);

    }


    public void eventHandler(View view){

        switch(view.getId()){
            case R.id.bOneHundred:

                String transAmt=bOneHundred.getText().toString();

                Toast.makeText(getApplicationContext(), "The amount was deposited in your account",
                        Toast.LENGTH_SHORT).show();

              //  String  accNr=sessions.retrieveAccountNr(accessValue1);


                updateAmount(transAmt);



                break;

            case R.id.bTwoHundred:

                String transAmt2=bTwoHundred.getText().toString();


//                String  accNr2=sessions.retrieveAccountNr(accessValue1);


                updateAmount(transAmt2);


                break;

            case R.id.bFiveHundred:

                String transAmt3=bFiveHundred.getText().toString();


             //   String  accNr3=sessions.retrieveAccountNr(accessValue1);


                updateAmount(transAmt3);



                break;

            case R.id.bOneThousand:


               String transAmt4=bOneThousand.getText().toString();

              //  String  accNr4=sessions.retrieveAccountNr(accessValue1);


                updateAmount(transAmt4);


                break;

            case R.id.bTwoThousand:

               String transAmt5=bTwoThousand.getText().toString();


               // String  accNr5=sessions.retrieveAccountNr(accessValue1);


                updateAmount(transAmt5);

                break;
        }

    }






  /*  public void sendSMS(String phoneNumber,String cmd, int sendSMSCounter, int deliverCounter ,PendingIntent sentPI,PendingIntent deliveredPI, BroadcastReceiver sendingBroadcastReceiver){
       sentPI = PendingIntent.getBroadcast(this,sendSMSCounter++,new Intent(CashDeposit.this,CashTransfer.class));
        deliveredPI = PendingIntent.getBroadcast(this,deliverCounter++,new Intent(CashDeposit.this,CashTransfer.class));

        sendingBroadcastReceiver = new B





    }*/

    public void updateAmount(String currentAmt){



        String amount;
       double finalAmount=0,previousAmount=0,currentAmount,interestRates=10.5,vat=1.14;


        //currentAmount = Double.parseDouble(currentAmt.toString());


        finalAmount=finalAmount+interestRates;


        String strAmount = Double.toString(finalAmount);
        String strVAT = Double.toString(vat);


        setValues(strAmount, strVAT);

        sendSMSNotification();
      /*  String phoneNumbers = "0726882993";
        String messageText="The amount of "+trend.getAmount()+" was deposited in your account";

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:"+phoneNumbers));

        sendIntent.putExtra("sms_body", messageText);*/



      //  trend.getTrendInformation(trend.getAmount(),trend.getTrendDate(),trend.getTrendTime(),trend.getBalance());

    }

    public String getTime(long time){

        SimpleDateFormat sdf10 = new SimpleDateFormat("MMM MM dd,yyyy h:mm a");
        String dateTime5 = sdf10.format(time);

        return dateTime5;

    }

    public void setValues(String currentAmt,String strVAT){


        Context context=null;
        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String accTypeId = pref.getString("AccTypeId", null);
        String accTypeDesc= pref.getString("AccType", null);
        String trendDate= pref.getString("TrendDate", null);
        String trendTime= pref.getString("TrendTime", null);

        edit.commit();

        trend = new Trend(trendDate,trendTime,currentAmt,accNumber,strVAT);
        AccType accType= new AccType(accTypeId,accTypeDesc);


        Toast.makeText(CashDeposit.this,trendDate+"& "+trendTime+"& "+currentAmt+"& "+accNumber+"& "+strVAT,
                Toast.LENGTH_LONG).show();



        db = openOrCreateDatabase("BusinessBankingApp34.sql",MODE_PRIVATE,null);
        db =  depositHelper.getWritableDatabase();

        depositHelper.addTransaction(trendDate, trendTime,currentAmt,accTypeId,accNumber,db);

     /*   Toast.makeText(getApplicationContext(), trend.getTrendDate()+" & "+trend.getTrendTime()+" & "+trend.getAmount()+" & "+trend.getAccountNumber()+"& "+accType.getAccTypeId()+" & "+strVAT,
                Toast.LENGTH_SHORT).show();*/


       /* long date5 = System.currentTimeMillis();
        db=depositHelper.getWritableDatabase();
        depositHelper.addTransaction(trend.getTrendDate(),trend.getTrendTime(),trend.getBalance(),accType.getAccTypeId(),trend.getAccountNumber(),db);*/

       /* String dateTime = getTime(date5);

        String date= trend.getTime(date5);*/

      /*  String dateTime="09:00 AM";
        String date="04 April 2016";*/



      /*  AccType accType = new AccType(accTypeDesc);

        accType.setAccTypeId(accTypeId);*/












       /* Context context2=null;

        SharedPreferences pref2 = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit2 = pref.edit();

        String accountType = pref.getString("AccType", null);




        edit.commit();*/




    }

    public void sendSMSNotification(){

       /* SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("0726882993",null,"The amount was deposited in your account",null,null);*/


       /*String phoneNumber="0726882993";
        Uri uri = Uri.parse("smsto:"+phoneNumber.toString());
        Intent smsSIntent = new Intent(Intent.ACTION_SENDTO,uri);
        String smsBody="My new message";
        smsSIntent.putExtra("sms_body",smsBody);

        try{
            startActivity(smsSIntent);
        }
        catch(Exception e){

            Toast.makeText(CashDeposit.this,"Your sms has failed...",
                        Toast.LENGTH_LONG).show();
            e.printStackTrace();

        }*/

        try{


            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("0726882993",null,"My new Message",null,null);
            Toast.makeText(getApplicationContext(),"SMS Sent",Toast.LENGTH_LONG).show();


        }
        catch(Exception e){


            Toast.makeText(getApplicationContext(),"SMS Failed",Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.cash_deposit, menu);
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
            Intent newsFeed = new Intent(CashDeposit.this,NewsFeeds.class);
            startActivity(newsFeed);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setNavigationHeaderCD(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_cash_deposit,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvUName3);
        Context context=null;

        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String cardNumber = pref.getString("CardNr", null);
        String name=pref.getString("Name",null);
        String surname=pref.getString("Surname", null);


        edit.commit();


        String text=name+" "+surname;

        user_name.setText(text);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            // Handle the camera action
            Intent home = new Intent(CashDeposit.this,Home.class);
            startActivity(home);

        } else if (id == R.id.Profile) {
            Intent profile = new Intent(CashDeposit.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(CashDeposit.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.CashTransfer) {
            Intent cashTransfer = new Intent(CashDeposit.this,CashTransfer.class);
            startActivity(cashTransfer);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent socialMedia = new Intent(CashDeposit.this,SocialMedia.class);
            startActivity(socialMedia);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
