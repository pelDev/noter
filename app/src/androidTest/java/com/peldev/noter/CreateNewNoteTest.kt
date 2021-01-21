package com.peldev.noter

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// user interface test
@RunWith(AndroidJUnit4::class)
class CreateNewNoteTest {

    @Rule
    @JvmField
    val noteListActivity = ActivityScenarioRule(ListItemsActivity::class.java)

    @Test
    fun createNewNote() {
        val noteTitle = "Test note title"
        val noteText = "This is the body of my test note"

        // click the fab button which displays the main activity
        onView(withId(R.id.fab)).perform(click())

        // select data in the course spinner
        val course = DataManager.courses["android_async"]
        onView(withId(R.id.spinner_courses)).perform(click())
        onData(allOf(instanceOf(CourseInfo::class.java), equalTo(course)))
            .perform(click())

        // type values into the edit text
        onView(withId(R.id.editTextNoteTitle)).perform(typeText(noteTitle))
        onView(withId(R.id.editTextNoteText)).perform(typeText(noteText), closeSoftKeyboard())

        pressBack()

        val newNote = DataManager.notes.last()
        assertEquals(course, newNote.course)
        assertEquals(noteTitle, newNote.title)
        assertEquals(noteText, newNote.text)
    }

}