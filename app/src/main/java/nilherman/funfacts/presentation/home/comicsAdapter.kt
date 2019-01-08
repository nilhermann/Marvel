package nilherman.funfacts.presentation.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.comics_list.view.*
import nilherman.funfacts.R
import nilherman.funfacts.data.comics.model.ComicsItem

class ComicsAdapter(val comics: List<ComicsItem?>?, val context: Context, val clickListener: (ComicsItem) -> Unit) : RecyclerView.Adapter<ViewHolderComics>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderComics {
        return ViewHolderComics(LayoutInflater.from(context).inflate(R.layout.comics_list, parent, false))
    }
    override fun getItemCount(): Int = comics?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolderComics, position: Int) {
        holder.tvComics.text = comics?.get(position)?.title
        Glide.with(context)
                .load(comics?.get(position)?.image?.path + "." + comics?.get(position)?.image?.extension)
                .into(holder.imageView)

        holder.bind(comics?.get(position)!!, clickListener)
    }
}

class ViewHolderComics(view: View) : RecyclerView.ViewHolder(view) {
    val tvComics: TextView = view.tv_comics
    val imageView: ImageView = view.im_image
    fun bind(comics: ComicsItem, clickListener: (ComicsItem) -> Unit) {
        itemView.tv_comics.text = comics.title
        itemView.setOnClickListener { clickListener(comics) }
    }
}