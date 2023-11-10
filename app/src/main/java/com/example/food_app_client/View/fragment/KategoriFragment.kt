package com.example.food_app_client.View.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_client.Model.Adapter.AdapterKategori
import com.example.food_app_client.Model.ListLokal.listdessert
import com.example.food_app_client.Model.ListLokal.listmakanan
import com.example.food_app_client.Model.ListLokal.listminuman
import com.example.food_app_client.R
import com.example.food_app_client.ViewModel.KategoriViewModel
import com.example.food_app_client.ViewModel.UserViewModel


class KategoriFragment : Fragment() {
    lateinit var recylerKategori: RecyclerView
    lateinit var btnKategoriMakanan: ImageView
    lateinit var btnKategoriMinuman : ImageView
    lateinit var btnKategoriDessert : ImageView
    lateinit var textNama: TextView
    lateinit var textAlamat: TextView
    val userViewModel : UserViewModel by activityViewModels()
    val kategoriViewModel : KategoriViewModel by  activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kategori, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recylerKategori = view.findViewById(R.id.recycleKategori)
        btnKategoriDessert = view.findViewById(R.id.kategoriDessert)
        btnKategoriMakanan = view.findViewById(R.id.kategoriMakanan)
        btnKategoriMinuman = view.findViewById(R.id.kategoriMinuman)
        textNama = view.findViewById(R.id.tvNamaOrang)
        textAlamat = view.findViewById(R.id.tvAlamat)

        setNamaAlamat(textNama,textAlamat)

        btnKategoriMinuman.setOnClickListener {
            setListKategori(listminuman)
        }

        btnKategoriMakanan.setOnClickListener {
            setListKategori(listmakanan)
        }

        btnKategoriDessert.setOnClickListener {
            setListKategori(listdessert)
        }

        pantauPerubahanList()






    }

    fun setNamaAlamat(textNama: TextView, textAlamat: TextView){
        userViewModel.nama.observe(viewLifecycleOwner){newValue ->
            textNama.text = newValue
        }

        userViewModel.alamat.observe(viewLifecycleOwner){newValue ->
            textAlamat.text = newValue
        }
    }

    fun setListKategori(menuBarucuy: List<com.example.food_app_client.Model.ModelClass.Menu>){
        kategoriViewModel.setMenu(menuBarucuy)
    }

    fun pantauPerubahanList(){
        kategoriViewModel.listMakananku.observe(viewLifecycleOwner){newValue ->
            recylerKategori.adapter = AdapterKategori(newValue)
            recylerKategori.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}