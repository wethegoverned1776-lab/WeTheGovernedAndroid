package net.wetheGoverned

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import net.wetheGoverned.ui.VerifiedNetworkApp
import net.wetheGoverned.util.AppContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        
        AppContext.context = applicationContext

        setContent {
            MaterialTheme {
                VerifiedNetworkApp()
            }
        }
    }
}
