package com.contactdevloperdk.notesshareing

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import com.applovin.mediation.ads.MaxInterstitialAd
import com.github.barteksc.pdfviewer.PDFView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_pdf_view.*
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit


class pdfView : AppCompatActivity(), MaxAdListener {

    lateinit var urls: String
    lateinit var uidx: String
    lateinit var key: String
    lateinit var testkey: String
    lateinit var pdfViewx: PDFView
    lateinit var dialog: ProgressDialog
    private lateinit var interstitialAd: MaxInterstitialAd
    private var retryAttempt = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_view)
        var adView: MaxAdView = findViewById(R.id.nativeAdContainer)
        adView.loadAd()
        pdfViewx = findViewById(R.id.abc)
        // Firstly we are showing the progress
        // dialog when we are loading the pdf
        dialog = ProgressDialog(this)
        dialog.setMessage("Loading..")
        dialog.setCancelable(false)
        dialog.show()
        // getting url of pdf using getItentExtra
        urls = intent.getStringExtra("lk").toString()
        key = intent.getStringExtra("lkx").toString()
        testkey = intent.getStringExtra("lkxx").toString()
        uidx = intent.getStringExtra("lkxxx").toString()
        RetrivePdfStream().execute(urls)
        linear4555.setOnClickListener {
            val hashMap = HashMap<String,Any>()
            hashMap.put("User", FirebaseAuth.getInstance().currentUser?.uid.toString())
            FirebaseDatabase.getInstance().getReference("Notes")
                .child(key).child("report")
                .child(FirebaseAuth.getInstance().currentUser?.uid.toString()).updateChildren(hashMap)
            Toast.makeText(this, "Report sumbited", Toast.LENGTH_SHORT).show()
        }
        linear4.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urls))
            startActivity(intent)
        }
        if (testkey == "condicton"){
            linear4555.visibility = View.GONE
            linear45.visibility = View.VISIBLE
            linear455.visibility = View.VISIBLE
            linear45.setOnClickListener {
                FirebaseDatabase.getInstance().getReference("Notes")
                    .child(key).removeValue()
                finish()
            }
            linear455.setOnClickListener {
                val intent = Intent(this, editPost::class.java)
                intent.putExtra("xxx", key)
                startActivity(intent)
            }
        }
        interstitialAd = MaxInterstitialAd( "6af805008b230fcf", this)
        interstitialAd.setListener( this )

        // Load the first ad
        interstitialAd.loadAd()

        FirebaseDatabase.getInstance().getReference("Notes")
            .child(key).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val checkViews =  snapshot.child("ads").value
                    if (checkViews == true) {
                        if ( interstitialAd.isReady() )
                        {
                            interstitialAd.showAd();
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.e("adapterError", error.message)
                }
            })
    }
    // Retrieving the pdf file using url
    internal inner class RetrivePdfStream :
        AsyncTask<String?, Void?, InputStream?>() {
        override fun doInBackground(vararg params: String?): InputStream? {
            var inputStream: InputStream? = null
            try {
                // adding url
                val url = URL(params[0])
                val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                // if url connection response code is 200 means ok the execute
                if (urlConnection.getResponseCode() === 200) {
                    inputStream = BufferedInputStream(urlConnection.getInputStream())
                }
            } // if error return null
            catch (e: IOException) {
                return null
            }
            return inputStream
        }
        // Here load the pdf and dismiss the dialog box
        override fun onPostExecute(inputStream: InputStream?) {
            pdfViewx.fromStream(inputStream)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .load()
            dialog.dismiss()
        }
    }
    override fun onAdLoaded(ad: MaxAd?) {
        // Interstitial ad is ready to be shown. interstitialAd.isReady() will now return 'true'

        // Reset retry attempt
        retryAttempt = 0.0
    }

    override fun onAdDisplayed(ad: MaxAd?) {

    }

    override fun onAdHidden(ad: MaxAd?) {
        if (uidx == FirebaseAuth.getInstance().currentUser?.uid.toString()){
            Log.d("TAG","")
        }else{
        val hashMap = HashMap<String,Any>()
        hashMap.put("User", FirebaseAuth.getInstance().currentUser?.uid.toString())
        FirebaseDatabase.getInstance().getReference("mads")
            .child(uidx).child("adsc")
            .push().updateChildren(hashMap)
    }
    }

    override fun onAdClicked(ad: MaxAd?) {

    }

    override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {

        // Interstitial ad failed to load
        // AppLovin recommends that you retry with exponentially higher delays up to a maximum delay (in this case 64 seconds)

        retryAttempt++
        val delayMillis = TimeUnit.SECONDS.toMillis( Math.pow( 2.0, Math.min( 6.0, retryAttempt ) ).toLong() )

        Handler().postDelayed( { interstitialAd.loadAd() }, delayMillis )

    }

    override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
// Interstitial ad failed to display. AppLovin recommends that you load the next ad.
        interstitialAd.loadAd()
    }

}