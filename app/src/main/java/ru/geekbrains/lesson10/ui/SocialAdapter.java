package ru.geekbrains.lesson10.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.lesson10.R;
import ru.geekbrains.lesson10.repo.NoteData;
import ru.geekbrains.lesson10.repo.NoteSource;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.MyViewHolder> {

    private NoteSource cardSource;
    OnItemClickListner onItemClickListner;

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setData(NoteSource cardSource) {
        this.cardSource = cardSource;
        notifyDataSetChanged(); // команда адаптеру перерисовать ВСЕ полученые данные
    }

    SocialAdapter(NoteSource cardSource) {
        this.cardSource = cardSource;
    }

    SocialAdapter() {

    }

    @NonNull
    @Override // метод который создает ViewHolder и зависимость от viewType
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_social_cardview_item, parent, false));

    }

    @Override // метод связвывает уже созданый ViewHolder с его позицией
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(cardSource.getCardData(position));
    }

    @Override
    public int getItemCount() { //метод который возращает его размер
        return cardSource.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private ImageView imageView;
        private ToggleButton toggleButton;

        public MyViewHolder(@NonNull View itemView) {//определяем ViewHolder который удерживает наш макет
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewDescription = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            toggleButton = (ToggleButton) itemView.findViewById(R.id.like);
            /* textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListner!=null){
                        onItemClickListner.onItemClick(getLayoutPosition());
                    }
                }
            });*/
        }

        // в этом методе связываем контент с макетом
        public void bindContentWithLayout(NoteData content) {
            textViewTitle.setText(content.getTitle());
            textViewDescription.setText(content.getDescription());
            imageView.setImageResource(content.getPicture());
            toggleButton.setChecked(content.isLike());
        }
    }
}
