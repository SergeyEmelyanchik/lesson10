package ru.geekbrains.lesson10.publisher;

import ru.geekbrains.lesson10.repo.NoteData;

public interface Observer {
    void receiveMessage (NoteData noteData);
}
