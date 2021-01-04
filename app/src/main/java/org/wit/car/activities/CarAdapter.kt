package org.wit.car.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_car.view.*
import kotlinx.android.synthetic.main.card_car.view.*
import kotlinx.android.synthetic.main.card_car.view.carMake
import kotlinx.android.synthetic.main.card_car.view.model
import kotlinx.android.synthetic.main.card_car.view.year
import org.wit.car.R
import org.wit.car.helpers.readImageFromPath
import org.wit.car.models.CarModel


// Interface to represent click events on the cars' Card, and allow us to abstract the response to this event
interface CarListener {
    fun onCarClick(car: CarModel)
}

class CarAdapter constructor(
    private var cars: List<CarModel>,
    private val listener: CarListener
) : RecyclerView.Adapter<CarAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_car,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val car = cars[holder.adapterPosition]
        holder.bind(car, listener)
    }

    override fun getItemCount(): Int = cars.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(car: CarModel,  listener : CarListener) {
            itemView.carMake.text = car.make
            itemView.model.text = car.model
            itemView.year.text = car.year
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, car.image))
            itemView.setOnClickListener { listener.onCarClick(car) }
        }
    }
}