package ru.alexpanov.composetalk.ui.login.mvvm

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import ru.alexpanov.composetalk.R
import ru.alexpanov.composetalk.databinding.ActivityLoginBinding

class LoginMvvmActivity : FragmentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.btnSignIn.setOnClickListener {
            viewModel.authorize()
        }
        binding.etEmail.addTextChangedListener {
            viewModel.setLogin(it?.toString().orEmpty())
        }
        binding.etPassword.addTextChangedListener {
            viewModel.setPassword(it?.toString().orEmpty())
        }
    }

    private fun initObservers() {
        viewModel.isAuthEnabled.observe(this, {
            binding.btnSignIn.isEnabled = it
        })
        viewModel.message.observe(this, { value ->
            val container: View = findViewById(android.R.id.content)
            Snackbar.make(container, value, Snackbar.LENGTH_LONG).show()
        })
    }
}