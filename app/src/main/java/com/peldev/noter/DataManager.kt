package com.peldev.noter

class DataManager {

    // first parameter is the key
    // second parameter if the value to be stored
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intent", "Android Programming with intent")
        courses[course.courseId] = course

        course = CourseInfo(courseId = "android_async", title = "Android Asyn Programming and Services")
        courses[course.courseId] = course

        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses[course.courseId] = course

        course = CourseInfo("java_core", "Java Fundamentals: The core Platform")
        courses[course.courseId] = course
    }

}