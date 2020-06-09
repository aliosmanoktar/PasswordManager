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
import com.aliosman.passwordmanager.Adapter.DialogAdapter
import com.aliosman.passwordmanager.Background.Backgrounds
import com.aliosman.passwordmanager.Background.IAddAndUpdate
import com.aliosman.passwordmanager.Models.IButtonClick
import com.aliosman.passwordmanager.Models.PasswordModel
import com.aliosman.passwordmanager.Models.key_item
import com.aliosman.passwordmanager.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddedPasswordActivity : AppCompatActivity() {
    var PasswordItem: PasswordModel? = null
    lateinit var HesapAdi: TextInputEditText
    lateinit var KullaniciAdi: TextInputEditText
    lateinit var Sifre: TextInputEditText
    lateinit var HesapAdiLayout: TextInputLayout
    lateinit var KullaniciAdiLayout: TextInputLayout
    lateinit var SifreLayout: TextInputLayout
    lateinit var kaydet: Button
    private val TAG = javaClass.name
    lateinit var dialog: DialogAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_added_password)
        PasswordItem = intent.extras?.getSerializable(key_item) as? PasswordModel
        HesapAdi = findViewById(R.id.AddedActivity_hesap_adi)
        KullaniciAdi = findViewById(R.id.AddedActivity_kullanici_adi)
        Sifre = findViewById(R.id.AddedActivity_sifre)
        kaydet = findViewById(R.id.AddedActivity_kaydet)
        HesapAdiLayout = findViewById(R.id.AddedActivity_hesap_adi_layout)
        KullaniciAdiLayout = findViewById(R.id.AddedActivity_kullanici_adi_layout)
        SifreLayout = findViewById(R.id.AddedActivity_sifre_layout)
        if (PasswordItem != null) {
            HesapAdi.setText(PasswordItem!!.HesapAdi)
            KullaniciAdi.setText(PasswordItem!!.KullaniciAdi)
            Sifre.setText(PasswordItem!!.Sifre)
        }
        kaydet.setOnClickListener(kaydetClick)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        dialog = DialogAdapter(this)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.password_added_menu, menu)
        return PasswordItem != null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_password_menu) {
            dialog.Question()
            dialog.setClick1(object : IButtonClick {
                override fun Click() {
                    Remove()
                }
            })
            dialog.setClick(object : IButtonClick {
                override fun Click() {
                    dialog.Hide()
                }
            })
            dialog.Show()
        } else if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    private fun Remove() {
        Backgrounds().RemovePassword(PasswordItem!!, object : IAddAndUpdate {
            override fun OnSucces() {
                Log.e(TAG, "Remove OnSucces")
                dialog.setTitle("Silme Başarılı")
                dialog.succes()
                dialog.Show()
                dialog.setClick(object : IButtonClick {
                    override fun Click() {
                        dialog.Hide()
                        finish()
                    }

                })
            }

            override fun OnFailure(message: String?) {
                Log.e(TAG, "Remove OnFailure")
            }

        })
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
                dialog.setTitle("Şifre Başarılı Bir Şekilde Kaydedildi")
                dialog.succes()
            }

            override fun OnFailure(message: String?) {
                dialog.setTitle("Şifre Kaydedilmesi sırasında hata oluştu!!")
                dialog.OnError()
                Log.e(TAG, "Update OnFailure: $message")
            }
        })
        dialog.setClick(object : IButtonClick {
            override fun Click() {
                dialog.Hide()
                finish()
            }
        })
    }

    fun Kaydet() {
        Backgrounds().AddPassword(GetData(), object : IAddAndUpdate {
            override fun OnSucces() {
                dialog.setTitle("Şifre Başarılı Bir Şekilde Kaydedildi")
                dialog.succes()
            }

            override fun OnFailure(message: String?) {
                Log.e(TAG, "OnFailure: $message")
                dialog.setTitle("Şifre Kaydedilmesi sırasında hata oluştu!!")
                dialog.OnError()
            }
        })
        dialog.setClick(object : IButtonClick {
            override fun Click() {
                dialog.Hide()
                finish()
            }
        })
    }

    val kaydetClick = View.OnClickListener {
        var error: Boolean = false
        if (HesapAdi.text.isNullOrEmpty()) {
            error = true
            HesapAdiLayout.error = "Hesap Adi Boş Olamaz"
        }
        if (KullaniciAdi.text.isNullOrEmpty()) {
            error = true
            KullaniciAdiLayout.error = "Kullanıcı Adı Boş Olamaz"
        }
        if (Sifre.text.isNullOrEmpty()) {
            error = true
            SifreLayout.error = "Şifre Boş Olamaz"
        }
        if (error)
            return@OnClickListener
        if (PasswordItem == null) {
            Kaydet()
            dialog.setTitle("Şifre Kaydediliyor lütfen bekleyiniz!!")
        } else {
            Update()
            dialog.setTitle("Şifre güncelleniyor lütfen bekleyiniz!!")
        }
        dialog.loaing()
        dialog.Show()
    }
}