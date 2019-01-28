package nilherman.funfacts.data.apiclient

import nilherman.funfacts.data.model.characters.Response as Characters
import nilherman.funfacts.data.model.comics.Response as Comics
import retrofit2.Call

class MarvelApiImpl : MarvelApi {

    override fun getCharacter(apikey: String, ts: String, hash: String, nameStartsWith: String): Call<Characters> {
        return RestApi().marvelApi.getCharacter(apikey, ts, hash, nameStartsWith)
    }

    override fun getCharacter(apikey: String, ts: String, hash: String): Call<Characters> {
        return RestApi().marvelApi.getCharacter(apikey, ts, hash)
    }

    override fun getComic(apikey: String, ts: String, hash: String, titleStartsWith: String): Call<Comics> {
        return RestApi().marvelApi.getComic(apikey, ts, hash, titleStartsWith)
    }

    override fun getComic(apikey: String, ts: String, hash: String): Call<Comics> {
        return RestApi().marvelApi.getComic(apikey, ts, hash)
    }

}