package com.example.watcha.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watcha.R
import com.example.watcha.data.MovieEntity

class StarAdapter(
    val movieList: ArrayList<MovieEntity>,
    val delMovie: (movieEntity: MovieEntity) -> Unit
) : RecyclerView.Adapter<StarAdapter.ViewHolder>() {
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.star_img)
        val tvTitle = view.findViewById<TextView>(R.id.star_tv_title)
        val tvDate = view.findViewById<TextView>(R.id.star_tv_date)
        fun bind(movie: MovieEntity) {
            Glide.with(view.context)
                .load(movie.imgUrl)
                .centerCrop()
                .into(img)
            tvTitle.text = movie.title
            tvDate.text = "${movie.date} 저장됨"
//            view.setOnClickListener {
//
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.star_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}