/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.aliosman.passwordmanager.Background.Backgrounds
import com.aliosman.passwordmanager.Background.IAddAndUpdate
import com.aliosman.passwordmanager.Models.PasswordModel
import com.aliosman.passwordmanager.R
import com.google.android.material.textfield.TextInputEditText

class AddedPasswordActivity : AppCompatActivity() {
    lateinit var HesapAdi: TextInputEditText
    lateinit var KullaniciAdi: TextInputEditText
    lateinit var Sifre: TextInputEditText
    lateinit var kaydet: Button
    private val TAG = javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_added_password)
        HesapAdi = findViewById(R.id.AddedActivity_hesap_adi)
        KullaniciAdi = findViewById(R.id.AddedActivity_kullanici_adi)
        Sifre = findViewById(R.id.AddedActivity_sifre)
        kaydet = findViewById(R.id.AddedActivity_kaydet)
        kaydet.setOnClickListener(kaydetClick)
    }

    val kaydetClick = View.OnClickListener {
        var item = PasswordModel(
            HesapAdi = HesapAdi.text.toString(),
            KullaniciAdi = KullaniciAdi.text.toString(),
            Sifre = Sifre.text.toString()
        )
        Backgrounds().AddPassword(item, object : IAddAndUpdate {
            override fun OnSucces() {
                Log.e(TAG, "OnSucces: ")
            }

            override fun OnFailure(message: String?) {
                Log.e(TAG, "OnFailure: $message")
            }

        })
    }
}