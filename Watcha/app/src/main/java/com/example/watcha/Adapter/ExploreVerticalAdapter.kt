package com.example.watcha.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watcha.R
import com.example.watcha.data.DailyBoxOffice
import com.example.watcha.data.IMG_POSTER_URL
import com.example.watcha.data.Movie

class ExploreVerticalAdapter(val movies : Movie) : RecyclerView.Adapter<ExploreVerticalAdapter.ViewHolder>() {
    class ViewHolder(val view:View):RecyclerView.ViewHolder(view) {
        val tvTitle=view.findViewById<TextView>(R.id.explore_tv_title)
        val tvCnt=view.findViewById<TextView>(R.id.explore_tv_cnt)
        val img=view.findViewById<ImageView>(R.id.explore_img)
        fun bind(movie:DailyBoxOffice){
            tvCnt.text=movie.audiCnt
            tvTitle.text=movie.movieNm
            Glide.with(view.context)
                .load(IMG_POSTER_URL)
                .centerCrop()
                .into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.explore_vertical_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies.boxOfficeResult.dailyBoxOfficeList[position])
    }

    override fun getItemCount(): Int {
        return 5
    }
}