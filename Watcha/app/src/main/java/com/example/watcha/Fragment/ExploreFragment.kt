package com.example.watcha.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.watcha.Adapter.ExploreHorizontalAdapter
import com.example.watcha.R
import com.example.watcha.api.Movie
import com.example.watcha.api.YOUTUBE_KEY
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.fragment_home.*

class ExploreFragment : Fragment() {
    lateinit var list:ArrayList<Movie>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        list= arguments?.getParcelableArrayList<Movie>("subList") as ArrayList<Movie>
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    fun init(){
        youtube_v.play(YOUTUBE_KEY)
        explore_rv.adapter=ExploreHorizontalAdapter(list)
        explore_rv.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(explore_rv)
    }



}