package com.example.food_app_client.Model.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_app_client.Model.ModelClass.Menu
import com.example.food_app_client.R
import com.example.food_app_client.View.fragment.DetailFragment
import com.example.food_app_client.ViewModel.KeranjangViewModel

class AdapterMenuPopuler(val list: List<Menu>,val konteks: Context,val fragment: FragmentManager,val viewmodel: KeranjangViewModel) : RecyclerView.Adapter<AdapterMenuPopuler.MenuViewHolder>() {

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

        Glide.with(konteks).load(binding.gambar).centerCrop().placeholder(R.drawable.makanan).into(holder.iconMakanan)
        holder.namaMakanan.text = binding.namaMakanan

        holder.btnDetail.setOnClickListener {
            val bundle = Bundle()
            val transaksi = fragment.beginTransaction()
            val toDetail = DetailFragment()
            bundle.putString("deskripsi",binding.deskripsi)
            bundle.putString("durasi",binding.lamaMemasak)
            bundle.putString("nama",binding.namaMakanan)
            bundle.putString("gambar",binding.gambar)
            bundle.putString("populer",binding.popularitas)
            bundle.putInt("harga",binding.harga)
            toDetail.arguments = bundle
            transaksi.replace(R.id.fragmentContainerView2,toDetail)
            transaksi.addToBackStack(null)
            transaksi.commit()
        }

        holder.btnAdd.setOnClickListener {
            viewmodel.addSingleFoodTOChart(binding.namaMakanan,binding.gambar,binding.harga)
            viewmodel.setHargaTotal()
            Toast.makeText(konteks, "Berhasil Menambahkan ${binding.namaMakanan} ke Keranjang", Toast.LENGTH_SHORT).show()
        }







    }
}