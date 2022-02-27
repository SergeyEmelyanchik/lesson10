package ru.geekbrains.lesson10.repo;

import java.util.List;

public interface CardsSource {
    int size();
    List<CardData> getAllCardsData();
    CardData getCardData(int position);
}
