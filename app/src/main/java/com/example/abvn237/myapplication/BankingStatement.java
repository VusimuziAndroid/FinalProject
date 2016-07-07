package com.example.abvn237.myapplication;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.pdf.PdfDocument;
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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Context;

import java.io.*;
import java.util.*;


public class BankingStatement extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final android.database.sqlite.SQLiteDatabase.CursorFactory MODE_PRIVATE =null ;
    public CheckBox chkSaving,chkCheque,chkVISA,chkDebit; // Declaring variables for the checkboxes
    SQLiteDatabase db; // declaring the variable for the sqlite database
    Context context=null;
    DatabaseHelper myDB; // declaring variable for the sqlite helper class


    TextView user_name,user_email; // declaring variable for the textviewa
    ImageView imgUpload,user_picture; // declaring variable for the ImageView
    NavigationView navigation_view; // declaring variable for the Navigation View


    private ListView lsSavings, lsCheque, lsCash , lsDebit, lsVISA; // declaring variables for the list viwews
    private ListView lsAdapterSavings,lsAdapterCheque,lsAdapterCash, lsAdapterdebit, lsAdapterVISA; //delcaring list adapter variables
    private ListAdapter adapterSavings, adapterCheque,adapterCash,adapterDebit,adapterVISA; // declaring list adapter variables
    // Adapter adapter;


    ListView lsShowStatement =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banking_statement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setNavigationHeaderBS();


        SQLiteDatabase db;

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




        chkSaving = (CheckBox) findViewById(R.id.chkSavings); //binding the declared variable to the Savings chechbox
        chkDebit = (CheckBox) findViewById(R.id.chkDebit); // Binding the declzred variable to the debit checkbox
        chkVISA = (CheckBox) findViewById(R.id.chkVISA); // binding the declared variable to the VISA checkox
        chkCheque = (CheckBox) findViewById(R.id.chkCheque); // binding the declarde variable to the Cheque checkbox


        chkSaving.setOnClickListener(checkboxClickListener); //The OnClick Listener for the Savings chackbox
        chkDebit.setOnClickListener(checkboxClickListener); // The OnClick Listener for the Debit Checkbox
        chkVISA.setOnClickListener(checkboxClickListener); // The OnClick Listener for the VISA check box
        chkCheque.setOnClickListener(checkboxClickListener); // The OnClick Listener for the Cheque check box

      //  db=openOrCreateDatabase("BusinessBankingApp29.sql",MODE_PRIVATE,null);
    }


    View.OnClickListener checkboxClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){

          // Cursor c = db.rawQuery(" SELECT ACC_TYPE_ID,ACC_TYPE_DESC FROM Acc_Type",null);


                        /* SharedPreferences pref = getSharedPreferences("MyPref",context.MODE_PRIVATE);

                        pref.getString("AccNr", null);
                        pref.getString("PinNr",null); */


            boolean checked = ((CheckBox) view).isChecked();
            if(checked){
                String text = null;
                switch(view.getId()){
                    case R.id.chkSavings:

                        String AccType=chkSaving.getText().toString();

                      //  Toast.makeText(BankingStatement.this, " Savings", Toast.LENGTH_SHORT).show();


                        checkAccountType(AccType);







                        break;
                    case R.id.chkDebit:
                        String AccType2=chkDebit.getText().toString();

                        checkAccountType(AccType2);
                      //  Toast.makeText(BankingStatement.this, " Debit ", Toast.LENGTH_SHORT).show();

                   /* Intent debit = new Intent(BankingStatement.this,ViewStatement.class);
                    startActivity(debit);*/
                      /*  lsSavings.setAdapter(adapterDebit);
                        Toast.makeText(BankingStatement.this, " Debit", Toast.LENGTH_SHORT).show();*/
                        // text="Weekly";
                        break;
                    case R.id.chkVISA:
                      /*  Intent visa = new Intent(BankingStatement.this,VISA.class);
                        startActivity(visa);*/

                        String AccType3=chkVISA.getText().toString();

                        checkAccountType(AccType3);
                      //  Toast.makeText(BankingStatement.this, " VISA ", Toast.LENGTH_SHORT).show();
                   /* Intent visa = new Intent(BankingStatement.this,ViewStatement.class);
                    startActivity(visa);*/

                        //  lsSavings.setAdapter(adapterVISA);
                        // Toast.makeText(BankingStatement.this, "VISA", Toast.LENGTH_SHORT).show();
                        // text="Monthly";
                        break;
                    case R.id.chkCheque:
                        String AccType4=chkCheque.getText().toString();

                        checkAccountType(AccType4);

                       /* Intent cheque = new Intent(BankingStatement.this,Cheque.class);
                        startActivity(cheque);*/
                        // lsSavings.setAdapter(adapterCheque);
                        //  Toast.makeText(BankingStatement.this, "Cheque", Toast.LENGTH_SHORT).show();
                        // text="Yearly";


                       // Toast.makeText(BankingStatement.this, " Cheque ", Toast.LENGTH_SHORT).show();

                   /* Intent cheque = new Intent(BankingStatement.this,ViewStatement.class);
                    startActivity(cheque);*/


                        break;
                }

            }


         //   eventHandler(view);


        }
    };



    public void eventHandler(View view){


        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            String text = null;
            switch(view.getId()){
                case R.id.chkSavings:

                    String AccType = chkSaving.getText().toString();


                    db.openOrCreateDatabase("BusinessBankingApp29.sql",MODE_PRIVATE,null);
                    db = myDB.getReadableDatabase();

                    Cursor c = db.rawQuery(" SELECT ACC_TYPE_ID, ACC_TYPE_DESC FROM AccType ",null);


                    while(c.moveToNext()){


                        String accTypeId = c.getString(0);
                        String accTypeDesc = c.getString(1);

                        // Toast.makeText(BankingStatement.this,AccType+"  "+accTypeDesc, Toast.LENGTH_SHORT).show();

          /* if(AccType.equals(accTypeDesc)){

               Intent savings = new Intent(BankingStatement.this,ViewStatement.class);
               startActivity(savings);

           }
            else
           {
               Toast.makeText(BankingStatement.this, " The type of account you selected is not the valid account", Toast.LENGTH_SHORT).show();
           }*/

                    }

                  //  Toast.makeText(BankingStatement.this, " Savings", Toast.LENGTH_SHORT).show();


                    //checkAccountType(AccType);

                    break;
                case R.id.chkDebit:


                    Toast.makeText(BankingStatement.this, " Debit ", Toast.LENGTH_SHORT).show();

                   /* Intent debit = new Intent(BankingStatement.this,ViewStatement.class);
                    startActivity(debit);*/
                      /*  lsSavings.setAdapter(adapterDebit);
                        Toast.makeText(BankingStatement.this, " Debit", Toast.LENGTH_SHORT).show();*/
                    // text="Weekly";
                    break;
                case R.id.chkVISA:
                      /*  Intent visa = new Intent(BankingStatement.this,VISA.class);
                        startActivity(visa);*/

                    Toast.makeText(BankingStatement.this, " VISA ", Toast.LENGTH_SHORT).show();
                   /* Intent visa = new Intent(BankingStatement.this,ViewStatement.class);
                    startActivity(visa);*/

                    //  lsSavings.setAdapter(adapterVISA);
                    // Toast.makeText(BankingStatement.this, "VISA", Toast.LENGTH_SHORT).show();
                    // text="Monthly";
                    break;
                case R.id.chkCheque:
                       /* Intent cheque = new Intent(BankingStatement.this,Cheque.class);
                        startActivity(cheque);*/
                    // lsSavings.setAdapter(adapterCheque);
                    //  Toast.makeText(BankingStatement.this, "Cheque", Toast.LENGTH_SHORT).show();
                    // text="Yearly";


                    Toast.makeText(BankingStatement.this, " Cheque ", Toast.LENGTH_SHORT).show();

                   /* Intent cheque = new Intent(BankingStatement.this,ViewStatement.class);
                    startActivity(cheque);*/


                    break;
            }

        }


    }



    public void checkAccountType(String AccType){

       // Cursor c = db.rawQuery(" SELECT ACC_NR, CARD_NR, PIN_NUMBER, DATE_OPENED, ACC_TYPE_ID FROM Account WHERE ACC_NR = "+accountNr+" AND ACC_TYPE_ID = "+,null);



      //  Toast.makeText(BankingStatement.this,AccType+" ", Toast.LENGTH_SHORT).show();


        SharedPreferences pref = getSharedPreferences("MyPref",context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String sessionAccType =  pref.getString("AccType", null);


        // Toast.makeText(BankingStatement.this,"Select the correct account type", Toast.LENGTH_SHORT).show();




        if (sessionAccType.equals(AccType)) {



            Intent viewStatement = new Intent(BankingStatement.this,Period.class);
            startActivity(viewStatement);

        }
        else{
            Toast.makeText(BankingStatement.this,"Select the correct account type", Toast.LENGTH_SHORT).show();

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
        getMenuInflater().inflate(R.menu.banking_statement, menu);
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


    public void setNavigationHeaderBS(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_banking_statement,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvNavBankingStatement);
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
            Intent home = new Intent(BankingStatement.this,Home.class);
            startActivity(home);
        } else if (id == R.id.Profile) {

            Intent profile = new Intent(BankingStatement.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.CashTransfer) {
            Intent cashTransfer = new Intent(BankingStatement.this,CashTransfer.class);
            startActivity(cashTransfer);

        } else if (id == R.id.CashDeposit) {

            Intent cashDeposit = new Intent(BankingStatement.this,CashDeposit.class);
            startActivity(cashDeposit);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            Intent socialMedia = new Intent(BankingStatement.this,SocialMedia.class);
            startActivity(socialMedia);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
