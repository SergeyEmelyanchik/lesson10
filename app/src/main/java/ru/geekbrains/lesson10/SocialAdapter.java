package ru.geekbrains.lesson10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.MyViewHolder> {

    private String[] data;

    public void setData(String[] data) {
        this.data = data;
        notifyDataSetChanged(); // команда адаптеру перерисовать ВСЕ полученые данные
    }

    @NonNull
    @Override // метод который создает ViewHolder и зависимость от viewType
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_social_recycler_item, parent, false));

    }

    @Override // метод связвывает уже созданый ViewHolder с его позицией
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(data[position]);
    }

    @Override
    public int getItemCount() { //метод который возращает его размер
        return data.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {//определяем ViewHolder который удерживает наш макет
            super(itemView);
            textView = (TextView) itemView;
        }

        // в этом методе связываем контент с макетом
        public void bindContentWithLayout(String content) {
            textView.setText(content);
        }
    }
}
