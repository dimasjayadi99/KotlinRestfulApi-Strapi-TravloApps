package com.example.travlo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travlo.R
import java.util.ArrayList

class FacilityAdapter(val listFacility: ArrayList<String>) : RecyclerView.Adapter<FacilityAdapter.MyViewHolder>() {
    class MyViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        val tv_facility = view.findViewById<TextView>(R.id.tv_facility)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_facility,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (listFacility != null){
            return listFacility.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_facility.text = listFacility[position]
    }
}