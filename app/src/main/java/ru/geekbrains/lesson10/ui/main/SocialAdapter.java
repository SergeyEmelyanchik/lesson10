package ru.geekbrains.lesson10.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.lesson10.R;
import ru.geekbrains.lesson10.repo.NoteData;
import ru.geekbrains.lesson10.repo.NotesSource;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.MyViewHolder> {

    private NotesSource notesSource;
    OnItemClickListner onItemClickListner;
    Fragment fragment;

    private int menuPosition;

    public int getMenuPosition() {
        return menuPosition;
    }



    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setData(NotesSource notesSource) {
        this.notesSource = notesSource;
        notifyDataSetChanged(); // команда адаптеру перерисовать ВСЕ полученые данные
    }

    SocialAdapter(NotesSource notesSource) {
        this.notesSource = notesSource;
    }
    SocialAdapter(){

    }
    SocialAdapter(Fragment fragment) {
        this.fragment = fragment;

    }

    @NonNull
    @Override // метод который создает ViewHolder и зависимость от viewType
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater.inflate(R.layout.fragment_social_noteview_item, parent, false));

    }

    @Override // метод связвывает уже созданый ViewHolder с его позицией
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(notesSource.getNoteData(position));
    }

    @Override
    public int getItemCount() { //метод который возращает его размер
        return notesSource.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescription;
        private final ImageView imageView;
        private final ToggleButton toggleButton;

        public MyViewHolder(@NonNull View itemView) {//определяем ViewHolder который удерживает наш макет
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewDescription = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            toggleButton = (ToggleButton) itemView.findViewById(R.id.like);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    menuPosition = getLayoutPosition();
                    return false;
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    menuPosition = getLayoutPosition();
                    return false;
                }
            });
            fragment.registerForContextMenu(itemView);

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
            textViewDescription.setText(content.getDescription()+"  "+content.getDate());
            imageView.setImageResource(content.getPicture());
            toggleButton.setChecked(content.isLike());
        }
    }
}
