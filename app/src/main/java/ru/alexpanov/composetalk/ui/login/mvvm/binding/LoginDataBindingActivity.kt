package ru.alexpanov.composetalk.ui.login.mvvm.binding

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import ru.alexpanov.composetalk.R
import ru.alexpanov.composetalk.databinding.ActivityBindingLoginBinding

class LoginDataBindingActivity : FragmentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityBindingLoginBinding>(
            this,
            R.layout.activity_binding_login
        )
        binding.vm = viewModel

        initObservers()
    }

    private fun initObservers() {
        viewModel.message.observe(this, { value ->
            val container: View = findViewById(android.R.id.content)
            Snackbar.make(container, value, Snackbar.LENGTH_LONG).show()
        })
    }
}