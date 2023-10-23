package com.example.food_app_client.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.food_app_client.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin: ImageView = findViewById(R.id.ivLogin)
        val btnReg: ImageView = findViewById(R.id.ivregister)

        btnLogin.setOnClickListener(this)
        btnReg.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ivregister -> {
                val intentRegkeLog = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intentRegkeLog)
            }
            R.id.ivLogin -> {
                val intentKemkeLog = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intentKemkeLog)
            }
        }
    }
}