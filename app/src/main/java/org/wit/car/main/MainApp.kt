package org.wit.car.main


import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.car.models.CarMemStore
import org.wit.car.models.CarModel

class MainApp : Application(), AnkoLogger {

//    val cars = ArrayList<CarModel>()
    val cars = CarMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Car app started")
    }
}