package com.example.myexercise.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myexercise.R;
import com.example.myexercise.bean.Person;


import java.util.List;

public class PersonAdapter extends BaseRecyclerViewAdapter<Person> {

    private OnDeleteClickLister mDeleteClickListener;

    public PersonAdapter(Context context, List<Person> data) {
        super(context, data, R.layout.personitem);
    }

    @Override
    protected void onBindData(RecyclerViewHolder holder, Person bean, int position) {
        View view = holder.getView(R.id.item_delete);
        view.setTag(position);
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDeleteClickListener != null) {
                        mDeleteClickListener.onDeleteClick(v, (Integer) v.getTag());
                    }
                }
            });
        }
        ((TextView) holder.getView(R.id.item_name)).setText(bean.getName());
        ((ImageView) holder.getView(R.id.item_image)).setImageResource(bean.getImg());
    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }
}