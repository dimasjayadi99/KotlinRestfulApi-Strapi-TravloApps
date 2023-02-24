package com.example.travlo.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travlo.Adapter.FacilityAdapter
import com.example.travlo.R
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    lateinit var img_thumb : ImageView

    lateinit var tv_name : TextView
    lateinit var tv_tag : TextView
    lateinit var rb_rate : RatingBar
    lateinit var tv_price : TextView
    lateinit var tv_description : TextView
    lateinit var tv_timedays : TextView
    lateinit var rv_facility : RecyclerView

    var thumb : String = ""
    var name : String = ""
    var place : String = ""
    var tag : String = ""
    var rate : Int = 0
    var price : Int = 0
    var description : String = ""
    var time : String = ""
    var days : String = ""

    // variabel facility
//    var facilities : String = ""
    var facilities = ArrayList<String>()

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tv_name = findViewById(R.id.tv_name)
        tv_tag = findViewById(R.id.tv_tag)
        rb_rate = findViewById(R.id.rb_rate)
        tv_price = findViewById(R.id.tv_price)
        tv_description = findViewById(R.id.tv_description)
        tv_timedays = findViewById(R.id.tv_timedays)

        rv_facility = findViewById(R.id.rv_facility)

        img_thumb = findViewById(R.id.img_thumb)

        thumb = intent.getStringExtra("thumb").toString()
        name = intent.getStringExtra("name").toString()
        place = intent.getStringExtra("place").toString()
        tag = intent.getStringExtra("tag").toString()
        rate = intent.getIntExtra("rate",0)
        price = intent.getIntExtra("price",0)
        description = intent.getStringExtra("description").toString()
        time = intent.getStringExtra("time").toString()
        days = intent.getStringExtra("days").toString()
        facilities = intent.getStringArrayListExtra("facility") as ArrayList<String>

        tv_name.text = "$name-($place)"
        tv_tag.text = tag
        rb_rate.rating = rate.toFloat()
        val localID = Locale("in","ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localID)
        tv_price.text = numberFormat.format(price)
        tv_description.text = description
        tv_timedays.text = "Time : $time || Days : $days"

        Picasso.get().load(thumb).into(img_thumb)

        val adapterFacility = FacilityAdapter(facilities)
        rv_facility.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            adapterFacility.notifyDataSetChanged()
            adapter = adapterFacility
        }

    }
}