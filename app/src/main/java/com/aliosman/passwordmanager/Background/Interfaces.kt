/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Background

import com.aliosman.passwordmanager.Models.PasswordModel

open interface IAddAndUpdate {
    open fun OnSucces()
    open fun OnFailure(message: String?)
}

interface IGetPasswords {
    fun OnSucces(mutableList: MutableList<PasswordModel>)
    fun OnFailure(message: String?)
}