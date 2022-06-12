package com.example.level1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.level1.data.storage.CatchDataSource
import com.example.level1.data.storage.DataSource
import com.example.level1.databinding.ActivityAuthBinding
import java.util.regex.Pattern

class AuthActivity : AppCompatActivity() {

    private lateinit var dataSource: DataSource
    private lateinit var textWatcherPassword: SimpleTextWatcher
    private lateinit var textWatcherMail: SimpleTextWatcher
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataSource = CatchDataSource(this)

        checkAuthorization()
        setListener()
    }

    private fun checkAuthorization() {
        val email = dataSource.getString(LOGIN_KEY)
        val password = dataSource.getString(PASSWORD_KEY)
        if (email != "" && password != "") {
            goOnProfile(email)
        }
    }

    private fun setListener() {
        textWatcherPassword = object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                binding.emailInputLayout.error = ""
            }
        }

        textWatcherMail = object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                binding.emailInputLayout.error = ""
            }
        }

        binding.buttonRegister.setOnClickListener {
            with(binding) {
                val email = emailInputEditLayout.text.toString()
                val password = passwordInputEditInput.text.toString()

                val emailCorrect = Patterns.EMAIL_ADDRESS.matcher(email).matches()
                val passwordCorrect = regexValidation(
                    password,
                    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
                )

                emailInputLayout.error = if (!emailCorrect) {
                    getString(R.string.incorrect_input)
                } else ""

                passwordInputLayout.error = if (!passwordCorrect) {
                    getString(R.string.incorrect_input)
                } else ""

                if (emailCorrect && passwordCorrect) {
                    if (binding.checkboxRememberMe.isChecked) {
                        dataSource.saveString(LOGIN_KEY, email)
                        dataSource.saveString(PASSWORD_KEY, password)
                    }
                    goOnProfile(email)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.emailInputEditLayout.removeTextChangedListener(textWatcherMail)
        binding.passwordInputEditInput.removeTextChangedListener(textWatcherPassword)
    }

    override fun onResume() {
        super.onResume()
        binding.emailInputEditLayout.addTextChangedListener(textWatcherMail)
        binding.passwordInputEditInput.addTextChangedListener(textWatcherPassword)
    }

    private fun goOnProfile(email: String) {
        val intent = Intent(this@AuthActivity, ProfileActivity::class.java)
        intent.putExtra(NAME_KEY, parsingEmailToLogin(email).capitalizeWords())
        startActivity(intent)
        finish()
    }

    private fun parsingEmailToLogin(email: String): String {
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

    fun regexValidation(text: String, regex: String): Boolean {
        val pattern: Pattern = Pattern.compile(regex)
        return pattern.matcher(text).matches()
    }

    companion object {
        private const val LOGIN_KEY = "LOGIN_KEY"
        private const val PASSWORD_KEY = "PASSWORD_KEY"
        const val NAME_KEY = "PASSWORD_KEY"
    }
}

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it -> it.lowercase().replaceFirstChar { it.uppercase() } }
