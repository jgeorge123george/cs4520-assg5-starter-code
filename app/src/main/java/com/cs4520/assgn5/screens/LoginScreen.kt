package com.cs4520.assgn5.screens


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cs4520.assgn5.model.LoginViewModel
import com.cs4520.assgn5.screens.ShowToast as ShowToast1


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel(),
    navController: NavController
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginSuccessful = remember { mutableStateOf(false) }
    var loginFailureMessage by remember { mutableStateOf<String?>(null) } // Store login failure message

    if (loginSuccessful.value) {
        ShowToast1("Logged In")
        navController.navigate("HelloWorldScreen")
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )


            Button(
                onClick = {
                    keyboardController?.hide()

                    if (username == "admin" && password == "admin") {
                        loginSuccessful.value = true
                    } else {
                        loginFailureMessage = "Login Failure" // Store login failure message
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
            loginFailureMessage?.let {
                ShowToast1(it)
                loginFailureMessage = null
            }
        }
    }
}

@Composable
fun ShowToast(message: String) {
    val context = androidx.compose.ui.platform.LocalContext.current
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
