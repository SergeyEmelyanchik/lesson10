package ru.geekbrains.lesson10.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.geekbrains.lesson10.R;
import ru.geekbrains.lesson10.repo.LocalRepoImpl;


public class SocialFragment extends Fragment implements OnItemClickListner {

    SocialAdapter socialAdapter;

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

    }

    void initAdapter() {
        socialAdapter = new SocialAdapter();
        LocalRepoImpl localRepoImpl = new LocalRepoImpl(requireContext().getResources());
        localRepoImpl.init();
        socialAdapter.setData(localRepoImpl.init());
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
