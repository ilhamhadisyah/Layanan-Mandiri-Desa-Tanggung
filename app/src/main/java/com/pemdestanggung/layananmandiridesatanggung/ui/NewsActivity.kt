package com.pemdestanggung.layananmandiridesatanggung.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pemdestanggung.layananmandiridesatanggung.data.datasource.network.ApiClient
import com.pemdestanggung.layananmandiridesatanggung.data.datasource.network.ApiHelper
import com.pemdestanggung.layananmandiridesatanggung.databinding.ActivityNewsBinding
import com.pemdestanggung.layananmandiridesatanggung.ui.home.ArticleAdapter
import com.pemdestanggung.layananmandiridesatanggung.ui.home.ArticleAdapterExtended
import com.pemdestanggung.layananmandiridesatanggung.ui.home.HomeViewModel
import com.pemdestanggung.layananmandiridesatanggung.utils.Status
import com.pemdestanggung.layananmandiridesatanggung.utils.ViewModelFactory

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: ArticleAdapterExtended

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Berita Desa"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setupViewModel()
        setUpAdapter()
        observeArticles()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiClient.apiService))
        )[HomeViewModel::class.java]
    }

    private fun observeArticles() {
        viewModel.getArticles().observe(this) {
            it?.let { resources ->
                when (resources.status) {
                    Status.ERROR -> {}
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        resources.data.let { articleData ->
                            adapter.apply {
                                addUsers(articleData?.data!!)
                                notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setUpAdapter() {
        binding.apply {
            rvArtikel.layoutManager = LinearLayoutManager(
                this@NewsActivity
            )
            adapter = ArticleAdapterExtended(arrayListOf())
            rvArtikel.adapter = adapter
        }
    }
}