package com.example.abvn237.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABVN237 on 3/19/2016.
 */

public class CustomAdapter extends ArrayAdapter<String> {


    Context c;
   // Account account;
    String[] Chats={};
    String[] Username={};
    int[] Images={};
    LayoutInflater inflater;
    List<String> galleryList;

    //List<RowItem> rowItems;




    public CustomAdapter(Context context,List<String> gallery) {
        super(context,R.layout.activity_rowmodel,gallery);
       //  super(context,R.layout.activity_rowmodel,chats,username,R.layout.activity_rowmodel);
        this.c = context;
      //  this.Chats = chats;
       // this.Images= images;
      //  this.Username= username;


    }

    public class ViewHolder{

        TextView chats;
        TextView tvUsername;
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null)
        {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(R.layout.content_rowmodel,null);


        }

        final ViewHolder holder = new ViewHolder();

        holder.img = (ImageView) convertView.findViewById(R.id.imgPicture);
        holder.chats = (TextView) convertView.findViewById(R.id.tvPicture);
        holder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);




        holder.img.setImageResource(Images[position]);
        holder.chats.setText(Chats[position]);
        holder.tvUsername.setText(Username[position]);



        return convertView;
    }
}

/*public void getItemList(List<String> dataList){


    String strQuery1=" SELECT Picture_Id, Picture ";
    String strQuery2=" FROM   Gallery g, Account a";
    String strQuery3=" WHERE  g.ACC_NR = a.ACC_NR ";




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






            arrlstchats2.add(Images.toString());
            arrlstchats2.toArray();

            customadapter = new CustomAdapter(this,strChats,strUsername,Images);
        arrlstchats2.add(Name+" "+Surname+"        "+ChatMessages);
        arrlstchats2.add(ChatDate);



    }


    return arrlstchats2;
}
}*/

    /*
    CustomAdapter(Context context,String []chats,){

        this.context = context;
        this.rowItems= rowItems;
    }

    @Override2
    public int getCount(){

        return rowItems.size();
    }

    @Override
    public Object getItem(int position){
        return rowItems.indexOf(getItem(position));
    }

    @Override
    public long getItemId(int position){
        return rowItems.indexOf(getItem(position));
    }


    private class ViewHolder{
        ImageView imgProfilePicture;
        TextView tvProfilePicture;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder holder=null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


        if(convertView == null){

            convertView  = mInflater.inflate(R.layout.content_rowmodel,null);
            holder = new ViewHolder();

            holder.tvProfilePicture= (TextView) convertView.findViewById(R.id.tvPicture);

            holder.imgProfilePicture = (ImageView) convertView.findViewById(R.id.imgPicture);

            RowItem row_pos = rowItems.get(position);

            holder.imgProfilePicture.setImageResource(row_pos.getProfile_pic_id());
            holder.tvProfilePicture.setText(row_pos.getName());


        }

        return null;
    }
}*/
/*


public class CustomAdapter extends ArrayAdapter<String> {


     Context c;

    String[] chats={};
    int[] Img={};
    LayoutInflater inflater;

    public CustomAdapter(MainActivity mainActivity,String[] Chats, int[] images) {
        super(mainActivity,Chats,images);


      chats = Chats;
        Img = images;

        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public class ViewHolder{

        ImageView imgPictures;
        TextView tvPicture;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.content_rowmodel,null);

        }

        final ViewHolder holder = new ViewHolder();

        holder.imgPictures= (ImageView) convertView.findViewById(R.id.imgPicture);
        holder.tvPicture = (TextView) convertView.findViewById(R.id.tvPicture);

        holder.imgPictures.setImageResource(Img[position]);
        holder.tvPicture.setText(Img[position]);



        return super.getView(position,convertView,parent);

    }


*/

  /*  public CustomAdapter(Context ctx,){

    }*/

   /* @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }*/

  /*  @Override
    public View getView(int position,View convertView,ViewGroup parent){
        if(convertView == null){
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_rowmodel,null);


        }

        ViewHolder holder = new ViewHolder();

        holder.nameTv = (TextView) convertView.findViewById(R.id.nameTv);
        holder.img = (ImageView) convertView.findViewById(R.id.imageView1);

        holder.nameTv.setText(players[position]);
        holder.img.setImageResource(images[position]);


        return convertView;
    }*/
//}
