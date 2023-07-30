package com.contactdevloperdk.notesshareing

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.contactdevloperdk.notesshareing.Signin_Signup.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*


class Settings : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_settings, container, false
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Now retrive our hedrer data
        FirebaseDatabase.getInstance().getReference("user")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString()).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val namedata =  snapshot.child("name").value.toString()
                    val emaildata =  snapshot.child("email").value.toString()
                    val uiddata =  snapshot.child("uid").value.toString()
                    usernameff.text = namedata
                    following.text = emaildata
                    followers.text = uiddata

                    c.setOnClickListener {
                        val c: ClipboardManager = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clipData = ClipData.newPlainText("TextView", followers.text.toString())
                        c.setPrimaryClip(clipData)
                        Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e("mainActivityError", error.message)
                }
            })
        material555555.setOnClickListener{
            Firebase.auth.signOut()
            val intent = Intent(context, Login::class.java)
            startActivity(intent)
            Toast.makeText(
                context,
                "You susessfully logout",
                Toast.LENGTH_SHORT
            ).show()
            fragmentManager?.isDestroyed
        }
        material55555.setOnClickListener{
            val intent = Intent(context, Earningdashbord::class.java)
            startActivity(intent)

        }
        material5.setOnClickListener{
            FirebaseDatabase.getInstance().getReference("appLink")
                .child("link").addValueEventListener(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val Link =  snapshot.child("data").value.toString()
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, Link)
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        startActivity(shareIntent)
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.e("settingsError", error.message)
                    }
                })
        }
        material55.setOnClickListener{
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://forms.gle/LETT5qbB7yULv9HCA")
            )
            startActivity(intent)
        }
        material5555.setOnClickListener{
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://forms.gle/hebYKZX1TZXtMP7J9")
            )
            startActivity(intent)
        }
        material555.setOnClickListener{
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://sites.google.com/view/askearncash/home")
            )
            startActivity(intent)
        }
    }
}