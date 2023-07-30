package com.contactdevloperdk.notesshareing

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_categories.*

class Categories : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_categories, container, false
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        material1.setOnClickListener{
            val intent = Intent(context, categoriesData::class.java)
            intent.putExtra("key","data")
            startActivity(intent)
        }
        material11.setOnClickListener{
            val intent = Intent(context, categoriesData::class.java)
            intent.putExtra("key1", "data1")
            startActivity(intent)
        }
        material111.setOnClickListener{
            val intent = Intent(context, categoriesData::class.java)
            intent.putExtra("key11", "data11")
            startActivity(intent)
        }
        material1111.setOnClickListener{
            val intent = Intent(context, categoriesData::class.java)
            intent.putExtra("key111", "data111")
            startActivity(intent)
        }
        material11111.setOnClickListener{
            val intent = Intent(context, categoriesData::class.java)
            intent.putExtra("key1111", "data1111")
            startActivity(intent)
        }
        material111111.setOnClickListener{
            val intent = Intent(context, categoriesData::class.java)
            intent.putExtra("key11111", "data11111")
            startActivity(intent)
        }
        material1111111.setOnClickListener{
            val intent = Intent(context, categoriesData::class.java)
            intent.putExtra("key111111", "data111111")
            startActivity(intent)
        }
        material11111111.setOnClickListener{
            val intent = Intent(context, categoriesData::class.java)
            intent.putExtra("key1111111", "data1111111")
            startActivity(intent)
        }
        material111111111.setOnClickListener{
            val intent = Intent(context, categoriesData::class.java)
            intent.putExtra("key11111111", "data11111111")
            startActivity(intent)
        }

    }
}
