package ru.alexpanov.composetalk.ui.login.mvp

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface LoginView : MvpView {
    fun setAuthEnabled(value: Boolean)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(value: String)
}

