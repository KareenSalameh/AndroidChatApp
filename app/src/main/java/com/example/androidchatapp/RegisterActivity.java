package com.example.androidchatapp;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

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
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        });
        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        confpass = findViewById(R.id.confirm_password);
        reg = findViewById(R.id.btn_register);

        auth = FirebaseAuth.getInstance();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = username.getText().toString();
                String txt_pass = pass.getText().toString();
                String txt_conf = confpass.getText().toString();

                if(TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_pass) || TextUtils.isEmpty(txt_conf)){
                    Toast.makeText(RegisterActivity.this , "All fields are required", Toast.LENGTH_SHORT).show();
                } else if ( txt_pass.length() < 8){
                    Toast.makeText(RegisterActivity.this , "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
                } else {
                    register(txt_username,txt_pass, txt_conf);
                }
            }
        });
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