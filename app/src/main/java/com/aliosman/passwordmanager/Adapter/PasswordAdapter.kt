/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aliosman.passwordmanager.Models.PasswordModel
import com.aliosman.passwordmanager.R

class PasswordAdapter(val PasswordItems: MutableList<PasswordModel>) :
    RecyclerView.Adapter<PasswordAdapter.PasswordViewHolder>() {

    class PasswordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val hesapAdi: TextView = itemView.findViewById(R.id.password_item_hesapAdi)
        private val hesapKullaniciAdi: TextView =
            itemView.findViewById(R.id.password_item_kullaniciAdi)
        private val sifre: TextView = itemView.findViewById(R.id.password_item_hesapSifre)
        private val show: ImageView = itemView.findViewById(R.id.password_item_show)
        private var isShow = false
        fun bindItems(item: PasswordModel) {
            hesapAdi.text = item.HesapAdi
            hesapKullaniciAdi.text = item.Sifre
            show.setOnClickListener {
                if (!isShow) {
                    sifre.text = item.GetPasswordHide()
                    show.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_eye, null))
                    isShow = true
                } else {
                    sifre.text = item.Sifre
                    show.setImageDrawable(
                        itemView.resources.getDrawable(
                            R.drawable.ic_close_eye,
                            null
                        )
                    )
                    isShow = false
                }
            }
            show.callOnClick()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.password_item, parent, false)
        return PasswordViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return PasswordItems.size
    }

    override fun onBindViewHolder(holder: PasswordViewHolder, position: Int) {
        holder.bindItems(PasswordItems[position])
    }
}