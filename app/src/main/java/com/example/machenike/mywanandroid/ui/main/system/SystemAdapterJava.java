package com.example.machenike.mywanandroid.ui.main.system;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.machenike.mywanandroid.R;
import com.example.machenike.mywanandroid.model.system.Children;
import com.example.machenike.mywanandroid.model.system.SystemModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

/**
 * created time：2020/3/19 19:16
 * created by：动感超人
 * Describe ：
 */
public class SystemAdapterJava extends RecyclerView.Adapter<SystemAdapterJava.MyViewHolder> {
    private List<SystemModel> mList;
    private Context mContext;
    private View view;
    public SystemAdapterJava(List<SystemModel> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
        .inflate(R.layout.item_system,parent));
        view = LayoutInflater.from(mContext).inflate(R.layout.item_system_chip,null,false);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.systemTitle.setText(mList.get(position).getName());
        for (Children tag:
             mList.get(position).getChildren()) {
//            View view = LayoutInflater.from(holder.systemContent.getContext()).
//                    inflate(R.layout.item_system_chip,,false);
            Chip chip = view.findViewById(R.id.itemSystemTag);
            chip.setText(tag.getName());
            holder.systemContent.addView(view);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private Chip createTagTextView(String tagString) {
        Chip chipText = new Chip(mContext);
        chipText.setTextColor(mContext.getResources().getColor(R.color.black));
        chipText.setTextSize(14);
        chipText.setText(tagString);
        chipText.setOnClickListener(v -> {
            String keyWord = chipText.getText().toString();
            if (TextUtils.isEmpty(keyWord)) {

            } else {

            }
        });
        return chipText;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView systemTitle;
        ChipGroup systemContent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            systemTitle = itemView.findViewById(R.id.itemSystemTitle);
            systemContent = itemView.findViewById(R.id.itemSystemContent);
        }
    }
}
