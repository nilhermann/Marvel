package nilherman.funfacts

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.frases_inspiradoras.view.*

class FrasesAdapter(val facts : List<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.frases_inspiradoras, parent, false))
    }

    override fun getItemCount(): Int = facts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvFacts.text = facts[position]
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val tvFacts: TextView = view.tv_facts

}
