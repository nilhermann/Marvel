package nilherman.funfacts.presentation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.frases_inspiradoras.view.*
import nilherman.funfacts.R
import nilherman.funfacts.data.model.ResultsItem

class CharactersAdapter(val characters: List<ResultsItem?>?, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.frases_inspiradoras, parent, false))
    }

    override fun getItemCount(): Int = characters?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvFacts.text = characters?.get(position)?.name
        Glide.with(context)
                .load(characters?.get(position)?.thumbnail?.path+ "." + characters?.get(position)?.thumbnail?.extension)
                .into(holder.imageView)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val tvFacts: TextView = view.tv_facts
    val imageView: ImageView = view.im_thumbnail
}
