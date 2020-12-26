package com.example.gitapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gitapplication.db.User;

import org.litepal.LitePal;

import java.util.List;

public class UserLogin extends AppCompatActivity {
    private Button bt1;
    private Button bt2;
   private EditText ed1;
   private EditText ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        bt2=findViewById(R.id.btn_2);
        bt1=findViewById(R.id.btn_1);
        ed1=findViewById(R.id.ed_1);
        ed2=findViewById(R.id.ed_2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=ed1.getText().toString();
                String pwd=ed2.getText().toString();
                List<User> user= LitePal.where("username like ?",name).limit(1).find(User.class);
                User user1=user.get(0);
                String pwd2=user1.getPassword();
                if (pwd.equals(pwd2)){
                    Intent intent1 = new Intent();
                    Intent intent=new Intent(UserLogin.this,MainActivity.class);
                    NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    PendingIntent pendingIntent=PendingIntent.getActivity(UserLogin.this,0,intent,0);
                    if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
                        NotificationChannel notificationChannel=new NotificationChannel("1","name",NotificationManager.IMPORTANCE_HIGH);
                        manager.createNotificationChannel(notificationChannel);

                    }
                    Notification notification=new NotificationCompat.Builder(UserLogin.this,"1")
                            .setContentTitle("Cool_WeatherAPP")
                            .setContentText("用户已成功登陆")
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.drawable.biaoqing)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .build();
                    manager.notify(1,notification);
                    intent1.setClass(UserLogin.this,MainActivity.class);
                    startActivity(intent1);
                }else {
                    Toast.makeText(UserLogin.this,"密码错误，请重新输入",Toast.LENGTH_SHORT).show();;
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserLogin.this,UserRegister.class);
                startActivity(intent);
            }
        });
    }
}