/*
 * Ali Osman OKTAR
 * Aliosmanoktar@gmail.com
 * Copyright (c) 2020.
 */

package com.aliosman.passwordmanager.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.aliosman.passwordmanager.Adapter.DialogAdapter
import com.aliosman.passwordmanager.Adapter.EmptyAdapter
import com.aliosman.passwordmanager.Adapter.IRecylerItems
import com.aliosman.passwordmanager.Adapter.PasswordAdapter
import com.aliosman.passwordmanager.Background.Backgrounds
import com.aliosman.passwordmanager.Background.IGetPasswords
import com.aliosman.passwordmanager.Models.PasswordModel
import com.aliosman.passwordmanager.Models.key_item
import com.aliosman.passwordmanager.R
import kotlinx.android.synthetic.main.activity_password_list.*

class PasswordListActivity : AppCompatActivity() {

    lateinit var recylerview: RecyclerView
    lateinit var dialogAdapter: DialogAdapter
    private val TAG = javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_list)
        recylerview = findViewById(R.id.password_list_activity_recylerview)
        password_list_activity_fab.setOnClickListener {
            startActivity(
                Intent(
                    baseContext,
                    AddedPasswordActivity::class.java
                )
            )
        }
        dialogAdapter = DialogAdapter(this)
        getItems()

    }

    private fun getItems() {
        dialogAdapter.loaing()
        dialogAdapter.Show()
        dialogAdapter.setTitle("Şifreler Yükleniyor Lütfen Bekleyiniz")
        Backgrounds().listenPassword(object : IGetPasswords {
            override fun OnSucces(mutableList: MutableList<PasswordModel>) {
                Log.e(TAG, "OnSucces: ${mutableList.size}")
                dialogAdapter.Hide()
                recylerview.adapter =
                    if (mutableList.size != 0)
                        PasswordAdapter(mutableList, itemClick = Click)
                    else EmptyAdapter()
            }

            override fun OnFailure(message: String?) {
                Log.e(TAG, "OnFailure: $message")
            }

        })
    }

    val Click: IRecylerItems<PasswordModel> = object : IRecylerItems<PasswordModel> {
        override fun Select(item: PasswordModel) {
            val i = Intent(baseContext, AddedPasswordActivity::class.java)
            val b = Bundle()
            b.putSerializable(key_item, item)
            i.putExtras(b)
            startActivity(i)
        }

        override fun LongClick(item: PasswordModel) {

        }

    }
}