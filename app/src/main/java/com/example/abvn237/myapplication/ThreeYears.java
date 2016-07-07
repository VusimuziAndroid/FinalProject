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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThreeYears extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SQLiteDatabase db;

    ArrayAdapter<String> adapterThreeYears;
    ArrayList<String> threeYears= new ArrayList<String>();
    ListView lsThreeYears;
    TextView user_name,user_email;
    ImageView imgUpload,user_picture;
    NavigationView navigation_view;

    String Yearly[] ={"Air Time      R500         5%", "Entertainment   R1500          %15","Food         R2500       25%",
            "Clothes      R2500        25%", "Furnisher      R2000        20%","Elecricity      R500        5%",
            "Luxury       R500        5%"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_years);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavigationHeaderThreeYears(); // The method for displaying the name and surname on the navigation header

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

        getThreeYearsExpenditures();
    }


    public void getThreeYearsExpenditures(){



    populateValues();
        listEventListener();

    }

    public void listEventListener(){


        lsThreeYears.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                String s = threeYears.get(position);


                displayDialogFragmentThreeYears();


               /* Toast.makeText(ThreeMonths.this, "Select Month is " + s,
                        Toast.LENGTH_SHORT).show();*/


            }
        });
    }

    public void displayDialogFragmentThreeYears(){

        AlertDialog.Builder builder4 = new AlertDialog.Builder(ThreeYears.this);
        builder4.setTitle("Financial Advise");
        builder4.setCancelable(false);

        builder4.setItems(Yearly, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), Yearly[which], Toast.LENGTH_SHORT).show();

            }


        });

        builder4.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog4 = builder4.create();
        alertDialog4.show();
    }
    public void populateValues(){

        lsThreeYears = (ListView) findViewById(R.id.lsThreeYears);


        db=openOrCreateDatabase("BusinessBankingApp33.sql",MODE_PRIVATE,null);

        Context context=null;
        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String trendDate = pref.getString("TrendDate", null);

        edit.commit();

        Datasource datasource = new Datasource();

        threeYears = datasource.displayThreeYearsExpenditures(threeYears,accNumber,db);


      /*  Cursor c = db.rawQuery(" SELECT sp.AMOUNT_SPENT AMOUNT_SPENT, sp.PERCENTAGE PERCENTAGE,sc.SC_DESCRIPTION SC_DESCRIPTION,sp.ACC_NR ACC_NR FROM Spending_Category sc, Spending_Pattern sp WHERE sc.SC_ID = sp.SC_ID AND sp.ACC_NR='"+accNumber+"'",null);

        threeYears.add("Category         Amount         Percentage");
        while(c.moveToNext()){


            String AmountSpent = c.getString(0);
            String Percentage = c.getString(1);
            String Description = c.getString(2);
            String AccNr = c.getString(3);


            threeYears.add(Description+"                  "+AmountSpent+"         "+Percentage);

        }*/

        adapterThreeYears = new ArrayAdapter<String>(ThreeYears.this,
                android.R.layout.simple_list_item_1,
                threeYears);
        lsThreeYears.setAdapter(adapterThreeYears);
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
        getMenuInflater().inflate(R.menu.three_years, menu);
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
         //   return true;
            Intent newsFeed = new Intent(ThreeYears.this,NewsFeeds.class);
            startActivity(newsFeed);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setNavigationHeaderThreeYears(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_three_years,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvNavThreeYears);
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
            Intent home = new Intent(ThreeYears.this,Home.class);
            startActivity(home);

        } else if (id == R.id.Profile) {
            Intent profile = new Intent(ThreeYears.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.Expenditures) {
            Intent expenditures = new Intent(ThreeYears.this,Expenditures.class);
            startActivity(expenditures);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(ThreeYears.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent socialMedia = new Intent(ThreeYears.this,SocialMedia.class);
            startActivity(socialMedia);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
