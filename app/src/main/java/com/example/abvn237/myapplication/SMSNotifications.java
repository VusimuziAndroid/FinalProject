package com.example.abvn237.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.content.ContentResolver;


/**
 * Created by ABVN237 on 4/7/2016.
 */
public class SMSNotifications {


    public void SMSManager(String strPhoneNr,String SMSMessage){

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(strPhoneNr,null,SMSMessage,null,null);


    }
    public void sendSMSNotification(String smsBody){

        /*Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.putExtra(smsBody);
        smsIntent.setType("vnd.android-dir/mms-sms");
        startActivity(smsIntent);*/



        Intent notification = new Intent("android.provider.Telephony.SMS_RECEIVED");
        byte[] b = (byte[]) (SmsMessage.getSubmitPdu("5551","5552","some_text",false).encodedMessage);
        Object[] vrs = {b};
        notification.putExtra("pdus", vrs);
       // sendBroadcast(notification);


        ContentValues values = new ContentValues();
        values.put("address","5551");
        values.put("body", "the text of this message");
      //  getContentResolver().insert(Uri.parse("content://sms/inbox"), values);



    }
}
