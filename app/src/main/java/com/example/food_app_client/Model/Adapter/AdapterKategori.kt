package com.example.food_app_client.Model.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_client.Model.ModelClass.Menu
import com.example.food_app_client.R

class AdapterKategori (val listMenu: List<Menu>): RecyclerView.Adapter<AdapterKategori.KategoriViewHolder>() {

    class KategoriViewHolder(row: View): RecyclerView.ViewHolder(row){
        val gambar = row.findViewById<ImageView>(R.id.iconMakanan)
        val judulMakanan = row.findViewById<TextView>(R.id.judulMakanan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.layout_menu_populer,parent,false)
        return  KategoriViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        val binding = listMenu[position]

        holder.gambar.setImageResource(binding.gambar)
        holder.judulMakanan.text = binding.namaMakanan
    }
}