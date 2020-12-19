package com.example.watcha.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.watcha.Adapter.InnerAdapter
import com.example.watcha.R
import com.example.watcha.Time
import com.example.watcha.data.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    val movieList = arrayListOf<DailyBoxOffice>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
    }

    fun init() {
        val db:MovieDatabase by inject()
        val title = intent.getStringExtra("title")
        val imgUrl = intent.getStringExtra("img")
        Glide.with(this)
            .load(imgUrl)
            .centerCrop()
            .into(detail_img)
        detail_tv_title.text = title
        detail_rv.adapter = InnerAdapter(movieList) { s: String, s1: String ->
            onClick(s, s1)
        }
        detail_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()
        detail_btn_like.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                db.getDao().insertMovie(MovieEntity(title!!, imgUrl!!,Time().getNow()))
                finish()
            }
            Toast.makeText(this@DetailActivity, "즐겨찾기 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show()
        }

    }

    fun getData() {
        val retrofit: MovieRetrofit by inject()
        retrofit.getDailyList(Time().getdateNow()).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                for (movie in response.body()!!.boxOfficeResult.dailyBoxOfficeList) {
                    movieList.add(movie)
                    detail_rv.adapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("RETRO", "failed")
            }
        })
    }

    fun onClick(title: String, img: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("img", img)
        startActivity(intent)
    }
}