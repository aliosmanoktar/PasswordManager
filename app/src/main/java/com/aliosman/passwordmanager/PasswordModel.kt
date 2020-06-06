/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager

data class PasswordModel(val HesapAdi: String, val KullaniciAdi: String, val Sifre: String) {
    fun GetPasswordHide(): String {
        var a = ""
        for (i in 0..Sifre.length)
            a += "*"
        return a
    }
}