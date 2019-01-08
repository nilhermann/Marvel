package nilherman.funfacts.data.comics.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicsItem (
        @field:SerializedName("image")
        val image: Image? = null,
        @field:SerializedName("title")
        val title: String? = null,
        @field:SerializedName("description")
        val description: String? = null
) : Parcelable