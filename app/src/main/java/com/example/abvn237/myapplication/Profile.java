package com.example.abvn237.myapplication;

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


public class  Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final int SELECT_PHOTO = 1;
    private static final int RESULT_LOAD_IMAGE=1;
    NavigationView navigation_view;

    Button bUploadPicture, bViewPicture;

    ImageView imgUpload,user_picture;
    DatabaseHelper myDB;
    SQLiteDatabase db;
    TextView user_name,user_email;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setNavigationHeader();
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

       // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        bUploadPicture = (Button) findViewById(R.id.bUploadPicture);
        bViewPicture = (Button) findViewById(R.id.bViewPicture);



        TextView tvUName;
        tvUName = (TextView) findViewById(R.id.tvUName);


        Context context=null;
       /* SharedPreferences pref = getSharedPreferences("MyPrefs", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);*/
      //  String accNumber="1234567890";


       // tvUName.setText(accNumber);


    }

    public void redirectNewsFeeds(View view){

        Intent newsFeeds = new Intent(Profile.this,EditProfile.class);
        startActivity(newsFeeds);

    }

    public void redirectEditProfile(View view){

        Intent editProfile = new Intent(Profile.this,EditProfile.class);
        startActivity(editProfile);

    }

    public void redirectRemoveProfile(View view){

        Intent removeProfile = new Intent(Profile.this,RemoveProfile.class);
        startActivity(removeProfile);

    }

    public void onClickProfile(View view){



   eventHandler(view);

    }



    public void eventHandler(View view){

        switch(view.getId())
        {

            case R.id.imgUpload:


                break;
            case R.id.bUploadPicture:


              //  UploadImage();
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);



                break;

            case R.id.bViewPicture:




                viewImage();


                break;
        }
    }



    public void UploadImage(){

     /*   Intent in = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        in.putExtra("crop","true");
        in.putExtra("outputX", 100);
        in.putExtra("outputY",100);
        in.putExtra("scale", true);*/






        try {


          /*  Intent in = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            in.putExtra("crop","true");
            in.putExtra("outputX", 100);
            in.putExtra("outputY",100);
            in.putExtra("scale", true);




            startActivityForResult(in, 1);*/


          /*  byte[] image = new byte[fls.available()];
            fls.read(image);*/

            openGallery(SELECT_PHOTO);

            FileInputStream fls = new FileInputStream("storage/sdcard/savedImages/pic12.jpg");
            BufferedInputStream bif = new BufferedInputStream(fls);
            byte[] byteImage1 = new byte[bif.available()];
            bif.read(byteImage1);

            myDB.addPicture(byteImage1, db);
            bif.close();

            Toast.makeText(Profile.this, "The picture was uploaded successfully",
                    Toast.LENGTH_SHORT).show();



        } catch (IOException e) {
            e.printStackTrace();
        }




      /*  Bitmap bitmap = BitmapFactory.decodeFile("picturePath");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);

        Object image = null;

        try{

            String path = null;
            //  image = readInFile()
        }
        catch(Exception e){

            e.printStackTrace();
        }*/

    }

    public void viewImage(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
    }



    public void openGallery(int req_code){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Selected file to upload"), req_code);

    }

    public String getPath(Uri uri){

        if(uri == null){
            return null;
        }

        String[] projection= {MediaStore.Images.Media.DATA};

        Cursor cursor = managedQuery(uri,projection,null,null,null);

        if(cursor != null){

            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();

            return cursor.getString(column_index);


        }

        return uri.getPath();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
          //  String[] filePathColumn = {MediaStore.Images.Media.DATA};

            imgUpload.setImageURI(selectedImage);




          /*  Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn,null,null,null);

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            imgUpload.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            Bitmap bmp = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG,100,baos);

            byte[] b = baos.toByteArray();
            String encodedImageString;

            encodedImageString = Base64.encodeToString(b,Base64.DEFAULT);

            byte[] bytearray = Base64.decode(encodedImageString,Base64.DEFAULT);
            Bitmap bmimage = BitmapFactory.decodeByteArray(bytearray, 0,
                    bytearray.length);*/

          /*  Bundle b2 = getIntent().getExtras();
            String img;
            ImageView targetImage;

            targetImage = (ImageView) findViewById(R.id.imgUpload);


            if(b2!=null){
            img = b2.getString("image");
            targetImage.setImageURI("image");

            }*/

           // myDB.addPicture(bmimage,db);

            Toast.makeText(getApplicationContext()," The picture was successfully uploaded!!!",
                    Toast.LENGTH_SHORT).show();









            /*if(requestCode == SELECT_FILE1){
                selectedPath1 = getPath(selectedImage);

            }

            if(requestCode == SELECT_FILE2){

                selectedPath2 = getPath(selectedImage);

            }*/


        }

    }

    public void saveImage(View view){

        //Bitmap bitmap = null;
        //  bitmap = BitmapFactory.decodeFile()

        //URLEncoder.
        //  myDB.addPicture(bitmap,db);

        Bitmap bitmap = BitmapFactory.decodeResource( getResources(),R.drawable.pic2);

        imgUpload.setImageBitmap(bitmap);

       /* try{
            FileInputStream fls = new FileInputStream("storage/1B01-0B0C/pic2.jpg");
            byte[] image = new byte[fls.available()];

            fls.read(image);
            Bitmap bitmap2 = null;
            myDB.addPicture(bitmap,db);




        }
        catch(Exception e){

        }*/
    }



    public void getImage(View view){

        Cursor c = db.rawQuery("SELECT Picture FROM Gallery", null);

        if(c.moveToNext())
        {

            byte[] image = c.getBlob(0);
            Bitmap bmp = BitmapFactory.decodeByteArray(image,0,image.length);
            imgUpload.setImageBitmap(bmp);

            Toast.makeText(this,"select success",Toast.LENGTH_SHORT).show();



        }
    }


    public void getInformation(String accNr) {
        Cursor c = db.rawQuery(" SELECT ac.ACC_HOLDER_NAME,ac.ACC_HOLDER_SURNAME FROM Account_Holder ah, Account a WHERE ah.ACC_NR =a.ACC_NR AND a.ACC_NR=" + accNr, null);
        while (c.moveToNext()) {


            String Name = c.getString(0);
            String Surname = c.getString(1);
           // String  = c.getString(2);



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
        getMenuInflater().inflate(R.menu.profile, menu);
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

            Intent newsFeed = new Intent(Profile.this,NewsFeeds.class);
            startActivity(newsFeed);
           // return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void setNavigationHeader(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);
        user_picture = (ImageView) findViewById(R.id.imgSocialMediaNav);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_profile,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvUName);
        Context context=null;

      //  Bitmap bmp;

      //  int drawable =R.drawable.pic2;


        //user_picture.setImageDrawable(R.drawable.pic2);

      //  user_picture.setImageResource(drawable);

     /*   Bitmap bitmap = BitmapFactory.decodeResource( getResources(),R.drawable.pic2);

        user_picture.setImageBitmap(bitmap);*/

        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String cardNumber = pref.getString("CardNr", null);
        String Name=pref.getString("Name",null);
        String Surname=pref.getString("Surname",null);


        edit.commit();


        String text="Welcome "+Name+" "+Surname;

        user_name.setText(text);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


      /*  NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header_profile,navigationView,false);
        navigationView.addHeaderView(headerView);

        TextView tv = (TextView) findViewById(R.id.tvUName);

        tv.setText("Vusi");*/



        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.Home) {
            // Handle the camera action
            Intent home = new Intent(Profile.this,Home.class);
            startActivity(home);

        } else if (id == R.id.ViewGallery) {

            Intent viewGallery = new Intent(Profile.this,Profile.class);
            startActivity(viewGallery);

        } else if (id == R.id.Expenditures) {
            Intent expenditures = new Intent(Profile.this,Expenditures.class);
            startActivity(expenditures);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(Profile.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            Intent socialMedia = new Intent(Profile.this,SocialMedia.class);
            startActivity(socialMedia);

        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
