package nilherman.funfacts.data.model.characters

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("data")
	val data: Data? = null
)