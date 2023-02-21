package com.example.travlo.Fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travlo.Adapter.RecommendedAdapter
import com.example.travlo.Api.Destination.ApiConfigDestionation
import com.example.travlo.Fragment.TablayoutFragment.*
import com.example.travlo.R
import com.example.travlo.Response.ResponseDestination
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var tab1 : TextView
    lateinit var tab2 : TextView
    lateinit var tab3 : TextView
    lateinit var tab4 : TextView
    lateinit var tab5 : TextView
    var selectedNumber : Int = 1

    lateinit var rv_horizon : RecyclerView
    lateinit var progressbar : ProgressBar

    @SuppressLint("CommitTransaction")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(parentFragment?.context).inflate(R.layout.fragment_home, container, false)

        // recyclerview horizontal
        rv_horizon = view.findViewById(R.id.horizontal_only)


        progressbar = view.findViewById(R.id.progressbar)
        progressbar.visibility = View.VISIBLE

        // call function recommended list
        getRecommendList(view)

        tab1 = view.findViewById(R.id.tab1)
        tab2 = view.findViewById(R.id.tab2)
        tab3 = view.findViewById(R.id.tab3)
        tab4 = view.findViewById(R.id.tab4)
        tab5 = view.findViewById(R.id.tab5)


        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.setReorderingAllowed(true)
        fragmentTransaction?.replace(R.id.fragmentcontainer,AllFragment::class.java,null)
        fragmentTransaction?.commit()

        tab1.setOnClickListener{
            selectTab(1)
        }
        tab2.setOnClickListener{
            selectTab(2)
        }
        tab3.setOnClickListener{
            selectTab(3)
        }
        tab4.setOnClickListener{
            selectTab(4)
        }
        tab5.setOnClickListener{
            selectTab(5)
        }

        return view
    }

    private fun getRecommendList(view: View?) {
    // get data recommended from api
        ApiConfigDestionation.getService().getListRecommended().enqueue(object : Callback<ResponseDestination>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponseDestination>,
                response: Response<ResponseDestination>
            ) {
                // Body Response

                if (response.isSuccessful) {
                    progressbar.visibility = View.GONE

                    val responseBody = response.body()
                    val responseList = responseBody?.data
                    val adapterRecommend = RecommendedAdapter(responseList)
                    rv_horizon.apply {
                        layoutManager = LinearLayoutManager(view?.context,LinearLayoutManager.HORIZONTAL,false)
                        setHasFixedSize(true)
                        adapterRecommend.notifyDataSetChanged()
                        adapter = adapterRecommend
                    }
                }else{
                    progressbar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponseDestination>, t: Throwable) {
                progressbar.visibility = View.GONE
            }
        })
    }


    @SuppressLint("CommitTransaction")
    private fun selectTab(tabNumber : Int) {
        val selectedTextView : TextView
        val nonselectedTextView1 : TextView
        val nonselectedTextView2 : TextView
        val nonselectedTextView3 : TextView
        val nonselectedTextView4 : TextView

        if (tabNumber == 1){
            selectedTextView = tab1
            nonselectedTextView1 = tab2
            nonselectedTextView2 = tab3
            nonselectedTextView3 = tab4
            nonselectedTextView4 = tab5

            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setReorderingAllowed(true)
            fragmentTransaction?.replace(R.id.fragmentcontainer,AllFragment::class.java,null)
            fragmentTransaction?.commit()
        }else if (tabNumber == 2){
            selectedTextView = tab2
            nonselectedTextView1 = tab1
            nonselectedTextView2 = tab3
            nonselectedTextView3 = tab4
            nonselectedTextView4 = tab5

            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setReorderingAllowed(true)
            fragmentTransaction?.replace(R.id.fragmentcontainer,NaturalFragment::class.java,null)
            fragmentTransaction?.commit()
        }else if (tabNumber == 3){
            selectedTextView = tab3
            nonselectedTextView1 = tab1
            nonselectedTextView2 = tab2
            nonselectedTextView3 = tab4
            nonselectedTextView4 = tab5

            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setReorderingAllowed(true)
            fragmentTransaction?.replace(R.id.fragmentcontainer,BeachFragment::class.java,null)
            fragmentTransaction?.commit()
        }else if(tabNumber == 4){
            selectedTextView = tab4
            nonselectedTextView1 = tab1
            nonselectedTextView2 = tab2
            nonselectedTextView3 = tab3
            nonselectedTextView4 = tab5

            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setReorderingAllowed(true)
            fragmentTransaction?.replace(R.id.fragmentcontainer,ForestFragment::class.java,null)
            fragmentTransaction?.commit()
        }else{
            selectedTextView = tab5
            nonselectedTextView1 = tab1
            nonselectedTextView2 = tab2
            nonselectedTextView3 = tab3
            nonselectedTextView4 = tab4

            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.setReorderingAllowed(true)
            fragmentTransaction?.replace(R.id.fragmentcontainer,HistoricFragment::class.java,null)
            fragmentTransaction?.commit()
        }

        val slideTo : Float = ((tabNumber - selectedNumber) * selectedTextView.width).toFloat()
        val translateAnimation = TranslateAnimation(0F,slideTo, 0F, 0F)
        if (selectedNumber == 1){
            tab1.startAnimation(translateAnimation)
        }else if (selectedNumber == 2){
            tab2.startAnimation(translateAnimation)
        }else if (selectedNumber == 3){
            tab3.startAnimation(translateAnimation)
        }else if (selectedNumber == 4){
            tab4.startAnimation(translateAnimation)
        }else if (selectedNumber == 5){
            tab5.startAnimation(translateAnimation)
        }

        translateAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                selectedTextView.setBackgroundResource(R.drawable.selected_background)
                selectedTextView.setTypeface(null,Typeface.BOLD)
                selectedTextView.setTextColor(Color.WHITE)

                nonselectedTextView1.setBackgroundResource(R.drawable.nonselected_background)
                nonselectedTextView1.setTypeface(null,Typeface.NORMAL)
                nonselectedTextView1.setTextColor(Color.GRAY)

                nonselectedTextView2.setBackgroundResource(R.drawable.nonselected_background)
                nonselectedTextView2.setTypeface(null,Typeface.NORMAL)
                nonselectedTextView2.setTextColor(Color.GRAY)

                nonselectedTextView3.setBackgroundResource(R.drawable.nonselected_background)
                nonselectedTextView3.setTypeface(null,Typeface.NORMAL)
                nonselectedTextView3.setTextColor(Color.GRAY)

                nonselectedTextView4.setBackgroundResource(R.drawable.nonselected_background)
                nonselectedTextView4.setTypeface(null,Typeface.NORMAL)
                nonselectedTextView4.setTextColor(Color.GRAY)

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

        selectedNumber = tabNumber

    }

}