package com.example.myexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myexercise.db.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void btn_start_register_onclick(View view) {
        EditText etId = findViewById(R.id.et_reg_id);  //给输入对应文本框的文本赋值
        EditText etEmail = findViewById(R.id.et_reg_email);
        EditText etPassword = findViewById(R.id.et_reg_password);

        //ContentValue类似HASHMAP，但是KEY只能为STRING,一个value相当于一条
        ContentValues values = new ContentValues();
        values.put("name",etId.getText().toString());
        values.put("password",etPassword.getText().toString());
        //数据库关联的固定语句
        DatabaseHelper helper = new DatabaseHelper(RegisterActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        //将一条value放入对应表里
        db.insert("user",null,values);
        Intent intent = new Intent();
        intent.putExtra("id",etId.getText().toString());  //返回值
        intent.putExtra("email",etEmail.getText().toString());
        intent.putExtra("password",etPassword.getText().toString());
        setResult(0,intent); //？？？
        finish(); //当你打开的Activity已经执行完成并且需要被关闭的时候可以调用这个方法，当你按返回的时候，他将返回到当前Activity的发起者。
    }
}
