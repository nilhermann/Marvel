package nilherman.funfacts.data.comics.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(

        @field:SerializedName("path")
        val path: String? = null,

        @field:SerializedName("extension")
        val extension: String? = null
) : Parcelable