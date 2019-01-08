package nilherman.funfacts.presentation.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_comics.*
import kotlinx.android.synthetic.main.fragment_characters.*
import nilherman.funfacts.R
import nilherman.funfacts.data.characters.model.ResultsItem
import nilherman.funfacts.data.comics.model.ComicsItem

class DetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var character = activity!!.intent.getParcelableExtra<ResultsItem>("SUPERHERO")
        tvCharacters.text = character.name
        tvDescription.text = character.description
        Glide.with(this)
                .load(character?.thumbnail?.path+ "." + character?.thumbnail?.extension)
                .into(imThumbnail)
    }
}

class ComicsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var comics = activity!!.intent.getParcelableExtra<ComicsItem>("COMICS")
        tvTitle.text = comics.title
        tvDescriptions.text = comics.description
        Glide.with(this)
                .load(comics?.image?.path+ "." + comics?.image?.extension)
                .into(imImage)
    }
}