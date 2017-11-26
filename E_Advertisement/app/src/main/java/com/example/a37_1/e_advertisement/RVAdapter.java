package com.example.a37_1.e_advertisement;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a37_1.e_advertisement.model.News;

import java.util.ArrayList;

/**
 * Created by User on 26.11.2017.
 */

    public class RVAdapter extends RecyclerView.Adapter<ViewHolder> {
        //Здесь мы будем хранить набор наших данных
        private ArrayList<News> news;

        //Простенький конструктор

    public RVAdapter(ArrayList<News> news) {
        this.news = news;
    }

    //Этот метод вызывается при прикреплении нового элемента к RecyclerView
        @Override
        public void onBindViewHolder(ViewHolder personViewHolder, int i){
            //Получаем элемент набора данных для заполнения
            News currentPerson = news.get(i);
            //Заполняем поля viewHolder'а данными из элемента набора данных
            personViewHolder.title.setText(currentPerson.title);
            personViewHolder.content.setText(currentPerson.content);
            personViewHolder.area.setText(currentPerson.area);
            personViewHolder.category.setText(currentPerson.category);
        }

        //Этот метод вызывается при создании нового ViewHolder'а

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
            //Создаём новый view при помощи LayoutInflater
            //Особенно упорные могут создать его программно с помощью view.addView
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_second, viewGroup, false);

            return new ViewHolder(itemView);
        }

        //Этот метод возвращает количество элементов списка
        @Override
        public int getItemCount(){
            //не мудрствуя лукаво, вернём размер списка
            return news.size();
        }
    }
