package com.example.androidchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.androidchatapp.messages.MessagesAdapter;
import com.example.androidchatapp.messages.MessagesList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private final List<MessagesList> messagesLists = new ArrayList<>();
    private String user;
    private String pass;
    private String nickname;
    private boolean dataSet = false;
    private RecyclerView messagesrv;
    private MessagesAdapter messagesAdapter;
    private String lastMessage = "";
    private String chatKey = "";
    DatabaseReference ref=FirebaseDatabase.getInstance().getReferenceFromUrl("https://androidchatapp-f44d4-default-rtdb.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messagesrv = findViewById(R.id.messagesRecyclerView);

        user = getIntent().getStringExtra("users");
        nickname = getIntent().getStringExtra("nickname");
        pass = getIntent().getStringExtra("password");

        messagesrv.setHasFixedSize(true);
        messagesrv.setLayoutManager(new LinearLayoutManager(this));
        //set adapter to recycle view
        messagesAdapter = new MessagesAdapter(messagesLists, ChatActivity.this);
        messagesrv.setAdapter(messagesAdapter);


        //get profile pic from database firebase // later
        ////////////////////////////////////////


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesLists.clear();
                lastMessage = "";
                chatKey = "";

                for(DataSnapshot dataSnapshot : snapshot.child("users").getChildren()){

                    final String getname = dataSnapshot.getKey();

                    dataSet = false;
                    if(!getname.equals(user)){

                        final String getNickname =  dataSnapshot.child("nickname").getValue(String.class);
                        MessagesList messagesList = new MessagesList(getname,getNickname," ",001122, chatKey);
                        messagesLists.add(messagesList);
                        String Lastmessage ="";
                        ref.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int getChatCounts = (int)snapshot.getChildrenCount();
                                if(getChatCounts < 0 ){
                                    for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                                        final String getKey = dataSnapshot1.getKey();
                                        chatKey = getKey;

                                        if((dataSnapshot1.hasChild("user_1") && dataSnapshot1.hasChild("user_2") && dataSnapshot1.hasChild("messages"))){

                                            final String getUserone = dataSnapshot1.child("user_1").getValue(String.class);
                                            final String getUsertwo = dataSnapshot1.child("user_2").getValue(String.class);

                                            if(((getUserone.equals(getname) && getUsertwo.equals(user)) ) || ((getUserone.equals(user) && getUsertwo.equals(getname)))){
                                                for(DataSnapshot chatdataSnapshot : dataSnapshot1.child("messages").getChildren()) {

                                                    final long getMsgkey = Long.parseLong(chatdataSnapshot.getKey());
                                                    final long getLastSeen = Long.parseLong(Memory.getLastMsg(ChatActivity.this, getKey));

                                                    lastMessage = chatdataSnapshot.child("msg").getValue(String.class);
                                                }

                                            }

                                        }
                                    }
                                }
                                if(!dataSet){
                                    dataSet = true;
                                    MessagesList messagesList = new MessagesList(getname,getNickname,Lastmessage,001122, chatKey);
                                    messagesLists.add(messagesList);
                                    // messagesrv.setAdapter(new MessagesAdapter(messagesLists, ChatActivity.this));
                                    messagesAdapter.updateData(messagesLists);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}