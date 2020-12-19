package com.example.watcha.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watcha.R
import com.example.watcha.data.DailyBoxOffice
import com.example.watcha.data.Movie
import com.example.watcha.data.TITLE
import kotlinx.android.synthetic.main.fragment_outer.view.*

class OuterAdapter(
    val list: ArrayList<Movie>,
    val onClick:(title:String,img:String)->Unit
) : RecyclerView.Adapter<OuterAdapter.ViewHolder>() {
    lateinit var context: Context

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val imgPoster = view.findViewById<ImageView>(R.id.img_poster)
        fun bind(movieList: List<DailyBoxOffice>) {
            view.tv_title.text = TITLE[adapterPosition]
            view.rv_outter.adapter = InnerAdapter(movieList,onClick)
            view.rv_outter.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_outer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position].boxOfficeResult.dailyBoxOfficeList)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}