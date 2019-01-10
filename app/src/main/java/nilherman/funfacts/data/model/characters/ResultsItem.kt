package nilherman.funfacts.data.model.characters

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import nilherman.funfacts.data.model.common.Thumbnail

@Parcelize
data class ResultsItem (
		@field:SerializedName("thumbnail")
		val thumbnail: Thumbnail? = null,
		@field:SerializedName("name")
		val name: String? = null,
		@field:SerializedName("description")
		val description: String? = null
) : Parcelable