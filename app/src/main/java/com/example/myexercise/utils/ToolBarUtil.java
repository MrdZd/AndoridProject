package com.example.myexercise.utils;

import android.text.PrecomputedText;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myexercise.R;

import java.util.ArrayList;
import java.util.List;

public class ToolBarUtil {

    private List<TextView> mTextViews = new ArrayList<TextView>();

    public void createToolBar(LinearLayout container, String[] toolBarTitleArr, int[] iconArr){
        for (int i=0;i<toolBarTitleArr.length;i++){

            TextView tv = (TextView) View.inflate(container.getContext(), R.layout.inflate_toolbar_btn,null);
            tv.setText(toolBarTitleArr[i]);
            //动态修改textview里面的drawableTop属性
            tv.setCompoundDrawablesWithIntrinsicBounds(0,iconArr[i],0,0);//左上右下
            int width = 0;
            int height = LinearLayout.LayoutParams.MATCH_PARENT;
            //居中显示
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
            //设置weight属性
            params.weight = 1;
            container.addView(tv,params);

            //保存textView到集合中
            mTextViews.add(tv);
            //设置点击事件
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //不同模块之间传值之间需要用接口回调
                    //3、需要传值的地方，用接口对象调用接口方法
                    mOnToolBarClickListener.OnToolBarClick(finalI);
                }
            });
        }
    }
    //1、创建接口和接口方法
    public interface OnToolBarClickListener{
        void OnToolBarClick(int position);
    }
    //2\定义接口变量
    OnToolBarClickListener mOnToolBarClickListener;

    //4、暴露一个公共的方法
    public void setmOnToolBarClickListener(OnToolBarClickListener onToolBarClickListener){
        mOnToolBarClickListener = onToolBarClickListener;
    }

    public void changeColor(int position){
        //还原所有颜色
        for (TextView tv : mTextViews){
            tv.setSelected(false);
        }

        mTextViews.get(position).setSelected(true);//通过设置selected属性控制为选中效果
    }
}
