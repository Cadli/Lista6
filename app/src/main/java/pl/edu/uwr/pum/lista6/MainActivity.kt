package pl.edu.uwr.pum.lista6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.edu.uwr.pum.lista6.ui.theme.Lista6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lista6Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel: UserViewModel = viewModel()
                    val response by viewModel.users.collectAsStateWithLifecycle()

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "usersList") {
                        composable("usersList") {

                            UsersListScreen(navController = navController, response = response)
                        }

                        composable("userDetails/{userId}") { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("userId") ?: ""
                            val user = viewModel.getUserById(userId)
                            UserDetailsScreen(navController = navController, user = user)
                        }

                    }
                }
            }
        }
    }
}
