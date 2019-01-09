package nilherman.funfacts.data.model.comics

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import nilherman.funfacts.data.model.common.Thumbnail

@Parcelize
data class ComicsItem (
        @field:SerializedName("thumbnail")
        val thumbnail: Thumbnail? = null,
        @field:SerializedName("title")
        val title: String? = null,
        @field:SerializedName("description")
        val description: String? = null
) : Parcelable