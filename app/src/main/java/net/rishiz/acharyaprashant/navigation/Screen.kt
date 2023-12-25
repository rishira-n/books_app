package net.rishiz.acharyaprashant.navigation

import androidx.annotation.StringRes
import net.rishiz.acharyaprashant.R

sealed class Screen (val route:String,@StringRes val strResId:Int){
    data object SplashScreen:Screen("splash_screen",R.string.splash_screen)
    data object MainScreen:Screen("main_screen", R.string.main_screen)
}