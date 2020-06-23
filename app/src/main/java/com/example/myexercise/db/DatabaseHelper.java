package com.example.myexercise.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "member.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        String sql = "create table user(name varchar(30) primary key,password varchar(30))";
        db.execSQL(sql);
        //初始化数据
        initDate(db);
    }

    private void initDate(SQLiteDatabase db) {
        String sql = String.format("insert into user(name,password) values(1,111)");
        db.execSQL(sql);//运行这条sql语句
        sql = String.format("insert into user(name,password) values(2,222)");
        db.execSQL(sql);
        sql = String.format("insert into user(name,password) values(3,333)");
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
