package nilherman.funfacts.data.apiclient

import nilherman.funfacts.data.characters.model.Response
import retrofit2.Call

class Repository : MarvelApi {
    override fun getCharacter(apikey: String, ts: String, hash: String, startsWith: String): Call<Response> {
        var result = RestApi().marvelApi.getCharacter(apikey, ts, hash, startsWith)
        return result
    }

    override fun getCharacter(apikey: String, ts: String, hash: String): Call<Response> {
        var result = RestApi().marvelApi.getCharacter(apikey, ts, hash)
        return result
    }

    override fun getComic(apikey: String, ts: String, hash: String, startsWith: String): Call<Response> {
        var result = RestApi().marvelApi.getComic(apikey, ts, hash, startsWith)
        return result
    }

    override fun getComic(apikey: String, ts: String, hash: String): Call<Response> {
        var result = RestApi().marvelApi.getComic(apikey, ts, hash)
        return result
    }
}

