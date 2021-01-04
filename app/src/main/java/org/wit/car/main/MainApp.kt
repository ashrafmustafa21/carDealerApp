package org.wit.car.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.car.models.CarJSONStore
import org.wit.car.models.CarStore

class MainApp : Application(), AnkoLogger {

    lateinit var cars: CarStore

    override fun onCreate() {
        super.onCreate()
        cars = CarJSONStore(applicationContext)
        info("MMW app started")
    }
}