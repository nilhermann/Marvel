package nilherman.funfacts.presentation.home.characters

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class CharactersFragment : BaseFragment() , Callback<Response> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val string: String = getString(R.string.characters)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTitle()
        initRecyclerView()
        initListeners()
    }

    private fun initTitle() {
        title.text = getString(R.string.characters)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Repository().getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)
    }

    private fun initListeners() {
        button.setOnClickListener{
            val searchFilter = search.text.toString()
            if (searchFilter.isEmpty()) {
                Repository().getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)
            } else {
                Repository().getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash, searchFilter).enqueue(this)
            }
        }
    }

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
}