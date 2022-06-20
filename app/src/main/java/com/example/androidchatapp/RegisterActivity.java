package com.example.androidchatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.MemoryFile;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    //MaterialEditText username, pass , confpass;
    //  Button reg;
    FirebaseAuth auth;
    DatabaseReference ref=FirebaseDatabase.getInstance().getReferenceFromUrl("https://androidchatapp-f44d4-default-rtdb.firebaseio.com");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       /* ImageButton hyper = findViewById(R.id.login_link);
        hyper.setOnClickListener(v -> {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        });*/
        final EditText username = findViewById(R.id.username);
        final EditText pass = findViewById(R.id.password);
        final EditText confpass = findViewById(R.id.confirm_password);
        final AppCompatButton reg = findViewById(R.id.btn_register);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_register:
                        String txt_username = username.getText().toString();
                        String txt_pass = pass.getText().toString();
                        String txt_conf = confpass.getText().toString();

                        if (txt_username.isEmpty()) {
                            username.setError("Username is Required");
                            username.requestFocus();
                            return;
                        }
                        if (txt_pass.isEmpty()) {
                            pass.setError("Password is Required");
                            pass.requestFocus();
                            return;
                        }
                        if (txt_pass.length() < 6) {
                            pass.setError("Password should be at least 6 characters");
                            pass.requestFocus();
                            return;
                        }
                        if (txt_conf.isEmpty()) {
                            confpass.setError("Please confirm password");
                            confpass.requestFocus();
                            return;
                        }else {
                            // ref=FirebaseDatabase.getInstance().getReferenceFromUrl("https://finalapp-145f1-default-rtdb.firebaseio.com/");
                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.child("users").hasChild(txt_username)) {
                                        Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                    } else {
                                        ref.child("users").child(txt_username).child("pass").setValue(txt_pass);

                                        //save username to memory
                                        Memory.saveData(txt_username, RegisterActivity.this);

                                        Toast.makeText(RegisterActivity.this, "user has been registered successfully", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(RegisterActivity.this, ChatActivity.class);
                                        intent.putExtra("username", txt_username);
                                        intent.putExtra("pass", txt_pass);
                                        startActivity(intent);
                                        finish();

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            break;
                        }
                }
            }
        });

        auth = FirebaseAuth.getInstance();

        if (!Memory.getData(this).isEmpty()) {
            Intent intent = new Intent(RegisterActivity.this, ChatActivity.class);
            intent.putExtra("username", Memory.getData(this));
            intent.putExtra("pass", "");
            startActivity(intent);
            finish();

        }

    }
}