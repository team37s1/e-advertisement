package com.example.a37_1.e_advertisement;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a37_1.e_advertisement.model.News;

import java.util.List;

/**
 * Created by User on 26.11.2017.
 */

public class RecViewAdapt extends RecyclerView.Adapter<RecViewAdapt.RecViewHolder> {
    private List<News> alist;

    public RecViewAdapt(List<News> alist) {
        this.alist = alist;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RecViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {
        News news = alist.get(position);
        holder.title.setText(news.content);
        holder.content.setText(news.content);
        holder.area.setText(news.area);
        holder.category.setText(news.category);
    }

    @Override
    public int getItemCount() {
        return alist.size();
    }

    class RecViewHolder extends RecyclerView.ViewHolder {
        TextView title, content, area, category;

        @SuppressLint("CutPasteId")
        public RecViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.RVtitle);
            content = itemView.findViewById(R.id.RVcontent);
            area = itemView.findViewById(R.id.RVarea);
            category = itemView.findViewById(R.id.RVcategory);
        }
    }
}
