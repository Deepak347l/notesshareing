package com.contactdevloperdk.notesshareing.Signin_Signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.contactdevloperdk.notesshareing.MainActivity
import com.contactdevloperdk.notesshareing.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {
    //here we intilized our data
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //register activity click
        signupText.setOnClickListener{
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }
        //intialized auth
        mAuth = Firebase.auth
        //we start our login code from here
        loginButton.setOnClickListener {
            pgbar.visibility = View.VISIBLE
            validdata()
        }
    }

    private fun validdata() {
        val email = username.text.toString()
        val passward = password.text.toString()
        if (email.isEmpty()){
            username.setError("required")
            username.requestFocus()
        }
        else if(passward.isEmpty()){
            password.setError("required")
            password.requestFocus()
        }
        else{
            loginuser()
        }
    }

    private fun loginuser() {
        val email = username.text.toString()
        val password = password.text.toString()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                    pgbar.visibility = View.GONE
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, task.exception?.message,
                        Toast.LENGTH_SHORT).show()
                    pgbar.visibility = View.GONE
                }
            }
    }
    //its checked for user login or not
        //on back click app closed
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}