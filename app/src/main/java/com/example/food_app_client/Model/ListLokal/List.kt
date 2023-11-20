package com.example.food_app_client.Model.ListLokal

import com.example.food_app_client.Model.ModelClass.Counter
import com.example.food_app_client.Model.ModelClass.Menu
import com.example.food_app_client.Model.ModelClass.Pesanan
import com.example.food_app_client.Model.ModelClass.Status

val listmakanan: List<Menu> = listOf(
    Menu("Nasi",),
    Menu("Nasi"),
    Menu("Nasi"),
    Menu("Nasi"),
    Menu("Nasi"),
)


val listminuman: List<Menu> = listOf(
    Menu("Es Teh",),
    Menu("Es Jeruk"),
    Menu("Es Coklat"),
    Menu("Marimas"),
    Menu("Kopi"),
)

val listdessert: List<Menu> = listOf(
    Menu("Kue",),
    Menu("Pudding"),
    Menu("Es krim"),
    Menu("Brownie"),
    Menu("Waffle"),
)

val liststatus: List<Status> = listOf(
    Status("Selesai", "Pesanan 1", "3 item"),
    Status("Selesai", "Pesanan 2", "1 item"),
    Status("Selesai", "Pesanan 3", "2 item"),
    Status("Selesai", "Pesanan 4", "4 item"),
    Status("Sedang diantar", "Pesanan 5", "5 item"),
)

val listpesanan: MutableList<Pesanan> = mutableListOf(
)

val listCounter: MutableList<Counter> = mutableListOf()

val listStatus: MutableList<Status> = mutableListOf()