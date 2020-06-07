/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aliosman.passwordmanager.R

class EmptyAdapter : RecyclerView.Adapter<EmptyAdapter.EmptyViewHolder>() {
    class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {
        return EmptyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_empty_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: EmptyViewHolder, position: Int) {

    }
}