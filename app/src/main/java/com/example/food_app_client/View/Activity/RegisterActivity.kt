package com.example.food_app_client.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.food_app_client.R
import android.view.View
import android.widget.EditText
import android.widget.ImageView

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var emailEdit: EditText
    private lateinit var passwordEdit: EditText
    private lateinit var rePasswordEdit: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister: ImageView = findViewById(R.id.ivBtnRegister)
        val btnKembali: ImageView = findViewById(R.id.ivBtnKembali)
        emailEdit = findViewById(R.id.etEmail)
        passwordEdit = findViewById(R.id.etPassword)
        rePasswordEdit = findViewById(R.id.etRePassword)

        btnRegister.setOnClickListener(this)
        btnKembali.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ivBtnRegister -> {
                val password = passwordEdit.text.toString()
                val repassword = rePasswordEdit.text.toString()
                val intentRegkeLog = Intent(this@RegisterActivity, LoginActivity::class.java)
                if (emailEdit.text.isEmpty()){
                    emailEdit.setError("Silahkan Masukkan Email Anda")
                    emailEdit.requestFocus()
                    return
                }
                if (passwordEdit.text.isEmpty()){
                    passwordEdit.setError("Silahkan Masukkan Password Anda")
                    passwordEdit.requestFocus()
                    return
                }
                if (rePasswordEdit.text.isEmpty()){
                    rePasswordEdit.setError("Silahkan Masukkan Ulang Password Anda")
                    rePasswordEdit.requestFocus()
                    return
                }
                if (password != repassword){
                    rePasswordEdit.setError("Silahkan cek kembali password anda")
                    rePasswordEdit.requestFocus()
                    return
                }
                startActivity(intentRegkeLog)
            }
            R.id.ivBtnKembali -> {
                val intentKemkeLog = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intentKemkeLog)
            }
        }
    }
}