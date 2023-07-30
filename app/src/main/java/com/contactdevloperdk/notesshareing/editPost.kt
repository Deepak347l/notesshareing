package com.contactdevloperdk.notesshareing

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_edit_post.*


class editPost : AppCompatActivity() {
    private val root = FirebaseDatabase.getInstance().getReference("Notes")
    private val reference = FirebaseStorage.getInstance().reference
    private lateinit var imageUri: Uri
    private lateinit var radioBtn: RadioButton
    private lateinit var btnRadio5: RadioButton
    private lateinit var btnRadio55: RadioButton
    private lateinit var btnRadio555: RadioButton
    private lateinit var btnRadio5555: RadioButton
    private lateinit var btnRadio55555: RadioButton
    private lateinit var btnRadio555555: RadioButton
    private lateinit var btnRadio5555555: RadioButton
    private lateinit var btnRadio55555555: RadioButton
    companion object {
        private const val STORAGE_PERMISSION_CODE = 101
    }
    lateinit var keyx: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_post)
        keyx = intent.getStringExtra("xxx").toString()
        progress_small1555551.setVisibility(View.GONE)
        aaa1.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                val galleryIntent = Intent()
                galleryIntent.action = Intent.ACTION_GET_CONTENT
                galleryIntent.type = "application/pdf"
                startActivityForResult(galleryIntent, 1)
            }else{
                checkPermission( Manifest.permission.READ_EXTERNAL_STORAGE,
                    editPost.STORAGE_PERMISSION_CODE
                )
            }
        }
        aaa5551.setOnClickListener{
            val dialog = BottomSheetDialog(this)
            // on below line we are inflating a layout file which we have created.
            val view = layoutInflater.inflate(R.layout.bottomsheetlayoutm, null)
            // on below line we are creating a variable for our button
            // which we are using to dismiss our dialog.
            val btnClose = view.findViewById<Button>(R.id.buttonxxx5)
            val btnRadio = view.findViewById<RadioGroup>(R.id.radio_group5)
            btnRadio5 = view.findViewById<RadioButton>(R.id.red5)
            btnRadio55 = view.findViewById<RadioButton>(R.id.green5)
            btnRadio555 = view.findViewById<RadioButton>(R.id.yellow5)
            btnRadio5555 = view.findViewById<RadioButton>(R.id.yellow55)
            btnRadio55555 = view.findViewById<RadioButton>(R.id.yellow555)
            btnRadio555555 = view.findViewById<RadioButton>(R.id.yellow5555)
            btnRadio5555555 = view.findViewById<RadioButton>(R.id.yellow55555)
            btnRadio55555555 = view.findViewById<RadioButton>(R.id.yellow555555)
            // on below line we are adding on click listener
            // for our dismissing the dialog button.
            btnClose.setOnClickListener {
                val btnslc = btnRadio.checkedRadioButtonId
                radioBtn = view.findViewById<RadioButton>(btnslc)

                // on below line we are calling a dismiss
                // method to close our dialog.
                dialog.dismiss()

            }
            // below line is use to set cancelable to avoid
            // closing of dialog box when clicking on the screen.
            dialog.setCancelable(true)
            // on below line we are setting
            // content view to our view.
            dialog.setContentView(view)
            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()
        }
        aaa55551.setOnClickListener{
            val title = xxxmx55.text.toString()
            val descripction = xxxmx555.text.toString()
            if (title.isEmpty()){
                xxxmx55.setError("required")
                xxxmx55.requestFocus()
            }
            else if(descripction.isEmpty()){
                xxxmx555.setError("required")
                xxxmx555.requestFocus()
            }
            else{
                try {
                    uploadToFirebase(imageUri)
                }catch (e: Exception) {
                    Toast.makeText(
                        this@editPost,
                        "Please check and try" + e.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    private fun checkPermission(readExternalStorage: String, storagePermissionCode: Int) {
        if (ContextCompat.checkSelfPermission(this, readExternalStorage) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(readExternalStorage), storagePermissionCode)
        } else {
            val galleryIntent = Intent()
            galleryIntent.action = Intent.ACTION_GET_CONTENT
            galleryIntent.type = "application/pdf"
            startActivityForResult(galleryIntent, 1)
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == editPost.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val galleryIntent = Intent()
                galleryIntent.action = Intent.ACTION_GET_CONTENT
                galleryIntent.type = "application/pdf"
                startActivityForResult(galleryIntent, 1)
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.data!!
        }
    }
    private fun uploadToFirebase(uri: Uri) {
        val fileRef =
            reference.child(System.currentTimeMillis().toString() + "." + getFileExtension(uri))
        fileRef.putFile(uri)
            .addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot?> {
                override fun onSuccess(p0: UploadTask.TaskSnapshot?) {
                    fileRef.downloadUrl.addOnSuccessListener(object : OnSuccessListener<Uri?> {
                        override fun onSuccess(p0: Uri?) {
                            if (radioBtn == btnRadio5){
                                val key = root.child(keyx)
                                val hashMap = HashMap<String,Any>()
                                hashMap.put("title",xxxmx55.text.toString())
                                hashMap.put("des",xxxmx555.text.toString())
                                hashMap.put("link",p0.toString())
                                hashMap.put("tag","11th")
                                hashMap.put("ads",false)
                                key.updateChildren(hashMap)
                                if (switch11.isChecked){
                                    val hashMap5 = HashMap<String,Any>()
                                    hashMap5.put("ads",true)
                                    key.updateChildren(hashMap5)
                                }

                            }
                            else if (radioBtn == btnRadio55){
                                val key = root.child(keyx)
                                val hashMap = HashMap<String,Any>()
                                hashMap.put("title",xxxmx55.text.toString())
                                hashMap.put("des",xxxmx555.text.toString())
                                hashMap.put("link",p0.toString())
                                hashMap.put("tag","12th")
                                hashMap.put("ads",false)
                                key.updateChildren(hashMap)
                                if (switch11.isChecked){
                                    val hashMap5 = HashMap<String,Any>()
                                    hashMap5.put("ads",true)
                                    key.updateChildren(hashMap5)
                                }
                            }
                            else if (radioBtn == btnRadio555){
                                val key = root.child(keyx)
                                val hashMap = HashMap<String,Any>()
                                hashMap.put("title",xxxmx55.text.toString())
                                hashMap.put("des",xxxmx555.text.toString())
                                hashMap.put("link",p0.toString())
                                hashMap.put("tag","Bsc")
                                hashMap.put("ads",false)
                                key.updateChildren(hashMap)
                                if (switch11.isChecked){
                                    val hashMap5 = HashMap<String,Any>()
                                    hashMap5.put("ads",true)
                                    key.updateChildren(hashMap5)
                                }
                            }
                            else if (radioBtn == btnRadio5555){
                                val key = root.child(keyx)
                                val hashMap = HashMap<String,Any>()
                                hashMap.put("title",xxxmx55.text.toString())
                                hashMap.put("des",xxxmx555.text.toString())
                                hashMap.put("link",p0.toString())
                                hashMap.put("tag","BA")
                                hashMap.put("ads",false)
                                key.updateChildren(hashMap)
                                if (switch11.isChecked){
                                    val hashMap5 = HashMap<String,Any>()
                                    hashMap5.put("ads",true)
                                    key.updateChildren(hashMap5)
                                }
                            }
                            else if (radioBtn == btnRadio55555){
                                val key = root.child(keyx)
                                val hashMap = HashMap<String,Any>()
                                hashMap.put("title",xxxmx55.text.toString())
                                hashMap.put("des",xxxmx555.text.toString())
                                hashMap.put("link",p0.toString())
                                hashMap.put("tag","Btech")
                                hashMap.put("ads",false)
                                key.updateChildren(hashMap)
                                if (switch11.isChecked){
                                    val hashMap5 = HashMap<String,Any>()
                                    hashMap5.put("ads",true)
                                    key.updateChildren(hashMap5)
                                }
                            }
                            else if (radioBtn == btnRadio555555){
                                val key = root.child(keyx)
                                val hashMap = HashMap<String,Any>()
                                hashMap.put("title",xxxmx55.text.toString())
                                hashMap.put("des",xxxmx555.text.toString())
                                hashMap.put("link",p0.toString())
                                hashMap.put("tag","Bcom")
                                hashMap.put("ads",false)
                                key.updateChildren(hashMap)
                                if (switch11.isChecked){
                                    val hashMap5 = HashMap<String,Any>()
                                    hashMap5.put("ads",true)
                                    key.updateChildren(hashMap5)
                                }
                            }
                            else if (radioBtn == btnRadio5555555){
                                val key = root.child(keyx)
                                val hashMap = HashMap<String,Any>()
                                hashMap.put("title",xxxmx55.text.toString())
                                hashMap.put("des",xxxmx555.text.toString())
                                hashMap.put("link",p0.toString())
                                hashMap.put("tag","BCA")
                                hashMap.put("ads",false)
                                key.updateChildren(hashMap)
                                if (switch11.isChecked){
                                    val hashMap5 = HashMap<String,Any>()
                                    hashMap5.put("ads",true)
                                    key.updateChildren(hashMap5)
                                }
                            }
                            else if (radioBtn == btnRadio55555555){
                                val key = root.child(keyx)
                                val hashMap = HashMap<String,Any>()
                                hashMap.put("title",xxxmx55.text.toString())
                                hashMap.put("des",xxxmx555.text.toString())
                                hashMap.put("link",p0.toString())
                                hashMap.put("tag","BBA")
                                hashMap.put("ads",false)
                                key.updateChildren(hashMap)
                                if (switch11.isChecked){
                                    val hashMap5 = HashMap<String,Any>()
                                    hashMap5.put("ads",true)
                                    key.updateChildren(hashMap5)
                                }
                            }
                            else{
                                val key = root.child(keyx)
                                val hashMap = HashMap<String,Any>()
                                hashMap.put("title",xxxmx55.text.toString())
                                hashMap.put("des",xxxmx555.text.toString())
                                hashMap.put("link",p0.toString())
                                hashMap.put("tag","Others")
                                hashMap.put("ads",false)
                                key.updateChildren(hashMap)
                                if (switch11.isChecked){
                                    val hashMap5 = HashMap<String,Any>()
                                    hashMap5.put("ads",true)
                                    key.updateChildren(hashMap5)
                                }
                            }
                            progress_small1555551!!.visibility = View.GONE
                            Toast.makeText(
                                this@editPost,
                                "Uploaded Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val mainActivityIntent = Intent(this@editPost, MainActivity::class.java)
                            startActivity(mainActivityIntent)
                            finish()
                        }

                    })
                }
            }).addOnProgressListener { progress_small1555551!!.visibility = View.VISIBLE }
            .addOnFailureListener {
                progress_small1555551!!.visibility = View.GONE
                Toast.makeText(
                    this,
                    "Uploading Failed !!",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun getFileExtension(mUri: Uri): String? {
        val cr = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cr.getType(mUri))
    }
}