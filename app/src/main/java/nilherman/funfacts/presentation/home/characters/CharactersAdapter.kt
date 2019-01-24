package nilherman.funfacts.presentation.home.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.characters_list.view.*
import nilherman.funfacts.R
import nilherman.funfacts.data.model.characters.ResultsItem

class CharactersAdapter(val characters: List<ResultsItem?>?, val context: Context, private val clickListener: (ResultsItem) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.characters_list, parent, false))
    }

    override fun getItemCount(): Int = characters?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animation : Animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
        holder.itemView.animation = animation
        holder.tvCharacters.text = characters?.get(position)?.name
        Glide.with(context)
                .load(characters?.get(position)?.thumbnail?.path+ "." + characters?.get(position)?.thumbnail?.extension)
                .into(holder.imageView)

        holder.bind(characters?.get(position)!!, clickListener)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvCharacters: TextView = view.tv_characters
    val imageView: ImageView = view.im_thumbnail
    fun bind(results: ResultsItem, clickListener: (ResultsItem) -> Unit) {
        itemView.tv_characters.text = results.name
        itemView.setOnClickListener{clickListener(results)}
    }
}
