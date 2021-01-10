package ru.avitotech.avitotestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.avitotech.avitotestapp.model.DataNumber
import ru.avitotech.avitotestapp.model.ListObject

class ListViewModel : ViewModel() {

    var dataList = ListObject.list
        private set

    fun deleteElementToList(element: DataNumber) {
        viewModelScope.launch {
            ListObject.deleteElementToList(element)
        }
    }
}