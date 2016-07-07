package com.example.abvn237.myapplication;

import android.app.AlertDialog;
import android.app.ListActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

//import java.net.HttpCookie;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SocialMedia extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
   // private ListAdapter adapter;
    //ListView lsSocialMedia;
    DatabaseHelper myDb;
    SQLiteDatabase db;
    EditText etMessage;
    String[] strChats= {"Hi, can u help me","What's the issue","my banking app credentials","What's the problem with them","I entered them and was not logged in","message6","message7","message8","message9"};
    String[] strUsername= {"Busi","Nhlanhla","Busi","Nhlanhla","Busi","Nhlanhla","Busi","Nhlanhla","Busi"};
    int[] Images = {R.drawable.pic1,R.drawable.pic6,R.drawable.pic1,R.drawable.pic6,R.drawable.pic1,R.drawable.pic6,R.drawable.pic1,R.drawable.pic6,R.drawable.pic1};

    TextView user_name2,user_email2;
    ImageView imgUpload2,user_picture2;
    NavigationView navigation_view2;

    ArrayAdapter<String> adapterChats2;
    ArrayList<String> arrlstchats2= new ArrayList<String>();
    ListView lsSocialMedia2;
    Context context=null;


    Trend trend;


   /* String[] names= {
            "picture 1",
            "picture 2"
    };

    int[] images={
        R.drawable.pic1,
                R.drawable.pic2
    };*/

    DatabaseHelper dbhelper;
  /*  ArrayAdapter<String> adapterSocialMedia;
    ArrayList<String> arrListSocialMedia= new ArrayList<String>();*/
    Button bSend;
    AccType accType;

    CustomAdapter customadapter;


   /* SharedPreferences accessValue1 = this.getSharedPreferences("MyPrefs",MODE_PRIVATE);
    SharedPreferences.Editor e = accessValue1.edit();*/
   /* List<RowItem> rowItems;
    ListView myListView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
       // setContentView(R.layout.activity_rowmodel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        setNavigationHeaderSM();

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

        etMessage = (EditText) findViewById((R.id.etMessage));
        bSend = (Button) findViewById(R.id.bSend);







       // lsSocialMedia = (ListView) findViewById(R.id.lsSocialMedia2);

        getMessages();

       // Cursor c = db.rawQuery(" SELECT Chat_Id,Chat_Date,Chat_Message,Username")




      /*  Cursor c = db.rawQuery(" SELECT CH_ID, CH_MESSAGE, CH_DATE,USERNAME FROM Chat_Messages",null);
int position;


        chats.add("Transaction Date        Balance   Available Amount");
        while(c.moveToNext()){


            String ChId = c.getString(0);
            String ChMessage= c.getString(1);
            String ChDate = c.getString(2);
            String Username=c.getString(3);



            String[] ChMsg={ChMessage};
            String[] ChUsername={Username};





        }*/

       /* adapterChats = new ArrayAdapter<String>(ViewStatement.this,
                android.R.layout.simple_list_item_1,
                chats);*/
      //  myView.setAdapter(adapter);

       /* customadapter = new CustomAdapter(this,strChats,strUsername,Images);
        lsSocialMedia.setAdapter(customadapter);*/

     //   CustomAdapter customadapter = new CustomAdapter(this,strChats,strUsername,Images);



      /*  lsSocialMedia.setOnItemClickListener(new OnItemClickListener(){


            @Override

            public void onItemClick(AdapterView<?> parent, View v,int pos,long id){

                Toast.makeText(getApplicationContext(),strChats[pos],Toast.LENGTH_SHORT).show();
            }

        });*/

      //  strChats = {"message1","message2","message3","message4","message5","message6"};
      //  db=dbhelper.getReadableDatabase();
      //  ViewMessage();



       /* rowItems = new ArrayList<RowItem>();
        names = getResources().getStringArray(R.array.users);
        images = getResources().getIntArray(R.array.profile_pics);


        for(int i =0 ; i < names.length;i++){

            RowItem item = new RowItem(names[i],images[i]);
            rowItems.add(item);

        }

        myListView = (ListView) findViewById(R.id.lsSocialMedia);
        CustomAdapter adapter = new CustomAdapter(this,rowItems);
        myListView.setAdapter(adapter);*/

       // myListView.setOnItemClickListener(this);





        //create new adapter and set its values
       /* CustomAdapter adapter = new CustomAdapter(this,players,images);
        setListAdapter(adapter);*/


      //  myList = (ListView) findViewById(R.id.lsSMedia);
      //  imgid = (ImageView) findViewById(R.id.imageView);

       // Adapter adapter;

       // adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, imgid);
    //    myList.setAdapter((ListAdapter) adapter);
    }

    public void getMessages(){

        //opening or creating the database before retreiving values from the sqlite database

        db=openOrCreateDatabase("BusinessBankingApp33.sql",MODE_PRIVATE,null);

        lsSocialMedia2 = (ListView) findViewById(R.id.lsSocialMedia2);


        String startDate = "01 March 2015";
        String endDate = "25 March 2015";

       // String strQuery1=" SELECT Chat_Id ,Chat_Date,Chat_Message,Username ";
       /* String strQuery1=" SELECT ch.Chat_Id Chat_Id, ch.Chat_Date Chat_Date,ch.Chat_Message Chat_Message, ch.USER_ID USER_ID,ah.ACC_HOLDER_ID ACC_HOLDER_ID,ah.ACC_HOLDER_NAME ACC_HOLDER_NAME,ah.ACC_HOLDER_SURNAME ACC_HOLDER_SURNAME ";
        String strQuery2=" FROM Chat_Messages ch,Account_Holder ah ";
        String strQuery3=" WHERE ch.ACC_HOLDER_ID=ah.ACC_HOLDER_ID ";




        Cursor c = db.rawQuery(strQuery1+
                               strQuery2+
                               strQuery3, null);


       while(c.moveToNext()){

            String ChatId = c.getString(0);
            String ChatDate = c.getString(1);
            String ChatMessages = c.getString(2);
            String UserId = c.getString(3);
            String AccHolderId = c.getString(4);
            String Name = c.getString(5);
            String Surname = c.getString(6);


            arrlstchats2.add(Name+" "+Surname+"         "+ChatMessages);
            arrlstchats2.add(ChatDate);



        }*/

      //  myDb.getMessages(arrlstchats2);
       // DisplayChatMessages();
        Datasource datasource = new Datasource();

        datasource.DisplayChatMessages(arrlstchats2,db);

        arrlstchats2 = datasource.DisplayChatMessages(arrlstchats2,db);
        adapterChats2 = new ArrayAdapter<String>(SocialMedia.this,
                android.R.layout.simple_list_item_1,
                arrlstchats2);


        lsSocialMedia2.setAdapter(adapterChats2);


     /*   lsSocialMedia.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            String Monthly[] ={trend.getTrendDate()+"     "+trend.getBalance()+"      "+trend.getAccountNumber()};

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                String s =   arrlstchats.get(position);



                AlertDialog.Builder builder4 = new AlertDialog.Builder(SocialMedia.this);
                builder4.setTitle("Financial Advise");
                builder4.setCancelable(false);

                builder4.setItems(Monthly, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for(int i=0;i<5;i++) {
                            Toast.makeText(getApplicationContext(), Monthly[which], Toast.LENGTH_SHORT).show();
                        }

                    }


                });

                AlertDialog alertDialog4 = builder4.create();
                alertDialog4.show();

            }
        });*/
    }

    public void DisplayChatMessages(){
        String strQuery1=" SELECT ch.Chat_Id Chat_Id, ch.Chat_Date Chat_Date,ch.Chat_Message Chat_Message, ch.USER_ID USER_ID,ah.ACC_HOLDER_ID ACC_HOLDER_ID,ah.ACC_HOLDER_NAME ACC_HOLDER_NAME,ah.ACC_HOLDER_SURNAME ACC_HOLDER_SURNAME ";
        String strQuery2=" FROM Chat_Messages ch,Account_Holder ah ";
        String strQuery3=" WHERE ch.ACC_HOLDER_ID=ah.ACC_HOLDER_ID ";




        Cursor c = db.rawQuery(strQuery1+
                strQuery2+
                strQuery3, null);


        while(c.moveToNext()){

            String ChatId = c.getString(0);
            String ChatDate = c.getString(1);
            String ChatMessages = c.getString(2);
            String UserId = c.getString(3);
            String AccHolderId = c.getString(4);
            String Name = c.getString(5);
            String Surname = c.getString(6);


           // String img = Integer.parseInt(images[]);






           /* arrlstchats2.add(Images.toString());
            arrlstchats2.toArray();

            customadapter = new CustomAdapter(this,strChats,strUsername,Images);*/
            arrlstchats2.add(Name+" "+Surname+"        "+ChatMessages);
            arrlstchats2.add(ChatDate);



        }
    }

    public void onClickSocialMedia(View view){


        String message = etMessage.getText().toString();
        addMessage(message);

      /* switch(view.getId()){
           case R.id.bSend:

          String message = etMessage.getText().toString();
          addMessage(message);

       }*/
      /*  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        String strDate = dateFormat.format(date);


        String message = etMessage.getText().toString();

        Chat_Messages Messages = new Chat_Messages(message,strDate);


        SharedPreferences pref = getSharedPreferences("MyPrefs",context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        edit.putString("Message", Messages.getChatMessage());
        edit.putString("ChatDate",Messages.getChatDate());*/

      //  myDb.addNewChat(Messages,db);


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

   /* @Override
    protected void onListItemClick(ListView l,View v,int position,long id){

        Toast.makeText(this,l.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT ).show();
    }*/
    public void addMessage(String ChatMessage){

        db = openOrCreateDatabase("BusinessBankingApp34.sql",
                MODE_PRIVATE,null);

        myDb = new DatabaseHelper(this);

        long date5 = System.currentTimeMillis();
        SimpleDateFormat sdf10 = new SimpleDateFormat("MMM MM dd,yyyy h:mm a");
        String dateTime5 = sdf10.format(date5);


        myDb.addNewChat(dateTime5,ChatMessage,db);

        Toast.makeText(this,"Message successfully sent",Toast.LENGTH_SHORT ).show();




       // Chat_Messages chatMessages = new Chat_Messages();

      //  chatMessages.setChatMessage(ChatMessage, dateTime5,accessValue1.getString("AccNr",null));



      //  myDb.addNewChat(chatMessages.getChatMessage(),chatMessages.getChatDate(),chatMessages.getUsername());


    }
    public void ViewMessage(){

        db=openOrCreateDatabase("BusinessBankingApp29.sql",MODE_PRIVATE,null);

        Cursor c = db.rawQuery(" SELECT CH_ID,CH_MESSAGE, CH_DATE, USERNAME FROM Chat_Messages ",null);


        while(c.moveToNext()){


            String ChatId=c.getString(0);
            String Message = c.getString(1);
            String Date = c.getString(2);
            String Username = c.getString(3);

            Toast.makeText(getApplicationContext(), " Successfully registered !!!",
                    Toast.LENGTH_SHORT).show();




            arrlstchats2.add(Username+"     "+Message);
       //     arrListSocialMedia.add(Date);


        }

        adapterChats2 = new ArrayAdapter<String>(SocialMedia.this,
                android.R.layout.simple_list_item_1,
                arrlstchats2);


        lsSocialMedia2.setAdapter((ListAdapter) arrlstchats2);

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
        getMenuInflater().inflate(R.menu.social_media, menu);
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
            Intent newsFeed = new Intent(SocialMedia.this,NewsFeeds.class);
            startActivity(newsFeed);
        }

        return super.onOptionsItemSelected(item);
    }


    public void setNavigationHeaderSM(){

        navigation_view2 = (NavigationView) findViewById(R.id.nav_view);

        View header2 = LayoutInflater.from(this).inflate(R.layout.nav_header_social_media,null);
        navigation_view2.addHeaderView(header2);


        /* LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        String path = Environment.getExternalStorageDirectory()+ "storage/1B01-0B0C/pic1.png"; */

        //RelativeLayout r1 = (RelativeLayout) findViewById(R.)



        user_name2 = (TextView) header2.findViewById(R.id.tvUName2);
        Context context=null;

     //   getPicture();

        SharedPreferences pref = getSharedPreferences("MyPref", context.MODE_PRIVATE);

        SharedPreferences.Editor edit = pref.edit();

        String accNumber = pref.getString("AccNr", null);
        String cardNumber = pref.getString("CardNr", null);
        String Name=pref.getString("Name",null);
        String Surname=pref.getString("Surname", null);


        edit.commit();

       // image_view.setImageBitmap(BitmapFactory.decodeFile(path));



       // getPicture();
        Toast.makeText(this,Name+" "+Surname,Toast.LENGTH_SHORT ).show();

        String text2="Welcome "+Name+" "+Surname;

        user_name2.setText(text2);

    }

    public void getPicture(){

        ImageView image_view;

        image_view = (ImageView) findViewById(R.id.imgSocialMediaNav);

        image_view.setImageURI(Uri.parse("file://storage/1B01-0B0C/pic15.png"));
       // image_view.setImageResource(R.drawable.pic11);


        /*String strQuery = " SELECT Picture FROM Gallery ";

        Cursor c = db.rawQuery(strQuery,null);
        byte b[] = c.getBlob(1);

        Bitmap bp = BitmapFactory.decodeByteArray(b,0,b.length);

        image_view.setImageBitmap(bp);*/

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            // Handle the camera action
            Intent home = new Intent(SocialMedia.this,Home.class);
            startActivity(home);

        } else if (id == R.id.Profile) {
            Intent profile = new Intent(SocialMedia.this,Profile.class);
            startActivity(profile);

        } else if (id == R.id.Expenditures) {
            Intent expenditures = new Intent(SocialMedia.this,Expenditures.class);
            startActivity(expenditures);

        } else if (id == R.id.BankingStatement) {
            Intent bankingStatement = new Intent(SocialMedia.this,BankingStatement.class);
            startActivity(bankingStatement);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent socialMedia = new Intent(SocialMedia.this,SocialMedia.class);
            startActivity(socialMedia);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
