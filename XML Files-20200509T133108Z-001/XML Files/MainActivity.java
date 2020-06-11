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

public class MainActivity extends AppCompatActivity {

    EditText editTextf,editTexte,editTextp,editTextph;
    TextView textView;
    Button button;
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextf=findViewById(R.id.fname);
        editTexte=findViewById(R.id.email);
        editTextp=findViewById(R.id.password);
        editTextph=findViewById(R.id.phno);
        button=findViewById(R.id.register);
        textView=findViewById(R.id.text);
        progressBar=findViewById(R.id.progress);

        auth= FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email=editTexte.getText().toString().trim();
                String password=editTextp.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    editTexte.setText("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    editTextp.setText("Password is Required");
                    return;
                }
                if(password.length() <6)
                {
                    editTextp.setText("Password Must be >=6 charater");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                if(auth.getCurrentUser() != null)
                {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                            }
                            else {
                                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                            }
                        }

                });
            }
        });
    }
}
