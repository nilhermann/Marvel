package nilherman.funfacts.domain.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nilherman.funfacts.data.apiclient.Repository
import nilherman.funfacts.data.model.characters.Response

class CharactersViewModel : ViewModel() {

    fun getCharacters() : LiveData<Response> {
        return Repository().getCharacters()
    }
}