package com.example.watcha.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcha.Adapter.StarAdapter
import com.example.watcha.MyViewModel
import com.example.watcha.R
import com.example.watcha.data.MovieDatabase
import com.example.watcha.data.MovieEntity
import kotlinx.android.synthetic.main.fragment_star.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class StarFragment : Fragment() {
    val db: MovieDatabase by inject()
    val viewModel: MyViewModel by inject()
    var movieList: ArrayList<MovieEntity>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_star, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val job = lifecycleScope.launch(Dispatchers.IO) {
            movieList = db.getDao().selectAll() as ArrayList<MovieEntity>
            viewModel.loadData(movieList!!)
        }
        viewModel.getSavedList().observe(this, Observer {
            rv_star.layoutManager = LinearLayoutManager(context)
            rv_star.adapter = StarAdapter(movieList!!) {
                delMovie(it)
            }
        })
    }

    fun delMovie(movieEntity: MovieEntity) {
        lifecycleScope.launch(Dispatchers.IO){
            db.getDao().deleteMovie(movieEntity)
        }
        movieList?.remove(movieEntity)
        rv_star.adapter?.notifyDataSetChanged()
    }
}