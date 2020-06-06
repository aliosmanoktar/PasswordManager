/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Models

import com.google.firebase.firestore.QueryDocumentSnapshot

data class PasswordModel(
    val HesapAdi: String,
    val KullaniciAdi: String,
    val Sifre: String,
    val ID: String? = null
) {
    fun GetPasswordHide(): String {
        var a = ""
        for (i in 0..Sifre.length)
            a += "*"
        return a
    }

    fun getPutData() = hashMapOf(
        "HesapAdi" to HesapAdi,
        "KullaniciAdi" to KullaniciAdi,
        "Sifre" to Sifre
    )
}

fun getData(item: QueryDocumentSnapshot): PasswordModel {
    val data = item.data
    return PasswordModel(
        HesapAdi = data["HesapAdi"] as String,
        KullaniciAdi = data["KullaniciAdi"] as String,
        Sifre = data["Sifre"] as String,
        ID = item.id
    )
}