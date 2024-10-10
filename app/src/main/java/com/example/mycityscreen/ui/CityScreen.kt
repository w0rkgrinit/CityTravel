package com.example.mycityscreen.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import com.example.mycityscreen.model.CityViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycityscreen.R
import com.example.mycityscreen.TopBar
import com.example.mycityscreen.database.City
import com.example.mycityscreen.database.Location

@Composable
fun CityListScreen(viewModel: CityViewModel, onCityClick: (Int) -> Unit, navController: NavController) {
    val cities = viewModel.cities.collectAsState().value
    val listState = rememberLazyListState()
    val locations = viewModel.locations.collectAsState().value
    val shuffledLocation = remember { viewModel.getShuffledLocations() }
    val limitedLocations = remember { shuffledLocation.take(20) }


    Scaffold(
        topBar = {
            TopBar(
                title = "Cities",
                showBackIcon = false
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            CategoryListScreen(viewModel = viewModel, navController = navController)
            LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = listState
        ) {
            items(cities.size + 1) { index ->
                when (index) {
                    3 -> {

                        if (shuffledLocation.isNotEmpty()) {
                            Famous(
                                locations = limitedLocations,
                                navController = navController,

                            )
                        }
                    }
                    else -> {
                        val cityIndex = if (index > 2) index - 1 else index
                        CityItem(
                            city = cities[cityIndex],
                            onClick = { onCityClick(cities[cityIndex].id) },
                            )
                    }
                }
            }
        }
    }
    }
}
@Composable
fun CityItem(modifier: Modifier = Modifier, city: City, onClick:()-> Unit,) {
    Column(modifier = modifier
        .padding(10.dp)
    )
    {
        Card(
            shape = RoundedCornerShape(
                topEnd = 10.dp,
                bottomEnd = 10.dp,
                topStart = 10.dp,
                bottomStart = 10.dp
            ),

            modifier = modifier
                .clickable(onClick = onClick)
                .height(215.dp)
                .fillMaxWidth(),

        ) {
            Box(modifier = Modifier
                .fillMaxSize()
            ){
            Column(modifier = modifier.padding(top = 5.dp)) {
                Row(modifier = modifier) {
                    Box(modifier = modifier.padding(start = 10.dp, top = 5.dp, end = 5.dp, )){
                    Image(
                        modifier = modifier
                            .height(115.dp)
                            .width(230.dp)
                            .clip(RoundedCornerShape(10.dp))

                            ,
                        painter = painterResource(id = city.img),
                        contentDescription = city.name,
                        contentScale = ContentScale.Crop
                    )}
                    Column(
                        modifier = modifier
                            .padding( end = 4.dp, start = 4.dp)
                    ) {


                        Text(
                            fontSize = 10.sp,
                            text = "City Population:",
                            color = MaterialTheme.colorScheme.tertiary,

                            lineHeight = 15.sp,
                            modifier = modifier.padding(top = 10.dp)
                        )
                        Text(
                            text = city.pop,
                            color = MaterialTheme.colorScheme.primary
                            )
                        Text(
                            text = "Tourist Ratings:",
                            color = MaterialTheme.colorScheme.tertiary,
                            maxLines = 2,
                            fontSize = 11.sp,
                            modifier = modifier.padding(top = 5.dp)
                        )
                        Text(
                            text = city.rating + "%",
                            color = MaterialTheme.colorScheme.primary

                        )
                    }
                }
                Text(
                    fontSize = 30.sp,
                    text = city.name,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = modifier.padding(start = 10.dp)
                )
                Text(
                    fontSize = 15.sp,
                    text = "Location: " + stringResource(id = city.address),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = modifier.padding(start = 10.dp)
                )
            }
        }
    }
}
}

@Composable
fun Famous(
    navController: NavController,
    locations: List<Location>,


) {

    Column(modifier = Modifier.padding(10.dp)) {
        Card(
            colors = CardDefaults.cardColors(
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
        ) {

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 2.dp)
                ) {



                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.scrim)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Highest rated Locations:",
                            color = MaterialTheme.colorScheme.primary

                        )
                    }

                LazyHorizontalGrid(modifier = Modifier.padding(top = 5.dp), rows = GridCells.Fixed(1)) {
                    items(locations) { location ->
                        Rated(location = location , navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Rated(
    location: Location,
    navController: NavController
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .padding(start = 5.dp)
       ) {
        Card(onClick = { navController.navigate("location_detail/${location.id}") } ,modifier = Modifier.width(200.dp).padding(5.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)) {
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Image(painter = painterResource(id = location.limg),
                    contentDescription = location.lname,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(200.dp)
                )

                Text(
                    modifier = Modifier.padding(10.dp).weight(1f),

                    text = location.lname,
                    color = MaterialTheme.colorScheme.tertiary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2

                )
            }
        }
    }
}
data class Category(
    val name: String,
    @DrawableRes val imageResId: Int
)
@Composable
fun CategoryListScreen(
    viewModel: CityViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val categories = listOf(

        Category("Historical Location", R.drawable.history),
        Category("Entertainment", R.drawable.entertain),
        Category("Resorts and Hotels", R.drawable.hotels),
        Category("Shopping Location", R.drawable.shopping),
        Category("Nature and Wildlife" , R.drawable.nature)
        )

    LazyRow(
        modifier = modifier
            .padding(5.dp)
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        items(categories) { category ->
            val categoryId = viewModel.categoryNameToId(category.name)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .padding(start = 16.dp, top = 5.dp, end = 5.dp, bottom = 5.dp)
                    .width(120.dp)
            ) {
                Column(
                    modifier = Modifier
                        .clickable {
                            viewModel.loadLocationsByCategory(categoryId)
                            navController.navigate("filtered_location_list/$categoryId")
                        }
                        .padding(5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = MaterialTheme.colorScheme.background)
                        .width(110.dp)


            ) {


                    Text(
                         color = MaterialTheme.colorScheme.primary,
                         text = category.name,
                         fontSize = 16.sp,
                         maxLines = 2,
                            style = TextStyle(hyphens = Hyphens.Auto),
                            lineHeight = 20.sp,
                            modifier = modifier
                                .padding(5.dp)
                    )
                }
            }
        }
    }
}

