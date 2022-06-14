package com.example.level1

import android.util.Patterns
import java.util.regex.Pattern

object Validator {

    fun regexValidation(text: String, regex: String): Boolean {
        val pattern: Pattern = Pattern.compile(regex)
        return pattern.matcher(text).matches()
    }

    fun parsingEmailToLogin(email: String): String {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            val emailWithout = email.substring(0, email.indexOf("@"))
            val array: List<String> = emailWithout.split(".")
            return if (array.size == 2) {
                "${array[0]} ${array[1]}"
            } else {
                emailWithout
            }
        }
        return ""
    }
}