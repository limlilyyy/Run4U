package com.example.run4u

import android.app.Application
import com.google.firebase.FirebaseApp

class Run4UApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
    }
}
