package com.example.booksfirebase;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1,b2;
    EditText e1,e2;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},123);
            return;
        }
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        e1=findViewById(R.id.email1);
        e2=findViewById(R.id.password1);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        auth=FirebaseAuth.getInstance();
        //FirebaseUser currentUser = auth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                login();
                break;

            case R.id.button2:
                register();
                break;
        }
    }
    void login(){
        String s1=e1.getText().toString().trim();
        String s2=e2.getText().toString().trim();

        auth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful()){
                Toast.makeText(MainActivity.this, "Valid User", Toast.LENGTH_SHORT).show();
                Intent it=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(it);
                }else{
                    Toast.makeText(MainActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void register(){
        String emailid=e1.getText().toString().trim();
        String password=e2.getText().toString().trim();

        Task<AuthResult> task;
        task=auth.createUserWithEmailAndPassword(emailid,password);
        task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task1) {
                if(task1.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Registeration Complete", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Registeration Not Complete", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}