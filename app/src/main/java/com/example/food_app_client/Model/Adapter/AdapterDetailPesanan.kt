package com.example.food_app_client.Model.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_client.Model.ModelClass.Pesanan
import com.example.food_app_client.R
import kotlinx.coroutines.NonDisposableHandle.parent

class AdapterDetailPesanan(val list:List<Pesanan>): RecyclerView.Adapter<AdapterDetailPesanan.DetailPesananViewHolder>() {

    class DetailPesananViewHolder(baris: View):RecyclerView.ViewHolder(baris){
        val tvNamaMakanan = baris.findViewById<TextView>(R.id.tvNamaMakananDetail)
        val tvCounterMakanan = baris.findViewById<TextView>(R.id.tvCounterMakanan)
        val tvHargaSatuan = baris.findViewById<TextView>(R.id.tvHargaSatuanMakanan)
        val tvHargaTotalMakanan = baris.findViewById<TextView>(R.id.tvHargaTotalMakanan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPesananViewHolder {
       val layout = LayoutInflater.from(parent.context).inflate(R.layout.layout_daftar_pesanan,parent,false)
        return  DetailPesananViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: DetailPesananViewHolder, position: Int) {
        val binding = list[position]

        holder.tvNamaMakanan.text = binding.namaMakanan
        holder.tvCounterMakanan.text = binding.jumlah.toString()
        holder.tvHargaSatuan.text = binding.hargaSatuan.toString()
        holder.tvHargaTotalMakanan.text = binding.harga.toString()
    }
}