package com.example.androidchatapp.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidchatapp.Memory;
import com.example.androidchatapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chat extends AppCompatActivity {
    DatabaseReference ref= FirebaseDatabase.getInstance().getReferenceFromUrl("https://androidchatapp-f44d4-default-rtdb.firebaseio.com");
    private String chatKey;
    String getUsername = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView nameTV = findViewById(R.id.nickname);
        final EditText messageEditText = findViewById(R.id.messageEditTxt);
        final ImageView sendBtn = findViewById(R.id.sendBtn);

        //get data from messages adapter
       // final String getNames = getIntent().getStringExtra("nickname");
        final String getNickName = getIntent().getStringExtra("nickname");
        final String getUser = getIntent().getStringExtra("users");

        chatKey = getIntent().getStringExtra("chat_key");

        if(chatKey.isEmpty()){
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //generate chat key. default is 1
                    chatKey = "1";
                    if(snapshot.hasChild("chat")){
                        chatKey = String.valueOf(snapshot.child("chat").getChildrenCount() + 1);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        //final String profilepic = getIntent().getStringExtra("pr")

        //get user name from memory
        getUsername = Memory.getData(Chat.this);
        nameTV.setText(getNickName);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getTxtMsg = messageEditText.getText().toString();

                //timestamp
                final String currentT = String.valueOf(System.currentTimeMillis()).substring(0,10);
                Memory.saveLastMsg(currentT,chatKey, Chat.this);
                ref.child("chat").child(chatKey).child("users_1").setValue(getUsername);
                ref.child("chat").child(chatKey).child("users_2").setValue(getNickName);
                ref.child("chat").child(chatKey).child("messages").child(currentT).child("msg").setValue(getTxtMsg);
                ref.child("chat").child(chatKey).child("messages").child(currentT).child("nickname").setValue(getUsername);

            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}