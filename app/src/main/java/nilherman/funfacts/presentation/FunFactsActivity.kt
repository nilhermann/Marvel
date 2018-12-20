package nilherman.funfacts.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_fun_facts.*
import nilherman.funfacts.R
import nilherman.funfacts.data.apiclient.Repository
import nilherman.funfacts.data.apiclient.RestApi
import nilherman.funfacts.data.model.Response
import retrofit2.Call
import retrofit2.Callback

class FunFactsActivity : AppCompatActivity(), Callback<Response> {
    override fun onFailure(call: Call<Response>?, t: Throwable?) {
        Log.d("", t?.message.toString())
    }

    override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
        recyclerView.adapter = FrasesAdapter(response?.body()?.data?.results, this)
    }

    val facts: List<String> = FactBook().factsList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fun_facts)

        recyclerView.layoutManager = LinearLayoutManager(this)

        //recyclerView.adapter = FrasesAdapter(facts, this)

        Repository().getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(this)

    }
}
