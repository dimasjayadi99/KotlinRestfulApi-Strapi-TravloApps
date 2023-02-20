package com.example.travlo.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.travlo.R
import com.example.travlo.Response.DataItem
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.squareup.picasso.Picasso

class RecommendedAdapter(val listRecommend: List<DataItem?>?) : RecyclerView.Adapter<RecommendedAdapter.MyViewHolder>() {
    class MyViewHolder (view : View) : RecyclerView.ViewHolder(view){
        val thumb = view.findViewById<ImageView>(R.id.thumb)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val place = view.findViewById<TextView>(R.id.tv_place)
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

        Picasso.get().load("http://10.0.2.2:1337" + listRecommend?.get(position)?.attributes?.thumb?.data?.attributes?.url).into(holder.thumb)
    }
}