package com.example.a37_1.e_advertisement;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.a37_1.e_advertisement.R;

/**
 * Created by User on 26.11.2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    //объявим поля, созданные в файле интерфейса itemView.xml
    public TextView title;
    public TextView content;
    public TextView area;
    public TextView category;

    //объявляем конструктор
    @SuppressLint("CutPasteId")
    public ViewHolder(View itemView){
        super(itemView);
        //привязываем элементы к полям
        title = (TextView)itemView.findViewById(R.id.RVtitle);
        content = (TextView)itemView.findViewById(R.id.RVcategory);
        area = (TextView)itemView.findViewById(R.id.RVarea);
        category = itemView.findViewById(R.id.RVcategory);
    }
}