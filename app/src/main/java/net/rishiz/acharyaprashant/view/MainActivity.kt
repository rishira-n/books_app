package net.rishiz.acharyaprashant.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import net.rishiz.acharyaprashant.NetworkConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint
import net.rishiz.acharyaprashant.navigation.NavGraph
import net.rishiz.acharyaprashant.ui.theme.AcharyaPrashantTheme
import net.rishiz.acharyaprashant.utils.ConnectivityObserver

/**
 * This the only activity of the app
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            AcharyaPrashantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Calling navgraph with connectivityObserver as argument that will observe paragraph
                    NavGraph(connectivityObserver as NetworkConnectivityObserver)
                }
            }
        }
    }
}
