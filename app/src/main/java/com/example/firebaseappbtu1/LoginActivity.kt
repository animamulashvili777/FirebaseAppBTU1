package com.example.firebaseappbtu1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var loginEmailEditText : EditText
    private lateinit var loginPasswondEdiclext : EditText
    private lateinit var LoginButton : Button
    private lateinit var forgotPasswordButton: TextView
    private lateinit var notRegisteredButton: TextView
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        Listeners()
    }

    private fun Listeners(){
        loginButton.setOnClickListener{
            val email = loginEmailEditText.text.toString()
            val password = loginPasswondEdiclext.text.toString()
            if(email.isEmpty() || password.isEmpty())
                return@setOnClickListener
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                if(it.isSuccessful){
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                } else{
                    Toast.makeText(this,"${it.exception?.message}" ,Toast.LENGTH_SHORT).show()
                }
            }
        }
        forgotPasswordButton.setOnClickListener{
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
            finish()
        }
        notRegisteredButton.setOnClickListener{
            val intent : Intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun init(){
        loginEmailEditText = findViewById(R.id.loginEmailEditText)
        loginPasswondEdiclext = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        ForgotPasswordButton = findViewById(R.id.forgotPasswordButton)
        notRegisteredButton = findViewById(R.id.notRegisteredButton)
    }
}