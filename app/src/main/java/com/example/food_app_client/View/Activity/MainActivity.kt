package com.example.food_app_client.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.food_app_client.R
import com.example.food_app_client.View.fragment.HomeFragment
import com.example.food_app_client.View.fragment.KategoriFragment
import com.example.food_app_client.View.fragment.KeranjangFragment
import com.example.food_app_client.View.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentHome = HomeFragment()
        val kategoriFragment = KategoriFragment()
        val keranjangFragment = KeranjangFragment()
        val profileFragment = ProfileFragment()

        bottomNav = findViewById(R.id.bottomNavigationView)

        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeid -> pindahFragment(fragmentHome)
                R.id.kategoriid -> pindahFragment(kategoriFragment)
                R.id.keranjangid -> pindahFragment(keranjangFragment)
                R.id.profileid -> pindahFragment(profileFragment)

                else -> {
                    val gagal= Intent(this, MainActivity::class.java)
                    startActivity(gagal)
                }

            }
            true
        }




    }

    fun pindahFragment(fragment: Fragment){
        val fragmentManager =supportFragmentManager
        val transaksi  = fragmentManager.beginTransaction()
        transaksi.replace(R.id.fragmentContainerView2,fragment)
        transaksi.commit()
    }
}