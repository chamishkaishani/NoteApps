package com.example.noteapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes:List<Task>, context: Context) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    private val db :Databsehelper = Databsehelper(context)

            class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
                val titleTextView : TextView = itemView.findViewById(R.id.titleTextView)
                val contentTextView :TextView = itemView.findViewById(R.id.contentTextView)
                val update :ImageView = itemView.findViewById(R.id.editButton)
                val delete :ImageView = itemView.findViewById(R.id.deleteButton)

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {


        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return  NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    val note =  notes[position]
        holder.titleTextView.text =note.title
        holder.contentTextView.text =note.content

        holder.update.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateNote::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.delete.setOnClickListener{
            db.deleteNote(note.id)
            refreshDate(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Note Deleted", Toast.LENGTH_SHORT).show()
        }

    }

    fun refreshDate(newNotes: List<Task>){
        notes = newNotes
        notifyDataSetChanged()
    }
}