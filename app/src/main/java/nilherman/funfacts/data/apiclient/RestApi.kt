package nilherman.funfacts.data.apiclient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestApi {
    var marvelApi: MarvelApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://gateway.marvel.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        marvelApi = retrofit.create(MarvelApi::class.java)
    }

    companion object {
        const val ts : String = "1"
        const val apikey : String = "b92c8a32f48723636b09e2a282321fdc"
        const val hash : String = "4bd192734f49d060f216298dec15f576"
    }
}