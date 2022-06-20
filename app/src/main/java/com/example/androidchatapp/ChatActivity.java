package com.example.androidchatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {
    private String user;
    private String pass;
    private RecyclerView messagesrv;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://finalapp-145f1-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messagesrv = findViewById(R.id.messagesRecyclerView);
        user = getIntent().getStringExtra("username");
        pass = getIntent().getStringExtra("password");

        messagesrv.setHasFixedSize(true);
        messagesrv.setLayoutManager(new LinearLayoutManager(this));

        //get profile pic from database firebase // later
        ////////////////////////////////////////



    }
}