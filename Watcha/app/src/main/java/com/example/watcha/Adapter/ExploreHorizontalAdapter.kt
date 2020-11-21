package com.example.watcha.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watcha.R
import com.example.watcha.api.Movie
import kotlinx.android.synthetic.main.explore_item.view.*

class ExploreHorizontalAdapter(val list:ArrayList<Movie>):
    RecyclerView.Adapter<ExploreHorizontalAdapter.ViewHolder>() {
    class ViewHolder(val view:View):RecyclerView.ViewHolder(view) {
        val rv=view.rv
        fun bind(movie: Movie){
            rv.adapter=ExploreVerticalAdapter(movie)
            rv.layoutManager=LinearLayoutManager(view.context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.explore_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}