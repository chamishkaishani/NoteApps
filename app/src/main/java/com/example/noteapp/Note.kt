package com.example.noteapp

import ColorViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.ActivityNoteAddBinding
import com.example.noteapp.databinding.ActivityNoteBinding
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*



class Note : AppCompatActivity() {

    private lateinit var binding:ActivityNoteBinding
    private  lateinit var db: Databsehelper
    private lateinit var notesAdapter: NotesAdapter

    private lateinit var changeColorButton: Button
    private lateinit var colorViewModel: ColorViewModel
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = Databsehelper(this)
        notesAdapter = NotesAdapter(db.getAllNotes(), this)

        binding.AddRecycleView.layoutManager = LinearLayoutManager(this)
        binding.AddRecycleView.adapter = notesAdapter







        binding.add.setOnClickListener {
            val intent = Intent(this, NoteAdd::class.java)
            startActivity(intent)
        }

//        changeColorButton = binding.root.findViewById(R.id.colourButton) // Assuming your button has id 'changeColorButton'
//        changeColorButton.setOnClickListener {
//            // Change background color to gray
//            binding.root.setBackgroundColor(Color.GRAY)
//        }

        colorViewModel = ViewModelProvider(this).get(ColorViewModel::class.java)

        colorViewModel.backgroundColor.observe(this, { color ->
            binding.root.setBackgroundColor(color)
        })

        changeColorButton = binding.root.findViewById(R.id.colourButton) // Assuming your button has id 'colourButton'
        changeColorButton.setOnClickListener {
            // Change background color to gray
            coroutineScope.launch {
                // Change background color to gray
                colorViewModel.changeBackgroundColor(Color.GRAY)
            }        }

    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshDate(db.getAllNotes())
    }


    override fun onDestroy() {
        super.onDestroy()
        // Cancel all coroutines when the activity is destroyed to avoid memory leaks
        coroutineScope.cancel()
    }
    
    
}