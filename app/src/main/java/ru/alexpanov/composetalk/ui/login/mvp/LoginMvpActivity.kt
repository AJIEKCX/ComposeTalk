package ru.alexpanov.composetalk.ui.login.mvp

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.alexpanov.composetalk.databinding.ActivityLoginBinding

class LoginMvpActivity : MvpAppCompatActivity(), LoginView {
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    lateinit var binding: ActivityLoginBinding

    @ProvidePresenter
    fun providePresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater).apply {
            btnSignIn.setOnClickListener {
                presenter.authorize()
            }
            etEmail.addTextChangedListener {
                presenter.setLogin(it?.toString().orEmpty())
            }
            etPassword.addTextChangedListener {
                presenter.setPassword(it?.toString().orEmpty())
            }
        }
        setContentView(binding.root)
    }

    override fun setAuthEnabled(value: Boolean) {
        binding.btnSignIn.isEnabled = value
    }

    override fun showMessage(value: String) {
        val container: View = findViewById(android.R.id.content)
        Snackbar.make(container, value, Snackbar.LENGTH_LONG).show()
    }
}