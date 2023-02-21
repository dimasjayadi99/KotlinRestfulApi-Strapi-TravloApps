package com.example.travlo.Fragment.TablayoutFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
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
    lateinit var layout_error_message: LinearLayout
    lateinit var progressbar : ProgressBar
    lateinit var error_text: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = LayoutInflater.from(parentFragment?.context).inflate(R.layout.fragment_all,container,false)

        rv_allListDestination = view.findViewById(R.id.rv_list_item)

        layout_error_message = view.findViewById(R.id.layout_error_message)
        layout_error_message.visibility = View.GONE

        progressbar = view.findViewById(R.id.progressbar)
        progressbar.visibility = View.VISIBLE

        error_text = view.findViewById(R.id.error_text)

        getAllDestination(view)

        return view
    }

    private fun getAllDestination(view: View?) {
        ApiConfigDestionation.getService().getListAll().enqueue(object : Callback<ResponseDestination>{
            @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
            override fun onResponse(call: Call<ResponseDestination>, response: Response<ResponseDestination>) {
                progressbar.visibility = View.GONE
                if (response.isSuccessful){
                    if (response.body()?.data?.isNotEmpty() == true) {
                        layout_error_message.visibility = View.GONE
                        val responseBody = response.body()
                        val responseList = responseBody?.data
                        val adapterList = ListAllAdapter(responseList)
                        rv_allListDestination.apply {
                            layoutManager = LinearLayoutManager(view?.context)
                            setHasFixedSize(true)
                            adapterList.notifyDataSetChanged()
                            adapter = adapterList
                        }
                    }else{
                        layout_error_message.visibility = View.VISIBLE
                    }
                }else{
                    error_text.text = "Gagal memuat data\nterjadi kesalahan jaringan atau server"
                    layout_error_message.visibility = View.VISIBLE
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<ResponseDestination>, t: Throwable) {
                error_text.text = "Gagal memuat data\nterjadi kesalahan jaringan atau server"
                progressbar.visibility = View.GONE
                layout_error_message.visibility = View.VISIBLE
//                Toast.makeText(view.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}