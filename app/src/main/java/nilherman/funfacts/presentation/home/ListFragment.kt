package nilherman.funfacts.presentation.home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import nilherman.funfacts.R
import nilherman.funfacts.data.apiclient.Repository
import nilherman.funfacts.data.apiclient.RestApi
import nilherman.funfacts.data.model.Response
import nilherman.funfacts.data.model.ResultsItem
import nilherman.funfacts.presentation.detail.DetailsFragment
import retrofit2.Call
import retrofit2.Callback

class ListFragment : Fragment() , Callback<Response> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initListeners()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Repository().getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)
    }

    private fun initListeners() {
        button.setOnClickListener{
            var searchFilter = search.text.toString()
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
        recyclerView.adapter = CharactersAdapter(response?.body()?.data?.results, requireContext()) { character: ResultsItem -> onCharactersClicked(character) }
        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun onCharactersClicked(character : ResultsItem) {
//        Toast.makeText(this, "Clicked: ${character.name}", Toast.LENGTH_LONG).show()
        activity?.intent?.putExtra("SUPERHERO", character)

        val fragment = DetailsFragment()
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }
}