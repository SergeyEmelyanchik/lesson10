package ru.geekbrains.lesson10.repo;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.lesson10.R;

public class LocalRepoImpl implements CardsSource {

        private List<CardData> dataSource;
        private Resources resources;


        public LocalRepoImpl(Resources resources){
            dataSource = new ArrayList<CardData>();
            this.resources = resources;
        }

        public LocalRepoImpl init(){

            String[] titles = resources.getStringArray(R.array.titles);
            String[] descriptions = resources.getStringArray(R.array.descriptions);
            TypedArray pictures = resources.obtainTypedArray(R.array.pictures);

            for(int i=0;i<titles.length;i++){
                dataSource.add(new CardData(titles[i],descriptions[i],pictures.getResourceId(i,0),false));
            }
            return this;
        }

        @Override
        public int size() {
            return dataSource.size();
        }

        @Override
        public List<CardData> getAllCardsData() {
            return dataSource;
        }

        @Override
        public CardData getCardData(int position) {
            return dataSource.get(position);

        }
    }
