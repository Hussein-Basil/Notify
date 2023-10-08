package com.example.notify;

import android.app.Application
import com.example.notify.data.NoteData
import com.orhanobut.hawk.Hawk

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize global resources or perform setup tasks here.

        val notes: List<NoteData> = listOf(
            NoteData(
                "Meeting with Client",
                "Discuss project requirements and timeline. Prepare presentation.",
                "📆"
            ),
            NoteData(
                "Grocery List",
                "Milk, eggs, bread, apples, and chicken for dinner.",
                "🛒"
            ),
            NoteData(
                "To-Do List",
                "1. Finish coding feature A. 2. Send progress report to the team. 3. Call John for lunch plans.",
                "✅"
            )
        )

        Hawk.init(this).build()
        Hawk.put("notes", notes)
    }
}
