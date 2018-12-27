package nilherman.funfacts.presentation.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import nilherman.funfacts.R
import nilherman.funfacts.data.apiclient.Repository
import nilherman.funfacts.data.apiclient.RestApi
import nilherman.funfacts.data.model.Response
import nilherman.funfacts.data.model.ResultsItem
import nilherman.funfacts.presentation.detail.DetailsActivity
import retrofit2.Call
import retrofit2.Callback


class MainActivity : AppCompatActivity(), Callback<Response> {
    override fun onFailure(call: Call<Response>?, t: Throwable?) {
        Log.d("", t?.message.toString())
    }

    override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
        recyclerView.adapter = CharactersAdapter(response?.body()?.data?.results, this) { character: ResultsItem -> onCharactersClicked(character) }
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        Repository().getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)

        button.setOnClickListener{
            var searchFilter = search.text.toString()
            if (searchFilter.isEmpty()) {
                Repository().getCharacter(RestApi.apikey, RestApi.ts,RestApi.hash).enqueue(this)
            } else {
                Repository().getCharacter(RestApi.apikey, RestApi.ts,RestApi.hash, searchFilter).enqueue(this)
            }
        }
    }

    private fun onCharactersClicked(character : ResultsItem) {
//        Toast.makeText(this, "Clicked: ${character.name}", Toast.LENGTH_LONG).show()
        var intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("SUPERHERO", character)
        startActivity(intent)
    }
}
