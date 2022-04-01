package com.pemdestanggung.layananmandiridesatanggung.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

object NetworkHelper {

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null){
            when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->{
                    Log.d("Internet","NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->{
                    Log.d("Internet","NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->{
                    Log.d("Internet","NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
            }
        }
        return false

    }
}