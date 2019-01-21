package nilherman.funfacts.presentation.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import nilherman.funfacts.R
import nilherman.funfacts.data.model.characters.ResultsItem
import nilherman.funfacts.data.model.comics.ComicsItem

class DetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
    }

    private fun loadData() {
        when (activity!!.intent.getStringExtra("ID")) {
            "COMIC" -> {
                var comic = activity!!.intent.getParcelableExtra<ComicsItem>("COMIC")
                comic.let { comic ->
                    tvCharacters.text = comic.title
                    tvDescription.text = comic.description
                    Glide.with(this)
                            .load(comic?.thumbnail?.path+ "." + comic?.thumbnail?.extension)
                            .into(imThumbnail)
                }
            }
            "SUPERHERO" -> {
                var character = activity!!.intent.getParcelableExtra<ResultsItem>("SUPERHERO")
                character.let { character ->
                    tvCharacters.text = character.name
                    tvDescription.text = character.description
                    Glide.with(this)
                            .load(character?.thumbnail?.path+ "." + character?.thumbnail?.extension)
                            .into(imThumbnail)
                }
            }
        }
    }
}