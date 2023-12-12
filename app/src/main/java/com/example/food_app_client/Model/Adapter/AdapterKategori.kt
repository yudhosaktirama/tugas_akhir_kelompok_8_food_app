package com.example.food_app_client.Model.Adapter



import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_app_client.Model.ModelClass.Menu
import com.example.food_app_client.R
import com.example.food_app_client.View.fragment.DetailFragment

class AdapterKategori (val listMenu: List<Menu>, val fragment: androidx.fragment.app.FragmentManager,val konteks: Context): RecyclerView.Adapter<AdapterKategori.KategoriViewHolder>() {

    class KategoriViewHolder(row: View): RecyclerView.ViewHolder(row){
        val gambar = row.findViewById<ImageView>(R.id.iconMakanan)
        val judulMakanan = row.findViewById<TextView>(R.id.judulMakanan)
        val btnDetail = row.findViewById<ImageView>(R.id.btnDetail)
        val btnAdd = row.findViewById<ImageView>(R.id.btnAdd)
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

        Glide.with(konteks).load(binding.gambar).centerCrop().placeholder(R.drawable.makanan).into(holder.gambar)
        holder.judulMakanan.text = binding.namaMakanan

        holder.btnDetail.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("deskripsi",binding.deskripsi)
            bundle.putString("durasi",binding.lamaMemasak)
            bundle.putString("nama",binding.namaMakanan)
            bundle.putString("gambar",binding.gambar)
            bundle.putString("populer",binding.popularitas)
            bundle.putInt("harga",binding.harga)
            val transaksi = fragment.beginTransaction()
            val fragmentDetail = DetailFragment()
            fragmentDetail.arguments = bundle
            transaksi.replace(R.id.fragmentContainerView2,fragmentDetail)
            transaksi.addToBackStack(null)
            transaksi.commit()
        }
    }
}