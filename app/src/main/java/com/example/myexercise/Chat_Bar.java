package com.example.myexercise;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myexercise.fragment.ContactFragment;
import com.example.myexercise.fragment.SessionFragment;
import com.example.myexercise.utils.ToolBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//黄油刀，以前总是要写很多findViewById来找到View对象，有了ButterKnife可以很轻松的省去这些步骤
import butterknife.ButterKnife;
import butterknife.InjectView;

public class Chat_Bar extends AppCompatActivity {
    //就是说上面id对应的控件跟下面的相联系起来（bufferknife）
    @InjectView(R.id.main_bottom)
    LinearLayout mLLbottom;

    @InjectView(R.id.tv_title)
    TextView mTvTitle;

    @InjectView(R.id.main_viewpager)
    NoScrollViewPager mViewPager;

    //Fragment类型的list
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private String[] toolBarTitleArr;
    private ToolBarUtil toolBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__bar);
        Intent intent = getIntent();

        //getStringExtra与之前的putExtra对应使用
        String data = intent.getStringExtra("extra_data");
        Toast toast = Toast.makeText(this,data,Toast.LENGTH_LONG);
        toast.show();

        //BufferKnife调用方法
        //在Fragment用，就在onCreateView里面加上ButterKnife.inject(this, view);
        ButterKnife.inject(this);

        initData();
        initListener();
        //下面是fragment不滑动的实现
        mViewPager.setCanScroll(false);
    }

    //与底部按钮有关
    private void initListener() {

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //修改颜色

                //修改title
                mTvTitle.setText(toolBarTitleArr[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        toolBarUtil.setmOnToolBarClickListener(new ToolBarUtil.OnToolBarClickListener() {
            @Override
            public void OnToolBarClick(int position) {
                mViewPager.setCurrentItem(position);
            }
        });
    }

    private void initData(){
        //添加Fragment到集合中
        mFragments.add(new SessionFragment());
        mFragments.add(new ContactFragment());

        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        //底部按钮
        toolBarUtil = new ToolBarUtil();
        //文字内容
        toolBarTitleArr = new String[]{"会话","网站搜索"};
        //底部按钮图标内容
        int[] iconArr = {R.mipmap.ic_launcher,R.mipmap.ic_launcher_round};
        toolBarUtil.createToolBar(mLLbottom, toolBarTitleArr,iconArr);

        //不写的话，根据ArrayList设置默认选中联系人


        toolBarUtil.changeColor(0);//???

    }

    //适配器类
    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            return mFragments.get(position);
        }

        @Override
        public int getCount(){
            return 2;
        }//几个Fragment
    }

    //返回键退出应用
    private boolean isExit=false;
    private Timer timer;

    //重写onKeyDown()方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //点击返回键调用方法
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
        }
        return false;
    }
    //点击返回键调用的方法
    private void exit(){
        if (isExit==false){
            isExit=true;
            Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
            timer=new Timer();
            //如果两秒内没有再次点击
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit=false;
                }
            },2000);
        }else {
            //2000ms内按第二次则退出
            finish();
            System.exit(0);
        }
    }

}
