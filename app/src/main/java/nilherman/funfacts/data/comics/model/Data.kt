package nilherman.funfacts.data.comics.model

import com.google.gson.annotations.SerializedName

data class Data(
        @field:SerializedName("comics")
        val comics: List<ComicsItem?>? = null
)