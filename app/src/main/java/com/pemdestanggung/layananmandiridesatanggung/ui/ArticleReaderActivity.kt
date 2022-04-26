package com.pemdestanggung.layananmandiridesatanggung.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import androidx.core.app.NavUtils
import com.pemdestanggung.layananmandiridesatanggung.R
import com.pemdestanggung.layananmandiridesatanggung.data.model.DataItem
import com.pemdestanggung.layananmandiridesatanggung.databinding.ActivityArticleReaderBinding
import com.pemdestanggung.layananmandiridesatanggung.utils.BaseImageUrl.BASE_IMAGE_URL
import com.pemdestanggung.layananmandiridesatanggung.utils.loadUrl

class ArticleReaderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleReaderBinding
    private lateinit var data: DataItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Berita Desa"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        data = intent.getParcelableExtra("ARTICLE_DATA")!!

        binding.apply {
            judulBerita.text = data.judul
            tanggalTerbit.text = data.tglUpload
            val articleContent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(data.isi, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(data.isi)
            }
            content.text = articleContent
            gambar.loadUrl(BASE_IMAGE_URL, data.gambar!!)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}