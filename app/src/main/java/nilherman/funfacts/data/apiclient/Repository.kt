package nilherman.funfacts.data.apiclient

import androidx.lifecycle.LiveData
import nilherman.funfacts.data.model.characters.Response as Characters
import nilherman.funfacts.data.model.comics.Response as Comics

class Repository : MarvelApi {
    // TODO intentar startWith = null
    override fun getCharacter(apikey: String, ts: String, hash: String, startsWith: String): LiveData<Characters> {
        return RestApi().marvelApi.getCharacter(apikey, ts, hash, startsWith)
    }

    override fun getCharacter(apikey: String, ts: String, hash: String): LiveData<Characters> {
        return RestApi().marvelApi.getCharacter(apikey, ts, hash)
    }

    override fun getComic(apikey: String, ts: String, hash: String, startsWith: String): LiveData<Comics> {
        return RestApi().marvelApi.getComic(apikey, ts, hash, startsWith)
    }

    override fun getComic(apikey: String, ts: String, hash: String): LiveData<Comics> {
        return RestApi().marvelApi.getComic(apikey, ts, hash)
    }
}