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

class NaturalFragment : Fragment() {

    lateinit var rv_natural : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = LayoutInflater.from(parentFragment?.context).inflate(R.layout.fragment_natural,container,false)

        rv_natural = view.findViewById(R.id.rv_list_natural)

        getListNatural(view)

        return view
    }

    private fun getListNatural(view: View?) {
        ApiConfigDestionation.getService().getListNatural().enqueue(object : Callback<ResponseDestination>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ResponseDestination>, response: Response<ResponseDestination>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val responseList = responseBody?.data
                    val adapterNatural = ListAllAdapter(responseList)
                    rv_natural.apply {
                        layoutManager = LinearLayoutManager(view?.context)
                        setHasFixedSize(true)
                        adapterNatural.notifyDataSetChanged()
                        adapter = adapterNatural
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