package com.example.noteapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ViewModel(app: Application, private val databaseHelper: Databsehelper) :
    AndroidViewModel(app) {

    fun addNote(note: Task) {
        databaseHelper.insertNote(note)
    }

    fun getAllNotes(): List<Task> {
        return databaseHelper.getAllNotes()
    }

    fun updateNote(note: Task) {
        databaseHelper.updateNote(note)
    }

    fun getNoteById(noteId: Int): Task {
        return databaseHelper.getNoteByID(noteId)
    }

    fun deleteNote(noteId: Int) {
        databaseHelper.deleteNote(noteId)
    }
}
