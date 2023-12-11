package com.example.food_app_client.View.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_client.Model.Adapter.AdapterDetailPesanan
import com.example.food_app_client.R
import com.example.food_app_client.ViewModel.StatusViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailPesananFragment : Fragment() {
    lateinit var recylerView: RecyclerView
    lateinit var tvNamaPemesan: TextView
    lateinit var tvAlamatPemesan : TextView
    lateinit var tvTotalBiayaMakanan: TextView
    lateinit var tvTotalBiaya : TextView
    lateinit var btnBack: FloatingActionButton
    val statusViewModel: StatusViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_pesanan, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recylerView = view.findViewById(R.id.recylerDaftarPesanan)
        tvNamaPemesan = view.findViewById(R.id.tvNamaDetail)
        tvAlamatPemesan = view.findViewById(R.id.tvAlamatdetail)
        tvTotalBiayaMakanan = view.findViewById(R.id.tvTotalBayarDetail)
        tvTotalBiaya = view.findViewById(R.id.tvtotalBiayaDetail)
        btnBack = view.findViewById(R.id.floatingActionBackMenu)

        tvNamaPemesan.text = arguments!!.getString("nama")
        tvAlamatPemesan.text = arguments!!.getString("alamat")




        statusViewModel.listDaftarMakanan.observe(viewLifecycleOwner){newValue ->
            recylerView.layoutManager = LinearLayoutManager(requireContext())
            recylerView.adapter = AdapterDetailPesanan(newValue)
            tvTotalBiayaMakanan.text = "Rp. ${statusViewModel.totalBayar.value.toString()}"
            tvTotalBiaya.text = "Rp. ${(statusViewModel.totalBayar.value?.plus(5000)).toString()}"

        }

        btnBack.setOnClickListener {
            val transaksi = requireActivity().supportFragmentManager.beginTransaction()
            val toStatus = ProfileFragment()
            transaksi.replace(R.id.fragmentContainerView2,toStatus)
            transaksi.addToBackStack(null)
            transaksi.commit()
        }

    }
}