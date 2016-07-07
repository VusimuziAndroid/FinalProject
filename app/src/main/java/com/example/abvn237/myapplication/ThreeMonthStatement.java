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

public class ThreeMonthStatement extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayAdapter<String> adapterThreeMonthStatement ;
    ArrayList<String> arrlstThreeMonthStatement= new ArrayList<String>();
    ListView lsThreeMonthStatement;
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
        setContentView(R.layout.activity_three_month_statement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavigationHeaderThreeMonthsStatement();

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

        viewThreeMonthStatement();
    }

    public void viewThreeMonthStatement(){

        //opening or creating the database before retreiving values from the sqlite database

       populateValues();

        eventListener();


    }

    public void eventListener(){

        lsThreeMonthStatement.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            String Monthly[] ={trend.getTrendDate()+"     "+trend.getBalance()+"      "+trend.getAccountNumber()};

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                String s =  arrlstThreeMonthStatement.get(position);



                AlertDialog.Builder builder4 = new AlertDialog.Builder(ThreeMonthStatement.this);
                builder4.setTitle("Financial Advise");
                builder4.setCancelable(false);


                builder4.setItems(Monthly, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for (int i = 0; i < 5; i++) {
                            Toast.makeText(getApplicationContext(), Monthly[which], Toast.LENGTH_SHORT).show();
                        }

                    }


                });


                AlertDialog alertDialog4 = builder4.create();
                alertDialog4.show();



            }
        });
    }


    public void populateValues(){

        db =myDB.getWritableDatabase();
        db = openOrCreateDatabase("BusinessBankingApp33.sql",MODE_PRIVATE,null);


        lsThreeMonthStatement = (ListView) findViewById(R.id.lsThreeMonthStatement);


        Context context=null;
        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String trendDate = pref.getString("TrendDate", null);

        edit.commit();


        String startDate = "'01 March 2015'";
        String endDate = "25 March 2015";

        Datasource datasource = new Datasource();

        arrlstThreeMonthStatement = datasource.displayThreeMonthsStatement(arrlstThreeMonthStatement,accNumber,db);



      /*  String strQuery1=" SELECT DISTINCT t.TREND_DATE TREND_DATE, t.TREND_TIME TREND_TIME,t.BALANCE BALANCE,t.ACC_NR ACC_NR, at.ACC_TYPE_DESC ACC_TYPE_DESC";
        String strQuery2=" FROM Trend t, AccType at";
        String strQuery3=" WHERE t.ACC_TYPE_ID = at.ACC_TYPE_ID";
        String strQuery4=" AND TREND_DATE='";
        String strQuery=strQuery1+strQuery2+strQuery3+strQuery4+startDate+"' AND t.ACC_NR='"+accNumber+"'";



         Cursor c = myDB.getThreeMonthsStatement(db);

        arrlstThreeMonthStatement.add("Date                                                 Balance");
        while(c.moveToNext()){


             trendDate = c.getString(0);
            String trendTime = c.getString(1);
            String balance = c.getString(2);
            String accNr = c.getString(3);
            String AccType = c.getString(4);

            arrlstThreeMonthStatement.add(trendDate+"     "+trendTime+"        "+ balance);




            double Balance = 250.0;
            double vat =Balance*1.14+Balance;
            String strVAT = Double.toString(vat);
            trend = new Trend(trendDate,trendTime,balance,accNr,strVAT);

        }*/



        adapterThreeMonthStatement = new ArrayAdapter<String>(ThreeMonthStatement.this,
                android.R.layout.simple_list_item_1,
                arrlstThreeMonthStatement);
        lsThreeMonthStatement.setAdapter(adapterThreeMonthStatement);

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
        getMenuInflater().inflate(R.menu.three_month_statement, menu);
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


    public void setNavigationHeaderThreeMonthsStatement(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_three_month_statement,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvNavThreeMonthStatement);
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
            Intent home = new Intent(ThreeMonthStatement.this,Home.class);
            startActivity(home);

        } else if (id == R.id.Profile) {
            Intent profile = new Intent(ThreeMonthStatement.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(ThreeMonthStatement.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.CashTransfer) {
            Intent cashTransfer = new Intent(ThreeMonthStatement.this,CashTransfer.class);
            startActivity(cashTransfer);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent socialMedia = new Intent(ThreeMonthStatement.this,SocialMedia.class);
            startActivity(socialMedia);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
