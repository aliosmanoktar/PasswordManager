package com.aliosman.passwordmanager

data class PasswordModel(val HesapAdi: String, val KullaniciAdi: String, val Sifre: String) {
    fun GetPasswordHide(): String {
        var a = ""
        for (i in 0..Sifre.length)
            a += "*"
        return a
    }
}