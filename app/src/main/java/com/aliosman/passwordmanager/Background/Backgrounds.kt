/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Background

import android.util.Log
import com.aliosman.passwordmanager.Models.PasswordModel
import com.aliosman.passwordmanager.Models.getData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Backgrounds {
    private val db = Firebase.firestore
    private val collections = db.collection("Passwords")
    fun AddPassword(item: PasswordModel, listener: IAddAndUpdate) {
        collections.add(item.getPutData())
            .addOnSuccessListener { listener.OnSucces() }
            .addOnFailureListener { e -> listener.OnFailure(e.message) }
    }

    fun getPasswords(listener: IGetPasswords) {
        collections.get().addOnSuccessListener { result ->
            val items = mutableListOf<PasswordModel>()
            for (document in result)
                items.add(getData(document))
            listener.OnSucces(items)
        }.addOnFailureListener { e ->
            listener.OnFailure(e.message)
        }
    }

    private val TAG = javaClass.name
    fun listenPassword(listener: IGetPasswords) {
        collections.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                return@addSnapshotListener
            }
            if (querySnapshot != null) {
                Log.e(TAG, "listenPassword: ")
                getPasswords(listener)
            }
        }
    }
}