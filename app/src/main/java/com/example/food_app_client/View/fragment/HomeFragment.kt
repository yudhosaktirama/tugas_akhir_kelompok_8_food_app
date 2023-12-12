package com.example.food_app_client.View.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_app_client.Model.Adapter.AdapterMenuPopuler
import com.example.food_app_client.Model.ListLokal.listmakanan
import com.example.food_app_client.Model.ModelClass.Menu
import com.example.food_app_client.R
import com.example.food_app_client.ViewModel.HomeViewModel
import com.example.food_app_client.ViewModel.KeranjangViewModel
import com.example.food_app_client.ViewModel.UserViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {
    private lateinit var adapter: AdapterMenuPopuler
    private lateinit var reycylerView: RecyclerView
    private lateinit var tvNama: TextView
    private lateinit var tvAlamat: TextView
    private lateinit var profileImage: ImageView
    private lateinit var konten1: ImageView
    private lateinit var konten2: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var firestore: FirebaseFirestore
    private  val keranjangViewModel: KeranjangViewModel by activityViewModels()
    private  val homeViewModel: HomeViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvAlamat = view.findViewById(R.id.tvAlamatUser)
        tvNama = view.findViewById(R.id.tvNamaUser)
        profileImage = view.findViewById(R.id.ivprofile)
        konten1 = view.findViewById(R.id.ivKonten1)
        konten2 = view.findViewById(R.id.ivKonten2)
        firestore = FirebaseFirestore.getInstance()
        reycylerView = view.findViewById(R.id.recyclerMenuPopuler)
        progressBar = view.findViewById(R.id.progressBarMenuPopuler)
        val namaUser = requireActivity().intent.getStringExtra("nama").toString()
        val alamatUser = requireActivity().intent.getStringExtra("alamat").toString()
        val noHpUSer = requireActivity().intent.getStringExtra("noHp").toString()
        profileImage.setImageResource(R.drawable.profile)

        userViewModel.setInformasiUser(namaUser,alamatUser,noHpUSer)

        Glide.with(requireContext()).load("https://awsimages.detik.net.id/community/media/visual/2020/07/06/nasi-padang.jpeg?w=600&q=90").centerCrop().placeholder(R.drawable.makanan).into(konten1)
        Glide.with(requireContext()).load("https://thumb.tvonenews.com/thumbnail/2022/08/07/62ef214a52e42-sate-dan-gule-balungan-wedus_665_374.jpg").centerCrop().placeholder(R.drawable.makanan).into(konten2)


        userViewModel.nama.observe(viewLifecycleOwner){newvalue ->
            tvNama.text = newvalue
        }

        userViewModel.alamat.observe(viewLifecycleOwner) {newValue ->
            tvAlamat.text = newValue
        }

        GlobalScope.launch { getDataMenuPopulerFirestore() }



        homeViewModel.listMenuPopuler.observe(viewLifecycleOwner){newValue ->
            reycylerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            reycylerView.adapter = AdapterMenuPopuler(newValue,requireContext(),requireActivity().supportFragmentManager,keranjangViewModel)
            if (homeViewModel.listMenuPopuler.value!!.size != 0){
                progressBar.visibility = View.GONE
            }


        }

    }

    suspend fun getDataMenuPopulerFirestore(){
        val database = firestore.collection("makanan").whereLessThan("harga",10000).get().await()
        withContext(Dispatchers.IO){
            database?.let { document ->
                val listMakananPopuler = document.map {doc ->
                    Menu(
                        doc.getString("nama")?: "",
                        doc.getString("deskripsi")?: "",
                        doc.getString("lamaMemasak")?: "",
                        (doc.get("harga") as? Number)?.toInt()?: 0,
                        doc.getString("gambar")?: ""
                    )
                }
                homeViewModel._listMenuPopuler.postValue(listMakananPopuler.toMutableList())

            }
        }
    }


}