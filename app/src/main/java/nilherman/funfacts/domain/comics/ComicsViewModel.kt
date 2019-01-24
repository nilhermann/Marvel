package nilherman.funfacts.domain.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nilherman.funfacts.data.apiclient.Repository
import nilherman.funfacts.data.apiclient.RestApi
import nilherman.funfacts.data.model.comics.Response

class ComicsViewModel : ViewModel() {

    fun getComic() : LiveData<Response> {
        return Repository().getComic(RestApi.apikey, RestApi.ts, RestApi.hash)
    }
}