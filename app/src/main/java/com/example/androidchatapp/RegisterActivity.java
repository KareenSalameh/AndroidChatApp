package com.example.androidchatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, reg_user;
    private EditText user,pas,conf;
    MaterialEditText username, pass , confpass;
    Button reg;
    FirebaseAuth auth;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageButton hyper = findViewById(R.id.login_link);
        hyper.setOnClickListener(v -> {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        });
        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        confpass = findViewById(R.id.confirm_password);
        reg = findViewById(R.id.btn_register);

        auth = FirebaseAuth.getInstance();
        reg.setOnClickListener(this);

    }

    @Override

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                reg_user();
                break;
        }
           /* String txt_username = username.getText().toString();
            String txt_pass = pass.getText().toString();
            String txt_conf = confpass.getText().toString();

            if(TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_pass) || TextUtils.isEmpty(txt_conf)){
                Toast.makeText(RegisterActivity.this , "All fields are required", Toast.LENGTH_SHORT).show();
            } else if ( txt_pass.length() < 8){
                Toast.makeText(RegisterActivity.this , "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
            } else {
                register(txt_username,txt_pass, txt_conf);
            }*/
    }

    private void reg_user() {
        String txt_username = username.getText().toString();
        String txt_pass = pass.getText().toString();
        String txt_conf = confpass.getText().toString();

        if(txt_username.isEmpty()){
            username.setError("Username is Required");
            username.requestFocus();
            return;
        }
        if(txt_pass.isEmpty()) {
            pass.setError("Password is Required");
            pass.requestFocus();
            return;
        }
        if(txt_pass.length() < 6){
            pass.setError("Password should be at least 6 characters");
            pass.requestFocus();
            return;
        }
        if(txt_conf.isEmpty()) {
            confpass.setError("Please confirm password");
            confpass.requestFocus();
            return;
        }
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("users").hasChild(txt_username)){
                    Toast.makeText(RegisterActivity.this,"User already exists",Toast.LENGTH_SHORT).show();
                }
                else{
                    ref.child("users").child(txt_username).child("pass").setValue(txt_pass);
                    Toast.makeText(RegisterActivity.this,"user has been registerd successfully",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(RegisterActivity.this , ChatActivity.class);
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
/*
        auth.createUserWithEmailAndPassword(txt_username,txt_pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User u = new User(txt_username,txt_pass);

                            FirebaseDatabase.getInstance().getReference("Users2")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterActivity.this,"user has been registerd",Toast.LENGTH_LONG).show();
                                                setProgressBarVisibility(false);
                                            }else{
                                                Toast.makeText(RegisterActivity.this,"Failed to Register, Try Again!",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                        } else {
                            Toast.makeText(RegisterActivity.this,"Failed to Register, Try Again!",Toast.LENGTH_LONG).show();

                        }
                    }
                });*/

    }


    private void register(String username , String pass, String confirm){
        setProgressBarVisibility(true); // not sure..

        auth.createUserWithEmailAndPassword(username,pass)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        //
                        User user = new User(username,pass);
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this,"user has been registerd",Toast.LENGTH_LONG).show();
                                            setProgressBarVisibility(false);
                                        }else{
                                            Toast.makeText(RegisterActivity.this,"faileD!",Toast.LENGTH_LONG).show();

                                        }
                                    }
                                });
    //                    FirebaseUser firebaseUser = auth.getCurrentUser();
    //                    String userid =firebaseUser.getUid();
    //
    //                    ref = FirebaseDatabase.getInstance().getReference("Users").child(userid);
    //
    //                    HashMap<String,String> hashMap = new HashMap<>();
    //                    hashMap.put("id", userid);
    //                    hashMap.put("username", username);
    //                    hashMap.put("imageURL", "default");
    //
    //                    ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
    //                        @Override
    //                        public void onComplete(@NonNull Task<Void> task) {
    //                            if(task.isSuccessful()){
    //                                Intent intent = new Intent(RegisterActivity.this , ChatActivity.class);
    //                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    //                                startActivity(intent);
    //                                finish();
    //                            }
    //                        }
    //                    });
                    } else {
                        Toast.makeText(RegisterActivity.this, "You Can't register!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}