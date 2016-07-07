package com.example.abvn237.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Random;


//The class for the registration activity
public class Registration extends AppCompatActivity {


    //declaring variables for the edit texts on the model
    EditText etName,etSurname,etAddress,etUsername,etATMPin,etConfirmPin,etIdNumber,etAccNr,etStartdate,etCardNr,etAccTypeId,etPinNr; // declaring the edit texts
   // private static final android.database.sqlite.SQLiteDatabase.CursorFactory MODE_PRIVATE =null ;
    TextView tvLoginLink;
    DatabaseHelper helper;
    DatabaseHelper myDB; // declaring the database handler
    SQLiteDatabase db; // declaring the sqlite database variable
    Button buttonRegister; // creating the regitration button
   // Datasource datasource;
    Context context=null;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
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
        etUsername = (EditText) findViewById(R.id.etUsername);
        etATMPin = (EditText) findViewById(R.id.etATMPin);
        etConfirmPin = (EditText) findViewById(R.id.etConfirmPin);
        tvLoginLink = (TextView) findViewById(R.id.tvLogin);

        buttonRegister = (Button)findViewById(R.id.bRegister);



        AddUser();


    }











    public void AddUser(){

        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                helper = new DatabaseHelper(getApplicationContext());

                String userName = etUsername.getText().toString();
                String atmPin = String.valueOf(etATMPin.getText().toString());
                String confirmPin = etConfirmPin.getText().toString();

                user = new User(userName, atmPin);

                //user.setUsername();



              /*  Cursor c = db.rawQuery(" SELECT USER_ID,USERNAME,PASSWORD FROM User", null);
                while (c.moveToNext()) {


                    String userId = c.getString(0);
                    String username = c.getString(1);
                    String password = c.getString(2);


                    user.setUserId(userId);
                    user.setUsername(username);
                    user.setPassword(password);

                   account.setAccountNr(AccNr);
                    account.setCardNumber(CardNr);
                    account.setPinNumber(PinNr);
                    account.setIDNumber(IdNr);


                    SharedPreferences pref2 = getSharedPreferences("MyPrefs2",context.MODE_PRIVATE);

                    SharedPreferences.Editor edit = pref2.edit();

                    edit.putString("UserId",user.getUserId());
                    edit.putString("Username",user.getUsername());
                    edit.putString("Password",user.getPassword());


                }*/

                SharedPreferences pref = getSharedPreferences("MyPrefs",context.MODE_PRIVATE);

                SharedPreferences.Editor edit = pref.edit();

                edit.putString("Username",userName);
                edit.putString("Password",atmPin);
                edit.putString("AccNr",userName);
               /* edit.putString("PinNr",account.getPinNumber());
                edit.putString("IDNr",account.getPinNumber());*/
               // edit.putString("PinNr",account.getPinNumber());


                edit.commit();

               // Account account = new Account()

                if(userName.equals(""))
                {

                    etUsername.setError("Supply your Username");

                }
                else if(atmPin.equals("")){

                    etATMPin.setError("Supply your ATM Pin");

                }
                else if(confirmPin.equals("")){

                    etConfirmPin.setError("Supply your Confirm Pin");

                }
                else if(!confirmPin.equals(atmPin)){

                    Toast.makeText(getApplicationContext(), " Username and password does n't match",
                            Toast.LENGTH_SHORT).show();
                }
                else {


                    db = helper.getWritableDatabase();
                    db= openOrCreateDatabase("BusinessBankingApp34.sql", MODE_PRIVATE, null);
                    helper.addAccount(userName,atmPin,db);


                    Toast.makeText(getApplicationContext(), " Successfully registered !!!",
                            Toast.LENGTH_SHORT).show();

                    // setSessions();
//
//
//          /*  Intent home = new Intent(Registration.this, Home.class);
//            startActivity(home);*/

                }







            }
        });

    }


    public void setSessions() {

        Cursor c = db.rawQuery(" SELECT ACC_NR,CARD_NR,PIN_NUMBER,ID_NUMBER,USER_ID FROM Account WHERE ACC_NR=" + user.getUsername(), null);
        while (c.moveToNext()) {


            String AccNr = c.getString(0);
            String CardNr = c.getString(1);
            String PinNr = c.getString(2);
            String IdNr = c.getString(3);

            // String Username=c.getString(3);

           /* Account account = new Account();



            account.setAccountNr(AccNr);
            account.setCardNumber(CardNr);
            account.setPinNumber(PinNr);
            account.setIDNumber(IdNr);


              SharedPreferences pref = getSharedPreferences("MyPrefs",context.MODE_PRIVATE);

                SharedPreferences.Editor edit = pref.edit();

                edit.putString("AccNr",account.getAccountNr());
                edit.putString("CardNr",account.getCardNumber());
                edit.putString("PinNr",account.getPinNumber());
                edit.putString("IDNr",account.getPinNumber());*/
              /*  edit.putString("DateOpened",account.getDateOpened());
                edit.putString("AccTypeId",account.getAccTypeId());*/
        }
    }

//    public void onClickRegister(View view){
//


       // Registration(Username,ATMPin,ConfirmPin);

//        switch( view.getId()) {
//            case R.id.bRegister:
//
//
//
//
//
//
//              //  db = myDB.getWritableDatabase();
//              //  myDB.addAccount(Username, ATMPin,db);
//
//
//
//
//                break;
//            case R.id.tvLogin:
//
//                Toast.makeText(getApplicationContext(), " Login",
//                        Toast.LENGTH_SHORT).show();
//
//                break;
//        }

//    }
//
   /* public void onAddAccount(){
        bRegister.setOnClickListener(
        new View.OnClickListener(){



            @Override
            public void onClick(View v) {

                String Username = etUsername.getText().toString();
                String ATMPin = etATMPin.getText().toString();
                String ConfirmPin = etConfirmPin.getText().toString();
                Account account = new Account();

                openOrCreateDatabase("BusinessBankingApp24.sql",MODE_PRIVATE,null);


                db=myDB.getWritableDatabase();

                String sql = " INSERT INTO "+Database.User.TABLE_NAME+"("+Database.User.USERNAME+","+Database.User.PASSWORD+") " +
                        "VALUES ('"+account.getAccountNr()+"','"+account.getPinNumber()+"')";

                db.execSQL(sql);
                db.close();

                myDB.addAccount(Username,ATMPin,db);

                Toast.makeText(getApplicationContext(), " Successfully registered !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
