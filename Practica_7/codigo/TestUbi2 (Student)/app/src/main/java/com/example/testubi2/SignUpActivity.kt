package com.example.testubi2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var signUpBtn: Button
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()


        emailEt = findViewById(R.id.email_edt_text)
        passwordEt = findViewById(R.id.pass_edt_text)

        loginBtn = findViewById(R.id.login_btn)
        signUpBtn = findViewById(R.id.signup_btn)

        signUpBtn.setOnClickListener{
            val email: String = emailEt.text.toString()
            val password: String = passwordEt.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Información faltante", Toast.LENGTH_LONG).show()
            } else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Cuenta creada!", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Registro falló", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        loginBtn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}