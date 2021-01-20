package com.peldev.noter

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.content_note.*

class NoteActivity : AppCompatActivity() {

    private var notePosition = POSITION_NOT_SET
    private val tag = this::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(findViewById(R.id.toolbar))

        val adapterCourses = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList()
        )
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_courses.adapter = adapterCourses

        // get note position from the intent only if save instance state is null
        notePosition =
            savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?: intent.getIntExtra(
                NOTE_POSITION,
                POSITION_NOT_SET
            )

        if (notePosition != POSITION_NOT_SET)
            displayNote()
        else {
            createNewNote()
        }
    }

    private fun createNewNote() {
        DataManager.notes.add(NoteInfo())
        notePosition = DataManager.notes.lastIndex
    }

    private fun displayNote() {
        if (notePosition > DataManager.notes.lastIndex) {
            showMessage("Note not found")
            Log.e(tag, "Invalid note position")
        }

        Log.i(
            tag,
            "Displaying note for position $notePosition, max position ${DataManager.notes.lastIndex}"
        )
        val note = DataManager.notes[notePosition]
        editTextNoteTitle.setText(note.title)
        editTextNoteText.setText(note.text)

        // retrieve course position
        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spinner_courses.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                if (notePosition <= DataManager.notes.lastIndex)
                    moveNext()
                else if (notePosition >= DataManager.notes.lastIndex)
                    showMessage("No more notes")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION, notePosition)
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title = editTextNoteTitle.text.toString()
        note.text = editTextNoteText.text.toString()
        note.course = spinner_courses.selectedItem as CourseInfo
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu?.findItem(R.id.action_next)
            if (menuItem != null) {
                menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_block)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

}