package com.example.travlo.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.travlo.Activity.DetailActivity
import com.example.travlo.R
import com.example.travlo.Response.DataItem
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class RecommendedAdapter(val listRecommend: List<DataItem?>?) : RecyclerView.Adapter<RecommendedAdapter.MyViewHolder>() {
    class MyViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val thumb = view.findViewById<ImageView>(R.id.thumb)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val place = view.findViewById<TextView>(R.id.tv_place)
        val cardview = view.findViewById<CardView>(R.id.cardview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_recommended,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (listRecommend != null) {
            val limit = 5
            return listRecommend.size.coerceAtMost(limit)
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = listRecommend?.get(position)?.attributes?.name
        holder.place.text = listRecommend?.get(position)?.attributes?.place

        val thumbUrl = "http://10.0.2.2:1337" + listRecommend?.get(position)?.attributes?.thumb?.data?.attributes?.url

        Picasso.get().load(thumbUrl).into(holder.thumb)

        val price = listRecommend?.get(position)?.attributes?.price?.toInt()
        val localID = Locale("in","ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localID)
        val formatPrice = numberFormat.format(price)

        val strFacilites = ArrayList<String>()

        for (i in listRecommend?.get(position)?.attributes?.facilities?.data?.indices!!){
            listRecommend[position]?.attributes?.facilities?.data!![i]?.attributes?.name?.let {
                strFacilites.add(
                    it
                )
            }
        }

        holder.cardview.setOnClickListener{
            val intent = Intent(it.context,DetailActivity::class.java)
            intent.putExtra("id",listRecommend?.get(position)?.id)
            intent.putExtra("thumb",thumbUrl)
            intent.putExtra("name",holder.name.text)
            intent.putExtra("tag",listRecommend?.get(position)?.attributes?.tag)
            intent.putExtra("place", holder.place.text)
            intent.putExtra("rate",listRecommend?.get(position)?.attributes?.rate)
            intent.putExtra("price",price)
            intent.putExtra("description",listRecommend?.get(position)?.attributes?.description)
            intent.putExtra("time",listRecommend?.get(position)?.attributes?.time)
            intent.putExtra("days",listRecommend?.get(position)?.attributes?.days)
            intent.putExtra("facility",strFacilites)
            it.context.startActivity(intent)
        }

    }
}