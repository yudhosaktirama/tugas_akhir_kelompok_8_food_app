package com.example.food_app_client.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KeranjangViewModel : ViewModel() {
    private var _counter: MutableLiveData<Int> = MutableLiveData(0)
    private var _harga : MutableLiveData<Int> = MutableLiveData(0)

    val counter:LiveData<Int>
        get() = _counter

    val harga: LiveData<Int>
        get() = _harga


    fun Increment(){
        _counter.value = _counter.value!! + 1
    }

    fun Decrement(){
        if (_counter.value!! <= 0){
            _counter.value = _counter.value!! + 0
        }else{
            _counter.value = _counter.value!! - 1
        }

    }

    fun setHarga(hargaMakananSatuan : Int){
        _harga.value = hargaMakananSatuan * _counter.value!!
    }
}