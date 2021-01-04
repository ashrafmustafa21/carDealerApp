package org.wit.car.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_car_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.car.R
import org.wit.car.main.MainApp
import org.wit.car.models.CarModel


class CarListActivity : AppCompatActivity(), CarListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_list)
        app = application as MainApp

        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadCars()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Start an activity to allow user add a new car
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<CarActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    // Pass a selected car when starting an activity
    override fun onCarClick(car: CarModel) {
        startActivityForResult(intentFor<CarActivity>().putExtra("car_edit", car), 0)
    }

    //Display the edit result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadCars()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadCars() {
        showCars(app.cars.findAll())
    }

    fun showCars (cars: List<CarModel>) {
        recyclerView.adapter = CarAdapter(cars, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}