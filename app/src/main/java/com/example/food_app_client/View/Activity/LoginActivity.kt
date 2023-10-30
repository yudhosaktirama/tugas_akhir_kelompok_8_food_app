package com.example.food_app_client.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.example.food_app_client.R

class LoginActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var etPassword : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val ivRegister = findViewById<ImageView>(R.id.ivregister)
        val ivLogin = findViewById<ImageView>(R.id.ivLogin)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        ivRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        ivLogin.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)

            if (etEmail.text.isEmpty() ){
                etEmail.error = "Silahkan Masukkan Email"
                etEmail.requestFocus()
                return@setOnClickListener

            }else if (etPassword.text.isEmpty()){
                etPassword.error = "Silahkan Masukkan Password"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            startActivity(intent)
        }
    }

}