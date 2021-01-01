package org.wit.car.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class CarMemStore : CarStore, AnkoLogger {

    val cars = ArrayList<CarModel>()

    override fun findAll(): List<CarModel> {
        return cars
    }

    override fun create(car: CarModel) {
        car.id = getId()
        cars.add(car)
        logAll()
    }

    override fun update(car: CarModel) {
        var foundCar: CarModel? = cars.find { p -> p.id == car.id }
        if (foundCar != null) {
            foundCar.title = car.title
            foundCar.description = car.description
            logAll()
        }
    }

    //  log all cars list, this to be called whenever a new car is added
    fun logAll() {
        cars.forEach { info("${it}") }
    }
}
