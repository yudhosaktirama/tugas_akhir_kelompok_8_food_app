package com.example.food_app_client.Model.ModelClass

data class Status(
    val id: String = "test",
    val statusPemesanan: String = "Sedang dibuat",
    val nomorPemesanan: String = "Pesanan 1",
    val jumlahItem: String = "3 item",
    val list: List<Pesanan> = listOf()
)
