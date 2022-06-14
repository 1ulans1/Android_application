package com.example.level1

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.level1.data.storage.CacheDataSource
import com.example.level1.data.storage.DataSource
import com.example.level1.databinding.ActivityAuthBinding
import com.example.level1.utils.Constants
import com.example.level1.utils.capitalizeWords

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSource = CacheDataSource(this)

        checkAuthorization(dataSource)

        setListener(dataSource)
    }

    private fun checkAuthorization(dataSource: DataSource) {
        val email = dataSource.getString(Constants.LOGIN_KEY)
        val password = dataSource.getString(Constants.PASSWORD_KEY)
        if (email != "" && password != "") {
            goOnProfile(email)
        }
    }

    private fun setListener(dataSource: DataSource) {

        with(binding) {

            binding.buttonRegister.setOnClickListener {

                val email = emailInputEditLayout.text.toString()
                val password = passwordInputEditLayout.text.toString()

                val emailCorrect = Patterns.EMAIL_ADDRESS.matcher(email).matches()
                val passwordCorrect = Validator.regexValidation(password, Constants.REGEX_PASSWORD)

                emailInputLayout.error = if (!emailCorrect) getString(R.string.incorrect_input) else ""

                passwordInputLayout.error = if (!passwordCorrect) getString(R.string.incorrect_input) else ""

                if (emailCorrect && passwordCorrect) {
                    if (binding.checkboxRememberMe.isChecked) {
                        dataSource.saveString(Constants.LOGIN_KEY, email)
                        dataSource.saveString(Constants.PASSWORD_KEY, password)
                    }
                    goOnProfile(email)
                }
            }
        }

        binding.passwordInputEditLayout.doAfterTextChanged { binding.passwordInputLayout.error = "" }

        binding.emailInputEditLayout.doAfterTextChanged { binding.emailInputLayout.error = "" }
    }

    private fun goOnProfile(email: String) {
        val intent = Intent(this@AuthActivity, ProfileActivity::class.java)
        intent.putExtra(Constants.NAME_KEY, Validator.parsingEmailToLogin(email).capitalizeWords())
        startActivity(intent)
        finish()
    }
}

//open class A {
//    open fun foo() {
//        print("foo")
//    }
//}
//
//class B : A() {
//
//    override fun foo() {
//        super.foo()
//    }
//}
