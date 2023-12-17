package com.example.food_app_client.View.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_client.Model.Adapter.AdapterMenuPesan
import com.example.food_app_client.Model.ListLokal.listdessert
import com.example.food_app_client.Model.ListLokal.listmakanan
import com.example.food_app_client.Model.ListLokal.listpesanan
import com.example.food_app_client.R
import com.example.food_app_client.View.Activity.MainActivity
import com.example.food_app_client.ViewModel.KeranjangViewModel
import com.example.food_app_client.ViewModel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.log

class KeranjangFragment : Fragment() {
    lateinit var adapter: AdapterMenuPesan
    lateinit var recyclerView: RecyclerView
    private lateinit var tvNama: TextView
    private lateinit var tvAlamat: TextView
    private lateinit var tvBiayaMakanan: TextView
    private lateinit var tvBiayaAntar: TextView
    private lateinit var tvTotalBiaya: TextView
    private lateinit var profile: ImageView
    private lateinit var btnClearKeranjang: ImageView
    private lateinit var btnCheckout: ImageView
    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth
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
        profile = view.findViewById(R.id.ivProfileKeranjang)
        btnClearKeranjang = view.findViewById(R.id.btnClearKeranjang)
        firestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        profile.setImageResource(R.drawable.profile)

        setInformasi(tvNama, tvAlamat)
        setKeranjang()

        keranjangViewModel.hargaTotal.observe(viewLifecycleOwner){newValue ->
            tvBiayaMakanan.text = newValue.toString()
        }

        keranjangViewModel.hargaAkhir.observe(viewLifecycleOwner){hargaAkhir ->
            tvTotalBiaya.text = hargaAkhir.toString()
        }


        btnCheckout.setOnClickListener {
            addToFirestoreDatabase(requireContext())
        }

        btnClearKeranjang.setOnClickListener {
            ClearKeranjang()
            setKeranjang()

        }





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
    fun addToFirestoreDatabase(konteks: Context){
        val data = hashMapOf(
            "nama" to userViewModel.nama.value,
            "alamat" to  userViewModel.alamat.value,
            "nomor" to  userViewModel.noHp.value,
            "status" to "Sedang dibuat",
            "email" to firebaseAuth.currentUser!!.email,
            "harga_total" to keranjangViewModel.hargaAkhir.value,
            "pesanan_user" to keranjangViewModel.listKeranjang.value
        )
        firestore.collection("pesanan").add(data).addOnFailureListener {
            Toast.makeText(konteks, "Gagal Menambah Pesanan", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener {
            Toast.makeText(konteks, "Berhasil Menambah Pesanan", Toast.LENGTH_SHORT).show()
            keranjangViewModel.ClearList()
            setKeranjang()
        }
    }

    fun ClearKeranjang (){
        keranjangViewModel.ClearList()
    }


}