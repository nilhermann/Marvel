package nilherman.funfacts.data.comics.model

import com.google.gson.annotations.SerializedName

data class Response(

        @field:SerializedName("data")
        val data: Data? = null
)