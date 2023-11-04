package com.example.food_app_client.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private var _alamat: MutableLiveData<String> = MutableLiveData<String>("")
    private var _nama : MutableLiveData<String> = MutableLiveData<String>("")
    private var _noHp: MutableLiveData<String> = MutableLiveData<String>("")

    val alamat:LiveData<String>
        get() = _alamat

    val nama:LiveData<String>
        get() = _nama

    val noHp: LiveData<String>
        get() = _noHp


    fun setInformasiUser(namaUser: String, alamatUser: String,noHp: String){
        _alamat.value = alamatUser
        _nama.value = namaUser
        _noHp.value = noHp
    }
    
}