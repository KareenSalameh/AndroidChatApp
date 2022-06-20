package com.example.androidchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    MaterialEditText user, pass;

    Button log;
//firebase
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        log = findViewById(R.id.btn_login);
        ImageButton hyper = findViewById(R.id.register_link);
        hyper.setOnClickListener(v -> {
            Intent i = new Intent(this,RegisterActivity.class);
            startActivity(i);
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_user = user.getText().toString();
                String txt_pass = pass.getText().toString();
                if (TextUtils.isEmpty(txt_user) || TextUtils.isEmpty(txt_pass)) {
                    Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(txt_user, txt_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this , "Authentication Failed!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
         });
    }
}