package com.example.androidchatapp.messages;

public class MessagesList {

    private String user , nick, lastMessage , chatKey;

    private int dateOfMessage;

    public MessagesList(String user, String nick, String lastMessage, int dateOfMessage, String chatKey){
        this.user = user;
        this.lastMessage = lastMessage;
        this.dateOfMessage = dateOfMessage;
        this.nick = nick;
        this.chatKey = chatKey;
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

    public String getChatKey() {
        return chatKey;
    }
}
