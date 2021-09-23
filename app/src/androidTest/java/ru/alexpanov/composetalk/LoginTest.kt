package ru.alexpanov.composetalk

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import ru.alexpanov.composetalk.ui.login.compose.LoginComposeActivity

class LoginTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<LoginComposeActivity>()

    @Test
    fun snackbarDisplayedTest() {
        composeTestRule.setContent {
            composeTestRule.activity.LoginScreen()
        }

        composeTestRule.onNodeWithText("Email").performTextInput("a@b.ru")
        composeTestRule.onNodeWithText("Password").performTextInput("123")
        composeTestRule.onNodeWithText("Sign In").performClick()
        composeTestRule.onNodeWithText("Success").assertIsDisplayed()
    }

    @Test
    fun signInButtonDisabledTest() {
        composeTestRule.setContent {
            composeTestRule.activity.LoginScreen()
        }

        composeTestRule.onNodeWithText("Email").performTextInput("a@b.ru")
        composeTestRule.onNodeWithText("Sign In").assertIsNotEnabled()

        composeTestRule.onNodeWithText("Email").performTextClearance()

        composeTestRule.onNodeWithText("Password").performTextInput("123")
        composeTestRule.onNodeWithText("Sign In").assertIsNotEnabled()
    }
}