package com.example.food_app_client.Model.ModelClass

data class Status(
    val id: String = "test",
    val nama: String ="yudho",
    val statusPemesanan: String = "Sedang dibuat",
    val Email: String = "Pesanan 1",
    val Alamat: String = "3 item",
    val list: List<Pesanan> = listOf()
)
