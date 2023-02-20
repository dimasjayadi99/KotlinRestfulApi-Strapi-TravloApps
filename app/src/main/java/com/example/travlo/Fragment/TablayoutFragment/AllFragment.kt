package com.example.travlo.Fragment.TablayoutFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.travlo.Adapter.ListAllAdapter
import com.example.travlo.Api.Destination.ApiConfigDestionation
import com.example.travlo.R
import com.example.travlo.Response.ResponseDestination
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllFragment : Fragment() {

    lateinit var rv_allListDestination : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = LayoutInflater.from(parentFragment?.context).inflate(R.layout.fragment_all,container,false)

        rv_allListDestination = view.findViewById(R.id.rv_list_item)

        ApiConfigDestionation.getService().getListAll().enqueue(object : Callback<ResponseDestination>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ResponseDestination>, response: Response<ResponseDestination>) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    val responseList = responseBody?.data
                    val adapterList = ListAllAdapter(responseList)
                    rv_allListDestination.apply {
                        layoutManager = LinearLayoutManager(view.context)
                        setHasFixedSize(true)
                        adapterList.notifyDataSetChanged()
                        adapter = adapterList
                    }
                }else{
                    Toast.makeText(view.context, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseDestination>, t: Throwable) {
                Toast.makeText(view.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

        return view
    }
}