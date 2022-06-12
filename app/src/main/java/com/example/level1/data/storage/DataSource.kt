package com.example.level1.data.storage

interface DataSource {

    fun saveString(key: String, value: String)

    fun getString(key: String): String
}