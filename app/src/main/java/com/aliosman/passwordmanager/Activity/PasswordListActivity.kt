/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.aliosman.passwordmanager.Adapter.PasswordAdapter
import com.aliosman.passwordmanager.Background.Backgrounds
import com.aliosman.passwordmanager.Background.IGetPasswords
import com.aliosman.passwordmanager.Models.PasswordModel
import com.aliosman.passwordmanager.R
import kotlinx.android.synthetic.main.activity_password_list.*

class PasswordListActivity : AppCompatActivity() {

    lateinit var recylerview: RecyclerView
    private val TAG = javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_list)
        recylerview = findViewById(R.id.password_list_activity_recylerview)
        password_list_activity_fab.setOnClickListener {
            startActivity(
                Intent(
                    baseContext,
                    AddedPasswordActivity::class.java
                )
            )
        }
        getItems()
    }

    private fun getItems() {
        Backgrounds().getPasswords(object : IGetPasswords {
            override fun OnSucces(mutableList: MutableList<PasswordModel>) {
                recylerview.adapter =
                    PasswordAdapter(mutableList)
            }

            override fun OnFailure(message: String?) {
                Log.e(TAG, "OnFailure: $message")
            }

        })
    }
}