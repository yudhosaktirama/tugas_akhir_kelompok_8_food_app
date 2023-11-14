package com.example.food_app_client.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.food_app_client.R
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var emailEdit: EditText
    lateinit var passwordEdit: EditText
    lateinit var rePasswordEdit: EditText

    var firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister: ImageView = findViewById(R.id.ivBtnRegister)
        val btnKembali: ImageView = findViewById(R.id.ivBtnKembali)
        emailEdit = findViewById(R.id.etEmail)
        passwordEdit = findViewById(R.id.etPassword)
        rePasswordEdit = findViewById(R.id.etRePassword)

        btnRegister.setOnClickListener{

            if (emailEdit.text.isNotEmpty() && passwordEdit.text.isNotEmpty() && rePasswordEdit.text.isNotEmpty()) {
                if (passwordEdit.text.toString() == rePasswordEdit.text.toString()) {
                    processRegister()
                } else {
                    Toast.makeText(this, "Masukkan password yang sama!", LENGTH_SHORT).show()
                }
            }else if (emailEdit.text.isEmpty()){
                emailEdit.setError("Silahkan Masukkan Email Anda")
                emailEdit.requestFocus()
                return@setOnClickListener
            }else if (passwordEdit.text.isEmpty()){
                passwordEdit.setError("Silahkan Masukkan Password Anda")
                passwordEdit.requestFocus()
                return@setOnClickListener
            }else if (rePasswordEdit.text.isEmpty()){
                rePasswordEdit.setError("Silahkan Masukkan Ulang Password Anda")
                rePasswordEdit.requestFocus()
                return@setOnClickListener
            }
        }

        btnKembali.setOnClickListener{
            val intentKemkeLog = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intentKemkeLog)
        }
    }
    fun processRegister() {
        val email = emailEdit.text.toString()
        val password = passwordEdit.text.toString()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    Toast.makeText(this, "Gagal mendaftar: " + task.exception?.localizedMessage, LENGTH_SHORT).show()
                }
            }
    }

}


