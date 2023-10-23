package com.example.food_app_client.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.food_app_client.R
import android.view.View
import android.widget.ImageView

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister: ImageView = findViewById(R.id.ivBtnRegister)
        val btnKembali: ImageView = findViewById(R.id.ivBtnKembali)

        btnRegister.setOnClickListener(this)
        btnKembali.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ivBtnRegister -> {
                val intentRegkeLog = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intentRegkeLog)
            }
            R.id.ivBtnKembali -> {
                val intentKemkeLog = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intentKemkeLog)
            }
        }
    }
}