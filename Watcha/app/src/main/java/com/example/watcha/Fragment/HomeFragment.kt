package com.example.watcha.Fragment

import MainAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.watcha.Adapter.OuterAdapter
import com.example.watcha.R
import com.example.watcha.Time
import com.example.watcha.activity.DetailActivity
import com.example.watcha.api.DailyBoxOffice
import com.example.watcha.api.Movie
import com.example.watcha.api.MovieRetrofit
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_outer.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    val list = arrayListOf<Movie>()
    val movie= arrayListOf<DailyBoxOffice>()
    var loadCounter=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit: MovieRetrofit by inject()
        setAdapter()
        if (loadCounter==false){
            loadDB(retrofit)
        }
    }
    fun setAdapter(){
        setMainAdapter()
        setSubAdapter()
    }
    fun setMainAdapter(){
        rv_main.adapter = MainAdapter(
            movie,
            onClick = { s: String, s1: String ->
                toDetail(s, s1)
            })
        rv_main.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_main)
    }
    fun setSubAdapter(){
        rv_sub.adapter = OuterAdapter(list,onClick = {title: String, img: String ->
            toDetail(title,img)
        })
        rv_sub.layoutManager = LinearLayoutManager(context)
    }
    fun loadDB(retrofit: MovieRetrofit) {
        Log.d("TAG","loadDB")
        loadCounter=true
        getMain(retrofit)
        getSub(retrofit)
    }

    fun getMain(retrofit: MovieRetrofit) {
        retrofit.getDailyList(Time().getdateNow()).enqueue(object : retrofit2.Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                for (dailyMovie in response.body()!!.boxOfficeResult.dailyBoxOfficeList){
                    movie.add(dailyMovie)
                }
                rv_main.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
            }
        })
    }

    fun getSub(retrofit: MovieRetrofit) {
        for (i in 1..5) {
            retrofit.getDailyList(Time().getdateAYearAgo(i)).enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    list.add(response.body()!!)
                    rv_sub.adapter!!.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.d("RETRO", "failed ${t.localizedMessage}")
                }
            })
        }
    }

    fun toDetail(title: String, imgURL: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("img", imgURL)
        startActivity(intent)
    }
}