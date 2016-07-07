package com.example.abvn237.myapplication;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Intent;

import java.util.Random;

public class ValidateAccount extends AppCompatActivity {

    ListView listview;
    SQLiteDatabase db;
    DatabaseHelper dbhelper;
    EditText etAccNr, etName, etIDNumber,etSurname,etAddress,etCardNr,etIdNr;
    Button bValidate;

    Cursor cursor;
    private ListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_account);
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

        //listview = (ListView) findViewById(R.id.listView);
       // etAccNr = (EditText) findViewById(R.id.etAccountNr);
        etAccNr = (EditText) findViewById(R.id.etAccNr);
        etIdNr = (EditText) findViewById(R.id.etIdNr);
        // etIdNr = (EditText) findViewById(R.id.etCardNr);
        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);





    }


    //Method for handling different events
    public void onClickValidate(View v) {

        switch (v.getId()) {
            case R.id.bValidate:

                //Declaring variables for retrieving the values from the text view
                String AccNr, Name, Surname,Address, CardNr, IdNr;

                //    IdNr = etIDNumber.getText().toString();
                AccNr = etAccNr.getText().toString();
                IdNr = etIdNr.getText().toString();
                Name = etName.getText().toString();
                Surname = etSurname.getText().toString();

                //   Address = etAddress.getText().toString();




                ValidateAccount(AccNr,IdNr,Name,Surname);

                break;
            case R.id.bLogin:
                Intent login = new Intent(ValidateAccount.this,Login.class);
                startActivity(login);


        }


    }


    public void ValidateAccount(String AccNr ,String IdNr,String Name, String Surname){


       // db=openOrCreateDatabase("BusinessBankingApp30.sql",MODE_PRIVATE,null);


       /* if (IdNr.equals("")) {
            etIDNumber.setError("Supply your ID Number");

        }*/

        if (AccNr.equals("")) {
            etAccNr.setError("Supply your Account Number");

        }
        else if (IdNr.equals("")) {
            etIdNr.setError("Supply your ID Number");
           /* Toast.makeText(ValidateAccount.this, "Supply your ID Number",
                    Toast.LENGTH_SHORT).show();*/
        }
      /*  else  if (AccNr.equals("")) {

            etAccNr.setError("Supply your Account Number");

        }*/
        else  if (Name.equals("")) {

            etName.setError("Supply your Name");

           /* Toast.makeText(ValidateAccount.this, "Supply your Name",
                    Toast.LENGTH_SHORT).show();*/
        } else if (Surname.equals("")) {

            etSurname.setError("Supply your Surname");
            /*Toast.makeText(ValidateAccount.this, "Supply your Surname",
                    Toast.LENGTH_SHORT).show();*/
        }
        /*else if (Address.equals("")) {

            etAccNr.setError("Supply your Address");

        }*/
        else {
            Intent validateAccount = new Intent(ValidateAccount.this, Registration.class);
            startActivity(validateAccount);

          //  checkExistance(AccNr,IdNr,Name,Surname);

        }




    }

    public void checkExistance(String AccNr,String IdNr,String Name,String Surname){
        //The while loop for retrieving information from the database and comparing with the suppplied information from the text
        Cursor c = db.rawQuery(" SELECT ACC_HOLDER_ID,ACC_HOLDER_NAME,ACC_HOLDER_SURNAME,ADDRESS,ID_NUMBER,ACC_NR,USER_ID FROM Account_Holder WHERE ID_NUMBER = "+IdNr, null);

        while (c.moveToNext()) {


            //declaring and assigning the values from the cusor to the variables
            String accHolderId=c.getString(0);
            String name = c.getString(1);
            String surname = c.getString(2);
            String address = c.getString(3);
            String idNr = c.getString(4);
            String accNr = c.getString(5);
            String userId=c.getString(6);







            AccountHolder accountHolder = new AccountHolder(accHolderId,name,surname,address,idNr,accNr);

            //Account account  =



            Toast.makeText(ValidateAccount.this, "Account Number :"+accountHolder.getAccountNumber()+" && "+AccNr,
                    Toast.LENGTH_SHORT).show();
            Toast.makeText(ValidateAccount.this, "Card Number :"+accountHolder.getIDNumber()+" && "+IdNr,
                    Toast.LENGTH_SHORT).show();
            Toast.makeText(ValidateAccount.this, "Name :"+accountHolder.getAccountHolderName()+" && "+Name,
                    Toast.LENGTH_SHORT).show();
            Toast.makeText(ValidateAccount.this, "Surname :"+accountHolder.getAccountHolderSurname()+" && "+Surname,
                    Toast.LENGTH_SHORT).show();
             /*   Toast.makeText(ValidateAccount.this, "Address :"+accountHolder.getAddress()+" && "+Address,
                        Toast.LENGTH_SHORT).show();*/
            /*    Toast.makeText(ValidateAccount.this, "Surname :"+accountHolder.getAccountHolderSurname()+" && "+Surname,
                        Toast.LENGTH_SHORT).show();*/

              /*  SharedPreferences accessValue1 = this.getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = accessValue1.edit();

                edit.putString("AccHolderName", accountHolder.getAccountHolderName());
                edit.putString("AccHolderSurname",accountHolder.getAccountHolderSurname());
                edit.putString("Address",accountHolder.getAddress());
                edit.putString("IDNumber",accountHolder.getIDNumber());
                edit.putString("AccNr",accountHolder.getAccountNumber());*/



              /*  Toast.makeText(ValidateAccount.this, accountHolder.getAccountHolderName()+"& "+accountHolder.getAccountHolderSurname()+"& "+accountHolder.getAddress()+"& "+accountHolder.getIDNumber()+"& "+accountHolder.getAccountNumber(),
                        Toast.LENGTH_SHORT).show();*/



            //accountHolder.setAccountHolder(AccHolderId,name,surname,address,idNr,accNr);


               /* Intent notification = new Intent("android.provider.Telephony.SMS_RECEIVED");
                byte[] b = (byte[]) (SmsMessage.getSubmitPdu("5551","5552","some_text",false).encodedMessage);
                Object[] vrs = {b};
                notification.putExtra("pdus", vrs);
                sendBroadcast(notification);


                ContentValues values = new ContentValues();
                values.put("address","5551");
                values.put("body","the text of this message");
                getContentResolver().insert(Uri.parse("content://sms/inbox"),values);*/

            //Checking the correspondence of the information from the text view and the database
              /*  Toast.makeText(ValidateAccount.this, "Account Holder Id :"+accountHolder.getAccountHolderId(),
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(ValidateAccount.this, "Name :"+accountHolder.getAccountHolderName()+" && "+Name,
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(ValidateAccount.this, "Surname :"+accountHolder.getAccountHolderSurname()+" && "+Surname,
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(ValidateAccount.this, "Address :"+accountHolder.getAddress()+" && "+Address,
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(ValidateAccount.this, "Id Number :"+accountHolder.getIDNumber()+" && "+IdNr,
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(ValidateAccount.this, "Account Number :"+accountHolder.getAccountNumber()+" && "+AccNr,
                        Toast.LENGTH_SHORT).show();*/
            //&& AccNr.equals(accNr) &&  Name.equals(name) && Surname.equals(surname) && Address.equals(address)



            if ( !accountHolder.getAccountNumber().equals(AccNr) && !accountHolder.getIDNumber().equals(IdNr) && !accountHolder.getAccountHolderName().equals(Name) && !accountHolder.getAccountHolderSurname().equals(Surname)) {



                Toast.makeText(ValidateAccount.this, "The information supplied does n't exists, register for online banking.",
                        Toast.LENGTH_SHORT).show();




            } else {


                //The intent for redirecting to the registration activity
                Intent validateAccount = new Intent(ValidateAccount.this, Registration.class);
                startActivity(validateAccount);

            }

        }
    }

    // }


}
