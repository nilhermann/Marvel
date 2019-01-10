package nilherman.funfacts.data.model.comics

import com.google.gson.annotations.SerializedName
import nilherman.funfacts.data.model.characters.ResultsItem

data class Data(
	@field:SerializedName("results")
	val results: List<ComicsItem?>? = null
)