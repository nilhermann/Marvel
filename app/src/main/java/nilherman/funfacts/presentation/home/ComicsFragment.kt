package nilherman.funfacts.presentation.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list_comics.*
import nilherman.funfacts.R
import nilherman.funfacts.data.apiclient.Repository
import nilherman.funfacts.data.apiclient.RestApi
import nilherman.funfacts.data.comics.model.ComicsItem
import nilherman.funfacts.data.comics.model.Response
import nilherman.funfacts.presentation.detail.ComicsFragment
import retrofit2.Call
import retrofit2.Callback

class ComicsFragment : Fragment() , Callback<Response> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_comics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initListeners()
    }

    private fun initRecyclerView() {
        recyclerViewComics.layoutManager = LinearLayoutManager(requireContext())

        Repository().getComic(RestApi.apikey, RestApi.ts, RestApi.hash)
    }

    private fun initListeners() {
        buttoncomics.setOnClickListener{
            var searchFilter = searchcomics.text.toString()
            if (searchFilter.isEmpty()) {
                Repository().getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash)
            } else {
                Repository().getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash, searchFilter)
            }
        }
    }

    override fun onFailure(call: Call<Response>?, t: Throwable?) {
        Log.d("", t?.message.toString())
    }

    override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
        recyclerViewComics.adapter = ComicsAdapter(response?.body()?.data?.comics, requireContext()) { comic: ComicsItem -> onComicClicked(comic) }
        recyclerViewComics.adapter?.notifyDataSetChanged()
    }


    private fun onComicClicked(comic : ComicsItem) {
        activity!!.intent.putExtra("COMIC", comic)

        val fragment = ComicsFragment()
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}