package ru.geekbrains.lesson10.publisher;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.lesson10.repo.NoteData;

public class Publisher {
    private List<Observer> observers; //вас всех запомнил

   public Publisher(){
        observers=new ArrayList<>();
    }
    //Научим Observer подписывать на свой observers список
    public void subscride(Observer observer) {
        observers.add(observer); //добавляем нового слушателя
    }

    public void unsubscride(Observer observer) {
        observers.remove(observer); //удаляем слушателя
    }

    public void sendMessage(NoteData noteData) { //даем возможность что то загрузить NoteData это просто пример урока
        for (Observer observer : observers) { // прохожусь по всему списку и отсылаю всем сообзения
            observer.receiveMessage(noteData);
        }
    }
}
