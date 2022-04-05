package com.pemdestanggung.layananmandiridesatanggung.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context :Context) {
    companion object{
        private const val PREF_NAME = "LAYANAN MANDIRI"
        private const val IS_FIRST_TIME = "IS FIRST TIME"
    }

    private val sharedPreferences:SharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
    private val editor :SharedPreferences.Editor=sharedPreferences.edit()

    var isFirstTimeLaunched :Boolean
        get() = sharedPreferences.getBoolean(PREF_NAME,true)
        set(value) {
            editor.putBoolean(PREF_NAME,value).commit()
        }
}