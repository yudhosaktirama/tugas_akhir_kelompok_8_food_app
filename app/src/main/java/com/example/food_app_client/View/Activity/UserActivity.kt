package com.example.food_app_client.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.food_app_client.R

class UserActivity : AppCompatActivity() {
    private lateinit var etNamaUser: EditText
    private lateinit var etAlamatUser: EditText
    private lateinit var etNoHpUser: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val iv5 = findViewById<ImageView>(R.id.imageView5)
        etNamaUser = findViewById(R.id.etNamaUser)
        etAlamatUser = findViewById(R.id.etAlamatUser)
        etNoHpUser = findViewById(R.id.etNoHpUser)



        iv5.setOnClickListener {
            if (checkCondition(etNamaUser,etAlamatUser,etNoHpUser)){
                val namaUser = etNamaUser.text.toString()
                val alamatUser = etAlamatUser.text.toString()
                val noHpUser = etNoHpUser.text.toString()

                val intent = Intent(this, MainActivity::class.java).apply {
                    this.putExtra("nama",namaUser)
                    this.putExtra("alamat",alamatUser)
                    this.putExtra("noHp",noHpUser)
                }
                startActivity(intent)
                finish()
            }else{
                return@setOnClickListener
            }


        }

    }

    fun  checkCondition(etNamaUser: EditText,etalamat: EditText,etNohp: EditText) : Boolean{
        if (etNamaUser.text.isEmpty() || etNohp.text.isEmpty() || etalamat.text.isEmpty()){
            Toast.makeText(this, "Semua Informasi Harus Diisi", Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }
    }
}