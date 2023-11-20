package com.example.food_app_client.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_app_client.Model.ListLokal.listStatus
import com.example.food_app_client.Model.ModelClass.Status
import com.google.firebase.firestore.FirebaseFirestore

class StatusViewModel() : ViewModel() {
    val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var _listStatusPesanan: MutableLiveData<MutableList<Status>> = MutableLiveData(listStatus)

    val listStatusPesanan:LiveData<MutableList<Status>>
        get() = _listStatusPesanan

    fun getData(): MutableList<Status>{

        firebaseFirestore.collection("pesanan").get().addOnSuccessListener {
            for (document in it){
                _listStatusPesanan.value!!.add(Status(statusPemesanan = document["status"].toString(),
                    nomorPemesanan = document["email"].toString(),
                    jumlahItem = document["harga_total"].toString()))
            }
        }.addOnFailureListener {
            Log.e("error cuy",it.toString())
        }
        return _listStatusPesanan.value!!

    }


}