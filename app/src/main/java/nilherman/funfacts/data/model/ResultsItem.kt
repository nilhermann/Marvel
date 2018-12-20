package nilherman.funfacts.data.model

import com.google.gson.annotations.SerializedName

data class ResultsItem(

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,
	@field:SerializedName("name")
	val name: String? = null
)