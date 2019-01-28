package nilherman.funfacts.presentation.home.characters

import android.content.Context
import android.os.Bundle
import android.view.*
import nilherman.funfacts.R
import nilherman.funfacts.SUPERHERO
import nilherman.funfacts.data.model.characters.ResultsItem
import nilherman.funfacts.presentation.BaseFragment
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import nilherman.funfacts.data.apiclient.MarvelApiImpl
import nilherman.funfacts.data.apiclient.Repository
import nilherman.funfacts.data.apiclient.RestApi
import nilherman.funfacts.domain.characters.CharactersViewModel

class CharactersFragment : BaseFragment() {

    private lateinit var viewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        setHasOptionsMenu(true)
        initRecyclerView(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        activity?.let {activity ->
            viewModel = ViewModelProviders.of(this).get(CharactersViewModel::class.java)
            viewModel.getCharacters().observe(this, androidx.lifecycle.Observer { characters ->
                recyclerView.adapter = CharactersAdapter(characters = characters.data?.results, context = activity) {
                    character: ResultsItem -> onCharactersClicked(character)
                }
            })
        }
    }

    private fun initRecyclerView(view : View) {
        view.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onCharactersClicked(character : ResultsItem) {
        removeExtras()
        activity?.intent?.putExtra(SUPERHERO, character)
        activity?.intent?.putExtra("ID", SUPERHERO)

        navigateToDetails()
    }

    fun View.closeKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)

        fun doSearch() {
            val searchFilter = searchView.toString()
            val marvelApi = MarvelApiImpl()
            if (searchFilter.isEmpty()) {
                marvelApi.getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash)
            } else {
                marvelApi.getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash, searchFilter)
            }
        }

        searchView.setOnQueryTextListener(object :  SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                doSearch()
                searchView.closeKeyboard()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })
    }
}