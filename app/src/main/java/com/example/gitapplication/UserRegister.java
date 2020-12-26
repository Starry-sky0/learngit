package com.example.gitapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gitapplication.db.User;

import org.litepal.tablemanager.Connector;

public class UserRegister extends AppCompatActivity {
    private Button bt5;
    private Button bt6;
    private Button bt7;
    private EditText ed3;
    private EditText ed4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bt5=findViewById(R.id.btn_5);
        bt6=findViewById(R.id.btn_6);
        bt7=findViewById(R.id.btn_7);
        ed3=findViewById(R.id.ed3);
        ed4=findViewById(R.id.ed4);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=ed3.getText().toString();
                String pwd=ed4.getText().toString();
                User user=new User();
                user.setUsername(name);
                user.setPassword(pwd);
                user.save();
                Toast.makeText(UserRegister.this,"注册成功",Toast.LENGTH_SHORT).show();;
                System.out.println(user.toString());
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connector.getDatabase();
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserRegister.this,UserLogin.class);
                startActivity(intent);
            }
        });


    }
}