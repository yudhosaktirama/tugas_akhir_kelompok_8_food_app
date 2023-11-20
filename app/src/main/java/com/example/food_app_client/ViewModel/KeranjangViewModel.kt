package com.example.food_app_client.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_app_client.Model.ListLokal.listCounter
import com.example.food_app_client.Model.ListLokal.listpesanan
import com.example.food_app_client.Model.ModelClass.Counter
import com.example.food_app_client.Model.ModelClass.Pesanan

class KeranjangViewModel : ViewModel() {
    private var _counter: MutableLiveData<Int> = MutableLiveData(0)
    private var _harga : MutableLiveData<Int> = MutableLiveData(0)
    private var _listKeranjang : MutableLiveData<MutableList<Pesanan>> = MutableLiveData(listpesanan)
    private var _counterList: MutableLiveData<MutableList<Counter>> = MutableLiveData(listCounter)
    private var _hargaTotal: MutableLiveData<Int> = MutableLiveData(0)
    private var _hargaAkhir: MutableLiveData<Int> = MutableLiveData(0)

    val counter:LiveData<Int>
        get() = _counter

    val harga: LiveData<Int>
        get() = _harga

    val listKeranjang: LiveData<MutableList<Pesanan>>
        get() = _listKeranjang

    val counterList:  LiveData<MutableList<Counter>>
        get() = _counterList

    val hargaTotal: LiveData<Int>
        get() = _hargaTotal

    val hargaAkhir : LiveData<Int>
        get() = _hargaAkhir


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

    fun tambahMakananKeKeranjang(namaMakanan: String,gambarMakanan: Int,hargaSatuan: Int){
       listpesanan.add(
           Pesanan(
           namaMakanan, _harga.value!!,_counter.value!!,gambarMakanan,hargaSatuan
       )
       )

    }

    fun incrementList(posisi:Int,hargaSatuan: Int){
        _listKeranjang.value!![posisi].jumlah+=1
        _listKeranjang.value!![posisi].harga = _listKeranjang.value!![posisi].jumlah * hargaSatuan
        setHargaTotal()
    }
    fun decrementList(posisi:Int,konteks: Context,hargaSatuan: Int){
        if (_listKeranjang.value!![posisi].jumlah == 1){
            Toast.makeText(konteks, "Tidak Bisa dikurangi", Toast.LENGTH_SHORT).show()
        }else{
            _listKeranjang.value!![posisi].jumlah-=1
            _listKeranjang.value!![posisi].harga = _listKeranjang.value!![posisi].jumlah * hargaSatuan
            setHargaTotal()
        }

    }

    fun setHargaTotal(){
        _hargaTotal.value = _listKeranjang.value!!.sumOf {
            it.harga
        }
        _hargaAkhir.value = _hargaTotal.value!! + 5000
    }

    fun ClearList(){
        _listKeranjang.value!!.clear()

    }
}