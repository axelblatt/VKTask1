package com.axelblatt.vktask1

import android.app.Application
import com.axelblatt.vktask1.network.data.AppContainer
import com.axelblatt.vktask1.network.data.DefaultAppContainer

class ProductsApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}