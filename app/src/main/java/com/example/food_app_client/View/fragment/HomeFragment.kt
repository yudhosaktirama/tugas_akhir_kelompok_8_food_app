package com.example.food_app_client.View.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app_client.Model.AdapterMenuPopuler
import com.example.food_app_client.Model.ListLokal.listmakanan
import com.example.food_app_client.R


class HomeFragment : Fragment() {
    lateinit var adapter: AdapterMenuPopuler
    lateinit var reycylerView: RecyclerView

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
        reycylerView = view.findViewById(R.id.recyclerMenuPopuler)


        reycylerView.adapter = AdapterMenuPopuler(listmakanan,requireContext())

        reycylerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)


    }


}