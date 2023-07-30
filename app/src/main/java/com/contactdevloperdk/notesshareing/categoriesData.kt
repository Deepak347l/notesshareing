package com.contactdevloperdk.notesshareing

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.applovin.mediation.ads.MaxAdView
import com.contactdevloperdk.notesshareing.Main_file.Adapter
import com.contactdevloperdk.notesshareing.Main_file.Model
import com.google.android.gms.ads.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_categories_data.*
import kotlinx.android.synthetic.main.fragment_home.*

class categoriesData : AppCompatActivity(), Adapter.DownloadClickInterface {
    private var notesRV: RecyclerView? = null
    private lateinit var loadingPB: ProgressBar
    private lateinit var  notesRVModalArrayList: ArrayList<Model>
    private lateinit var notesRVAdapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_data)
        var adView: MaxAdView = findViewById(R.id.adView)
        adView.loadAd()
        val data = intent.getStringExtra("key")
        val data1 = intent.getStringExtra("key1")
        val data11 = intent.getStringExtra("key11")
        val data111 = intent.getStringExtra("key111")
        val data1111 = intent.getStringExtra("key1111")
        val data11111 = intent.getStringExtra("key11111")
        val data111111 = intent.getStringExtra("key111111")
        val data1111111 = intent.getStringExtra("key1111111")
        val data11111111 = intent.getStringExtra("key11111111")

        scv.addTextChangedListener(object : TextWatcher {
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



        if(data == "data"){
            //data retrive for rcv
            notesRV = findViewById(R.id.rcv)
            loadingPB = findViewById(R.id.progress_small1v)
            // mAuth = FirebaseAuth.getInstance()
            notesRVModalArrayList = ArrayList()
            // on below line initializing our adapter class.
            // on below line initializing our adapter class.
            notesRVAdapter = Adapter(notesRVModalArrayList, this, this)
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
                    val sdata = snapshot.getValue(Model::class.java)
                    if(sdata!!.tag == "11th"){
                        notesRVModalArrayList.add(snapshot.getValue(Model::class.java)!!)
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
                        this@categoriesData,
                        "Fail to get the data" + error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
        else if(data1 == "data1"){
            //data retrive for rcv
            notesRV = findViewById(R.id.rcv)
            loadingPB = findViewById(R.id.progress_small1v)
            // mAuth = FirebaseAuth.getInstance()
            notesRVModalArrayList = ArrayList()
            // on below line initializing our adapter class.
            // on below line initializing our adapter class.
            notesRVAdapter = Adapter(notesRVModalArrayList, this, this)
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
                    val sdata = snapshot.getValue(Model::class.java)
                    if(sdata!!.tag == "12th"){
                        notesRVModalArrayList.add(snapshot.getValue(Model::class.java)!!)
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
                        this@categoriesData,
                        "Fail to get the data" + error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
        else if(data11 == "data11"){
            //data retrive for rcv
            notesRV = findViewById(R.id.rcv)
            loadingPB = findViewById(R.id.progress_small1v)
            // mAuth = FirebaseAuth.getInstance()
            notesRVModalArrayList = ArrayList()
            // on below line initializing our adapter class.
            // on below line initializing our adapter class.
            notesRVAdapter = Adapter(notesRVModalArrayList, this, this)
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
                    val sdata = snapshot.getValue(Model::class.java)
                    if(sdata!!.tag == "Bsc"){
                        notesRVModalArrayList.add(snapshot.getValue(Model::class.java)!!)
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
                        this@categoriesData,
                        "Fail to get the data" + error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
        else if(data111 == "data111"){
            //data retrive for rcv
            notesRV = findViewById(R.id.rcv)
            loadingPB = findViewById(R.id.progress_small1v)
            // mAuth = FirebaseAuth.getInstance()
            notesRVModalArrayList = ArrayList()
            // on below line initializing our adapter class.
            // on below line initializing our adapter class.
            notesRVAdapter = Adapter(notesRVModalArrayList, this, this)
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
                    val sdata = snapshot.getValue(Model::class.java)
                    if(sdata!!.tag == "BA"){
                        notesRVModalArrayList.add(snapshot.getValue(Model::class.java)!!)
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
                        this@categoriesData,
                        "Fail to get the data" + error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
        else if(data1111 == "data1111"){
            //data retrive for rcv
            notesRV = findViewById(R.id.rcv)
            loadingPB = findViewById(R.id.progress_small1v)
            // mAuth = FirebaseAuth.getInstance()
            notesRVModalArrayList = ArrayList()
            // on below line initializing our adapter class.
            // on below line initializing our adapter class.
            notesRVAdapter = Adapter(notesRVModalArrayList, this, this)
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
                    val sdata = snapshot.getValue(Model::class.java)
                    if(sdata!!.tag == "Btech"){
                        notesRVModalArrayList.add(snapshot.getValue(Model::class.java)!!)
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
                        this@categoriesData,
                        "Fail to get the data" + error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
        else if(data11111 == "data11111"){
            //data retrive for rcv
            notesRV = findViewById(R.id.rcv)
            loadingPB = findViewById(R.id.progress_small1v)
            // mAuth = FirebaseAuth.getInstance()
            notesRVModalArrayList = ArrayList()
            // on below line initializing our adapter class.
            // on below line initializing our adapter class.
            notesRVAdapter = Adapter(notesRVModalArrayList, this, this)
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
                    val sdata = snapshot.getValue(Model::class.java)
                    if(sdata!!.tag == "Bcom"){
                        notesRVModalArrayList.add(snapshot.getValue(Model::class.java)!!)
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
                        this@categoriesData,
                        "Fail to get the data" + error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
        else if(data111111 == "data111111"){
            //data retrive for rcv
            notesRV = findViewById(R.id.rcv)
            loadingPB = findViewById(R.id.progress_small1v)
            // mAuth = FirebaseAuth.getInstance()
            notesRVModalArrayList = ArrayList()
            // on below line initializing our adapter class.
            // on below line initializing our adapter class.
            notesRVAdapter = Adapter(notesRVModalArrayList, this, this)
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
                    val sdata = snapshot.getValue(Model::class.java)
                    if(sdata!!.tag == "BCA"){
                        notesRVModalArrayList.add(snapshot.getValue(Model::class.java)!!)
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
                        this@categoriesData,
                        "Fail to get the data" + error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
        else if(data1111111 == "data1111111"){
            //data retrive for rcv
            notesRV = findViewById(R.id.rcv)
            loadingPB = findViewById(R.id.progress_small1v)
            // mAuth = FirebaseAuth.getInstance()
            notesRVModalArrayList = ArrayList()
            // on below line initializing our adapter class.
            // on below line initializing our adapter class.
            notesRVAdapter = Adapter(notesRVModalArrayList, this, this)
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
                    val sdata = snapshot.getValue(Model::class.java)
                    if(sdata!!.tag == "BBA"){
                        notesRVModalArrayList.add(snapshot.getValue(Model::class.java)!!)
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
                        this@categoriesData,
                        "Fail to get the data" + error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }
        else if(data11111111 == "data11111111"){
            //data retrive for rcv
            notesRV = findViewById(R.id.rcv)
            loadingPB = findViewById(R.id.progress_small1v)
            // mAuth = FirebaseAuth.getInstance()
            notesRVModalArrayList = ArrayList()
            // on below line initializing our adapter class.
            // on below line initializing our adapter class.
            notesRVAdapter = Adapter(notesRVModalArrayList, this, this)
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
                    val sdata = snapshot.getValue(Model::class.java)
                    if(sdata!!.tag == "Others"){
                        notesRVModalArrayList.add(snapshot.getValue(Model::class.java)!!)
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
                        this@categoriesData,
                        "Fail to get the data" + error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }

    }


    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filteredNames = ArrayList<Model>()
        //looping through existing elements and adding the element to filtered list
        notesRVModalArrayList.filterTo(filteredNames) {
            //if the existing elements contains the search input
            it.title.toLowerCase().contains(text.toLowerCase())
        }
        //calling a method of the adapter class and passing the filtered list
        notesRVAdapter.filterList(filteredNames)
    }
    override fun onDownloadClick(position: Int, notesRVModal: Model, holder: Adapter.ViewHolder) {

        // Create the object of AlertDialog Builder class
        val builder = AlertDialog.Builder(this)
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
            hashMap.put("User", FirebaseAuth.getInstance().currentUser?.uid.toString())
            FirebaseDatabase.getInstance().getReference("Notes")
                .child(notesRVModal.key).child("views")
                .child(FirebaseAuth.getInstance().currentUser?.uid.toString()).updateChildren(hashMap)
            val intent = Intent(this, pdfView::class.java)
            intent.putExtra("lk", notesRVModal.link)
            intent.putExtra("lkx", notesRVModal.key)
            intent.putExtra("lkxxx", notesRVModal.uid)
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

    override fun onMenuClick(position: Int, notesRVModal: Model, holder: Adapter.ViewHolder) {
    }
}