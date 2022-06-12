package com.example.level1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.level1.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myProfileName.text = intent.getStringExtra(AuthActivity.NAME_KEY)
    }
}