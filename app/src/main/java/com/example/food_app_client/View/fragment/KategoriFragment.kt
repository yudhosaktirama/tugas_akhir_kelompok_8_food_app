package com.example.food_app_client.View.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
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
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await


class KategoriFragment : Fragment() {
    lateinit var recylerKategori: RecyclerView
    lateinit var btnKategoriMakanan: ImageView
    lateinit var btnKategoriMinuman : ImageView
    lateinit var btnKategoriDessert : ImageView
    lateinit var profile: ImageView
    lateinit var textNama: TextView
    lateinit var textAlamat: TextView
    lateinit var  firestore: FirebaseFirestore
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

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recylerKategori = view.findViewById(R.id.recycleKategori)
        btnKategoriDessert = view.findViewById(R.id.kategoriDessert)
        btnKategoriMakanan = view.findViewById(R.id.kategoriMakanan)
        btnKategoriMinuman = view.findViewById(R.id.kategoriMinuman)
        textNama = view.findViewById(R.id.tvNamaOrang)
        textAlamat = view.findViewById(R.id.tvAlamat)
        profile = view.findViewById(R.id.ivProfileKategori)
        firestore = FirebaseFirestore.getInstance()

        profile.setImageResource(R.drawable.profile)

        GlobalScope.launch { getMenuFirestore("Makanan") }

        setNamaAlamat(textNama,textAlamat)

        btnKategoriMinuman.setOnClickListener {
            setListKategori("Minuman")
        }

        btnKategoriMakanan.setOnClickListener {
            setListKategori("Makanan")
        }

        btnKategoriDessert.setOnClickListener {
            setListKategori("Dessert")
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

    @OptIn(DelicateCoroutinesApi::class)
    fun setListKategori(tipe: String){
        GlobalScope.launch {getMenuFirestore(tipe)  }
    }

    fun pantauPerubahanList(){
        kategoriViewModel.listMakananku.observe(viewLifecycleOwner){newValue ->
            recylerKategori.adapter = AdapterKategori(newValue,requireActivity().supportFragmentManager,requireContext())
            recylerKategori.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    suspend fun getMenuFirestore(tipe: String){
        val database = firestore.collection("makanan").whereEqualTo("kategori",tipe
        ).get().await()
        withContext(Dispatchers.IO){
            database?.let {document ->
                val listMakanan = document.map {
                    com.example.food_app_client.Model.ModelClass.Menu(
                        it.getString("nama")?: "",
                        it.getString("deskripsi")?: "",
                        it.getString("lamaMemasak")?: "",
                        (it.get("harga") as? Number)!!.toInt()?: 0,
                        it.getString("gambar")?: "https://storage.googleapis.com/proudcity/mebanenc/uploads/2021/03/placeholder-image.png",


                    )
                }

                kategoriViewModel._listMakanan.postValue(listMakanan.toMutableList())



            }
        }
    }
}