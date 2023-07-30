package com.contactdevloperdk.notesshareing.Main_file

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.contactdevloperdk.notesshareing.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList


class Adapter(
    notesRVModalArrayList: ArrayList<Model>,
    context: Context,
    downloadClickInterface: DownloadClickInterface,
) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    // creating variables for our list, context, interface and position.
    private var notesRVModalArrayList: ArrayList<Model>
    private val context: Context
    private val downloadClickInterface: DownloadClickInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file on below line.
        return try {
            val view: View =
                LayoutInflater.from(context).inflate(R.layout.notes_items, parent, false)
            ViewHolder(view)
        } catch (e: Exception) {
            Log.e("adapterError", e.message.toString())
            val view: View =
                LayoutInflater.from(context).inflate(R.layout.notes_items, parent, false)
            ViewHolder(view)
        }

    }
    //
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notesRVModal:Model = notesRVModalArrayList[position]
        holder.headText.text = notesRVModal.title
        holder.descText.text =  notesRVModal.des
        holder.view.setOnClickListener { downloadClickInterface.onDownloadClick(
            position,
            notesRVModal,holder,
        ) }
        holder.viewxx.setOnClickListener { downloadClickInterface.onMenuClick(
            position,
            notesRVModal,holder,
        ) }
        val colorCode = getRandomColor()
        holder.viewx.setBackgroundColor(holder.itemView.resources.getColor(colorCode,null))

        FirebaseDatabase.getInstance().getReference("Notes")
            .child(notesRVModal.key).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val checkViews =  snapshot.child("views").childrenCount.toInt() - 1
                holder.timeText.text = "Views:" + checkViews.toString()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("adapterError", error.message)
            }
        })
    }
    fun filterList(filteredNames: ArrayList<Model>) {
        this.notesRVModalArrayList = filteredNames
        notifyDataSetChanged()
    }
    private fun getRandomColor(): Int {
        val colorcode: MutableList<Int> = ArrayList()
        colorcode.add(R.color.gray)
        colorcode.add(R.color.lightgreen)
        colorcode.add(R.color.skyblue)
        colorcode.add(R.color.color1)
        colorcode.add(R.color.color2)
        colorcode.add(R.color.color3)

        colorcode.add(R.color.color4)
        colorcode.add(R.color.color5)
        colorcode.add(R.color.green)

        val random = Random()
        val number: Int = random.nextInt(colorcode.size)
        return colorcode[number]
    }

    override fun getItemCount(): Int {
        return notesRVModalArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val headText: TextView
        val descText: TextView
        val timeText: TextView
        val view: LinearLayout
        val viewx: View
        val viewxx: ImageView

        init {
            headText = itemView.findViewById(R.id.head1)
            descText = itemView.findViewById(R.id.desc1)
            timeText = itemView.findViewById(R.id.time1)
            view = itemView.findViewById(R.id.note)
            viewx = itemView.findViewById(R.id.view1)
            viewxx = itemView.findViewById(R.id.menupopbutton515)
        }
    }

    interface DownloadClickInterface {
        fun onDownloadClick(position: Int, notesRVModal: Model,holder: ViewHolder)
        fun onMenuClick(position: Int, notesRVModal: Model,holder: ViewHolder)
    }
    // creating a constructor.
    init {
        this.notesRVModalArrayList = notesRVModalArrayList
        this.context = context
        this.downloadClickInterface = downloadClickInterface
    }
}