/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Models

import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


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
        "Sifre" to Blob.fromBytes(encrypt(clear = Sifre))
    )
}

private val TAG = "GETDATA"
fun getData(item: QueryDocumentSnapshot): PasswordModel {
    val data = item.data
    return PasswordModel(
        HesapAdi = data["HesapAdi"] as String,
        KullaniciAdi = data["KullaniciAdi"] as String,
        Sifre = decrypt((data["Sifre"] as Blob).toBytes()),
        ID = item.id
    )
}

@Throws(Exception::class)
fun encrypt(clear: String): ByteArray {
    return getChipper(Cipher.ENCRYPT_MODE).doFinal(clear.toByteArray(Charset.defaultCharset()))
}

@Throws(java.lang.Exception::class)
fun decrypt(encrypted: ByteArray): String {
    return String(getChipper(Cipher.DECRYPT_MODE).doFinal(encrypted))
}

fun getChipper(opMode: Int): Cipher {
    val skeySpec = SecretKeySpec(
        "2DD24A17FC9D94FC213D5085E0EEB16E".toByteArray(Charset.defaultCharset()),
        "AES"
    )
    val cipher = Cipher.getInstance("AES")
    cipher.init(opMode, skeySpec)
    return cipher
}
