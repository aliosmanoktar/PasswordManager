/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.aliosman.passwordmanager.Background.Backgrounds
import com.aliosman.passwordmanager.Background.IAddAndUpdate
import com.aliosman.passwordmanager.Models.PasswordModel
import com.aliosman.passwordmanager.Models.key_item
import com.aliosman.passwordmanager.R
import com.google.android.material.textfield.TextInputEditText

class AddedPasswordActivity : AppCompatActivity() {
    var PasswordItem: PasswordModel? = null
    lateinit var HesapAdi: TextInputEditText
    lateinit var KullaniciAdi: TextInputEditText
    lateinit var Sifre: TextInputEditText
    lateinit var kaydet: Button
    private val TAG = javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_added_password)
        PasswordItem = intent.extras?.getSerializable(key_item) as? PasswordModel
        HesapAdi = findViewById(R.id.AddedActivity_hesap_adi)
        KullaniciAdi = findViewById(R.id.AddedActivity_kullanici_adi)
        Sifre = findViewById(R.id.AddedActivity_sifre)
        kaydet = findViewById(R.id.AddedActivity_kaydet)
        if (PasswordItem != null) {
            HesapAdi.setText(PasswordItem!!.HesapAdi)
            KullaniciAdi.setText(PasswordItem!!.KullaniciAdi)
            Sifre.setText(PasswordItem!!.Sifre)
        }
        kaydet.setOnClickListener(kaydetClick)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.password_added_menu, menu)
        return PasswordItem != null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_password_menu)
            Backgrounds().RemovePassword(PasswordItem!!, object : IAddAndUpdate {
                override fun OnSucces() {
                    Log.e(TAG, "Remove OnSucces")
                }

                override fun OnFailure(message: String?) {
                    Log.e(TAG, "Remove OnFailure")
                }

            })
        else if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    private fun GetData(): PasswordModel =
        PasswordModel(
            HesapAdi = HesapAdi.text.toString(),
            KullaniciAdi = KullaniciAdi.text.toString(),
            Sifre = Sifre.text.toString(),
            ID = PasswordItem?.ID
        )

    fun Update() {
        Backgrounds().UploadPassword(GetData(), object : IAddAndUpdate {
            override fun OnSucces() {
                Log.e(TAG, "Update OnSucces: ")
            }

            override fun OnFailure(message: String?) {
                Log.e(TAG, "Update OnFailure: $message")
            }
        })
    }

    fun Kaydet() {
        Backgrounds().AddPassword(GetData(), object : IAddAndUpdate {
            override fun OnSucces() {
                Log.e(TAG, "OnSucces: ")
            }

            override fun OnFailure(message: String?) {
                Log.e(TAG, "OnFailure: $message")
            }

        })
    }

    val kaydetClick = View.OnClickListener {
        if (PasswordItem == null)
            Kaydet()
        else
            Update()
    }
}