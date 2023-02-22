package com.example.travlo.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travlo.R
import com.example.travlo.Response.Facilities
import java.text.NumberFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    lateinit var img_thumb : ImageView

    lateinit var tv_name : TextView
    lateinit var tv_tag : TextView
    lateinit var tv_price : TextView
    lateinit var tv_description : TextView
    lateinit var tv_timedays : TextView

    var name : String = ""
    var place : String = ""
    var tag : String = ""
    var price : Int = 0
    var description : String = ""
    var time : String = ""
    var days : String = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tv_name = findViewById(R.id.tv_name)
        tv_tag = findViewById(R.id.tv_tag)
        tv_price = findViewById(R.id.tv_price)
        tv_description = findViewById(R.id.tv_description)
        tv_timedays = findViewById(R.id.tv_timedays)

        img_thumb = findViewById(R.id.img_thumb)

        name = intent.getStringExtra("name").toString()
        place = intent.getStringExtra("place").toString()
        tag = intent.getStringExtra("tag").toString()
        price = intent.getIntExtra("price",0)
        description = intent.getStringExtra("description").toString()
        time = intent.getStringExtra("time").toString()
        days = intent.getStringExtra("days").toString()

//        val facility = intent.getSerializableExtra("facility")

        tv_name.text = "$name-($place)"
        tv_tag.text = tag
        val localID = Locale("in","ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localID)
        tv_price.text = numberFormat.format(price)
        tv_description.text = description
        tv_timedays.text = "Time : $time || Days : $days"


    }
}