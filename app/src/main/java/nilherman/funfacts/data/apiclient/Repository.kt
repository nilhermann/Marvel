package nilherman.funfacts.data.apiclient

import nilherman.funfacts.data.model.characters.Response as Characters
import nilherman.funfacts.data.model.comics.Response as Comics
import retrofit2.Call

class Repository : MarvelApi {
    override fun getCharacter(apikey: String, ts: String, hash: String, startsWith: String): Call<Characters> {
        val result = RestApi().marvelApi.getCharacter(apikey, ts, hash, startsWith)
        return result
    }

    override fun getCharacter(apikey: String, ts: String, hash: String): Call<Characters> {
        val result = RestApi().marvelApi.getCharacter(apikey, ts, hash)
        return result
    }

    override fun getComic(apikey: String, ts: String, hash: String, startsWith: String): Call<Comics> {
        val result = RestApi().marvelApi.getComic(apikey, ts, hash, startsWith)
        return result
    }

    override fun getComic(apikey: String, ts: String, hash: String): Call<Comics> {
        val result = RestApi().marvelApi.getComic(apikey, ts, hash)
        return result
    }
}

