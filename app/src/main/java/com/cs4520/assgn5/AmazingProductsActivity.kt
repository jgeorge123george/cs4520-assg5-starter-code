package com.cs4520.assgn5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.cs4520.assgn5.screens.ListItemScreen
import com.cs4520.assgn5.screens.LoginScreen
import com.cs4520.assgn5.ui.theme.Assgn5Theme
import com.cs4520.assgn5.workmanager.ApiWorker
import java.util.concurrent.TimeUnit

class AmazingProductsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //Schedule with WorkManger
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val apiWorker = PeriodicWorkRequest.Builder(
            ApiWorker::class.java,
            1, //WorkManager to fetch data every hour
            TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .setInitialDelay(1,TimeUnit.HOURS) //worker will be after 1 hour of being scheduled
            .build()
        //Schedule work
        WorkManager.getInstance(this).enqueue(apiWorker)

        setContent {
            Assgn5Theme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "loginScreen") {
                        composable("loginScreen") { LoginScreen(navController = navController) }
                        composable("helloWorldScreen") { ListItemScreen(navController = navController) }
                    }
                }
            }
        }
    }
}
