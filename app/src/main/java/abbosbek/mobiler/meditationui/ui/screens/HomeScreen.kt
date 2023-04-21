package abbosbek.mobiler.meditationui.ui.screens

import abbosbek.mobiler.meditationui.R
import abbosbek.mobiler.meditationui.model.BottomMenuContent
import abbosbek.mobiler.meditationui.model.Feature
import abbosbek.mobiler.meditationui.ui.theme.AquaBlue
import abbosbek.mobiler.meditationui.ui.theme.Beige1
import abbosbek.mobiler.meditationui.ui.theme.BlueViolet1
import abbosbek.mobiler.meditationui.ui.theme.ButtonBlue
import abbosbek.mobiler.meditationui.ui.theme.DarkerButtonBlue
import abbosbek.mobiler.meditationui.ui.theme.DeepBlue
import abbosbek.mobiler.meditationui.ui.theme.LightGreen1
import abbosbek.mobiler.meditationui.ui.theme.LightRed
import abbosbek.mobiler.meditationui.ui.theme.OrangeYellow1
import abbosbek.mobiler.meditationui.ui.theme.TextWhite
import abbosbek.mobiler.meditationui.ui.theme.gothica
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DeepBlue)
    ) {
        Column() {
            TopAppBarHeader()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()

            FeatureSection(
                features = listOf(
                    Feature(
                        title = "Sleep meditation",
                        iconId = R.drawable.ic_headphone,
                        color = BlueViolet1,
                    ),
                    Feature(
                        title = "Tips for sleeping",
                        iconId = R.drawable.ic_videocam,
                        color = LightGreen1,
                    ),Feature(
                        title = "Night island",
                        iconId = R.drawable.ic_headphone,
                        color = OrangeYellow1,
                    ),Feature(
                        title = "Calming sounds",
                        iconId = R.drawable.ic_headphone,
                        color = Beige1,
                    ),
                )
            )
        }
        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Meditate", R.drawable.ic_bubble),
                BottomMenuContent("Sleep", R.drawable.ic_moon),
                BottomMenuContent("Music", R.drawable.ic_music),
                BottomMenuContent("Profile", R.drawable.ic_profile),
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighLightColor : Color = ButtonBlue,
    activeTextColor : Color = Color.White,
    inactiveTextColor : Color = AquaBlue,
    initialSelectedItemIndex : Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(color = DeepBlue)
            .padding(15.dp)
    ) {

        items.forEachIndexed { index, bottomMenuContent ->
           BottomMenuItem(
               item = bottomMenuContent,
               isSelected = index == selectedItemIndex,
               activeTextColor = activeTextColor,
               inactiveTextColor = inactiveTextColor,
               activeHighLightColor = activeHighLightColor
           ){
               selectedItemIndex = index
           }
        }

    }
}

@Composable
fun BottomMenuItem(
    item : BottomMenuContent,
    isSelected : Boolean = false,
    activeHighLightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick : () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighLightColor else Color.Transparent)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = "",
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }

}

@Composable
fun FeatureSection(features: List<Feature>) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Featured",
            style = TextStyle(
                fontFamily = gothica,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(15.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 7.5.dp,
                end = 7.5.dp,
                bottom = 100.dp
            )
        ){
            items(features.size){
                FeatureItem(feature = features[it])
            }
        }
    }

}

@Composable
fun FeatureItem(feature: Feature) {

    Box(modifier = Modifier
        .size(150.dp)
        .padding(7.5.dp)
        .clip(RoundedCornerShape(4.dp))
        .clickable { }
        .background(color = feature.color)
        .padding(12.dp)
    ){
        Text(
            text = feature.title,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = gothica,
                color = Color.White
            ),
            modifier = Modifier.align(Alignment.TopStart)
        )

        Icon(
            painter = painterResource(id = feature.iconId),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.BottomStart)
        )
        
       Box(
           modifier = Modifier
               .clip(RoundedCornerShape(8.dp))
               .background(color = ButtonBlue)
               .align(Alignment.BottomEnd)
               .padding(horizontal = 8.dp, vertical = 4.dp),
           contentAlignment = Alignment.Center
       ) {
            Text(
                text = "Start",
                style = TextStyle(
                    fontFamily = gothica,
                    color = Color.White,
                    fontSize = 14.sp
                )
            )
        }

    }

}

@Composable
fun CurrentMeditation(
    color : Color = LightRed
) {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(color = color)
            .padding(horizontal = 16.dp, vertical = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column{
            Text(
                text = "Daily Thought",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite,
                    fontFamily = gothica
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Meditation 3-10 min",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextWhite,
                    fontFamily = gothica
                )
            )
        }
        
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color = ButtonBlue)
                .clickable { }
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "",
                tint = Color.White
            )
        }

    }

}

@Composable
fun ChipSection(chips : List<String>) {

    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    
    LazyRow{
        items(chips.size){
            Box(modifier = Modifier
                .padding(
                    start = 15.dp,
                    top = 15.dp,
                    bottom = 15.dp
                )
                .clickable {
                    selectedChipIndex = it
                }
                .clip(RoundedCornerShape(4.dp))
                .background(
                    if (selectedChipIndex == it) ButtonBlue
                    else DarkerButtonBlue
                )
                .padding(15.dp)
            ){
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
    

}

@Composable
fun TopAppBarHeader(name : String = "Abbosbek") {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, ${name}",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite,
                    fontFamily = gothica
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "We wish you have a good day",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkerButtonBlue,
                    fontFamily = gothica
                )
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.size(32.dp)
        )

    }

}
