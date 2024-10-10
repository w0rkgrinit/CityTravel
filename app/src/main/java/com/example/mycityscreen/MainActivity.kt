@file:Suppress("NAME_SHADOWING")

package com.example.mycityscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycityscreen.database.CityRepository
import com.example.mycityscreen.model.CityViewModel
import com.example.mycityscreen.model.CityViewModelFactory
import com.example.mycityscreen.ui.CityListScreen
import com.example.mycityscreen.ui.FilteredLocationsDisplay
import com.example.mycityscreen.ui.LocationListScreen
import com.example.mycityscreen.ui.LocationDetail
import com.example.mycityscreen.ui.theme.MyCityScreenTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCityScreenTheme {
                MyApp()

            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    showBackIcon: Boolean = false,
    onBackClick: (() -> Unit)? = null,
) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.secondary,
            navigationIconContentColor = MaterialTheme.colorScheme.secondary
        ),
        navigationIcon = {
            if (showBackIcon) {
                    IconButton(onClick = { onBackClick?.invoke() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
            }
            else(null)
        },


    )
}


@Composable
fun MyApp() {
    val cityRepository = CityRepository()
    val cityViewModel: CityViewModel = viewModel(
        factory = CityViewModelFactory(cityRepository)
    )
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "city_list"
    ) {
        composable("city_list") {
            CityListScreen(
                viewModel = cityViewModel,
                onCityClick = { cityId ->
                    navController.navigate("location_list/$cityId")
                },
                navController = navController
            )
        }
        composable("location_list/{cityId}") { backStackEntry ->
            val cityId = backStackEntry.arguments?.getString("cityId")?.toIntOrNull() ?: 0
            LocationListScreen(
                cityId = cityId,
                viewModel = cityViewModel,
                navController = navController
            )
        }
        composable("filtered_location_list/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull() ?: 0
            val categoryName = cityViewModel.getCategoryNameById(categoryId)

            if (categoryId > 0) {
                FilteredLocationsDisplay(
                    categoryId = categoryId,
                    viewModel = cityViewModel,
                    navController = navController
                )
            } else {

                Text(text = "Invalid Category")
            }
        }
        composable("location_detail/{locationId}") { backStackEntry ->
            val locationId = backStackEntry.arguments?.getString("locationId")?.toIntOrNull() ?: 0
            val cityId = backStackEntry.arguments?.getString("cityId")?.toIntOrNull() ?: 0
            val selectedLocation = cityViewModel.getLocationById(locationId)


            LocationDetail(
                selectLoc = selectedLocation,
                onBackPressed = { navController.navigateUp() },
                contentPadding = PaddingValues(),

            )
        }
    }
}




