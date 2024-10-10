package com.example.mycityscreen.ui



import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycityscreen.model.CityViewModel
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import com.example.mycityscreen.database.Location
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycityscreen.R
import com.example.mycityscreen.TopBar


@Composable
fun LocationListScreen(
    cityId: Int,
    viewModel: CityViewModel,
    navController: NavController
) {
    LaunchedEffect(cityId) {
        viewModel.fetchLocationsForCity(cityId)
    }
    val locations by viewModel.locations.collectAsState()
    val cityName = viewModel.getCityById(cityId)?.name ?: "Default City"

    Scaffold(
        topBar = {
            TopBar(
                title = cityName,
                showBackIcon = true,
                onBackClick = { navController.navigateUp() }
            )
        }
    ) { paddingValues ->
            LazyVerticalGrid(
                contentPadding = paddingValues,
                columns = GridCells.Fixed(2),
            ) {
                item(span = { GridItemSpan(2) }){
                    CityDetailCard(
                        cityId = cityId,
                        viewModel =viewModel,
                    )
                }
                items(locations) { location ->
                    LocationItem(
                        location = location,
                        onCardClick = {
                            navController.navigate("location_detail/${location.id}")
                    }
                )
            }
        }
    }
}





@Composable
fun CityDetailCard(
    modifier: Modifier = Modifier,
    cityId: Int,
    viewModel: CityViewModel,
){
    val Gradient = remember {
        Brush.horizontalGradient(
            listOf(
                Color(0xFF2196F3),
                Color(0xFF03A9F4),
                Color(0xFF2196F3),
                Color(0xFF03A9F4),
                Color(0xFF2196F3),
                Color(0xFF03A9F4),
                Color(0xFF2196F3),
                Color(0xFF03A9F4),

                )
        )
    }

    val borderWidth = 4.dp
    val cityName = viewModel.getCityById(cityId)?.name ?: "Default City"
    val cityImg = viewModel.getCityById(cityId)?.img ?: R.drawable.champs
    val cityDes = viewModel.getCityById(cityId)?.des ?: R.string.cd1
    Card (modifier = modifier
        .fillMaxWidth().clip(RoundedCornerShape(topEnd = 0.dp, bottomEnd = 20.dp, topStart = 0.dp, bottomStart = 20.dp))
        .height(260.dp)){
        Column {
            Row {
                Image(
                    modifier = modifier
                        .padding(start = 10.dp)
                        .padding(top = 10.dp)
                        .size(170.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            BorderStroke(borderWidth, Gradient),
                            RoundedCornerShape(10.dp)

                        )
                        .background(Color.Green),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = cityImg),
                    contentDescription = null,

                )
                Text(
                    text = stringResource(id = cityDes),
                    modifier = modifier.padding(10.dp),
                    fontSize = 10.sp,
                    lineHeight = 14.sp

                )
            }
            Text(

                text = cityName,
                style = TextStyle(
                    brush = Gradient
                ) ,
                fontSize = 35.sp,
                modifier = modifier
                    .padding(start = 10.dp)
            )
        }
    }
}
@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    location: Location,
    onCardClick: () -> Unit
) {

    Column {
        Card(
            modifier = modifier
                .height(290.dp)
                .padding(10.dp)
                .fillMaxWidth(),

            onClick = onCardClick
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()

            ){
            Column(
            ) {
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                ) {

                    Text(
                        modifier = modifier.padding(4.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = location.category),
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontWeight = FontWeight.Bold
                        )
                }
                Box (
                    modifier = modifier.height(10.dp).fillMaxWidth()
                ){

                }
                Image(
                    modifier = modifier
                        .size(140.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .align(Alignment.CenterHorizontally)
                    ,
                    alignment = Alignment.Center,
                    painter = painterResource(id = location.limg),
                    contentDescription = location.lname,
                    contentScale = ContentScale.Crop

                )
                Text(
                    text = location.lname,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        ,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = location.ldescription,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    maxLines = 2,
                    modifier = modifier.padding(start = 5.dp, top = 4.dp),
                    overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}
@Composable
fun FilteredLocationsDisplay(
    categoryId: Int,
    viewModel: CityViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val categoryName = viewModel.getCategoryNameById(categoryId)
    val locations by viewModel.locationsByCategory.collectAsState()

    LaunchedEffect(categoryId) {
        viewModel.loadLocationsByCategory(categoryId)
    }

    Scaffold(
        topBar = {
            TopBar(
                title =categoryName,
                showBackIcon = true,
                onBackClick = { navController.navigateUp() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (locations.isEmpty()) {
                Text(
                    text = "No locations available for this category.",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(locations) { location ->
                        FilteredLocationItem(
                            location = location,
                            onCardClick = {
                                val locationExists = locations.any { it.id == location.id }
                                if (locationExists) {
                                    navController.navigate("location_detail/${location.id}")
                                } else {
                                    Log.e("FilteredLocationsDisplay", "Location with id ${location.id} not found")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FilteredLocationItem(
    location: Location,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {



    if (location == null) {
        Log.e("FilteredLocationItem", "Location is null")
        return
    }
    Column {
        Card(
            modifier = modifier
                .height(290.dp)
                .padding(10.dp)
                .fillMaxWidth(),

            onClick = onCardClick
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()


            ) {


                Column(
                ) {
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        ),
                        shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                    ) {

                        Text(
                            modifier = modifier.padding(4.dp),
                            textAlign = TextAlign.Center,
                            text = stringResource(id = location.category),
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.inversePrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Image(
                        modifier = modifier
                            .size(140.dp)

                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        alignment = Alignment.Center,
                        painter = painterResource(id = location.limg),
                        contentDescription = location.lname,
                        contentScale = ContentScale.Crop

                        )
                    Text(

                        text = location.lname,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Left
                    )
                    Text(
                        text = location.ldescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black,
                        modifier = modifier.padding(start = 5.dp, top = 4.dp)
                    )
                }
            }
        }
    }
}