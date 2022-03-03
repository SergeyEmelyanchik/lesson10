package ru.geekbrains.lesson10.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;

import ru.geekbrains.lesson10.R;
import ru.geekbrains.lesson10.publisher.Observer;
import ru.geekbrains.lesson10.repo.LocalRepoImpl;
import ru.geekbrains.lesson10.repo.NoteData;
import ru.geekbrains.lesson10.repo.NotesSource;
import ru.geekbrains.lesson10.ui.MainActivity;
import ru.geekbrains.lesson10.ui.editor.NoteEditorFragment;


public class SocialFragment extends Fragment implements OnItemClickListner {

    SocialAdapter socialAdapter;
    NotesSource data;
    RecyclerView recyclerView;


    public static SocialFragment newInstance() {
        SocialFragment fragment = new SocialFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_social, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initRecycler(view);
        setHasOptionsMenu(true);//говорим что менюшки есть

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notes_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //вешаем обработку кликов
        switch (item.getItemId()) {
            case R.id.action_add: { // добавление новой карточки
                data.addNoteData(new NoteData("Заголовок новой карточки" + data.size(),
                        "Описание новой карточки" + data.size(), R.drawable.flag, false, Calendar.getInstance().getTime()));
                socialAdapter.notifyItemInserted(data.size() - 1);
                recyclerView.smoothScrollToPosition(data.size() - 1); //плавный скрол
                return true;
            }
            case R.id.action_clear: { // очистка всего списка
                data.clearNotesData();
                socialAdapter.notifyDataSetChanged();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.note_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int menuPosition = socialAdapter.getMenuPosition();
        switch (item.getItemId()) {
            case R.id.action_update: {
                /* data.updateNoteData(menuPosition, new NoteData("Заголовок обновленной карточки" + data.size(),
                        "Описание обновленной карточки" + data.size(), data.getNoteData(menuPosition).getPicture(), false, Calendar.getInstance().getTime()));
                socialAdapter.notifyItemChanged(menuPosition); */


                Observer observer = new Observer() {
                    @Override
                    public void receiveMessage(NoteData noteData) {
                        ((MainActivity) requireActivity()).getPublisher().unsubscride(this);
                        data.updateNoteData(menuPosition, noteData);
                        socialAdapter.notifyItemChanged(menuPosition);
                    }
                };
                ((MainActivity) requireActivity()).getPublisher().subscride(observer);
                ((MainActivity) requireActivity()).getNavigation().addFragment(NoteEditorFragment.newInstance(data.getNoteData(menuPosition)), true);
                return true;
            }
            case R.id.action_delete: {
                data.deleteNoteData(menuPosition);
                socialAdapter.notifyItemRemoved(menuPosition);
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }


    void initAdapter() {
        socialAdapter = new SocialAdapter(this);
        data = new LocalRepoImpl(requireContext().getResources()).init();
        socialAdapter.setData(data);
        socialAdapter.setOnItemClickListner(SocialFragment.this); //место куда ты передаешь клики (кусочек фрагмента) буду Я
    }


    void initRecycler(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(socialAdapter);

        DefaultItemAnimator animator = new DefaultItemAnimator(); // бесполезные четыре строки кода (для галочки)
        animator.setChangeDuration(3000);
        animator.setRemoveDuration(3000);
        recyclerView.setItemAnimator(animator);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);


    }

    String[] getData() {
        String[] data = getResources().getStringArray(R.array.titles);
        return data;
    }

    @Override
    public void onItemClick(int position) {
        String[] data = getData();
        Toast.makeText(requireContext(), "Нажали на" + data[position], Toast.LENGTH_SHORT).show();

    }

}

