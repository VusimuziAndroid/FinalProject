package com.example.abvn237.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewExpenditures extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public CheckBox chkThreeWeeks,chkThreeMonths,chkThreeYears;
    TextView user_name,user_email;
    ImageView imgUpload,user_picture;
    NavigationView navigation_view;

   /* String ThreeWeeks[] ={"Current Date : 3 March 2016", "Balance : R1000","Available Amount : R3500"};
    String ThreeMonths[] ={"Date : 3 March 2016", "Balance : R1000","Available Amount : R3500",
            "    ",
            "Date : 09 March 2016", "Balance : R1000","Available Amount : R3500",
            "    ",
            "Date : 17 March 2016", "Balance : R1000","Available Amount : R3500",
            "    ",
            "Date : 30 March 2016", "Balance : R1000","Available Amount : R3500"};

    String ThreeYears[] ={"Current Month : March 2016", "Balance : R45000","Available Amount : R7800"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenditures);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavigationHeaderExpenditure();

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



        chkThreeWeeks = (CheckBox) findViewById(R.id.chkThreeWeeks);
        chkThreeMonths = (CheckBox) findViewById(R.id.chkThreeMonths);
        chkThreeYears = (CheckBox) findViewById(R.id.chkThreeYears);


        chkThreeWeeks.setOnClickListener(checkboxClickListener);
        chkThreeMonths.setOnClickListener(checkboxClickListener);
        chkThreeYears.setOnClickListener(checkboxClickListener);
    }

    View.OnClickListener checkboxClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
          eventHandler(view);
        }
    };


    public void eventHandler(View view){


        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            String text = null;
            switch(view.getId()){
                case R.id.chkThreeWeeks:


                    Intent threeWeeks = new Intent(ViewExpenditures.this,ThreeWeeks.class);
                    startActivity(threeWeeks);

                    break;
                case R.id.chkThreeMonths:

                    Intent threeMonths = new Intent(ViewExpenditures.this,ThreeMonths.class);
                    startActivity(threeMonths);


                    break;
                case R.id.chkThreeYears:

                    Intent threeYears = new Intent(ViewExpenditures.this,ThreeYears.class);
                    startActivity(threeYears);

                    break;

            }

        }

    }


    public void setNavigationHeaderExpenditure(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_view_expenditures,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvNavExpenditures);
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
        getMenuInflater().inflate(R.menu.view_expenditures, menu);
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
            Intent newsFeed = new Intent(ViewExpenditures.this,NewsFeeds.class);
            startActivity(newsFeed);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            // Handle the camera action
            Intent home = new Intent(ViewExpenditures.this,Home.class);
            startActivity(home);

        } else if (id == R.id.Profile) {
            Intent profile = new Intent(ViewExpenditures.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(ViewExpenditures.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.CashTransfer) {
            Intent cashTransfer = new Intent(ViewExpenditures.this,CashTransfer.class);
            startActivity(cashTransfer);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent socialMedia = new Intent(ViewExpenditures.this,SocialMedia.class);
            startActivity(socialMedia);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
