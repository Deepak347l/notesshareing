package com.contactdevloperdk.notesshareing

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.contactdevloperdk.notesshareing.Main_file.Adapterm
import com.contactdevloperdk.notesshareing.Main_file.Modelm
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_profile.*


class Profile : Fragment(), Adapterm.DownloadClickInterfacem {
    private var notesRV: RecyclerView? = null
    private lateinit var loadingPB: ProgressBar
    private lateinit var  notesRVModalArrayList: ArrayList<Modelm>
    private lateinit var notesRVAdapter: Adapterm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_profile, container, false
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //data retrive for rcv
        notesRV = view.findViewById(R.id.recycler5)
        loadingPB = view.findViewById(R.id.progress_small15)
        // mAuth = FirebaseAuth.getInstance()
        notesRVModalArrayList = ArrayList()
        // on below line initializing our adapter class.
        // on below line initializing our adapter class.
        notesRVAdapter = Adapterm(notesRVModalArrayList, context!!, this)
        // setting layout malinger to recycler view on below line.
        // setting layout malinger to recycler view on below line.
        val stagredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        notesRV!!.layoutManager = stagredGridLayoutManager
        // setting adapter to recycler view on below line.
        // setting adapter to recycler view on below line.
        notesRV!!.adapter = notesRVAdapter
        // on below line calling a method to fetch courses from database.
        // on below line calling a method to fetch courses from database.
        notesRVModalArrayList.clear()
        // on below line we are calling add child event listener method to read the data.
        // on below line we are calling add child event listener method to read the data.
        FirebaseDatabase.getInstance().getReference("Notes").addChildEventListener(object :
            ChildEventListener {
            override fun onChildAdded(
                snapshot: DataSnapshot,
                @Nullable previousChildName: String?
            ) {
                // on below line we are hiding our progress bar.
                loadingPB.visibility = View.GONE
                // adding snapshot to our array list on below line.
                val sdata = snapshot.getValue(Modelm::class.java)
                if(sdata!!.uid == FirebaseAuth.getInstance().currentUser?.uid.toString()){
                    notesRVModalArrayList.add(snapshot.getValue(Modelm::class.java)!!)
                    // notifying our adapter that data has changed.
                    notesRVAdapter.notifyDataSetChanged()
                }
            }

            override fun onChildChanged(
                snapshot: DataSnapshot,
                @Nullable previousChildName: String?
            ) {
                // this method is called when new child is added
                // we are notifying our adapter and making progress bar
                // visibility as gone.
                loadingPB.visibility = View.GONE
                notesRVAdapter.notifyDataSetChanged()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // notifying our adapter when child is removed.
                notesRVAdapter.notifyDataSetChanged()
                loadingPB.visibility = View.GONE
            }

            override fun onChildMoved(
                snapshot: DataSnapshot,
                @Nullable previousChildName: String?
            ) {
                // notifying our adapter when child is moved.
                notesRVAdapter.notifyDataSetChanged()
                loadingPB.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                loadingPB.visibility = View.GONE
                Toast.makeText(
                    context,
                    "Fail to get the data" + error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        ascva5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do Nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do Nothing
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })

        //Bottom sheet dialog
        ascv5.setOnClickListener {
            showDialog()
        }
    }



    private fun showDialog() {
        val dialog = BottomSheetDialog(context!!)
        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.bottomsheetlayout, null)
        // on below line we are creating a variable for our button
        // which we are using to dismiss our dialog.
        val btnClose = view.findViewById<Button>(R.id.buttonxxx)
        // on below line we are adding on click listener
        // for our dismissing the dialog button.
        btnClose.setOnClickListener {
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
    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filteredNames = ArrayList<Modelm>()
        //looping through existing elements and adding the element to filtered list
        notesRVModalArrayList.filterTo(filteredNames) {
            //if the existing elements contains the search input
            it.title.toLowerCase().contains(text.toLowerCase())
        }
        //calling a method of the adapter class and passing the filtered list
        notesRVAdapter.filterList(filteredNames)
    }
    override fun onDownloadClick(position: Int, notesRVModal: Modelm) {
        val testkey = "condicton"
        // Create the object of AlertDialog Builder class
        val builder = AlertDialog.Builder(context)
        // Set the message show for the Alert time
        builder.setMessage("Are you sure you want to open it ?")
        // Set Alert Title
        builder.setTitle("Conform !")
        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false)
        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes") {
            // When the user click yes button then app will close
                dialog, which ->
            val hashMap = HashMap<String,Any>()
            hashMap.put("User",FirebaseAuth.getInstance().currentUser?.uid.toString())
            FirebaseDatabase.getInstance().getReference("Notes")
                .child(notesRVModal.key).child("views")
                .child(FirebaseAuth.getInstance().currentUser?.uid.toString()).updateChildren(hashMap)
            val intent = Intent(context, pdfView::class.java)
            intent.putExtra("lk", notesRVModal.link)
            intent.putExtra("lkx", notesRVModal.key)
            intent.putExtra("lkxxx", notesRVModal.uid)
            intent.putExtra("lkxx", testkey)
            startActivity(intent)
        }
        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No") {
            // If user click no then dialog box is canceled.
                dialog, which -> dialog.cancel()
        }
        // Create the Alert dialog
        val alertDialog = builder.create()
        alertDialog.setOnShowListener(object : DialogInterface.OnShowListener {
            @SuppressLint("ResourceAsColor")
            override fun onShow(dialog: DialogInterface?) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black)
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.black)
            }
        })
        // Show the Alert Dialog box
        alertDialog.show()
    }

    override fun onMenuClick(position: Int, notesRVModal: Modelm) {

    }
}