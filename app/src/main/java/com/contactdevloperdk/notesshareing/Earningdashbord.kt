package com.contactdevloperdk.notesshareing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_earningdashbord.*
import kotlinx.android.synthetic.main.activity_post.*

class Earningdashbord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earningdashbord)
        FirebaseDatabase.getInstance().getReference("mads")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val checkViews =  snapshot.child("adsc").childrenCount.toInt()
                    mmm.text =  checkViews.toString()
                    FirebaseDatabase.getInstance().getReference("appLink")
                        .child("link").addValueEventListener(object :
                            ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val Link =  snapshot.child("imps").value.toString().toFloat()
                                val LinkX =  checkViews*Link
                                mmmx.text =  LinkX.toString()
                            }
                            override fun onCancelled(error: DatabaseError) {
                                Log.e("settingsError", error.message)
                            }
                        })
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e("adapterError", error.message)
                }
            })
        mmmxxxx.setOnClickListener{
            val hashMap = HashMap<String,Any>()
            hashMap.put("upiID",mmmxxxxX.text.toString())
            FirebaseDatabase.getInstance().getReference("User_upiID")
                .child(FirebaseAuth.getInstance().currentUser?.uid.toString()).setValue(hashMap)
            Toast.makeText(this,"Saved your upi_ID",Toast.LENGTH_SHORT).show()
        }
    }
}