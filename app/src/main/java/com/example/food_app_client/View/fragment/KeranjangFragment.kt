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
import com.example.food_app_client.Model.Adapter.AdapterMenuPesan
import com.example.food_app_client.Model.Adapter.AdapterStatusPesanan
import com.example.food_app_client.Model.ListLokal.listpesanan
import com.example.food_app_client.Model.ListLokal.liststatus
import com.example.food_app_client.R
import com.example.food_app_client.ViewModel.KeranjangViewModel
import com.example.food_app_client.ViewModel.UserViewModel

class KeranjangFragment : Fragment() {
    lateinit var adapter: AdapterMenuPesan
    lateinit var recyclerView: RecyclerView
    private lateinit var tvNama: TextView
    private lateinit var tvAlamat: TextView
    private lateinit var tvBiayaMakanan: TextView
    private lateinit var tvBiayaAntar: TextView
    private lateinit var tvTotalBiaya: TextView
    private lateinit var btnCheckout: ImageView
    private val userViewModel: UserViewModel by activityViewModels()
    private val keranjangViewModel: KeranjangViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keranjang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvNama = view.findViewById(R.id.tvNamaAtas)
        tvAlamat = view.findViewById(R.id.tvAlamatAtas)
        tvBiayaMakanan = view.findViewById(R.id.tvbMakanan)
        tvBiayaAntar = view.findViewById(R.id.tvbAntar)
        tvTotalBiaya = view.findViewById(R.id.tvTotal)
        btnCheckout = view.findViewById(R.id.ivCheckout)
        recyclerView = view.findViewById(R.id.rvMenuPemesanan)

        keranjangViewModel.hargaTotal.observe(viewLifecycleOwner){newValue ->
            tvBiayaMakanan.text = newValue.toString()
        }

        keranjangViewModel.hargaAkhir.observe(viewLifecycleOwner){hargaAkhir ->
            tvTotalBiaya.text = hargaAkhir.toString()
        }




        setInformasi(tvNama, tvAlamat)
        setKeranjang()
    }

    fun setInformasi(tvnama: TextView, tvalamat: TextView) {
        userViewModel.nama.observe(viewLifecycleOwner) {newValue ->
            tvnama.text = newValue
        }
        userViewModel.alamat.observe(viewLifecycleOwner) {newValue ->
            tvalamat.text = newValue
        }

    }

    fun setKeranjang(){
        keranjangViewModel.listKeranjang.observe(viewLifecycleOwner){newValue ->
            recyclerView.adapter = AdapterMenuPesan(newValue, requireContext(),keranjangViewModel,viewLifecycleOwner)
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}