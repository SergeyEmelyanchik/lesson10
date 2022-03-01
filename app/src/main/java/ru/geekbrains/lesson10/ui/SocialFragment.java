package ru.geekbrains.lesson10.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.geekbrains.lesson10.R;
import ru.geekbrains.lesson10.repo.LocalRepoImpl;
import ru.geekbrains.lesson10.repo.NoteData;
import ru.geekbrains.lesson10.repo.NotesSource;


public class SocialFragment extends Fragment implements OnItemClickListner {

    SocialAdapter socialAdapter;
    NotesSource data;

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
            case R.id.action_add: {
                data.addNoteData(new NoteData("Заголовок новой карточки" + data.size(),
                        "Описание новой карточки" + data.size(), R.drawable.flag, false));
                socialAdapter.notifyDataSetChanged();
            }
            case R.id.action_clear: {
            }
        }
        return super.onOptionsItemSelected(item);
    }

    void initAdapter() {
        socialAdapter = new SocialAdapter();
        data = new LocalRepoImpl(requireContext().getResources()).init();
        socialAdapter.setData(data);
        socialAdapter.setOnItemClickListner(SocialFragment.this); //место куда ты передаешь клики (кусочек фрагмента) буду Я
    }


    void initRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(socialAdapter);

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
