package com.example.food_app_client.View.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.food_app_client.Model.ListLokal.listpesanan
import com.example.food_app_client.R
import com.example.food_app_client.ViewModel.KeranjangViewModel

class DetailFragment : Fragment() {
    lateinit var tvDeskripsi : TextView
    lateinit var tvLamaMemasak : TextView
    lateinit var tvPopularitas : TextView
    lateinit var tvCounter : TextView
    lateinit var tvHarga : TextView
    lateinit var btnIncrement: ImageView
    lateinit var btnDecrement : ImageView
    lateinit var btnTambahKeranjang : ImageView
    lateinit var gambarLandScape: ImageView
    lateinit var btnCancel : ImageView
    val keranjangViewModel : KeranjangViewModel by activityViewModels()
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
        btnIncrement = view.findViewById(R.id.btnIncrement)
        gambarLandScape = view.findViewById(R.id.gambarMakananLandscapeDetail)
        btnDecrement = view.findViewById(R.id.btnDecremenet)
        btnTambahKeranjang = view.findViewById(R.id.btnTambahMakanan)
        btnCancel = view.findViewById(R.id.btnCancel)

        keranjangViewModel.resetCounter()


        val harga =arguments?.getInt("harga")
        val namaMakanan = arguments?.getString("nama")
        val gambar = arguments?.getString("gambar")
        val deskripsi = arguments?.getString("deskripsi")
        val durasi = arguments?.getString("durasi")
        val populer = arguments?.getString("populer")

        Glide.with(requireContext()).load(gambar).centerCrop().placeholder(R.drawable.makanan).into(gambarLandScape)
        setHarga(harga!!)
        setCounter(harga)
        tambahMakananKeranjang(namaMakanan!!,gambar!!, harga)




        tvDeskripsi.text = deskripsi
        tvLamaMemasak.text = durasi
        tvPopularitas.text = populer

        backToKategori()


    }

    fun setCounter(harga: Int){
        btnIncrement.setOnClickListener {
            keranjangViewModel.Increment()
            setHarga(harga)
        }

        btnDecrement.setOnClickListener {
            keranjangViewModel.Decrement(requireContext())
            setHarga(harga)
        }

        keranjangViewModel.counter.observe(viewLifecycleOwner){newValue ->
            tvCounter.text = newValue.toString()
        }

    }

    @SuppressLint("SetTextI18n")
    fun setHarga(hargaSatuan: Int){
        keranjangViewModel.setHarga(hargaSatuan)
        keranjangViewModel.harga.observe(viewLifecycleOwner){newValue ->
            tvHarga.text = "Rp. $newValue"
        }
    }

    fun tambahMakananKeranjang(nama: String,gambar: String,hargaSatuan: Int){
        btnTambahKeranjang.setOnClickListener {
            keranjangViewModel.tambahMakananKeKeranjang(nama,gambar,hargaSatuan)
            keranjangViewModel.setHargaTotal()
            Toast.makeText(requireContext(), "Berhasil Menambahkan Makanan ke Keranjang", Toast.LENGTH_SHORT).show()
        }
    }

    fun backToKategori(){
        btnCancel.setOnClickListener {
            val transaksi = requireActivity().supportFragmentManager.beginTransaction()
            val toKategori = KategoriFragment()
            transaksi.replace(R.id.fragmentContainerView2,toKategori)
            transaksi.addToBackStack(null)
            transaksi.commit()
        }
    }

}