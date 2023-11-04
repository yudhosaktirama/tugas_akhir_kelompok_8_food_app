package com.example.food_app_client.Model.ModelClass

import com.example.food_app_client.R

data class Pesanan(
    val namaMakanan: String = "Nasi Uduk",
    val harga: Int = 5000,
    val jumlah: Int = 1,
    val iconMakanan: Int = R.drawable.makanan
)
