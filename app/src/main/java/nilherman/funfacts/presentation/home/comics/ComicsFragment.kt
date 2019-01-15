package nilherman.funfacts.presentation.home.comics

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.support.v7.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_list.*
import nilherman.funfacts.COMIC
import nilherman.funfacts.R
import nilherman.funfacts.data.apiclient.Repository
import nilherman.funfacts.data.apiclient.RestApi
import nilherman.funfacts.data.model.comics.ComicsItem
import nilherman.funfacts.data.model.comics.Response
import nilherman.funfacts.presentation.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import android.widget.SearchView.OnQueryTextListener

class ComicsFragment : BaseFragment(), Callback<Response> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val string: String = getString(R.string.comics)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
//        initListeners()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        Repository().getComic(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)
    }

//    private fun initListeners() {
//        toolbar.setOnMenuItemClickListener{menuItem ->
//            when (menuItem.itemId) {
//                R.id.action_search -> {
//                    // TODO lo que quieras
//                    val searchFilter = menuItem.
//                    if (searchFilter.isEmpty()) {
//                        Repository().getComic(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)
//                    } else {
//                        Repository().getComic(RestApi.apikey, RestApi.ts, RestApi.hash, searchFilter).enqueue(this)
//                    }
//                    true
//                }
//                else -> true
//            }
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(context, "HOLA", Toast.LENGTH_LONG)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false            }
        } )
        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onFailure(call: Call<Response>?, t: Throwable?) {
        Log.d("", t?.message.toString())
    }

    override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
        recyclerView.let {
                recyclerView.adapter = ComicsAdapter(response?.body()?.data?.results, requireContext()) {
                    comic: ComicsItem -> onComicClicked(comic)
                }
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun onComicClicked(comic : ComicsItem) {
        removeExtras()
        activity?.intent?.putExtra(COMIC, comic)
        activity?.intent?.putExtra("ID", COMIC)

        navigateToDetails()
    }
}