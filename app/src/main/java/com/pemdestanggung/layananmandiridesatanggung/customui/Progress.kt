package com.pemdestanggung.layananmandiridesatanggung.customui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import com.pemdestanggung.layananmandiridesatanggung.R

class Progress constructor(
    context: Context?,
    @StringRes private val titleRes: Int,
    cancelable: Boolean = false
) {

    private var view: View? = null
    private var builder: AlertDialog.Builder
    private var dialog: Dialog

    init {
        view = LayoutInflater.from(context).inflate(R.layout.progress, null)
        builder = AlertDialog.Builder(context)
        builder.setView(view)
        dialog = builder.create()
        dialog.setCancelable(cancelable)


    }

    fun show() {
        dialog.show()
    }

    fun dismiss() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    fun setProgressDialogVisibility(isVisible: Boolean) {

    }
}