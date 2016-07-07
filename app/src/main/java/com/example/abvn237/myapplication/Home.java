package com.example.abvn237.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;


public class Home extends AppCompatActivity {
    Button bProfile,bBankingStatement,bSocialMedia,bExpenditure,bCashTransfer,bCashDeposit;

    SQLiteDatabase db;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bSocialMedia= (Button) findViewById(R.id.bSocialMedia);
        bProfile = (Button) findViewById(R.id.bProfile);
        bBankingStatement = (Button) findViewById(R.id.bBankingStatement);
        bCashTransfer = (Button) findViewById(R.id.bCashTransfer);
        bExpenditure = (Button) findViewById(R.id.bExpenditures);
        bCashDeposit = (Button) findViewById(R.id.bCashDeposit);

        //The while loop for looping through the cursor



    }


    public void onClickHome(View view){



        eventHandler(view);

    }




    public void eventHandler(View view)
    {

        switch(view.getId()){
            case R.id.bProfile:

                // Intent advise = new Intent(Home.this,FinancialAdvise.class);
                // Intent

                Intent profile = new Intent(Home.this,Profile.class);
                startActivity(profile);

                /*TextView tv = (TextView)findViewById(R.id.tvUName);
                tv.setText("Vusi");*/

               /* Toast.makeText(getApplicationContext(), " View Balance ...",
                        Toast.LENGTH_SHORT).show(); */


                break;

            case R.id.bSocialMedia:


                Intent socialMedia = new Intent(Home.this,SocialMedia.class);
                startActivity(socialMedia);
                /*Toast.makeText(getApplicationContext(), " Profile ...",
                        Toast.LENGTH_SHORT).show();*/

                // Intent profile = new Intent(Home.this,Profile.class);
                break;

            case R.id.bBankingStatement:

                Intent BankingStatement = new Intent(Home.this,BankingStatement.class);
                startActivity(BankingStatement);

              /*  Toast.makeText(getApplicationContext(), " Social Media ...",
                        Toast.LENGTH_SHORT).show();*/
                // Intent socialMedia = new Intent(Home.this,SocialMedia.class);

                break;

            case R.id.bCashTransfer:


                Intent cashTransfer = new Intent(Home.this,CashTransfer.class);
                startActivity(cashTransfer);
               /* Toast.makeText(getApplicationContext(), " Trends ...",
                        Toast.LENGTH_SHORT).show();*/

                // Intent trends = new Intent(Home.this,Trends.class);

                break;

            case R.id.bExpenditures:


                Intent expenditures = new Intent(Home.this,Expenditures.class);
                startActivity(expenditures);
                /* Toast.makeText(getApplicationContext(), " Profile Picture ...",
                        Toast.LENGTH_SHORT).show(); */
                // Intent ProfilePicture = new Intent(Home.this,ProfilePicture);

                break;
            case R.id.bCashDeposit:


                Intent cashDeposit = new Intent(Home.this,CashDeposit.class);
                startActivity(cashDeposit);
                /* Toast.makeText(getApplicationContext(), " Profile Picture ...",
                        Toast.LENGTH_SHORT).show(); */
                // Intent ProfilePicture = new Intent(Home.this,ProfilePicture);

                break;
        }
    }

}
