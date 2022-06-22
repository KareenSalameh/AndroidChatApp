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
    private RecyclerView messagesrv;
    DatabaseReference ref=FirebaseDatabase.getInstance().getReferenceFromUrl("https://androidchatapp-f44d4-default-rtdb.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messagesrv = findViewById(R.id.messagesRecyclerView);

        user = getIntent().getStringExtra("name");
        nickname = getIntent().getStringExtra("nickname");
        pass = getIntent().getStringExtra("password");

        messagesrv.setHasFixedSize(true);
        messagesrv.setLayoutManager(new LinearLayoutManager(this));

        //get profile pic from database firebase // later
        ////////////////////////////////////////
       /* ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesLists.clear();

                for(DataSnapshot dataSnapshot : snapshot.child("users").getChildren()){

                    final String getNames = dataSnapshot.getKey();

                    if(!getNames.equals(user)){

                        final String getNickname =  dataSnapshot.child("nickname").getValue(String.class);

                        MessagesList messagesList = new MessagesList(getNames,getNickname);
                        messagesLists.add(messagesList);
                    }
                }

                messagesrv.setAdapter(new MessagesAdapter(messagesLists, ChatActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}