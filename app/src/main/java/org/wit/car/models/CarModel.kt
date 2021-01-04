package org.wit.car.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


// Parcelable interface

@Parcelize
data class CarModel(var id: Long = 0,
                          var make: String = "",
                          var model: String = "",
                          var year: String = "",
                          var image: String = "",
                          var lat : Double = 0.0,
                          var lng: Double = 0.0,
                          var zoom: Float = 0f) : Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable



