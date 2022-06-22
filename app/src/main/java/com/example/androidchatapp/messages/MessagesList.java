package com.example.androidchatapp.messages;

public class MessagesList {

    private String user , nick, lastMessage ;

    private int dateOfMessage;

    public MessagesList(String user, String nick){
        this.user = user;
        this.lastMessage = lastMessage;
        this.dateOfMessage = dateOfMessage;
        this.nick = nick;
    }

    public String getUser() {
        return user;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getNickname() {
        return nick;
    }

    public int getDateOfMessage() {
        return dateOfMessage;
    }
}
