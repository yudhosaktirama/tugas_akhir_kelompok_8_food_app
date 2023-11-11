package com.example.food_app_client.Model.ModelClass

import com.example.food_app_client.R

data class Menu(val namaMakanan: String = "Nasi Goreng",
    val deskripsi: String = "Test",
    val lamaMemasak:String =  "15 Menit",
    val harga: Int= 5000,
val gambar: Int = R.drawable.makanan,
val popularitas: String = "Biasa saja")
