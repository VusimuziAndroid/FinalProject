package com.example.abvn237.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

public class ThreeWeekStatement extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayAdapter<String> adapterThreeWeekStatement ;
    ArrayList<String> arrlstThreeWeekStatement= new ArrayList<String>();
    ListView lsThreeWeekStatement;
    //  private ListAdapter adapter;
    SQLiteDatabase db;
    DatabaseHelper myDB;
    SpendingPattern sp;
    public CheckBox chkDaily,chkWeekly,chkMonthly,chkYearly;
    Cursor cursor;

    ListView myView;

    AccType accType;



    TextView user_name,user_email;
    ImageView imgUpload,user_picture;
    NavigationView navigation_view;


    Trend trend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_week_statement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavigationHeaderThreeWeekStatement();//The method for displaying the name and surname on the navigation header


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

        viewThreeWeekStatement();
    }


    public void viewThreeWeekStatement(){

        //opening or creating the database before retreiving values from the sqlite database

       populateValues();
        listEventListener();



    }

    public void listEventListener(){


        lsThreeWeekStatement.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            String Monthly[] ={trend.getTrendDate()+"     "+trend.getBalance()+"      "+trend.getAccountNumber()};

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                String s =  arrlstThreeWeekStatement.get(position);



                AlertDialog.Builder builder4 = new AlertDialog.Builder(ThreeWeekStatement.this);
                builder4.setTitle("Financial Advise");
                builder4.setCancelable(false);

                builder4.setItems(Monthly, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for(int i=0;i<5;i++) {
                            Toast.makeText(getApplicationContext(), Monthly[which], Toast.LENGTH_SHORT).show();
                        }

                    }


                });

                builder4.setMessage("")
                        .setTitle(R.string.title_activity_three_week_statement)
                        .setPositiveButton(android.R.string.ok,null);

                AlertDialog alertDialog4 = builder4.create();
                alertDialog4.show();

            }
        });
    }

    public void populateValues(){

        db=myDB.getWritableDatabase();
        db = openOrCreateDatabase("BusinessBankingApp33.sql",MODE_PRIVATE,null);


        lsThreeWeekStatement = (ListView) findViewById(R.id.lsThreeWeekStatement);

        Context context=null;

        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String trendDate = pref.getString("TrendDate", null);

        edit.commit();


        String startDate = "11 April 2015";
        String endDate = "14 April 2015";






        edit.commit();

        Datasource datasource = new Datasource();

        arrlstThreeWeekStatement = datasource.displayThreeWeeksStatement(arrlstThreeWeekStatement,accNumber,db);
       /* String strQuery1=" SELECT t.TREND_DATE TREND_DATE, t.TREND_TIME TREND_TIME,t.BALANCE BALANCE,t.ACC_NR ACC_NR, at.ACC_TYPE_DESC ACC_TYPE_DESC";
        String strQuery2=" FROM Trend t, AccType at";
        String strQuery3=" WHERE t.ACC_TYPE_ID = at.ACC_TYPE_ID";
        String strQuery4=" AND TREND_DATE>='";


          Cursor c = db.rawQuery(" SELECT t.TREND_DATE TREND_DATE, t.TREND_TIME TREND_TIME,t.BALANCE BALANCE,t.ACC_NR ACC_NR, at.ACC_TYPE_DESC ACC_TYPE_DESC"+
                " FROM Trend t, AccType at"+
                " WHERE t.ACC_TYPE_ID = at.ACC_TYPE_ID AND t.TREND_DATE='"+startDate+"' AND t.ACC_NR="+accNumber+"'"
                ,null);



        arrlstThreeWeekStatement.add("Date                                                 Balance");
        while(c.moveToNext()){


             trendDate = c.getString(0);
            String trendTime = c.getString(1);
            String balance = c.getString(2);
            String accNr = c.getString(3);
            String AccType = c.getString(4);

            arrlstThreeWeekStatement.add(trendDate+"     "+trendTime+"        "+ balance);

            double Balance = 250.0;
            double vat =Balance*1.14+Balance;
            String strVAT = Double.toString(vat);
            trend = new Trend(trendDate,trendTime,balance,accNr,strVAT);

        }*/



        adapterThreeWeekStatement = new ArrayAdapter<String>(ThreeWeekStatement.this,
                android.R.layout.simple_list_item_1,
                arrlstThreeWeekStatement);
        lsThreeWeekStatement.setAdapter(adapterThreeWeekStatement);

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
        getMenuInflater().inflate(R.menu.three_week_statement, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void setNavigationHeaderThreeWeekStatement(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_three_week_statement,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvNavThreeWeekStatement);
        Context context=null;

        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String cardNumber = pref.getString("CardNr", null);
        String Name=pref.getString("Name",null);
        String Surname=pref.getString("Surname", null);


        edit.commit();


        String text="Welcome "+Name+" "+Surname;

        user_name.setText(text);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            // Handle the camera action
            Intent home = new Intent(ThreeWeekStatement.this,Home.class);
            startActivity(home);

        } else if (id == R.id.Profile) {
            Intent profile = new Intent(ThreeWeekStatement.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(ThreeWeekStatement.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.CashTransfer) {
            Intent cashTransfer = new Intent(ThreeWeekStatement.this,CashTransfer.class);
            startActivity(cashTransfer);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent socialMedia = new Intent(ThreeWeekStatement.this,SocialMedia.class);
            startActivity(socialMedia);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
