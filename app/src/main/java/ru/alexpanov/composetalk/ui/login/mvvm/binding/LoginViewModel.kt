package ru.alexpanov.composetalk.ui.login.mvvm.binding

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import ru.alexpanov.composetalk.ui.login.mvvm.SingleLiveData

class LoginViewModel : ViewModel() {
    val login = ObservableField<String>()

    val password = ObservableField<String>()

    val message = SingleLiveData<String>()

    val isAuthEnabled = ObservableField<Boolean>(login, password).apply {
        this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                checkAuthEnabled()
            }
        })
    }

    fun authorize() {
        if (validateFields().not()) return

        message.value = "Success"
    }

    private fun checkAuthEnabled() {
        isAuthEnabled.set(validateFields())
    }

    private fun validateFields(): Boolean {
        return (login.get().isNullOrBlank() || password.get().isNullOrBlank()).not()
    }
}