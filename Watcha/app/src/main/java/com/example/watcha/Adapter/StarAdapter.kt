package com.example.watcha.Adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watcha.R
import com.example.watcha.data.MovieDatabase
import com.example.watcha.data.MovieEntity
import org.koin.java.KoinJavaComponent.inject

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
            view.setOnClickListener {
                val builder = AlertDialog.Builder(view.context).apply {
                    setTitle("삭제하시겠습니까?")
                    setPositiveButton("확인",
                        DialogInterface.OnClickListener { Dialog, id ->
                            delMovie.invoke(movie)
                        })
                    setNegativeButton("취소") { Dialog, id ->
                    }
                }.show()
            }
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