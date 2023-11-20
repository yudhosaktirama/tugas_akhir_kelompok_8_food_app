package com.example.food_app_client.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_app_client.Model.ListLokal.listCounter
import com.example.food_app_client.Model.ListLokal.listpesanan
import com.example.food_app_client.Model.ModelClass.Pesanan

class KeranjangViewModel : ViewModel() {
    private var _counter: MutableLiveData<Int> = MutableLiveData(0)
    private var _harga : MutableLiveData<Int> = MutableLiveData(0)
    private var _listKeranjang : MutableLiveData<MutableList<Pesanan>> = MutableLiveData(listpesanan)
    private var _counterList: MutableLiveData<MutableList<Int>> = MutableLiveData(listCounter)

    val counter:LiveData<Int>
        get() = _counter

    val harga: LiveData<Int>
        get() = _harga

    val listKeranjang: LiveData<MutableList<Pesanan>>
        get() = _listKeranjang

    val counterList:  LiveData<MutableList<Int>>
        get() = _counterList


    fun Increment(){
        _counter.value = _counter.value!! + 1
    }

    fun Decrement(konteks : Context){
        if (_counter.value!! <= 0){
            _counter.value = _counter.value!! + 0
            Toast.makeText(konteks, "Tidak bisa mengurangi lagi ", Toast.LENGTH_SHORT).show()
        }else{
            _counter.value = _counter.value!! - 1
        }

    }

    fun setHarga(hargaMakananSatuan : Int){
        _harga.value = hargaMakananSatuan * _counter.value!!

    }

    fun resetCounter(){
        _counter.value = 0
    }

    fun tambahMakananKeKeranjang(namaMakanan: String,gambarMakanan: Int){
       listpesanan.add(
           Pesanan(
           namaMakanan, _harga.value!!,_counter.value!!
       )
       )
    }

    fun incrementList(posisi:Int){
        _counterList.value!![posisi]+=1
    }
    fun decrementList(posisi:Int,konteks: Context){
        if (_counterList.value!![posisi] == 1){
            Toast.makeText(konteks, "Tidak Bisa dikurangi", Toast.LENGTH_SHORT).show()
        }else{
            _counterList.value!![posisi]-=1
        }

    }

    fun setCounterList(list: List<Pesanan>){
        if (listCounter.size != list.size){
            listCounter.clear()
            for (i in list){
                listCounter.add(i.jumlah)
            }
        }
    }
}