package com.peldev.noter

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NextThroughNotesTest {

    @Rule
    @JvmField
    val noteListActivity = ActivityScenarioRule(NoteListActivity::class.java)

    @Test
    fun nextThroughNotes() {

        onData(
            allOf(
                instanceOf(NoteInfo::class.java),
                equalTo(DataManager.notes[0])
            )
        ).perform(click())

        for (index in 0..DataManager.notes.lastIndex) {
            val note = DataManager.notes[index]

            // check spinner has selected the right course
            onView(withId(R.id.spinner_courses)).check(
                matches(withSpinnerText(note.course?.title))
            )

            onView(withId(R.id.editTextNoteTitle)).check(
                matches(withText(note.title))
            )

            onView(withId(R.id.editTextNoteText)).check(
                matches(withText(note.text))
            )

            if (index != DataManager.notes.lastIndex)
                onView(allOf(withId(R.id.action_next), isEnabled())).perform(click())
        }

        // check if the next button has been disabled
        onView(withId(R.id.action_next)).check(matches(not(isEnabled())))
    }

}