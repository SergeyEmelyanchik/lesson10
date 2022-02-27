package ru.geekbrains.lesson10.repo;

import java.util.List;

public interface NoteSource {
    int size();
    List<NoteData> getAllCardsData();
    NoteData getCardData(int position);
}
