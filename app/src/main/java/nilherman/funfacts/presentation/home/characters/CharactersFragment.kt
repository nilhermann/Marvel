package nilherman.funfacts.presentation.home.characters

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_list.*
import nilherman.funfacts.R
import nilherman.funfacts.SUPERHERO
import nilherman.funfacts.data.apiclient.Repository
import nilherman.funfacts.data.apiclient.RestApi
import nilherman.funfacts.data.model.characters.Response
import nilherman.funfacts.data.model.characters.ResultsItem
import nilherman.funfacts.presentation.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import android.support.v7.widget.SearchView
import android.widget.SearchView.OnQueryTextListener

class CharactersFragment : BaseFragment() , Callback<Response> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val string: String = getString(R.string.characters)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
//        initListeners()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Repository().getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)
    }

//    private fun initListeners() {
//        button.setOnClickListener{
//            val searchFilter = search.text.toString()
//            if (searchFilter.isEmpty()) {
//                Repository().getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)
//            } else {
//                Repository().getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash, searchFilter).enqueue(this)
//            }
//        }
//    }

    override fun onFailure(call: Call<Response>?, t: Throwable?) {
        Log.d("", t?.message.toString())
    }

    override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
        recyclerView.let {
            recyclerView.adapter = CharactersAdapter(response?.body()?.data?.results, requireContext()) {
                character: ResultsItem -> onCharactersClicked(character)
            }
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun onCharactersClicked(character : ResultsItem) {
        removeExtras()
        activity?.intent?.putExtra(SUPERHERO, character)
        activity?.intent?.putExtra("ID", SUPERHERO)

        navigateToDetails()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object :  SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(context, "HOLA", Toast.LENGTH_LONG)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        } )
        super.onCreateOptionsMenu(menu, inflater)
    }
}