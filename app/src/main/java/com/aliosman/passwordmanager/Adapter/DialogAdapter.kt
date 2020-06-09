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
        text = dialogView.findViewById(R.id.dialog_text)
        progess = dialogView.findViewById(R.id.dialog_progress)
        background = dialogView.findViewById(R.id.dialog_img_background)
    }

    fun Show() {
        dialog.show()
    }

    fun setClick(click: IButtonClick): DialogAdapter {
        button.setOnClickListener {
            click.Click()
        }
        return this
    }

    fun loaing() {
        progess.visibility = View.VISIBLE
        img.visibility = View.GONE
        button.visibility = View.GONE
        background.background.setTint(ContextCompat.getColor(context, R.color.color_loading))
    }

    fun succes() {
        progess.visibility = View.GONE
        img.visibility = View.VISIBLE
        button.visibility = View.VISIBLE
        img.setImageDrawable(context.resources.getDrawable(R.drawable.ic_success, null))
        background.background.setTint(ContextCompat.getColor(context, R.color.color_succes))
        button.background.setTint(ContextCompat.getColor(context, R.color.color_succes))
    }

    fun OnError() {
        progess.visibility = View.GONE
        img.visibility = View.VISIBLE
        button.visibility = View.VISIBLE
        img.setImageDrawable(context.resources.getDrawable(R.drawable.ic_dialog_error, null))
        background.background.setTint(ContextCompat.getColor(context, R.color.color_error))
        button.background.setTint(ContextCompat.getColor(context, R.color.color_error))
    }

    fun setTitle(title: String) {
        text.text = title
    }

    fun Hide() {
        dialog.dismiss()
    }
    /*override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.layout_dialog,container,false)
        img=v.findViewById(R.id.dialog_image)
        button=v.findViewById(R.id.dialog_btn)
        text=v.findViewById(R.id.dialog_text)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(false)
        setClick()
        return v
    }

    fun settitle(title: String){
        text.text=title
    }

    fun loaing(){
        img.visibility=View.GONE
        button.visibility=View.GONE
        background.background.setTint(ContextCompat.getColor(context!!,R.color.color_loading))
    }

    fun succes(){
        progess.visibility=View.GONE
        img.visibility=View.VISIBLE
        button.visibility=View.VISIBLE
        img.setImageDrawable(resources.getDrawable(R.drawable.ic_success,null))
        background.background.setTint(ContextCompat.getColor(context!!,R.color.color_succes))
        button.background.setTint(ContextCompat.getColor(context!!,R.color.color_succes))
    }

    fun OnError(){
        progess.visibility=View.GONE
        img.visibility=View.VISIBLE
        button.visibility=View.VISIBLE
        img.setImageDrawable(resources.getDrawable(R.drawable.ic_dialog_error,null))
        background.background.setTint(ContextCompat.getColor(context!!,R.color.color_error))
        button.background.setTint(ContextCompat.getColor(context!!,R.color.color_error))
    }*/
}