package com.example.medicoapp.ui
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.medicoapp.ui.cita.CitaDestination
import com.example.medicoapp.ui.cita.CitaScreen
import com.example.medicoapp.ui.medico.MedicoDestination
import com.example.medicoapp.ui.medico.MedicoScreen
@Composable
fun MedicoCitaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = MedicoDestination.route,
        modifier = modifier
    ) {
        composable(route = MedicoDestination.route) {
            MedicoScreen(
                navigateToDetails = {
                    navController.navigate("${MedicoDestination.route}/${it}")
                },
            )
        }
        composable(
            route = CitaDestination.routeWithArgs,
            arguments = listOf(navArgument(CitaDestination.citaIdArg) {
                type = NavType.IntType
            })
        ) {
            CitaScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}