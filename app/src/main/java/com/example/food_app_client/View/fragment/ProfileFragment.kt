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
import com.example.food_app_client.Model.ModelClass.Status
import com.example.food_app_client.R
import com.example.food_app_client.ViewModel.StatusViewModel
import com.example.food_app_client.ViewModel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await


class ProfileFragment : Fragment() {
    lateinit var adapter: AdapterStatusPesanan
    lateinit var recyclerView: RecyclerView
    private lateinit var tvNama: TextView
    private lateinit var tvAlamat: TextView
    private lateinit var tvNoHp : TextView
    private lateinit var tvNamaTop: TextView
    private lateinit var tvAlamatTop: TextView
    private lateinit var tvEmail: TextView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseFirestore: FirebaseFirestore
    private val userViewModel: UserViewModel by activityViewModels()
    private val statusViewModel: StatusViewModel by activityViewModels()
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
        tvEmail = view.findViewById(R.id.tvEmail)
        firebaseFirestore = FirebaseFirestore.getInstance()

        firebaseAuth = FirebaseAuth.getInstance()

        tvEmail.text = firebaseAuth.currentUser!!.email


        setInformasi(tvNama,tvAlamat,tvNoHp,tvNamaTop,tvAlamatTop)
        GlobalScope.launch { getDataFromFirestore() }

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
        statusViewModel.listStatusPesanan.observe(viewLifecycleOwner){newValue ->
            recyclerView.adapter = AdapterStatusPesanan(newValue, requireContext())
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        }


    }

    suspend fun getDataFromFirestore() {
        try {
            val document = firebaseFirestore.collection("pesanan").get().await()

            withContext(Dispatchers.IO) {
                document?.let { documents ->
                    val listStatusPesanan = documents.map { dc ->
                        Status(dc.getString("status") ?: "",
                            dc.getString("email")?:"",
                            dc.getString("alamat")?:"")
                    }

                    // Gunakan postValue untuk mengupdate LiveData di dalam wadah ViewModel
                    statusViewModel._listStatusPesanan.postValue(listStatusPesanan.toMutableList())
                }
            }
        } catch (e: Exception) {
            // Handle exception, misalnya logging atau menampilkan pesan kesalahan
            e.printStackTrace()
        }
    }

}