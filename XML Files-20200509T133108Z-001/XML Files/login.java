package com.example.chat_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
EditText eemail,epassword;
Button button;
TextView textView;
ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eemail=findViewById(R.id.email);
        epassword=findViewById(R.id.password);
        button=findViewById(R.id.login);
        textView=findViewById(R.id.text);
        progressBar=findViewById(R.id.progress);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=eemail.getText().toString().trim();
                String password=epassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    eemail.setText("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    epassword.setText("Password is Required");
                    return;
                }
                if(password.length() <6)
                {
                    epassword.setText("Password Must be >=6 charater");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"Login Sucessfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                        }
                       else
                        {
                            Toast.makeText(LoginActivity.this,"Error occurs",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }
}
