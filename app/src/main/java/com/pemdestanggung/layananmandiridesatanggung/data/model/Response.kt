package com.pemdestanggung.layananmandiridesatanggung.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: ArrayList<DataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable