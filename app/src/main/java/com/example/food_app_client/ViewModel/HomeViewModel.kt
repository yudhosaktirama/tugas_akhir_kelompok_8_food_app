package com.example.food_app_client.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_app_client.Model.ListLokal.test
import com.example.food_app_client.Model.ModelClass.Menu

class HomeViewModel : ViewModel() {
    var _listMenuPopuler : MutableLiveData<MutableList<Menu>> = MutableLiveData(test)

    val listMenuPopuler: LiveData<MutableList<Menu>>
        get() = _listMenuPopuler






}