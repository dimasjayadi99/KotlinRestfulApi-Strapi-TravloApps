package com.example.travlo.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travlo.R
import com.example.travlo.Response.DataItem
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.Locale

class ListAllAdapter (val allList : List<DataItem?>?) : RecyclerView.Adapter<ListAllAdapter.MyViewHolder>() {
    class MyViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        val thumb = view.findViewById<ImageView>(R.id.thumb)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val place = view.findViewById<TextView>(R.id.tv_place)
        val rate = view.findViewById<TextView>(R.id.tv_rate)
        val price = view.findViewById<TextView>(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (allList != null){
            val limit = 15
            return allList.size.coerceAtMost(limit)
        }
        return 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = allList?.get(position)?.attributes?.name
        holder.place.text = allList?.get(position)?.attributes?.place
        holder.rate.text = "Rating : " + allList?.get(position)?.attributes?.rate.toString()

        // formating number price
        val price = allList?.get(position)?.attributes?.price?.toInt()
        val localID = Locale("in","ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localID)
        holder.price.text = numberFormat.format(price)

        Picasso.get().load("http://10.0.2.2:1337"+allList?.get(position)?.attributes?.thumb?.data?.attributes?.url).into(holder.thumb)

    }
}