package com.example.firebaseappbtu1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var recoveryButton : Button
    private lateinit var recoveryEmail : EditText

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        recoveryButton = findViewById(R.id.forgotPasswordButton)
        recoveryEmail = findViewById(R.id.forgotEmailEditText)

        recoveryButton.setOnClickListener{
            val email = recoveryEmail.text.toString()
            if(email.isEmpty()) return@setOnClickListener

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
                if(it.isSuccessful){
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else{
                    Toast.makeText(this,"${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
