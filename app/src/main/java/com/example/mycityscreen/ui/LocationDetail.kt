package com.example.mycityscreen.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycityscreen.TopBar
import com.example.mycityscreen.database.Location
import com.example.mycityscreen.model.CityViewModel


@Composable
fun LocationDetail(
    selectLoc: Location,
    onBackPressed: ()-> Unit,
    contentPadding: PaddingValues,


    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }

    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    val borderWidth = 4.dp


    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = selectLoc.category),
                showBackIcon = true,
                onBackClick = { onBackPressed() }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .verticalScroll(state = scrollState)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection),
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            Column {
                Box {
                     Image(
                            painter = painterResource(id =selectLoc.banner),
                            contentDescription = null,
                            modifier = modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            alignment = Alignment.TopCenter,
                            contentScale = ContentScale.Crop
                        )
                    Column(
                        modifier = modifier
                            .align(Alignment.TopEnd)
                            .fillMaxHeight()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                                    0f,
                                    400f,
                                )
                            )
                    ) {
                        Spacer(modifier = Modifier
                            .height(3.dp)
                            .fillMaxWidth())
                        Box (
                            modifier = modifier
                                .background(color = MaterialTheme.colorScheme.tertiary)
                                .padding(start = 10.dp)
                        ){
                            Text(
                                text = selectLoc.lname,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                modifier = modifier.padding(5.dp)

                            )
                        }
                    }
                }
                Box (
                    modifier = modifier.padding(10.dp)
                ){
                    Text(

                        text = stringResource(id = selectLoc.desc),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier.padding(
                            top = 100.dp

                        )
                    )
                }
            }
        }
        Box(modifier = modifier
            .padding(top = 120.dp, start = 20.dp)
        ) {
            Image(
                painter = painterResource(id = selectLoc.limg),
                contentDescription = selectLoc.lname,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        BorderStroke(borderWidth, MaterialTheme.colorScheme.tertiary),
                        RoundedCornerShape(10.dp)
                    )
            )
        }
    }
}