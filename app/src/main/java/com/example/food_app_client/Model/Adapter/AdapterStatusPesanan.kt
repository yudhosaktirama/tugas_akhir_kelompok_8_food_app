package com.example.food_app_client.Model.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_client.Model.ModelClass.Status
import com.example.food_app_client.R
import com.example.food_app_client.View.fragment.DetailPesananFragment
import com.example.food_app_client.ViewModel.StatusViewModel

class AdapterStatusPesanan(val list: List<Status>, val konteks: Context,val fragment: FragmentManager,val viewmodel: StatusViewModel) : RecyclerView.Adapter<AdapterStatusPesanan.StatusViewHolder>() {
    class StatusViewHolder(row: View): RecyclerView.ViewHolder(row) {
        val statusPemesanan = row.findViewById<TextView>(R.id.tvStatusPemesanan)
        val nomorPemesanan = row.findViewById<TextView>(R.id.tvNomorPemesanan)
        val jumlahItem = row.findViewById<TextView>(R.id.tvJumlahItem)
        val layout = row.findViewById<ConstraintLayout>(R.id.row)
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
        holder.nomorPemesanan.text = binding.Email
        holder.jumlahItem.text = binding.Alamat

        holder.layout.setOnClickListener {
            viewmodel.addDaftar(binding.list)
            viewmodel.totalBiayaMakanan(binding.list)
            val transaksi = fragment.beginTransaction()
            val toDetail = DetailPesananFragment()
            val bundle = Bundle()
            bundle.putString("nama",binding.nama)
            bundle.putString("alamat",binding.Alamat)
            toDetail.arguments = bundle
            transaksi.replace(R.id.fragmentContainerView2,toDetail)
            transaksi.addToBackStack(null)
            transaksi.commit()
        }
    }
}