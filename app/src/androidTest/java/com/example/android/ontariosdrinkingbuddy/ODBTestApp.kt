package com.example.android.ontariosdrinkingbuddy

import com.example.android.ontariosdrinkingbuddy.di.DaggerTestAppComponent

class ODBTestApp : ODBApp() {

    var baseUrl = ""

    override var appComponent = DaggerTestAppComponent.builder()
        .application(this)
        .build()

}
