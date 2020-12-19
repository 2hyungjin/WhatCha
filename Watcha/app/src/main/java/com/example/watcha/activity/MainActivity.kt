package com.example.watcha.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.example.watcha.Fragment.ExploreFragment
import com.example.watcha.Fragment.HomeFragment
import com.example.watcha.Fragment.StarFragment
import com.example.watcha.MyViewModel
import com.example.watcha.R
import com.example.watcha.Time
import com.example.watcha.data.DailyBoxOffice
import com.example.watcha.data.Movie
import com.example.watcha.data.MovieRetrofit
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener {
    private val homeFragment=HomeFragment()
    val mainList= arrayListOf<DailyBoxOffice>()
    val subList= arrayListOf<Movie>()
    private val bundle=Bundle()
    val retrofit: MovieRetrofit by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            if(it==6){
                progressBar.visibility=View.INVISIBLE
                bottomNav.visibility=View.VISIBLE
                bundle.putParcelableArrayList("mainList",mainList)
                bundle.putParcelableArrayList("subList",subList)
                homeFragment.arguments=bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, homeFragment)
                    .commit()
            }
        })
    }

    fun getMainData(retrofit: MovieRetrofit, viewModel: MyViewModel) {
        retrofit.getDailyList(Time().getdateNow()).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                for (dailyMovie in response.body()!!.boxOfficeResult.dailyBoxOfficeList) {
                    mainList.add(dailyMovie)
                }
                viewModel.cnt()

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
                    subList.add(response.body()!!)
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
        val starFragment=StarFragment()
        when (item.itemId) {
            R.id.menu_home -> {
                homeFragment.arguments=bundle
                fragmentManager.beginTransaction().replace(R.id.container, homeFragment).commit()
                return true
            }

            R.id.menu_explore -> {
                exploreFragment.arguments=bundle
                fragmentManager.beginTransaction().replace(R.id.container, exploreFragment).commit()
                return true
            }
            R.id.menu_star->{
                fragmentManager.beginTransaction().replace(R.id.container,starFragment).commit()
                return true
            }
            else -> return false

        }
    }

    override fun onNavigationItemReselected(item: MenuItem) {
    }


}