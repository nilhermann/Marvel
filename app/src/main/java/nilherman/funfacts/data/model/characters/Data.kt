package nilherman.funfacts.data.model.characters

import com.google.gson.annotations.SerializedName
import nilherman.funfacts.data.model.characters.ResultsItem

data class Data(
	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)