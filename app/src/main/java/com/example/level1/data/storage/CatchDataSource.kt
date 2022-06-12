package com.example.level1.data.storage

import android.content.Context

class CatchDataSource(context: Context) : DataSource {

    private val sharedPreferences =
        context.getSharedPreferences("accountDetails", Context.MODE_PRIVATE)

    override fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }
}