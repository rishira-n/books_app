package net.rishiz.acharyaprashant.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.rishiz.acharyaprashant.view.MainScreen
import net.rishiz.acharyaprashant.view.SplashScreen
import net.rishiz.acharyaprashant.viewmodel.MainViewModel


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val actions= remember(navController){MainActions(navController)}
    val context= LocalContext.current
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController,actions)
        }
        composable(Screen.MainScreen.route) {
            val viewModel:MainViewModel=viewModel(
                factory= HiltViewModelFactory(context,it))
            viewModel.getBookData()
            MainScreen(actions,viewModel)
        }
        composable("description_screen") {
            //DescScreen()
        }
    }
}

class MainActions(navController: NavController) {
    val backpress:() -> Unit={
        navController.navigateUp()
    }
    val launchScreen:()->Unit={

    }
}




