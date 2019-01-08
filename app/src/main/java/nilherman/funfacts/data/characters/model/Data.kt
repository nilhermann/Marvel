package nilherman.funfacts.data.characters.model

import com.google.gson.annotations.SerializedName

data class Data(
	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)