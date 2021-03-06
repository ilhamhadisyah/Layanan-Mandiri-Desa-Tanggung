package com.pemdestanggung.layananmandiridesatanggung.ui.home

import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.shape.CornerFamily
import com.pemdestanggung.layananmandiridesatanggung.R
import com.pemdestanggung.layananmandiridesatanggung.customui.MaterialCardCustomCorner
import com.pemdestanggung.layananmandiridesatanggung.data.datasource.network.ApiClient
import com.pemdestanggung.layananmandiridesatanggung.data.datasource.network.ApiHelper
import com.pemdestanggung.layananmandiridesatanggung.databinding.ActivityHomeBinding
import com.pemdestanggung.layananmandiridesatanggung.ui.NewsActivity
import com.pemdestanggung.layananmandiridesatanggung.ui.WebViewActivity
import com.pemdestanggung.layananmandiridesatanggung.utils.MapController
import com.pemdestanggung.layananmandiridesatanggung.utils.Status
import com.pemdestanggung.layananmandiridesatanggung.utils.ViewModelFactory
import java.lang.NullPointerException


class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setUpAdapter()
        observeArticles()

        binding.imageContainer.shapeAppearanceModel =
            binding
                .imageContainer
                .shapeAppearanceModel
                .toBuilder()
                .setTopLeftCorner(MaterialCardCustomCorner())
                .setTopRightCorner(MaterialCardCustomCorner())
                .setBottomLeftCorner(CornerFamily.ROUNDED, 70f)
                .setBottomRightCorner(CornerFamily.ROUNDED, 70f)
                .build()

        binding.layananMandiriBtnGroup.apply {
            pesanBtn.setOnClickListener(this@HomeActivity)
            arsipBtn.setOnClickListener(this@HomeActivity)
            bantuanBtn.setOnClickListener(this@HomeActivity)
            permohonanBtn.setOnClickListener(this@HomeActivity)
        }

        binding.sinergiButtonGroup.apply {
            bpjsBtn.setOnClickListener(this@HomeActivity)
            kemensosBtn.setOnClickListener(this@HomeActivity)
            peduliLindungiBtn.setOnClickListener(this@HomeActivity)
            posyanduBtn.setOnClickListener(this@HomeActivity)
            simPbbBtn.setOnClickListener(this@HomeActivity)
        }
        binding.apply {
            loginAdmin.setOnClickListener(this@HomeActivity)
            loginLayananMandiri.setOnClickListener(this@HomeActivity)
        }
        binding.apply {
            lapakDesaBtn.setOnClickListener(this@HomeActivity)
            beritaDesa.setOnClickListener(this@HomeActivity)
            expandArticles.setOnClickListener(this@HomeActivity)
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(MapController())
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.permohonan_btn -> {
                loadWebView("https://pemdestanggung.com/layanan-mandiri/surat/buat")
            }
            R.id.pesan_btn -> {
                loadWebView("https://pemdestanggung.com/layanan-mandiri/pesan-masuk")
            }
            R.id.arsip_btn -> {
                loadWebView("https://pemdestanggung.com/layanan-mandiri/arsip-surat")
            }
            R.id.bantuan_btn -> {
                loadWebView("https://pemdestanggung.com/layanan-mandiri/bantuan")
            }
            R.id.posyandu_btn -> {
                loadWebView("https://posyandu.pemdestanggung.com/login")
            }
            R.id.bpjs_btn -> {
                loadWebView("https://pcare.bpjs-kesehatan.go.id/vaksin/Login")
            }
            R.id.kemensos_btn -> {
                loadWebView("https://siks.kemensos.go.id/kemsos/login/login_form")
            }
            R.id.peduli_lindungi_btn -> {
                openApp("com.telkom.tracencare")
            }
            R.id.sim_pbb_btn -> {
                loadWebView("https://pipilpajak.online/login")
            }
            R.id.login_layanan_mandiri -> {
                loadWebView("https://pemdestanggung.com/layanan-mandiri")
            }
            R.id.login_admin -> {
                loadWebView("https://pemdestanggung.com/siteman")
            }
            R.id.lapak_desa_btn -> {
                loadWebView("https://pemdestanggung.com/lapak")
            }
            R.id.berita_desa -> {
                loadWebView("https://pemdestanggung.com/artikel/kategori/berita-desa")
            }
            R.id.expand_articles -> {
                val intent = Intent(this, NewsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun loadWebView(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("EXTRA_URL", url)
        startActivity(intent)
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
            rvArtikel.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = ArticleAdapter(arrayListOf())
            rvArtikel.adapter = adapter
        }
    }


    private fun openApp(packageName: String) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)

        try {
            intent?.action = ACTION_VIEW
            intent?.addFlags(FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: NullPointerException) {
            val playStoreIntent = Intent(ACTION_VIEW)
            playStoreIntent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            playStoreIntent.data = Uri.parse("market://details?id=$packageName")
            startActivity(playStoreIntent)
            Toast.makeText(this, "Unduh Aplikasi Terlebih dahulu", Toast.LENGTH_SHORT).show()
        }
    }
}