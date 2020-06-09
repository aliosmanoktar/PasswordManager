/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.*
import androidx.core.content.ContextCompat
import com.aliosman.passwordmanager.Models.IButtonClick
import com.aliosman.passwordmanager.R

class DialogAdapter(val context: Context) {
    private lateinit var dialog: Dialog
    private lateinit var img: ImageView
    private lateinit var text: TextView
    private lateinit var button: Button
    private lateinit var progess: ProgressBar
    private lateinit var background: RelativeLayout
    private lateinit var button1: Button

    init {
        createDialog(AlertDialog.Builder(context))
    }

    private fun createDialog(dialogBuilder: AlertDialog.Builder) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null)
        dialog = dialogBuilder.setView(dialogView).create()
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        dialog.setCanceledOnTouchOutside(false)
        img = dialogView.findViewById(R.id.dialog_image)
        button = dialogView.findViewById(R.id.dialog_btn)
        button1 = dialogView.findViewById(R.id.dialog_btn_remove)
        text = dialogView.findViewById(R.id.dialog_text)
        progess = dialogView.findViewById(R.id.dialog_progress)
        background = dialogView.findViewById(R.id.dialog_img_background)
    }

    fun Show() {
        if (!dialog.isShowing)
            dialog.show()
    }

    fun setClick(click: IButtonClick): DialogAdapter {
        button.setOnClickListener {
            click.Click()
        }
        return this
    }

    fun setClick1(click: IButtonClick) {
        button1.setOnClickListener {
            click.Click()
        }
    }

    fun loaing() {
        button1.visibility = View.GONE
        progess.visibility = View.VISIBLE
        img.visibility = View.GONE
        button.visibility = View.GONE
        background.background.setTint(ContextCompat.getColor(context, R.color.color_loading))
    }

    fun succes() {
        button1.visibility = View.GONE
        progess.visibility = View.GONE
        img.visibility = View.VISIBLE
        button.visibility = View.VISIBLE
        img.setImageDrawable(context.resources.getDrawable(R.drawable.ic_success, null))
        background.background.setTint(ContextCompat.getColor(context, R.color.color_succes))
        button.background.setTint(ContextCompat.getColor(context, R.color.color_succes))
    }

    fun OnError() {
        button1.visibility = View.GONE
        progess.visibility = View.GONE
        img.visibility = View.VISIBLE
        button.visibility = View.VISIBLE
        img.setImageDrawable(context.resources.getDrawable(R.drawable.ic_dialog_error, null))
        background.background.setTint(ContextCompat.getColor(context, R.color.color_error))
        button.background.setTint(ContextCompat.getColor(context, R.color.color_error))
    }

    fun Question() {
        progess.visibility = View.GONE
        img.visibility = View.VISIBLE
        button.visibility = View.VISIBLE
        button1.visibility = View.VISIBLE
        img.setImageDrawable(context.resources.getDrawable(R.drawable.ic_question, null))
        background.background.setTint(ContextCompat.getColor(context, R.color.color_question))
        button.background.setTint(ContextCompat.getColor(context, R.color.color_question))
    }

    fun setTitle(title: String) {
        text.text = title
    }

    fun Hide() {
        dialog.hide()
    }
}