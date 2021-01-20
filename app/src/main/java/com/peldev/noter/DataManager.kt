package com.peldev.noter

// singleton class
object DataManager {

    // first parameter is the key
    // second parameter if the value to be stored
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    fun addNote(course: CourseInfo, noteTitle: String, noteText: String): Int {
        val note = NoteInfo(course, noteTitle, noteText)
        notes.add(note)
        return notes.indexOf(note)
    }

    fun findNote(course: CourseInfo, noteTitle: String, noteText: String): NoteInfo? {
        for (note in notes)
            if (course == note.course
                && noteTitle == note.title
                && noteText == note.text
            )
                return note
        return null
    }

    fun initializeNotes() {
        var note = courses["android_intent"]?.let {
            NoteInfo(
                it, "Intents", "Intents can be used to navigate " +
                        "between activities"
            )
        }
        notes.add(note!!)

        note = courses.get("java_lang")?.let {
            NoteInfo(
                it, "Asynchronous Java",
                "Writing async code in java language"
            )
        }
        notes.add(note!!)

        note = courses.get("android_intent")?.let {
            NoteInfo(
                it,
                "Dynamic intent resolution",
                "Wow, intents allow components to be resolved at runtime"
            )
        }
        notes.add(note!!)

        note = courses.get("android_async")?.let {
            NoteInfo(
                it,
                "Service default threads",
                "Did you know that by default an Android Service will tie up the UI thread?"
            )
        }
        notes.add(note!!)

        note = courses.get("android_async")?.let {
            NoteInfo(
                it,
                "Long running operations",
                "Foreground Services can be tied to a notification icon"
            )
        }
        notes.add(note!!)

        note = courses.get("java_lang")?.let {
            NoteInfo(
                it, "Parameters", "Leverage variable-length parameter lists?"
            )
        }
        notes.add(note!!)

        note = courses.get("java_core")?.let {
            NoteInfo(
                it,
                "Compiler options",
                "The -jar option isn't compatible with with the -cp option"
            )
        }
        notes.add(note!!)

        note = courses.get("java_core")?.let {
            NoteInfo(
                it,
                "Serialization",
                "Remember to include SerialVersionUID to assure version compatibility"
            )
        }
        notes.add(note!!)

    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intent", "Android Programming with intent")
        courses[course.courseId] = course

        course =
            CourseInfo(courseId = "android_async", title = "Android Asyn Programming and Services")
        courses[course.courseId] = course

        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses[course.courseId] = course

        course = CourseInfo("java_core", "Java Fundamentals: The core Platform")
        courses[course.courseId] = course
    }

}