package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapp.databinding.ActivityNoteBinding
import com.example.noteapp.databinding.ActivityUpdateNoteBinding

class UpdateNote : AppCompatActivity() {

    private  lateinit var binding: ActivityUpdateNoteBinding
    private  lateinit var  db :Databsehelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Databsehelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if(noteId == -1){
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.updatetheam.setText(note.title)
        binding.updatemessage.setText(note.content)

        binding.updatesave.setOnClickListener{
            val newTitle = binding.updatetheam.text.toString()
            val newContent = binding.updatemessage.text.toString()
            val updateNote = Task(noteId, newTitle ,newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"change Saved", Toast.LENGTH_SHORT).show()
        }
    }

}