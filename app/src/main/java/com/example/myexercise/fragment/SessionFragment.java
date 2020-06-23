package com.example.myexercise.fragment;


import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.myexercise.R;
import com.example.myexercise.adapter.PersonAdapter;
import com.example.myexercise.bean.Person;
import com.example.myexercise.view.SlideRecyclerView;

import java.util.ArrayList;

//会话Fragment

/**
 * A simple {@link Fragment} subclass.
 */
public class SessionFragment extends Fragment  {


    private SlideRecyclerView recycler_view_list;
    private PersonAdapter mPersonAdapter;

    private View view ;
    private ArrayList<Person> peronlist = new ArrayList<Person>();

    private String[] name = {"小红","小黑","小黄","小绿","小白","小暗","小只"};
    private int[] imgs = {R.mipmap.tx1,R.mipmap.tx2,R.mipmap.tx3,R.mipmap.tx4,R.mipmap.tx5,R.mipmap.tx6,R.mipmap.tx7};

    public SessionFragment() {
        // Required empty public constructor
    }


    @Override//第一个用来找xml，第二个表示容器，第三个表示保存机制
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate将一个xml加载为view
        view =  inflater.inflate(R.layout.fragment_session2, container, false);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        recycler_view_list = (SlideRecyclerView) view.findViewById(R.id.myrecyclerview);
        recycler_view_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        //加分割线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.divider_inset));
        recycler_view_list.addItemDecoration(itemDecoration);

        //人员列表数组
        peronlist = new ArrayList<>();

        for(int i=0;i<name.length;i++){
            Person person = new Person(name[i],imgs[i]);
            peronlist.add(person);
        }

        //设置是适配器
        mPersonAdapter = new PersonAdapter(getActivity(), peronlist);
        recycler_view_list.setAdapter(mPersonAdapter);

        mPersonAdapter.setOnDeleteClickListener(new PersonAdapter.OnDeleteClickLister() {
            @Override
            public void onDeleteClick(View view, int position) {
                peronlist.remove(position);
                mPersonAdapter.notifyDataSetChanged();
                recycler_view_list.closeMenu();
            }
        });
    }
}
