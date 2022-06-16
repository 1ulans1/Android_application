package com.example.level1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.level1.databinding.ActivityProfileBinding
import com.example.level1.utils.Constants

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.name.text = intent.getStringExtra(Constants.NAME_KEY)
    }
}