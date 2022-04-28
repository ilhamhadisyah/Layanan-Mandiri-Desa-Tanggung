package com.pemdestanggung.layananmandiridesatanggung.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pemdestanggung.layananmandiridesatanggung.R
import com.pemdestanggung.layananmandiridesatanggung.data.model.DataItem
import com.pemdestanggung.layananmandiridesatanggung.databinding.ArticleItemBinding
import com.pemdestanggung.layananmandiridesatanggung.databinding.ArticleItemFullBinding
import com.pemdestanggung.layananmandiridesatanggung.ui.ArticleReaderActivity
import com.pemdestanggung.layananmandiridesatanggung.utils.BaseImageUrl.BASE_IMAGE_URL
import com.pemdestanggung.layananmandiridesatanggung.utils.loadUrl

class ArticleAdapterExtended(private val articles: ArrayList<DataItem>) :
    RecyclerView.Adapter<ArticleAdapterExtended.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ArticleItemFullBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(itemData: DataItem) {
            binding.apply {
                var title = ""
                title = if (itemData.judul?.length!! > 50) {
                    "${itemData.judul.take(50)}..."
                } else {
                    itemData.judul
                }

                judulBerita.text = title
                tanggalTerbit.text = "Diterbitkan : ${itemData.tglUpload}"
                gambar.loadUrl(BASE_IMAGE_URL, itemData.gambar!!)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ArticleReaderActivity::class.java)
                intent.putExtra("ARTICLE_DATA", itemData)
                itemView.context.startActivity(intent)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.article_item_full, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = this.articles.size

    fun addUsers(users: ArrayList<DataItem>) {
        this.articles.apply {
            clear()
            addAll(users)
        }
    }
}