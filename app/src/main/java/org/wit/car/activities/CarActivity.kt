package org.wit.car.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_car.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.car.R
import org.wit.car.main.MainApp
import org.wit.car.models.CarModel

class CarActivity : AppCompatActivity(), AnkoLogger {

    var car = CarModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        info("Car Activity started..")

        app = application as MainApp

        if (intent.hasExtra("car_edit")) {
            car = intent.extras?.getParcelable<CarModel>("car_edit")!!
            carTitle.setText(car.title)
            description.setText(car.description)
        }

        btnAdd.setOnClickListener() {
            car.title = carTitle.text.toString()
            car.description = description.text.toString()
            if (car.title.isNotEmpty()) {
                app.cars.create(car.copy())
                info("add Button Pressed: ${car}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast("Please Enter a title")
            }
        }
    }

    // Inflate menu resource and display on toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_car, menu)
        return super.onCreateOptionsMenu(menu)
    }


    // Handle cancel event
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}