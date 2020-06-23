package com.example.myexercise;

import android.content.Context;
import android.util.AttributeSet;

import androidx.viewpager.widget.ViewPager;

public class NoScrollViewPager extends ViewPager {
    private boolean isCanScroll = true;

    public NoScrollViewPager(Context context){
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public void setCanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }

    @Override
    public void setCurrentItem(int item,boolean smoothScroll){
        super.setCurrentItem(item,smoothScroll);
    }

    @Override
    public void setCurrentItem(int item){
        super.setCurrentItem(item);
    }
}
