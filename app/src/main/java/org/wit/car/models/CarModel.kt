package org.wit.car.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


// Parcelable interface
@Parcelize
data class CarModel(var id: Long = 0,
                    var title: String = "",
                    var description: String = "") : Parcelable

