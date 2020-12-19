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
import com.example.watcha.MyViewModel
import com.example.watcha.R
import com.example.watcha.activity.DetailActivity
import com.example.watcha.data.DailyBoxOffice
import com.example.watcha.data.Movie
import com.example.watcha.data.MovieRetrofit
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject


class HomeFragment : Fragment() {

    private lateinit var mainList: ArrayList<DailyBoxOffice>
    private lateinit var subList: ArrayList<Movie>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainList =
            arguments?.getParcelableArrayList<DailyBoxOffice>("mainList") as ArrayList<DailyBoxOffice>
        subList = arguments?.getParcelableArrayList<Movie>("subList") as ArrayList<Movie>
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    fun setAdapter() {
        val viewModel: MyViewModel by inject()
        setMainAdapter()
        setSubAdapter()
    }

    fun setMainAdapter() {
        rv_main.adapter = MainAdapter(
            mainList,
            onClick = { s: String, s1: String ->
                toDetail(s, s1)
            })
        rv_main.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_main)
    }

    fun setSubAdapter() {
        rv_sub.adapter = OuterAdapter(subList, onClick = { title: String, img: String ->
            toDetail(title, img)
        })
        rv_sub.layoutManager = LinearLayoutManager(context)
    }

    fun loadDB(retrofit: MovieRetrofit) {
        Log.d("TAG", "loadDB")
    }

    fun toDetail(title: String, imgURL: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("img", imgURL)
        startActivity(intent)
    }
}