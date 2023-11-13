package com.example.food_app_client.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.food_app_client.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var ivLogin: ImageView
    lateinit var ivregister: ImageView


    var firebaseAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

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

            if (etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()){
                prosesLogin()

            }else if (etEmail.text.isEmpty()) {
                etEmail.error = "Silahkan Masukkan Email"
                etEmail.requestFocus()
                return@setOnClickListener

            } else if (etPassword.text.isEmpty()) {
                etPassword.error = "Silahkan Masukkan Password"
                etPassword.requestFocus()
                return@setOnClickListener

            }
        }
    }

    private fun prosesLogin() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, UserActivity::class.java))
            }
            .addOnFailureListener{ error ->
                Toast.makeText(this, error.localizedMessage, LENGTH_SHORT).show()
            }
    }
}