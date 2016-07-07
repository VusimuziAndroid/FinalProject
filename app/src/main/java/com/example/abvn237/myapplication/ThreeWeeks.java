package com.example.abvn237.myapplication;

import android.app.AlertDialog;
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
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ThreeWeeks extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SQLiteDatabase db;//declaring the variable for the sqlite database

    ArrayAdapter<String> adapterThreeWeeks2; //declaring variable for the Array Adapter
    ArrayList<String> threeWeeks2= new ArrayList<String>(); // declaring variable for the Array List
    ListView lsThreeWeeks2; // declaring variable for the list view

    SpendingPattern sp;
    Calendar calendar;
    Context context=null;

    TextView user_name,user_email;
    ImageView imgUpload,user_picture;
    NavigationView navigation_view;

    ArrayList<String> suggestedPattern3 = new ArrayList<>();
    ArrayAdapter<String> suggestedPatternAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_weeks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavigationHeaderThreeWeeks();

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
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        db=openOrCreateDatabase("BusinessBankingApp33.sql",MODE_PRIVATE,null);
        toggle.syncState();



        lsThreeWeeks2 = (ListView) findViewById(R.id.lsThreeWeeks);

        ViewExpenditures(); // calling the function to display the banking statement in three weeks




    }


    //The function for displaying the banking statement in three months using the cursor
    public void ViewExpenditures(){

        //opening the database before reading values

        populateValues();
        eventListener();





    }


    public void eventListener(){

        lsThreeWeeks2.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                String s = threeWeeks2.get(position);



                displayDialogFragment();

               /* Toast.makeText(ThreeMonths.this, "Select Month is " + s,
                        Toast.LENGTH_SHORT).show();*/


            }
        });

    }

    public void displayDialogFragment()
    {


        AlertDialog.Builder builder4 = new AlertDialog.Builder(ThreeWeeks.this);
        builder4.setTitle("Suggested Spending Pattern");
        builder4.setCancelable(false);


        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);


        edit.commit();

        //  String accNumber = getSessionAccNr();

        Cursor c2 = db.rawQuery(" SELECT sp.AMOUNT_SPENT AMOUNT_SPENT, sp.PERCENTAGE PERCENTAGE,sc.SC_DESCRIPTION SC_DESCRIPTION,sp.ACC_NR ACC_NR FROM Spending_Category sc, Spending_Pattern sp WHERE sc.SC_ID = sp.SC_ID ", null);


        suggestedPattern3.add("Category Amount Percentage Rewards");
        while (c2.moveToNext()) {


            String AmountSpent = c2.getString(0);
            String Percentage = c2.getString(1);
            String Description = c2.getString(2);
            String AccNr = c2.getString(3);


            getNewPercentage(Percentage, Description, AccNr);

            // sp = new Spending_Pattern(AmountSpent,Percentage,Description,AccNr);

            //  String Monthly[] = {sp.getSCategory() + "     " + sp.getAmountSpent() + "      " + sp.getPercentage()};
            String SCategory = sp.getSCategory();
            String AmtSpent = sp.getAmountSpent();
            String Perc = sp.getPercentage();

            //threeMonths.add(SCategory+"                  "+AmtSpent+"         "+Perc);
            suggestedPatternAdapter3 = new ArrayAdapter<String>(ThreeWeeks.this,
                    android.R.layout.simple_list_item_1,
                    suggestedPattern3);


            if (Percentage.equals("10%") || Percentage.equals("20%")) {
                String rewards;
                rewards = "5% discount";
                sp.setRewards(rewards);

                suggestedPattern3.add(SCategory + "  " + AmtSpent + "  " + Perc + "         " + sp.getRewards());

            } else {

                suggestedPattern3.add(SCategory + "     " + AmtSpent + "      " + Perc);

            }

            builder4.setAdapter(suggestedPatternAdapter3, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    suggestedPattern3.clear();
                    // Toast.makeText(getApplicationContext(), Monthly[which], Toast.LENGTH_SHORT).show();
                }


            });


            // builder4.setMessage(suggestedPattern.toString().trim());




                  /*  builder4.setItems(Monthly, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                           // Toast.makeText(getApplicationContext(), Monthly[which], Toast.LENGTH_SHORT).show();
                        }


                    });*/

        }
        builder4.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog4 = builder4.create();
        alertDialog4.show();
    }


    public void populateValues(){

        db=openOrCreateDatabase("BusinessBankingApp33.sql",MODE_PRIVATE,null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMMM dd");
        calendar = new GregorianCalendar(2016,3,18);

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year= calendar.get(Calendar.YEAR);



        String dateDay = sdf.format(calendar.getTime());
        String dateMonth = sdf.format(calendar.getTime());
        String dateYear = sdf.format(calendar.getTime());



        Toast.makeText(ThreeWeeks.this, "The current month is " + dateMonth,
                Toast.LENGTH_SHORT).show();


        Toast.makeText(ThreeWeeks.this, "The current day is " + dateDay,
                Toast.LENGTH_SHORT).show();



        Toast.makeText(ThreeWeeks.this, "The current Year is " + dateYear,
                Toast.LENGTH_SHORT).show();






        lsThreeWeeks2 = (ListView) findViewById(R.id.lsThreeWeeks);



        //The method for retrieving values from the sessions
        getSessions();

        //The method for diaplying values
        displayValues();





    }


    public void displayValues(){

        String startDate ="29 March 2016";
        String endDate = "19 April 2015";

        //Declaring the cursor to retrieve values from the Spending_Category and Spending_Pattern table

        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String trendDate = pref.getString("TrendDate", null);

        edit.commit();


        Datasource datasource = new Datasource();

       threeWeeks2 = datasource.displayThreeWeeksExpenditures(threeWeeks2,accNumber,db);

     /*   String strQuery = " SELECT DISTINCT sp.AMOUNT_SPENT AMOUNT_SPENT, sp.PERCENTAGE PERCENTAGE,sc.SC_DESCRIPTION SC_DESCRIPTION,sp.ACC_NR ACC_NR " +
                "FROM Spending_Category sc, Spending_Pattern sp, Trend t, Account a " +
                "WHERE  sc.SC_ID = sp.SC_ID AND t.ACC_NR = sp.ACC_NR AND a.ACC_NR = t.ACC_NR AND" +
                " t.TREND_DATE '"+startDate+"' AND t.ACC_NR="+accNumber+"'";


        Cursor c = db.rawQuery(strQuery+"'",null);


        threeWeeks2.add("Category         Amount         Percentage");
        while(c.moveToNext()){


            String AmountSpent = c.getString(0);
            String Percentage = c.getString(1);
            String Description = c.getString(2);
            String AccNr = c.getString(3);



            threeWeeks2.add(Description+"                  "+AmountSpent+"         "+Percentage);



        }*/


        adapterThreeWeeks2 = new ArrayAdapter<String>(ThreeWeeks.this,
                android.R.layout.simple_list_item_1,
                threeWeeks2);
        lsThreeWeeks2.setAdapter(adapterThreeWeeks2);
    }
    public void getSessions(){

        // String accNumber = getSessionAccNr();
        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String trendDate = pref.getString("TrendDate", null);
    }


    public void getNewPercentage(String Percentage,String Description, String AccNr){

        String strPercentageSuggested;
        String strNewAmountSpent;


        if(Percentage.equals("20%"))
        {
            strPercentageSuggested="10%";
            strNewAmountSpent="R1000";
            sp = new SpendingPattern(strNewAmountSpent,strPercentageSuggested,Description,AccNr);

        }
        else if(Percentage.equals("10%")){

            strNewAmountSpent="1500";
            strPercentageSuggested="15%";
            sp = new SpendingPattern(strNewAmountSpent,strPercentageSuggested,Description,AccNr);
        }
        else if(Percentage.equals("5%")){

            strNewAmountSpent="1500";
            strPercentageSuggested="15%";
            sp = new SpendingPattern(strNewAmountSpent,strPercentageSuggested,Description,AccNr);

        }
                  /*  else if(Percentage.equals("20%") && Percentage.equals("10%")){
                        strNewAmountSpent="R2000";
                        strPercentageSuggested="20%";
                        sp = new Spending_Pattern(strNewAmountSpent,strPercentageSuggested,Description,AccNr);
                        suggestedPattern.add("You have been rewarded with 5% benefit for buying electricity or prepaid air time ");
                    }*/
        else{
            strNewAmountSpent="R2000";
            strPercentageSuggested="20%";
            sp = new SpendingPattern(strNewAmountSpent,strPercentageSuggested,Description,AccNr);


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
        getMenuInflater().inflate(R.menu.three_weeks, menu);
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
            Intent newsFeed = new Intent(ThreeWeeks.this,NewsFeeds.class);
            startActivity(newsFeed);
        }

        return super.onOptionsItemSelected(item);
    }


    public void setNavigationHeaderThreeWeeks(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_three_weeks,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvNavThreeWeeks);
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
            Intent home = new Intent(ThreeWeeks.this,Home.class);
            startActivity(home);

        } else if (id == R.id.Profile) {
            Intent profile = new Intent(ThreeWeeks.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.Expenditures) {
            Intent expenditures = new Intent(ThreeWeeks.this,Expenditures.class);
            startActivity(expenditures);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(ThreeWeeks.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent socialMedia = new Intent(ThreeWeeks.this,SocialMedia.class);
            startActivity(socialMedia);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
