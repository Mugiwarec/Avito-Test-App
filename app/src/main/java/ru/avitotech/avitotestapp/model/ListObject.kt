package ru.avitotech.avitotestapp.model

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

object ListObject {

    private const val elementsCount = 1
    private const val longTimeMillis: Long = 5000
    private var pool: MutableList<DataNumber> = mutableListOf()

    var list: MutableLiveData<MutableList<DataNumber>> = MutableLiveData()
        private set

    init {
        list.value = (1..elementsCount).map { DataNumber(number = it) }.toMutableList()

        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(longTimeMillis)

                if (pool.isNotEmpty()) {
                    addElementToList(pool.first())
                    pool.removeFirst()
                }
                else
                    list.value?.let {
                        addElementToList(DataNumber(number = it.size.plus(1)))
                    }

                Timber.i(list.value.toString())
            }
        }
    }

    private fun addElementToList(element: DataNumber) {
        val newValue: MutableList<DataNumber> = mutableListOf()

        list.value?.let { newValue.addAll(it) }

        val randomIndex = (0..newValue.size).random()
        newValue.add(randomIndex, element)

        list.postValue(newValue)
    }

    fun deleteElementToList(element: DataNumber) {
        val newValue: MutableList<DataNumber> = mutableListOf()

        list.value?.let { newValue.addAll(it) }

        newValue.remove(element)

        list.postValue(newValue)

        pool.add(element)

        pool.sortBy {
            it.number
        }
    }
}