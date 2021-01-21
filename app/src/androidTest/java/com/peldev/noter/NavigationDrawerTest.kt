package com.peldev.noter

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import kotlinx.coroutines.channels.consumesAll

@RunWith(AndroidJUnit4::class)
class NavigationDrawerTest {

    @Rule @JvmField
    val noteListActivity =
        ActivityScenarioRule(ListItemsActivity::class.java)

    @Test
    fun selectNoteAfterNavDrawerChange() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        onView(withId(R.id.nav_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_courses))

        val coursePosition = 0

        onView(withId(R.id.list_items))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<CourseRecyclerAdapter.ViewHolder>(coursePosition, click()))

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        onView(withId(R.id.nav_view))
            .perform(NavigationViewActions.navigateTo(R.id.nav_notes))

        val notePosition = 0

        onView(withId(R.id.list_items))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<NoteRecyclerAdapter.ViewHolder>(notePosition, click()))

        val note = DataManager.notes[notePosition]

        onView(withId(R.id.spinner_courses))
            .check(matches(withSpinnerText(containsString(note.course?.title))))

        onView(withId(R.id.editTextNoteTitle))
            .check(matches(withText(containsString(note.title))))

        onView(withId(R.id.editTextNoteText))
            .check(matches(withText(containsString(note.text))))

    }

}