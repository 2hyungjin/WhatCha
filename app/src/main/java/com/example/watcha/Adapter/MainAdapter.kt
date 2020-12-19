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
import kotlinx.android.synthetic.main.rv_inner_item.view.*

class MainAdapter(val list :List<DailyBoxOffice>
                  ,val onClick :(title:String,img:String)->Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvName=view.findViewById<TextView>(R.id.tv_name)
        val imgPoster=view.findViewById<ImageView>(R.id.img_poster)
        fun bind(movie: DailyBoxOffice){
            tvName.text=movie.movieNm
            Glide.with(view.context)
                .load(IMG_POSTER_URL)
                .centerCrop()
                .into(view.img_poster)
            view.setOnClickListener {
                onClick.invoke(movie.movieNm, IMG_POSTER_URL)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.main_rv_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}