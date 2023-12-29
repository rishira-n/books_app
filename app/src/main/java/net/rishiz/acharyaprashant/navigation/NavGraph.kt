package net.rishiz.acharyaprashant.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.rishiz.acharyaprashant.NetworkConnectivityObserver
import net.rishiz.acharyaprashant.utils.ConnectivityObserver
import net.rishiz.acharyaprashant.R
import net.rishiz.acharyaprashant.view.BookScreen
import net.rishiz.acharyaprashant.view.MainScreen
import net.rishiz.acharyaprashant.view.SplashScreen
import net.rishiz.acharyaprashant.viewmodel.MainViewModel

/**
 * NavGraph controls the all screen navigation
 */
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun NavGraph(connectivityObserver: NetworkConnectivityObserver) {

    val navController = rememberNavController()
    val context = LocalContext.current

    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )


    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {

        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }

        composable(Screen.MainScreen.route) {

            //Observing availability or unavailability of internet connection
            if (status == ConnectivityObserver.Status.Available) {

                val viewModel: MainViewModel = viewModel(
                    factory = HiltViewModelFactory(context, it)
                )

                viewModel.getBookData()
                MainScreen(navController, viewModel)

            } else {

                //Show the connection error status
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.no_internet),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                        )
                        Text(text = "${stringResource(id = R.string.network_status)}: $status")
                    }
                }
            }
        }

        //Launching Book screen with book id argument
        composable("${Screen.BookScreen.route}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {

            val viewModel = hiltViewModel<MainViewModel>(it)

            val id = it.arguments?.getString("id")
                ?: throw IllegalStateException("'Book ISBN No' shouldn't be null")
            viewModel.getBookByID(id = id)

            BookScreen(viewModel = viewModel, navController = navController)

        }
    }
}





