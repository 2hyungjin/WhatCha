package com.example.watcha.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.watcha.Fragment.ExploreFragment
import com.example.watcha.Fragment.HomeFragment
import com.example.watcha.MyViewModel
import com.example.watcha.R
import com.example.watcha.Time
import com.example.watcha.api.Movie
import com.example.watcha.api.MovieRetrofit
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener {
    val retrofit: MovieRetrofit by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment())
            .commit()
        bottomNav.setOnNavigationItemSelectedListener(this)
        bottomNav.setOnNavigationItemReselectedListener(this)
        loadData()

    }

    fun loadData() {
        val retrofit: MovieRetrofit by inject()
        val viewModel: MyViewModel by inject()
        getMainData(retrofit, viewModel)
        getSubData(retrofit, viewModel)
        viewModel.getLoadCounter().observe(this, Observer {
            if(it==5){
                progressBar.visibility=View.INVISIBLE
                bottomNav.visibility=View.VISIBLE
            }
        })
    }

    fun getMainData(retrofit: MovieRetrofit, viewModel: MyViewModel) {
        retrofit.getDailyList(Time().getdateNow()).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                for (dailyMovie in response.body()!!.boxOfficeResult.dailyBoxOfficeList) {
                    viewModel.addMainMovie(dailyMovie)
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("RETRO", "failed ${t.localizedMessage}")
            }
        })
    }
    fun getSubData(retrofit: MovieRetrofit, viewModel: MyViewModel){
        for (i in 1..5) {
            retrofit.getDailyList(Time().getdateAYearAgo(i)).enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    viewModel.addSubMovie(response.body()!!)
                    viewModel.cnt()
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.d("RETRO", "failed ${t.localizedMessage}")
                }
            })
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmentManager = supportFragmentManager
        val homeFragment = HomeFragment()
        val exploreFragment = ExploreFragment()
        val curFrag = fragmentManager.findFragmentById(R.id.container)
        when (item.itemId) {
            R.id.menu_home -> {
                fragmentManager.beginTransaction().replace(R.id.container, homeFragment).commit()
                return true
            }

            R.id.menu_explore -> {
                fragmentManager.beginTransaction().replace(R.id.container, exploreFragment).commit()
                return true
            }
            else -> return false

        }
    }

    override fun onNavigationItemReselected(item: MenuItem) {
    }


}