package com.example.abvn237.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.PageInfo.Builder;
import android.graphics.pdf.PdfRenderer;
//import org.apache.http.*;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.view.View.OnClickListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;


import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Rect;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ViewStatement extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public CheckBox chkSaving3,chkCheque3,chkVISA3,chkDebit3;
    ArrayAdapter<String> adapterViewStatement ;
    ArrayList<String> arrlstViewStatement= new ArrayList<String>();
    ListView lsViewStatement;
  //  private ListAdapter adapter;
    SQLiteDatabase db;
    DatabaseHelper myDB;
    SpendingPattern sp;
    public CheckBox chkDaily,chkWeekly,chkMonthly,chkYearly;
    TextView tvName;
    AccType accType;

    TextView user_name,user_email;
    ImageView imgUpload,user_picture;
    NavigationView navigation_view;

    Cursor cursor;

    ListView myView;








    Trend trend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_statement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavigationHeaderStatement();

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

        viewStatement();


    }

    public void viewStatement(){

        //opening or creating the database before retreiving values from the sqlite database

        populateStatement();

        listViewEvent();



    }

    public void populateStatement(){


        lsViewStatement = (ListView) findViewById(R.id.listView);


        String startDate = "01 January 2013";
        String endDate = "01 January 2015";

        Context context=null;

        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String trendDate = pref.getString("TrendDate", null);

        edit.commit();

        Toast.makeText(ViewStatement.this, " "+accNumber+" &"+trendDate,
                Toast.LENGTH_SHORT).show();



        Datasource datasource = new Datasource();

        db=myDB.getReadableDatabase();
        db = openOrCreateDatabase("BusinessBankingApp33.sql",MODE_PRIVATE,null);



        arrlstViewStatement = datasource.displayThreeYearsStatement(arrlstViewStatement,accNumber,db);

       /* Cursor c = db.query(Database.Trends.TABLE_NAME,null,Database.Trends.TRANSACTION_DATE+" BETWEEN ? AND ?"+new String[]{
                   startDate,endDate},null,null,null,null);*/
        //  })


        //Declaring the cursor to retrieve values from the Spending_Category and Spending_Pattern table

        // Cursor c = db.rawQuery(" SELECT t.TREND_DATE TREND_DATE, t.TREND_TIME TREND_TIME,t.BALANCE BALANCE,t.ACC_NR ACC_NR, at.ACC_TYPE_DESC ACC_TYPE_DESC FROM Trend t, AccType at WHERE t.ACC_TYPE_ID = at.ACC_TYPE_ID AND TREND_DATE ='"+startDate+"' AND TREND_DATE='"+endDate+"'",null);


      /*  String strQuery1=" SELECT t.TREND_DATE TREND_DATE, t.TREND_TIME TREND_TIME,t.BALANCE BALANCE,t.ACC_NR ACC_NR, at.ACC_TYPE_DESC ACC_TYPE_DESC";
        String strQuery2=" FROM Trend t, AccType at";
        String strQuery3=" WHERE t.ACC_TYPE_ID = at.ACC_TYPE_ID";
        String strQuery4=" AND TREND_DATE<='";*/

      /*  String strQuery1=" SELECT t.TREND_DATE TREND_DATE, t.TREND_TIME TREND_TIME,t.BALANCE BALANCE,t.ACC_NR ACC_NR, at.ACC_TYPE_DESC ACC_TYPE_DESC";
        String strQuery2=" FROM Trend t, AccType at";
        String strQuery3=" WHERE t.ACC_TYPE_ID = at.ACC_TYPE_ID";
        String strQuery4=" AND TREND_DATE>='";




        Cursor c = db.rawQuery(strQuery1+
                strQuery2+
                strQuery3
                +strQuery4+startDate+"' AND TREND_DATE<='"+endDate+"'",null);


        arrlstViewStatement.add("Date                                                 Balance");
        while(c.moveToNext()){


            String trendDate = c.getString(0);
            String trendTime = c.getString(1);
            String balance = c.getString(2);
            String accNr = c.getString(3);
            String AccType = c.getString(4);

            arrlstViewStatement.add(trendDate+"     "+trendTime+"        "+ balance);


            double Balance = 250.0;
            double vat =Balance*1.14+Balance;
            String strVAT = Double.toString(vat);
            trend = new Trend(trendDate,trendTime,balance,accNr,strVAT);

        }*/



        adapterViewStatement = new ArrayAdapter<String>(ViewStatement.this,
                android.R.layout.simple_list_item_1,
                arrlstViewStatement);
        lsViewStatement.setAdapter(adapterViewStatement);
    }

    public void listViewEvent(){
        lsViewStatement.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            String Monthly[] ={trend.getTrendDate()+"     "+trend.getBalance()+"      "+trend.getAccountNumber()};

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                String s = arrlstViewStatement.get(position);
                boolean[] checked={false};
                MenuItem menuItem;


                AlertDialog.Builder builder4 = new AlertDialog.Builder(ViewStatement.this);
                builder4.setTitle("Financial Advise");
                builder4.setCancelable(false);
               // builder4.setMultiChoiceItems(position, checked,onMenuItemSelected(position,menuItem));
              //  builder4.s


                builder4.setItems(Monthly, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for (int i = 0; i < 5; i++) {
                            Toast.makeText(getApplicationContext(), Monthly[which], Toast.LENGTH_SHORT).show();
                        }

                    }


                });

                AlertDialog alertDialog4 = builder4.create();
                alertDialog4.show();

            }
        });
    }


    public void saveDocument(){


        PdfDocument document = new PdfDocument();

        View content = findViewById(R.id.lsShowStatement);

        int pageNumber =1;

        PdfDocument.PageInfo pageInfo = new Builder(content.getWidth(),
                content.getHeight() - 20,pageNumber).create();

        PdfDocument.Page page = document.startPage(pageInfo);
        content.draw(page.getCanvas());
        document.finishPage(page);

        SimpleDateFormat sdf = new SimpleDateFormat(" ddMMyyyyhhmmss");
        String pdfName = "pdfdemo"
                +sdf.format(Calendar.getInstance().getTime())+".pdf";

        File outputFile = new File("/sdcard/",pdfName);

        //File outputFile = new File()

        try{

            outputFile.createNewFile();
            OutputStream out = new FileOutputStream(outputFile);
            document.writeTo(out);
            document.close();
            out.close();

        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public void displyPdf(String filePath){

       /* File file = new File(filePath);

        ParcelFileDescriptor mFileDescriptor = null;

        try{
          mFileDescriptor = ParcelFileDescriptor.open(file,
                  ParcelFileDescriptor.MODE_READ_ONLY);

        }
        catch(FileNotFoundException e){
        e.printStackTrace();
        }

        PdfRenderer mPdfRenderer = null;

        try{
            mPdfRenderer = new PdfRenderer(mFileDescriptor);

        }
        catch(IOException e){

        }
        int index=0;

        PdfRenderer.Page mCurrentPage = mPdfRenderer.openPage(index);

        Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(),
                mCurrentPage.getHeight(), Bitmap.Config.ARGB_8888);

        mCurrentPage.render(bitmap,null,null,
                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        //pdfView.setImageBitmap(bitmap);

        mCurrentPage.close();
        mPdfRenderer.close();

        try{
         mFileDescriptor.close();
        }
        catch(IOException e){
         e.printStackTrace();
        }*/

       /* PdfDocument document = new PdfDocument();
        document.se*/
        String pdfname="doc1";
        try{
            File myFile1 = new File("/storage/1B01-0B0C/savedBankingStatements/"+pdfname+".pdf");
            OutputStream outFile = new OutputStream() {
                @Override
                public void write(int oneByte) throws IOException {

                }
            };

            myFile1.createNewFile();

       PdfDocument pdfDoc = new PdfDocument();
       pdfDoc.writeTo(outFile);

        Document doc;




        }
         catch (IOException e) {
            e.printStackTrace();
        }

        //Document pdfDocumnet = new Document();



       // PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(new Rect(0,0,100,100),1).create();
        /*PdfDocument.PageInfo = new PdfDocument().PageInfo(null) ;

        PdfDocument.Page page = document.startPage(pageInfo);*/



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


    public void setNavigationHeaderStatement(){

        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_view_statement,null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.tvNavStatement);
        Context context=null;

        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String cardNumber = pref.getString("CardNr", null);
        String Name=pref.getString("Name",null);
        String Surname=pref.getString("Surname", null);


        edit.commit();


        String text="Welcome "+Name+" "+Surname;

        user_name.setText(text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_statement, menu);
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
          //  return true;
            Intent newsFeed = new Intent(ViewStatement.this,NewsFeeds.class);
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
            Intent home = new Intent(ViewStatement.this,Home.class);
            startActivity(home);

        } else if (id == R.id.Profile) {
            Intent profile = new Intent(ViewStatement.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(ViewStatement.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.CashTransfer) {
            Intent cashTransfer = new Intent(ViewStatement.this,CashTransfer.class);
            startActivity(cashTransfer);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent socialMedia = new Intent(ViewStatement.this,SocialMedia.class);
            startActivity(socialMedia);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
