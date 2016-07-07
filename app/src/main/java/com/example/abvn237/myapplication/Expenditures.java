package com.example.abvn237.myapplication;

import android.content.SharedPreferences;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;



public class Expenditures extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public CheckBox chkSavings2,chkCheque2,chkDebit2,chkVISA2;
    private RelativeLayout mainLayout;
    Context context=null;
    //private PieChart mChart;

    TextView user_name,user_email;
    ImageView imgUpload,user_picture;
    NavigationView navigation_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditures);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavigationHeaderExpenditures();

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


        chkSavings2 = (CheckBox) findViewById(R.id.chkSavings2);
        chkCheque2 = (CheckBox) findViewById(R.id.chkCheque2);
        chkDebit2 = (CheckBox) findViewById(R.id.chkDebit2);
        chkVISA2 = (CheckBox) findViewById(R.id.chkVISA2);


        chkSavings2.setOnClickListener(checkboxClickListener);
        chkCheque2.setOnClickListener(checkboxClickListener);
        chkDebit2.setOnClickListener(checkboxClickListener);
        chkVISA2.setOnClickListener(checkboxClickListener);

    }


    View.OnClickListener checkboxClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){

            eventHandler(view); //The method for handling event for the checkboxes

        }
    };

    public void eventHandler(View view){


        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            String text = null;
            switch(view.getId()){
                case R.id.chkSavings2:

                    String AccType=chkSavings2.getText().toString();



                    checkAccountType(AccType);



                      /*  Intent viewStatement = new Intent(Expenditures.this,ViewStatement.class);
                        startActivity(viewStatement);*/




                    break;
                case R.id.chkDebit2:
                    String AccType2=chkDebit2.getText().toString();

                    checkAccountType(AccType2);
                    //  Toast.makeText(BankingStatement.this, " Debit ", Toast.LENGTH_SHORT).show();

                   /* Intent debit = new Intent(BankingStatement.this,ViewStatement.class);
                    startActivity(debit);*/
                      /*  lsSavings.setAdapter(adapterDebit);
                        Toast.makeText(BankingStatement.this, " Debit", Toast.LENGTH_SHORT).show();*/
                    // text="Weekly";
                    break;
                case R.id.chkVISA2:
                      /*  Intent visa = new Intent(BankingStatement.this,VISA.class);
                        startActivity(visa);*/

                    String AccType3=chkVISA2.getText().toString();

                    checkAccountType(AccType3);


                       /* Intent viewStatement = new Intent(Expenditures.this,ViewStatement.class);
                        startActivity(viewStatement);*/
                    //  Toast.makeText(BankingStatement.this, " VISA ", Toast.LENGTH_SHORT).show();
                   /* Intent visa = new Intent(BankingStatement.this,ViewStatement.class);
                    startActivity(visa);*/

                    //  lsSavings.setAdapter(adapterVISA);
                    // Toast.makeText(BankingStatement.this, "VISA", Toast.LENGTH_SHORT).show();
                    // text="Monthly";
                    break;
                case R.id.chkCheque2:
                    String AccType4=chkCheque2.getText().toString();

                    checkAccountType(AccType4);

                        /*Intent viewStatement = new Intent(Expenditures.this,ViewStatement.class);
                        startActivity(viewStatement);*/

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
            // Toast.makeText(Expenditures.this, text, Toast.LENGTH_SHORT).show();
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



            Intent viewExpenditures = new Intent(Expenditures.this,ViewExpenditures.class);
            startActivity(viewExpenditures);

        }
        else{
            Toast.makeText(Expenditures.this,"Select the correct account type", Toast.LENGTH_SHORT).show();

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
        getMenuInflater().inflate(R.menu.expenditures, menu);
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
            //return true;
            Intent newsFeed = new Intent(Expenditures.this,NewsFeeds.class);
            startActivity(newsFeed);
        }

        return super.onOptionsItemSelected(item);
    }


    public void setNavigationHeaderExpenditures(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_expenditures,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvNavViewExpenditures);
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
            Intent home = new Intent(Expenditures.this,Home.class);
            startActivity(home);
        } else if (id == R.id.Profile) {
            Intent profile = new Intent(Expenditures.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(Expenditures.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.CashTransfer) {
            Intent cashTransfer = new Intent(Expenditures.this,CashTransfer.class);
            startActivity(cashTransfer);

        }
        /*else if (id == R.id.nav_share) {

        } */
        else if (id == R.id.nav_send) {
            Intent socialMedia = new Intent(Expenditures.this,SocialMedia.class);
            startActivity(socialMedia);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
