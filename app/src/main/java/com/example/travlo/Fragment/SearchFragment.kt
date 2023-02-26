package com.example.travlo.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travlo.Adapter.ListAllAdapter
import com.example.travlo.Api.Destination.ApiConfigDestionation
import com.example.travlo.R
import com.example.travlo.Response.ResponseDestination
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

class SearchFragment : Fragment() {

    lateinit var rv_destination : RecyclerView
    lateinit var searchView: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = LayoutInflater.from(parentFragment?.context).inflate(R.layout.fragment_search, container, false)

        searchView = view.findViewById(R.id.sv_destination)

        rv_destination = view.findViewById(R.id.rv_destination)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null){
                    getSearchList(view,p0)
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                getListDestination(view)

                return true
            }

        })

        getListDestination(view)

        return view

    }

    private fun getSearchList(view: View?, p0: String) {
        ApiConfigDestionation.getService().getSearchList(p0).enqueue(object : Callback<ResponseDestination>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ResponseDestination>, response: Response<ResponseDestination>) {
                val responseBody = response.body()
                val responseList = responseBody?.data
                val searhAdapter = ListAllAdapter(responseList)
                rv_destination.apply {
                    layoutManager = LinearLayoutManager(view?.context)
                    setHasFixedSize(true)
                    searhAdapter.notifyDataSetChanged()
                    adapter = searhAdapter
                }
            }

            override fun onFailure(call: Call<ResponseDestination>, t: Throwable) {
                Toast.makeText(view?.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getListDestination(view: View?) {
        ApiConfigDestionation.getService().getListAll().enqueue(object : Callback<ResponseDestination>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ResponseDestination>, response: Response<ResponseDestination>) {
                val responseBody = response.body()
                val responseList = responseBody?.data
                val searhAdapter = ListAllAdapter(responseList)
                rv_destination.apply {
                    layoutManager = LinearLayoutManager(view?.context)
                    setHasFixedSize(true)
                    searhAdapter.notifyDataSetChanged()
                    adapter = searhAdapter
                }
            }

            override fun onFailure(call: Call<ResponseDestination>, t: Throwable) {
                Toast.makeText(view?.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}