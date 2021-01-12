package com.peldev.noter

import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val dm = DataManager()

        val adapterCourses = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            dm.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_courses.adapter = adapterCourses

    }

}