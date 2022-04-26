package com.pemdestanggung.layananmandiridesatanggung.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(

	@field:SerializedName("boleh_komentar")
	val bolehKomentar: Int? = null,

	@field:SerializedName("id_kategori")
	val idKategori: Int? = null,

	@field:SerializedName("gambar1")
	val gambar1: String? = null,

	@field:SerializedName("tgl_upload")
	val tglUpload: String? = null,

	@field:SerializedName("gambar3")
	val gambar3: String? = null,

	@field:SerializedName("gambar2")
	val gambar2: String? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("enabled")
	val enabled: Int? = null,

	@field:SerializedName("dokumen")
	val dokumen: String? = null,

	@field:SerializedName("hit")
	val hit: Int? = null,

	@field:SerializedName("link_dokumen")
	val linkDokumen: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("headline")
	val headline: Int? = null,

	@field:SerializedName("isi")
	val isi: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
) : Parcelable