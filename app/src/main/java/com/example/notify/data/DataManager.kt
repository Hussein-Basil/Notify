package com.example.notify.data;

import com.orhanobut.hawk.Hawk

object DataManager {
    private val notesList = Hawk.get<MutableList<NoteData>>("notes")
    val notes: List<NoteData> get() = notesList

    fun addNote(note: NoteData) {
        notesList.add(note)
        Hawk.put("notes", notes)
    }

    fun deleteNoteAt(index: Int) {
        notesList.removeAt(index)
        Hawk.put("notes", notes)
    }
}
