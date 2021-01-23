package com.peldev.noter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerAdapter(private val context :Context,
                          private val notes: List<NoteInfo>)
    : RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {


    private lateinit var listener: OnSelectedListener
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    fun setOnSelectedListener(listener: OnSelectedListener) {
        this.listener = listener
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
        var note: NoteInfo? = null
        init {
            itemView.setOnClickListener {
                note?.let { it1 -> listener.onNoteSelected(it1) }
                listener.navigateToNoteActivity(context, notePosition)
            }
        }
        fun bindTo(note: NoteInfo) {
            this.note = note
            noteTitle?.text = note.title
            courseTitle?.text = note.course?.title
        }
    }

    interface OnSelectedListener {
        fun onNoteSelected(note: NoteInfo)
        fun navigateToNoteActivity(context: Context, notePosition: Int) {
            val intent = Intent(context, NoteActivity::class.java)

            intent.putExtra(NOTE_POSITION, notePosition)

            context.startActivity(intent)
        }
    }

}