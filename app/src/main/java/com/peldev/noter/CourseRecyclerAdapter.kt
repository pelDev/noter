package com.peldev.noter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseRecyclerAdapter(private val context :Context, private val courses: List<CourseInfo>) : RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder>() {


    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_course, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = courses.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(courses[position])
        holder.coursePosition = position
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val courseTitle: TextView? = itemView.findViewById<TextView?>(R.id.course_title_course)
        var coursePosition = 0
//        init {
//            itemView.setOnClickListener {
//                val intent = Intent(context, NoteActivity::class.java)
//
//                intent.putExtra(NOTE_POSITION, coursePosition)
//
//                context.startActivity(intent)
//            }
//        }
        fun bindTo(course: CourseInfo) {
            courseTitle?.text = course.title
        }
    }

}