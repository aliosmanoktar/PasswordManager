/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Adapter

open interface IRecylerItems<T> {
    fun Select(item: T)
    fun LongClick(item: T)
}