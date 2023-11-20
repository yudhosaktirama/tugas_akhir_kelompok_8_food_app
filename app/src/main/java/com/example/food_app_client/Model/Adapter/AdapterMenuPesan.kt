package com.example.food_app_client.Model.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_client.Model.ModelClass.Pesanan
import com.example.food_app_client.R
import com.example.food_app_client.ViewModel.KeranjangViewModel
import com.google.android.gms.common.api.internal.LifecycleActivity

class AdapterMenuPesan(val list: List<Pesanan>, val konteks: Context, val keranjangViewModel: KeranjangViewModel,val lifecycle: LifecycleOwner) : RecyclerView.Adapter<AdapterMenuPesan.PesananViewHolder>() {
    class PesananViewHolder(row: View): RecyclerView.ViewHolder(row) {
        val iconMakanan = row.findViewById<ImageView>(R.id.ivMakanan)
        val counter = row.findViewById<TextView>(R.id.tvJumlah)
        val hargaMakanan = row.findViewById<TextView>(R.id.tvHarga)
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
        val binding = list[position]
        holder.iconMakanan.setImageResource(binding.iconMakanan)
        holder.namaMakanan.text = binding.namaMakanan
        keranjangViewModel.counterList.observe(lifecycle){newValue ->
            holder.counter.text = newValue[position].counter.toString()
            holder.hargaMakanan.text = newValue[position].harga.toString()
        }

        holder.btnPlus.setOnClickListener {
            keranjangViewModel.incrementList(position,binding.hargaSatuan)
            notifyDataSetChanged()
        }
        holder.btnMinus.setOnClickListener {
            keranjangViewModel.decrementList(position,konteks,binding.hargaSatuan)
            notifyDataSetChanged()
        }


    }
}