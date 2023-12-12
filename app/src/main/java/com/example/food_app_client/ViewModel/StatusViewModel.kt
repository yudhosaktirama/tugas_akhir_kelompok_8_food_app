package com.example.food_app_client.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_app_client.Model.ListLokal.daftarPesanan
import com.example.food_app_client.Model.ListLokal.listStatus
import com.example.food_app_client.Model.ListLokal.listpesanan
import com.example.food_app_client.Model.ModelClass.Pesanan
import com.example.food_app_client.Model.ModelClass.Status
import com.google.firebase.firestore.FirebaseFirestore

class StatusViewModel() : ViewModel() {
    var _listStatusPesanan: MutableLiveData<MutableList<Status>> = MutableLiveData(listStatus)
    var _listDaftarMakanan: MutableLiveData<MutableList<Pesanan>> = MutableLiveData(daftarPesanan)

    var _totalbayar: MutableLiveData<Int> = MutableLiveData(0)

    val listStatusPesanan:LiveData<MutableList<Status>>
        get() = _listStatusPesanan

    val listDaftarMakanan: LiveData<MutableList<Pesanan>>
        get() = _listDaftarMakanan

    val totalBayar: LiveData<Int>
        get() = _totalbayar

    fun addDaftar(list: List<Pesanan>){
        _listDaftarMakanan.value!!.clear()
        for (i in list){
            _listDaftarMakanan.value!!.add(i)
        }


    }

    fun totalBiayaMakanan(list: List<Pesanan>){
        _totalbayar.value = 0
        for (i in list){
            _totalbayar.value = _totalbayar.value?.plus(i.jumlah*i.hargaSatuan)
        }
    }


}