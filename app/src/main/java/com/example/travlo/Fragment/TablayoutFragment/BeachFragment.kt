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
import com.example.travlo.Adapter.ListAllAdapter
import com.example.travlo.Api.Destination.ApiConfigDestionation
import com.example.travlo.R
import com.example.travlo.Response.ResponseDestination
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeachFragment : Fragment() {

    lateinit var rv_beach : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = LayoutInflater.from(parentFragment?.context).inflate(R.layout.fragment_beach,container,false)

        rv_beach = view.findViewById(R.id.rv_beach)
        getListBeach(view)

        return view
    }

    private fun getListBeach(view: View?) {
        ApiConfigDestionation.getService().getListBeach().enqueue(object : Callback<ResponseDestination>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ResponseDestination>, response: Response<ResponseDestination>) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    val responseList = responseBody?.data
                    val adapterBeach = ListAllAdapter(responseList)
                    rv_beach.apply {
                        layoutManager = LinearLayoutManager(view?.context)
                        setHasFixedSize(true)
                        adapterBeach.notifyDataSetChanged()
                        adapter = adapterBeach
                    }
                }else{
                    Toast.makeText(view?.context, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseDestination>, t: Throwable) {
                Toast.makeText(view?.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

}