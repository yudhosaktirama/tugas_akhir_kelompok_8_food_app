package com.example.food_app_client.Model.ModelClass

import com.example.food_app_client.R

data class Pesanan(
    val namaMakanan: String = "Nasi Uduk",
    var harga: Int = 5000,
    var jumlah: Int = 1,
    val iconMakanan: Int = R.drawable.makanan,
    val hargaSatuan : Int = 1000,
)
