package nilherman.funfacts.presentation.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import nilherman.funfacts.R
import nilherman.funfacts.data.apiclient.Repository
import nilherman.funfacts.data.apiclient.RestApi
import nilherman.funfacts.data.model.comics.ComicsItem
import nilherman.funfacts.data.model.comics.Response
import nilherman.funfacts.presentation.BaseFragment
import retrofit2.Call
import retrofit2.Callback

class ComicsFragment : BaseFragment(), Callback<Response> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val string: String = getString(R.string.comics)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTitle()
        initRecyclerView()
        initListeners()
    }

    private fun initTitle() {
        title.text = getString(R.string.comics)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Repository().getComic(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)
    }

    private fun initListeners() {
        button.setOnClickListener{
            var searchFilter = search.text.toString()
            if (searchFilter.isEmpty()) {
                Repository().getComic(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)
            } else {
                Repository().getComic(RestApi.apikey, RestApi.ts, RestApi.hash, searchFilter).enqueue(this)
            }
        }
    }

    override fun onFailure(call: Call<Response>?, t: Throwable?) {
        Log.d("", t?.message.toString())
    }

    override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
        recyclerView?.let {
                recyclerView.adapter = ComicsAdapter(response?.body()?.data?.results, requireContext()) { comic: ComicsItem -> onComicClicked(comic) }
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun onComicClicked(comic : ComicsItem) {
        removeExtras()
        activity!!.intent.putExtra("COMIC", comic)
        activity!!.intent.putExtra("ID", "COMIC")

        navigateToDetails()
    }
}