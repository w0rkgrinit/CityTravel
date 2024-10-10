package com.example.mycityscreen.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mycityscreen.R
import com.example.mycityscreen.database.City
import com.example.mycityscreen.database.CityRepository
import com.example.mycityscreen.database.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CityViewModel(private val repository: CityRepository) : ViewModel() {

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities.asStateFlow()

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations: StateFlow<List<Location>> = _locations.asStateFlow()

    private val _locationsByCategory = MutableStateFlow<List<Location>>(emptyList())
    val locationsByCategory: StateFlow<List<Location>> = _locationsByCategory.asStateFlow()

    init {
        loadCitiesAndLocations()
    }

    private fun loadCitiesAndLocations() {
        try {
            _cities.value = repository.getCities()
            _locations.value = repository.getAllLocations()
        } catch (e: Exception) {
            _cities.value = emptyList()
            _locations.value = emptyList()
        }
    }


    fun fetchLocationsForCity(cityId: Int) {
        try {
            val locations = repository.getLocationsForCity(cityId)
            _locations.value = locations
            Log.d("CityViewModel", "Fetched locations for city $cityId: $locations")
        } catch (e: Exception) {
            _locations.value = emptyList()
            Log.e("CityViewModel", "Error fetching locations for city $cityId", e)
        }
    }

    fun getLocationById(locationId: Int): Location {
        return locations.value.firstOrNull { it.id == locationId }
            ?: throw IllegalArgumentException("Location not found with ID $locationId")
    }

    fun getCityById(cityId: Int): City? {
        return cities.value.firstOrNull { it.id == cityId }
    }
    private var _shuffledLocations: List<Location>? = null

    fun getShuffledLocations(): List<Location> {
        if (_shuffledLocations == null) {
            _shuffledLocations = _locations.value.shuffled()
        }
        return _shuffledLocations!!
    }

    fun loadLocationsByCategory(categoryId: Int) {
        try {
            _locationsByCategory.value = repository.getLocationsByCategory(categoryId)
        } catch (e: Exception) {
            _locationsByCategory.value = emptyList()
        }
    }


    fun categoryNameToId(name: String): Int {
        return when (name) {

            "Entertainment" -> R.string.entertainment
            "Shopping Location" -> R.string.shopping
            "Historical Location" -> R.string.history
            "Resorts and Hotels" -> R.string.hotel
            "Nature and Wildlife" -> R.string.nature
            else -> R.string.app_name
        }
    }

    fun getCategoryNameById(categoryId: Int): String {
        return when (categoryId) {
            R.string.entertainment -> "Entertainment"
            R.string.shopping -> "Shopping Location"
            R.string.history -> "Historical Location"
            R.string.hotel -> "Resorts and Hotels"
            R.string.nature -> "Nature and Wildlife"
            else -> "Unknown Category"
        }
    }
}
