package ru.alexpanov.composetalk.ui.login.mvi

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.alexpanov.composetalk.R
import ru.alexpanov.composetalk.databinding.ActivityLoginBinding
import ru.alexpanov.composetalk.ui.login.mvi.LoginContract.SideEffect
import ru.alexpanov.composetalk.ui.login.mvi.LoginContract.Event

class LoginMviActivity : FragmentActivity() {
    private val store by viewModels<LoginStore>()

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
            store.setEvent(Event.SignInClicked)
        }
        binding.etEmail.addTextChangedListener {
            store.setEvent(Event.EmailChanged(it.toString()))
        }
        binding.etPassword.addTextChangedListener {
            store.setEvent(Event.PasswordChanged(it.toString()))
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            store.uiState.collect {
                binding.btnSignIn.isEnabled = it.isSignInEnabled
            }
        }

        lifecycleScope.launchWhenStarted {
            store.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is SideEffect.ShowMessage -> {
                        val container: View = findViewById(android.R.id.content)
                        Snackbar.make(container, sideEffect.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}