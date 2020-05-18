package com.example.android.ontariosdrinkingbuddy

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.test.runner.AndroidJUnitRunner

class ODBTestRunner : AndroidJUnitRunner() {
    override fun onCreate(arguments: Bundle?) {
        // Override thread policy to use MockWebServer in tests.
//        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        super.onCreate(arguments)
    }

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        // Use [ODBTestApp] when running tests.
        return super.newApplication(cl, ODBTestApp::class.java.name, context)
    }
}