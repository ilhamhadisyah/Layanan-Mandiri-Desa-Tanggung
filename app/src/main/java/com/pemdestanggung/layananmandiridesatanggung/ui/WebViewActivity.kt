package com.pemdestanggung.layananmandiridesatanggung.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.KeyEvent
import android.webkit.*
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.pemdestanggung.layananmandiridesatanggung.customui.Progress
import com.pemdestanggung.layananmandiridesatanggung.R
import com.pemdestanggung.layananmandiridesatanggung.databinding.ActivityWebViewBinding
import com.pemdestanggung.layananmandiridesatanggung.utils.NetworkHelper.isOnline

class WebViewActivity : AppCompatActivity() {

    private var progress : Progress? = null
    private var isLoaded : Boolean = false
    private var doubleClickToExitPressedOnce= false
    private var url :String? = "https://pemdestanggung.com/"

    private lateinit var binding : ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
            setContentView(binding.root)

        binding.webView.settings.apply {
            javaScriptEnabled = true
            allowFileAccess = true
            domStorageEnabled=true
            javaScriptCanOpenWindowsAutomatically=true
            supportMultipleWindows()
        }
        progress = Progress(this, R.string.zero_text,true)
        if (!isOnline(this)){
            showToast(getString(R.string.no_internet))
            showNoNetSnackBar()
            return
        }
    }

    override fun onResume() {
        if(isOnline(this) && !isLoaded) loadWebView()
        super.onResume()
    }

    private fun loadWebView(){
        binding.webView.loadUrl(url!!)
        binding.webView.webViewClient = object :WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()
                if (url.startsWith("tel:")||url.startsWith("whatsapp:")){
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                    binding.webView.goBack()
                    return true
                }else{
                    view?.loadUrl(url)
                }
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                showProgress(true)
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                isLoaded = true
                showProgress(false)
                super.onPageFinished(view, url)
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                isLoaded = false
                val errorMessage ="Error! $error"
                showToast(errorMessage)
                showProgress(false)
                super.onReceivedError(view, request, error)
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler!!.proceed()
                super.onReceivedSslError(view, handler, error)
            }
        }
    }

    private fun showProgress(visible: Boolean) {
        progress?.apply { if (visible) show() else dismiss() }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showNoNetSnackBar() {
        val snack =
            Snackbar.make(binding.rootView, getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE)
        snack.setAction(getString(R.string.setting)) {
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        }
        snack.show()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    showToastToExit()
                }
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun showToastToExit() {
        when {
            doubleClickToExitPressedOnce -> {
                onBackPressed()
            }
            else -> {
                doubleClickToExitPressedOnce = true
                showToast(getString(R.string.back_again_to_exit))
                Handler(Looper.myLooper()!!).postDelayed(
                    { doubleClickToExitPressedOnce = false },
                    2000)
            }
        }
    }
}