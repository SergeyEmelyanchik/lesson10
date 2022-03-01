package ru.geekbrains.lesson10.repo;

import java.util.List;

public interface NotesSource {
    int size();

    List<NoteData> getAllNotesData();

    NoteData getNoteData(int position);

    void clearNotesData();

    void addNoteData(NoteData noteData);


    void deleteNoteData(int position);

    void updateNoteData(int position, NoteData newNoteData);
}
