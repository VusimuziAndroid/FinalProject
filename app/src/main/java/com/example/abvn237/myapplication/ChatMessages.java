package com.example.abvn237.myapplication;

/**
 * Created by Vusi Ngwenya on 4/6/2016.
 */
public class ChatMessages {

    private String chatId;
    private String chatMessage;
    private String chatDate;
    private String userame;

    public ChatMessages(String chatmessage, String chatdate){

    //    this.Chat_Id=ChatId=ChatId;
        this.chatMessage=chatmessage;
        this.chatDate=chatdate;
      //  this.Username =userName;

    }

   /* public String getChatId(){

        return chatId;
    }*/

    public String getChatMessage(){

        return this.chatMessage;
    }

    public String getChatDate(){

        return this.chatDate;
    }

   /* public String getUsername(){

        return this.Username;
    }*/


}
