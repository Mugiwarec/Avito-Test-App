package ru.avitotech.avitotestapp.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.avitotech.avitotestapp.model.DataNumber

@BindingAdapter("dataNumberInt")
fun TextView.setIntToString(item: DataNumber) {
    text = item.number.toString()
}