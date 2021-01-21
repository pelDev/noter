package com.peldev.noter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerAdapter(private val context :Context, private val notes: List<NoteInfo>) : RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {


    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = notes.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(notes[position])
        holder.notePosition = position
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val courseTitle: TextView? = itemView.findViewById<TextView?>(R.id.course_title)
        private val noteTitle: TextView? = itemView.findViewById<TextView?>(R.id.note_title)
        var notePosition = 0
        init {
            itemView.setOnClickListener {
                val intent = Intent(context, NoteActivity::class.java)

                intent.putExtra(NOTE_POSITION, notePosition)

                context.startActivity(intent)
            }
        }
        fun bindTo(note: NoteInfo) {
            noteTitle?.text = note.title
            courseTitle?.text = note.course?.title
        }
    }

}