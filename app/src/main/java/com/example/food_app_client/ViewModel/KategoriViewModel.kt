package com.example.food_app_client.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_app_client.Model.ListLokal.listmakanan
import com.example.food_app_client.Model.ModelClass.Menu

class KategoriViewModel : ViewModel() {
   var _listMakanan: MutableLiveData<List<Menu>> = MutableLiveData(listmakanan)

    val listMakananku:LiveData<List<Menu>>
        get() = _listMakanan


    fun setMenu(menuBaru: List<Menu>){
        _listMakanan.value = menuBaru
    }
}