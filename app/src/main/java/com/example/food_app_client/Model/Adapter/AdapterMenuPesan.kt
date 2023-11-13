package com.example.food_app_client.Model.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_client.Model.ModelClass.Pesanan
import com.example.food_app_client.R

class AdapterMenuPesan(val list: List<Pesanan>, val konteks: Context) : RecyclerView.Adapter<AdapterMenuPesan.PesananViewHolder>() {
    class PesananViewHolder(row: View): RecyclerView.ViewHolder(row) {
        val iconMakanan = row.findViewById<ImageView>(R.id.ivMakanan)
        val namaMakanan = row.findViewById<TextView>(R.id.tvNamaMakanan)
        val btnMinus = row.findViewById<ImageView>(R.id.ivMinus)
        val btnPlus = row.findViewById<ImageView>(R.id.ivPlus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {
        val layput = LayoutInflater.from(parent.context).inflate(R.layout.layout_menu_keranjang, parent, false)
        return PesananViewHolder(layput)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
        val binding = list[position]
        holder.iconMakanan.setImageResource(binding.iconMakanan)
        holder.namaMakanan.text = binding.namaMakanan
    }
}