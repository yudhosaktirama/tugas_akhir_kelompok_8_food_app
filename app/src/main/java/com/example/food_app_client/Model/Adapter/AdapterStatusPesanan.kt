package com.example.food_app_client.Model.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_client.Model.ModelClass.Status
import com.example.food_app_client.R

class AdapterStatusPesanan(val list: List<Status>, val konteks: Context) : RecyclerView.Adapter<AdapterStatusPesanan.StatusViewHolder>() {
    class StatusViewHolder(row: View): RecyclerView.ViewHolder(row) {
        val statusPemesanan = row.findViewById<TextView>(R.id.tvStatusPemesanan)
        val nomorPemesanan = row.findViewById<TextView>(R.id.tvNomorPemesanan)
        val jumlahItem = row.findViewById<TextView>(R.id.tvJumlahItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val layput = LayoutInflater.from(parent.context).inflate(R.layout.layout_status_pemesanan, parent, false)
        return StatusViewHolder(layput)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val binding = list[position]

        holder.statusPemesanan.text = binding.statusPemesanan
        holder.nomorPemesanan.text = binding.nomorPemesanan
        holder.jumlahItem.text = binding.jumlahItem
    }
}