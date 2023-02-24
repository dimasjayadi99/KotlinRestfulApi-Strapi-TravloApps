package com.example.travlo.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.travlo.Activity.DetailActivity
import com.example.travlo.R
import com.example.travlo.Request.Destination
import com.example.travlo.Response.Attributes
import com.example.travlo.Response.Data
import com.example.travlo.Response.DataItem
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*


class ListAllAdapter(val allList: List<DataItem?>?) :
    RecyclerView.Adapter<ListAllAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardview = view.findViewById<CardView>(R.id.cardview)
        val thumb = view.findViewById<ImageView>(R.id.thumb)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val place = view.findViewById<TextView>(R.id.tv_place)
        val rb_rate = view.findViewById<RatingBar>(R.id.rb_rate)
//        val rate = view.findViewById<TextView>(R.id.tv_rate)
        val price = view.findViewById<TextView>(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (allList != null) {
            val limit = 15
            return allList.size.coerceAtMost(limit)
        }
        return 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.name.text = allList?.get(position)?.attributes?.name
        holder.place.text = allList?.get(position)?.attributes?.place
        val rate = allList?.get(position)?.attributes?.rate?.toFloat()
        holder.rb_rate.rating = rate!!
//        holder.rate.text = "Rating : " + allList?.get(position)?.attributes?.rate.toString()

        // formating number price
        val price = allList?.get(position)?.attributes?.price?.toInt()
        val localID = Locale("in","ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localID)
        holder.price.text = numberFormat.format(price)

        val thumbUrl = "http://10.0.2.2:1337" + allList?.get(position)?.attributes?.thumb?.data?.attributes?.url

        Picasso.get().load(thumbUrl).into(holder.thumb)

        val strFacilites = ArrayList<String>()

        for (i in allList?.get(position)?.attributes?.facilities?.data?.indices!!){
            allList[position]?.attributes?.facilities?.data!![i]?.attributes?.name?.let {
                strFacilites.add(
                    it
                )
            }
        }

        holder.cardview.setOnClickListener{
            val intent = Intent(it.context,DetailActivity::class.java)
            intent.putExtra("id",allList?.get(position)?.id)
            intent.putExtra("thumb",thumbUrl)
            intent.putExtra("name",holder.name.text)
            intent.putExtra("tag",allList?.get(position)?.attributes?.tag)
            intent.putExtra("place", holder.place.text)
            intent.putExtra("rate",allList?.get(position)?.attributes?.rate)
            intent.putExtra("price",price)
            intent.putExtra("description",allList?.get(position)?.attributes?.description)
            intent.putExtra("time",allList?.get(position)?.attributes?.time)
            intent.putExtra("days",allList?.get(position)?.attributes?.days)
            intent.putExtra("facility",strFacilites)
            it.context.startActivity(intent)
        }

    }
}