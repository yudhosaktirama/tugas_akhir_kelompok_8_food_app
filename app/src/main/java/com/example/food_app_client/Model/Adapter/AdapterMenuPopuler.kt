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

class AdapterMenuPopuler(val list: List<Menu>,val konteks: Context) : RecyclerView.Adapter<AdapterMenuPopuler.MenuViewHolder>() {

    class MenuViewHolder(row: View): RecyclerView.ViewHolder(row){
        val namaMakanan = row.findViewById<TextView>(R.id.judulMakanan)
        val iconMakanan = row.findViewById<ImageView>(R.id.iconMakanan)
        val btnDetail = row.findViewById<ImageView>(R.id.btnDetail)
        val btnAdd = row.findViewById<ImageView>(R.id.btnAdd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layput = LayoutInflater.from(parent.context).inflate(R.layout.layout_menu_populer,parent,false)
        return MenuViewHolder(layput)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val binding = list[position]

        holder.iconMakanan.setImageResource(binding.gambar)
        holder.namaMakanan.text = binding.namaMakanan

    }
}