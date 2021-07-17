package com.example.corona

import androidx.multidex.MultiDexApplication
import com.example.corona.di.AppComponent
import com.example.corona.di.DaggerAppComponent

class MyApplication : MultiDexApplication() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}