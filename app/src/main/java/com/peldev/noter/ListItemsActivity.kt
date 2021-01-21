package com.peldev.noter

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list_items.*
import kotlinx.android.synthetic.main.app_bar_list_items.*
import kotlinx.android.synthetic.main.content_list_items.*

class ListItemsActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private val noteLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    private val courseLayoutManager by lazy {
        GridLayoutManager(this, 2)
    }

    private val noteRecyclerAdapter by lazy {
        NoteRecyclerAdapter(this, DataManager.notes)
    }

    private val courseRecyclerAdapter by lazy {
        CourseRecyclerAdapter(this, DataManager.courses.values.toList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items)
        setSupportActionBar(findViewById(R.id.toolbar))
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.open_nav_content_description,
            R.string.close_nav_content_description
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        fab.setOnClickListener {
            val activityIntent = Intent(this, NoteActivity::class.java)
            startActivity(activityIntent)
        }

        displayNotes()
    }

    private fun displayNotes() {
        list_items.layoutManager = noteLayoutManager
        list_items.adapter = noteRecyclerAdapter

        nav_view.menu.findItem(R.id.nav_notes).isChecked = true
    }

    fun displayCourse() {
        list_items.layoutManager = courseLayoutManager
        list_items.adapter = courseRecyclerAdapter

        nav_view.menu.findItem(R.id.nav_courses).isChecked = true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        list_items.adapter?.notifyDataSetChanged()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_notes -> {
                displayNotes()
            }

            R.id.nav_courses -> {
                displayCourse()
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}