package com.example.abvn237.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Context;


/**
 * Created by Vusi Ngwenya on 4/7/2016.
 */
public class Sessions {

    Account account;
    AccType accountType;
    User user;


    public Sessions(String accNumber,String cardNr,String pinNr,String idNr,String acTypeId, String accTypeDesc,String userName,String passWord,String name, String surName ){

        account = new Account(accNumber, cardNr, pinNr, acTypeId, idNr);

        accountType = new AccType(acTypeId, accTypeDesc);
        //  accType.setAccTypeId(AccTypeId);


        user = new User(userName, passWord);

    }

    public Account getAccount(){

        return this.account;
    }

    public AccType getAccountType(){

        return this.accountType;
    }

    public User getUser(){

        return this.user;
    }

      /*  public void storeSessionsAccount(String AccNr, String CardNr, String PinNr, String DateOpened, String AccTypeId, SharedPreferences pref) {

            Account account = new Account();



            SharedPreferences.Editor edit = pref.edit();

            edit.putString("AccNr", account.getAccountNr());
            edit.putString("PinNr", account.getPinNumber());
            edit.putString("CardNr", account.getCardNumber());
        }

        public String retrieveAccountNr(SharedPreferences accessValue1) {


            SharedPreferences.Editor e = accessValue1.edit();

            String AccNr;
            AccNr=accessValue1.getString("AccNr", null);

            return AccNr;

        }

        public String retrieveAccountSessionCardNr(String CardNr, SharedPreferences accessValue1) {

            SharedPreferences.Editor e = accessValue1.edit();

            CardNr=accessValue1.getString("CardNr", null);

            return CardNr;

        }

        public String retrieveAccountSessionPinNr(String PinNr, SharedPreferences accessValue1) {

            SharedPreferences.Editor e = accessValue1.edit();

            PinNr=accessValue1.getString("PinNr", null);

            return PinNr;

        }*/








}
