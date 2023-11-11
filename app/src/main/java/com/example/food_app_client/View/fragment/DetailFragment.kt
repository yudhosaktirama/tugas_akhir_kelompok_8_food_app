package com.example.food_app_client.View.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.food_app_client.R

class DetailFragment : Fragment() {
    lateinit var tvDeskripsi : TextView
    lateinit var tvLamaMemasak : TextView
    lateinit var tvPopularitas : TextView
    lateinit var tvCounter : TextView
    lateinit var tvHarga : TextView
    lateinit var btnTambahKeranjang : ImageView
    lateinit var btnCancel : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvDeskripsi = view.findViewById(R.id.tvDeskripsi)
        tvLamaMemasak = view.findViewById(R.id.tvDurasi)
        tvPopularitas = view.findViewById(R.id.tvPopuler)
        tvCounter = view.findViewById(R.id.tvCounter)
        tvHarga = view.findViewById(R.id.tvhargaMakanan)
        btnTambahKeranjang = view.findViewById(R.id.btnTambahMakanan)
        btnCancel = view.findViewById(R.id.btnCancel)

    }
}