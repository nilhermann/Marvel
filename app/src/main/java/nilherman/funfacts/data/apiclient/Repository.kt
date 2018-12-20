package nilherman.funfacts.data.apiclient

import nilherman.funfacts.data.model.Response
import retrofit2.Call

class Repository : MarvelApi {
    override fun getCharacter(apikey: String, ts: String, hash: String): Call<Response> {
        var result = RestApi().marvelApi.getCharacter(apikey, ts, hash)
        return result
    }

}

