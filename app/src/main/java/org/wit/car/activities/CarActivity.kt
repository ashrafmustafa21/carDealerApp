package org.wit.car.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_car.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.car.R
import org.wit.car.helpers.readImage
import org.wit.car.helpers.readImageFromPath
import org.wit.car.helpers.showImagePicker
import org.wit.car.main.MainApp
import org.wit.car.models.CarModel
import org.wit.car.models.Location

class CarActivity : AppCompatActivity(), AnkoLogger {

    // class members
    val IMAGE_REQUEST = 1
    var car = CarModel()
    lateinit var app: MainApp
    val LOCATION_REQUEST = 2
    var edit = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        info("Car App started..")

        app = application as MainApp


        if (intent.hasExtra("car_edit")) {
            edit = true
            car = intent.extras?.getParcelable<CarModel>("car_edit")!!
            carMake.setText(car.make)
            model.setText(car.model)
            year.setText(car.year)
            carImage.setImageBitmap(readImageFromPath(this, car.image))
            if (car.image != null) {
                chooseImage.setText(R.string.change_car_image)
            }
            btnAdd.setText(R.string.save_car)
        }

        btnAdd.setOnClickListener() {
            car.make = carMake.text.toString()
            car.model = model.text.toString()
            car.year = year.text.toString()
            if (car.make.isEmpty()) {
                toast(R.string.enter_car_make)
            } else {
                if (edit) {
                    app.cars.update(car.copy())
                } else {
                    app.cars.create(car.copy())
                }
            }
            info("add Button Pressed: $carMake")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

        carLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (car.zoom != 0f) {
                location.lat =  car.lat
                location.lng = car.lng
                location.zoom = car.zoom
            }
            startActivityForResult(intentFor<MapActivity>().putExtra("location", location), LOCATION_REQUEST)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_car, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                app.cars.delete(car)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    car.image = data.getData().toString()
                    carImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_car_image)
                }
            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras?.getParcelable<Location>("location")!!
                    car.lat = location.lat
                    car.lng = location.lng
                    car.zoom = location.zoom
                }
            }
        }
    }
}