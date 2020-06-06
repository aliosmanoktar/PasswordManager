/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aliosman.passwordmanager.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(baseContext, PasswordListActivity::class.java))
    }
}