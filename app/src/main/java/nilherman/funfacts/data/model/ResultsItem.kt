package nilherman.funfacts.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultsItem (
	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("description")
	val description: String? = null
) : Parcelable