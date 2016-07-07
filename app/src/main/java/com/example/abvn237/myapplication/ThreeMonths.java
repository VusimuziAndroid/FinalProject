package com.example.abvn237.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.print.pdf.PrintedPdfDocument;
import android.provider.DocumentsContract;
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
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

//import org.w3c.dom.Document;

//import org.w3c.dom.Document;

import org.w3c.dom.Document;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ThreeMonths extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SQLiteDatabase db;

    ArrayAdapter<String> adapterThreeMonths ;
    ArrayList<String> threeMonths= new ArrayList<String>();
    ListView lsThreeMonths;

    ArrayList<String> suggestedPattern = new ArrayList<>();
    ArrayAdapter<String> suggestedPatternAdapter;
    private View mRootView;

    TextView user_name,user_email;
    ImageView imgUpload,user_picture;
    NavigationView navigation_view;



    /*String Monthly[] ={"Air Time      R500         5%", "Electricity   R1500          %15","Clothes         R2500       25%",
                       "Food      R2500        25%", "Furnisher      R2000        20%"};*/


    Trend trend;
    SpendingPattern sp;
    Context context=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_months);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavigationHeaderThreeMonths();

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




       getExpenditures();








    }


    public void getExpenditures(){



        populateValues();
        getExpendituresSuggestions();
    }

    public void populateValues(){

        db=openOrCreateDatabase("BusinessBankingApp33.sql",MODE_PRIVATE,null);

        lsThreeMonths = (ListView) findViewById(R.id.lsThreeMonths);

        // String accNumber = getSessionAccNr();
        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String trendDate = pref.getString("TrendDate", null);

        edit.commit();

        // String accNumber = getSessionAccNr();






      /*  while(c.moveToNext()){


            String AmountSpent = c.getString(0);
            String Percentage = c.getString(1);
            String Description = c.getString(2);
            String AccNr = c.getString(3);



            threeMonths.add(Description+"                  "+AmountSpent+"         "+Percentage);

        }*/
        Datasource datasource = new Datasource();
        threeMonths = datasource.displayThreeMonthsExpenditures(threeMonths,accNumber,db);

        adapterThreeMonths = new ArrayAdapter<String>(ThreeMonths.this,
                android.R.layout.simple_list_item_1,
                threeMonths);
        lsThreeMonths.setAdapter(adapterThreeMonths);
    }

    public void getExpendituresSuggestions()
    {
        lsThreeMonths.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                String s = threeMonths.get(position);


                AlertDialog.Builder builder4 = new AlertDialog.Builder(ThreeMonths.this);
                builder4.setTitle("Suggested Spending Pattern");
                builder4.setCancelable(false);


                SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

                SharedPreferences.Editor edit = pref.edit();

                String accNumber = pref.getString("AccNr", null);



                edit.commit();

                //  String accNumber = getSessionAccNr();

                Cursor c2 = db.rawQuery(" SELECT sp.AMOUNT_SPENT AMOUNT_SPENT, sp.PERCENTAGE PERCENTAGE,sc.SC_DESCRIPTION SC_DESCRIPTION,sp.ACC_NR ACC_NR FROM Spending_Category sc, Spending_Pattern sp WHERE sc.SC_ID = sp.SC_ID ", null);


                suggestedPattern.add("Category Amount Percentage Rewards");
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
                    suggestedPatternAdapter = new ArrayAdapter<String>(ThreeMonths.this,
                            android.R.layout.simple_list_item_1,
                            suggestedPattern);


                    if (Percentage.equals("10%") || Percentage.equals("20%")) {
                        String rewards;
                        rewards = "5% discount";
                        sp.setRewards(rewards);

                        suggestedPattern.add(SCategory + "  " + AmtSpent + "  " + Perc + "         " + sp.getRewards());

                    } else {

                        suggestedPattern.add(SCategory + "     " + AmtSpent + "      " + Perc);

                    }

                    builder4.setAdapter(suggestedPatternAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            suggestedPattern.clear();
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

                    createPdf();
                    }
                });
                AlertDialog alertDialog4 = builder4.create();
                alertDialog4.show();


               /* Toast.makeText(ThreeMonths.this, "Select Month is " + s,
                        Toast.LENGTH_SHORT).show();*/


            }
        });
    }

    public String getSessionAccNr(){

        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);

        //  edit.putString("PinNr",account.getPinNumber());


        // edit.commit();

        return accNumber;
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

    public void createPdf(){


    String state = Environment.getExternalStorageState();

        if(!Environment.MEDIA_MOUNTED.equals(state)){

            Toast.makeText(ThreeMonths.this,"",Toast.LENGTH_SHORT).show();
        }

        File pdfDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"myapplication");

        if(!pdfDir.exists()){

            pdfDir.mkdir();
        }

        Bitmap screen;

        View v1 = mRootView.getRootView();

        v1.setDrawingCacheEnabled(true);
        screen = Bitmap.createBitmap(v1.getDrawingCache());
        v1.setDrawingCacheEnabled(false);

        File pdfFile = new File(pdfDir,"storage/sdcard/savedpdfDocuments/banking_statment1.pdf");




    }

    public void convertToPdf(){

        try{

           Bitmap screen=null;
            File PdfWriter;
           //Document doc;
          //  Document docu
            Document document;


           // PdfWriter.getInstance(document,new FileOutputStream(file));
            //document.open();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            screen.compress(Bitmap.CompressFormat.PNG,100,stream);
            byte[] byteArray = stream.toByteArray();




        }
        catch(Exception e){

         e.printStackTrace();
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
        getMenuInflater().inflate(R.menu.three_months, menu);
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
            Intent newsFeed = new Intent(ThreeMonths.this,NewsFeeds.class);
            startActivity(newsFeed);
        }

        return super.onOptionsItemSelected(item);
    }


    public void setNavigationHeaderThreeMonths(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_three_months,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvNavThreeMonths);
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
            Intent home = new Intent(ThreeMonths.this,Home.class);
            startActivity(home);

        } else if (id == R.id.Profile) {
            Intent profile = new Intent(ThreeMonths.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.Expenditures) {
            Intent expenditures = new Intent(ThreeMonths.this,Expenditures.class);
            startActivity(expenditures);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(ThreeMonths.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent socialMedia = new Intent(ThreeMonths.this,SocialMedia.class);
            startActivity(socialMedia);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
