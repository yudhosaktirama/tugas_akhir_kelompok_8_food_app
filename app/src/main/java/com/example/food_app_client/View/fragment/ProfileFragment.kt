package com.example.food_app_client.View.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_client.Model.Adapter.AdapterStatusPesanan
import com.example.food_app_client.Model.ListLokal.liststatus
import com.example.food_app_client.R
import com.example.food_app_client.ViewModel.UserViewModel


class ProfileFragment : Fragment() {
    lateinit var adapter: AdapterStatusPesanan
    lateinit var recyclerView: RecyclerView
    private lateinit var tvNama: TextView
    private lateinit var tvAlamat: TextView
    private lateinit var tvNoHp : TextView
    private lateinit var tvNamaTop: TextView
    private lateinit var tvAlamatTop: TextView
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvNama = view.findViewById(R.id.tvNama)
        tvAlamat = view.findViewById(R.id.tvAlamatProfile)
        tvNoHp = view.findViewById(R.id.tvNohp)
        tvNamaTop = view.findViewById(R.id.tvNamaOrang)
        tvAlamatTop = view.findViewById(R.id.tvAlamat)
        recyclerView = view.findViewById(R.id.rvStatusPemesanan)

        setInformasi(tvNama,tvAlamat,tvNoHp,tvNamaTop,tvAlamatTop)

    }

    fun setInformasi(tvnama: TextView,tvalamat: TextView,tvnoHp: TextView,tvtopNama: TextView,tvTopAlamat: TextView){
        userViewModel.nama.observe(viewLifecycleOwner) {newValue ->
            tvnama.text = newValue
            tvtopNama.text = newValue
        }
        userViewModel.alamat.observe(viewLifecycleOwner){newValue ->
            tvalamat.text = newValue
            tvTopAlamat.text = newValue
        }
        userViewModel.noHp.observe(viewLifecycleOwner){newValue ->
            tvnoHp.text = newValue
        }
        recyclerView.adapter = AdapterStatusPesanan(liststatus, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)

    }
}