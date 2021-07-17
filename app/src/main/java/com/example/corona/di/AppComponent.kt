package com.example.corona.di

import android.content.Context
import com.example.corona.ui.chart.ChartFragment
import com.example.corona.ui.home.HomeFragment
import com.example.corona.ui.search.SearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RestClientModule::class])
interface AppComponent {
    fun inject(fragment: HomeFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: ChartFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}