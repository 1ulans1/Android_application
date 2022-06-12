package com.example.level1

import android.text.Editable
import android.text.TextWatcher

abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}