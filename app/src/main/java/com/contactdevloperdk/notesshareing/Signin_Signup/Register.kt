package com.contactdevloperdk.notesshareing.Signin_Signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.contactdevloperdk.notesshareing.MainActivity
import com.contactdevloperdk.notesshareing.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //login activity click
        signupTextR.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        //intialized auth
        mAuth = Firebase.auth
        //we start our login code from here
        loginButtonR.setOnClickListener {
            //pgbar visibility on for after click login
            pgbarR.visibility = View.VISIBLE
            //first we check devise id register or not
            validdata()
        }
    }
    private fun validdata() {
        val name = usernameR.text.toString()
        val email = passwordRR.text.toString()
        val passward = passwordR.text.toString()
         if (name.isEmpty()){
             usernameR.setError("required")
             usernameR.requestFocus()
        }
        else if (email.isEmpty()){
             passwordRR.setError("required")
             passwordRR.requestFocus()
        }
        else if(passward.isEmpty()){
            passwordR.setError("required")
            passwordR.requestFocus()
        }
        else{
            loginuser()
        }
    }

    private fun loginuser() {
        val email = passwordRR.text.toString()
        val password = passwordR.text.toString()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val firebaseUser = mAuth.currentUser
                    updateUI(firebaseUser)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this, task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(firebaseUser: FirebaseUser?) {
        if (firebaseUser != null){

          val hashMap = HashMap<String, Any>()
          hashMap.put("uid", firebaseUser.uid)
          hashMap.put("name", usernameR.text.toString())
          hashMap.put("email", firebaseUser.email.toString())

            FirebaseDatabase.getInstance().getReference("user").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).setValue(
              hashMap
          )
            val mainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
            Toast.makeText(this, "registered", Toast.LENGTH_SHORT).show()
            pgbarR.visibility = View.GONE
        }
        else{
            pgbarR.visibility = View.GONE
            Toast.makeText(this, "Not registered", Toast.LENGTH_SHORT).show()
        }
    }
}