package com.aliosman.passwordmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class PasswordListActivity : AppCompatActivity() {

    lateinit var recylerview: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_list)
        recylerview = findViewById(R.id.password_list_activity_recylerview)
        recylerview.adapter = PasswordAdapter(getItems())
    }

    private fun getItems(): MutableList<PasswordModel> {
        return mutableListOf(
            PasswordModel("Twitter", "Account", "Password"),
            PasswordModel("Google", "Account", "Password"),
            PasswordModel("Spotify", "Account", "Password")
        )
    }
}