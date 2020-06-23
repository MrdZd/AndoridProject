package com.example.myexercise.fragment;


import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myexercise.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//网络查询Fragment

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    View view;
    Button button;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_contact, container, false);
        initsetOnClickListener();
        return view;
    }

    private void initsetOnClickListener() {
        button = (Button) view.findViewById(R.id.button);
        textView = (TextView) view.findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("haha","go");
                commonDialog();
                run1();
            }
        });
    }

    private void run1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn = null;
                BufferedReader br = null;
                try {
                    URL url = new URL("http://www.baidu.com/");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in = conn.getInputStream();
                    br = new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb = new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                }catch (Exception e) {
                    e.printStackTrace();  //在命令行打印异常信息在程序中出错的位置及原因
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn != null){
                        conn.disconnect();
                    }
                    if (br != null){
                        try {
                            br.close();
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public void setContent(final String s) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run(){
                textView.setText(s);
                Log.d("haha",s);
            }
        });
    }

    private void commonDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("网络请求进行中......");
        builder.create().show();//使用show()方法显示对话框
    }

}
