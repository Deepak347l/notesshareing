package com.contactdevloperdk.notesshareing

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentTransaction
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkConfiguration
import com.contactdevloperdk.notesshareing.Signin_Signup.Login
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_background.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var view: View
    private lateinit var mAuth: FirebaseAuth
    //app update
    val UPDATE_CODE = 22
    lateinit var appUpdateManager: AppUpdateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Make sure to set the mediation provider value to "max" to ensure proper functionality
        AppLovinSdk.getInstance( this ).setMediationProvider( "max" )
        AppLovinSdk.getInstance( this ).initializeSdk({ configuration: AppLovinSdkConfiguration ->
            // AppLovin SDK is initialized, start loading ads
        })
        tabLayout = findViewById(R.id.tabLayout);
        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_background, null);
        //change the font family of text view in tabs(Make sure internet is on, for the first time to prevent crash)
        //if(internet is on)
        try {
        val typeface = ResourcesCompat.getFont(application, R.font.poppins_medium)
        tv1.setTypeface(typeface)
        } catch (e: Exception) {
            Log.e("mainActivityError", e.message.toString())
        }
        //else
        //..default font
        setCustomView(0, 1, 2, 3)
        setTextAndImageWithAnimation("HOME", R.drawable.ic_home);
        handleFragment(HomeFragment())
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    1 -> {
                        setCustomView(1, 0, 2, 3)
                        setTextAndImageWithAnimation("CATEGORIES", R.drawable.ic_categories)
                        //change to the fragment which you want to display
                        handleFragment_categories(Categories())
                    }
                    2 -> {
                        setCustomView(2, 1, 0, 3)
                        setTextAndImageWithAnimation("SETTINGS", R.drawable.ic_settings)
                        //change to the fragment which you want to display
                        handleFragment_settings(Settings())
                    }
                    3 -> {
                        setCustomView(3, 1, 2, 0)
                        setTextAndImageWithAnimation("PROFILE", R.drawable.ic_person)
                        //change to the fragment which you want to display
                        handleFragment_person(Profile())
                    }
                    0 -> {
                        setCustomView(0, 1, 2, 3)
                        setTextAndImageWithAnimation("HOME", R.drawable.ic_home)
                        //change to the fragment which you want to display
                        handleFragment(HomeFragment())
                    }
                    else -> {
                        setCustomView(0, 1, 2, 3)
                        setTextAndImageWithAnimation("HOME", R.drawable.ic_home)
                        handleFragment(HomeFragment())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        //data retrive for rcv
        mAuth = FirebaseAuth.getInstance()
        //compolsory app update methode
        inappupdate()
        //Now retrive our hedrer data
        FirebaseDatabase.getInstance().getReference("user")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString()).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val namedata =  snapshot.child("name").value.toString()
                    userName.text = namedata
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e("mainActivityError", error.message)
                }
            })

        msc.setOnClickListener {
            val intent = Intent(this, Post::class.java)
            startActivity(intent)
        }

    }

    private fun handleFragment_person(profile: Profile) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
        transaction.replace(R.id.frameLayout, profile)
        transaction.commit()
    }

    private fun handleFragment_settings(settings: Settings) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
        transaction.replace(R.id.frameLayout, settings)
        transaction.commit()
    }

    private fun handleFragment_categories(categories: Categories) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
        transaction.replace(R.id.frameLayout, categories)
        transaction.commit()
    }

    private fun setCustomView(selectedtab: Int, non1: Int, non2: Int, non3: Int) {
        Objects.requireNonNull(tabLayout.getTabAt(selectedtab))?.setCustomView(view)
        Objects.requireNonNull(tabLayout.getTabAt(non1))?.setCustomView(null)
        Objects.requireNonNull(tabLayout.getTabAt(non2))?.setCustomView(null)
        Objects.requireNonNull(tabLayout.getTabAt(non3))?.setCustomView(null)
    }

    private fun setTextAndImageWithAnimation(text: String, images: Int) {
        val animation: Animation =
            AnimationUtils.loadAnimation(applicationContext, android.R.anim.slide_in_left)
        animation.setInterpolator(AccelerateDecelerateInterpolator())
        tv1.setText(text)
        iv1.setImageResource(images)
        tv1.startAnimation(animation)
        iv1.startAnimation(animation)
    }

    private fun handleFragment(fragment: HomeFragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }
    private fun inappupdate() {
        appUpdateManager = AppUpdateManagerFactory.create(this)
        val task : com.google.android.play.core.tasks.Task<AppUpdateInfo> = appUpdateManager.appUpdateInfo
        task.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && it.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)){
                try {
                    appUpdateManager.startUpdateFlowForResult(
                        it,
                        AppUpdateType.IMMEDIATE,
                        this,
                        UPDATE_CODE
                    )

                } catch (e: InternalError){ }
            }
        }
        appUpdateManager.registerListener(listner)
    }
    val listner: InstallStateUpdatedListener = InstallStateUpdatedListener{ state ->

        if (state.installStatus() == InstallStatus.DOWNLOADING){
            popup()
        }
    }

    private fun popup() {
        val sneekbar = Snackbar.make(
            findViewById(android.R.id.content), "App update done", Snackbar.LENGTH_INDEFINITE
        )
        sneekbar.setAction("Reloaded", object : View.OnClickListener {
            override fun onClick(v: View?) {
                appUpdateManager.completeUpdate()
            }

        })
        sneekbar.setTextColor(Color.parseColor("#FF0000"))
        sneekbar.show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == UPDATE_CODE){
            if (resultCode != RESULT_OK){
            }
        }
    }

    //CHEK USER LOGIN STATUS
    override fun onStart() {
        super.onStart()
        val currentuser = mAuth.currentUser
        if (currentuser == null){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    //when on back presed click app closed
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}