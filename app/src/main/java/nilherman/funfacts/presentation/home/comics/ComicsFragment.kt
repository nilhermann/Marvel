package nilherman.funfacts.presentation.home.comics

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import nilherman.funfacts.COMIC
import nilherman.funfacts.R
import nilherman.funfacts.data.apiclient.Repository
import nilherman.funfacts.data.apiclient.RestApi
import nilherman.funfacts.data.model.comics.ComicsItem
import nilherman.funfacts.data.model.comics.Response
import nilherman.funfacts.presentation.BaseFragment
import retrofit2.Call
import retrofit2.Callback

class ComicsFragment : BaseFragment(), Callback<Response> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_list, container, false)
        setHasOptionsMenu(true)

        initRecyclerView(view)

        return view
    }

    override fun onStart() {
        super.onStart()

        //initListeners()
        Repository().getComic(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)
    }

    private fun initRecyclerView(view : View) {

        view.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onFailure(call: Call<Response>?, t: Throwable?) {
        Log.d("", t?.message.toString())
    }

    override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
        context?.let {context ->
                recyclerView.adapter = ComicsAdapter(response?.body()?.data?.results, context) {
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

    fun View.closeKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
//        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        if (null != searchView) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(ComponentName.unflattenFromString(String())))
//            searchView.setIconifiedByDefault(false)
//        }

        fun doSearch() {
            val searchFilter = searchView.toString()
            if (searchFilter.isEmpty()) {
                Repository().getComic(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)
            } else {
                Repository().getComic(RestApi.apikey, RestApi.ts, RestApi.hash, searchFilter).enqueue(this)
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.closeKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                doSearch()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }
}