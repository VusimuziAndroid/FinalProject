package com.example.abvn237.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.app.AlertDialog;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.test.mock.MockDialogInterface;
import android.util.Base64;
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
import android.widget.Button;
import android.content.DialogInterface;
import android.widget.ImageView;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import android.content.ContentValues;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vusi Ngwenya on 4/25/2016.
 */
public class NavigationHeader {



    public void setNavigationHeader(NavigationView navigation_view,View header,TextView user_name ){

        //navigation_view = (NavigationView) findViewById(R.id.nav_view);

        //View header = LayoutInflater.from(this).inflate(R.layout.nav_header_profile,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvUName);
        Context context=null;


       /* String Name = pref.getString("Name", null);
        String Surname = pref.getString("Surname", null);*/


        //   String text="Welcome Busi Nkosi";
        //user_name.setText(text);

        /*Cursor c = db.rawQuery(" SELECT ah.ACC_HOLDER_NAME ACC_HOLDER_NAME,ah.ACC_HOLDER_SURNAME ACC_HOLDER_SURNAME FROM Account_Holder ah, Account a  WHERE ah.ACC_NR=a.ACC_NR AND ah.ACC_NR=" + accNumber, null);
        while (c.moveToNext()) {


            String Name2 = c.getString(0);
            String Surname2 = c.getString(1);


            String text="Welcome "+Name2+" "+Surname2;

            user_name.setText(text);





        }*/







    }
}
