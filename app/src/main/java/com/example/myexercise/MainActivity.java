package com.example.myexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.example.myexercise.db.DatabaseHelper;
import com.example.myexercise.view.CustomVideoView;

import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {
    private TextView tvStatus;  //定义主界面文本变量
    private TextView tvId;
    private TextView tvEmail;

    private SharedPreferences sp;
    private String key = "com.example.myexercise_preferences";

    private static final int REQUEST_REGISTER_CODE = 1; //定义传值的值
    //private static final int REQUEST_SIGNIN_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartVideo();

        sp = this.getSharedPreferences(key,Context.MODE_PRIVATE);

        tvStatus = findViewById(R.id.tv_status); //主界面textview引用其他Activity的输入返回值显示
        tvEmail = findViewById(R.id.tv_email);
        tvId = findViewById(R.id.tv_id);
        //System.out.println("hello");
    }

    public void btn_start_signin_onclick(View view) { //按钮跳转界面
        Boolean str = sp.getBoolean("readeddd",false);
        System.out.println(str);
        if(!sp.getBoolean("readeddd",false)){
            Intent intent = new Intent(this,SigninActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            finish();
            String data = "Start your continue use!";//toast使用
            Intent intent = new Intent(this,Chat_Bar.class);
            intent.putExtra("extra_data",data);//intent.putExtra是用来传参数的
            startActivity(intent);
            finish();
        }
    }

    public void btn_start_register_onclick(View view) { //同上
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivityForResult(intent,REQUEST_REGISTER_CODE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        if (intent == null){ //如果无Activity则返回退出选项
            return;
        }
        switch (requestCode){ //按照传值进行操作
            case REQUEST_REGISTER_CODE:

                StartVideo();

                //在TextView上显示Text
                tvId.setText("ID:"+intent.getStringExtra("id"));
                tvEmail.setText("Email:"+intent.getStringExtra("email"));
                break;
        }
    }

    public void StartVideo(){
        //找VideoView控件
        final CustomVideoView customVideoView = (CustomVideoView) findViewById(R.id.videoview);
        //加载视频文件
        customVideoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sky));
        //播放
        customVideoView.start();
        //循环播放
        customVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                customVideoView.start();
            }
        });
    }
}
