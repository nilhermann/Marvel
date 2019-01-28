package nilherman.funfacts.data.apiclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import nilherman.funfacts.data.model.characters.Response as Characters
import nilherman.funfacts.data.model.comics.Response as Comics

class Repository {

    private val marvelApi = MarvelApiImpl()

    fun getCharacters() : LiveData<Characters> {
        var liveData = MutableLiveData<Characters>()

        marvelApi.getCharacter(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                liveData.value = response.body()
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {}
        })
        return liveData
    }

    fun getComic() : LiveData<Comics> {
        var liveData = MutableLiveData<Comics>()

        marvelApi.getComic(RestApi.apikey, RestApi.ts, RestApi.hash).enqueue(object : Callback<Comics> {
            override fun onResponse(call: Call<Comics>, response: Response<Comics>) {
                liveData.value = response.body()
            }

            override fun onFailure(call: Call<Comics>, t: Throwable) {}
        })
        return liveData
    }
}