package com.example.myexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myexercise.db.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class SigninActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private String key = "com.example.myexercise_preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        sp = this.getSharedPreferences(key, Context.MODE_PRIVATE);
    }

    public void btn_submit_signin_onclick(View view) {
        boolean flag = false;//用于判断是否账号密码错误
        EditText etId = findViewById(R.id.et_signin_id);
        EditText etPassword = findViewById(R.id.et_signin_password);
        String userName = etId.getText().toString();
        String passWord = etPassword.getText().toString();
        //数据库起始通用语句
        DatabaseHelper helper = new DatabaseHelper(SigninActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        //参数1：表名
        //参数2：想要得到的列的String
        //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式？？？
        //参数6：having条件？？？
        //参数7：排序方式
        Cursor cursor = db.query("user",new String[]{"password"},"name=?", new String[]{etId.getText().toString()},null,null,null);
        //System.out.println("查到的数据为：");
        while(cursor.moveToNext()){
            String password = cursor.getString(cursor.getColumnIndex("password"));
            if(password.equals(passWord))   flag = true;//可以加个break语句，但是没必要
            //System.out.println("-->"+password);
        }
        //错误处理
        if(TextUtils.isEmpty(userName)){
            etPassword.setError("用户名不能为空");
            return ;
        }
        if(TextUtils.isEmpty(passWord)){
            etPassword.setError("密码不能为空");
            return ;
        }

        if(flag){//如果确认成功就进入下一个页面

            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("readeddd",true).apply();

            finish();
            String data = "hi,"+userName;//toast使用
            Intent intent = new Intent(SigninActivity.this,Chat_Bar.class);
            intent.putExtra("extra_data",data);//intent.putExtra是用来传参数的
            startActivity(intent);
        }
        else{
            Toast.makeText(SigninActivity.this,"账号或密码错误！",Toast.LENGTH_LONG).show();
        }
    }
}
